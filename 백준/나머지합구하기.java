import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_10986 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    long[] S = new long[N];
    long[] C = new long[M]; 
    
    st = new StringTokenizer(br.readLine()," ");
    S[0] = Long.parseLong(st.nextToken());
    for(int i = 1; i < N; i++) {
      S[i] = S[i-1] + Long.parseLong(st.nextToken());
    }
    
    long answer = 0;
    for(int i = 0; i < N; i++) {
      int remainder = (int) (S[i] % M);
      if(remainder == 0) {
        answer++;
      }
      C[remainder]++;
    }
    
    for(int i = 0; i < M; i++) {
      if(C[i] > 1) {
        answer = answer + (C[i] * (C[i]-1) / 2);
      }
    }
    System.out.println(answer);
  }

}
