import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_12891 {
  static int S;
  static int P;
  static int[] checkArr = new int[4];// 비밀번호 체크배열
  static int[] checkCur = new int[4];// 현재 문자열 상태 체크배열
  static char[] DNA;// 입력받은 DNA문자열을 저장할 배열
  static int checkOk = 0;// 체크해야할 문자중 몇개가 만족했는가 => checkOk==4이면 answer++
  static int answer = 0;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");
    
    S = Integer.parseInt(st.nextToken()); //민호가 임의로 만든 문자열길이 S
    P = Integer.parseInt(st.nextToken()); //비밀번호로 사용할 부분 문자열길이 P
    DNA = new char[S];
    
    DNA = br.readLine().toCharArray();//입력받은 문자열 캐릭터배열로 
    
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < 4; i++) {
      checkArr[i] = Integer.parseInt(st.nextToken());//문자당 만족해야하는 갯수
      if(checkArr[i] == 0)//없어도 상관없는 문자이므로 항상 만족
        checkOk++;
    }
    
    for(int i = 0; i < P; i++) {//부분 문자열 처음 받을 때 세팅
      addInRange(DNA[i]);
    }
    
    if(checkOk == 4)
      answer++;
    
    //슬라이딩윈도우
    for(int right = P; right < S; right++) {
      int left = right-P;
      addInRange(DNA[right]);
      removeInRange(DNA[left]);
      if(checkOk == 4)
        answer++;
    }
    
    System.out.println(answer);
  }

  private static void addInRange(char c) {// 범위에 들어온 문자 처리
    switch(c) {
    case 'A':
      checkCur[0]++;
      if(checkCur[0] == checkArr[0])
        checkOk++;
      break;
    case 'C':
      checkCur[1]++;
      if(checkCur[1] == checkArr[1])
        checkOk++;
      break;
    case 'G':
      checkCur[2]++;
      if(checkCur[2] == checkArr[2])
        checkOk++;
      break;
    case 'T':
      checkCur[3]++;
      if(checkCur[3] == checkArr[3])
        checkOk++;
      break;
    }
  }
  private static void removeInRange(char c) {// 범위에서 제거한 문자 처리
    switch(c) {
    case 'A':
      if(checkCur[0] == checkArr[0])
        checkOk--;
      checkCur[0]--;
      break;
    case 'C':
      if(checkCur[1] == checkArr[1])
        checkOk--;
      checkCur[1]--;
      break;
    case 'G':
      if(checkCur[2] == checkArr[2])
        checkOk--;
      checkCur[2]--;
      break;
    case 'T':
      if(checkCur[3] == checkArr[3])
        checkOk--;
      checkCur[3]--;
      break;
    }
  }
}