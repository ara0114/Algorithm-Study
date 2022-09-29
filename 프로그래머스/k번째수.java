import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};
        answer = new int[commands.length];
        //ArrayList<Integer> retarr = new ArrayList<>();
        for(int i = 0; i < commands.length; i++){
            ArrayList<Integer> retarr = new ArrayList<>();
            for(int j = commands[i][0] - 1; j < commands[i][1]; j++){
                retarr.add(array[j]);
            }
            Collections.sort(retarr);
            //System.out.println(retarr);
            answer[i] = retarr.get(commands[i][2]-1);
        }
        return answer;
    }
}