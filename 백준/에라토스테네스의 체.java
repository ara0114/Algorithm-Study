import java.util.ArrayList;
import java.util.Scanner;

public class Eratos {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int K = sc.nextInt();
    
    int count = 0; 
    ArrayList<Boolean> primeList = new ArrayList<>();
    primeList.add(false);
    primeList.add(false);
    for(int i = 2; i <= N; i++) {
      primeList.add(i,true);
    }
    for(int i = 2; i <= N ; i++) {
      for(int j = i; j <= N; j += i) {
        if(primeList.get(j)) {
          primeList.set(j, false);
          count++;
          if(count == K) {
            System.out.println(j);
            break;
          }
        }
      }
    }
  }
}