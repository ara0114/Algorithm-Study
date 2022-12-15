import java.util.Scanner;

public class 개미전사 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();//식량창고의 갯수
    int[] arr = new int[N];//각 식량창고에 저장된 식량의 갯수
    
    for(int i = 0; i < N; i++) {
      arr[i] = sc.nextInt();
    }

    int[] dp = new int[N];
    dp[0] = arr[0];
    dp[1] = Math.max(arr[0], arr[1]);
    for(int i = 2; i < N; i++) {
      dp[i] = Math.max(dp[i-1], dp[i-2] + arr[i]);
    }
    
    System.out.println(dp[N-1]);
  }

}
