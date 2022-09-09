## 문자열 내 p와 y의 개수

> 연습문제

### 문제 설명

대문자와 소문자가 섞여있는 문자열 s가 주어집니다. s에 'p'의 개수와 'y'의 개수를 비교해 같으면 True, 다르면 False를 return 하는 solution를 완성하세요. 'p', 'y' 모두 하나도 없는 경우는 항상 True를 리턴합니다. 단, 개수를 비교할 때 대문자와 소문자는 구별하지 않습니다.

예를 들어 s가 "pPoooyY"면 true를 return하고 "Pyy"라면 false를 return합니다.

### 제한사항

- - 문자열 s의 길이 : 50 이하의 자연수
  - 문자열 s는 알파벳으로만 이루어져 있습니다.

### 입출력 예

| s         | answer |
| --------- | ------ |
| "pPoooyY" | true   |
| "Pyy"     | false  |

### 입출력 예 설명

입출력 예 #1
'p'의 개수 2개, 'y'의 개수 2개로 같으므로 true를 return 합니다.

입출력 예 #2
'p'의 개수 1개, 'y'의 개수 2개로 다르므로 false를 return 합니다.

---

### 내 답과 풀이

- 대문자/소문자 구분이 없으므로 모두 소문자로 변환한 후, p와 y의 갯수를 카운트 하여 비교


```python
def solution(s):
    answer = True
    s1 = s.lower();
    pcount = 0
    ycount = 0

    for i in s1:
        if i == 'p':
            pcount += 1
        elif i == 'y':
            ycount += 1
            
    if pcount == ycount:
        answer = True
    else:
        answer = False
    
    return answer
```

- 문자열을 char배열로 바꾸어 대문자소문자 구분없이 일치하면 카운트

```java
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int pcount = 0;
        int ycount = 0;
        char[] s1 = s.toCharArray();
        
        for(int i = 0; i < s1.length; i++){
            if(s1[i] == 'p' || s1[i] == 'P'){
                pcount++;
            } 
            else if(s1[i] == 'y' || s1[i] == 'Y'){
                ycount++;
            }
        }
        
        if(pcount == ycount){
            answer = true;
        }else{
            answer = false;
        }
        
        return answer;
    }
}
```

---

### 오답

* 증감 연산자 `++` 은 invalid syntax (python)

```python
def solution(s):
    answer = True
    s1 = s.lower();
    pcount = 0
    ycount = 0

    for i in s1:
        if i == 'p':
            pcount ++
        elif i == 'y':
            ycount ++
            
    if pcount == ycount:
        answer = True
    else:
        answer = False
    
    return answer
```



