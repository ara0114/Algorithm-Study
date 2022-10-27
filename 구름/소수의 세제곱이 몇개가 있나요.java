// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Main {
		public int getPrime(int num){
			int x = (int)Math.sqrt(num);
			return x;
		}
    public int solution(int a, int b) {
        // 여기에 코드를 작성해주세요.
        int answer = 0;
				int MaxPrime = getPrime(b);
				ArrayList<Integer> primelist = new ArrayList<>();
				for(int i = 2; i<=MaxPrime; i++){
					if(MaxPrime % i == 0){
						continue;
					}else{
						primelist.add(i);
					}
				}
			int scount = 0;
			int ccount = 0;
			int squareNum = 0;
			int cubeNum = 0;
			
			for(int i = 0; i < primelist.size(); i++){
				int pnum = primelist.get(i);
				squareNum = (int)Math.pow((double)pnum,2);
				if(a < squareNum && squareNum < b)
					scount++;
				cubeNum = (int)Math.pow((double)pnum,3);
				if(a < cubeNum && cubeNum < b)
					ccount++;
			}
 			answer = scount + ccount;
			return answer;
    }
   // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args){
        Main sol = new Main();
        int a = 6;
        int b = 30;
        int ret = sol.solution(a, b);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}