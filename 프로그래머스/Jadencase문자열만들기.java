class Solution {
    public String solution(String s) {
        String answer = "";
        String[] temp = s.toLowerCase().split(" ");
        
        for(int i = 0; i < temp.length; i++){
            if(temp[i].length() == 0)
                answer += " ";
            else{
                answer += Character.toUpperCase(temp[i].charAt(0));
                answer += temp[i].substring(1,temp[i].length());
                if(i != temp.length - 1)
                    answer += " ";
            }

        }
        if((s.substring(s.length()-1, s.length())).equals(" "))
            answer += " ";
        return answer;
    }
}