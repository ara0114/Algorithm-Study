import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark{
  int x, y, dir;//상어의 x좌표, y 좌표, 방향

  public Shark(int x, int y, int dir) {
    this.x = x;
    this.y = y;
    this.dir = dir;
  }
}


public class AdultShark {
  
  static int N,M,k; //크기 N, 상어 수 M, 냄새유지시간 k
  static Shark[] shark;//각 상어 위치와 방향
  static int smellNum[][];//해당칸에 냄새뿌린 상어번호
  static int restTime[][];//해당칸의 냄새 유효시간
  static int priority[][][];//각 상어의 현재방향에서 우선순위
  //방향: 상하좌우
  static int[] dx = {0, -1, 1, 0, 0};
  static int[] dy = {0, 0, 0, -1, 1};
  
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    
    shark = new Shark[M+1];//번호대로 계산하기위해 1칸 더
    smellNum = new int[N+1][N+1];
    restTime = new int[N+1][N+1];
    priority = new int[M+1][5][4];
    
    for(int i = 1; i <= N; i++) {//공간정보입력받기
      st = new StringTokenizer(br.readLine()," ");
      for(int j = 1; j <= N; j++) {
        int n = Integer.parseInt(st.nextToken());
        if(n > 0) {
          shark[n] = new Shark(i,j,0);//해당 번호의 상어 위치 저장
          smellNum[i][j] = n;//해당 칸에 냄새뿌린 상어 번호 저장
          restTime[i][j] = k;//냄새 유효시간 k로 초기화        
        }
      }
    }
    
    st = new StringTokenizer(br.readLine()," ");//상어방향입력받기
    for(int i = 1; i <= M; i++) {
      shark[i].dir = Integer.parseInt(st.nextToken());
    }
    
    for(int i = 1; i <= M; i++) {//M번 상어까지
      for(int j = 1; j <= 4; j++) {//현재 j방향일 때의 우선순위 입력받기
        st = new StringTokenizer(br.readLine()," ");
        for(int k = 0; k < 4; k++) {
          priority[i][j][k] = Integer.parseInt(st.nextToken());
        }
      }
    }
    
    System.out.println(solution());
  }
  
  public static int solution() {
    int time = 0;
    
    while(true) {
      int sharkCnt = 0;//상어의 수
      for(int i = 1; i <= M; i++ ) {
        if(shark[i] != null) {
          sharkCnt++;
        }
      }
      
      if(sharkCnt == 1 && shark[1] != null) {//1번 상어만 살아남은경우 시간 리턴
        return time;
      }
      if(time >= 1000) {//시간이 1000초가 넘고 다른 상어남아있으면 -1 리턴
        return -1;
      }
      
      int[][] temp = new int[N+1][N+1];//현재 상어보다 이전의 상어들이 이동한 결과(몇번째 위치칸에 이전 상어번호)
      //0이라면 이전에 그 위치로 이동한 상어가 없다는 뜻, 0이 아니면 이동한 상어존재(충돌:이전상어번호 더 작으므로 현재상어가 쫓겨나게 됌)
      for(int i = 1; i <= M ; i++) {
        if(shark[i] != null) { //상어가 공간 안에 있을경우
          moveShark(temp, i);//상어이동
        }
      }
      //상어가 이동했으므로 모든위치의 냄새 유효시간 감소
      for(int i = 1; i <= N; i++) {
        for(int j = 1; j <= N; j++) {
          if(restTime[i][j] > 0) {//유효시간 있다면
            restTime[i][j]--;//유효시간 감소시키기
          }
          if(restTime[i][j] == 0) {//유효시간끝났다면
            smellNum[i][j] = 0;//해당위치 냄새 0으로 변경
          }
        }
      }
      //이동 후 냄새뿌린 상어번호 업데이트, 유효시간 초기화
      for(int i = 1; i <= N; i++) {
        for(int j = 1; j <= N; j++) {
          if(temp[i][j] > 0) {
            restTime[i][j] = k;
            smellNum[i][j] = temp[i][j];
          }
        }
      }
      
      time++;//시간증가
      
    }
  }
  
  public static void moveShark(int[][] temp, int num) {
    boolean flag = false;//냄새여부
    //다음좌표와 방향
    int next_x = 0;
    int next_y = 0;
    int dir = 0;
    
    for(int i = 0; i < 4; i++) {//높은 우선순위부터 차례로 탐색
      
      dir = priority[num][shark[num].dir][i];
      next_x = shark[num].x + dx[dir];
      next_y = shark[num].y + dy[dir];
      
      if((next_x >= 1 && next_y <= N) && (next_x <= N && next_y >= 1) && smellNum[next_x][next_y] == 0) {//공간 안에 존재하면서 냄새가 없다면
        flag = true;//냄새뿌리기
        break;
      }
    }//end for
    
    if(!flag) {//전부 냄새가 있다면
      for(int i = 0; i < 4; i++) {//높은 우선순위부터 차레로 탐색
        dir = priority[num][shark[num].dir][i];
        next_x = shark[num].x + dx[dir];
        next_y = shark[num].y + dy[dir];
        
        if((next_x >= 1 && next_y <= N) && (next_x <= N && next_y >= 1) && smellNum[next_x][next_y] == num) {//인접한 칸중 자기 냄새가 있는 칸 찾기
          break;
        }
      }
    }
    
    if(temp[next_x][next_y] == 0) {//이동할 위치에 이전에 이동한 상어 없다면
      temp[next_x][next_y] = num;//현재 상어번호로 업데이트
      //현재 상어의 위치, 방향 변경
      shark[num].x = next_x;
      shark[num].y = next_y;
      shark[num].dir = dir;
    }else {//이동할 위치에 이전에 이동한 상어가 있다면(충돌)
      shark[num] = null;//현재 상어 공간 밖으로 쫓겨남
    }
  }
}
