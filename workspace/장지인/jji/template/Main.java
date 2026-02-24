package jji.template;

import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static char[][] grid;
	static boolean[][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine().trim());

		grid = new char[N][];
		isVisited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			grid[i] = line.toCharArray();
		}

		// 적록색약이 아닌 기준 dfs
		int A = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!isVisited[i][j]) {
					isVisited[i][j] = true;
					dfs(i, j, grid[i][j]);
					A++;
				}
				// 이미 지났을테니 Gray로 변경함
				if (grid[i][j] != 'B') {
					grid[i][j] = 'G';
				}
			}
		}
		// 적록색약 기준 dfs
		isVisited = new boolean[N][N];
		int B = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!isVisited[i][j]) {
					isVisited[i][j] = true;
					dfs(i, j, grid[i][j]);
					B++;
				}
			}
		}
		System.out.println(A + " " + B);
	}

	static void dfs(int r, int c, char t) {
		int nr, nc;
		for (int i = 0; i < 4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];

			if (nr >= 0 && nc >= 0 && nr < N && nc < N && !isVisited[nr][nc]) {
				if (grid[nr][nc] != t) {
					continue;
				}
				isVisited[nr][nc] = true;
				dfs(nr, nc, t);
			}
		}
	}
}