import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        answer += dfs(p);
        return answer;
    }
    
    public static String dfs(String w){
        String u = "";
        String v = "";
        
        int open = 0;
        int close = 0;
        
        if(w.length() == 0)
            return "";
        for(int i = 0; i < w.length(); i++){
            if(w.charAt(i) == '(')
                open++;
            if(w.charAt(i) == ')')
                close++;
            
            u += w.charAt(i);
            if(open == close){
                v = w.substring(i+1);
                break;
            }
        }
        
        if(isRight(u)){
            return u += dfs(v);
        }else{
            String temp = "(";
            temp += dfs(v);
            temp += ")";
            
            u = u.substring(1, u.length()-1);
            
            for(int i = 0; i < u.length(); i++){
                if(u.charAt(i) == '(')
                    temp += ')';
                else
                    temp += '(';
            }
            
            return temp;
        }
    }
    
    public static boolean isRight(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push('(');                
            }else{
                if(stack.isEmpty() || stack.peek() ==')'){
                    return false;
                }else{
                    stack.pop();
                }
            }
        }
        return true;
    }
}