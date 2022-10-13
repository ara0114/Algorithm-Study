## 등산로 조성

> 1949, [모의 SW 역량테스트], DFS



### 문제 

등산로를 조성하려고 한다.

등산로를 만들기 위한 부지는 N * N 크기를 가지고 있으며, 이곳에 최대한 긴 등산로를 만들 계획이다.

등산로 부지는 아래 [Fig. 1]과 같이 숫자가 표시된 지도로 주어지며, 각 숫자는 지형의 높이를 나타낸다.
 

![img](https://swexpertacademy.com/main/common/fileDownload.do?downloadType=CKEditorImages&fileId=AV5PvGLaAbQDFAUq) 


등산로를 만드는 규칙은 다음과 같다.

  ① 등산로는 가장 높은 봉우리에서 시작해야 한다.

  ② 등산로는 산으로 올라갈 수 있도록 반드시 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결이 되어야 한다.
    즉, 높이가 같은 곳 혹은 낮은 지형이나, 대각선 방향의 연결은 불가능하다.

  ③ 긴 등산로를 만들기 위해 **딱 한 곳**을 정해서 최대 K 깊이만큼 지형을 깎는 공사를 할 수 있다.

N * N 크기의 지도가 주어지고, 최대 공사 가능 깊이 K가 주어진다.

이때 만들 수 있는 가장 긴 등산로를 찾아 그 길이를 출력하는 프로그램을 작성하라.

[문제 자세히 보기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq)

### 입력

입력의 맨 첫 줄에는 총 테스트 케이스의 개수 T가 주어지고, 그 다음 줄부터 T개의 테스트 케이스가 주어진다.

각 테스트 케이스의 첫 번째 줄에는 지도의 한 변의 길이 N, 최대 공사 가능 깊이 K가 차례로 주어진다.

그 다음 N개의 줄에는 N * N 크기의 지도 정보가 주어진다.

### 출력

테스트 케이스 개수만큼 T개의 줄에 각각의 테스트 케이스에 대한 답을 출력한다.

각 줄은 "#t"로 시작하고 공백을 하나 둔 다음 정답을 출력한다. (t는 1부터 시작하는 테스트 케이스의 번호이다)

출력해야 할 정답은 만들 수 있는 가장 긴 등산로의 길이이다.

### 제한

1. 시간 제한 : 최대 51개 테스트 케이스를 모두 통과하는 데 C/C++/Java 모두 3초

2. 지도의 한 변의 길이 N은 3 이상 8 이하의 정수이다. (3 ≤ N ≤ 8)

3. 최대 공사 가능 깊이 K는 1 이상 5 이하의 정수이다. (1 ≤ K ≤ 5)

4. 지도에 나타나는 지형의 높이는 1 이상 20 이하의 정수이다.

5. 지도에서 가장 높은 봉우리는 최대 5개이다.

6. 지형은 정수 단위로만 깎을 수 있다.

7. 필요한 경우 지형을 깎아 높이를 1보다 작게 만드는 것도 가능하다.

### 입출력 예 

- 문제링크참조

---

### 내 답과 풀이

- 대각선 방향 이동은 불가하고 가로 또는 세로 방향이므로 방향은 총 4개 상하좌우임
- 등산로의 길이는 지나간 칸의 갯수임(갱신되는 answer와 length값 비교해서 최대값)
- K는 최대 공사 깊이이므로 처음부터 전부 깎지말고 1씩만 깍아나가기
- 기존 높이는 탐색할 때 계속 써야하므로 백업해두고 깎는거 진행해야함
-  방문여부도 다시 길 찾을때 사용하므로 리셋

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakeHikingTrail {
  static int N, K;
  static int[][] map;
  static boolean[][] visited;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static int answer;
  
  public static void main(String args[]) throws Exception{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int T = Integer.parseInt(st.nextToken());

    for(int test_case = 1; test_case <= T; test_case++){
      st = new StringTokenizer(br.readLine()," ");
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      map = new int[N][N];
      visited = new boolean[N][N];
      
      int max = 0;
      for(int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine()," ");
        for(int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
          if(max < map[i][j]) {//최고높이 기록해두기
            max = map[i][j];
          }
        }
      }
      
      answer = 0;
      
      for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
          if(map[i][j] == max) {//최고높이일 때 dfs
            visited[i][j] = true;
            dfs(i, j, false, 1);
            visited[i][j] = false;
          }
        }
      }
      System.out.println("#"+test_case+" "+answer);
    }
  }
  
  static void dfs(int x, int y, boolean cut, int length) {//cut은 산 깎았는지 여부
    answer = Math.max(answer, length);//최대값 비교 갱신
    
    for(int d = 0; d < 4; d++) {//모든방향탐색
      int nextX = x + dx[d];
      int nextY = y + dy[d];
      
      if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY]) continue; //공간 벗어나거나 이미 방문했으면 스킵

      visited[nextX][nextY] = true;
      
      if(map[nextX][nextY] < map[x][y]) {//다음 봉우리 높이 현재보다 낮으면 dfs
        dfs(nextX, nextY, cut, length + 1);
      }else if(cut == false && (map[nextX][nextY] - map[x][y]) < K){//아직 깎지 않았고, 다음봉우리 깎을 수 있으면(다음지형이 현재보다 높지만 깎을 높이가 k보다 작으면)
        int temp = map[nextX][nextY];//높이 값 복사해두고
        map[nextX][nextY] = map[x][y] - 1;//깎아서
        dfs(nextX, nextY, true, length + 1);//dfs 다시(깎았으므로 cut은 true로)
        map[nextX][nextY] = temp;//복사해둔 값 가져와
      }
      visited[nextX][nextY] = false;
    }
  }
}

```

