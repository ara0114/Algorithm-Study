## p진법 to q진법

> COS Pro 1급 Java[5차] 문제6)

### 문제 설명

p 진법으로 표현한 수란, 각 자리를 0부터 p-1의 숫자로만 나타낸 수를 의미합니다. p 진법으로 표현한 자연수 두개를 더한 결과를 q 진법으로 표현하려 합니다.

예를 들어, 3진법 수 112001과 12010을 더한 결과를 8진법으로 나타내면 1005입니다.

solution 함수의 매개변수로 p 진법 자연수를 담은 문자열 s1, s2와 두 수를 나타내는 진법의 기수 p, 두 수의 덧셈 결과를 표현할 진법의 기수 q가 매개변수로 주어집니다. p진법으로 표현된 두 수를 더한 결과를 q 진법으로 나타낸 값을 return 하도록 solution 함수를 완성해주세요.

### 매개변수 설명

p 진법으로 자연수를 담은 문자열 s1, s2와 두 수를 표현한 진법의 기수 p, 두 수의 덧셈 결과를 표현할 진법의 기수 q가 solution 함수의 매개변수로 주어집니다.

- p와 q는 2 이상 10 이하인 자연수입니다.
- s1과 s2의 길이는 1 이상 9 이하입니다.
- s1과 s2의 원소는 '0', '1', '2', …, ‘p-1’로만 구성됩니다.
- s1이나 s2가 ‘0’인 경우는 주어지지 않습니다.

### return값 설명

두 수를 더한 결과를 q 진법으로 나타낸 값을 문자열로 return 하도록 solution 함수를 완성해주세요.

### 예제

| s1       | s2      | p    | q    | return |
| -------- | ------- | ---- | ---- | ------ |
| "112001" | "12010" | 3    | 8    | "1005" |

### 예제 설명

문제에 나온 예와 같습니다.

---

### 내 답과 풀이

- 각 수를 10진수로 만들고 더해서 q진법수로 만들어줌

```java
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
```

