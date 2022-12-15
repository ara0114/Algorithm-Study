import java.util.Scanner;

public class 바닥공사 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();//가로의 길이
    int[] dp = new int[N+1];
    
    dp[1] = 1;
    dp[2] = 3;
    for(int i = 3; i < N+1; i++) {
      dp[i] = (dp[i-1] + 2 * dp[i-2]) % 796796;
    }
    
    System.out.println(dp[N]);

  }

}
