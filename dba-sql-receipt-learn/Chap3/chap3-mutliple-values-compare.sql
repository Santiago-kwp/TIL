create schema sql_receipt collate utf8mb4_general_ci;

use sql_receipt;


# 데이터 6-2
create table quarterly_sales(
    year varchar(4),
    q1 int,
    q2 int,
    q3 int,
    q4 int
);

insert into quarterly_sales(year, q1, q2, q3, q4) values(2015,82000,83000,78000, 84000);
insert into quarterly_sales(year, q1, q2, q3, q4) values(2016,85000,85000,80000, 81000);
insert into quarterly_sales(year, q1, q2) values(2017,92000,81000);

select * from quarterly_sales;

# 분기별 매출 증감 판정하기
select year, q1, q2,
       -- Q1 과 Q2의 매출 변화 평가하기
       case
           when q1 < q2 then '+'
           when q1 = q2 then ' '
           else '-'
       end as judge_q1_q2
       -- Q1 과 Q2의 매출액의 차이 계산하기
       , q2-q1 as diff_q2_q1
       -- Q1 과 Q2의 매출 변화를 1, 0, -1로 표현하기
       , sign(q2-q1) as sign_q2_q1
       from
           quarterly_sales
        order by year;

# 연간 평균 4분기 매출 계산하기
# NULL이 아닌 컬럼만을 사용해서 평균값을 구하는 쿼리
select year
    , (coalesce(q1,0) + coalesce(q2,0) + coalesce(q3,0) + coalesce(q4,0))
    / (sign(coalesce(q1,0)) + sign(coalesce(q2,0)) + sign(coalesce(q3,0)) + sign(coalesce(q4,0))) as average
from quarterly_sales
order by year;


# 데이터 6-3 광고 통계 정보(advertising_stats) 테이블
drop table if exists advertising_stats;
create table advertising_stats(
    dt date,
    ad_id int,
    impressions bigint,
    clicks int
);

insert ignore into advertising_stats(dt, ad_id, impressions, clicks) values
('2017-04-01',001, 100000, 3000),
('2017-04-01',002, 120000, 1200),
('2017-04-01',003, 500000, 10000),
('2017-04-02',001, 0, 0),
('2017-04-02',002, 130000, 1400),
('2017-04-02',003, 620000, 15000);

select * from advertising_stats;

# 정수형 자료형의 데이터 나누기
# CTR = 클릭 / 노출 수, CAST 함수를 사용해 clicks를 double precision 자료형으로 변환하고 계산

SELECT 
dt, ad_id
-- Hive, Redshift, BigQuery, SparkSQL의 경우
-- 정수를 나눌 때는 자동적으로 실수로 변
, clicks / impressions as ctr
-- PostgreSQL의 경우 정수를 나누면 소수점이 잘리므로 명시적으로 자료형 변환
-- , CAST(clicks as double precision) / impression as ctr
-- 실수를 상수로 앞에 두고 계산하면 암묵적으로 자료형 변환이 일어남
, 100.0 * clicks / impressions as ctr_as_percent
from advertising_stats
WHERE
dt = '2017-04-01'
order by
dt, ad_id;

# 0으로 나누는 것 피하기
# 1. case 식을 사용해 impressions 가 0인지 확인, 0이면 null 출력
# 코드 6-8
select
dt, ad_id
-- case 식으로 분모가 0일 경우를 분기해서, 0으로 나누지 않게 만드는 방법
, case
	when impressions > 0 then 100.0 * clicks / impressions
	end as ctr_as_percent_by_case
	-- 분모가 0이라면 null로 변환해서, 0으로 나누지 않게 만드는 방법
	-- PostgreSQL, Redshift, BigQuery, SparkSQL의 경우 NULLIF 함수 사용하기
	, 100.0 * clicks / nullif(impressions, 0) as ctr_as_percent_by_null
	-- Hive의 경우 NULLIF 대신 CASE 식 사용하기
	-- , 100.0 * clicks
	-- / CASE when impressions = 0 then null else impressions end
	-- as ctr_as_percent_by_null
from
advertising_stats
order by
dt, ad_id;
end

# 두 값의 거리 계산하기
# 데이터 6-4 일차원 위치 정보(location_1d) 테이블
create table location_1d(
x1 int,
x2 int);

insert ignore into location_1d(x1, x2) VALUES 
(5, 10),
(10, 5),
(-2, 4),
(3, 3),
(0, 1);

# 코드 6-9 일차원 데이터의 절댓값과 제곱 평균 제곱근을 계산하는 쿼리
select
abs(x1-x2) as abs
, sqrt(power(x1-x2, 2)) as rms
from location_1d;

# xy 평면 위에 있는 두 점의 유클리드 거리 계산하기
# 데이터 6-5 이차원 위치 정보(location_2d) 테이블
create table location_2d(
x1 int,
x2 int,
y1 int,
y2 int);

insert ignore into location_2d(x1,y1,x2,y2) values
(0,0,2,2),
(3,5,1,2),
(5,3,2,1);

# 코드 6-10 이차원 테이블에 대해 제곱 평균 제곱근(유클리드 거리)을 구하는 쿼리
select
sqrt(power(x1-x2,2) + power(y1-y2,2)) as dist
-- PostgreSQL의 경우는 point 자료형과 거리 연산자 <-> 사용하기
-- ,point(x1, y1) <-> point(x2,y2) as dist
from location_2d;

# 날짜/시간 계산하기
# 데이터 6-6 등록 시간과 생일을 포함하는 사용자 마스터(mst_users_with_dates) 테이블
drop table if exists mst_users_with_dates;
create table mst_users_with_dates(
user_id varchar(10),
register_stamp datetime,
birth_date date);

insert ignore into mst_users_with_dates(user_id, register_stamp, birth_date) values
('U001','2016-02-28 10:00:00','2000-02-29'),
('U002','2016-02-29 10:00:00','2000-02-29'),
('U003','2016-03-01 10:00:00','2000-02-29');

# 코드 6-11 미래 또는 과거의 날짜/시간을 계산하는 쿼리
select user_id
, register_stamp as register_stamp
, register_stamp + interval 1 hour as after_1_hour
, register_stamp - interval 30 minute as before_30_minutes
, date(register_stamp) as register_date
, date(register_stamp + interval 1 day) as after_1_day
, date(register_stamp - interval 1 month) as before_1_month
-- PostgreSQL의 경우 interval 자료형의 데이터에 사칙 연산 적용하기
-- , register_stamp::timestamp as register_stamp
-- , register_stamp::timestamp + '1 hour'::interval as after_1_hour
-- , register_stamp::timestamp - '30 minutes'::interval as before_30_minutes
-- , register_stamp::date as register_date
-- , (register_stamp::date + '1 day'::interval)::date as after_1_day 
-- , (register_stamp::date - '1 month'::interval)::date as before_1_month
-- BigQuery의 경우 timestamp_add/sub, date_add,sub 등의 함수 사용하기
-- , timestamp(register_stamp) as register_stamp
-- , timestamp_add(timestamp(register_stamp), interval 1 hour) as after_1_hour
-- , timestamp_sub(timestamp(register_stamp), interval 30 minute) as before_30_minutes
from mst_users_with_dates;

# 날짜 데이터들의 차이 계산하기
# 코드 6-12 두 날짜의 차이를 계산하는 쿼리
select user_id
, CURRENT_DATE() as today
, date(timestamp(register_stamp)) as register_date
, DATEDIFF(current_date(), date(timestamp(register_stamp))) as diff_days
-- PostgreSQL, Redshift의 경우 날짜 자료형끼리 뺄 수 있음
-- , current_date as today
-- , register_stamp::date as register_date
-- , current_date - register_stamp::date as diff_days
-- BigQuery의 경우 date_diff 함수 사용하기
-- , current_date as today
-- , date(timestamp(register_stamp)) as register_date
-- , date_diff(current_date, date(timestamp(register_stamp)), day) as diff_days
from mst_users_with_dates;

# 사용자의 생년월일로 나이 계산하기
# 나이를 계산하기 위한 전용 함수가 구현되어 있는 것은 PostgreSQL 뿐
select user_id
-- PostgreSQL의 경우 age 함수와 EXTRACT 함수를 사용해 나이 집계하기
, current_date as today
, register_stamp::date as register_date
, birth_date::date as birth_date
, extract(year from age(birth_date::date)) as current_age
, extract(year from age(register_stamp::date, birth_date::date)) as register_age
from mst_users_with_dates;