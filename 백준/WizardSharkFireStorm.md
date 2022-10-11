## 마법사 상어와 파이어스톰

> NO.20058, 삼성 SW 역량 테스트 기출, 구현, 시뮬레이션, 그래프 이론, 그래프 탐색, DFS, BFS



### 문제 

마법사 상어는 [파이어볼](https://www.acmicpc.net/problem/20056)과 [토네이도](https://www.acmicpc.net/problem/20057)를 조합해 파이어스톰을 시전할 수 있다. 오늘은 파이어스톰을 크기가 2^N × 2^N인 격자로 나누어진 얼음판에서 연습하려고 한다. 위치 (r, c)는 격자의 r행 c열을 의미하고, A\[r][c]는 (r, c)에 있는 얼음의 양을 의미한다. A\[r][c]가 0인 경우 얼음이 없는 것이다.

파이어스톰을 시전하려면 시전할 때마다 단계 L을 결정해야 한다. 파이어스톰은 먼저 격자를 2^L × 2^L 크기의 부분 격자로 나눈다. 그 후, 모든 부분 격자를 시계 방향으로 90도 회전시킨다. 이후 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다. (r, c)와 인접한 칸은 (r-1, c), (r+1, c), (r, c-1), (r, c+1)이다. 아래 그림의 칸에 적힌 정수는 칸을 구분하기 위해 적은 정수이다.

| ![img](https://upload.acmicpc.net/68137f5d-fdbd-48c6-92f0-0a74ee53b0c2/-/preview/) | ![img](https://upload.acmicpc.net/4216e4de-a9f7-4bf0-9385-e20c583c1228/-/preview/) | ![img](https://upload.acmicpc.net/a58a4219-afc7-4f77-a194-a5495882eeb4/-/preview/) |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 마법을 시전하기 전                                           | L = 1                                                        | L = 2                                                        |

마법사 상어는 파이어스톰을 총 Q번 시전하려고 한다. 모든 파이어스톰을 시전한 후, 다음 2가지를 구해보자.

1. 남아있는 얼음 A\[r][c]의 합
2. 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수

얼음이 있는 칸이 얼음이 있는 칸과 인접해 있으면, 두 칸을 연결되어 있다고 한다. 덩어리는 연결된 칸의 집합이다.

[문제 자세히 보기](https://www.acmicpc.net/problem/20058)

### 입력

첫째 줄에 N과 Q가 주어진다. 둘째 줄부터 2^N개의 줄에는 격자의 각 칸에 있는 얼음의 양이 주어진다. r번째 줄에서 c번째 주어지는 정수는 A[r][c] 이다.

마지막 줄에는 마법사 상어가 시전한 단계 L1, L2, ..., LQ가 순서대로 주어진다.

### 출력

첫째 줄에 남아있는 얼음 A\[r][c]의 합을 출력하고, 둘째 줄에 가장 큰 덩어리가 차지하는 칸의 개수를 출력한다. 단, 덩어리가 없으면 0을 출력한다.

### 제한

- 2 ≤ N ≤ 6
- 1 ≤ Q ≤ 1,000
- 0 ≤ A\[r][c] ≤ 100
- 0 ≤ Li ≤ N

### 입출력 예 

![image](https://user-images.githubusercontent.com/103404127/195135462-8e21baaf-b195-4178-b449-b36d0ded6263.png)

![image](https://user-images.githubusercontent.com/103404127/195135564-1ecd8a5e-6d49-4f23-ac3c-5e4ac2c6bff2.png)

---

### 내 답과 풀이

- na982님 강의보며 이해하고 따라서 구현해봄
- 시계방향 90도 회전
  - X` = N - 1 - Y
  - Y` = X
  - 원본배열 건들지 않도록 백업 배열로

- 얼음 3칸이상 인접하지 않으면 녹임
- DFS, BFS모두 쓸 수있는데 격자 최대크기가 64(=2^6)로 작아서 DFS를 사용해도된다

- 주석참고

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WizardSharkFireStorm {
  static int N, Q;
  static int M;//2^N
  static int map[][];
  static boolean visited[][];
  static int L[];
  //방향: 상 우 하 좌
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};
  static int curSize = 0;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");
    N = Integer.parseInt(st.nextToken());
    Q = Integer.parseInt(st.nextToken());
    
    M = (int)Math.pow(2, N);
    map = new int[M][M];
    visited = new boolean[M][M];
    L = new int[Q];
    
    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine()," ");
      for(int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    st = new StringTokenizer(br.readLine()," ");
    for(int i = 0; i < Q; i++) {
      L[i] = Integer.parseInt(st.nextToken());
    }
    
    for(int i = 0; i < Q; i++) {
      if(L[i] > 0) {//L이 0이면 2^0으로 1이기때문에 할 필요 없음.
        rotate(0, 0, M, (int)Math.pow(2, L[i]));
      }
      melt();
    }
    
    int total = 0, maxSize = 0;
    for(int i = 0; i < M; i++) {
      for(int j = 0; j < M; j++) {
        total += map[i][j];
        if(!visited[i][j] && map[i][j] != 0) {
          visited[i][j] = true;
          curSize = 1;
          dfs(i, j, visited);
          if(maxSize < curSize) {
            maxSize = curSize;
          }
        }
      }
    }
    System.out.println(total);
    System.out.println(maxSize);
    
  }
  
  static void rotate(int curX, int curY, int len, int unit) {//시계방향 90도 회전
    if(len == unit) {
      int copy[][] = new int[M][M];//원본배열 건들지않게
      for(int i = 0; i < len; i++) {
        for(int j = 0; j < len; j++) {
          copy[curX + j][curY + len - i - 1] = map[curX + i][curY + j];
        }
      }
      for(int i = 0; i < len; i++) {//백업
        for(int j = 0; j < len; j++) {
          map[curX + i][curY + j] = copy[curX + i][curY + j];
        }
      }
      return;
    }
    len /= 2; //격자 나눠서 각각 회전
    rotate(curX, curY, len, unit);
    rotate(curX + len, curY, len, unit);
    rotate(curX, curY + len, len, unit);
    rotate(curX + len, curY + len, len, unit);
  }
  
  static void melt() {//얼음녹이기
    int copy[][] = new int[M][M];
    for(int i = 0; i < M; i++) {
      for(int j = 0; j < M; j++) {
        int cnt = 0;//몇개 면이 얼음과 닿았는지 계산
        for(int d = 0; d < 4; d++) {//모든방향 탐색
          int nextX = i + dx[d];
          int nextY = j + dy[d];
          
          if(nextX < 0 || nextX >= M || nextY < 0 || nextY >= M) {//맵 밖으로 나갔으면
            continue;
          }
          if(map[nextX][nextY] == 0) {//다음위치 얼음없다면
            continue;
          }
          cnt++;
        }
        if(cnt >= 3 || map[i][j] == 0) {//갱신안해도 되는 조건
          copy[i][j] = map[i][j];
        }else {
          copy[i][j] = map[i][j] - 1;
        }
      }
    }
    for(int i = 0; i < M; i++) {//백업
      for(int j = 0; j < M; j++) {
        map[i][j] = copy[i][j];
      }
    }
  }
  
  static void dfs(int x, int y, boolean[][] visited) {
    for(int d = 0; d < 4; d++) {
      int nextX = x + dx[d];
      int nextY = y + dy[d];
      if(nextX < 0 || nextX >= M || nextY < 0 || nextY >= M) {//맵 밖으로 나갔으면
        continue;
      }
      if(visited[nextX][nextY] || map[nextX][nextY] == 0) { //방문했거나 갈수없는 곳이면
        continue;
      }
      visited[nextX][nextY] = true;
      dfs(nextX, nextY, visited);
      curSize++;
    }
  }
}
```
