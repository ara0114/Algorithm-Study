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
  
  static void rotate(int curX, int curY, int len, int unit) {//시계방향 90도회전
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
