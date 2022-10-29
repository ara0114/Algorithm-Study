## 오랜 기간 보호한 동물(1)

> LV3. JOIN

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

`ANIMAL_OUTS` 테이블은 동물 보호소에서 입양 보낸 동물의 정보를 담은 테이블입니다. `ANIMAL_OUTS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `NAME`, `SEX_UPON_OUTCOME`는 각각 동물의 아이디, 생물 종, 입양일, 이름, 성별 및 중성화 여부를 나타냅니다. `ANIMAL_OUTS` 테이블의 `ANIMAL_ID`는 `ANIMAL_INS`의 `ANIMAL_ID`의 외래 키입니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_OUTCOME | VARCHAR(N) | FALSE    |

아직 입양을 못 간 동물 중, 가장 오래 보호소에 있었던 동물 3마리의 이름과 보호 시작일을 조회하는 SQL문을 작성해주세요. 이때 결과는 보호 시작일 순으로 조회해야 합니다.

### 예시

예를 들어, `ANIMAL_INS` 테이블과 `ANIMAL_OUTS` 테이블이 다음과 같다면

```
ANIMAL_INS
```

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME   | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | ------ | --------------- |
| A354597   | Cat         | 2014-05-02 12:16:00 | Normal           | Ariel  | Spayed Female   |
| A373687   | Dog         | 2014-03-20 12:31:00 | Normal           | Rosie  | Spayed Female   |
| A412697   | Dog         | 2016-01-03 16:25:00 | Normal           | Jackie | Neutered Male   |
| A413789   | Dog         | 2016-04-19 13:28:00 | Normal           | Benji  | Spayed Female   |
| A414198   | Dog         | 2015-01-29 15:01:00 | Normal           | Shelly | Spayed Female   |
| A368930   | Dog         | 2014-06-08 13:20:00 | Normal           |        | Spayed Female   |

```
ANIMAL_OUTS
```

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | NAME  | SEX_UPON_OUTCOME |
| --------- | ----------- | ------------------- | ----- | ---------------- |
| A354597   | Cat         | 2014-05-02 12:16:00 | Ariel | Spayed Female    |
| A373687   | Dog         | 2014-03-20 12:31:00 | Rosie | Spayed Female    |
| A368930   | Dog         | 2014-06-13 15:52:00 |       | Spayed Female    |

SQL문을 실행하면 다음과 같이 나와야 합니다.

| NAME   | DATETIME            |
| ------ | ------------------- |
| Shelly | 2015-01-29 15:01:00 |
| Jackie | 2016-01-03 16:25:00 |
| Benji  | 2016-04-19 13:28:00 |

※ 입양을 가지 못한 동물이 3마리 이상인 경우만 입력으로 주어집니다.

---

### 내 답과 풀이

- JOIN
  - (INNER) JOIN: 교집합, ON절 조건을 만족하는 데이터만 가져옴
  - LEFT JOIN: 왼쪽테이블 중심으로 오른쪽테이블 매칭, 왼쪽은 무조건 표시하고 오른쪽에 매칭되는것 없으면 NULL, 왼쪽 테이블 한개 레코드에 여러 오른쪽 레코드 일치할경우 왼쪽 레코드 여러번 표시
  - RIGTH JOIN: 오른쪽테이블 중심으로 왼쪽테이블 매칭, 오른쪽 무조건 표시하고 왼쪽에 매칭되는 것 없으면 NULL, 오른쪽 테이블 한개 레코드에 여러 왼쪽 레코드 일치할경우 오른쪽 레코드 여러번 표시


```mysql
SELECT I.NAME, I.DATETIME
FROM ANIMAL_INS I
LEFT JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE O.ANIMAL_ID IS NULL
ORDER BY 2
LIMIT 3
```

## 오랜 기간 보호한 동물(2)

> LV3. String, Date

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

`ANIMAL_OUTS` 테이블은 동물 보호소에서 입양 보낸 동물의 정보를 담은 테이블입니다. `ANIMAL_OUTS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `NAME`, `SEX_UPON_OUTCOME`는 각각 동물의 아이디, 생물 종, 입양일, 이름, 성별 및 중성화 여부를 나타냅니다. `ANIMAL_OUTS` 테이블의 `ANIMAL_ID`는 `ANIMAL_INS`의 `ANIMAL_ID`의 외래 키입니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_OUTCOME | VARCHAR(N) | FALSE    |

입양을 간 동물 중, 보호 기간이 가장 길었던 동물 두 마리의 아이디와 이름을 조회하는 SQL문을 작성해주세요. 이때 결과는 보호 기간이 긴 순으로 조회해야 합니다.

### 예시

예를 들어, `ANIMAL_INS` 테이블과 `ANIMAL_OUTS` 테이블이 다음과 같다면

```
ANIMAL_INS
```

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME       | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | ---------- | --------------- |
| A354597   | Cat         | 2014-05-02 12:16:00 | Normal           | Ariel      | Spayed Female   |
| A362707   | Dog         | 2016-01-27 12:27:00 | Sick             | Girly Girl | Spayed Female   |
| A370507   | Cat         | 2014-10-27 14:43:00 | Normal           | Emily      | Spayed Female   |
| A414513   | Dog         | 2016-06-07 09:17:00 | Normal           | Rocky      | Neutered Male   |

```
ANIMAL_OUTS
```

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | NAME       | SEX_UPON_OUTCOME |
| --------- | ----------- | ------------------- | ---------- | ---------------- |
| A354597   | Cat         | 2014-06-03 12:30:00 | Ariel      | Spayed Female    |
| A362707   | Dog         | 2017-01-10 10:44:00 | Girly Girl | Spayed Female    |
| A370507   | Cat         | 2015-08-15 09:24:00 | Emily      | Spayed Female    |

SQL문을 실행하면 다음과 같이 나와야 합니다.

| ANIMAL_ID | NAME       |
| --------- | ---------- |
| A362707   | Girly Girl |
| A370507   | Emily      |

※ 입양을 간 동물이 2마리 이상인 경우만 입력으로 주어집니다.

### 내 답과 풀이

```mysql
SELECT I.ANIMAL_ID, I.NAME
FROM ANIMAL_INS I
INNER JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
ORDER BY O.DATETIME-I.DATETIME DESC
LIMIT 2
```

## 있었는데요 없었습니다.

> LV3. JOIN

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

`ANIMAL_OUTS` 테이블은 동물 보호소에서 입양 보낸 동물의 정보를 담은 테이블입니다. `ANIMAL_OUTS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `NAME`, `SEX_UPON_OUTCOME`는 각각 동물의 아이디, 생물 종, 입양일, 이름, 성별 및 중성화 여부를 나타냅니다. `ANIMAL_OUTS` 테이블의 `ANIMAL_ID`는 `ANIMAL_INS`의 `ANIMAL_ID`의 외래 키입니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_OUTCOME | VARCHAR(N) | FALSE    |

관리자의 실수로 일부 동물의 입양일이 잘못 입력되었습니다. 보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름을 조회하는 SQL문을 작성해주세요. 이때 결과는 보호 시작일이 빠른 순으로 조회해야합니다.

### 예시

예를 들어, `ANIMAL_INS` 테이블과 `ANIMAL_OUTS` 테이블이 다음과 같다면

```
ANIMAL_INS
```

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME     | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | -------- | --------------- |
| A350276   | Cat         | 2017-08-13 13:50:00 | Normal           | Jewel    | Spayed Female   |
| A381217   | Dog         | 2017-07-08 09:41:00 | Sick             | Cherokee | Neutered Male   |

```
ANIMAL_OUTS
```

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | NAME     | SEX_UPON_OUTCOME |
| --------- | ----------- | ------------------- | -------- | ---------------- |
| A350276   | Cat         | 2018-01-28 17:51:00 | Jewel    | Spayed Female    |
| A381217   | Dog         | 2017-06-09 18:51:00 | Cherokee | Neutered Male    |

SQL문을 실행하면 다음과 같이 나와야 합니다.

| ANIMAL_ID | NAME     |
| --------- | -------- |
| A381217   | Cherokee |

### 내 답과 풀이

```mysql
SELECT I.ANIMAL_ID, I.NAME
FROM ANIMAL_INS I
INNER JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.DATETIME > O.DATETIME
ORDER BY I.DATETIME
```

## 없어진 기록 찾기

> LV3. JOIN

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

`ANIMAL_OUTS` 테이블은 동물 보호소에서 입양 보낸 동물의 정보를 담은 테이블입니다. `ANIMAL_OUTS` 테이블 구조는 다음과 같으며, `ANIMAL_ID`, `ANIMAL_TYPE`, `DATETIME`, `NAME`, `SEX_UPON_OUTCOME`는 각각 동물의 아이디, 생물 종, 입양일, 이름, 성별 및 중성화 여부를 나타냅니다. `ANIMAL_OUTS` 테이블의 `ANIMAL_ID`는 `ANIMAL_INS`의 `ANIMAL_ID`의 외래 키입니다.

| NAME             | TYPE       | NULLABLE |
| ---------------- | ---------- | -------- |
| ANIMAL_ID        | VARCHAR(N) | FALSE    |
| ANIMAL_TYPE      | VARCHAR(N) | FALSE    |
| DATETIME         | DATETIME   | FALSE    |
| NAME             | VARCHAR(N) | TRUE     |
| SEX_UPON_OUTCOME | VARCHAR(N) | FALSE    |

천재지변으로 인해 일부 데이터가 유실되었습니다. 입양을 간 기록은 있는데, 보호소에 들어온 기록이 없는 동물의 ID와 이름을 ID 순으로 조회하는 SQL문을 작성해주세요.

### 예시

예를 들어, `ANIMAL_INS` 테이블과 `ANIMAL_OUTS` 테이블이 다음과 같다면

```
ANIMAL_INS
```

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | INTAKE_CONDITION | NAME | SEX_UPON_INTAKE |
| --------- | ----------- | ------------------- | ---------------- | ---- | --------------- |
| A352713   | Cat         | 2017-04-13 16:29:00 | Normal           | Gia  | Spayed Female   |
| A350375   | Cat         | 2017-03-06 15:01:00 | Normal           | Meo  | Neutered Male   |

```
ANIMAL_OUTS
```

| ANIMAL_ID | ANIMAL_TYPE | DATETIME            | NAME  | SEX_UPON_OUTCOME |
| --------- | ----------- | ------------------- | ----- | ---------------- |
| A349733   | Dog         | 2017-09-27 19:09:00 | Allie | Spayed Female    |
| A352713   | Cat         | 2017-04-25 12:25:00 | Gia   | Spayed Female    |
| A349990   | Cat         | 2018-02-02 14:18:00 | Spice | Spayed Female    |

`ANIMAL_OUTS` 테이블에서

- Allie의 ID는 `ANIMAL_INS`에 없으므로, Allie의 데이터는 유실되었습니다.
- Gia의 ID는 `ANIMAL_INS`에 있으므로, Gia의 데이터는 유실되지 않았습니다.
- Spice의 ID는 `ANIMAL_INS`에 없으므로, Spice의 데이터는 유실되었습니다.

따라서 SQL문을 실행하면 다음과 같이 나와야 합니다.

| ANIMAL_ID | NAME  |
| --------- | ----- |
| A349733   | Allie |
| A349990   | Spice |

### 내 답과 풀이

```mysql
SELECT O.ANIMAL_ID, O.NAME
FROM ANIMAL_OUTS O
LEFT JOIN ANIMAL_INS I
ON O.ANIMAL_ID = I.ANIMAL_ID
WHERE I.DATETIME IS NULL
ORDER BY 1
```

```mysql
SELECT O.ANIMAL_ID, O.NAME
FROM ANIMAL_OUTS O
WHERE O.ANIMAL_ID NOT IN (SELECT I.ANIMAL_ID FROM ANIMAL_INS I)
ORDER BY 1
```

## 즐겨찾기가 가장 많은 식당 정보 출력하기

> LV3. GROUP BY

### 문제 설명

다음은 식당의 정보를 담은 `REST_INFO` 테이블입니다. `REST_INFO` 테이블은 다음과 같으며 `REST_ID`, `REST_NAME`, `FOOD_TYPE`, `VIEWS`, `FAVORITES`, `PARKING_LOT`, `ADDRESS`, `TEL`은 식당 ID, 식당 이름, 음식 종류, 조회수, 즐겨찾기수, 주차장 유무, 주소, 전화번호를 의미합니다.

| Column name | Type         | Nullable |
| ----------- | ------------ | -------- |
| REST_ID     | VARCHAR(5)   | FALSE    |
| REST_NAME   | VARCHAR(50)  | FALSE    |
| FOOD_TYPE   | VARCHAR(20)  | TRUE     |
| VIEWS       | NUMBER       | TRUE     |
| FAVORITES   | NUMBER       | TRUE     |
| PARKING_LOT | VARCHAR(1)   | TRUE     |
| ADDRESS     | VARCHAR(100) | TRUE     |
| TEL         | VARCHAR(100) | TRUE     |

`REST_INFO` 테이블에서 음식종류별로 즐겨찾기수가 가장 많은 식당의 음식 종류, ID, 식당 이름, 즐겨찾기수를 조회하는 SQL문을 작성해주세요. 이때 결과는 음식 종류를 기준으로 내림차순 정렬해주세요.

### 예시

`REST_INFO` 테이블이 다음과 같을 때

| REST_ID | REST_NAME    | FOOD_TYPE | VIEWS   | FAVORITES | PARKING_LOT | ADDRESS                            | TEL           |
| ------- | ------------ | --------- | ------- | --------- | ----------- | ---------------------------------- | ------------- |
| 00001   | 은돼지식당   | 한식      | 1150345 | 734       | N           | 서울특별시 중구 다산로 149         | 010-4484-8751 |
| 00002   | 하이가쯔네   | 일식      | 120034  | 112       | N           | 서울시 중구 신당동 375-21          | NULL          |
| 00003   | 따띠따띠뜨   | 양식      | 1234023 | 102       | N           | 서울시 강남구 신사동 627-3 1F      | 02-6397-1023  |
| 00004   | 스시사카우스 | 일식      | 1522074 | 230       | N           | 서울시 서울시 강남구 신사동 627-27 | 010-9394-2554 |
| 00005   | 코슌스       | 일식      | 15301   | 123       | N           | 서울특별시 강남구 언주로153길      | 010-1315-8729 |

SQL을 실행하면 다음과 같이 출력되어야 합니다.

| FOOD_TYPE | REST_ID | REST_NAME    | FAVORITES |
| --------- | ------- | ------------ | --------- |
| 한식      | 00001   | 은돼지식당   | 734       |
| 일식      | 00004   | 스시사카우스 | 230       |
| 양식      | 00003   | 따띠따띠뜨   | 102       |

### 내 답과 풀이

```mysql
SELECT R.FOOD_TYPE, R.REST_ID, R.REST_NAME, R.FAVORITES
FROM REST_INFO R
INNER JOIN (SELECT FOOD_TYPE, MAX(FAVORITES) AS MFAVOR
           FROM REST_INFO
           GROUP BY FOOD_TYPE) MR
ON R.FOOD_TYPE = MR.FOOD_TYPE AND R.FAVORITES = MR.MFAVOR
ORDER BY FOOD_TYPE DESC
```

## 조건별로 분류하여 주문상태 출력하기

> LV3. String, Date

### 문제 설명

다음은 식품공장의 주문정보를 담은 `FOOD_ORDER` 테이블입니다. `FOOD_ORDER` 테이블은 다음과 같으며 `ORDER_ID`, `PRODUCT_ID`, `AMOUNT`, `PRODUCE_DATE`, `IN_DATE`,`OUT_DATE`,`FACTORY_ID`, `WAREHOUSE_ID`는 각각 주문 ID, 제품 ID, 주문양, 생산일자, 입고일자, 출고일자, 공장 ID, 창고 ID를 의미합니다.

| Column name  | Type        | Nullable |
| ------------ | ----------- | -------- |
| ORDER_ID     | VARCHAR(10) | FALSE    |
| PRODUCT_ID   | VARCHAR(5)  | FALSE    |
| AMOUNT       | NUMBER      | FALSE    |
| PRODUCE_DATE | DATE        | TRUE     |
| IN_DATE      | DATE        | TRUE     |
| OUT_DATE     | DATE        | TRUE     |
| FACTORY_ID   | VARCHAR(10) | FALSE    |
| WAREHOUSE_ID | VARCHAR(10) | FALSE    |

`FOOD_ORDER` 테이블에서 5월 1일을 기준으로 주문 ID, 제품 ID, 출고일자, 출고여부를 조회하는 SQL문을 작성해주세요. 출고여부는 5월 1일까지 출고완료로 이 후 날짜는 출고 대기로 미정이면 출고미정으로 출력해주시고, 결과는 주문 ID를 기준으로 오름차순 정렬해주세요.

### 예시

`FOOD_ORDER` 테이블이 다음과 같을 때

| ORDER_ID   | PRODUCT_ID | AMOUNT | PRODUCE_DATE | IN_DATE    | OUT_DATE   | FACTORY_ID | WAREHOUSE_ID |
| ---------- | ---------- | ------ | ------------ | ---------- | ---------- | ---------- | ------------ |
| OD00000051 | P0002      | 4000   | 2022-04-01   | 2022-04-21 | 2022-04-21 | FT19970003 | WH0005       |
| OD00000052 | P0003      | 2500   | 2022-04-10   | 2022-04-27 | 2022-04-27 | FT19970003 | WH0006       |
| OD00000053 | P0005      | 6200   | 2022-04-15   | 2022-04-30 | 2022-05-01 | FT19940003 | WH0003       |
| OD00000054 | P0006      | 1000   | 2022-04-21   | 2022-04-30 | NULL       | FT19940003 | WH0009       |
| OD00000055 | P0008      | 1500   | 2022-04-25   | 2022-05-11 | 2022-05-11 | FT19980003 | WH0009       |

SQL을 실행하면 다음과 같이 출력되어야 합니다.

| ORDER_ID   | PRODUCT_ID | OUT_DATE   | 출고여부 |
| ---------- | ---------- | ---------- | -------- |
| OD00000051 | P0002      | 2022-04-21 | 출고완료 |
| OD00000052 | P0003      | 2022-04-27 | 출고완료 |
| OD00000053 | P0005      | 2022-05-01 | 출고완료 |
| OD00000054 | P0006      |            | 출고미정 |
| OD00000055 | P0008      | 2022-05-11 | 출고대기 |

### 내 답과 풀이

```mysql
SELECT ORDER_ID, PRODUCT_ID, DATE_FORMAT(OUT_DATE, "%Y-%m-%d") AS OUT_DATE, 
                                CASE WHEN OUT_DATE IS NULL THEN "출고미정"
                                     WHEN OUT_DATE >= "2022-05-02" THEN "출고대기"
                                     ELSE "출고완료"
                                END AS `출고여부`
FROM FOOD_ORDER
ORDER BY ORDER_ID
```

## 헤비 유저가 소유한 장소

> LV3. 2021 Dev-Matching: 웹 백엔드 개발자(상반기)

### 문제 설명

`PLACES` 테이블은 공간 임대 서비스에 등록된 공간의 정보를 담은 테이블입니다. `PLACES` 테이블의 구조는 다음과 같으며 `ID`, `NAME`, `HOST_ID`는 각각 공간의 아이디, 이름, 공간을 소유한 유저의 아이디를 나타냅니다. `ID`는 기본키입니다.

| NAME    | TYPE    |
| ------- | ------- |
| ID      | INT     |
| NAME    | VARCHAR |
| HOST_ID | INT     |

이 서비스에서는 공간을 둘 이상 등록한 사람을 "헤비 유저"라고 부릅니다. 헤비 유저가 등록한 공간의 정보를 아이디 순으로 조회하는 SQL문을 작성해주세요.

### 예시

예를 들어, `PLACES` 테이블이 다음과 같다면

| ID       | NAME                                            | HOST_ID  |
| -------- | ----------------------------------------------- | -------- |
| 4431977  | BOUTIQUE STAYS - Somerset Terrace, Pet Friendly | 760849   |
| 5194998  | BOUTIQUE STAYS - Elwood Beaches 3, Pet Friendly | 760849   |
| 16045624 | Urban Jungle in the Heart of Melbourne          | 30900122 |
| 17810814 | Stylish Bayside Retreat with a Luscious Garden  | 760849   |
| 22740286 | FREE PARKING - The Velvet Lux in Melbourne CBD  | 30900122 |
| 22868779 | ★ Fresh Fitzroy Pad with City Views! ★          | 21058208 |

- 760849번 유저는 공간을 3개 등록했으므로 이 유저는 헤비유저입니다.
- 30900122번 유저는 공간을 2개 등록했으므로 이 유저는 헤비유저입니다.
- 21058208번 유저는 공간을 1개 등록했으므로 이 유저는 헤비유저가 아닙니다.

따라서 SQL 문을 실행하면 다음과 같이 나와야 합니다.

| ID       | NAME                                            | HOST_ID  |
| -------- | ----------------------------------------------- | -------- |
| 4431977  | BOUTIQUE STAYS - Somerset Terrace, Pet Friendly | 760849   |
| 5194998  | BOUTIQUE STAYS - Elwood Beaches 3, Pet Friendly | 760849   |
| 16045624 | Urban Jungle in the Heart of Melbourne          | 30900122 |
| 17810814 | Stylish Bayside Retreat with a Luscious Garden  | 760849   |
| 22740286 | FREE PARKING - The Velvet Lux in Melbourne CBD  | 30900122 |

### 내 답과 풀이

```mysql
SELECT *
FROM PLACES
WHERE HOST_ID IN (SELECT HOST_ID
                 FROM PLACES
                 GROUP BY HOST_ID
                 HAVING COUNT(HOST_ID) >= 2)
```
