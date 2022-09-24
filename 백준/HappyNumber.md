## Happy Number

> NO.14954

### 문제 

Consider the following function *f* defined for any natural number *n*:

> *f*(*n*) is the number obtained by summing up the squares of the digits of *n* in decimal (or base-ten).

If *n* = 19, for example, then *f*(19) = 82 because 12 + 92 = 82.

Repeatedly applying this function *f*, some natural numbers eventually become 1. Such numbers are called *happy* *numbers*. For example, 19 is a happy number, because repeatedly applying function *f* to 19 results in:

- *f*(19) = 12 + 92 = 82
- *f*(82) = 82 + 22 = 68
- *f*(68) = 62 + 82 = 100
- *f*(100) = 12 + 02 + 02 = 1

However, not all natural numbers are happy. You could try 5 and you will see that 5 is not a happy number. If *n* is not a happy number, it has been proved by mathematicians that repeatedly applying function *f* to *n* reaches the following cycle:

> 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4.

Write a program that decides if a given natural number *n* is a happy number or not.

### 입력

Your program is to read from standard input. The input consists of a single line that contains an integer, *n* (1 ≤ *n* ≤ 1,000,000,000)

### 출력

Your program is to write to standard output. Print exactly one line. If the given number *n* is a happy number, print out `HAPPY`; otherwise, print out `UNHAPPY`.

### 입출력 예 

![image](https://user-images.githubusercontent.com/103404127/192085708-5d555f64-cfa5-4711-b5bf-5125871bee03.png)

---

### 내 답과 풀이

- 입력받은 문장을 숫자로 바꾸고 10으로 나누면서 자릿수 제곱의 합을 구함.(반복)
- 거듭제곱 구하기 `Math.pow(밑, 지수)`
- 입력한 값이나 제곱의 합이 4가되면 무한반복되므로 무조건 `UNHAPPY`  출력하고 멈춤.
- 1이 될 경우 Happy Number 이므로 `HAPPY`출력

```java
import java.util.Scanner;

public class HappyNumber {//백준 제출시 Main으로 수정해서

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.next();
    int n = Integer.parseInt(str);
   
    while(n!=1) {
      n = solution(n);
      if(n == 4) {
        System.out.println("UNHAPPY");
        break;
      }
    }
    if(n == 1) {
      System.out.println("HAPPY");
    }
  }
  public static int solution(int n) {
    int result=0;
    while(n > 0){
      result += Math.pow((n % 10),2);
      n /= 10;
    }
    return result;
  }
}
```
