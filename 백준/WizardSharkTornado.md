## 마법사 상어와 토네이도

> NO.20057, 삼성 SW 역량 테스트 기출, 구현, 시뮬레이션



### 문제 

[마법사 상어](https://www.acmicpc.net/problem/20056)가 토네이도를 배웠고, 오늘은 토네이도를 크기가 N×N인 격자로 나누어진 모래밭에서 연습하려고 한다. 위치 (r, c)는 격자의 r행 c열을 의미하고, A[r][c]는 (r, c)에 있는 모래의 양을 의미한다.

토네이도를 시전하면 격자의 가운데 칸부터 토네이도의 이동이 시작된다. 토네이도는 한 번에 한 칸 이동한다. 다음은 N = 7인 경우 토네이도의 이동이다.

토네이도가 한 칸 이동할 때마다 모래는 다음과 같이 일정한 비율로 흩날리게 된다.

토네이도가 x에서 y로 이동하면, y의 모든 모래가 비율과 α가 적혀있는 칸으로 이동한다. 비율이 적혀있는 칸으로 이동하는 모래의 양은 y에 있는 모래의 해당 비율만큼이고, 계산에서 소수점 아래는 버린다. α로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남은 모래의 양과 같다. 모래가 이미 있는 칸으로 모래가 이동하면, 모래의 양은 더해진다. 위의 그림은 토네이도가 왼쪽으로 이동할 때이고, 다른 방향으로 이동하는 경우는 위의 그림을 해당 방향으로 회전하면 된다.

토네이도는 (1, 1)까지 이동한 뒤 소멸한다. 모래가 격자의 밖으로 이동할 수도 있다. 토네이도가 소멸되었을 때, 격자의 밖으로 나간 모래의 양을 구해보자.

[문제 자세히 보기](https://www.acmicpc.net/problem/20057)

### 입력

첫째 줄에 격자의 크기 N이 주어진다. 둘째 줄부터 N개의 줄에는 격자의 각 칸에 있는 모래가 주어진다. r번째 줄에서 c번째 주어지는 정수는 A\[r][c] 이다.

### 출력

격자의 밖으로 나간 모래의 양을 출력한다.

### 제한

- 3 ≤ N ≤ 499
- N은 홀수
- 0 ≤ A[r][c] ≤ 1,000
- 가운데 칸에 있는 모래의 양은 0

### 입출력 예 

![image](https://user-images.githubusercontent.com/103404127/194745239-8526b9ac-6002-4ca4-bc8f-e8a716a7d3ac.png)

![image](https://user-images.githubusercontent.com/103404127/194745247-5e84f62c-ac38-481c-95f6-76f6d429a383.png)

---

### 내 답과 풀이

- 다른 사람의 풀이보며 이해하기
- 각 방향에서 모래 퍼지는게 이해가 안가서 한참 걸렸다.

![image](https://user-images.githubusercontent.com/103404127/194745395-4f3f3a7f-8322-4157-ab40-5b4541164c21.png)

![image](https://user-images.githubusercontent.com/103404127/194745415-e34b3eb8-b9ed-49fb-8ef0-d351b94d92e5.png)

- 각 방향에서 이동하는 칸의 수는 한 사이클이 끝날때마다 2씩 증가한다

![image](https://user-images.githubusercontent.com/103404127/194745954-aad29e61-3cf0-4207-a090-3c3fa4131849.png) 

- 반복사이클
  - 현재위치에서 다음위치로 이동하고
  - 다음위치에 있는 모래를 뿌리고
  - 이동한 위치를 현재 위치로 업데이트
- 주석참고

```java
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
  
  //각 방향에서 이동하는 칸의 수 : 한 사이클 끝나면 각각 2씩 증가
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
        for(int i = 0; i < moveCnt[d]; i++) {//각 방향 이동할 칸 수만큼
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
```
