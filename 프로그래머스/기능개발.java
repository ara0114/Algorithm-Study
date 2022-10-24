import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> q = new LinkedList<>();
        int[] workday = new int[progresses.length];

        for(int i = 0; i <  workday.length; i++){
            if((100-progresses[i]) % speeds[i] == 0){
                workday[i] = (100-progresses[i]) / speeds[i];
            }else{
                workday[i] = ((100-progresses[i]) / speeds[i]) + 1;                
            }
            q.offer(workday[i]);
        }
        
        List<Integer> list = new ArrayList<Integer>();
        int count = 1;
        int cur = q.poll();
        
        while(!q.isEmpty()){
            if(cur>=q.peek()){
                q.poll();
                count++;
            }else{
                list.add(count);
                count = 1;
                cur = q.poll();
            }
        }
        list.add(count);
        
        answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}