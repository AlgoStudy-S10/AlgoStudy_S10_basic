package ksm.template;

import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<int[]> q; // 스도쿠 판에서 0(빈칸)인 좌표들을 순서대로 담을 리스트
    static int[][] answer;     // 정답을 찾았을 때 스도쿠 판의 상태를 복사해둘 배열

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] matrix = new int[9][9];
        q = new ArrayList<>();

        // 1. 9x9 스도쿠 판 입력 데이터 읽기
        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                // 만약 값이 0이라면, 나중에 숫자를 채워야 할 '빈칸'이므로 리스트에 좌표 저장
                if(matrix[i][j]==0) {
                    q.add(new int[]{i, j});
                }
            }
        }

        answer = new int[9][9];
        // 2. DFS 탐색 시작 (리스트의 0번째 빈칸부터 시작)
        dfs(0, matrix);

        // 4. 탐색이 완료된 후 저장된 정답 배열 출력
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                System.out.printf("%d ", answer[i][j]);
            }
            System.out.println();
        }
    }

    // 백트래킹을 이용한 숫자 채우기 함수
    public static void dfs(int idx, int[][] matrix) {

        // [기저 조건] 모든 빈칸(q의 크기만큼)을 다 채웠다면 정답을 복사하고 종료
        if(idx>=q.size()) {
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    answer[i][j] = matrix[i][j];
                }
            }
            return;
        }

        // 현재 채워야 할 빈칸의 행(r)과 열(c) 좌표 추출
        int r = q.get(idx)[0];
        int c = q.get(idx)[1];

        // 해당 빈칸에 1부터 9까지의 숫자를 하나씩 시도
        for(int value=1; value<=9; value++) {
            // 숫자가 스도쿠 규칙(가로, 세로, 박스)에 어긋나지 않는지 확인
            if(check(r, c, value, matrix)) {
                matrix[r][c] = value; // 조건을 만족하면 숫자 배치
                dfs(idx+1, matrix);   // 다음 빈칸(idx+1)을 채우러 재귀 호출
                matrix[r][c] = 0;     // 중요: 탐색이 실패하거나 돌아온 경우 다시 빈칸으로 복구(백트래킹)
            }
        }
    }

    // 가로, 세로, 3x3 박스에 중복된 숫자가 없는지 통합 검사
    public static boolean check(int r, int c, int value, int[][] matrix) {
        if(checkRow(r, value, matrix) && checkCol(c, value, matrix) && checkBox(r, c, value, matrix)) {
            return true;
        }
        return false;
    }

    // 가로 방향(행)에 중복된 숫자가 있는지 검사
    public static boolean checkRow(int r, int value, int[][] matrix) {
        for(int i=0; i<9; i++) {
            if(matrix[r][i] == value) {
                return false;
            }
        }
        return true;
    }

    // 세로 방향(열)에 중복된 숫자가 있는지 검사
    public static boolean checkCol(int c, int value, int[][] matrix) {
        for(int i=0; i<9; i++) {
            if(matrix[i][c] == value) {
                return false;
            }
        }
        return true;
    }

    // 현재 위치(r, c)가 속한 3x3 작은 사각형 안에 중복 숫자가 있는지 검사
    public static boolean checkBox(int r, int c, int value, int[][] matrix) {
        // 3x3 박스의 시작 좌표(왼쪽 위) 계산
        int startRow = (r / 3) * 3;
        int startCol = (c / 3) * 3;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(matrix[startRow+i][startCol+j] == value) {
                    return false;
                }
            }
        }
        return true;
    }
}