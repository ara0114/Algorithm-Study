import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_11003 {
  static int N;
  static int L;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    
    Deque<Node> mydeque = new LinkedList<>();
    
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      int now = Integer.parseInt(st.nextToken());
      
      while(!mydeque.isEmpty() && mydeque.getLast().value > now)//덱 마지막 위치에서 현재보다 큰값은 덱에서 제거
        mydeque.removeLast();
      
      mydeque.addLast(new Node(now,i));// 덱 마지막 위치에 현재값 저장
      
      if(mydeque.getFirst().index <= i - L) //덱의 첫번째 위치부터 L의 범위 벗어난 값 덱에서 제거
        mydeque.removeFirst();
      
      bw.write(mydeque.getFirst().value +" "); // 덱의 첫번째 데이터 출력
    }
    bw.flush();
    bw.close();
  }
  static class Node{
    int value;//자신의 값
    int index;//자신의 위치
    Node(int value, int index){
      this.value = value;
      this.index = index;
    }
  }

}