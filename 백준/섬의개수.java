import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CntIsland {
  static int w;
  static int h;
  static int[][] map;
  static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
  static boolean[][] visited;
  static int count;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    while(true) {
      st = new StringTokenizer(br.readLine()," ");
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());
      
      if(w == 0 && h == 0)
        break;
      
      map = new int[h][w];
      visited = new boolean[h][w];
      
      for(int i = 0; i < h; i++) {
        st = new StringTokenizer(br.readLine()," ");
        for(int j = 0; j < w; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      
      count = 0;
      for(int i = 0; i< h; i++) {
        for(int j = 0; j < w; j++) {
          if(map[i][j] == 1 && !visited[i][j]) {
            dfs(i,j);
            count++;
          }
        }
      }
      System.out.println(count);
    }
  }
  
  public static void dfs(int x, int y) {
    visited[x][y] = true;
    
    for(int d = 0; d < 8; d++) {
      int nx = x + dx[d];
      int ny = y + dy[d];
      
      if(nx >= 0 && nx < h && ny >= 0 && ny < w) {
        if(map[nx][ny] == 1 && !visited[nx][ny]) {
          dfs(nx,ny);
        }
      }
    }
  }
}
