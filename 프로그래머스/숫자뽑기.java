import java.util.*;

class Solution {
    public int solution(int[] arr, int K) {

        int answer = 10000;
        Arrays.sort(arr);
        
        for(int i = 0; i <= arr.length-K; i++){
            answer = Math.min(answer, arr[i+K-1]-arr[i]);
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {9, 11, 9, 6, 4, 19};
        int K = 4;
        int ret = sol.solution(arr, K);

        System.out.println("solution 메소드의 반환 값은 " + ret + "입니다.");
    }
}