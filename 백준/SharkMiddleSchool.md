## 상어 중학교

> NO.21609, 삼성 SW 역량 테스트 기출, 구현, 시뮬레이션, 그래프 이론, 그래프 탐색, BFS, DFS



### 문제 

상어 중학교의 코딩 동아리에서 게임을 만들었다. 이 게임은 크기가 N×N인 격자에서 진행되고, 초기에 격자의 모든 칸에는 블록이 하나씩 들어있고, 블록은 검은색 블록, 무지개 블록, 일반 블록이 있다. 일반 블록은 M가지 색상이 있고, 색은 M이하의 자연수로 표현한다. 검은색 블록은 -1, 무지개 블록은 0으로 표현한다. (i, j)는 격자의 i번 행, j번 열을 의미하고, |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸 (r1, c1)과 (r2, c2)를 인접한 칸이라고 한다.

블록 그룹은 연결된 블록의 집합이다. 그룹에는 일반 블록이 적어도 하나 있어야 하며, 일반 블록의 색은 모두 같아야 한다. 검은색 블록은 포함되면 안 되고, 무지개 블록은 얼마나 들어있든 상관없다. 그룹에 속한 블록의 개수는 2보다 크거나 같아야 하며, 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야 한다. 블록 그룹의 기준 블록은 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작은 블록, 그러한 블록이 여러개면 열의 번호가 가장 작은 블록이다.

오늘은 이 게임에 오토 플레이 기능을 만드려고 한다. 오토 플레이는 다음과 같은 과정이 블록 그룹이 존재하는 동안 계속해서 반복되어야 한다.

1. 크기가 가장 큰 블록 그룹을 찾는다. 그러한 블록 그룹이 여러 개라면 포함된 무지개 블록의 수가 가장 많은 블록 그룹, 그러한 블록도 여러개라면 기준 블록의 행이 가장 큰 것을, 그 것도 여러개이면 열이 가장 큰 것을 찾는다.
2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹에 포함된 블록의 수를 B라고 했을 때, B2점을 획득한다.
3. 격자에 중력이 작용한다.
4. 격자가 90도 반시계 방향으로 회전한다.
5. 다시 격자에 중력이 작용한다.

격자에 중력이 작용하면 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동한다. 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속 된다.

...

오토 플레이가 모두 끝났을 때 획득한 점수의 합을 구해보자.

[문제 자세히 보기](https://www.acmicpc.net/problem/21609)

### 입력

첫째 줄에 격자 한 변의 크기 N, 색상의 개수 M이 주어진다.

둘째 줄부터 N개의 줄에 격자의 칸에 들어있는 블록의 정보가 1번 행부터 N번 행까지 순서대로 주어진다. 각 행에 대한 정보는 1열부터 N열까지 순서대로 주어진다. 입력으로 주어지는 칸의 정보는 -1, 0, M이하의 자연수로만 이루어져 있다.

### 출력

첫째 줄에 획득한 점수의 합을 출력한다.

### 제한

- 1 ≤ N ≤ 20
- 1 ≤ M ≤ 5

### 입출력 예 

![image](https://user-images.githubusercontent.com/103404127/194887741-073b08b7-0236-47fc-a58f-76f7c5bd0c47.png)

---

### 내 답과 풀이

- 풀이보며 이해하기
- Comparable로 블록갯수, 무지개블록갯수, 행, 열 정렬
- 블록그룹
  - 일반블록: 적어도 하나 존재하며, 모두 같은 색
  - 검은색 블록은 존재하지 않음
  - 무지개블록 갯수는 상관 없음
  - 블록의 갯수는 2개 이상
  - 기준 블록은 일반 블록중 행번호가 가장 작은 블록, 같으면 열번호가 가장 작은 블록(가장 위쪽)

- 오토플레이 : 블록 그룹 존재하는 동안 반복
  - 크기가 가장 큰 블록 그룹 찾기, 크기가 같을 경우
    - 무지개 블록 갯수 가장 많은 그룹 -> 기준 블록 행이 가장 큰 것 -> 기준 블록 열이 가장 큰 것
  - 찾은 블록그룹의 블록을 모두 제거하고 점수 획득(갯수가 B라면 B^2점)
  - 중력 작용
    - 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동, 다른 블록이나 경계 만나기 전까지
  - 반시계방향 90도 회전
  - 중력작용
- 반시계 90도 회전
  - X` = Y
  - Y` = N - 1 - X
- System.arraycopy(원본, 원본의 복사시작인덱스, 대상, 대상의 붙여넣기 시작인덱스, 몇개 복사할건지)
- Math.pow(대상숫자, 지수): 입출력 값 모두 double형

- 주석참고

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SharkMiddleSchool {
  static class Block implements Comparable<Block>{//Comparable을 이용해 Block객체 정렬
    int x, y;//기준블록좌표
    int totalCnt;//총블록갯수
    int rainbowCnt;//무지개블록갯수
    public Block(int x, int y, int totalCnt, int rainbowCnt) {
      this.x = x;
      this.y = y;
      this.totalCnt = totalCnt;
      this.rainbowCnt = rainbowCnt;
    }
    @Override
    public int compareTo(Block o) {//비교함수
      if(this.totalCnt == o.totalCnt) {
        if(this.rainbowCnt == o.rainbowCnt) {
          if(this.x == o.x) {
            return o.y - this.y;
          }
          return o.x - this.x;
        }
        return o.rainbowCnt - this.rainbowCnt;
      }
      return o.totalCnt - this.totalCnt;
    }
  }
  
  static int N;//공간 크기 N*N
  static int M;//일반블록 색상 갯수(1~M)
  //검정블록은 -1, 무지개블록은 0으로 표현함
  static int BLANK = -2;//블록값 -1 ~ 5 까지이므로 그 외의 수로 설정
  
  static int[][] map;//블록게입맵
  static boolean[][] visited;//방문여부 저장
  
  //상하좌우
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  
  static ArrayList<Block> blockGroup;//블록그룹저장할리스트
  static int answer = 0;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    
    map = new int[N][N];
    
    
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine()," ");
      for(int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    while(true) {//블록 그룹찾기
      visited = new boolean[N][N];
      blockGroup = new ArrayList<>();
      for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
          if(map[i][j] > 0 && !visited[i][j]) {//일반블록이 있고 방문하지 않았다면
            findBlockGroup(i,j,true);
          }
        }
      }
      if(blockGroup.isEmpty()) {
        break;
      }
      
      Collections.sort(blockGroup);
      //블록 찾고 없애기
      visited = new boolean[N][N];
      findBlockGroup(blockGroup.get(0).x, blockGroup.get(0).y, false);
      removeBlock();//블록제거
      
      gravity();//중력작용
      rotate();//반시계회전
      gravity();//중력작용
      
      blockGroup.clear();
    }
    System.out.println(answer);
  }
  
  static void findBlockGroup(int x, int y, boolean flag) {//bfs로 블록그룹찾기
    Queue<int[]> q = new LinkedList<>();
    int color = map[x][y];
    visited[x][y] = true;
    q.offer(new int[]{x,y});
    int total = 1;
    int rainbow = 0;
    
    while(!q.isEmpty()) {
      int[] cur = q.poll();
      
      for(int d = 0; d < 4; d++) {
        int nextX = cur[0] + dx[d];
        int nextY = cur[1] + dy[d];
        
        if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY]) {//공간 밖이거나 방문했으면
          continue;
        }
        if(map[nextX][nextY] == color || map[nextX][nextY] == 0) {//색깔같거나 무지개블록이면
          if(map[nextX][nextY] == 0) {
            rainbow += 1;
          }
          total += 1;
          visited[nextX][nextY] = true;
          q.offer(new int[] {nextX, nextY});
        }
      }
    }
    
    if(total >= 2) {//블록갯수 2개이상일때만 블록그룹으로
      blockGroup.add(new Block(x, y, total, rainbow));
    }
    if(flag) {
      for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
          if(map[i][j] == 0) {//무지개블록일경우 공통으로 사용될수있으므로 초기화
            visited[i][j] = false;
          }
        }
      }
    }
  }
  
  static void removeBlock() {//블록제거
    int cnt = 0;
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        if(visited[i][j]==true) {
          cnt++;
          map[i][j] = BLANK;
        }
      }
    }
    answer += (int)Math.pow(cnt, 2);//점수획득
  }
  
  static void gravity() {//중력작용
    for(int j = 0; j < N; j++) {
      for(int i = N - 1; i >= 0; i--) {
        if(map[i][j] == BLANK || map[i][j] == -1) continue;
        int destination = i + 1;
        
        while(true) {
          if(destination == N) break;//바닥이면 멈춤
          if(map[destination][j] == BLANK) {//빈칸이면
            destination += 1;
          }else {
            break;
          }
        }
        if(destination == i+1) continue;
        map[destination-1][j] = map[i][j];
        map[i][j] = BLANK;
      }
    }
  }
  
  static void rotate() {//반시계회전
    int[][] temp = new int[N][N];
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        temp[N-j-1][i] = map[i][j];
      }
    }
    for(int i = 0; i < N; i++) {//배열복사
      System.arraycopy(temp[i], 0, map[i], 0, N);
    }
  }
}
```
