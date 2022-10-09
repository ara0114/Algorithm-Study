import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WizardSharkTornado {
  
  static int N;//입력받을 홀수 N
  static int[][] sand;//공간 모래정보
  //방향: 좌,하,우,상
  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {-1, 0, 1, 0};
  
  //각 방향에서 이동하는 칸의 수 : 한 사이클 끝나면 각각 +2씩 증가
  static int[] moveCnt = {1, 1, 2, 2};
  
  //각 방향(좌,하,우,상)에서 모래 퍼지는 방향
  static int[][] sdx = {
      {-1, 1, -2, -1, 1, 2, -1, 1, 0},  //left
      {-1, -1, 0, 0, 0, 0, 1, 1, 2},    //down
      {1, -1, 2, 1, -1, -2, 1, -1, 0},  //right
      {1, 1, 0, 0, 0, 0, -1, -1, -2}    //up
  };
  static int[][] sdy = {
      {1, 1, 0, 0, 0, 0, -1, -1, -2},   //left 
      {-1, 1, -2, -1, 1, 2, -1, 1, 0},  //down
      {-1, -1, 0, 0, 0, 0, 1, 1, 2},    //right
      {1, -1, 2, 1, -1, -2, 1, -1, 0}   //up
  };
  static int[] sratio = {1, 1, 2, 7, 7, 2, 10, 10, 5};//해당 방향에서 모래가 퍼지는 비율

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());//공간크기입력받기
    sand = new int[N][N];

    
    for(int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine()," ");
      for(int j = 0; j < N; j++) {
        sand[i][j] = Integer.parseInt(st.nextToken());//맵정보입력받기
      }
    }

    System.out.println(tornado(N/2,N/2));// 중앙값(N/2)부터 시작
  }
  
  public static int tornado(int x, int y) {
    
    int outsand = 0;//밖으로 나간 모래의 양
    
    //현재위치
    int currentX = x;
    int currentY = y;
    
    while(true) {
      for(int d = 0; d < 4; d++) {//좌 하 우 상 순으로 이동
        for(int i = 0; i < moveCnt[d]; i++) {//각방향 이동할 칸수만큼
           //그림의 y에서 이동한 위치
           int nextX = currentX + dx[d];
           int nextY = currentY + dy[d];
           
           //y가 이동한위치가 범위 벗어날경우 밖으로 나간 모래의 양 return
           if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
             return outsand;
           }
           
           //y가 이동한 위치 모래 뿌리기
           int sandAmount = sand[nextX][nextY];//y에 있는 모래의 양 
           sand[nextX][nextY] = 0;//y에 있는 모든 모래 이동할 것 이므로 0으로 
           int totalSpread = 0;
           
           for(int j = 0; j < 9; j++) {//y를 기준 각 방향에서 해당 비율만큼 모래의 이동
             int sandX = nextX + sdx[d][j];
             int sandY = nextY + sdy[d][j];
             int spreadAmount = (sandAmount * sratio[j])/100;
             
             if(sandX < 0 || sandX >= N || sandY < 0 || sandY >= N) {//공간 벗어나면 밖으로 나간 모래의 양 누적
               outsand += spreadAmount;
             }else {//공간 안에 있으면 해당칸에 모래 누적
               sand[sandX][sandY] += spreadAmount;
             }
             
             totalSpread += spreadAmount;//y에 있던 모래중 주변으로 뿌려진 모래의 양
           }
           
           //a칸 변화
           int alphaX = nextX + dx[d];
           int alphaY = nextY + dy[d];
           int alphaSand = sandAmount - totalSpread; //a의 모래 = y에 있던 모래중 주변으로 뿌려진 모래를 제외한 모래
           
           if(alphaX < 0 || alphaX >= N || alphaY < 0 || alphaY >= N) {//공간 벗어나면 밖으로 나간 모래의 양 누적
             outsand += alphaSand;
           }else {//공간 안에 있으면 해당칸에 모래 누적
             sand[alphaX][alphaY] += alphaSand;
           }
           
           //이동위치를 현재위치로 업데이트
           currentX = nextX;
           currentY = nextY;
        }
      }
      
      //각 방향에서 이동할 칸의 수 업데이트
      for(int k = 0; k < 4; k++) {
        moveCnt[k] += 2;
      }
    }
  }
}
