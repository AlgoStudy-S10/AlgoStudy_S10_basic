package ish.template;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*
;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, min, C;
    static ArrayList<Home> homes = new ArrayList<Home>();
    static ArrayList<Chicken> chickens = new ArrayList<Chicken>();
    static boolean[] selected;

    static class Home {
        int x;
        int y;
        ArrayList<Chickdist> cd;
        
        public Home(int x, int y){
            this.x = x;
            this.y = y;
            this.cd = new ArrayList<Chickdist>();
        }
    }

    static class Chickdist {
        int dist;
        int chickNo;
        
        public Chickdist(int dist, int chickNo){
            this.dist = dist;
            this.chickNo = chickNo;
        }
    }

    static class Chicken {
        int x;
        int y;
        int chickNo;
        public Chicken(int x, int y, int chickNo) {
            this.x = x;
            this.y = y;
            this.chickNo = chickNo;
        }
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){ // 입력과 동시에 계산
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int temp = Integer.parseInt(st.nextToken());
                switch (temp) {
                    case 0:
                        break;
                    case 1:
                        homes.add(new Home(i, j));
                        break;
                    case 2:
                        chickens.add(new Chicken(i, j, chickens.size()));
                        break;
                    default:
                        break;
                }
            }
        }

        C = chickens.size();
        selected = new boolean[C]; //dfs 순회를 위해 치킨집 개수 만큼 방문 boolean 배열 생성
        min = Integer.MAX_VALUE; // min 초기화

        for(Home h : homes){ // 각 집 - 치킨집에 대해서 거리를 모두 계산 후 저장
            for(Chicken c : chickens){
                int dist = Math.abs(h.x-c.x)+Math.abs(h.y-c.y);
                h.cd.add(new Chickdist(dist, c.chickNo));
            }
            // dist를 기준으로 오름차순 정렬
            h.cd.sort(Comparator.comparingInt(cd -> cd.dist));
        }

        dfs(0,0);


        System.out.println(min);
    }

    public static void dfs(int start, int cnt){
        if(cnt==M){
            calDist();
            return;
        }

        for(int i=start;i<C;i++){
            selected[i]=true;
            dfs(i+1, cnt+1);
            selected[i]=false;
        }
    }

    public static void calDist(){
        int sum = 0;
        for(Home h : homes){ // 각 집에 대해서
            for(Chickdist cd : h.cd){ 
                if(selected[cd.chickNo]){ // 조합상 도달할 수 있는 치킨 집 중 가장 작은 거리를 더함
                    sum+=cd.dist;
                    break;
                }
            }
        }
        if(sum<min) min = sum; // 최소값 보다 작다면 업데이트
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] map;
    static boolean[][] visited;
    static int cnt, N;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];
        cnt = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j=0;j<N;j++){
                char temp = line.charAt(j);
                if(temp=='R'){
                    map[i][j]=1;
                }else if (temp=='G'){
                    map[i][j]=2;
                }else if (temp=='B'){
                    map[i][j]=3;
                }
            }
        }

        for(int i=0;i<N;i++){ 
            for(int j=0;j<N;j++){
                if(visited[i][j]) continue;
                bfs(i,j); // 색맹이 아닐 때 bfs flood fill
                cnt++;
            }
        }

        sb.append(cnt).append(" ");

        cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                visited[i][j] = false;
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(visited[i][j]) continue;
                jbfs(i,j); // 색맹이 일 때 bfs flood fill
                cnt++;
            }
        }

        sb.append(cnt);

        System.out.println(sb);
    }

    static void bfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<int[]>();

        visited[x][y] = true;
        q.add(new int[] {x,y});

        while(!q.isEmpty()){
            int[] temp;
            temp = q.poll();
            int nx,ny,color;
            color = map[temp[0]][temp[1]];

            for(int i=0;i<4;i++){
                nx = temp[0] + dx[i];
                ny = temp[1] + dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
                if(visited[nx][ny] || map[nx][ny]!=color) continue;
                visited[nx][ny] = true;
                q.add(new int[] {nx, ny});
            }
        }
    }

    static void jbfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<int[]>();

        visited[x][y] = true;
        q.add(new int[] {x,y});

        while(!q.isEmpty()){
            int[] temp;
            temp = q.poll();
            int nx,ny,color;
            color = map[temp[0]][temp[1]];

            for(int i=0;i<4;i++){
                nx = temp[0] + dx[i];
                ny = temp[1] + dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
                if(visited[nx][ny]) continue;
                if((color<3 && map[nx][ny]==3) ||(color==3 && map[nx][ny]<3)) continue;
                visited[nx][ny] = true;
                q.add(new int[] {nx, ny});
            }
        }
    }
}
