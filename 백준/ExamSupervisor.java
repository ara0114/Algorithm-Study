import java.util.Scanner;

public class ExamSupervisor {
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);    
    int N = sc.nextInt();//시험장 갯수
    
    int[] tester = new int[N]; //시험장당 응시자수    

    for(int i = 0; i < N; i++) {
      tester[i] = sc.nextInt();
    }
    
    int B = sc.nextInt();//총감독관이 감독 가능한 인원
    int C = sc.nextInt();//부감독관이 감독 가능한 인원
   
    long cnt = N;//시험장 갯수만큼 총감독관 필요
    for (int i = 0; i < N; i++) {
      tester[i] -= B; //고사장 총감독관이 감독할 수 있는 인원 빼고
      if(tester[i] <= 0) {
        continue;
      }else {//응시자수 남아있다면
        cnt += tester[i] / C;//응시자수를 부감독관이 가능한 인원으로 나누어 부감독관의 수 구해서 더해주기
        if(tester[i] % C != 0) {//만약 나누어떨어지지 않으면 부감독 한명 더
          cnt++;
        }
      }     
    }   
    System.out.println(cnt);
  }
}
