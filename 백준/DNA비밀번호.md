## DNA비밀번호

> NO.12891, 문자열, 투포인터, 슬라이딩윈도우



### 문제  

평소에 문자열을 가지고 노는 것을 좋아하는 민호는 DNA 문자열을 알게 되었다. DNA 문자열은 모든 문자열에 등장하는 문자가 {‘A’, ‘C’, ‘G’, ‘T’} 인 문자열을 말한다. 예를 들어 “ACKA”는 DNA 문자열이 아니지만 “ACCA”는 DNA 문자열이다. 이런 신비한 문자열에 완전히 매료된 민호는 임의의 DNA 문자열을 만들고 만들어진 DNA 문자열의 부분문자열을 비밀번호로 사용하기로 마음먹었다.

하지만 민호는 이러한 방법에는 큰 문제가 있다는 것을 발견했다. 임의의 DNA 문자열의 부분문자열을 뽑았을 때 “AAAA”와 같이 보안에 취약한 비밀번호가 만들어 질 수 있기 때문이다. 그래서 민호는 부분문자열에서 등장하는 문자의 개수가 특정 개수 이상이여야 비밀번호로 사용할 수 있다는 규칙을 만들었다.

임의의 DNA문자열이 “AAACCTGCCAA” 이고 민호가 뽑을 부분문자열의 길이를 4라고 하자. 그리고 부분문자열에 ‘A’ 는 1개 이상, ‘C’는 1개 이상, ‘G’는 1개 이상, ‘T’는 0개 이상이 등장해야 비밀번호로 사용할 수 있다고 하자. 이때 “ACCT” 는 ‘G’ 가 1 개 이상 등장해야 한다는 조건을 만족하지 못해 비밀번호로 사용하지 못한다. 하지만 “GCCA” 은 모든 조건을 만족하기 때문에 비밀번호로 사용할 수 있다.

민호가 만든 임의의 DNA 문자열과 비밀번호로 사용할 부분분자열의 길이, 그리고 {‘A’, ‘C’, ‘G’, ‘T’} 가 각각 몇번 이상 등장해야 비밀번호로 사용할 수 있는지 순서대로 주어졌을 때 민호가 만들 수 있는 비밀번호의 종류의 수를 구하는 프로그램을 작성하자. 단 부분문자열이 등장하는 위치가 다르다면 부분문자열이 같다고 하더라도 다른 문자열로 취급한다.

[문제 자세히 보기](https://www.acmicpc.net/problem/12891)

### 입력

첫 번째 줄에 민호가 임의로 만든 DNA 문자열 길이 |S|와 비밀번호로 사용할 부분문자열의 길이 |P| 가 주어진다. (1 ≤ |P| ≤ |S| ≤ 1,000,000)

두번 째 줄에는 민호가 임의로 만든 DNA 문자열이 주어진다.

세번 째 줄에는 부분문자열에 포함되어야 할 {‘A’, ‘C’, ‘G’, ‘T’} 의 최소 개수가 공백을 구분으로 주어진다. 각각의 수는 |S| 보다 작거나 같은 음이 아닌 정수이며 총 합은 |S| 보다 작거나 같음이 보장된다.

### 출력

첫 번째 줄에 민호가 만들 수 있는 비밀번호의 종류의 수를 출력해라.

### 입출력 예 

#### 예제 입력 1

```
9 8
CCTGGATTG
2 0 1 1
```

#### 예제 출력 1

```
0
```

#### 예제 입력 2

```
4 2
GATA
1 0 0 1
```

#### 예제 출력 2

```
2
```

### 내 답과 풀이

- 슬라이딩 윈도우를 이용해 이동하면서 기존탐색 결과에 새로 들어온 문자열, 제거되는 문자열만 보고 업데이트

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_12891 {
  static int S;
  static int P;
  static int[] checkArr = new int[4];// 비밀번호 체크배열
  static int[] checkCur = new int[4];// 현재 문자열 상태 체크배열
  static char[] DNA;// 입력받은 DNA문자열을 저장할 배열
  static int checkOk = 0;// 체크해야할 문자중 몇개가 만족했는가 => checkOk==4이면 answer++
  static int answer = 0;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");
    
    S = Integer.parseInt(st.nextToken()); //민호가 임의로 만든 문자열길이 S
    P = Integer.parseInt(st.nextToken()); //비밀번호로 사용할 부분 문자열길이 P
    DNA = new char[S];
    
    DNA = br.readLine().toCharArray();//입력받은 문자열 캐릭터배열로 
    
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < 4; i++) {
      checkArr[i] = Integer.parseInt(st.nextToken());//문자당 만족해야하는 갯수
      if(checkArr[i] == 0)//없어도 상관없는 문자이므로 항상 만족
        checkOk++;
    }
    
    for(int i = 0; i < P; i++) {//부분 문자열 처음 받을 때 세팅
      addInRange(DNA[i]);
    }
    
    if(checkOk == 4)
      answer++;
    
    //슬라이딩윈도우
    for(int right = P; right < S; right++) {
      int left = right-P;
      addInRange(DNA[right]);
      removeInRange(DNA[left]);
      if(checkOk == 4)
        answer++;
    }
    
    System.out.println(answer);
  }

  private static void addInRange(char c) {// 범위에 들어온 문자 처리
    switch(c) {
    case 'A':
      checkCur[0]++;
      if(checkCur[0] == checkArr[0])
        checkOk++;
      break;
    case 'C':
      checkCur[1]++;
      if(checkCur[1] == checkArr[1])
        checkOk++;
      break;
    case 'G':
      checkCur[2]++;
      if(checkCur[2] == checkArr[2])
        checkOk++;
      break;
    case 'T':
      checkCur[3]++;
      if(checkCur[3] == checkArr[3])
        checkOk++;
      break;
    }
  }
  private static void removeInRange(char c) {// 범위에서 제거한 문자 처리
    switch(c) {
    case 'A':
      if(checkCur[0] == checkArr[0])
        checkOk--;
      checkCur[0]--;
      break;
    case 'C':
      if(checkCur[1] == checkArr[1])
        checkOk--;
      checkCur[1]--;
      break;
    case 'G':
      if(checkCur[2] == checkArr[2])
        checkOk--;
      checkCur[2]--;
      break;
    case 'T':
      if(checkCur[3] == checkArr[3])
        checkOk--;
      checkCur[3]--;
      break;
    }
  }
}

```

