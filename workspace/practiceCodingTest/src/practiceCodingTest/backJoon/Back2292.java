package practiceCodingTest.backJoon;
import java.util.*;
import java.io.*;

public class Back2292 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 1;
		
		for(int i=0; i<Integer.MAX_VALUE; i++) {
			if (N==1) {
				break;
			}
			else if(N>6+(6*i)) {
				answer += 1;
			}
			else break;
		}
		System.out.println(answer);
	}
}

/*
 * 1	
 * 6	+5
 * 17	+11
 * 34	+17
 * 57	+23
 */
