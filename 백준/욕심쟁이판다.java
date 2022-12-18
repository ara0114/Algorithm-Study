import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1937 {

  static int n;//대나무 숲의 크기
  static int[][] bamboo;//대나무 숲
  static int[][] dpCnt;//해당 좌표에서 갈 수 있는 최대거리 저장배열
  
  //상하좌우
  static int[] dx = {-1,1,0,0};
  static int[] dy = {0,0,-1,1};
  
  static int count = 0;//판다가 이동할 수 있는 칸의 수 최대값
  
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");
    
    n = Integer.parseInt(st.nextToken());
    bamboo = new int[n][n];
    dpCnt = new int[n][n];
    
    for(int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine()," ");
      for(int j = 0; j < n; j++) {
        bamboo[i][j] = Integer.parseInt(st.nextToken());//대나무 숲의 정보
      }
    }
    
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        count = Math.max(count, dfs(i,j));//이동거리를 최대로 하는 칸의 수 최대값
      }
    }
    
    System.out.println(count);
  }
  static int dfs(int x, int y) {
    if(dpCnt[x][y] != 0) {
      return dpCnt[x][y];
    }
 
    dpCnt[x][y] = 1;//자기자신 1 세팅
    
    for(int d = 0; d < 4; d++) {//상하좌우 4방향 탐색
      int nx = x + dx[d];
      int ny = y + dy[d];
      
      if(0 <= nx && nx < n && 0 <= ny && ny < n && bamboo[x][y] < bamboo[nx][ny]) {//맵 안에 있고, 현재자리보다 다음자리 대나무가 더 많을때
        dpCnt[x][y] = Math.max(dpCnt[x][y], dfs(nx,ny) + 1);//4방향 중 최댓값갱신
      }
    }
    
    return dpCnt[x][y];
  }

}