package ish.template;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] sudoku, answer;
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        sudoku = new int[9][9];
        answer = new int[9][9];
        
        for(int i=0;i<9;i++){ // 입력 받기
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<9;j++){
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0); // backtracking으로 탐색


        for(int i=0;i<9;i++){ // 결과 출력
            for(int j=0;j<9;j++){
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static public void dfs(int coord){ // 직렬화한 좌표
        if(coord==81){ // 끝까지 탐색을 완료했으면 답을 기록하고 반환한다.
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    answer[i][j]=sudoku[i][j];
                }
            }
            return;
        }
        int x = coord/9; // 몫으로 구하는 x좌표
        int y = coord%9; // 나머지로 구하는 y좌표
        if(sudoku[x][y]!=0){ // 이미 채워져있으면 넘어간다.
            dfs(coord+1);
            return;
        }
        
        ArrayList<Integer> candidate = findCandidate(x, y); // 해당 좌표에 올 수 있는 숫자 후보를 찾는다
        for(int i=0;i<candidate.size();i++){ // size가 0이면 자동으로 넘어가짐
            int num = candidate.get(i);
            sudoku[x][y]=num; // 후보지에서 특정 숫자 선택하고,
            dfs(coord+1); // 다음 좌표 탐색 호출
            sudoku[x][y]=0; // backtracking
        }
    }

    static public ArrayList<Integer> findCandidate(int x, int y){
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] candidate = new boolean[10];

        Arrays.fill(candidate, true);

        for(int i=0;i<9;i++){
            candidate[sudoku[x][i]]=false; // 가로축 탐색 후 후보 제거
            candidate[sudoku[i][y]]=false; // 세로축 탐색 후 후보 제거
        }

        int areax = x/3;
        int areay = y/3;

        for(int i=areax*3;i<areax*3+3;i++){
            for(int j=areay*3;j<areay*3+3;j++){
                candidate[sudoku[i][j]]=false; // 같은 구역에 있는 숫자 탐색 후 후보 제거
            }
        }

        for(int i=1;i<=9;i++){
            if(candidate[i])
                list.add(i);
        }

        return list;
    }
}
