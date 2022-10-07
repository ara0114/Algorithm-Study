import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Fish{//물고기
  int x, y, num, dir;//x좌표, y좌표, 번호, 방향
  boolean live = true;//생사여부(죽으면false,살아있으면true)
  
  public Fish(int x, int y, int num, int dir, boolean live) {
    this.x = x;
    this.y = y;
    this.num = num;
    this.dir = dir;
    this.live = live;
  }
  
}

public class TeenagerShark {
  public static int[][] map = new int[4][4];//4x4 공간
  //방향 { ↑, ↖, ←, ↙, ↓, ↘, →, ↗ }  x축 y축과 다름 반대로 생각
  public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  public static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
  
  public static Fish[] fish = new Fish[17];//물고기배열
  
  public static int max_eat = 0;//최대값
  
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    for(int i = 0; i < 4; i++) {
      //Scanner sc = new Scanner(System.in);
      st = new StringTokenizer(br.readLine()," ");
      for(int j = 0; j < 4; j++) {
        //int num = sc.nextInt();
        //int dir = sc.nextInt() - 1 ;
        int num = Integer.parseInt(st.nextToken());//물고기번호입력
        int dir = Integer.parseInt(st.nextToken())-1;//방향입력    
        fish[num] = new Fish(i, j, num, dir, true);//물고기 번호마다 물고기의 위치와 방향, 생사여부 저장
        map[i][j] = num;//맵에 물고기 번호 저장
      }
    }
    //(0,0)위치에 있는 물고기 상어에게 먹힘
    int shark_x = 0, shark_y = 0;//상어의 위치
    int shark_dir = fish[map[0][0]].dir;//상어의 방향
    int eat = map[0][0];//먹은 물고기 번호들의 합
    fish[map[0][0]].live = false;//물고기 죽음
    map[0][0] = -1; //상어가 있는 위치 표현
    
    dfs(shark_x, shark_y, shark_dir, eat);

    System.out.println(max_eat);
  }
  
  public static void dfs(int shark_x, int shark_y, int shark_dir, int eat) {
    max_eat = Math.max(max_eat, eat); //이전에 먹은 물고기번호의 합(eat)과 비교하여 최대값 max_eat에 저장
    
    //map과 fish배열 복사(재귀로 인해 이전 상태로 되돌려 놓는 과정이 필요하기때문에)
    int [][] copyMap = new int[4][4];
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 4; j++) {
        copyMap[i][j] = map[i][j];
      }
    }
    Fish[] copyFish = new Fish[17];
    for(int i = 1; i <= 16; i++) {
      copyFish[i] = new Fish(fish[i].x, fish[i].y, fish[i].num, fish[i].dir, fish[i].live);
    }
    
    //물고기 이동
    moveFish();
    
    //상어 이동
    for(int i = 1; i < 4; i++) {//1칸~3칸까지 이동가능
      int next_x = shark_x + dx[shark_dir] * i;
      int next_y = shark_y + dy[shark_dir] * i;
      
      if(next_x >= 0 && next_x < 4 && next_y >= 0 && next_y < 4 && map[next_x][next_y] != 0) {//새 위치가 공간내부이고 물고기 있다면
        int eatFishNum = map[next_x][next_y];
        int next_dir = fish[eatFishNum].dir;
        map[shark_x][shark_y] = 0;//상어 지나간자리 빈칸으로
        map[next_x][next_y] = -1;//상어있는위치(-1)로 갱신
        fish[eatFishNum].live = false;//먹은 물고기 상태 죽음으로 갱신
        
        dfs(next_x, next_y, next_dir, eat + eatFishNum);//재귀 호출
        
        //물고기상태와 상어위치 원래대로 초기화
        fish[eatFishNum].live = true;
        map[shark_x][shark_y] = -1;
        map[next_x][next_y] = eatFishNum;
      }
    }
    
    //map과 fish배열 되돌리기
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 4; j++) {
        map[i][j] = copyMap[i][j];
      }
    }
    for(int i = 1; i <= 16; i++) {
      fish[i] = new Fish(copyFish[i].x, copyFish[i].y, copyFish[i].num, copyFish[i].dir, copyFish[i].live);
    }
  }
  
  public static void moveFish() {//물고기 이동 함수
    for(int i = 1; i < 17; i++) {
      if(!fish[i].live) {//물고기 죽었으면 패스
        continue;
      }
      
      int dirChk = 0; //8방향체크
      int dir = fish[i].dir;//i번 물고기의 방향
      int next_x = 0, next_y = 0;//이동할 다음  x, y좌표
      
      while(dirChk < 8) {//이동가능한 위치 찾을때까지 45도씩 반시계방향으로 바꾸며 반복
        dir %= 8;//8방향 넘기지 않도록 나머지연산
        fish[i].dir = dir;//바뀐 방향 적용
        //좌표이동
        next_x = fish[i].x + dx[dir];
        next_y = fish[i].y + dy[dir];
        
        if(next_x >= 0 && next_x < 4 && next_y >= 0 && next_y < 4 && map[next_x][next_y] != -1) {//새 위치가 공간내부이고 상어가 없다면
          if(map[next_x][next_y] == 0) {//새 위치에 물고기 없을때(빈칸일때)
            map[fish[i].x][fish[i].y] = 0;//기존 위치 물고기 없음(빈칸)으로 갱신
            fish[i].x = next_x;
            fish[i].y = next_y;
            map[next_x][next_y] = i;
          }else {//새 위치에 물고기 있을때
            //해당 물고기 위치 변경
            int changeNum = fish[map[next_x][next_y]].num;
            fish[changeNum].x = fish[i].x;
            fish[changeNum].y = fish[i].y;
            map[fish[changeNum].x][fish[changeNum].y] = changeNum;            
            
            //기존 물고기 새 위치로 위치 변경
            fish[i].x = next_x;
            fish[i].y = next_y;
            map[next_x][next_y] = i;
          }
          break;
        }else {//공간 벗어나거나 상어가 있다면 회전시키기
          dir++;
          dirChk++;
        }
      }
    }
  }
}
