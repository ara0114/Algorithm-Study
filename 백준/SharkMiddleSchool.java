import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SharkMiddleSchool {
  static class Block implements Comparable<Block>{//Comparable을 이용해 Block객체 정렬
    int x, y;//기준블록좌표
    int totalCnt;//총블록갯수
    int rainbowCnt;//무지개블록갯수
    public Block(int x, int y, int totalCnt, int rainbowCnt) {
      this.x = x;
      this.y = y;
      this.totalCnt = totalCnt;
      this.rainbowCnt = rainbowCnt;
    }
    @Override
    public int compareTo(Block o) {//비교함수
      if(this.totalCnt == o.totalCnt) {
        if(this.rainbowCnt == o.rainbowCnt) {
          if(this.x == o.x) {
            return o.y - this.y;
          }
          return o.x - this.x;
        }
        return o.rainbowCnt - this.rainbowCnt;
      }
      return o.totalCnt - this.totalCnt;
    }
  }
  
  static int N;//공간 크기 N*N
  static int M;//일반블록 색상 갯수(1~M)
  //검정블록은 -1, 무지개블록은 0으로 표현함
  static int BLANK = -2;//블록값 -1 ~ 5 까지이므로 그 외의 수로 설정
  
  static int[][] map;//블록게입맵
  static boolean[][] visited;//방문여부 저장
  
  //상하좌우
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  
  static ArrayList<Block> blockGroup;//블록그룹저장할리스트
  static int answer = 0;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    
    map = new int[N][N];
    
    
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine()," ");
      for(int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    while(true) {//블록 그룹찾기
      visited = new boolean[N][N];//무지개블록때문에 방문초기화해줘야..
      blockGroup = new ArrayList<>();
      for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
          if(map[i][j] > 0 && !visited[i][j]) {//일반블록이 있고 방문하지 않았다면
            findBlockGroup(i,j,true);
          }
        }
      }
      if(blockGroup.isEmpty()) {
        break;
      }
      
      Collections.sort(blockGroup);
      //블록 찾고 없애기
      visited = new boolean[N][N];
      findBlockGroup(blockGroup.get(0).x, blockGroup.get(0).y, false);
      removeBlock();//블록제거
      
      gravity();//중력작용
      rotate();//반시계회전
      gravity();//중력작용
      
      blockGroup.clear();
    }
    System.out.println(answer);
  }
  
  static void findBlockGroup(int x, int y, boolean flag) {//bfs로 블록그룹찾기
    Queue<int[]> q = new LinkedList<>();
    int color = map[x][y];
    visited[x][y] = true;
    q.offer(new int[]{x,y});
    int total = 1;
    int rainbow = 0;
    
    while(!q.isEmpty()) {
      int[] cur = q.poll();
      
      for(int d = 0; d < 4; d++) {
        int nextX = cur[0] + dx[d];
        int nextY = cur[1] + dy[d];
        
        if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY]) {//공간 밖이거나 방문했으면
          continue;
        }
        if(map[nextX][nextY] == color || map[nextX][nextY] == 0) {//색깔같거나 무지개블록이면
          if(map[nextX][nextY] == 0) {
            rainbow += 1;
          }
          total += 1;
          visited[nextX][nextY] = true;
          q.offer(new int[] {nextX, nextY});
        }
      }
    }
    
    if(total >= 2) {//블록갯수 2개이상일때만 블록그룹으로
      blockGroup.add(new Block(x, y, total, rainbow));
    }
    if(flag) {
      for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
          if(map[i][j] == 0) {//무지개블록일경우 공통으로 사용될수있으므로 초기화
            visited[i][j] = false;
          }
        }
      }
    }
  }
  
  static void removeBlock() {//블록제거
    int cnt = 0;
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        if(visited[i][j]==true) {
          cnt++;
          map[i][j] = BLANK;
        }
      }
    }
    answer += (int)Math.pow(cnt, 2);//점수획득
  }
  
  static void gravity() {//중력작용
    for(int j = 0; j < N; j++) {
      for(int i = N - 1; i >= 0; i--) {
        if(map[i][j] == BLANK || map[i][j] == -1) continue;
        int destination = i + 1;
        
        while(true) {
          if(destination == N) break;//바닥이면 멈춤
          if(map[destination][j] == BLANK) {//빈칸이면
            destination += 1;
          }else {
            break;
          }
        }
        if(destination == i+1) continue;
        map[destination-1][j] = map[i][j];
        map[i][j] = BLANK;
      }
    }
  }
  
  static void rotate() {//반시계회전
    int[][] temp = new int[N][N];
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        temp[N-j-1][i] = map[i][j];
      }
    }
    for(int i = 0; i < N; i++) {//배열복사
      System.arraycopy(temp[i], 0, map[i], 0, N);
    }
  }
}
