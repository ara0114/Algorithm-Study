class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int pcount = 0;
        int ycount = 0;
        char[] s1 = s.toCharArray();

        for (int i = 0; i < s1.length; i++) {
            if (s1[i] == 'p' || s1[i] == 'P') {
                pcount++;
            } else if (s1[i] == 'y' || s1[i] == 'Y') {
                ycount++;
            }
        }

        if (pcount == ycount) {
            answer = true;
        } else {
            answer = false;
        }

        return answer;
    }
}
