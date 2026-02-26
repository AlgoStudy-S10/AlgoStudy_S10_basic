package ksm.template;
import java.util.*;
import java.io.*;

public class Main {
    // 보드의 세로(R)와 가로(C) 크기를 저장할 변수입니다.
    static int R;
    static int C;
    
    // 입력받은 알파벳 보드를 숫자로 변환하여 저장할 2차원 배열입니다.
    static int[][] matrix;
    
    // 알파벳 'A'를 기준으로 삼아 문자를 0~25 사이의 숫자로 변환하기 위한 기준값입니다.
    static int std = 'A';
    
    // (참고) 선언은 되었지만 현재 코드 로직상 사용되지 않는 변수입니다.
    static boolean[][] visited;
    
    // 클래스 레벨에서 선언된 알파벳 방문 여부 체크 배열입니다.
    // (주의: main 메서드 안에서 같은 이름으로 새로 선언해서 사용 중이므로, 이 변수는 실제론 쓰이지 않습니다.)
    static boolean[] check;
    
    // 말이 지날 수 있는 최대 칸의 수(정답)를 갱신하고 저장할 변수입니다.
    static int counter;
    
	public static void main(String[] args) throws IOException{
        // 빠른 입력을 위해 BufferedReader와 StringTokenizer를 사용합니다.
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
        // 첫 줄에서 R(행)과 C(열)를 입력받습니다.
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
        
        // 보드의 크기만큼 matrix 배열을 초기화합니다.
    	matrix = new int[R][C];
        
        // 보드의 상태를 입력받아 matrix 배열을 채웁니다.
    	for(int i=0; i<R; i++) {
    		String line = br.readLine();
    		for(int j=0; j<C; j++) {
                // 문자를 입력받은 뒤, 'A'를 빼서 0~25 사이의 정수로 변환하여 저장합니다.
                // (예: 'A' -> 0, 'B' -> 1, ... 'Z' -> 25)
    			matrix[i][j] = line.charAt(j)-std;
    		}	
    	}

        // 알파벳 A부터 Z까지(총 26개) 방문 여부를 체크할 배열을 생성합니다.
		boolean[] check = new boolean['Z'-'A'+1];
        
        // 시작점(0, 0)의 알파벳을 방문했다고 표시합니다.
		check[matrix[0][0]] = true;
        
        // (0, 0) 위치에서 시작하여 DFS 탐색을 시작합니다. 초기 이동 횟수는 1입니다.
		dfs(0, 0, 1, check);
        
        // 탐색이 모두 끝난 후, 최댓값으로 갱신된 counter를 출력합니다.
		System.out.println(counter);
	}
    
    // 상하좌우 탐색을 위한 방향 배열입니다. (dy: y좌표 이동, dx: x좌표 이동)
	static int[] dy = {1, -1, 0, 0}; // 하, 상, 제자리, 제자리
	static int[] dx = {0, 0, 1, -1}; // 제자리, 제자리, 우, 좌
	
    // 깊이 우선 탐색(DFS)과 백트래킹을 수행하는 메서드입니다.
	static void dfs(int y, int x, int count, boolean[] check) {
        // 상하좌우 4가지 방향으로 이동을 시도합니다.
		for(int i=0; i<4; i++) {
            // 다음으로 이동할 새로운 y, x 좌표를 계산합니다.
			int ny = y+dy[i];
			int nx = x+dx[i];
            
            // 1. 새로운 좌표가 보드 범위(R x C) 안에 있고
            // 2. 해당 좌표의 알파벳을 아직 방문하지 않았다면 (!check[...])
			if((ny>=0&&ny<R) && (nx>=0&&nx<C) && !check[matrix[ny][nx]]) {
                
                // 해당 알파벳을 방문 처리합니다.
				check[matrix[ny][nx]] = true;
                
                // 다음 칸으로 이동하여 DFS를 재귀 호출합니다. (이동 횟수 count+1 증가)
				dfs(ny, nx, count+1, check);
                
                // [백트래킹] 다른 경로로 탐색할 때 영향을 주지 않도록, 방문 처리를 다시 해제합니다.
				check[matrix[ny][nx]] = false;
			}
		}
        // 각 재귀 호출마다 현재까지의 이동 횟수(count)와 기존의 최대 이동 횟수(counter)를 비교하여 최댓값을 갱신합니다.
		counter = Math.max(counter, count);
	}
}
