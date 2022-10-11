class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        if(s.length() < 2){
            answer = s.length();
            return answer;
        }
        for(int i = s.length(); i >= 0; i--){
            for(int j = 0; j+i < s.length(); j++){
                int left = j;
                int right = j + i;
                if(isPalindrome(s,left,right)){
                    answer = right-left+1;
                    return answer;
                }
            }
        }
        return answer;
    }
    public boolean isPalindrome(String s, int start, int end){
        while(start < end){
            if(s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}