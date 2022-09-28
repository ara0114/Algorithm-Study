import java.util.ArrayList;
class Solution {
    public long[] solution(int x, int n) {
        long[] answer = {};
        ArrayList <Long> arr = new ArrayList<>();
        int cnt = 0;
        long x1 = Long.valueOf(x);
        long interval = x1;
        while(cnt < n){
            arr.add(x1);
            x1 += interval;
            cnt++;
        }
        answer = new long[arr.size()];
        for(int i = 0; i < arr.size(); i++){
            answer[i] = arr.get(i).longValue();
        }
        System.out.println(arr);
        return answer;
    }
}