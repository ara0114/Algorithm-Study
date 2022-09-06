# 하나의 수열에 크기에 상관없이 다양한 수가 나열
# 큰 수부터 작은 수의 순서로 정렬(내림차순)
# 수열에 속해있는 수의 갯수 N
# 입력으로 주어진 수열이 내림차순으로 정렬된 결과를 공백으로 구분하여 출력

# 숫자의 갯수 n 입력받기
n = int(input())

# n개의 정수 입력받아 리스트에 저장
array = []
for i in range(n):
    array.append(int(input()))

# 내림차순정렬
array = sorted(array, reverse=True)

# 정렬 결과 공백으로 구분하여 출력
for i in array:
    print(i, end=' ')
