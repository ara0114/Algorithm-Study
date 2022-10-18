import java.util.*;

class Main {
    public int solution(int[] enemies, int[] armies) {
      int answer = 0;
			for(int i = 0; i < armies.length; i++ ){
				int cnt = 0;
				for(int j = 0; j < enemies.length; j++){
					if(armies[i]>=enemies[j]){
						cnt++;
					}
					answer = Math.max(answer,cnt);
				}
			}
      return answer;
    }

    public static void main(String[] args) {
    	Main sol = new Main();
    	int[] enemies1 = {1, 4, 3};
    	int[] armies1 = {1, 3};
    	int ret1 = sol.solution(enemies1, armies1);

    	System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

    	int[] enemies2 = {1, 1, 1};
    	int[] armies2 = {1, 2, 3, 4};
    	int ret2 = sol.solution(enemies2, armies2);

    	System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }
}
