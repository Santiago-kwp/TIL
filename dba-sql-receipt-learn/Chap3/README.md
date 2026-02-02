# Chapter 3: 데이터 가공을 위한 SQL

## 개요
여러 값을 비교하고 다양한 데이터 타입을 다루는 SQL 기법을 학습합니다.

---

## 1. 여러 값 비교하기

### 분기별 매출 증감 판정
- `CASE` 문을 사용해 분기별 매출 증감을 '+', '-', ' '로 표시
- `SIGN()` 함수: 양수(1), 0, 음수(-1) 반환

```sql
SELECT year, q1, q2,
    CASE
        WHEN q1 < q2 THEN '+'
        WHEN q1 = q2 THEN ' '
        ELSE '-'
    END AS judge_q1_q2,
    SIGN(q2-q1) AS sign_q2_q1
FROM quarterly_sales;
```

### NULL을 포함한 연산
- `COALESCE(값, 기본값)`: NULL일 경우 기본값 반환
- `NULLIF(값1, 값2)`: 값1과 값2가 같으면 NULL 반환 (0으로 나누기 방지에 유용)

```sql
-- 연간 평균 계산 (NULL 제외)
SELECT year,
    (COALESCE(q1,0) + COALESCE(q2,0) + COALESCE(q3,0) + COALESCE(q4,0))
    / NULLIF(SIGN(COALESCE(q1,0)) + SIGN(COALESCE(q2,0)) + SIGN(COALESCE(q3,0)) + SIGN(COALESCE(q4,0)), 0)
    AS average
FROM quarterly_sales;
```

---

## 2. CTR(클릭률) 계산

### 0으로 나누기 방지
```sql
SELECT dt, ad_id,
    -- CASE 문 사용
    CASE WHEN impressions > 0 THEN 100.0 * clicks / impressions END AS ctr_by_case,
    -- NULLIF 사용
    100.0 * clicks / NULLIF(impressions, 0) AS ctr_by_nullif
FROM advertising_stats;
```

---

## 3. 거리 계산

### 1차원 거리
- `ABS()`: 절대값
- `SQRT()`, `POWER()`: 제곱근과 거듭제곱

```sql
SELECT ABS(x1-x2) AS abs,
       SQRT(POWER(x1-x2, 2)) AS rms
FROM location_1d;
```

### 2차원 거리 (유클리드 거리)
- PostgreSQL의 `POINT` 자료형과 `<->` 연산자 활용

```sql
SELECT SQRT(POWER(x1-x2,2) + POWER(y1-y2,2)) AS dist,
       POINT(x1,y1) <-> POINT(x2,y2) AS dist_point
FROM location_2d;
```

---

## 4. 날짜/시간 다루기

### 날짜 연산
- `INTERVAL`: 시간 간격 더하기/빼기

```sql
SELECT
    register_stamp + INTERVAL '1 hour' AS after_1_hour,
    register_stamp - INTERVAL '30 minutes' AS before_30_minutes,
    (register_stamp::date + INTERVAL '1 day')::date AS after_1_day
FROM mst_users_with_dates;
```

### 날짜 차이 계산
```sql
SELECT
    CURRENT_DATE - register_stamp::date AS diff_days
FROM mst_users_with_dates;
```

### 나이 계산
```sql
-- AGE 함수 사용
SELECT
    EXTRACT(YEAR FROM AGE(birth_date::date)) AS current_age,
    EXTRACT(YEAR FROM AGE(register_stamp::date, birth_date::date)) AS register_age
FROM mst_users_with_dates;

-- 문자열 연산으로 계산 (이식성 높음)
SELECT
    FLOOR((CAST(REPLACE(CAST(CURRENT_DATE AS TEXT), '-', '') AS INTEGER)
         - CAST(REPLACE(birth_date, '-', '') AS INTEGER)) / 10000) AS current_age
FROM mst_users_with_dates;
```

---

## 5. IP 주소 다루기

### inet 자료형 (PostgreSQL 전용)
```sql
-- IP 주소 비교
SELECT CAST('127.0.0.1' AS inet) < CAST('127.0.0.2' AS inet) AS lt;

-- 네트워크 범위 포함 여부 (<<, >> 연산자)
SELECT CAST('127.10.22.1' AS inet) << CAST('127.0.0.0/8' AS inet) AS is_contained;
```

### 문자열로 IP 주소 다루기
- `SPLIT_PART()`: 구분자로 문자열 분리
- `LPAD()`: 문자열 왼쪽 채우기

```sql
-- IP를 정수로 변환
SELECT ip,
    CAST(SPLIT_PART(ip, '.', 1) AS INTEGER) * 2^24
  + CAST(SPLIT_PART(ip, '.', 2) AS INTEGER) * 2^16
  + CAST(SPLIT_PART(ip, '.', 3) AS INTEGER) * 2^8
  + CAST(SPLIT_PART(ip, '.', 4) AS INTEGER) AS ip_integer
FROM (SELECT '192.168.0.1' AS ip) AS t;

-- IP를 0으로 패딩
SELECT ip,
    LPAD(SPLIT_PART(ip, '.', 1), 3, '0')
 || LPAD(SPLIT_PART(ip, '.', 2), 3, '0')
 || LPAD(SPLIT_PART(ip, '.', 3), 3, '0')
 || LPAD(SPLIT_PART(ip, '.', 4), 3, '0') AS ip_padding
FROM (SELECT '192.168.0.1' AS ip) AS t;
```

---

## 6. 윈도우 함수

### 순위 함수
| 함수 | 설명 |
|------|------|
| `ROW_NUMBER()` | 유일한 순위 부여 |
| `RANK()` | 동일 값에 같은 순위, 다음 순위 건너뜀 |
| `DENSE_RANK()` | 동일 값에 같은 순위, 다음 순위 이어감 |

### 이전/다음 행 참조
- `LAG(컬럼, n)`: n행 이전 값
- `LEAD(컬럼, n)`: n행 이후 값

```sql
SELECT product_id, score,
    ROW_NUMBER() OVER(ORDER BY score DESC) AS row,
    RANK() OVER(ORDER BY score DESC) AS rank,
    DENSE_RANK() OVER(ORDER BY score DESC) AS dense_rank,
    LAG(product_id) OVER(ORDER BY score DESC) AS lag1,
    LEAD(product_id) OVER(ORDER BY score DESC) AS lead1
FROM popular_products;
```

### 윈도우 프레임과 집계 함수
- `ROWS BETWEEN ... AND ...`: 윈도우 프레임 지정

```sql
SELECT product_id, score,
    -- 누계 점수
    SUM(score) OVER(ORDER BY score DESC
        ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS cum_score,
    -- 이동 평균 (앞뒤 1행 포함)
    AVG(score) OVER(ORDER BY score DESC
        ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS local_avg,
    -- 최고/최저 순위 상품
    FIRST_VALUE(product_id) OVER(ORDER BY score DESC
        ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS first_value,
    LAST_VALUE(product_id) OVER(ORDER BY score DESC
        ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS last_value
FROM popular_products;
```

### PARTITION BY - 그룹별 윈도우 함수
```sql
SELECT category, product_id, score,
    ROW_NUMBER() OVER(PARTITION BY category ORDER BY score DESC) AS row,
    RANK() OVER(PARTITION BY category ORDER BY score DESC) AS rank
FROM popular_products;
```

### 카테고리별 상위 N개 추출
```sql
SELECT * FROM (
    SELECT category, product_id, score,
        ROW_NUMBER() OVER(PARTITION BY category ORDER BY score DESC) AS rank
    FROM popular_products
) AS ranked
WHERE rank <= 2;
```

---

## 7. 행과 열 변환 (Pivot)

### 행을 열로 변환
```sql
SELECT dt,
    MAX(CASE WHEN indicator='impressions' THEN val END) AS impressions,
    MAX(CASE WHEN indicator='sessions' THEN val END) AS sessions,
    MAX(CASE WHEN indicator='users' THEN val END) AS users
FROM daily_kpi
GROUP BY dt;
```

### 행을 쉼표로 구분된 문자열로 집약
```sql
SELECT purchase_id,
    STRING_AGG(product_id, ',') AS product_ids,
    SUM(price) AS amount
FROM purchase_detail_log
GROUP BY purchase_id;
```

---

## 핵심 함수 요약

| 함수 | 용도 |
|------|------|
| `COALESCE()` | NULL 대체값 지정 |
| `NULLIF()` | 조건부 NULL 반환 |
| `SIGN()` | 부호 반환 (-1, 0, 1) |
| `ABS()`, `SQRT()`, `POWER()` | 수학 연산 |
| `INTERVAL` | 날짜/시간 연산 |
| `AGE()` | 날짜 차이 계산 |
| `SPLIT_PART()` | 문자열 분리 |
| `LPAD()` | 문자열 왼쪽 채우기 |
| `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()` | 순위 부여 |
| `LAG()`, `LEAD()` | 이전/다음 행 참조 |
| `FIRST_VALUE()`, `LAST_VALUE()` | 프레임 내 첫/마지막 값 |
| `STRING_AGG()` | 문자열 집약 |