class Solution {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean visited[][][][] = new boolean[11][11][11][11];
    public int solution(String dirs) {
        int answer = 0;
        int cx = 0;
        int cy = 0;
        int nx = 0;
        int ny = 0;
        
        for(int i = 0; i < dirs.length(); i++){
            char dir = dirs.charAt(i);
            switch(dir){
                case 'U':
                    nx = cx + dx[0];
                    ny = cy + dy[0];
                    break;
                case 'D':
                    nx = cx + dx[1];
                    ny = cy + dy[1];
                    break;
                case 'L':
                    nx = cx + dx[2];
                    ny = cy + dy[2];
                    break;
                case 'R':
                    nx = cx + dx[3];
                    ny = cy + dy[3];
                    break;
            }
            if(nx < -5 || nx > 5 || ny < -5 || ny > 5){
                nx = cx;
                ny = cy;
                continue;
            }
            if(!visited[nx + 5][ny + 5][cx + 5][cy + 5] || !visited[cx + 5][cy + 5][nx + 5][ny + 5]){
                visited[nx + 5][ny + 5][cx + 5][cy + 5] = true;
                visited[cx + 5][cy + 5][nx + 5][ny + 5] = true;
                answer++;
            }
            cx = nx;
            cy = ny;
        }
        return answer;
    }
}