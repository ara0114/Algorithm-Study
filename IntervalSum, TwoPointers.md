## 특정 연속된 구간 처리하기

### 구간 합(Interval sum)

- 시간복잡도를 줄이기 위한 특수목적 알고리즘
- Prefix Sum 이용하기

```
[Prefix Sum 활용한 알고리즘 설명]
1. Prefix Sum을 계산해 배열P에 저장
2. 매 M개의 쿼리 정보를 확인할 때, 구간합은 P[R] - P[L-1]
```

```python
# L~R구간 합 빠르게 계산하기
# 데이터의 개수 N과 데이터 입력받기
n = 5
data = [10,20,30,40,50]

# Prefix Sum 배열계산
summary = 0
prefix_sum = [0]
for i in data:
    summary += i
    prefix_sum_append(summary)
    
# 구간 합 계산
left = 3
right = 4
print(prefix_sum[right] - prefix_sum[left-1])
```

### 투포인터(Two pointers)

- 리스트에 순차적으로 접근해야할때 두개의 점을 이용해 위치를 기록하면서 접근하는 기법

```
[투 포인터 활용한 알고리즘 설명]
1. 시작점(start)과 끝점(end)이 첫번째 원소의 인덱스(0)를 가리키도록 함
2. 현재 부분합이 M과 같다면, 카운트
3. 현재 부분합이 M보다 작거나 같다면, end를 1 증가
4. 현재 부분합이 M보다 크다면, start를 1 증가
5. 모든 경우를 확인할 때까지 2번부터 4번까지의 과정 반복
```

```java
// 투 포인터 이동원칙
N 변수 설정
사용변수 초기화 count = 1,start_index = 1, end_index = 1, sum = 1
while(end_index != N){
    if(sum == N){
        end_index++;
        sum = sum + end_index;
        count++;
    }else if(sum < N){
        end_indx++;
        sum = sum + end_index;
    }else if(sum > N){
        sum = sum - start_index;
        start_index++;
    }
}
count출력
```

```python
# N개의 자연수로 구성된 수열, 합이M인 부분연속수열 갯수 구하기
# 데이터의 개수 N과 부분 연속 수열의 합 M을 입력받기
n, m = 5, 5
date = [1,2,3,2,5]
result = 0
summary = 0
end = 0

# start를 차례로 증가시키며 반복
for start in range(n):
    #end를 가능한 만큼 이동시키기
    while summary < m and end < n:
        summary += date[end]
        end += 1
    # 부분합이 m일때 카운트 증가
    if summary == m : 
        result += 1
    summary -= data[start]

print(result)
```

