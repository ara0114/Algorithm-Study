import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for(int i = 0; i < s.length(); i++){
            s = s.substring(1,s.length()) + s.substring(0,1);
            if(check(s))
                answer++;
        }
        return answer;
    }
    public static boolean check(String str){
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()) {
                    return false;
                }else{
                    char p = stack.pop();
                    if(c == ')' && p == '(')
                        continue;
                    else if(c == ']' && p == '[')
                        continue;
                    else if(c == '}' && p == '{')
                        continue;
                    else
                        return false;
                }
            }
        }
        
        if(!stack.isEmpty())
            return false;
        else
            return true;
    }
}