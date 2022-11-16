import java.util.*;
import java.util.stream.*;
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int[] arr = Stream.of(number.split("")).mapToInt(Integer::parseInt).toArray();
        int len = number.length() - k;
        
        for(int i = 0; i< arr.length; i++){
            pq.add(arr[i]);
        }
        
        while(len-- > 0){
            answer += Integer.toString(pq.poll());
        }
        
        return answer;
    }
}