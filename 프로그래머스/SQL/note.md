## JOIN

- (INNER) JOIN: 교집합, ON절 조건을 만족하는 데이터만 가져옴
- LEFT JOIN: 왼쪽테이블 중심으로 오른쪽테이블 매칭, 왼쪽은 무조건 표시하고 오른쪽에 매칭되는것 없으면 NULL, 왼쪽 테이블 한개 레코드에 여러 오른쪽 레코드 일치할경우 왼쪽 레코드 여러번 표시
- RIGTH JOIN: 오른쪽테이블 중심으로 왼쪽테이블 매칭, 오른쪽 무조건 표시하고 왼쪽에 매칭되는 것 없으면 NULL, 오른쪽 테이블 한개 레코드에 여러 왼쪽 레코드 일치할경우 오른쪽 레코드 여러번 표시

## NULL처리

- IFNULL(컬럼명, "NULL일때 대체값")
- CASE WHEN 조건식 THEN 실행할값 WHEN..THEN.. ELSE .. END : TRUE FALSE 판단해서 변환
- COALESCE(컬럼명, 컬럼명이 NULL인경우 대체값) : 처음으로 만나는 NULL이 아닌 값 출력

## IF / CASE~ WHEN

```mysql
IF(조건문, TRUE일때 값, FALSE일때 값)

CASE WHEN 조건문 THEN 값
	WHEN 조건문 THEN 값
	...
	ELSE 값
END
```

## GROUP BY

WHERE -> GROUP BY -> HAVING -> SELECT -> ORDER BY

1. 컬럼 그룹핑

SELECT 컬럼 FROM 테이블 GROUP BY 그룹화할 컬럼

2. 조건 처리 후 컬럼 그룹핑

SELECT 컬럼 FROM 테이블 WHERE 조건식 GROUP BY 그룹화할 컬럼

3. 그룹핑 된 컬럼 조건처리

SELECT 컬럼 FROM 테이블 GROUP BY 그룹화할 컬럼 HAVING 조건식

4. 조건 처리후 그룹핑 된 컬럼 조건 처리

SELECT 컬럼 FROM 테이블 WHERE 조건식 GROUP BY 그룹화할 컬럼 HAVING 조건식

## WITH RECURSIVE

``` mysql
WITH RECURSIVE 테이블명 AS(

 SELECT 초기값 AS 별칭

 UNION ALL

 SELECT 별칭 반복할 계산식 FROM 테이블명 WHERE 제어문

)

SELECT * FROM 테이블명
```

## DATE_FORMAT

- 날짜표기

|      |                                  |
| ---- | -------------------------------- |
| %Y   | 년도 - Year(4자리 표기)          |
| %y   | 년도 (뒤에 2자리 표기)           |
| %M   | 월 - 월 이름(January ~ December) |
| %m   | 월 - 월 숫자(00 ~ 12)            |
| %b   | 월- 월 이름(Jan, Feb, ...)       |
| %d   | 일(00 ~ 31)                      |
| %W   | 요일(Monday, Tuesday, ..)        |
| %a   | 요일(Mon, Tue, ..)               |

- 시간표기

|      |                      |
| ---- | -------------------- |
| %H   | 시간 24시간(00 ~ 23) |
| %h   | 시간 12시간(00 ~ 12) |
| %i   | 분 (00 ~ 59)         |
| %s   | 초 (00 ~ 59)         |

## 날짜(시간) 데이터 추출

|                                         |      |
| --------------------------------------- | ---- |
| YEAR(기준날짜)                          | 연도 |
| MONTH(기준날짜)                         | 월   |
| DAY(기준날짜) 또는 DAYOFMONTH(기준날짜) | 일   |
| HOUR(기준날짜)                          | 시간 |
| MINUTE(기준날짜)                        | 분   |
| SECOND(기준날짜)                        | 초   |

## 반올림, 올림, 버림

- ROUND(숫자, 자릿값): 반올림
- CEIL(): 올림
- FLOOR(): 버림

## LIMIT

- LIMIT N: N개 가져오기
- LIMIT N, M: N+1부터 M개(첫행 0부터 시작)

## ETC

- DISTINCT: 중복제거

- COUNT: 갯수
- MAX, MIN :최대값, 최소값
- SUM, AVG: 합, 평균

- UNION

  - UNION : 중복제거 + 합치기

  - UNION: 중복제거하지않고 합치기


- IN / NOT IN

  - IN("A","B","C") : A or B or C

  - NOT IN("A","B"): NOT A or B 


- BETWEEN A AND B: A<=값<=B
- != 또는 <> : 같지 않다
- IS NULL / IS NOT NULL: NULL 일때, NULL이 아닐때

- UPPER() :대문자 변경 / LOWER(): 소문자 변경



