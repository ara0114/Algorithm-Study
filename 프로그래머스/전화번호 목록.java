import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Map<String, String> pf = new HashMap<>();
        for(int i = 0; i < phone_book.length; i++){
            pf.put(phone_book[i],phone_book[i]);
        }
        
        for(int i = 0; i < phone_book.length; i++){
            for(int j = 1; j < phone_book[i].length(); j++){
                if(pf.containsKey(phone_book[i].substring(0,j))){
                    answer = false;
                    return answer;
                }
            }
        }    
        return answer;
    }
}