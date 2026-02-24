package ish.template;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R,C, cnt, max;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] picked;
    static int[] dx = {1, -1, 0 , 0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException{

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C]; // 방문 기록
        picked = new boolean[26]; // 알파벳 기록
        cnt = 1;
        max = 0;

        for(int i=0;i<R;i++){
            String line = br.readLine();
            for(int j=0;j<C;j++){
                char temp = line.charAt(j);
                map[i][j] = temp - 'A';
            }
        }

        visited[0][0] = true;
        picked[map[0][0]] = true;
        dfs(0,0);

        System.out.println(max);
    }

    static void dfs(int x, int y){
        int nx, ny;
        for(int i=0;i<4;i++){ // 상하좌우에 대해서
            nx = x + dx[i];
            ny = y + dy[i];
            // 범위에서 벗어나거나 방문했거나 고른 알파벳이 등장한다면 pass
            if(nx<0 || nx>=R || ny<0 || ny>=C || visited[nx][ny] || picked[map[nx][ny]]) continue;
            visited[nx][ny] = true;
            picked[map[nx][ny]] = true;
            cnt++;
            dfs(nx,ny);
            cnt--; // backtracking
            visited[nx][ny] = false;
            picked[map[nx][ny]] = false;
        }
        if(cnt>max) max = cnt;
    }
}
