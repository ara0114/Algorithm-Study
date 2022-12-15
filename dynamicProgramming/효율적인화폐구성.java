import java.util.Scanner;

public class 효율적인화폐구성 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();
    int M = sc.nextInt();
    
    int[] value = new int[N];
    
    for(int i = 0; i < N; i++) {
      value[i] = sc.nextInt();
    }
    
    int[] dp = new int[M+1];
    dp[0] = 0;
    for(int i = 1; i < M+1; i++) {
      dp[i] = 10001;
    }
    
//dp[i-k]  는 금액 i-k 원을 만들 수 있는 최소한의 화폐 개수
//i-k원을 만드는 방법이 존재하는 경우 dp[i] = min(dp[i], dp[i-k]+1)
//i-k원을 만드는 방법이 존재하지않는 경우 dp[i] = 10001
    for(int i = 0; i < N; i++) {
      for(int j = value[i]; j < M+1; j++) {
        if(dp[j-value[i]] != 10001) {
          dp[j] = Math.min(dp[j], dp[j - value[i]] + 1);
        }
      }
    }
    
    if(dp[M] == 10001)
      System.out.println(-1);
    else
      System.out.println(dp[M]);
  }

}
