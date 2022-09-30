class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int leftLoc = 10;
        int rightLoc = 12;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
                answer += "L";
                leftLoc = numbers[i];
            } else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
                answer += "R";
                rightLoc = numbers[i];
            } else {
                if (numbers[i] == 0) {
                    numbers[i] = 11;
                }
                int leftDis = (Math.abs(numbers[i] - leftLoc)) / 3 + (Math.abs(numbers[i] - leftLoc)) % 3;
                int rightDis = (Math.abs(numbers[i] - rightLoc)) / 3 + (Math.abs(numbers[i] - rightLoc)) % 3;
                if (leftDis == rightDis) {
                    if (hand.equals("left")) {
                        answer += "L";
                        leftLoc = numbers[i];
                    } else if (hand.equals("right")) {
                        answer += "R";
                        rightLoc = numbers[i];
                    }
                } else if (leftDis > rightDis) {
                    answer += "R";
                    rightLoc = numbers[i];
                } else if (leftDis < rightDis) {
                    answer += "L";
                    leftLoc = numbers[i];
                }
            }
        }
        return answer;
    }
}