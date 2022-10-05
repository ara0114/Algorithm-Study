import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] snum = new String[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            snum[i]=String.valueOf(numbers[i]);
        }

        Arrays.sort(snum, (a,b)->(b+a).compareTo(a+b));
        
        if(snum[0].equals("0")){
            answer = "0";
        }else{
            for(int i = 0; i < snum.length; i++){
                answer += snum[i];
            }
        }
        return answer;
    }
}