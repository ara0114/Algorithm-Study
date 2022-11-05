import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> dpq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i < operations.length; i++){
            String[] temp = operations[i].split(" ");
            int num = Integer.parseInt(temp[1]);
            if(temp[0].equals("I")){
                pq.add(num);
                dpq.add(num);
            }else if(temp[0].equals("D")){
                if(!pq.isEmpty()){
                    if(num == 1){
                        int dmax = dpq.poll();
                        pq.remove(dmax);
                    }else if(num == -1){
                        int dmin = pq.poll();
                        dpq.remove(dmin);
                    }
                }
            }
        }
        if(pq.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
        }else if(pq.size() == 1){
            answer[0] = pq.peek();
            answer[1] = pq.peek();
        }else{
            answer[0] = dpq.peek();
            answer[1] = pq.peek();
        }
        return answer;
    }
}