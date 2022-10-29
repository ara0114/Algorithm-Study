import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int minidx = 0;
        
        for(int maxidx = people.length-1; minidx <= maxidx; maxidx--){
            if(people[maxidx] + people[minidx] <= limit)
                minidx++;
            answer++;
        }
        return answer;
    }
}