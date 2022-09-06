# Sorting : 정렬

: 연속된 데이터를 특정한 기준에 따라 순서대로 나열

- 프로그램 작성 시 가장 많이 사용되는 알고리즘 중 하나

## 버블(bubble) 정렬

- 인접한 데이터 요소끼리 크기를 비교하며 정렬(swap연산을 수행하며 정렬)
- 시간복잡도 O(N^2)

```java
버블 정렬 과정
1) 비교 연산이 필요한 loop범위 설정
2) 인접한 데이터값 비교
3) swap 조건 부합하면 swap 연산 수행
4) loop 범위 끝날때까지 2~3 반복
5) 정렬 영역 설정. 다음 loop실행할때는 이 영역 제외
6) 비교 대상 없을때까지 1~5반복
```

- 특정한 loop 전체 영역에서 swap이 한번도 발생하지 않았다면 그 영역 뒤에 있는 데이터가 모두 정렬됐다는 듯이므로 종료해도됌

## 병합(merge) 정렬

- 분할 정복 방식을 사용해 데이터 분할하고 분할한 집합을 정렬하며 합침
- 이미 정렬된 부분 집합들을 효율적으로 병합해 전체를 정렬하는 방식
- 시간복잡도 O(NlogN)

## 기수(radix) 정렬

- 데이터의 자릿수를 바탕으로 비교해 데이터를 정렬

## 선택(selection) 정렬

- 매번 가장 작은/큰 것을 선택
- 모든 원소를 비교하고 위치를 바꿈
- 가장 작은/큰 데이터를 맨 앞 데이터와 바꾸고 그다음 작은/큰 데이터를 선택해 앞에서 두번째 데이터와 바꾸는 과정 반복

- 가장 작은/큰 것을 선택해 앞으로 보내는 과정 반복 수행

```python
array = [7,5,9,0,3,1,6,2,4,8]

for i in range(len(array)):
    min_index = i # 가장 작은 원소의 인덱스
    for j in range(i+1, len(array)):
        if array[min_index] > array[j]:
            min_index = j
    array[i],array[min_index] = array[min_index], array[i] # 파이썬 swap. 각 인덱스의 원소 교체하기
```

- 시간복잡도 O(N^2)

## 삽입(insertion) 정렬

- 특정한 데이터를 적절한 위치에 삽입
- 필요할때만 위치를 바꿈 => 데이터가 거의 정렬되어있을때 효율적
- 데이터가 적절한 위치에 들어가기 전, 그 앞까지 데이터는 이미 정렬되어 있다고 가정
- 정렬되어 있는 데이터 리스트에서 적절한 위치 찾은 뒤 그 위치에 삽입된다는 특징

- 정렬이 이루어진 원소는 항상 오름차순을 유지 => 삽입될 위치를 찾기 위해 왼쪽으로 한칸씩 이동할 때, 삽입할 데이터 보다 작은 데이터 만나면 그 위치에서 멈추면 됌

```python
array = [7,5,9,0,3,1,6,2,4,8]

for i in range(1, len(array)):
    for j in range(i, 0, -1): # 인덱스 i부터 1까지 감소하며 반복
        if array[j] < array[j-1]: # 왼쪽으로 한칸씩 이동
            array[j], array[j-1] = array[j-1], array[j]
        else: # 자기보다 작은 데이터 만나면 멈춤
            break
```

- 시간복잡도 O(N^2) 이지만, 데이터가 거의 정렬되어 있는 상태라면 매우 빠르게 동작. 최선의 경우 O(N)의 시간복잡도

## 퀵(quick) 정렬

- 기준을 설정한 다음 큰 수와 작은 수를 교환한 후 리스트를 반으로 나누는 방식으로 동작

- pivot(교환하기위한 기준)사용

- 피벗의 왼쪽에는 피벗보다 작은 데이터, 오른쪽에는 피벗보다 큰 데이터가 위치하도록 분할.

  왼쪽과 오른쪽 리스트를 같은 방식으로 다시 정렬

```python
#퀵소트
array = [5,7,9,0,3,1,6,2,4,8]

def quick_sort(array,start,end):
    if start >= end: #원소가 1개일경우 종료
        return
    pivot = start # pivot을 첫번째 원소로 설정
    left = start + 1
    right = end
    while left <= right: 
        #pivot보다 큰 데이터 찾을때까지
        while left < = end and array[left] <= array[pivot]:
            left += 1
        #pivot보다 작은 데이터 찾을때까지
        while right > start and array[right] >= array[pivot]:
            right -= 1
        if left > right: #엇갈렸으면 작은 데이터와 pivot교체
            array[right], array[pivot] = array[pivot], array[right]
        else: #엇갈리지 않았다면 작은 데이터와 큰 데이터 교체
            array[left], array[right] = array[right], array[left]
    #분할 이후 왼쪽/오른쪽 부분에서 각각 정렬 수행
    quick_sort(array, start, right-1)
    quick_sort(array, right_1, end)

quick_sort(array, 0, len(array)-1)

#----------------------------------------------------------------------#
#파이썬 장점을 살린 퀵소트 - 직관적이고 기억하기 쉽지만 비교 연산 횟수 증가하므로 시간면에서는 조금 비효율적임.
array = [5,7,9,0,3,1,6,2,4,8]

def quick_sort(array):
    #리스트가 1개 이하의 원소만 갖고 있으면 종료
    if len(array) <= 1:
        return array
    
    pivot = array[0] #pivot을 첫번째 원소로 설정
	tail = array[1:0] #pivot을 제외한 리스트
    
    left_side = [x for x in tail if x <= pivot] #분할된 왼쪽 리스트
    right_side = [x for x in tail if x > pivot] #분할된 오른쪽 리스트
    
    #분할 이후 왼쪽/오른쪽 부분에서 각각 정렬 수행 후, 전체 리스트 반환
    return quick_sort(left_side) + [pivot] + quick_sort(right_side)

quick_sort(array)
```

- 시간복잡도 O(NlogN) 이지만, 데이터가 이미 정렬되어 있다면 매우 느리게 동작. 최악의 경우 시간복잡도 O(N^2)

## 계수(count) 정렬

- 데이터의 크기 범위가 제한되어 정수형태로 표현할수 있을 때만 사용가능
- 특정한 조건이 부합할때만 사용할 수 있지만 매우 빠름
- 데이터의 갯수 N, 데이터 중 최댓값이 K일때 시간복잡도 O(N+K)
- 모든 범위를 담을 수 있는 크기의 리스트(배열)을 선언해야하므로, 가장 큰 데이터와 작은 데이터의 차이가 너무 크다면 사용할수 없음

- 하나의 리스트를 생성해서 0으로 초기화한후, 데이터 하나씩 확인하며 데이터 값과 동일한 인덱스의 데이터를 1씩 증가 시킴

```python
#모든 원소값 0보다 크거나 같다고 가정
array = [7,5,9,0,3,1,6,2,9,1,4,8,0,5,2]
#모든 범위를 포함하는 리스트 선언(모든 값 0으로 초기화)
count = [0]*(max(array)+1)

for i in range(len(array)):
    count[array[i]] += 1 #각 데이터에 해당하는 인덱스 값 증가
    
for i in range(len(count)): #리스트에 기록된 정렬 정보 확인
    for j in range(count[i]):
        print(i, end='') #띄어쓰기를 구분으로 등장한 횟수만큼 인덱스 출력
```



---

*cf.*파이썬 정렬 라이브러리

- 최악의 경우에도 시간복잡도 O(NlogN) 보장

- sorted()

- sort()
