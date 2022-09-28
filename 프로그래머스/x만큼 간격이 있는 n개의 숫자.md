## x만큼 간격이 있는 n개의 숫자

> 연습문제

### 문제 설명

함수 solution은 정수 x와 자연수 n을 입력 받아, x부터 시작해 x씩 증가하는 숫자를 n개 지니는 리스트를 리턴해야 합니다. 다음 제한 조건을 보고, 조건을 만족하는 함수, solution을 완성해주세요.

### 제한사항

- x는 -10000000 이상, 10000000 이하인 정수입니다.
- n은 1000 이하인 자연수입니다.

### 입출력 예

| x    | n    | answer       |
| ---- | ---- | ------------ |
| 2    | 5    | [2,4,6,8,10] |
| 4    | 3    | [4,8,12]     |
| -4   | 2    | [-4, -8]     |

---

### 내 답과 풀이

- ArrayList를 이용해서 x부터 x씩 증가하는 숫자 n개의 리스트배열을 만들어줌
- int, Integer일때 테스트케이스를 통과하지 못해 long타입으로 변환해줌.


```java
import java.util.ArrayList;
class Solution {
    public long[] solution(int x, int n) {
        long[] answer = {};
        ArrayList <Long> arr = new ArrayList<>();
        int cnt = 0;
        long x1 = Long.valueOf(x);
        long interval = x1;
        while(cnt < n){
            arr.add(x1);
            x1 += interval;
            cnt++;
        }
        answer = new long[arr.size()];
        for(int i = 0; i < arr.size(); i++){
            answer[i] = arr.get(i).longValue();
        }
        System.out.println(arr);
        return answer;
    }
}
```

---

### 오답 및 해결

* x만큼 간격이 있는 n개의 숫자배열 arr는 구성하였으나 answer로 넣어주질 못함.


```java
import java.util.ArrayList;
class Solution {
    public long[] solution(int x, int n) {
        long[] answer = {};
        ArrayList <Integer> arr = new ArrayList<>();
        int cnt = 0;
        int interval = x;
        while(cnt < n){
            arr.add(x);
            x += interval;
            cnt++;
        }
        //System.out.println(arr);
        for(int i = 0; i < arr.size(); i++){
            answer[i] = arr[i];
        }

        return answer;
    }
}
```

- 컬렉션 프레임워크의 값을 가져오는 get과 long타입으로 변환해주고 answer의 배열크기 지정해주어 해결 
- 코드실행은 맞았지만 테스트13,14를 통과하지 못함

```java
import java.util.ArrayList;
class Solution {
    public long[] solution(int x, int n) {
        long[] answer = {};
        ArrayList <Integer> arr = new ArrayList<>();
        int cnt = 0;
        int interval = x;
        while(cnt < n){
            arr.add(x);
            x += interval;
            cnt++;
        }
        answer = new long[arr.size()];
        //System.out.println(arr);
        for(int i = 0; i < arr.size(); i++){
            answer[i] = arr.get(i).longValue();
        }
        System.out.println(arr);
        return answer;
    }
}
```

