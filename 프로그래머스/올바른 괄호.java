import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack  = new Stack<>();
        int slen = s.length();
        if(s.charAt(0) == ')' || s.charAt(slen-1) == '(' || slen%2 == 1){
            answer = false;
            return answer;
        }
        for(int i = 0; i < slen; i++){
            if(s.charAt(i) == '('){
                stack.push(s.charAt(i));
            }else{
                if(stack.isEmpty()){
                    answer = false;
                    return answer;
                }else{
                    stack.pop();
                }
            }
        }
        if(!stack.isEmpty()){
            answer = false;
        }
        return answer;
    }
}