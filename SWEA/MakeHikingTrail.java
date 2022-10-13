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
  
  static void dfs(int x, int y, boolean cut, int length) {//cut은 산깎았는지여부
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
