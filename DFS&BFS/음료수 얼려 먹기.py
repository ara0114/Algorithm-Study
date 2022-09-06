# n x m 크기의 얼음틀
# 구멍이 뚫려 있는 부분은 0, 칸막이가 있는 부분은 1로 표시
# 구멍이 뚫려 있는 부분끼리 상,하,좌,우로 붙어있는 경우 서로 연결된 것으로 간주
# 얼음 틀의 모양이 주어졌을때 생성되는 총 아이스크림의 갯수를 구하는 프로그램 작성
# (0인 값이 상,하,좌,우로 연결되어 있는 묶음의 갯수)

# n, m을 공백으로 구분하여 입력받기
n, m = map(int, input().split())

# 2차원 리스트 맵 정보 입력받기
graph = []
for i in range(n):
    graph.append(list(map(int, input())))

# DFS로 특정 노드 방문한 뒤 연결된 노드들 방문


def dfs(x, y):
    if x <= -1 or x >= n or y <= -1 or y >= m:  # 주어진 범위 벗어나는 경우 종료
        return False
    if graph[x][y] == 0:  # 현재 노드를 아직 방문하지않았다면
        graph[x][y] = 1  # 해당 노드 방문 처리
        # 상하좌우 위치 모두 재귀적으로 호출
        dfs(x-1, y)
        dfs(x, y-1)
        dfs(x+1, y)
        dfs(x, y+1)
        return True
    return False


# 모든 노드(위치)에 대해 음료수 채우기
result = 0
for i in range(n):
    for j in range(m):
        if dfs(i, j) == True:
            result += 1

print(result)
