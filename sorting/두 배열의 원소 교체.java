import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 
 * 두 배열 A,B는 N개의 원소로 구성되어있으며 모든 원소는 10,000,000보다 작은 자연수
 * 최대 K번 바꿔치기 연산을 수행할 수 있고
 * 바꿔치기 연산은 배열A의 원소 하나와 배열B의 원소 하나를 골라 서로 바꾸는 것을 말함
 * 배열 A의 모든 원소의 합이 최대가 되도록 바꿔치기 연산을 수행하여 모든 원소의 합(최댓값)을 출력
 *
 * 첫째줄에 N,K가ㅏ 공백으로 구분되어 입력(1<=N<=100,000, 0<=K<=N)
 * 두번째줄에 배열A의 원소들이 공백으로 구분되어 입력.
 * 세번째줄에 배열B의 원소들이 공백으로 구분되어 입력.
 * 최대K번의 바꿔치기 연산을 수행하여 만들 수 있는 배열A의 모든 원소의 합의 최댓값을 출력
 */
public class ChangeElement {
  static int[] A;
  static int[] B;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");
    
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    
    A = new int[N];
    B = new int[N];
    
    st = new StringTokenizer(br.readLine()," ");
    for(int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(A);//A를 오름차순 정렬
    
    st = new StringTokenizer(br.readLine()," ");
    for(int i = 0; i < N; i++) {
      B[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(B);//B를 오름차순 정렬
    
  
    // A의 최솟값과 B의 최댓값을 바꿔치기(A의 가장 작은 원소가 B의 가장 큰원소보다 작을때만)
    for(int i = 0; i < K; i++) {
      if(A[i] < B[N-1-i]) {
        int temp = A[i];
        A[i] = B[N-1-i];
        B[N-1-i] = temp;
      }else {
        break;
      }
    }
    
    int sum = 0;
    for(int i = 0; i < N; i++) {
      sum += A[i];
    }
    
    System.out.println(sum);
  }
}
