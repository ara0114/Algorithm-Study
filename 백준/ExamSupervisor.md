## 시험 감독

> NO.13458, 삼성 SW 역량 테스트 기출, 수학, 사칙연산



### 문제 

총 N개의 시험장이 있고, 각각의 시험장마다 응시자들이 있다. i번 시험장에 있는 응시자의 수는 Ai명이다.

감독관은 총감독관과 부감독관으로 두 종류가 있다. 총감독관은 한 시험장에서 감시할 수 있는 응시자의 수가 B명이고, 부감독관은 한 시험장에서 감시할 수 있는 응시자의 수가 C명이다.

각각의 시험장에 총감독관은 오직 1명만 있어야 하고, 부감독관은 여러 명 있어도 된다.

각 시험장마다 응시생들을 모두 감시해야 한다. 이때, 필요한 감독관 수의 최솟값을 구하는 프로그램을 작성하시오.

[문제 자세히 보기](https://www.acmicpc.net/problem/13458)

### 입력

첫째 줄에 시험장의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄에는 각 시험장에 있는 응시자의 수 Ai (1 ≤ Ai ≤ 1,000,000)가 주어진다.

셋째 줄에는 B와 C가 주어진다. (1 ≤ B, C ≤ 1,000,000)

### 출력

각 시험장마다 응시생을 모두 감독하기 위해 필요한 감독관의 최소 수를 출력한다.

### 입출력 예 

![image](https://user-images.githubusercontent.com/103404127/194708329-0c1ec719-05b7-486f-9f0f-b33d896bef4d.png)

---

### 내 답과 풀이

- 입력받을때마다 객체생성해줘서 답 제출시 런타임에러 발생했었음
- long타입으로 cnt선언해야함(결과값이 int의 범위 넘어갈 수 있으므로)

```java
import java.util.Scanner;

public class ExamSupervisor {
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);    
    int N = sc.nextInt();//시험장 갯수
    
    int[] tester = new int[N]; //시험장당 응시자수    
    for(int i = 0; i < N; i++) {
      tester[i] = sc.nextInt();
    }
    
    int B = sc.nextInt();//총감독관이 감독 가능한 인원
    int C = sc.nextInt();//부감독관이 감독 가능한 인원
   
    long cnt = N;//필요한 감독관의 수(시험장 갯수만큼 총감독관 필요하므로 시험장 갯수로 값 초기화)
    for (int i = 0; i < N; i++) {
      tester[i] -= B; //고사장 총감독관이 감독할 수 있는 인원 빼고
      if(tester[i] <= 0) {
        continue;
      }else {//응시자수 남아있다면
        cnt += tester[i] / C;//응시자수를 부감독관이 가능한 인원으로 나누어 부감독관의 수 구해서 더해주기
        if(tester[i] % C != 0) {//만약 나누어떨어지지 않으면 부감독 한명 더
          cnt++;
        }
      }     
    }   
    System.out.println(cnt);
  }
}
```
