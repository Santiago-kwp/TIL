# Chapter 5: 사용자를 파악하기 위한 데이터 추출

---

# 11강. 사용자 전체의 특징과 경향 찾기

사용자 속성과 행동 패턴을 분석하여 서비스 개선에 활용하는 방법

---

## 1. 사용자의 액션 수 집계하기

### 액션과 관련된 지표 집계
- **UU (Unique Users)**: 중복 없이 집계된 사용자 수
- **사용률 (usage_rate)**: 특정 액션 UU / 전체 UU
- **1인당 액션 수**: 액션 수 / 액션 UU

```sql
WITH stats AS (
    SELECT COUNT(DISTINCT session) AS total_uu
    FROM action_log
)
SELECT
    l.action,
    COUNT(DISTINCT l.session) AS action_uu,
    COUNT(1) AS action_count,
    s.total_uu,
    100.0 * COUNT(DISTINCT l.session) / s.total_uu AS usage_rate,
    1.0 * COUNT(1) / COUNT(DISTINCT l.session) AS count_per_user
FROM action_log AS l
CROSS JOIN stats AS s
GROUP BY l.action, s.total_uu;
```

### 로그인/비로그인 사용자 구분 집계
```sql
WITH action_log_with_status AS (
    SELECT
        session,
        user_id,
        action,
        CASE WHEN COALESCE(user_id, '') <> '' THEN 'login' ELSE 'guest' END
            AS login_status
    FROM action_log
)
SELECT
    COALESCE(action, 'all') AS action,
    COALESCE(login_status, 'all') AS login_status,
    COUNT(DISTINCT session) AS action_uu,
    COUNT(1) AS action_count
FROM action_log_with_status
GROUP BY ROLLUP(action, login_status);
```

### 회원/비회원 구분 (세션 내 로그인 이력 기반)
```sql
WITH action_log_with_status AS (
    SELECT
        session,
        user_id,
        action,
        CASE
            WHEN COALESCE(
                MAX(user_id) OVER(
                    PARTITION BY session
                    ORDER BY stamp
                    ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
                ), '') <> ''
            THEN 'member'
            ELSE 'none'
        END AS member_status,
        stamp
    FROM action_log
)
SELECT * FROM action_log_with_status;
```

### 회원 판별 핵심 기법
- `MAX(user_id) OVER(...)`: 세션 내에서 한 번이라도 로그인하면 user_id가 전파됨
- `ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW`: 시간 순서대로 누적

---

## 2. 연령별 구분 집계하기

### 생년월일로 나이 계산
```sql
WITH mst_users_with_age AS (
    SELECT
        *,
        FLOOR(
            (20170101 - CAST(REPLACE(SUBSTRING(birth_date, 1, 10), '-', '') AS INTEGER))
            / 10000
        ) AS age
    FROM mst_users
)
SELECT user_id, sex, birth_date, age FROM mst_users_with_age;
```

### 성별 + 연령대 카테고리 분류
| 카테고리 | 설명 |
|---------|------|
| C | 4~12세 (Child) |
| T | 13~19세 (Teen) |
| M1/F1 | 20~34세 남성/여성 |
| M2/F2 | 35~49세 남성/여성 |
| M3/F3 | 50세 이상 남성/여성 |

```sql
WITH mst_users_with_category AS (
    SELECT
        user_id,
        sex,
        age,
        CONCAT(
            CASE WHEN 20 <= age THEN sex ELSE '' END,
            CASE
                WHEN age BETWEEN 4 AND 12 THEN 'C'
                WHEN age BETWEEN 13 AND 19 THEN 'T'
                WHEN age BETWEEN 20 AND 34 THEN '1'
                WHEN age BETWEEN 35 AND 49 THEN '2'
                WHEN age >= 50 THEN '3'
            END
        ) AS category
    FROM mst_users_with_age
)
SELECT category, COUNT(1) AS user_count
FROM mst_users_with_category
GROUP BY category;
```

---

## 3. 연령별 구분의 특징 추출하기

연령대별로 어떤 카테고리 상품을 구매하는지 분석

```sql
SELECT
    p.category AS product_category,
    u.category AS user_category,
    COUNT(*) AS purchase_count
FROM action_log AS p
JOIN mst_users_with_category AS u
    ON p.user_id = u.user_id
WHERE action = 'purchase'
GROUP BY p.category, u.category
ORDER BY p.category, u.category;
```

---

## 4. 사용자의 방문 빈도 집계하기

한 주 동안 며칠 서비스를 사용하는지 분석

```sql
WITH action_day_count_per_user AS (
    SELECT
        user_id,
        COUNT(DISTINCT SUBSTRING(stamp, 1, 10)) AS action_day_count
    FROM action_log
    WHERE SUBSTRING(stamp, 1, 10) BETWEEN '2016-11-01' AND '2016-11-07'
    GROUP BY user_id
)
SELECT
    action_day_count,
    COUNT(DISTINCT user_id) AS user_count
FROM action_day_count_per_user
GROUP BY action_day_count
ORDER BY action_day_count;
```

---

## 5. 벤 다이어그램으로 사용자 액션 집계하기

### 액션 플래그 생성
```sql
WITH user_action_flag AS (
    SELECT
        user_id,
        SIGN(SUM(CASE WHEN action = 'purchase' THEN 1 ELSE 0 END)) AS has_purchase,
        SIGN(SUM(CASE WHEN action = 'review' THEN 1 ELSE 0 END)) AS has_review,
        SIGN(SUM(CASE WHEN action = 'favorite' THEN 1 ELSE 0 END)) AS has_favorite
    FROM action_log
    GROUP BY user_id
)
SELECT * FROM user_action_flag;
```

### CUBE로 모든 액션 조합 집계
```sql
WITH user_action_flag AS (
    SELECT
        user_id,
        SIGN(SUM(CASE WHEN action = 'purchase' THEN 1 ELSE 0 END)) AS has_purchase,
        SIGN(SUM(CASE WHEN action = 'review' THEN 1 ELSE 0 END)) AS has_review,
        SIGN(SUM(CASE WHEN action = 'favorite' THEN 1 ELSE 0 END)) AS has_favorite
    FROM action_log
    GROUP BY user_id
),
action_venn_diagram AS (
    SELECT
        has_purchase,
        has_review,
        has_favorite,
        COUNT(1) AS users
    FROM user_action_flag
    GROUP BY CUBE(has_purchase, has_review, has_favorite)
)
SELECT
    CASE has_purchase WHEN 1 THEN 'purchase' WHEN 0 THEN 'not purchase' ELSE 'any' END AS has_purchase,
    CASE has_review WHEN 1 THEN 'review' WHEN 0 THEN 'not review' ELSE 'any' END AS has_review,
    CASE has_favorite WHEN 1 THEN 'favorite' WHEN 0 THEN 'not favorite' ELSE 'any' END AS has_favorite,
    users,
    100.0 * users / NULLIF(
        SUM(CASE WHEN has_purchase IS NULL AND has_review IS NULL AND has_favorite IS NULL
            THEN users ELSE 0 END) OVER(), 0
    ) AS ratio
FROM action_venn_diagram
ORDER BY has_purchase, has_review, has_favorite;
```

### CUBE vs ROLLUP
- `ROLLUP`: 계층적 소계 (A > B > 전체)
- `CUBE`: 모든 조합의 소계 (A, B, A+B, 전체)

---

## 6. Decile 분석으로 사용자를 10단계 그룹으로 나누기

구매 금액 기준으로 사용자를 10등분하여 분석

### Decile 분석 과정
1. 구매 금액 순으로 정렬
2. 상위 10%씩 Decile 1~10 할당 (NTILE 함수)
3. 그룹별 구매 금액 합계/평균 집계
4. 구성비, 구성비누계 계산

```sql
WITH user_purchase_amount AS (
    SELECT user_id, SUM(amount) AS purchase_amount
    FROM action_log
    WHERE action = 'purchase'
    GROUP BY user_id
),
users_with_decile AS (
    SELECT
        user_id,
        purchase_amount,
        NTILE(10) OVER(ORDER BY purchase_amount DESC) AS decile
    FROM user_purchase_amount
),
decile_with_purchase_amount AS (
    SELECT
        decile,
        SUM(purchase_amount) AS amount,
        AVG(purchase_amount) AS avg_amount,
        SUM(SUM(purchase_amount)) OVER(ORDER BY decile) AS cumulative_amount,
        SUM(SUM(purchase_amount)) OVER() AS total_amount
    FROM users_with_decile
    GROUP BY decile
)
SELECT
    decile,
    amount,
    avg_amount,
    100.0 * amount / total_amount AS total_ratio,
    100.0 * cumulative_amount / total_amount AS cumulative_ratio
FROM decile_with_purchase_amount;
```

### NTILE 함수
- `NTILE(n)`: 데이터를 n개의 동일한 크기 그룹으로 분할
- 정렬 후 순번 기반으로 그룹 할당

---

## 7. RFM 분석으로 사용자를 3가지 관점의 그룹으로 나누기

Decile 분석의 한계(기간 의존성)를 보완하는 다차원 분석

### RFM 3가지 지표
| 지표 | 설명 | 우량 고객 기준 |
|------|------|--------------|
| **R (Recency)** | 최근 구매일 | 최근에 구매 |
| **F (Frequency)** | 구매 횟수 | 자주 구매 |
| **M (Monetary)** | 구매 금액 합계 | 많이 구매 |

### RFM 지표 집계
```sql
WITH purchase_log AS (
    SELECT
        user_id,
        amount,
        SUBSTRING(stamp, 1, 10) AS dt
    FROM action_log
    WHERE action = 'purchase'
),
user_rfm AS (
    SELECT
        user_id,
        MAX(dt) AS recent_date,
        CURRENT_DATE - MAX(dt::date) AS recency,
        COUNT(dt) AS frequency,
        SUM(amount) AS monetary
    FROM purchase_log
    GROUP BY user_id
)
SELECT * FROM user_rfm;
```

### RFM 랭크 부여 (각 지표 1~5점)
```sql
WITH user_rfm_rank AS (
    SELECT
        user_id,
        recency,
        frequency,
        monetary,
        CASE
            WHEN recency < 14 THEN 5
            WHEN recency < 28 THEN 4
            WHEN recency < 60 THEN 3
            WHEN recency < 90 THEN 2
            ELSE 1
        END AS r,
        CASE
            WHEN 20 <= frequency THEN 5
            WHEN 10 <= frequency THEN 4
            WHEN 5 <= frequency THEN 3
            WHEN 2 <= frequency THEN 2
            ELSE 1
        END AS f,
        CASE
            WHEN 300000 <= monetary THEN 5
            WHEN 100000 <= monetary THEN 4
            WHEN 30000 <= monetary THEN 3
            WHEN 5000 <= monetary THEN 2
            ELSE 1
        END AS m
    FROM user_rfm
)
SELECT * FROM user_rfm_rank;
```

### RFM 활용 방법

#### 1차원: 통합 랭크 (R+F+M)
```sql
SELECT
    r + f + m AS total_rank,
    COUNT(user_id)
FROM user_rfm_rank
GROUP BY r + f + m
ORDER BY total_rank DESC;
```

#### 2차원: R x F 매트릭스
```sql
SELECT
    CONCAT('r_', r) AS r_rank,
    COUNT(CASE WHEN f = 5 THEN 1 END) AS f_5,
    COUNT(CASE WHEN f = 4 THEN 1 END) AS f_4,
    COUNT(CASE WHEN f = 3 THEN 1 END) AS f_3,
    COUNT(CASE WHEN f = 2 THEN 1 END) AS f_2,
    COUNT(CASE WHEN f = 1 THEN 1 END) AS f_1
FROM user_rfm_rank
GROUP BY r
ORDER BY r_rank DESC;
```

### RFM 기반 마케팅 전략
| 사용자 상태 | 대책 |
|-----------|------|
| 신규 → 단골 | 신규 배송 무료 쿠폰 |
| 안정 → 단골 | SNS 팔로우 유도 |
| 단골 이탈 전조 | 포인트 잔고 통지, 신규 상품 메일 |
| 신규 이탈 전조 | 신규 배송 무료 쿠폰 |

---

# 핵심 함수 요약

## 11강 함수/구문
| 함수/구문 | 용도 |
|----------|------|
| `COUNT(DISTINCT col)` | 중복 제거 카운트 (UU 계산) |
| `COALESCE(col, '')` | NULL 처리 (로그인 판별) |
| `MAX() OVER(PARTITION BY ... ORDER BY ...)` | 세션 내 로그인 이력 전파 |
| `ROLLUP(col1, col2)` | 계층적 소계/총계 |
| `CUBE(col1, col2, col3)` | 모든 조합의 소계 (벤 다이어그램) |
| `SIGN()` | 0/1 플래그 생성 |
| `NTILE(n)` | n등분 그룹 할당 (Decile) |
| `SUM(SUM()) OVER()` | 집계 후 누적합 (구성비누계) |
| `generate_series(1, n)` | 순번 테이블 생성 (PostgreSQL) |

## 주요 분석 패턴

### 사용률 패턴
```sql
100.0 * COUNT(DISTINCT session) / total_uu AS usage_rate
```

### 연령대 분류 패턴
```sql
FLOOR((기준일정수 - 생년월일정수) / 10000) AS age
```

### Decile 분석 패턴
```sql
NTILE(10) OVER(ORDER BY purchase_amount DESC) AS decile
```

### RFM 분석 패턴
```sql
CURRENT_DATE - MAX(dt::date) AS recency  -- R
COUNT(dt) AS frequency                    -- F
SUM(amount) AS monetary                   -- M
```
