## N과M(1)

> NO.15649

### 문제 

자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

- 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열

### 입력

첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

### 출력

한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

### 입출력 예 

![image](https://user-images.githubusercontent.com/103404127/194491158-f21db82b-5cec-4b9e-b649-5490dddf571c.png)

---

### 내 답과 풀이

- 재귀, 백트래킹중 DFS

```java
import java.util.*;

public class NandM_1 {

  static int arr[];// 탐색 값을 담을 배열
  static boolean visit[];// 방문했는지를 체크하는 배열

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    arr = new int[m];
    visit = new boolean[n + 1];// 노드 보통 1부터 시작함(인덱스0은비워두고시작하기위해)
    dfs(n, m, 0);
  }

  public static void dfs(int n, int m, int idx) {

    if (idx == m) {// 인덱스가 M과 같아지면 탐색값을 담았던 배열 출력
      for (idx = 0; idx < arr.length; idx++) {
        System.out.print(arr[idx] + " ");
      }

      System.out.println();

      return;
    }

    for (int i = 1; i <= n; i++) {
      if (!visit[i]) {// 해당 노드 방문하지 않았다면
        visit[i] = true;// 노드를 방문상태로 변경
        arr[idx] = i;// 해당인덱스값에 i값 저장
        dfs(n, m, idx + 1);// 다음 자식 노드 방문을 재귀적으로 방문
        visit[i] = false;// 자식노드 방문 끝나면 방문하지 않은 상태로 변경
      }
    }
  }
}
```
