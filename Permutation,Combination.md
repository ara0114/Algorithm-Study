# 순열과 조합

## 팩토리얼(Factorial)

- 서로 다른 n개를 나열하는 경우의 수 `n!`
- n! = n(n-1)(n-2)(n-3)...1

## 순열(Permutation)

- n개의 숫자에서 r개를 뽑아 **순서를 고려**해 나열할 경우의 수

- 순서가 다르면 다른 경우의 수로 인정

![image](https://user-images.githubusercontent.com/103404127/194805397-1b3b873f-a8a6-495e-a28b-fdc727527a9f.png)

- n개의 숫자중 n개를 뽑는 경우(nPn)는 `n!` 와 같음

```java
import java.util.Scanner;

public class Permutation {
  static int N,K;//N개중 K를 뽑을 경우의 수
  static int inputArr[];//입력받을 N크기의 배열
  static int outputArr[];//결과를 출력할 거임
  static boolean visited[];
  static long cnt = 0;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    K = sc.nextInt();
    
    inputArr = new int[N];
    visited = new boolean[N];
    outputArr = new int[N];
    for(int i = 0; i < N; i++) {
      inputArr[i] = sc.nextInt();
    }
    
    permu(inputArr, outputArr, visited, 0, N, K);
    System.out.println("경우의 수 : " + cnt);
  }
    static void permu(int[] input, int[] output, boolean visited[], int depth, int n, int r) {
    if(depth == r) {
      for(int i = 0; i < r; i++) {
        System.out.print(output[i] + " ");
      }
      cnt++;
      System.out.println();
      return;
    }
    for(int i = 0; i < n; i++) {
      if(visited[i] != true) {
        visited[i] = true;
        output[depth] = input[i];
        permu(input, output, visited, depth+1, n, r);
        output[depth] = 0;
        visited[i] = false;
      }
    }
  }
}
```



## 중복 순열(Permutation with repetition)

- 중복 가능한 n개 중에서 r개를 선택하는 경우의 수(**순서 상관 있음**)

![image](https://user-images.githubusercontent.com/103404127/194809673-aacc4d1e-50dd-4ab4-9ca0-64c1418532f4.png)

- n\*n\*n..

```java
import java.util.Scanner;

public class Permutation {
  static int N, K;// N개중 K를 뽑을 경우의 수
  static int inputArr[];// 입력받을 N크기의 배열
  static int outputArr[];// 결과를 출력할 거임
  static long cnt = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    K = sc.nextInt();

    inputArr = new int[N];
    outputArr = new int[N];
    for (int i = 0; i < N; i++) {
      inputArr[i] = sc.nextInt();
    }

    repermu(inputArr, outputArr, 0, N, K);
    System.out.println("경우의 수 : " + cnt);
  }
  static void repermu(int[] input, int[] output, int depth, int n, int r) {
    if (depth == r) {
      for (int i = 0; i < r; i++) {
        System.out.print(output[i] + " ");
      }
      cnt++;
      System.out.println();
      return;
    }
    for(int i = 0; i < n; i++) {
      output[depth] = input[i];
      repermu(input, output, depth+1, n, r);
      output[depth] = 0;
    }
  }
}
```



## 조합(Combination)

- n개의 숫자에서 r개를 뽑는 경우의 수(**순서 상관 없음**)

![image](https://user-images.githubusercontent.com/103404127/194804509-a310577e-08a8-4923-a472-7c32910aa37a.png)

- 조합의 성질

![image](https://user-images.githubusercontent.com/103404127/194804571-91fbeeab-b58d-4ab8-949a-2429a38bff41.png)

![image](https://user-images.githubusercontent.com/103404127/194804625-72faea97-75ce-4e8f-9a25-fb7bdb474f6c.png)

- 조합의 성질에 따른 dp배열 초기화

```
DP배열 dp[N+1][N+1]


dp배열 초기화

dp[i][1] = i 	// i개에서 1개를 선택하는 경우의 수는 i개

dp[i][0] = 1	//i개에서 1개도 선택하지 않는 경우의 수는 1개

dp[i][i] = 1	//i개에서 i개를 선택하는 경우의 수는 1개



i를 N만큼, j를 i만큼 반복

dp[i][j] = dp[i-1][j] + dp[i-1][j-1]
```

- 백트래킹을 이용한 조합

```java
import java.util.Scanner;

public class Combination {
  static int N,K;
  static int[] inputArr;
  static boolean[] visited;
  static long cnt = 0;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    K = sc.nextInt();
    
    inputArr = new int[N];
    visited = new boolean[N];    
    
    for(int i = 0; i < N; i++) {
      inputArr[i] = sc.nextInt();
    }
    combi(inputArr, visited, 0, N, K);
    System.out.println("경우의 수 : " + cnt);
  }
  static void combi(int[] input, boolean[] visited, int start, int n, int r) {
    if(r == 0) {
      for(int i = 0; i < n; i++) {
        if(visited[i]) {
          System.out.print(input[i]+" ");
        }
      }
      cnt++;
      System.out.println();
      return;
    }
    
    for(int i = start; i < n; i++) {
      visited[i] = true;
      combi(input, visited, i+1, n, r-1);
      visited[i] = false;
    }
  }
}
```

## 중복 조합(Combination with repetition)

- 중복 가능한 n개 중에서 r개를 선택하는 경우의 수(**순서 상관 없음**)

![image](https://user-images.githubusercontent.com/103404127/194820091-9c3cdfc5-afd7-4ebc-af62-3ce9cbb0d8e6.png)

```java
package day03;

import java.util.Scanner;

public class Combination {
  static int N,K;
  static int[] inputArr;
  static int[] outputArr;
  static long cnt = 0;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    K = sc.nextInt();
    
    inputArr = new int[N];
    outputArr = new int[N];
    for(int i = 0; i < N; i++) {
      inputArr[i] = sc.nextInt();
    }

    recombi(inputArr, outputArr, 0, 0, N, K);
    System.out.println("경우의 수 : " + cnt);
  }

  static void recombi(int[] input, int[] output, int start, int depth, int n, int r) {
    if(depth == r) {
      for(int i = 0; i < r; i++) {
          System.out.print(output[i]+" ");
      }
      cnt++;
      System.out.println();
      return;
    }
    
    for(int i = start; i < n; i++) {
      output[depth] = input[i];
      recombi(input, output, i, depth+1, n, r);
    }
  }
}
```

