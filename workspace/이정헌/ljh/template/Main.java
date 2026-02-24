package ljh.template;

import java.io.*;
import java.util.*;

public class Main {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int maxCnt, R, C;
    static char[][] board;
    static Map<Character, Boolean> isSelected;

    static int[] dr = { -1, 1, 0, 0 }; // 상하좌우(0123)
    static int[] dc = { 0, 0, -1, 1 };

    // main
    public static void main(String[] args) throws IOException {
        // init
        maxCnt = 0;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        isSelected = new HashMap<>();
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            isSelected.put(ch, false);
        }

        // input
        for (int r = 0; r < R; r++) {
            String line = br.readLine();
            for (int c = 0; c < C; c++) {
                board[r][c] = line.charAt(c);
            }
        }

        // solve
        isSelected.put(board[0][0], true);
        dfs(0, 0, 1);

        // output
        System.out.println(maxCnt);
        br.close();
    }

    // DFS
    private static void dfs(int r, int c, int cnt) {
        boolean isLast = true;

        // recursion
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C)
                continue;
            if (isSelected.get(board[nr][nc]))
                continue;
            isSelected.put(board[nr][nc], true);
            dfs(nr, nc, cnt + 1);
            isSelected.put(board[nr][nc], false);
            isLast = false;
        }

        // base
        if (isLast) {
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }
    }
}