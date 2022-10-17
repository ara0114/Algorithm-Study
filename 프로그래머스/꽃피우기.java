import java.util.*;

class Solution {
    public boolean check(int[][] garden){
        for(int i = 0; i < garden.length; i++){
            for(int j = 0; j < garden.length; j++){
                if(garden[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    public int solution(int[][] garden) {
        int answer = 0;
        
        int []dx = {-1,0,1,0};
        int []dy = {0,1,0,-1};
        
        int glen = garden.length;
        int cnt = 0;
        
        while(true){
            int[][] copy = new int[glen][glen];

            if(check(garden)) break;
            
            for(int i = 0; i < glen; i++){
                for(int j = 0; j < glen; j++){
                    copy[i][j] = garden[i][j];
                }
            }
            
            for(int i = 0; i < glen; i++){
                for(int j = 0; j < glen; j++){
                    if(garden[i][j] == 1){
                        for(int k = 0;k < 4; k++){
                            int nextx = i + dx[k];
                            int nexty = j + dy[k];
                            if(nextx >= 0 && nextx < glen && nexty >= 0 && nexty < glen){
                                copy[nextx][nexty] = 1;
                            }
                        }
                        cnt = 1;
                    }
                }
            }
            if(cnt == 1){
                answer++;
            }
            cnt = 0;
            for(int i = 0; i < glen; i++){
                for(int j = 0; j < glen; j++){
                    garden[i][j] = copy[i][j];
                }
            }
        }
        
 
        return answer;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] garden1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int ret1 = sol.solution(garden1);
        
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");
        
        int[][] garden2 = {{1, 1}, {1, 1}};
        int ret2 = sol.solution(garden2);
        
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
        
    }    
}