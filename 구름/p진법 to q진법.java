import java.util.*;

class Main {	
    public String solution(String s1, String s2, int p, int q) {
      String answer = "";
			int num1 = 0;
			int num2 = 0;
			int k = 0;
			for(int i = s1.length()-1; i >= 0; i--){
				num1 += Integer.parseInt(s1.substring(i,i+1)) * Math.pow(p,k);
				k++;
			}
			k=0;
			for(int i = s2.length()-1; i >= 0; i--){
				num2 += Integer.parseInt(s2.substring(i,i+1)) * Math.pow(p,k);
				k++;
			}
			int sum = num1+num2;
			while(sum > 0){
				answer = String.valueOf(sum % q) + answer;
				sum /= q;
			}
        return answer;
    }
    public static void main(String[] args) {
    	Main sol = new Main();
    	String s1 = new String("112001");
        String s2 = new String("12010");
        int p = 3;
        int q = 8;
    	String ret = sol.solution(s1, s2, p, q);
    	
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 \"" + ret + "\" 입니다.");
   }
}    