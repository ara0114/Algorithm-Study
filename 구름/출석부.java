import java.io.*;
import java.util.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		String[] name = new String[N];
		double[] height = new double[N];
		Map<String, Double> map = new HashMap<>();
		

		
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine()," ");
			name[i] = st.nextToken();
			height[i] = Double.parseDouble(st.nextToken());
			map.put(name[i], height[i]);
		}
		
		Arrays.sort(name);
		String sname = name[k-1];
		String result = String.format("%.2f",map.get(sname));
		System.out.println(sname +" "+ result);
	}
}