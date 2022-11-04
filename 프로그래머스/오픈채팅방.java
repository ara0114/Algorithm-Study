import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        
        Map<String, String> map = new HashMap<>();
        
        ArrayList<String> strList = new ArrayList<>();
        
        for(int i = 0; i < record.length; i++){
            String[] info = record[i].split(" ");
            
            if(info[0].equals("Enter")){
                map.put(info[1],info[2]);
               strList.add(info[1]+"님이 들어왔습니다.");
            }else if(info[0].equals("Leave")){
               strList.add(info[1]+"님이 나갔습니다.");
            }else if(info[0].equals("Change")){
               map.put(info[1],info[2]);
            }
        }
        answer = new String[strList.size()];
        for(int i = 0; i < answer.length; i++){
            String userinfo = strList.get(i);
            String uid = userinfo.substring(0, userinfo.indexOf("님"));
            answer[i] = userinfo.replace(uid, map.get(uid));
        }
        return answer;
    }
}