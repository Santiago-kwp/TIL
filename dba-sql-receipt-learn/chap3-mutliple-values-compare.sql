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
create table advertising_stats(
    dt date,
    ad_id int,
    impression bigint,
    clicks int
);

insert into advertising_stats(dt, ad_id, impression, clicks) values(
'2017-04-01',001,100000, 3000),
('2017-04-01',002,120000, 1200);







