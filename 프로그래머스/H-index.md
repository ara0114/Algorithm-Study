## H-index

> 정렬

### 문제 설명

H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 위키백과[1](https://school.programmers.co.kr/learn/courses/30/lessons/42747#fn1)에 따르면, H-Index는 다음과 같이 구합니다.

어떤 과학자가 발표한 논문 `n`편 중, `h`번 이상 인용된 논문이 `h`편 이상이고 나머지 논문이 h번 이하 인용되었다면 `h`의 최댓값이 이 과학자의 H-Index입니다.

어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.

### 제한사항

- 과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
- 논문별 인용 횟수는 0회 이상 10,000회 이하입니다.

### 입출력 예

| citations       | return |
| --------------- | ------ |
| [3, 0, 6, 1, 5] | 3      |

### 입출력 예 설명

이 과학자가 발표한 논문의 수는 5편이고, 그중 3편의 논문은 3회 이상 인용되었습니다. 그리고 나머지 2편의 논문은 3회 이하 인용되었기 때문에 이 과학자의 H-Index는 3입니다.

---

### 내 답과 풀이

- h번 이상 인용된 논문이 h편 이상인 h의 최댓값
  - 논문 인용 수 정렬
  - 갈수록 인용된 논문 편 수가 줄어드므로 가장 처음 성립하는게 최댓값


```java
import java.util.Arrays;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        for(int i = 0; i < citations.length; i++){
            int h = citations.length - i; // 해당논문과 인용횟수가 같거나 큰 논문 편 수
            
            if(citations[i] >= h){ // 해당논문 인용횟수(citiations[i])와 값 비교
                answer = h;
                break;
            }
        }
        
        return answer;
    }
}
```

```python
def solution(citations):
    answer = 0
    citations.sort()
    
    for i in range(0, len(citations)):
        h = len(citations) - i
        if citations[i] >= h:
            answer = h
            break
    return answer
```



