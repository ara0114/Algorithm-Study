import java.util.*;

public class 15649 {

  static int arr[];// 탐색 값을 담을 배열
  static boolean visit[];// 방문했는지를 체크하는 배열

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    arr = new int[m];
    visit = new boolean[n + 1];// 노드 보통 1부터 시작함(인덱스0은비워두고시작하기위해)
    dfs(n, m, 0);
  }

  public static void dfs(int n, int m, int idx) {

    if (idx == m) {// 인덱스가 M과 같아지면 탐색값을 담았던 배열 출력
      for (idx = 0; idx < arr.length; idx++) {
        System.out.print(arr[idx] + " ");
      }

      System.out.println();

      return;
    }

    for (int i = 1; i <= n; i++) {
      if (!visit[i]) {// 해당 노드 방문하지 않았다면
        visit[i] = true;// 노드를 방문상태로 변경
        arr[idx] = i;// 해당인덱스값에 i값 저장
        dfs(n, m, idx + 1);// 다음 자식 노드 방문을 재귀적으로 방문
        visit[i] = false;// 자식노드 방문 끝나면 방문하지 않은 상태로 변경
      }
    }
  }
}
