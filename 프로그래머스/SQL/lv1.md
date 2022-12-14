## 어린 동물 찾기

> LV1. SELECT

### 문제 설명

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| INTAKE_CONDITION | VARCHAR(N) | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_INTAKE  | VARCHAR(N) | FALSE    |

동물 보호소에 들어온 동물 중 젊은 동물의 아이디와 이름을 조회하는 SQL 문을 작성해주세요. 이때 결과는 아이디 순으로 조회해주세요. 

### 예시

예를 들어 `ANIMAL_INS` 테이블이 다음과 같다면

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME     | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | -------- | --------------- |
| A365172   | Dog         | 2014-08-26 12:53:00 | Normal           | Diablo   | Neutered Male   |
| A367012   | Dog         | 2015-09-16 09:06:00 | Sick             | Miller   | Neutered Male   |
| A365302   | Dog         | 2017-01-08 16:34:00 | Aged             | Minnie   | Spayed Female   |
| A381217   | Dog         | 2017-07-08 09:41:00 | Sick             | Cherokee | Neutered Male   |

이 중 젊은 동물은 Diablo, Miller, Cherokee입니다. 따라서 SQL문을 실행하면 다음과 같이 나와야 합니다.

| ANIMAL_ID | NAME     |
| --------- | -------- |
| A365172   | Diablo   |
| A367012   | Miller   |
| A381217   | Cherokee |

---

### 내 답과 풀이

- 젊은 동물: `INTAKE_CONDITION`이 Aged가 아닌 경우를 뜻함
- 같지 않다: `!=` 또는 `<>` 

```mysql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION != 'Aged';
```

```mysql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION <> 'Aged';
```



## 아픈 동물 찾기

> LV1. SELECT

### 문제 설명

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| INTAKE_CONDITION | VARCHAR(N) | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_INTAKE  | VARCHAR(N) | FALSE    |

동물 보호소에 들어온 동물 중 아픈 동물[1](https://school.programmers.co.kr/learn/courses/30/lessons/59036#fn1)의 아이디와 이름을 조회하는 SQL 문을 작성해주세요. 이때 결과는 아이디 순으로 조회해주세요.

### 예시

예를 들어 `ANIMAL_INS` 테이블이 다음과 같다면

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME     | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | -------- | --------------- |
| A365172   | Dog         | 2014-08-26 12:53:00 | Normal           | Diablo   | Neutered Male   |
| A367012   | Dog         | 2015-09-16 09:06:00 | Sick             | Miller   | Neutered Male   |
| A365302   | Dog         | 2017-01-08 16:34:00 | Aged             | Minnie   | Spayed Female   |
| A381217   | Dog         | 2017-07-08 09:41:00 | Sick             | Cherokee | Neutered Male   |

이 중 아픈 동물은 Miller와 Cherokee입니다. 따라서 SQL문을 실행하면 다음과 같이 나와야 합니다.

| ANIMAL_ID | NAME     |
| --------- | -------- |
| A367012   | Miller   |
| A381217   | Cherokee |

---

### 내 답과 풀이

- 아픈동물: `INTAKE_CONDITION`이 Sick 인 경우를 뜻함
- ORDER BY: 오름차순ASC는 생략가능 (내림차순DESC)

```mysql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION = "Sick"
ORDER BY ANIMAL_ID;
```



## 이름이 있는 동물의 아이디

> LV1. IS NULL

### 문제 설명

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| INTAKE_CONDITION | VARCHAR(N) | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_INTAKE  | VARCHAR(N) | FALSE    |

동물 보호소에 들어온 동물 중, 이름이 있는 동물의 ID를 조회하는 SQL 문을 작성해주세요. 단, ID는 오름차순 정렬되어야 합니다.

### 예시

예를 들어 `ANIMAL_INS` 테이블이 다음과 같다면

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME       | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | ---------- | --------------- |
| A434523   | Cat         | 2015-11-20 14:18:00 | Normal           | NULL       | Spayed Female   |
| A562649   | Dog         | 2014-03-20 18:06:00 | Sick             | NULL       | Spayed Female   |
| A524634   | Dog         | 2015-01-02 18:54:00 | Normal           | *Belle     | Intact Female   |
| A465637   | Dog         | 2017-06-04 08:17:00 | Injured          | *Commander | Neutered Male   |

이름이 있는 동물의 ID는 A524634와 A465637입니다. 따라서 SQL을 실행하면 다음과 같이 출력되어야 합니다.

| ANIMAL_ID |
| --------- |
| A465637   |
| A524634   |

---

### 내 답과 풀이

- NULL이 아닐때 : IS NOT NULL
- ORDER BY: 오름차순ASC는 생략가능 (내림차순DESC)

```mysql
SELECT ANIMAL_ID
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
ORDER BY ANIMAL_ID;
```



## 동물의 아이디와 이름

> LV1. SELECT

### 문제 설명

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| INTAKE_CONDITION | VARCHAR(N) | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_INTAKE  | VARCHAR(N) | FALSE    |

동물 보호소에 들어온 모든 동물의 아이디와 이름을 ANIMAL_ID순으로 조회하는 SQL문을 작성해주세요. 

### 예시

SQL을 실행하면 다음과 같이 출력되어야 합니다.

| ANIMAL_ID | NAME         |
| --------- | ------------ |
| A349996   | Sugar        |
| A350276   | Jewel        |
| A350375   | Meo          |
| A352555   | Harley       |
| A352713   | Gia          |
| A352872   | Peanutbutter |
| A353259   | Bj           |

((이하 생략))

### 내 답과 풀이

```mysql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;
```



## 상위 N개 레코드

> LV1. SELECT

### 문제 설명

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| INTAKE_CONDITION | VARCHAR(N) | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_INTAKE  | VARCHAR(N) | FALSE    |

동물 보호소에 가장 먼저 들어온 동물의 이름을 조회하는 SQL 문을 작성해주세요.

### 예시

예를 들어 `ANIMAL_INS` 테이블이 다음과 같다면

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME     | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | -------- | --------------- |
| A399552   | Dog         | 2013-10-14 15:38:00 | Normal           | Jack     | Neutered Male   |
| A379998   | Dog         | 2013-10-23 11:42:00 | Normal           | Disciple | Intact Male     |
| A370852   | Dog         | 2013-11-03 15:04:00 | Normal           | Katie    | Spayed Female   |
| A403564   | Dog         | 2013-11-18 17:03:00 | Normal           | Anna     | Spayed Female   |

이 중 가장 보호소에 먼저 들어온 동물은 Jack입니다. 따라서 SQL문을 실행하면 다음과 같이 나와야 합니다.

| NAME |
| ---- |
| Jack |

※ 보호소에 가장 먼저 들어온 동물은 한 마리인 경우만 테스트 케이스로 주어집니다.

### 내 답과 풀이

- LIMIT N: N개 가져오기
- LIMIT N, M: N+1부터 M개(첫행 0부터 시작)

```mysql
SELECT NAME
FROM ANIMAL_INS
ORDER BY DATETIME
LIMIT 1
```

## 여러 기준으로 정렬하기

> LV1. SELECT

### 문제 설명

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| INTAKE_CONDITION | VARCHAR(N) | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_INTAKE  | VARCHAR(N) | FALSE    |

동물 보호소에 들어온 모든 동물의 아이디와 이름, 보호 시작일을 이름 순으로 조회하는 SQL문을 작성해주세요. 단, 이름이 같은 동물 중에서는 보호를 나중에 시작한 동물을 먼저 보여줘야 합니다.

### 예시

예를 들어, `ANIMAL_INS` 테이블이 다음과 같다면

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME  | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | ----- | --------------- |
| A349996   | Cat         | 2018-01-22 14:32:00 | Normal           | Sugar | Neutered Male   |
| A350276   | Cat         | 2017-08-13 13:50:00 | Normal           | Jewel | Spayed Female   |
| A396810   | Dog         | 2016-08-22 16:13:00 | Injured          | Raven | Spayed Female   |
| A410668   | Cat         | 2015-11-19 13:41:00 | Normal           | Raven | Spayed Female   |

1. 이름을 사전 순으로 정렬하면 다음과 같으며, 'Jewel', 'Raven', 'Sugar'
2. 'Raven'이라는 이름을 가진 개와 고양이가 있으므로, 이 중에서는 보호를 나중에 시작한 개를 먼저 조회합니다.

따라서 SQL문을 실행하면 다음과 같이 나와야 합니다.

| ANIMAL_ID | NAME  | DATETIME            |
| --------- | ----- | ------------------- |
| A350276   | Jewel | 2017-08-13 13:50:00 |
| A396810   | Raven | 2016-08-22 16:13:00 |
| A410668   | Raven | 2015-11-19 13:41:00 |
| A349996   | Sugar | 2018-01-22 14:32:00 |

### 내 답과 풀이

```mysql
SELECT ANIMAL_ID, NAME, DATETIME
FROM ANIMAL_INS
ORDER BY NAME, DATETIME DESC;
```



## 역순 정렬하기

> LV1. SELECT

### 문제 설명

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| INTAKE_CONDITION | VARCHAR(N) | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_INTAKE  | VARCHAR(N) | FALSE    |

동물 보호소에 들어온 모든 동물의 이름과 보호 시작일을 조회하는 SQL문을 작성해주세요. 이때 결과는 ANIMAL_ID 역순으로 보여주세요. 

### 예시

SQL을 실행하면 다음과 같이 출력되어야 합니다.

| NAME   | DATETIME            |
| ------ | ------------------- |
| Rocky  | 2016-06-07 09:17:00 |
| Shelly | 2015-01-29 15:01:00 |
| Benji  | 2016-04-19 13:28:00 |
| Jackie | 2016-01-03 16:25:00 |
| *Sam   | 2016-03-13 11:17:00 |

..이하 생략

### 내 답과 풀이

```mysql
SELECT NAME, DATETIME
FROM ANIMAL_INS
ORDER BY ANIMAL_ID DESC;
```

## 인기있는 아이스크림

> LV1. SELECT

### 문제 설명

`FIRST_HALF` 테이블은 아이스크림 가게의 상반기 주문 정보를 담은 테이블입니다.`FIRST_HALF` 테이블 구조는 다음과 같으며, `SHIPMENT_ID`, `FLAVOR`, `TOTAL_ORDER`는 각각 아이스크림 공장에서 아이스크림 가게까지의 출하 번호, 아이스크림 맛, 상반기 아이스크림 총주문량을 나타냅니다.

| NAME        | TYPE       | NULLABLE |
| :---------- | :--------- | -------- |
| SHIPMENT_ID | INT(N)     | FALSE    |
| FLAVOR      | VARCHAR(N) | FALSE    |
| TOTAL_ORDER | INT(N)     | FALSE    |

상반기에 판매된 아이스크림의 맛을 총주문량을 기준으로 내림차순 정렬하고 총주문량이 같다면 출하 번호를 기준으로 오름차순 정렬하여 조회하는 SQL 문을 작성해주세요.

### 예시

예를 들어 `FIRST_HALF` 테이블이 다음과 같을 때

| SHIPMENT_ID | FLAVOR          | TOTAL_ORDER |
| ----------- | --------------- | ----------- |
| 101         | chocolate       | 3200        |
| 102         | vanilla         | 2800        |
| 103         | mint_chocolate  | 1700        |
| 104         | caramel         | 2600        |
| 105         | white_chocolate | 3100        |
| 106         | peach           | 2450        |
| 107         | watermelon      | 2150        |
| 108         | mango           | 2900        |
| 109         | strawberry      | 3100        |
| 110         | melon           | 3150        |
| 111         | orange          | 2900        |
| 112         | pineapple       | 2900        |

상반기 아이스크림 맛을 총주문량을 기준으로 내림차순 정렬하고 총주문량이 같은 경우 출하 번호를 기준으로 오름차순 정렬하면 chocolate, melon, white_chocolate, strawberry, mango, orange, pineapple, vanilla, caramel, peach, watermelon, mint_chocolate 순서대로 조회되어야 합니다. 따라서 SQL문을 실행하면 다음과 같이 나와야 합니다.

| FLAVOR          |
| --------------- |
| chocolate       |
| melon           |
| white_chocolate |
| strawberry      |
| mango           |
| orange          |
| pineapple       |
| vanilla         |
| caramel         |
| peach           |
| watermelon      |
| mint_chocolate  |

---

### 내 답과 풀이

```mysql
SELECT FLAVOR
FROM FIRST_HALF
ORDER BY TOTAL_ORDER DESC, SHIPMENT_ID;
```

## 이름이 없는 동물의 아이디

> LV1. IS NULL

### 문제 설명

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| INTAKE_CONDITION | VARCHAR(N) | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_INTAKE  | VARCHAR(N) | FALSE    |

동물 보호소에 들어온 동물 중, 이름이 없는 채로 들어온 동물의 ID를 조회하는 SQL 문을 작성해주세요. 단, ID는 오름차순 정렬되어야 합니다.

### 예시

예를 들어 `ANIMAL_INS` 테이블이 다음과 같다면

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME       | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | ---------- | --------------- |
| A368930   | Dog         | 2014-06-08 13:20:00 | Normal           | NULL       | Spayed Female   |
| A524634   | Dog         | 2015-01-02 18:54:00 | Normal           | *Belle     | Intact Female   |
| A465637   | Dog         | 2017-06-04 08:17:00 | Injured          | *Commander | Neutered Male   |

이름이 없는 채로 들어온 동물의 ID는 A368930입니다. 따라서 SQL을 실행하면 다음과 같이 출력되어야 합니다.

| ANIMAL_ID |
| --------- |
| A368930   |

### 내 답과 풀이

```mysql
SELECT ANIMAL_ID
FROM ANIMAL_INS
WHERE NAME IS NULL
ORDER BY ANIMAL_ID
```

## 가장 비싼 상품 구하기

> LV1. SUM, MAX, MIN

### 문제 설명

다음은 어느 의류 쇼핑몰에서 판매 중인 상품들의 정보를 담은 `PRODUCT` 테이블입니다. `PRODUCT` 테이블은 아래와 같은 구조로 되어있으며, `PRODUCT_ID`, `PRODUCT_CODE`, `PRICE`는 각각 상품 ID, 상품코드, 판매가를 나타냅니다.

| Column name  | Type       | Nullable |
| ------------ | ---------- | -------- |
| PRODUCT_ID   | INTEGER    | FALSE    |
| PRODUCT_CODE | VARCHAR(8) | FALSE    |
| PRICE        | INTEGER    | FALSE    |

상품 별로 중복되지 않는 8자리 상품코드 값을 가지며, 앞 2자리는 카테고리 코드를 의미합니다.

`PRODUCT` 테이블에서 판매 중인 상품 중 가장 높은 판매가를 출력하는 SQL문을 작성해주세요. 이때 컬럼명은 MAX_PRICE로 지정해주세요

### 예시

예를 들어 `PRODUCT` 테이블이 다음과 같다면

| PRODUCT_ID | PRODUCT_CODE | PRICE |
| ---------- | ------------ | ----- |
| 1          | A1000001     | 10000 |
| 2          | A2000005     | 9000  |
| 3          | C1000006     | 22000 |

가장 높은 판매가는 22,000 원 이므로, 다음과 같은 결과가 나와야 합니다.

| MAX_PRICE |
| --------- |
| 22000     |

### 내 답과 풀이

```mysql
SELECT MAX(PRICE) AS MAX_PRICE
FROM PRODUCT;
```

## 강원도에 위치한 생산공장 목록 출력하기

> LV1. SELECT

### 문제 설명

다음은 식품공장의 정보를 담은 `FOOD_FACTORY` 테이블입니다. `FOOD_FACTORY` 테이블은 다음과 같으며 `FACTORY_ID`, `FACTORY_NAME`, `ADDRESS`, `TLNO`는 각각 공장 ID, 공장 이름, 주소, 전화번호를 의미합니다.

| Column name  | Type         | Nullable |
| ------------ | ------------ | -------- |
| FACTORY_ID   | VARCHAR(10)  | FALSE    |
| FACTORY_NAME | VARCHAR(50)  | FALSE    |
| ADDRESS      | VARCHAR(100) | FALSE    |
| TLNO         | VARCHAR(20)  | TRUE     |

`FOOD_FACTORY` 테이블에서 강원도에 위치한 식품공장의 공장 ID, 공장 이름, 주소를 조회하는 SQL문을 작성해주세요. 이때 결과는 공장 ID를 기준으로 오름차순 정렬해주세요.

### 예시

`FOOD_FACTORY` 테이블이 다음과 같을 때

| FACTORY_ID | FACTORY_NAME     | ADDRESS                                 | TLNO         |
| ---------- | ---------------- | --------------------------------------- | ------------ |
| FT19980003 | (주)맛있는라면   | 강원도 정선군 남면 칠현로 679           | 033-431-3122 |
| FT19980004 | (주)맛있는기름   | 경기도 평택시 포승읍 포승공단순환로 245 | 031-651-2410 |
| FT20010001 | (주)맛있는소스   | 경상북도 구미시 1공단로7길 58-11        | 054-231-2121 |
| FT20010002 | (주)맛있는통조림 | 전라남도 영암군 미암면 곤미현로 1336    | 061-341-5210 |
| FT20100001 | (주)맛있는차     | 전라남도 장성군 서삼면 장산리 233-1번지 | 061-661-1420 |
| FT20100002 | (주)맛있는김치   | 충청남도 아산시 탕정면 탕정면로 485     | 041-241-5421 |
| FT20100003 | (주)맛있는음료   | 강원도 원주시 문막읍 문막공단길 154     | 033-232-7630 |
| FT20100004 | (주)맛있는국     | 강원도 평창군 봉평면 진조길 227-35      | 033-323-6640 |
| FT20110001 | (주)맛있는밥     | 경기도 화성시 팔탄면 가재리 34번지      | 031-661-1532 |
| FT20110002 | (주)맛있는과자   | 광주광역시 북구 하서로 222              | 062-211-7759 |

SQL을 실행하면 다음과 같이 출력되어야 합니다.

| FACTORY_ID | FACTORY_NAME   | ADDRESS                             |
| ---------- | -------------- | ----------------------------------- |
| FT19980003 | (주)맛있는라면 | 강원도 정선군 남면 칠현로 679       |
| FT20100003 | (주)맛있는음료 | 강원도 원주시 문막읍 문막공단길 154 |
| FT20100004 | (주)맛있는국   | 강원도 평창군 봉평면 진조길 227-35  |

### 내 답과 풀이

```mysql
SELECT FACTORY_ID, FACTORY_NAME, ADDRESS
FROM FOOD_FACTORY
WHERE ADDRESS LIKE "강원도%"
ORDER BY FACTORY_ID
```

## 경기도에 위치한 식품창고 목록 출력하기

> LV1. IS NULL

### 문제 설명

다음은 식품창고의 정보를 담은 `FOOD_WAREHOUSE` 테이블입니다. `FOOD_WAREHOUSE` 테이블은 다음과 같으며 `WAREHOUSE_ID`, `WAREHOUSE_NAME`, `ADDRESS`, `TLNO`, `FREEZER_YN`는 창고 ID, 창고 이름, 창고 주소, 전화번호, 냉동시설 여부를 의미합니다.

| Column name    | Type         | Nullable |
| -------------- | ------------ | -------- |
| WAREHOUSE_ID   | VARCHAR(10)  | FALSE    |
| WAREHOUSE_NAME | VARCHAR(20)  | FALSE    |
| ADDRESS        | VARCHAR(100) | TRUE     |
| TLNO           | VARCHAR(20)  | TRUE     |
| FREEZER_YN     | VARCHAR(1)   | TRUE     |

`FOOD_WAREHOUSE` 테이블에서 경기도에 위치한 창고의 ID, 이름, 주소, 냉동시설 여부를 조회하는 SQL문을 작성해주세요. 이때 냉동시설 여부가 NULL인 경우, 'N'으로 출력시켜 주시고 결과는 창고 ID를 기준으로 오름차순 정렬해주세요.

### 예시

`FOOD_WAREHOUSE` 테이블이 다음과 같을 때

| WAREHOUSE_ID | WAREHOUSE_NAME | ADDRESS                                   | TLNO         | FREEZER_YN |
| ------------ | -------------- | ----------------------------------------- | ------------ | ---------- |
| WH0001       | 창고_경기1     | 경기도 안산시 상록구 용담로 141           | 031-152-1332 | Y          |
| WH0002       | 창고_충북1     | 충청북도 진천군 진천읍 씨제이로 110       | 043-623-9900 | Y          |
| WH0003       | 창고_경기2     | 경기도 이천시 마장면 덕평로 811           | 031-221-7241 | NULL       |
| WH0004       | 창고_경기3     | 경기도 김포시 대곶면 율생중앙로205번길    | 031-671-1900 | N          |
| WH0005       | 창고_충남1     | 충청남도 천안시 동남구 광덕면 신덕리1길 9 | 041-876-5421 | Y          |

SQL을 실행하면 다음과 같이 출력되어야 합니다.

| WAREHOUSE_ID | WAREHOUSE_NAME | ADDRESS                                | FREEZER_YN |
| ------------ | -------------- | -------------------------------------- | ---------- |
| WH0001       | 창고_경기1     | 경기도 안산시 상록구 용담로 141        | Y          |
| WH0003       | 창고_경기2     | 경기도 이천시 마장면 덕평로 811        | N          |
| WH0004       | 창고_경기3     | 경기도 김포시 대곶면 율생중앙로205번길 | N          |

### 내 답과 풀이

```mysql
SELECT WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, IFNULL(FREEZER_YN,"N") AS FREEZER_YN
FROM FOOD_WAREHOUSE
WHERE ADDRESS LIKE "경기도%"
ORDER BY WAREHOUSE_ID
```

## 조건에 맞는 회원수 구하기

> LV1. SELECT

### 문제 설명

다음은 어느 의류 쇼핑몰에 가입한 회원 정보를 담은 `USER_INFO` 테이블입니다. `USER_INFO` 테이블은 아래와 같은 구조로 되어있으며 `USER_ID`, `GENDER`, `AGE`, `JOINED`는 각각 회원 ID, 성별, 나이, 가입일을 나타냅니다.

| Column name | Type       | Nullable |
| ----------- | ---------- | -------- |
| USER_ID     | INTEGER    | FALSE    |
| GENDER      | TINYINT(1) | TRUE     |
| AGE         | INTEGER    | TRUE     |
| JOINED      | DATE       | FALSE    |

`GENDER` 컬럼은 비어있거나 0 또는 1의 값을 가지며 0인 경우 남자를, 1인 경우는 여자를 나타냅니다.

`USER_INFO` 테이블에서 2021년에 가입한 회원 중 나이가 20세 이상 29세 이하인 회원이 몇 명인지 출력하는 SQL문을 작성해주세요.

### 예시

예를 들어 `USER_INFO` 테이블이 다음과 같다면

| USER_ID | GENDER | AGE  | JOINED     |
| ------- | ------ | ---- | ---------- |
| 1       | 1      | 26   | 2021-10-05 |
| 2       | 0      | NULL | 2021-11-25 |
| 3       | 1      | 22   | 2021-11-30 |
| 4       | 0      | 31   | 2021-12-03 |
| 5       | 0      | 28   | 2021-12-16 |
| 6       | 1      | 24   | 2022-01-03 |
| 7       | 1      | NULL | 2022-01-09 |

2021년에 가입한 회원 중 나이가 20세 이상 29세 이하인 회원은 `USER_ID` 가 1, 3, 5 인 회원들 이므로, 다음과 같이 결과가 나와야 합니다.

| USERS |
| ----- |
| 3     |

### 내 답과 풀이

```mysql
SELECT COUNT(*) AS USERS
FROM USER_INFO
WHERE AGE BETWEEN 20 AND 29
    AND YEAR(JOINED) = 2021
```

## 흉부외과 또는 일반외과 의사 목록 출력하기

> LV1. SELECT

### 문제 설명

다음은 종합병원에 속한 의사 정보를 담은`DOCTOR` 테이블입니다. `DOCTOR` 테이블은 다음과 같으며 `DR_NAME`, `DR_ID`, `LCNS_NO`, `HIRE_YMD`, `MCDP_CD`, `TLNO`는 각각 의사이름, 의사ID, 면허번호, 고용일자, 진료과코드, 전화번호를 나타냅니다.

| Column name | Type        | Nullable |
| ----------- | ----------- | -------- |
| DR_NAME     | VARCHAR(20) | FALSE    |
| DR_ID       | VARCHAR(10) | FALSE    |
| LCNS_NO     | VARCHAR(30) | FALSE    |
| HIRE_YMD    | DATE        | FALSE    |
| MCDP_CD     | VARCHAR(6)  | TRUE     |
| TLNO        | VARCHAR(50) | TRUE     |

`DOCTOR` 테이블에서 진료과가 흉부외과(CS)이거나 일반외과(GS)인 의사의 이름, 의사ID, 진료과, 고용일자를 조회하는 SQL문을 작성해주세요. 이때 결과는 고용일자를 기준으로 내림차순 정렬하고, 고용일자가 같다면 이름을 기준으로 오름차순 정렬해주세요.

### 예시

`DOCTOR` 테이블이 다음과 같을 때

| DR_NAME | DR_ID      | LCNS_NO    | HIRE_YMD   | MCDP_CD | TLNO        |
| ------- | ---------- | ---------- | ---------- | ------- | ----------- |
| 루피    | DR20090029 | LC00010001 | 2009-03-01 | CS      | 01085482011 |
| 패티    | DR20090001 | LC00010901 | 2009-07-01 | CS      | 01085220122 |
| 뽀로로  | DR20170123 | LC00091201 | 2017-03-01 | GS      | 01034969210 |
| 티거    | DR20100011 | LC00011201 | 2010-03-01 | NP      | 01034229818 |
| 품바    | DR20090231 | LC00011302 | 2015-11-01 | OS      | 01049840278 |
| 티몬    | DR20090112 | LC00011162 | 2010-03-01 | FM      | 01094622190 |
| 니모    | DR20200012 | LC00911162 | 2020-03-01 | CS      | 01089483921 |
| 오로라  | DR20100031 | LC00010327 | 2010-11-01 | OS      | 01098428957 |
| 자스민  | DR20100032 | LC00010192 | 2010-03-01 | GS      | 01023981922 |
| 벨      | DR20100039 | LC00010562 | 2010-07-01 | GS      | 01058390758 |

SQL을 실행하면 다음과 같이 출력되어야 합니다.

| DR_NAME | DR_ID      | MCDP_CD | HIRE_YMD   |
| ------- | ---------- | ------- | ---------- |
| 니모    | DR20200012 | CS      | 2020-03-01 |
| 뽀로로  | DR20170123 | GS      | 2017-03-01 |
| 벨      | DR20100039 | GS      | 2010-07-01 |
| 자스민  | DR20100032 | GS      | 2010-03-01 |
| 패티    | DR20090001 | CS      | 2009-07-01 |
| 루피    | DR20090029 | CS      | 2009-03-01 |

### 내 답과 풀이

```mysql
SELECT DR_NAME, DR_ID, MCDP_CD, DATE_FORMAT(HIRE_YMD,"%Y-%m-%d") AS HIRE_YMD
FROM DOCTOR
WHERE MCDP_CD IN("CS","GS")
ORDER BY HIRE_YMD DESC, DR_NAME
```

## 12세 이하인 여자 환자 목록 출력하기

> LV1. SELECT

### 문제 설명

다음은 종합병원에 등록된 환자정보를 담은 `PATIENT` 테이블입니다. `PATIENT` 테이블은 다음과 같으며 `PT_NO`, `PT_NAME`, `GEND_CD`, `AGE`, `TLNO`는 각각 환자번호, 환자이름, 성별코드, 나이, 전화번호를 의미합니다.

| Column name | Type        | Nullable |
| ----------- | ----------- | -------- |
| PT_NO       | VARCHAR(10) | FALSE    |
| PT_NAME     | VARCHAR(20) | FALSE    |
| GEND_CD     | VARCHAR(1)  | FALSE    |
| AGE         | INTEGER     | FALSE    |
| TLNO        | VARCHAR(50) | TRUE     |

`PATIENT` 테이블에서 12세 이하인 여자환자의 환자이름, 환자번호, 성별코드, 나이, 전화번호를 조회하는 SQL문을 작성해주세요. 이때 전화번호가 없는 경우, 'NONE'으로 출력시켜 주시고 결과는 나이를 기준으로 내림차순 정렬하고, 나이 같다면 환자이름을 기준으로 오름차순 정렬해주세요.

### 예시

`PATIENT` 테이블이 다음과 같을 때

| PT_NO      | PT_NAME | GEND_CD | AGE  | TLNO        |
| ---------- | ------- | ------- | ---- | ----------- |
| PT22000003 | 브라운  | M       | 18   | 01031246641 |
| PT22000004 | 크롱    | M       | 7    | NULL        |
| PT22000006 | 뽀뽀    | W       | 8    | NULL        |
| PT22000009 | 한나    | W       | 12   | 01032323117 |
| PT22000012 | 뿡뿡이  | M       | 5    | NULL        |
| PT22000013 | 크리스  | M       | 30   | 01059341192 |
| PT22000014 | 토프    | W       | 22   | 01039458213 |
| PT22000018 | 안나    | W       | 11   | NULL        |
| PT22000019 | 바라    | W       | 10   | 01079068799 |
| PT22000021 | 릴로    | W       | 33   | 01023290767 |

SQL을 실행하면 다음과 같이 출력되어야 합니다.

| PT_NAME | PT_NO      | GEND_CD | AGE  | TLNO        |
| ------- | ---------- | ------- | ---- | ----------- |
| 한나    | PT22000009 | W       | 12   | 01032323117 |
| 안나    | PT22000018 | W       | 11   | NONE        |
| 바라    | PT22000019 | W       | 10   | 01079068799 |
| 뽀뽀    | PT22000006 | W       | 8    | NONE        |

### 내 답과 풀이

```mysql
SELECT PT_NAME,	PT_NO, GEND_CD,	AGE, IFNULL(TLNO,"NONE") AS TLNO
FROM PATIENT
WHERE AGE <= 12 AND GEND_CD = "W"
ORDER BY AGE DESC, PT_NAME
```

## 모든 레코드 조회하기

> LV1. SELECT

### 문제 설명

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| INTAKE_CONDITION | VARCHAR(N) | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_INTAKE  | VARCHAR(N) | FALSE    |

동물 보호소에 들어온 모든 동물의 정보를 ANIMAL_ID순으로 조회하는 SQL문을 작성해주세요. 

### 예시

 SQL을 실행하면 다음과 같이 출력되어야 합니다.

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME   | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | ------ | --------------- |
| A349996   | Cat         | 2018-01-22 14:32:00 | Normal           | Sugar  | Neutered Male   |
| A350276   | Cat         | 2017-08-13 13:50:00 | Normal           | Jewel  | Spayed Female   |
| A350375   | Cat         | 2017-03-06 15:01:00 | Normal           | Meo    | Neutered Male   |
| A352555   | Dog         | 2014-08-08 04:20:00 | Normal           | Harley | Spayed Female   |

..이하 생략

### 내 답과 풀이

```mysql
SELECT *
FROM ANIMAL_INS
ORDER BY ANIMAL_ID
```

## 최댓값 구하기

> LV1. SUM,MAX,MIN

### 문제 설명

`ANIMAL_INS` 테이블은 동물 보호소에 들어온 동물의 정보를 담은 테이블입니다. `ANIMAL_INS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `INTAKE_CONDITION`, `NAME`, `SEX_UPON_INTAKE`는 각각 동물의 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부를 나타냅니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| INTAKE_CONDITION | VARCHAR(N) | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_INTAKE  | VARCHAR(N) | FALSE    |

가장 최근에 들어온 동물은 언제 들어왔는지 조회하는 SQL 문을 작성해주세요.

### 예시

예를 들어 `ANIMAL_INS` 테이블이 다음과 같다면

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME     | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | -------- | --------------- |
| A399552   | Dog         | 2013-10-14 15:38:00 | Normal           | Jack     | Neutered Male   |
| A379998   | Dog         | 2013-10-23 11:42:00 | Normal           | Disciple | Intact Male     |
| A370852   | Dog         | 2013-11-03 15:04:00 | Normal           | Katie    | Spayed Female   |
| A403564   | Dog         | 2013-11-18 17:03:00 | Normal           | Anna     | Spayed Female   |

가장 늦게 들어온 동물은 Anna이고, Anna는 2013-11-18 17:03:00에 들어왔습니다. 따라서 SQL문을 실행하면 다음과 같이 나와야 합니다.

| 시간                |
| ------------------- |
| 2013-11-18 17:03:00 |

※ 컬럼 이름(위 예제에서는 "시간")은 일치하지 않아도 됩니다.

### 내 답과 풀이

```mysql
SELECT DATETIME AS '시간'
FROM ANIMAL_INS
ORDER BY DATETIME DESC
LIMIT 1
```

## 과일로 만든 아이스크림 고르기

> LV1. SUM,MAX,MIN

### 문제 설명

다음은 아이스크림 가게의 상반기 주문 정보를 담은 `FIRST_HALF` 테이블과 아이스크림 성분에 대한 정보를 담은 `ICECREAM_INFO` 테이블입니다. `FIRST_HALF` 테이블 구조는 다음과 같으며, `SHIPMENT_ID`, `FLAVOR`, `TOTAL_ORDER` 는 각각 아이스크림 공장에서 아이스크림 가게까지의 출하 번호, 아이스크림 맛, 상반기 아이스크림 총주문량을 나타냅니다. `FIRST_HALF` 테이블의 기본 키는 `FLAVOR`입니다.

| NAME        | TYPE       | NULLABLE |
| :---------- | :--------- | -------- |
| SHIPMENT_ID | INT(N)     | FALSE    |
| FLAVOR      | VARCHAR(N) | FALSE    |
| TOTAL_ORDER | INT(N)     | FALSE    |

`ICECREAM_INFO` 테이블 구조는 다음과 같으며, `FLAVOR`, `INGREDITENT_TYPE` 은 각각 아이스크림 맛, 아이스크림의 성분 타입을 나타냅니다. `INGREDIENT_TYPE`에는 아이스크림의 주 성분이 설탕이면 `sugar_based`라고 입력되고, 아이스크림의 주 성분이 과일이면 `fruit_based`라고 입력됩니다. `ICECREAM_INFO`의 기본 키는 `FLAVOR`입니다. `ICECREAM_INFO`테이블의 `FLAVOR`는 `FIRST_HALF` 테이블의 `FLAVOR`의 외래 키입니다.

| NAME            | TYPE       | NULLABLE |
| :-------------- | :--------- | -------- |
| FLAVOR          | VARCHAR(N) | FALSE    |
| INGREDIENT_TYPE | VARCHAR(N) | FALSE    |

상반기 아이스크림 총주문량이 3,000보다 높으면서 아이스크림의 주 성분이 과일인 아이스크림의 맛을 총주문량이 큰 순서대로 조회하는 SQL 문을 작성해주세요.

### 예시

예를 들어 `FIRST_HALF` 테이블이 다음과 같고

| SHIPMENT_ID | FLAVOR          | TOTAL_ORDER |
| :---------- | :-------------- | ----------- |
| 101         | chocolate       | 3200        |
| 102         | vanilla         | 2800        |
| 103         | mint_chocolate  | 1700        |
| 104         | caramel         | 2600        |
| 105         | white_chocolate | 3100        |
| 106         | peach           | 2450        |
| 107         | watermelon      | 2150        |
| 108         | mango           | 2900        |
| 109         | strawberry      | 3100        |
| 110         | melon           | 3150        |
| 111         | orange          | 2900        |
| 112         | pineapple       | 2900        |

`ICECREAM_INFO` 테이블이 다음과 같다면

| FLAVOR          | INGREDIENT_TYPE |
| :-------------- | --------------- |
| chocolate       | sugar_based     |
| vanilla         | sugar_based     |
| mint_chocolate  | sugar_based     |
| caramel         | sugar_based     |
| white_chocolate | sugar_based     |
| peach           | fruit_based     |
| watermelon      | fruit_based     |
| mango           | fruit_based     |
| strawberry      | fruit_based     |
| melon           | fruit_based     |
| orange          | fruit_based     |
| pineapple       | fruit_based     |

상반기 아이스크림 총주문량이 3,000보다 높은 아이스크림 맛은 chocolate, strawberry, melon, white_chocolate입니다. 이 중에 아이스크림의 주 성분이 과일인 아이스크림 맛은 strawberry와 melon이고 총주문량이 큰 순서대로 아이스크림 맛을 조회하면 melon, strawberry 순으로 조회되어야 합니다. 따라서 SQL 문을 실행하면 다음과 같이 나와야 합니다.

| FLAVOR     |
| :--------- |
| melon      |
| strawberry |

### 내 답과 풀이

```mysql
SELECT FLAVOR
FROM FIRST_HALF
WHERE TOTAL_ORDER > 3000 AND FLAVOR IN (SELECT FLAVOR FROM ICECREAM_INFO WHERE INGREDIENT_TYPE LIKE "fruit%")
ORDER BY TOTAL_ORDER DESC
```

```mysql
SELECT F.FLAVOR
FROM FIRST_HALF F, ICECREAM_INFO I
WHERE F.FLAVOR = I.FLAVOR AND F.TOTAL_ORDER > 3000 AND I.INGREDIENT_TYPE LIKE "fruit%"
ORDER BY TOTAL_ORDER DESC
```

