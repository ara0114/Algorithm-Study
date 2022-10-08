## 어른 상어

> NO.19237, 삼성 SW 역량 테스트 기출, 구현, 시뮬레이션



### 문제 

[청소년 상어](https://www.acmicpc.net/problem/19236)는 더욱 자라 어른 상어가 되었다. 상어가 사는 공간에 더 이상 물고기는 오지 않고 다른 상어들만이 남아있다. 상어에는 1 이상 M 이하의 자연수 번호가 붙어 있고, 모든 번호는 서로 다르다. 상어들은 영역을 사수하기 위해 다른 상어들을 쫓아내려고 하는데, 1의 번호를 가진 어른 상어는 가장 강력해서 나머지 모두를 쫓아낼 수 있다.

N×N 크기의 격자 중 M개의 칸에 상어가 한 마리씩 들어 있다. 맨 처음에는 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다. 그 후 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동하고, 자신의 냄새를 그 칸에 뿌린다. 냄새는 상어가 k번 이동하고 나면 사라진다.

각 상어가 이동 방향을 결정할 때는, 먼저 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다. 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다. 이때 가능한 칸이 여러 개일 수 있는데, 그 경우에는 특정한 우선순위를 따른다. 우선순위는 상어마다 다를 수 있고, 같은 상어라도 현재 상어가 보고 있는 방향에 따라 또 다를 수 있다. 상어가 맨 처음에 보고 있는 방향은 입력으로 주어지고, 그 후에는 방금 이동한 방향이 보고 있는 방향이 된다.

모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아 있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로 쫓겨난다.

...

이 과정을 반복할 때, 1번 상어만 격자에 남게 되기까지 몇 초가 걸리는지를 구하는 프로그램을 작성하시오.

[문제 자세히 보기](https://www.acmicpc.net/problem/19237)

### 입력

첫 줄에는 N, M, k가 주어진다. (2 ≤ N ≤ 20, 2 ≤ M ≤ N2, 1 ≤ k ≤ 1,000)

그 다음 줄부터 N개의 줄에 걸쳐 격자의 모습이 주어진다. 0은 빈칸이고, 0이 아닌 수 x는 x번 상어가 들어있는 칸을 의미한다.

그 다음 줄에는 각 상어의 방향이 차례대로 주어진다. 1, 2, 3, 4는 각각 위, 아래, 왼쪽, 오른쪽을 의미한다.

그 다음 줄부터 각 상어의 방향 우선순위가 상어 당 4줄씩 차례대로 주어진다. 각 줄은 4개의 수로 이루어져 있다. 하나의 상어를 나타내는 네 줄 중 첫 번째 줄은 해당 상어가 위를 향할 때의 방향 우선순위, 두 번째 줄은 아래를 향할 때의 우선순위, 세 번째 줄은 왼쪽을 향할 때의 우선순위, 네 번째 줄은 오른쪽을 향할 때의 우선순위이다. 각 우선순위에는 1부터 4까지의 자연수가 한 번씩 나타난다. 가장 먼저 나오는 방향이 최우선이다. 예를 들어, 우선순위가 1 3 2 4라면, 방향의 순서는 위, 왼쪽, 아래, 오른쪽이다.

맨 처음에는 각 상어마다 인접한 빈 칸이 존재한다. 따라서 처음부터 이동을 못 하는 경우는 없다.

### 출력

1번 상어만 격자에 남게 되기까지 걸리는 시간을 출력한다. 단, 1,000초가 넘어도 다른 상어가 격자에 남아 있으면 -1을 출력한다.

### 입출력 예 

![image](https://user-images.githubusercontent.com/103404127/194700825-395b667b-b8c8-43c3-bfc9-4088eb803e3c.png)

- 문제에 나온 그림과 같다

![image](https://user-images.githubusercontent.com/103404127/194700881-e9f85a76-511c-4884-a661-026a07f231f6.png)

![image](https://user-images.githubusercontent.com/103404127/194700907-f17eb76a-b3f0-48ee-b412-fbab53a42f9f.png)

---

### 내 답과 풀이

- 다른 사람 풀이 보면서 이해하는 식으로 함.
- 주석참고

```java
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
    }
    
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
```

- 너무 오래걸리고(청소년상어보다는 짧게 걸림) 헷갈린다.. 다들 어떻게 하는거임
