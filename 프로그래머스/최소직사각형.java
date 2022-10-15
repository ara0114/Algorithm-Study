class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int width = 0;
        int height = 0;
        //sizes[n][0] = w
        //sizes[n][1] = h
        
        for(int i = 0; i < sizes.length; i++){
            
            if(sizes[i][0] < sizes[i][1]){//가로 세로 길이 비교해서 큰값을 가로로, 작은값을 세로로 swap
                int temp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = temp;
            }
            
            if(width < sizes[i][0]){//가로길이 갱신
                width = sizes[i][0];
            }
            
            if(height < sizes[i][1]){//세로길이 갱신
                height = sizes[i][1];
            }
        }
        answer = width * height;
        return answer;
    }
}