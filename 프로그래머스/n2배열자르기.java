class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right-left) + 1];
        
        int index = 0;
        
        for(long i = left; i <= right; i++){
            answer[index++] = (int)Math.max(i / n, i % n) + 1;
        }
        return answer;
    }
}