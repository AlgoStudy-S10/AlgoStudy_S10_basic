package ksm.template;
import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<int[]> q; // 0(빈칸)인 좌표들을 저장할 리스트
    static int[][] answer;     // 완성된 스도쿠 판을 저장할 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] matrix = new int[9][9];
        q = new ArrayList<>();

        // 1. 입력 받기 및 빈칸(0) 위치 저장
        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 0) {
                    q.add(new int[]{i, j}); // 채워야 할 칸의 좌표를 리스트에 담음
                }
            }
        }

        answer = new int[9][9];
        dfs(0, matrix); // 탐색 시작

        // 3. 결과 출력
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                System.out.printf("%d ", answer[i][j]);
            }
            System.out.println();
        }
    }

    // 깊이 우선 탐색(DFS) 및 백트래킹을 통해 숫자 채우기
    public static void dfs(int idx, int[][] matrix) {

        // [기저 조건] 모든 빈칸을 다 채웠을 때
        if(idx >= q.size()) {
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    answer[i][j] = matrix[i][j]; // 결과값 복사
                }
            }
            // 만약 하나의 정답만 찾고 종료하고 싶다면 여기서 System.exit(0) 등을 사용 가능
            return;
        }

        // 현재 채워야 할 빈칸의 좌표 가져오기
        int r = q.get(idx)[0];
        int c = q.get(idx)[1];

        // 1부터 9까지의 숫자를 하나씩 대입해봄
        for(int value=1; value<=9; value++) {
            // 현재 숫자가 해당 위치에 들어갈 수 있는지 검사
            if(check(r, c, value, matrix)) {
                matrix[r][c] = value; // 유효하다면 숫자 입력
                dfs(idx+1, matrix);   // 다음 빈칸으로 이동 (재귀 호출)

                // 재귀에서 돌아왔을 때, 이미 정답을 찾았다면 불필요한 탐색 방지를 위해 체크 로직이 추가되면 더 좋음
                // 백트래킹 핵심: 다음 경우의 수를 위해 다시 0으로 초기화
                matrix[r][c] = 0;
            }
        }
    }

    // 가로, 세로, 3x3 박스 조건을 모두 확인하는 함수
    public static boolean check(int r, int c, int value, int[][] matrix) {
        return checkRow(r, value, matrix) &&
                checkCol(c, value, matrix) &&
                checkBox(r, c, value, matrix);
    }

    // 가로(행) 검사: 해당 줄에 같은 숫자가 있는지 확인
    public static boolean checkRow(int r, int value, int[][] matrix) {
        for(int i=0; i<9; i++) {
            if(matrix[r][i] == value) return false;
        }
        return true;
    }

    // 세로(열) 검사: 해당 줄에 같은 숫자가 있는지 확인
    public static boolean checkCol(int c, int value, int[][] matrix) {
        for(int i=0; i<9; i++) {
            if(matrix[i][c] == value) return false;
        }
        return true;
    }

    // 3x3 박스 검사: 작은 사각형 안에 같은 숫자가 있는지 확인
    public static boolean checkBox(int r, int c, int value, int[][] matrix) {
        // 박스의 시작 위치 계산 (0, 3, 6 중 하나)
        int startRow = (r / 3) * 3;
        int startCol = (c / 3) * 3;

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(matrix[startRow+i][startCol+j] == value) return false;
            }
        }
        return true;
    }
}