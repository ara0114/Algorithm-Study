import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        char[] arr = new char[number.length()-k];
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < number.length(); i++){
            char c = number.charAt(i);
            while(!stack.isEmpty() && stack.peek() < c && k-- > 0){
                stack.pop();
            }
            stack.push(c);
        }
        
        while(stack.size() > arr.length){
            stack.pop();
        }
        for(int i = arr.length - 1; i >= 0; i--){
            arr[i] = stack.get(i);
        }
        answer = String.valueOf(arr);
        return answer;
    }
}