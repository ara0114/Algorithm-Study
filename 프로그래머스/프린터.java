import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int n: priorities){
            queue.offer(n);
        }
        
        while(!queue.isEmpty()){
            for(int i = 0; i < priorities.length; i++){
                if(queue.peek() == priorities[i]){
                    answer++;
                    queue.poll();
                    if(i == location){
                        return answer;
                    }

                }
            }
        }
        return answer;
    }
}