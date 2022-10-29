import java.io.*;
import java.util.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		int count = 1;
		for(int i = 1; i < n; i++){
			if(i > 0 && str.charAt(i) != str.charAt(i-1)){
				count++;
			}
		}
		
		System.out.println(count);
	}
}