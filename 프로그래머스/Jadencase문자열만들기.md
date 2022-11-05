## Jadencase문자열만들기

> 연습문제

### 문제 설명

JadenCase란 모든 단어의 첫 문자가 대문자이고, 그 외의 알파벳은 소문자인 문자열입니다. 단, 첫 문자가 알파벳이 아닐 때에는 이어지는 알파벳은 소문자로 쓰면 됩니다. (첫 번째 입출력 예 참고)
문자열 s가 주어졌을 때, s를 JadenCase로 바꾼 문자열을 리턴하는 함수, solution을 완성해주세요.

### 제한사항

- s는 길이 1 이상 200 이하인 문자열입니다.
- s는 알파벳과 숫자, 공백문자(" ")로 이루어져 있습니다.
  - 숫자는 단어의 첫 문자로만 나옵니다.
  - 숫자로만 이루어진 단어는 없습니다.
  - 공백문자가 연속해서 나올 수 있습니다.

### 입출력 예

| s                       |         return          |
| ----------------------- | :---------------------: |
| "3people unFollowed me" | "3people Unfollowed Me" |
| "for the last week"     |   "For The Last Week"   |

### 입출력 예 설명

입출력 예 설명#1
예제와 같습니다

---

### 내 답과 풀이

- 문자열의 길이가 공백일 때 or 문자열 마지막이 공백일때  공백 추가 해줘야함.


```java
class Solution {
    public String solution(String s) {
        String answer = "";
        String[] temp = s.toLowerCase().split(" ");//문자열을 전부 소문자로 바꾸고 공백기준으로 쪼개넣기
        
        for(int i = 0; i < temp.length; i++){
            if(temp[i].length() == 0)
                answer += " ";
            else{
                answer += temp[i].substring(0,1).toUpperCase();
                //answer += Character.toUpperCase(temp[i].charAt(0)); 이거로해도통과함
                answer += temp[i].substring(1,temp[i].length());
                if(i != temp.length - 1)
                    answer += " ";
            }

        }
        if((s.substring(s.length()-1, s.length())).equals(" "))
            answer += " ";
        return answer;
    }
}
```

### 오답

- 코드 실행은 통과하지만 일부 테스트 케이스에서 런타임에러발생

```java
class Solution {
    public String solution(String s) {
        String answer = "";
        String[] temp = s.toLowerCase().split(" ");
        
        for(int i = 0; i < temp.length; i++){
            answer += Character.toUpperCase(temp[i].charAt(0));
            answer += temp[i].substring(1,temp[i].length());
            if(i != temp.length - 1)
                answer += " ";
        }
        return answer;
    }
}
```





