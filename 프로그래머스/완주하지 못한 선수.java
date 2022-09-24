import java.util.Arrays;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        for(int i = 0; i < completion.length; i++){
            if(!participant[i].equals(completion[i])){
                answer = participant[i];
                break;
            }else if(i == completion.length-1){
                answer = participant[i+1];
            }
        }
        return answer;
    }
}