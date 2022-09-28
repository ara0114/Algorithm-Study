class Solution {
    public int solution(int num) {
        int answer = 0;
        long n = Long.valueOf(num);
        if (n == 1) {
            answer = 0;
        } else {
            while (n != 1) {
                if (n % 2 == 0) {
                    n = n / 2;
                } else {
                    n = n * 3 + 1;
                }
                answer++;
                if (answer == 500 && n != 1) {
                    answer = -1;
                    break;
                }
            }
        }
        return answer;
    }
}