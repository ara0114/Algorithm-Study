import java.util.ArrayList;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] count = new int[3]; //정답갯수체크
        int[] p1answer={1,2,3,4,5};//수포자1
        int[] p2answer={2,1,2,3,2,4,2,5};//수포자2
        int[] p3answer={3,3,1,1,2,2,4,4,5,5};//수포자3
        
        for(int i = 0; i < answers.length; i++){
            if(answers[i] == p1answer[i%5]){//수포자1 맞힌갯수
                count[0]++;
            }
            if(answers[i] == p2answer[i%8]){//수포자2 맞힌갯수
                count[1]++;
            }
            if(answers[i] == p3answer[i%10]){//수포자3 맞힌갯수
                count[2]++;
            }
        }
        
        int max = count[0];//가장 많이 맞춘 갯수(가장높은점수)
        for(int i = 0; i < 3; i++){
            if(count[i] > max){
                max = count[i];
            }
        }
        
        ArrayList<Integer> chkPerson = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            if(max == count[i]){//가장많이맞힌갯수와 같은 카운트갯수면 누구인지 체크(리스트에넣기)
                chkPerson.add(i+1);
            }
        }
        answer = new int[chkPerson.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = chkPerson.get(i);
        }
        
        return answer;
    }
}