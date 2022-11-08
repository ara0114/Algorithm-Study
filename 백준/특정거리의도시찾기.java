import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Findcity {
  static int N;
  static int M;
  static int K;
  static int X;
  
  static ArrayList<Integer>[] list;//그래프 데이터 인접리스트
  static int[] visited;//방문거리
  static ArrayList<Integer> answer;//정답 리스트
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");
    
    N = Integer.parseInt(st.nextToken());//도시 갯수
    M = Integer.parseInt(st.nextToken());//도로 갯수
    K = Integer.parseInt(st.nextToken());//거리정보
    X = Integer.parseInt(st.nextToken());//출발도시번호
    
    list = new ArrayList[N+1];
    for(int i = 1; i <= N; i++) {
      list[i] = new ArrayList<>();//그래프 인접리스트 초기화
    }
    
    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine()," ");
      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());
      list[A].add(B);//그래프 인접리스트 데이터 저장 
    }
    
    visited = new int[N+1];
    for(int i = 0; i <= N; i++) {
      visited[i] = -1;//방문거리 초기화
    }
    
    bfs(X);
    
    answer = new ArrayList<>();
    for(int i = 0; i <= N; i++) {
      if(visited[i] == K) {
        answer.add(i);//방문거리K인 노드번호 리스트 추가
      }
    }
    if(answer.isEmpty()) {
      System.out.println("-1");//K인도시 존재하지않으면 -1
    }else {
      Collections.sort(answer);//정답리스트 정렬
      for(int i: answer) {
        System.out.println(i);//노드번호출력하기
      }
    }
    
  }
  static void bfs(int node) {
    Queue<Integer> q = new LinkedList<>();
    q.add(node);
    visited[node]++;
    while(!q.isEmpty()) {
      int nowNode = q.poll();
      for(int i : list[nowNode]) {
        if(visited[i] == -1) {//현재노드에서 연결된 노드중 방문하지 않은 노드라면, 방문거리 넣고 큐에 데이터넣기
          visited[i] = visited[nowNode] + 1;
          q.add(i);
        }
      }
    }
  }

}
