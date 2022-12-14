// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Main {
    public int solution(String s1, String s2) {
        // 여기에 코드를 작성해주세요.
        int answer = 0;
				int len = Math.min(s1.length(), s2.length());
			int idx = 0;
				for(int i = 0; i < len; i++){
					String subs1 = s1.substring(s1.length()-1 - i, s1.length() -1);
					String subs2 = s2.substring(0, i);
					if(subs1.equals(subs2)){
						idx = i;
					}
				}
			answer = s1.concat(s2.substring(idx, s2.length()-1)).length();
        return answer;
    }
    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        Main sol = new Main();
        String s1 = new String("ababc");
        String s2 = new String("abcdab");
        int ret = sol.solution(s1, s2);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}