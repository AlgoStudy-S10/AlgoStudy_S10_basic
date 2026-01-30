package practiceCodingTest.backJoon;
import java.util.*;
import java.io.*;

public class Back5073 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean checker = true;
		StringTokenizer st;
		while(checker) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==0 && b==0 && c==0) {
				break;
			}
			else if(a==0 || b==0 || c==0) {
			System.out.println("Invalid");
			}
			else {
				int maxValue = Math.max(a, b);
				maxValue = Math.max(maxValue,c);
				int answer = (maxValue*2)-(a+b+c);
				
				if (answer>=0) {
					System.out.println("Invalid");	
				}
				else {
					if(a==b && a==c) {
						System.out.println("Equilateral");
					}
					else if(a==b || a==c || b==c) {
						System.out.println("Isosceles");
					}
					else {
						System.out.println("Scalene");
					}
				}
			}
		}
	}

}
