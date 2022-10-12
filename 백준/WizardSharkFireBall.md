## 마법사 상어와 파이어볼

> NO.20056, 삼성 SW 역량 테스트 기출, 구현, 시뮬레이션



### 문제 

[어른 상어](https://www.acmicpc.net/problem/19237)가 마법사가 되었고, 파이어볼을 배웠다.

마법사 상어가 크기가 N×N인 격자에 파이어볼 M개를 발사했다. 가장 처음에 파이어볼은 각자 위치에서 이동을 대기하고 있다. i번 파이어볼의 위치는 (ri, ci), 질량은 mi이고, 방향은 di, 속력은 si이다. 위치 (r, c)는 r행 c열을 의미한다.

격자의 행과 열은 1번부터 N번까지 번호가 매겨져 있고, 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.

파이어볼의 방향은 어떤 칸과 인접한 8개의 칸의 방향을 의미하며, 정수로는 다음과 같다.

![image](https://user-images.githubusercontent.com/103404127/195389933-508aca65-42b3-4104-adb2-200acde342a4.png)

마법사 상어가 모든 파이어볼에게 이동을 명령하면 다음이 일들이 일어난다.

1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
   - 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
   1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
   2. 파이어볼은 4개의 파이어볼로 나누어진다.
   3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
      1. 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
      2. 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
      3. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
   4. 질량이 0인 파이어볼은 소멸되어 없어진다.

마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 구해보자.

[문제 자세히 보기](https://www.acmicpc.net/problem/20056)

### 입력

첫째 줄에 N, M, K가 주어진다.

둘째 줄부터 M개의 줄에 파이어볼의 정보가 한 줄에 하나씩 주어진다. 파이어볼의 정보는 다섯 정수 ri, ci, mi, si, di로 이루어져 있다.

서로 다른 두 파이어볼의 위치가 같은 경우는 입력으로 주어지지 않는다.

### 출력

마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 출력한다.

### 제한

- 4 ≤ N ≤ 50
- 0 ≤ M ≤ N2
- 1 ≤ K ≤ 1,000
- 1 ≤ ri, ci ≤ N
- 1 ≤ mi ≤ 1,000
- 1 ≤ si ≤ 1,000
- 0 ≤ di ≤ 7

### 입출력 예 

![image](https://user-images.githubusercontent.com/103404127/195389531-69874d1a-54a0-4463-885b-a0198c7f650a.png)

---

### 내 답과 풀이

- 주어진 방향은

  ![image](https://user-images.githubusercontent.com/103404127/195390871-61cd65ac-e7c5-4faf-8ccf-6eb1095645cd.png)

  로 시계방향.

    파이어볼 이동방향(시계방향): ↑, ↗, →, ↘, ↓, ↙, ←, ↖
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

- 시작과 끝의 경계는 이어짐 -> 모듈러 연산 사용

- ArrayList로 구현하려니 시간초과가 났다. => 다른 사람 풀이 참고해서 Queue로..

- 처음에 접근이 잘되었다 싶었는데 계속 오답이 나와서 시간이 오래걸렸음
- 자꾸 /by zero라며 0으로 나눈다고 에러가 나는데 원인을 찾지 못하다가 전역변수로 선언해둔 N,M,K를 메인 안에서 다시 선언하면서 입력받은 것을 한참 뒤에 발견했다...

- 향상된 for문, foreach를 한번 더 봐둬야겠음

- 주석참고

```java
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
```

