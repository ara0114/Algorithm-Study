import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(1, s.length() - 1);
        
        String[] str = s.split("},");
        
        Arrays.sort(str, (a, b) -> a.length() - b.length());
        
        for(int i = 0; i < str.length; i++){
            str[i] = str[i].replace("{","");
            str[i] = str[i].replace("}","");
        }
        
        Set<Integer> set = new LinkedHashSet<>();
        for(int i = 0; i<str.length; i++){
            String[] sdata = str[i].split(",");
            for(int j = 0; j < sdata.length; j++)
                set.add(Integer.parseInt(sdata[j]));
        }
        
        answer = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        for(int i = 0; i<set.size(); i++){
            answer[i] = iterator.next();
        }
        return answer;
    }
}