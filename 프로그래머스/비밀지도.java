class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            answer[i] = Integer.toBinaryString(arr1[i] | arr2[i]);

            while (answer[i].length() < n) {
                answer[i] = " " + answer[i];
            }

            answer[i] = answer[i].replace("0", " ");
            answer[i] = answer[i].replace("1", "#");

            // System.out.println(answer[i]);
        }

        return answer;
    }
}
