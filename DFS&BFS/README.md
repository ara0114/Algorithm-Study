# 탐색
: 많은 양의 데이터 중 원하는 데이터를 찾는 과정
-> 그래프, 트리 등의 자료구조 안에서 탐색하는 문제 자주 다룸
-> 대표적인 탐색 알고리즘으로 DFS, BFS가 있음



## DFS - Depth First Search(깊이 우선 탐색)

: 그래프에서 깊은 부분을 우선으로 탐색

- 스택 자료구조 사용

- 동작과정

  ```python
  1) 탐색 시작 노드를 스택에 삽입하고 방문처리
  2) 스택 최상단 노드에 방문하지 않은 인접 노드 있으면 스택에 넣고 방문처리.
     방문하지 않은 인접 노드가 없으면 스택에서 최상단 노드를 꺼냄
  3) 2를 더이상 수행할 수 없을 때까지 반복
  
  # 방문처리: 스택에 삽이되 처리된 노드가 다시 삽입되지 않게 체크하는 것을 의미(각 노드를 한번씩만 처리할 수 있게 해줌)
  ```

- 순서와 상관없이 처리해도 되지만, 보통 번호가 낮은 순서부터 처리하도록 구현

- 스택 또는 재귀함수 이용하여 구현할 수 있음

```python
#DFS메소드 정의
def dfs(graph, v, visited):
    #현재노드 방문처리
    visited[v] = True
	print(v, end=' ')
    #현재노드와 연결된 다른노드 재귀적으로 방문
    for i in graph[v]:
        if not visited[i]:
            dfs(graph, i, visited)
            
#각 노드가 연결된 정보 2차원 리스트로 표현
graph =[
    [],
    [2,3,8],
    [1,7],
    [1,4,5],
    [3,5],
    [3,4],
    [7],
    [2,6,8],
    [1,7]
]

#각 노드가 방문된 정보를 1차원 리스트로 표현
visited = [False] * 9

#DFS호출
dfs(graph, 1, visited)
```

![image](https://user-images.githubusercontent.com/103404127/188580473-fd473c10-384f-4a62-a058-101948b16787.png)

```java
import java.util.*;

public class Main{
    public static boolean[] visited = new boolean[9];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    
    //DFS 함수 정의
    public static void dfs(int x){
        //현재 노드를 방문 처리
        visited[x] = true;
        System.out.print(x + " ");
        //현재 노드와 연결된 다른 노드를 재귀적으로 방문
        for(int i = 0; i < graph.get(x).size(); i++){
            int y = graph.get(x).get(i);
            if(!visited[y]){
                dfs(y);
            }
        }
    }
    ...
}
```



![image](https://user-images.githubusercontent.com/103404127/188470222-46edf71c-fe07-4da1-bde2-073f69a1060c.png)

## BFS - Breadth Frist Search(너비 우선 탐색)

: 가까운 노드부터 탐색하는 알고리즘

- 큐 자료구조 사용

- 동작과정

  ```python
  1) 탐색 시작 노드를 큐에 삽입하고 방문처리
  2) 큐에서 노드를 꺼내 해당 노드의 인접 노드 중 방문하지 않은 노드를 모두 큐에 삽입하고 방문처리
  3) 2를 더이상 수행할 수 없을 때까지 반복
  ```

- deque 라이브러리 사용하는 것이 좋음

- 일반적인 경우 실제 수행시간 DFS보다 좋은편

```python
from collections import deque

#BFS메소드 정의
def bfs(graph, start, vistied):
    #deque라이브러리 사용하여 큐 구현
    queue = deque([start]) # 시작노드를 큐에 넣어줌
    #현재노드 방문처리
    visited[start] = True
    #큐가 빌 때까지 반복
    while queue:
        #큐에서 원소 하나 뽑아 해당 노드번호 출력
        v = queue.popleft()
        print(v, end=' ')
        #해당 원소와 연결된, 아직 방문하지 않은 원소들 큐에 삽입
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True
                
#각 노드가 연결된 정보 2차원 리스트로 표현
graph =[
    [], # 인덱스 0의 값을 비워 나머지를 노드번호와 인덱스 일치시켜
    [2,3,8],
    [1,7],
    [1,4,5],
    [3,5],
    [3,4],
    [7],
    [2,6,8],
    [1,7]
]

#각 노드가 방문된 정보를 1차원 리스트로 표현
visited = [False] * 9

#BFS호출
bfs(graph, 1, visited)               
```



```java
import java.util.*;
public class Main{
    public static boolean[] visited = new boolean[9];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    
    //BFS함수 정의
    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);//큐에 삽입
        //현재 노드 방문처리
        visited[start] = true;
        //큐가 빌 때까지 반복
        while(!q.isEmpty()){
            //큐에서 하나의 원소를 뽑아 출력
            int x = q.poll();
            System.out.println(x+" ");
            //해당 원소와 연결된 아직 방문하지 않은 원소들을 큐에 삽입
            for(int i = 0; i < graph.get(x).size(); i++){
                int y = graph.get(x).get(i);
                if(!visited[y]){
                    q.offer(y);
                    visited[y] = true;
                }
            }
        }
    }
    ...
}
```



![image](https://user-images.githubusercontent.com/103404127/188470437-925f3938-e33e-4173-95cf-ace8190c8d9d.png)

