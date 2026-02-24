package lkh.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	// R, G, B 값만 들어오기에, char array로 설정함
	// 3가지 경우의 수밖에 없으므로 자료형 크기를 극한으로 줄이는 방법도 있겠지만, 굳이?
	static char[][] paint;
	static boolean[][] visited;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		paint = new char[N][N];
		
		// StringTokenizer 필요 없이 charAt으로 값 받기
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				paint[r][c] = str.charAt(c);
			}
		}
		
		// 적록색약이 아닌 사람의 스캔
		visited = new boolean[N][N];
		int count1 = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c])
					continue;
				
				// 방문 안 한 곳은 새로운 구역
				// 해당 구역에 해당하는 곳을 모두 찾아 visited = true 처리하기
				// 적록색약이 아니므로 threeEyeSearch의 rgConfusion = false 처리 후 진행
				visited[r][c] = true;
				threeEyeSearch(r, c, paint[r][c], false);
				count1++;
			}
		}
		
		
		// 적록색약인 사람의 스캔
		visited = new boolean[N][N];
		int count2 = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c])
					continue;
				
				visited[r][c] = true;
				threeEyeSearch(r, c, paint[r][c], true);
				count2++;
			}
		}
		sb.append(count1 + " " + count2 + "\n");
		
		System.out.println(sb);
	}
	
	// 1) 적록색약인 경우와 2) 적록색약이 아닌 경우 모두를 처리하는 재귀적 함수 구현
	// 예상보다 깔끔하지는 않지만, 생각나서 진행함
	static void threeEyeSearch(int r, int c, char type, boolean rgConfusion) {
		// 1) 적록색맹의 경우 실행되는 코드
		if (rgConfusion) {
			for (int i = 0; i < 4; i++) {
				if (isIn(r + dr[i], c + dc[i]) && !visited[r + dr[i]][c + dc[i]] &&
						// 1-1) 타겟 색과 동일한 경우
							(paint[r + dr[i]][c + dc[i]] == type
						// 1-2) 타겟 색이 Red이고, 현재 선택한 공간의 색이 Green인 경우
						|| (type == 'R' && paint[r + dr[i]][c + dc[i]] == 'G')
						// 1-3) 타겟 색이 Green이고, 현재 선택한 공간의 색이 Red인 경우
						|| (type == 'G' && paint[r + dr[i]][c + dc[i]] == 'R'))) {
					visited[r + dr[i]][c + dc[i]] = true;
					threeEyeSearch(r + dr[i], c + dc[i], type, rgConfusion);
					continue;
				}
			}
			
			// 모두 찾았으면 종료
			return;
		}
		
		// 2) 적록색맹이 아닌 경우 실행되는 코드
		for (int i = 0; i < 4; i++) {
			if (isIn(r + dr[i], c + dc[i]) && !visited[r + dr[i]][c + dc[i]] &&
					paint[r + dr[i]][c + dc[i]] == type) {
				visited[r + dr[i]][c + dc[i]] = true;
				threeEyeSearch(r + dr[i], c + dc[i], type, rgConfusion);
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
} 
