## 아기 상어

> NO.16236, 삼성 SW 역량 테스트 기출, 구현, 시뮬레이션, 그래프 이론, 그래프 탐색, BFS



### 문제 

N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 물고기가 최대 1마리 존재한다.

아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다. 가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.

아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다. 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.

아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.

- 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
- 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
- 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
  - 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
  - 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.

아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다. 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.

아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.

공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.

[문제 자세히 보기](https://www.acmicpc.net/problem/16236)

### 입력

첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.

둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.

- 0: 빈 칸
- 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
- 9: 아기 상어의 위치

아기 상어는 공간에 한 마리 있다.

### 출력

첫째 줄에 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력한다.

### 제한

- 4 ≤ N ≤ 50
- 0 ≤ M ≤ N2
- 1 ≤ K ≤ 1,000
- 1 ≤ ri, ci ≤ N
- 1 ≤ mi ≤ 1,000
- 1 ≤ si ≤ 1,000
- 0 ≤ di ≤ 7

### 입출력 예 

![image](https://user-images.githubusercontent.com/103404127/195530075-1ff88de2-9a24-4a48-9f15-d02cb0eeeb92.png)

---

### 내 답과 풀이

- map의 값 9라면 아기상어 위치한 것이므로 상어 셋팅해주기
- shark.time = Integer.MAX_VALUE 시간 최대로 설정해서 최소값으로 갱신되는지 확인할것
- 인접한 상하좌우로 이동할때 시간 +1초
- 아기상어 자신보다 큰 물고기 지날수 없음.
- 아기상어 자신이랑 크기 같으면 지나기만 할 수 있음. 먹지는 못함
- 아기상어 자신보다 작은 물고기 지날수 있고 먹을 수 있음
  - 1마리면 먹음
  - 1마리보다 많으면 거리가 가까운 물고기 먹기
    - 거리 같은거 여러마리면, 그중 가장 위쪽의 물고기
      - 가장 위쪽에 여러마리면, 그중 가장 왼쪽의 물고기

- 먹은 물고기의 수가 아기상어의 크기와 같으면 아기상어의 크기 1증가
- 더이상 먹을 수 없을때 시간

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BabyShark {
  
  static class Shark{
    int x, y;//아기상어좌표
    int time;//시간
    
    public Shark(int x, int y, int time) {
      this.x = x;
      this.y = y;
      this.time = time;
    }
  }
  
  static int N;//공간크기
  static int[][] map;//공간상태
  //0: 빈칸, 1~6: 칸에 있는 물고기크기, 9: 아기상어위치
  
  //상어 이동방향:상하좌우
  static int[] dx= {-1,1,0,0};
  static int[] dy= {0,0,-1,1};
  static Shark shark;
  static int sharkSize;//아기상어의 처음크기 2,같은 크기의 물고기 먹을 때마다 1증가
  static int sharkEat;//상어가 먹은 물고기 수
  static int answer = 0;
  
  static boolean[][] visited;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    
    map = new int[N][N];
    
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine()," ");
      for(int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] == 9) {//아기상어면
          shark = new Shark(i,j,0);
          sharkSize = 2;
          sharkEat = 0;
          map[i][j] = 0;//상어위치 표시해둿으니까 빈칸으로 갱신
        }
      }
    }
    
    do {
      visited = new boolean[N][N];
      Queue<Shark> q = new LinkedList<Shark>();
      visited[shark.x][shark.y] = true;
      q.add(new Shark(shark.x, shark.y, 0));
      shark.time = Integer.MAX_VALUE;
      
      while(!q.isEmpty()) {
        Shark cur = q.poll();
        
        if(cur.time > shark.time) break;//이미 가장 가까운 물고기이므로 더이상 확인할거 없음.
        if(map[cur.x][cur.y] > sharkSize) continue;//물고기가 더 크면 지나갈수없으므로 스킵
        if(map[cur.x][cur.y] < sharkSize && map[cur.x][cur.y] != 0) {//맵에 있는 물고기보다 상어 크기가 크면(물고기 먹을수있는조건)
          if(cur.time < shark.time) {//거리 더 작을때 갱신
            shark = cur;
          }else if(cur.time == shark.time) {//거리 같을 때
            if(shark.x > cur.x || (shark.x == cur.x && shark.y > cur.y)) {//가장 위, 가장 위에 있는 물고기 여럿이면 가장 왼쪽이면 갱신
              shark = cur;
            }
          }
        }
        
        for(int dir = 0; dir < 4; dir++) {//상하좌우탐색
          int nextX = cur.x + dx[dir];
          int nextY = cur.y + dy[dir];
          int nextTime = cur.time + 1;

          if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;  //공간 안에 있는지 체크
          if(visited[nextX][nextY]) continue; //방문했는지 체크
          
          if(!visited[nextX][nextY] && sharkSize >= map[nextX][nextY]) {//움직일수있는지체크(방문하지않았고, 상어크기 물고기보다 크거나 같을때)
            visited[nextX][nextY] = true;
            q.add(new Shark(nextX, nextY, nextTime));
          }
        }
      }
      
      if(shark.time != Integer.MAX_VALUE) {//물고기먹을수잇다면
        answer += shark.time;//시간 갱신
        sharkEat++;//먹은갯수증가
        if(sharkEat == sharkSize) {//먹은물고기수와 상어크기같으면
          sharkSize++;//상어사이즈 증가
          sharkEat = 0;//먹은 물고기수 초기화
        }
        map[shark.x][shark.y] = 0;//먹었으니까 빈칸으로        
      }
    }while(shark.time != Integer.MAX_VALUE); 
    
    System.out.println(answer);
  }
}
```

