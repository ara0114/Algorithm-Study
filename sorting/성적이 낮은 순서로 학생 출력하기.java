
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * N명의 학생정보가 있음. 학생정보는 학생의 이름과 성적으로 구분
 * 각 학생의 이름과 성적정보 주어졌을때 성적이 낮은 순서대로 학생의 이름을 출력
 *
 * 첫번째줄에 학생의수 N이 입력됌(1<=N<=100,000)
 * 두번째줄부터 N+1번째 줄에는 학생의 이름을 나타내는 문자열 A와 학생의 성적을 나타내는 정수B가
 * 공백으로 구분되어 입력됌. 문자열 A의 길이와 학생의 성적은 100 이하의 자연수.
 * 
 * 모든 학생의 이름을 성적이 낮은 순서대로 출력, 성적이 동일한 학생들의 순서는 자유롭게 출력해도 괜찮음.
 */
class Student implements Comparable<Student>{//정렬을 위한 comparable 인터페이스구현
  private String name;
  private int score;
  
  public Student(String name, int score) {
    this.name = name;
    this.score = score;
  }
  public String getName(){
    return this.name;
  }

  @Override
  public int compareTo(Student o) {//성적순으로 정렬
    if(this.score < o.score) {
      return -1;
    }
    return 1;
  }
}

public class PrintStudentGrade {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());//학생수
    
    ArrayList<Student> students = new ArrayList<>();
    for(int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine()," ");
      String name = st.nextToken();
      int score = Integer.parseInt(st.nextToken());
      students.add(new Student(name, score));//학생리스트
    }
    
    Collections.sort(students);//학생리스트 정렬
    
    for(int i = 0; i < students.size(); i++) {
      System.out.print(students.get(i).getName() + " ");//정렬된 순으로 학생이름 출력
    }
    System.out.println();
  }

}
