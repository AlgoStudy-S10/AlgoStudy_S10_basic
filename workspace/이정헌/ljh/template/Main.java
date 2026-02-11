import java.io.*;
import java.util.*;

public class Main {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int answer;
    static int N;
    static int[][] grid;

    static boolean[][] row;
    static boolean[][] col;
    static boolean[][] box;

    // main
    public static void main(String[] args) throws IOException {
        // init
        answer = 0;
        N = 0;
        grid = new int[9][9];
        row = new boolean[9][9 + 1];
        col = new boolean[9][9 + 1];
        box = new boolean[9][9 + 1];

        // input
        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                int num = Integer.parseInt(st.nextToken());
                grid[i][j] = num;
                if(num == 0) {
                    N++;
                } else {
                    setSelected(i, j, num, true);
                }
            }
        }

        // solve
        dfs(0);

        // answer
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        br.close();
    }

    // dfs
    private static boolean dfs(int cnt) {
        // base case
        if(cnt == N) {
            return true;
        }

        // find MRV
        int[] mrv = findMRV();
        int r = mrv[0];
        int c = mrv[1];
        List<Integer> nums = getNums(r, c);

        // recursion case
        for(int num : nums) {
            grid[r][c] = num;
            setSelected(r, c, num, true);

            if(dfs(cnt + 1)) return true;

            grid[r][c] = 0;
            setSelected(r, c, num, false);
        }
        return false;
    }

    // set selected
    private static void setSelected(int r, int c, int num, boolean b) {
        row[r][num] = b;
        col[c][num] = b;
        box[getBoxIndex(r, c)][num] = b;
    }

    // get box index
    private static int getBoxIndex(int r, int c) {
        if(r < 3) {
            if(c < 3)       return 0;
            else if(c < 6)  return 1;
            else            return 2;
        } else if(r < 6) {
            if(c < 3)       return 3;
            else if(c < 6)  return 4;
            else            return 5;
        } else {
            if(c < 3)       return 6;
            else if(c < 6)  return 7;
            else            return 8;
        }
    }

    // find MRV
    private static int[] findMRV() {
        int min = Integer.MAX_VALUE;
        int r = 0, c = 0;

        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(grid[i][j] != 0) continue;
                int cnt = 0;
                for(int num=1; num<10; num++) {
                    if(!row[i][num] && !col[j][num] && !box[getBoxIndex(i, j)][num]) cnt++;
                }
                if(cnt < min) {
                    min = cnt;
                    r = i;
                    c = j;
                    if(min == 1) return new int[]{r, c};
                }
            }
        }

        return new int[]{r, c};
    }

    // get able nums
    private static List<Integer> getNums(int r, int c) {
        List<Integer> nums = new ArrayList<>();
        for(int num=1; num<10; num++) {
            if(!row[r][num] && !col[c][num] && !box[getBoxIndex(r, c)][num]) nums.add(num);
        }
        return nums;
    }

}