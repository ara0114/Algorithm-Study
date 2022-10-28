class Solution {
    //area = brown+yellow
    //yellow = (width-2) * (height-2)
    //area = width * height (단, width >= height)
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int area = brown + yellow;
        int width = 0;
        int height = 0;
        
        for(int i = 1; i <= area; i++){
            height = i;
            if(area % i == 0){
                width = area/i;
            }
            if(width >= height){
                if((width-2)*(height-2) == yellow){
                    answer[0] = width;
                    answer[1] = height;
                }else{
                    continue;
                }
            }
        }
        return answer;
    }
}