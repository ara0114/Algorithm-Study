import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < words.length; i++){
            String cur = words[i];
            
            if(i>0){
                String prev = words[i-1];
                
                char last = prev.charAt(prev.length()-1);
                char first = cur.charAt(0);
                
                if(map.containsKey(cur) || last != first){
                    answer[0] = i % n + 1;
                    answer[1] = i / n + 1;
                    break;
                }
            }
            map.put(cur,1);
        }
        
        return answer;
    }
}