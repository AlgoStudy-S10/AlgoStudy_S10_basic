package jji.template;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, RESULT;
	static int[][] chicken_road;
	static List<Pos> home, chicken;
	static boolean[] isSelected;
	
	static class Pos {
		int r, c;
		Pos(int r, int c) {this.r = r; this.c =c;}
	}
	/** 
	 * 치킨집과 집의 위치를 편리하게 관리하기 위해 Pos 클래스로 좌표 사용
	 * 
	 * 모든 치킨집 A개와 모든 집 B개 사이의 거리를 모두 저장하는 chicken_road 배열
	 * 행은 치킨집, 열은 집, 값은 두 사이의 거리.
	 * 
	 * 치킨집과 집의 개수를 미리 알 수 없으므로 연결리스트를 사용하여 저장
	 * */
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				int b = Integer.parseInt(st.nextToken());
				if (b == 1) home.add(new Pos(i, j));    	// 1 집
				if (b == 2) chicken.add(new Pos(i, j));		// 2 치킨집
			}
		}
		
		chicken_road = new int[chicken.size()][home.size()]; // 행은 치킨집, 열은 집
		
		for (int i=0; i<chicken.size(); i++) {
			for (int j=0; j<home.size(); j++) {
				chicken_road[i][j] = getDistance(chicken.get(i), home.get(j)); // 치킨집과 집 사이의 거리를 값으로 저장
			}
		}
		
		isSelected = new boolean[chicken.size()]; // 조합에서 활용하기 위함. 선택한 치킨집이므로 치킨집 수만큼
		RESULT = Integer.MAX_VALUE; // 최솟값을 구해야하므로 큰 값을 미리 저장
		
		comb(0,0);
		
		System.out.println(RESULT);
	}
	
	static void comb(int cnt, int start) {
		// M개의 치킨집을 선택
		if (cnt == M) { 
			// 집별로 치킨거리를 알기위한 임시배열 생성
			int[] arr = new int[home.size()];
			for (int i=0; i<chicken.size(); i++) {
				// 선택한 치킨집이 나오면
				if (isSelected[i]) {
					for (int j=0; j<home.size(); j++) {
						// 아직 아무 값도 없으면 그냥할당
						if (arr[j] == 0) arr[j] = chicken_road[i][j];
						// 이미 거리가 적혀있으면, 최솟값을 할당
						else arr[j] = Math.min(arr[j], chicken_road[i][j]);
					}
				}
			}
			// 위의 반복문이 끝나면, 배열에는 모든 집에서 가장 가까운 치킨집과의 거리가 저장됨
			int sum = 0;
			for (int i =0; i<home.size();i++) {
				sum += arr[i];
			}
			// 다 더하면 도시의 치킨거리를 구할 수 있음.
			// 최솟값을 전역변수에 업데이트
			RESULT = Math.min(RESULT, sum);
			return;
		}
		
		for (int i=start; i<chicken.size(); i++) {
			isSelected[i] = true;
			comb(cnt+1, i+1);
			isSelected[i] = false;
		}
		
	}
	static int getDistance(Pos A, Pos B) {
		return Math.abs(A.r-B.r) + Math.abs(A.c-B.c);
	}
}
