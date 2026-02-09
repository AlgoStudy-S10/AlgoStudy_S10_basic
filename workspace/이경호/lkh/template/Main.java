package lkh.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// static 변수 지정하는 것이 무조건 좋은 것은 아니지만,
	// 조금이나마 깔끔한 인자 구성을 위해 여러 변수를 static으로 지정.
	private final static int MAX_CHICKEN = 13;
	static int minDist;
	static int numHome, numChicken;
	static int[][] homeLocat, chickenLocat;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 초기화 필수
		minDist = Integer.MAX_VALUE;
		
		// 전체 맵에 대한 정보(allMap) 없이
		// 1. 치킨집 위치만 담은 array (chickenLocat)
		// 2. 가정집 위치만 담은 array (homeLocat) 로 구성
		homeLocat = new int[2 * N][2];
		chickenLocat = new int[MAX_CHICKEN][2];
		numHome = 0;
		numChicken = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int curNum = Integer.parseInt(st.nextToken());
				// 치킨집 수가 최대 13개이므로, 빠른 호출을 위한 array 생성에도 문제가 없을 것으로 판단
				if (curNum == 2) {
					chickenLocat[numChicken][0] = i;
					chickenLocat[numChicken++][1] = j;
				}
				// 가정집 수도 최대 100개로, 큰 문제는 아닐 것으로 판단해 가정집에 대한 좌표도 저장
				else if (curNum == 1) {
					homeLocat[numHome][0] = i;
					homeLocat[numHome++][1] = j;
				}
			}
		}
		
		// chooseList : 재귀 호출을 통해 M개의 치킨집을 선정하고, 이후 dfs() 호출
		// dfs : 주어진 치킨집에서 DFS를 통해 최소값 찾기 (with Backtracking)
		chooseList(new boolean[numChicken], 0, 0, M);
		
		sb.append(minDist);
		System.out.println(sb);
	}
	
	// 재귀 호출 형식의 DFS
	public static void dfs(boolean[] choosed, int[][] homeLocat, int prevValue, int i) {
		// 마지막 상황에는, minDist와 비교해 필요 시 갱신
		if (i == numHome) {
			minDist = Math.min(minDist, prevValue);
			return;
		}
		
		int curMinValue = Integer.MAX_VALUE;
		for (int c = 0; c < numChicken; c++) {
			// 이전 chooseList 메서드에서 선택된 경우에만 사용 가능
			if (!choosed[c])
				continue;
			
			// 다양한 치킨집 중 최소 거리에 있는 값을 찾는 중
			curMinValue = Math.min(curMinValue, Math.abs(chickenLocat[c][0] - homeLocat[i][0])
											+ Math.abs(chickenLocat[c][1] - homeLocat[i][1]));
		}
		// 이미 총 최소값(minDist)을 넘겼다면, DFS를 더 이상 진행하지 않음
		if (prevValue + curMinValue > minDist)
			return;
		
		// 가능성이 있는 경우, 다음 인덱스(i + 1)에 대해서 DFS 이어 진행
		dfs(choosed, homeLocat, prevValue + curMinValue, i + 1);
	}
	
	// 최대 13개의 치킨집 중 M개의 치킨집을 선정하는 모든 경우의 수를 고려하기 위한 함수
	public static void chooseList(boolean[] choosed, int curr, int picked, int M) {
		// 남은 치킨집(numChicken - curr)이 남은 선택 수(M - picked)보다 적으면 불가능 사례
		// 매번 계산을 통해 가망이 없는 경우의 수는 제외
		// ex. 너무 선택을 안 해서 더 이상 남은 선택지를 충족할 수 없을 때
		if (curr + M - picked > numChicken)
			return;
		
		// 다 뽑은 상황이므로, DFS 이어서 진행
		if (picked == M) {
			dfs(choosed, homeLocat, 0, 0);
			return;
		}
		
		// 인덱스 curr에서 선택을 한 경우
		choosed[curr] = true;
		chooseList(choosed, curr + 1, picked + 1, M);

		// 인덱스 curr에서 선택을 안 한 경우
		choosed[curr] = false;
		chooseList(choosed, curr + 1, picked, M);
	}
}
