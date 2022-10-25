class Solution {    
    public int solution(int n) {
        int answer = 0;        
        int[] arr = new int[n+1];
        
        for(int i = 0; i <= n; i++){
            if(i == 0 || i == 1){
                arr[i] = i;
            }else{
                arr[i] = arr[i-1] % 1234567 + arr[i-2] % 1234567;
            }
        }
        
        answer = arr[n] % 1234567;
        
        return answer;
    }
    
}