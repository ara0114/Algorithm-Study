import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Artistry {
  static int n;
  static int[][] map;
  static int[][] copyMap;

  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static boolean[][] visited;
  
  static int gNum;//그룹의 갯수(몇번그룹까지있는지)
  static int[][] group;//그룹번호 적을거임
  static int[] gNumCnt;//그룹마다 칸수
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    map = new int[n][n];
//    visited = new boolean[n][n];
    
    for(int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine()," ");
      for(int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    copyMap = new int[n][n];
    int answer = 0;
    for(int i = 0; i < 4; i++) {//최초~3회전
      //점수구해서 더하기
      answer += Score();
      //회전
      rotate();
    }
    
    System.out.println(answer);
  }
  static int Score() {//점수
    visited = new boolean[n][n];
    group = new int[n][n];
    gNumCnt = new int[n * n +1];//최대 맵의 칸갯수니까
    grouping();//그룹핑
    return getScore();//예술점수구하기
  }
  static void grouping() {//그룹핑
    gNum = 0;
    
    for(int i = 0; i < n; i++) {//dfs이용해그룹만들기
      for(int j = 0; j < n; j++) {
        if(!visited[i][j]) {
          gNum++;
          visited[i][j] = true;
          group[i][j] = gNum;
          gNumCnt[gNum] = 1;
          dfs(i, j);
        }
      }
    }
  }
  static void dfs(int x, int y) {//dfs
    for(int i = 0; i < 4 ; i++) {
      int nextX = x + dx[i];
      int nextY = y + dy[i];
      
      if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextX][nextY] && map[nextX][nextY] == map[x][y]) {
        //공간안에 있고 인접한 숫자가 동일하며 방문한적이 없을때
        visited[nextX][nextY] = true;
        group[nextX][nextY] = gNum;
        gNumCnt[gNum]++;
        dfs(nextX,nextY);
      }
    }
  }
  static int getScore() {//예술점수구하기
    int artScore = 0;
    //특정 변을 사이에 두고 두칸의 그룹이 다르면
    //(그룹a에 속한 칸 수 + 그룹b에 속한 칸 수)*a숫자 값*b숫자 값*a와 b가 맞닿은 변의 수
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        for(int d = 0; d < 4; d++) {
          
          int nextX = i + dx[d];
          int nextY = j + dy[d];
          if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && map[i][j] != map[nextX][nextY]) {
            int g1 = group[i][j];
            int g2 = group[nextX][nextY];
            
            int n1 = map[i][j];
            int n2 = map[nextX][nextY];
            
            int cnt1 = gNumCnt[g1];
            int cnt2 = gNumCnt[g2];
            
            artScore += (cnt1 + cnt2) * n1 * n2;
          }
        }
      }
    }
    return artScore / 2;
    //중복계산제외
  }
  static void rotate(){//회전
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        copyMap[i][j] = 0;
      }
    }
    //반시계방향 회전 x'=y, y'= n-1-x
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        if(j == n/2){
          //십자가중 세로일때
          copyMap[j][i] = map[i][j];
        }
        else if(i == n/2) {
          //십자가중 가로일때
          copyMap[n-1-j][i] = map[i][j];
        }
      }
    }
    
    //4개 정사각형 시계방향 회전(r,c)(r,c+1)(r+1,c)(r+1,c+1)
    int square = n/2;
    rotateSquare(0, 0, square);
    rotateSquare(0, square + 1, square);
    rotateSquare(square + 1, 0, square);
    rotateSquare(square + 1, square + 1, square);
    
    
    for(int i = 0; i < n; i++) {//맵값 돌려주기
      for(int j = 0; j < n; j++) {
        map[i][j] = copyMap[i][j];
      }
    }
  }
  static void rotateSquare(int startX, int startY, int square){//정사각형시계방향회전
    //시계방향회전 x'= n-1-y, y'=x
    for(int x = startX; x < startX + square; x++) {
      for(int y = startY; y < startY + square; y++) {
        int zeroX = x - startX;
        int zeroY = y - startY;
        
        int roX = zeroY;
        int roY = square - zeroX -1;
        
        copyMap[roX + startX][roY + startY]= map[x][y];
      }
    }
  }
}
