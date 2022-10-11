import java.util.Arrays;

class Solution {
    public boolean solution(int[] arr) {
        boolean answer = true;
    	long cnt = 0;
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == i+1){
                cnt++;
            }else{
                answer = false;
                break;
            }
        }
        if(cnt == arr.length){
            answer = true;
        }
        return answer;
    }
}