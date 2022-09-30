class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = { 0, 0 };
        int hit = 0;
        int zeroCnt = 0;
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) {
                zeroCnt++;
            }
            for (int j = 0; j < win_nums.length; j++) {
                if (lottos[i] == win_nums[j]) {
                    hit++;
                }
            }
        }
        int maxCnt = hit + zeroCnt;
        int minCnt = hit;

        int[] result = { maxCnt, minCnt };

        for (int i = 0; i < result.length; i++) {
            switch (result[i]) {
                case 6:
                    answer[i] = 1;
                    break;
                case 5:
                    answer[i] = 2;
                    break;
                case 4:
                    answer[i] = 3;
                    break;
                case 3:
                    answer[i] = 4;
                    break;
                case 2:
                    answer[i] = 5;
                    break;
                default:
                    answer[i] = 6;
                    break;
            }
        }
        return answer;
    }
}