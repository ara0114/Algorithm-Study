import java.util.Scanner;

public class HappyNumber {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.next();
    int n = Integer.parseInt(str);

    while (n != 1) {
      n = solution(n);
      if (n == 4) {
        System.out.println("UNHAPPY");
        break;
      }
    }
    if (n == 1) {
      System.out.println("HAPPY");
    }
  }

  public static int solution(int n) {
    int result = 0;
    while (n > 0) {
      result += Math.pow((n % 10), 2);
      n /= 10;
    }
    return result;
  }
}