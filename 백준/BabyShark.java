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
