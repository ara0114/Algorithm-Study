import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WizardSharkFireBall {
  
  static class Fireball{
    int r;  //x좌표
    int c;  //y좌표
    int m;  //질량
    int s;  //속도
    int d;  //방향

    public Fireball(int r, int c, int m, int s, int d) {
      this.r = r;
      this.c = c;
      this.m = m;
      this.s = s;
      this.d = d;
    }

  }
  static int N, M, K; // 격자크기, 파이어볼갯수, 상어가 명령한 이동횟수
  //파이어볼 이동방향(시계방향): ↑, ↗, →, ↘, ↓, ↙, ←, ↖
  static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
  static ArrayList<Fireball> ball;//파이어볼
  static Queue<Fireball>[][] map; //파이어볼맵
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    
    ball = new ArrayList<>();

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine()," ");
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      int m = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      ball.add(new Fireball(r, c, m, s, d));
    }
    
    map = new Queue[N][N];
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        map[i][j] = new LinkedList<Fireball>();
      }
    }
    while(K-- > 0) {
      move();
      sum();
    }
    int answer = 0;
    for(Fireball f : ball) {
      answer += f.m;
    }
    System.out.println(answer);
  }
  
  
  static void move() {
    for(Fireball f : ball) {
      int dir = f.d;
      int speed = f.s % N;//속력은 1000, 맵크기 N은 50이 최대이므로 모듈러 연산사용
      //모든파이어볼 자신의 방향d로 속력s칸 이동
      f.r = (f.r + (dx[dir] * speed) + N) % N; //계산한 값이 음수일 수 있기 때문에 N을 더하고 N으로 모듈러연산
      f.c = (f.c + (dy[dir] * speed) + N) % N;

      map[f.r][f.c].add(f);
    }
  }
  
  static void sum() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (map[i][j].size() >= 2) {//이동후 파이어볼 2개이상이면
          
          int sum_m = 0;
          int sum_s = 0;
          int cnt = map[i][j].size();// 합친갯수
          boolean odd = true;
          boolean even = true;

          while (!map[i][j].isEmpty()) {//같은칸에있는거 합치기
            Fireball f = map[i][j].poll();
            sum_m += f.m;
            sum_s += f.s;
            if (f.d % 2 == 0) {//파이어볼방향짝수인지
              odd = false;
            } else {
              even = false;
            }
            ball.remove(f);
          }


          int cur_m = sum_m / 5;//질량계산
          if (cur_m == 0) {//질량0이면소멸
            continue;
          }
          int cur_s = sum_s / cnt;//속력계산

          for (int k = 0; k < 4; k++) {
            if (odd || even) {//파이어볼방향 모두 홀수 또는 짝수이면  방향은 0,2,4,6
              ball.add(new Fireball(i, j, cur_m, cur_s, k * 2));
            } else {//아니면 방향 1,3,5,7
              ball.add(new Fireball(i, j, cur_m, cur_s, k * 2 + 1));
            }
          }
        } else {//같은칸에 2개이상존재하지않으면 제거
          map[i][j].clear();
        }
      }
    }
  }
}

