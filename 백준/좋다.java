import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1253 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    long[] num = new long[N];
    
    st = new StringTokenizer(br.readLine()," ");
    for(int i = 0; i < N; i++) {
      num[i] = Long.parseLong(st.nextToken());
    }
    Arrays.sort(num);
    
    int answer = 0;
    for(int i = 0; i < N; i++) {
      long gNum = num[i];
      int start = 0;
      int end = N-1;
      
      while(start < end) {
        if(num[start] + num[end] == gNum) {
          if(start != i && end != i) {
            answer++;
            break;
          }else if(start == i) {
            start++;
          }else if(end == i) {
            end--;
          }
        }else if(num[start] + num[end] < gNum) {
          start++;
        }else if(num[start] + num[end] > gNum) {
          end--;
        }
      }
    } 
    System.out.println(answer);
  }
}