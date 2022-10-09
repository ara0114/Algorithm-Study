import java.util.*;

class Solution {
    //공간크기
    static int n, m;
    //상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    //방문여부체크
    static boolean[][] visited;
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        
        answer = bfs(0, 0, maps);
        
        return answer;
    }
    
    public int bfs(int x, int y, int[][] maps){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 1));//큐에 삽입
        visited[x][y] = true;//방문처리
        
        while(!q.isEmpty()){//큐가 비어있지않다면
            Node node = q.poll();//큐에서 꺼냄
            if(node.x == n - 1 && node.y == m - 1){
                return node.cost; //만약 목적지 도착했으면 cost반환
            }
            for(int i = 0; i < 4; i++){//모든방향 탐색
                //다음 위치좌표
                int nextX = node.x + dx[i];
                int nextY = node.y + dy[i];
                
                if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m){//다음 위치가 공간안에 있을 때
                    if(maps[nextX][nextY] == 1 && !visited[nextX][nextY]){//벽이 없고 방문이 안되었다면
                        visited[nextX][nextY] = true;//방문처리
                        q.offer(new Node(nextX, nextY, node.cost +1));//큐에 삽입
                    }
                }
            }
        }
        
        return -1; // 탐색마쳤는데 값 리턴되지 않았으면 도착하지 못한 것이므로 -1 리턴
    }
    
    public class Node{
        int x; //x좌표
        int y; //y좌표
        int cost; //현재까지의 거리
        
        public Node(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}