# 다양한 수로 이루어진 배열이 있을때 주어진 수들을 m번 더하여 가장 큰 수를 만드는 법칙
# 배열의 특정한 인덱스에 해당하는 수가 연속해서 k번을 초과해 더해질수 없음
#[ n = 배열의 크기, m = 숫자가 더해지는 횟수, k = 연속해서 더해질 수 있는 횟수]

# n,m,k 를 공백으로 구분하여 입력받기
n,m,k = map(int,input().split())

# n개의 수를 공백으로 구분하여 입력받기
data = list(map(int,input().split()))

# 입력받은 수 오름차순 정렬
data.sort()

first = data[n-1] # 가장 큰 수
second = data[n-2] # 두번째로 큰 수

# 가장 큰 수와 두번째로 큰 수의 합이 더해지는 횟수
cnt = m//k

result = 0

result = ((first * k)+second)*cnt

print(result)

