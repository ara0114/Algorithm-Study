import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1940 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int[] material = new int[N];
    
    st = new StringTokenizer(br.readLine());
    int M = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine()," ");
    for(int i = 0; i < N; i++) {
      material[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(material);
    
    int count = 0;
    int start = 0;
    int end = N-1;
    
    while(start < end) {
      if(material[start] + material[end] > M) {
        end--;
      }else if(material[start] + material[end] < M) {
        start++;
      }else if(material[start] + material[end] == M) {
        start++;
        end--;
        count++;
      }
    }
    System.out.println(count);
  }
}