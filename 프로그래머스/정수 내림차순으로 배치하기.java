import java.util.Arrays; 
class Solution {
    public long solution(long n) {
        long answer = 0;        
        String str = Long.toString(n);  //입력받은 n을 문자열로 변환
        int[] descArr = Arrays.stream(str.split("")).mapToInt(Integer::parseInt).toArray();
        //문자열을 쪼개어 배열에 넣기     
        Arrays.sort(descArr);//정렬(오름차순)
        str = "";
        for(int i = descArr.length - 1; i >= 0; i--){//오름차순으로 정렬된 배열을 거꾸로 문자열에 넣기
            str += descArr[i];
        }
        answer = Long.parseLong(str); //문자열을 숫자로 변환
        return answer;
    }
}