package lkh.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] sudoku;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sudoku = new int[9][9];
		for (int r = 0; r < 9; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 9; c++)
				sudoku[r][c] = Integer.parseInt(st.nextToken());
		}
		
		// (0,0)부터 탐색하기
		findAnswer(0, 0);
	}
	
	// 순차적으로 탐색하며 값을 하나씩 넣기
	public static void findAnswer(int row, int col) {
		// 열이 10 이상인 경우에는, 행 교체 타이밍
		if (col == 9) {
			findAnswer(row + 1, 0);
			return;
		}

		// row = 8, col = 8로 탐색 완료 시, 그것이 바로 첫 정답
		// 바로 print 후 시스템 종료하기
		if (row == 9) {
			StringBuilder sb = new StringBuilder();
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++)
					sb.append(sudoku[r][c] + " ");
				
				sb.append('\n');
			}
			
			System.out.println(sb);
			// return; 하면 답 찾은 뒤에도 계속 탐색해서 틀린 답 나올 가능성 UP
			System.exit(0);
		}
		
		// 해당 좌표에 있는 스도쿠가 비어있지 않으면(0이 아니면), 바로 넘어가기
		if (sudoku[row][col] != 0) {
			findAnswer(row, col + 1);
			return;
		}
		
		// 해당 좌표에 1에서 9까지의 수를 한 번씩 대입하여 DFS 재귀적으로 진행
		// 정답이 아니라면 결국 다시 돌아오기 때문에, 설정한 값은 다시 0으로 바꾸어 진행하기
		for (int num = 1; num <= 9; num++) {
			if (checkValid(row, col, num)) {
				sudoku[row][col] = num;
				findAnswer(row, col + 1);
				sudoku[row][col] = 0;
			}
		}
	}
	
	// 해당 값(value)이 스도쿠 조건을 충족하는지 체크하는 메서드
	public static boolean checkValid(int row, int col, int value) {
		for (int i = 0; i < 9; i++) {
			// 세로 라인 체크
			if (sudoku[i][col] == value)
				return false;
			// 가로 라인 체크
			if (sudoku[row][i] == value)
				return false;
		}
		
		// 해당하는 3 x 3 배열 내 중복 체크
		for (int r = row - row % 3; r < row - row % 3 + 3; r++) {
			for (int c = col - col % 3; c < col - col % 3 + 3; c++) {
				if (r == row && c == col)
					continue;
				
				if (sudoku[r][c] == value)
					return false;
			}
		}
		
		return true;
	}
}