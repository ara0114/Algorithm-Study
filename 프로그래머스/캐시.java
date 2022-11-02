import java.util.*;

class Solution {
    static final int cachehit = 1;
    static final int cachemiss = 5;
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if(cacheSize == 0)//캐시사이즈0일때 모든 경우 캐시미스이므로
            return cachemiss * cities.length;
        
        LinkedList<String> cache = new LinkedList<>();
        for(int i = 0; i < cities.length; i++){
            String s = cities[i].toUpperCase();
            if(cache.remove(s)){
                answer += cachehit;
                cache.add(s);
            }else{
                answer += cachemiss;
                if(cache.size() >= cacheSize){
                    cache.remove(0);
                }
                cache.add(s);
            }
        }
        return answer;
    }
}