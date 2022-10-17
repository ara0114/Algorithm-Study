import java.util.*;

class Solution {
    public int solution(int K, String[] words) {
        int answer = 1;
        int len = 0;
        for(int i = 0; i < words.length; i++){
            len += words[i].length() + 1;
            if(len > K + 1){
                answer++;
                len = words[i].length() + 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int K = 10;
        String[] words = {new String("nice"), new String("happy"), new String("hello"), new String("world"), new String("hi")};
        int ret = sol.solution(K, words);

        System.out.println("solution 메소의 반환 값은 " + ret + " 입니다.");
    }
}