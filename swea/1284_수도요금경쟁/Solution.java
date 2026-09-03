import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int t = Integer.parseInt(br.readLine());
	
		for(int i=1; i<=t; i++) {
			int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int answer = comparison(n);
			
			bw.write("#"+i+" "+answer+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int comparison(int[] n) {
		int a = a(n[0], n[4]);
		int b = b(Arrays.copyOfRange(n, 1, n.length));
		
		return a<b?a:b;
	}
	
	public static int a(int p, int w) {
		return w*p;
	}
	
	public static int b(int[] n) {
		if(n[3] <= n[1]) {
			return n[0];
		} else {
			return n[0] + ((n[3] - n[1]) * n[2]);
		}
	}
	
}
