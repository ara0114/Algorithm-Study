class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int sum = 0;
        int n = x;

        while (n > 0) {
            sum += n % 10; // 10으로 계속 나누면서 나머지를 더해줌
            n /= 10; // 몫을 n에 저장
        }

        if (x % sum == 0) {// 양의 정수 x를 합으로 나누어 체크
            answer = true;
        } else {
            answer = false;
        }
        return answer;
    }
}