package jji.template;

import java.io.*;
import java.util.*;

public class Main {

	static int N, M, RESULT;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		RESULT = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		func(0, 0);
		System.out.println(RESULT);
	}

	// 무작위 3개의 빈칸을 세워보는 함수(조합 응용, 재귀로구현하고 3개의 벽을 세웠으면 확인하는 함수 호출 및 값 갱신)
	static void func(int start, int cnt) {
		// 벽이 3개 세워졌으면 계산 및 갱신
		if (cnt == 3) {
			int area = calc();
			RESULT = Math.max(RESULT, area);
			return;
		}

		for (int i = start; i < N * M; i++) {
			int n = i / M;
			int m = i % M;
			// 빈칸에만 벽을 세울 수 있음
			if (map[n][m] == 0) {
				map[n][m] = 1; // 벽 세우기
				func(i + 1, cnt + 1);
				map[n][m] = 0; // 재귀 나오면 원상복구
			}
		}
	}

	// 주어진 맵에서 바이러스와 접촉하지 않는 구역을 구하는 함수 (BFS로 탐색하다가 바이러스'2'를 만나면 나가버리기, 안 만나면 영역의 크기
	// 반환)
	static int calc() {
		boolean[][] isVisited = new boolean[N][M];
		Queue<Integer> queue = new ArrayDeque<>();
		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				// 빈칸이고 아직 방문하지 않은 지점 찾고 BFS
				if (map[i][j] == 0 && !isVisited[i][j]) {

					int cnt = 0; // 구역의 크기 계산용
					boolean isSpread = false; // 해당 구간이 바이러스가 퍼지는지 안퍼지는지 체크용
					isVisited[i][j] = true;
					queue.offer(i * M + j);

					while (!queue.isEmpty()) {
						cnt++;
						int temp = queue.poll();
						int r = temp / M;
						int c = temp % M;

						for (int k = 0; k < 4; k++) {
							int nr = r + dr[k];
							int nc = c + dc[k];

							// 조건: 범위 내일것 + 방문하지 않았을 것 + 벽이 아닐 것
							if (nr >= 0 && nc >= 0 && nr < N && nc < M && !isVisited[nr][nc] && map[nr][nc] != 1) {
								if (map[nr][nc] == 2) {
									isSpread = true; // 이 구역 전체는 바이러스가 퍼질것.
									// 반복문을 나가지 않는 이유, 어차피 모든 0을 돌게 될 것이므로 방문체크 한번에 처리하려고
								}
								isVisited[nr][nc] = true;
								queue.offer(nr * M + nc);
							}
						}
					}
					// 바이러스가 퍼지지 않았다면,, 결과추가
					if (!isSpread)
						result += cnt;
				}
			}
		}
		return result;
	}
}
