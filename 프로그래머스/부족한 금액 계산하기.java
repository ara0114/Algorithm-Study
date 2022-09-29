class Solution {
    public long solution(int price, int money, int count) {
        long answer = -1;
        long sum = 0;
        long result = 0;
        for(int i = 1; i <= count; i++){
            sum += Long.valueOf(price * i);
        }
        result = sum - Long.valueOf(money);
        if(result > 0){
            answer = result;
        }else if(result <= 0){
            answer = 0;
        }
        return answer;
    }
}