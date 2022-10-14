## 예술성

> 삼성 SW 역량테스트 2022 상반기 오전 2번 문제



### 문제 

예술가 Sam은 그림에 대한 예술성을 평가하는 알고리즘을 만들어냈습니다. 그림을 편의상 n * n 크기의 격자로 생각하고, 각 칸의 색깔을 1이상 10이하의 숫자로 표현하여 이 알고리즘을 적용해보려 합니다.

먼저 그림의 동일한 숫자가 상하좌우로 인접해있는 경우 동일한 그룹이라 본다면, 총 4개의 그룹이 만들어지게 됩니다.

예술 점수는 모든 그룹 쌍의 조화로움의 합으로 정의됩니다. 그룹 a와 그룹 b의 조화로움은 `(그룹 a에 속한 칸의 수 + 그룹 b에 속한 칸의 수 ) x 그룹 a를 이루고 있는 숫자 값 x 그룹 b를 이루고 있는 숫자 값 x 그룹 a와 그룹 b가 서로 맞닿아 있는 변의 수`로 정의됩니다.

초기 예술 점수를 구한 뒤에는 그림에 대한 회전을 진행합니다.

회전은 정중을 기준으로 두 선을 그어 만들어지는 십자 모양과 그 외 부분으로 나뉘어 진행됩니다.

- 십자 모양의 경우 통째로 반시계 방향으로 90' 회전합니다.
- 십자 모양을 제외한 4개의 정사각형은 각각 개별적으로 시계 방향으로 90'씩 회전이 진행됩니다.

이제 예술 점수를 마찬가지 방법으로 구하면 1회전 이후 예술 점수가 됩니다.

n * n 크기의 그림 정보가 주어졌을 때, 초기 예술 점수, 1회전 이후 예술 점수, 2회전 이후 예술 점수, 그리고 3회전 이후 예술 점수의 합을 구하는 프로그램을 작성해보세요.

[문제 자세히 보기](https://www.codetree.ai/frequent-problems/artistry/description)

### 입력

첫 번째 줄에 n이 주어집니다. n은 반드시 홀수입니다.
이후 n개의 줄에 걸쳐 각 행에 칠해져 있는 색깔에 대한 정보인 숫자들이 공백을 사이에 두고 주어집니다.

### 출력

첫 번째 줄에 초기 예술 점수, 1회전 이후 예술 점수, 2회전 이후 예술 점수, 그리고 3회전 이후 예술 점수를 모두 합한 값을 출력합니다.

### 제한

- 3 ≤ n ≤ 29
- 1 ≤ 주어지는 숫자 ≤ 10

### 입출력 예 

- 문제링크참조

---

### 내 답과 풀이

- 공간 입력받고 초기예술점수 -> 회전-> 예술점수->회전->예술점수->회전->예술점수->회전
  - 예술점수
    - 그룹핑 : 상하좌우 인접=> 같은그룹=> dfs반복
    - 점수계산: 그룹의 갯수, 어떤 그룹인지, 각 그룹의 칸수 저장할 변수필요

  - 회전
    - 십자 모양은 반시계방향, 십자 모양을 기준으로 정사각형 4개는 시계방향

- 회전이 헷갈림..
- 그룹끼리 점수 계산하고 (G1,G2),(G2,G1)처럼 같은 예술점수 중복제거하기위해 2로 나눠줌
- 주석참고

```java
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

```

