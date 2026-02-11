package ksm.template;

import java.util.*;
import java.io.*;

public class Main {
    // 집의 좌표를 저장할 리스트
    static ArrayList<int[]> house;
    // 치킨집의 좌표를 저장할 리스트
    static ArrayList<int[]> store;
    // 선택된 M개의 치킨집 조합 인덱스를 저장할 리스트
    static ArrayList<ArrayList<Integer>> comb;
    // 각 집에서 가장 가까운 치킨집까지의 거리를 임시 저장할 배열
    static int[] distance;
    // 최종 정답 (도시의 치킨 거리 최솟값)
    static int answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N: 도시의 크기 (N x N), M: 폐업시키지 않고 남길 치킨집의 수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        house = new ArrayList<>();
        store = new ArrayList<>();
        comb = new ArrayList<ArrayList<Integer>>();

        // 도시 정보 입력 받기
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                switch(tmp) {
                    case 1:
                        // 1인 경우 집 리스트에 좌표 추가
                        house.add(new int[]{i, j});
                        break;
                    case 2:
                        // 2인 경우 치킨집 리스트에 좌표 추가
                        store.add(new int[]{i, j});
                    case 0:
                        // 0인 경우 빈 칸 (아무것도 안 함)
                        continue;
                }
            }
        }

        distance = new int[house.size()];
        answer = Integer.MAX_VALUE; // 최솟값을 구하기 위해 최대값으로 초기화

        // 전체 치킨집 중 M개를 뽑는 모든 조합 구하기 (백트래킹 시작)
        combination(0, M, new ArrayList<Integer>());

        // 구해진 각 조합(살려둘 M개의 치킨집 경우의 수)마다 반복
        for(int i=0; i<comb.size(); i++) {
            // 거리 계산 전, 각 집의 치킨 거리를 최대값으로 초기화
            for(int c=0; c<house.size(); c++) {
                distance[c] = Integer.MAX_VALUE;
            }

            // 현재 조합에 포함된 M개의 치킨집에 대해 반복
            for(int j=0; j<M; j++) {
                // 선택된 치킨집의 좌표
                int yStore = store.get(comb.get(i).get(j))[0];
                int xStore = store.get(comb.get(i).get(j))[1];

                // 모든 집을 순회하며 현재 선택된 치킨집과의 거리 계산
                for(int k=0; k<house.size(); k++) {
                    int yHouse = house.get(k)[0];
                    int XHouse = house.get(k)[1];

                    // 맨해튼 거리 계산: |r1 - r2| + |c1 - c2|
                    int dist = Math.abs(yStore-yHouse) + Math.abs(xStore-XHouse);

                    // 해당 집에서 가장 가까운 치킨 거리로 갱신 (기존 값과 비교하여 더 작은 값 선택)
                    distance[k] = Math.min(distance[k], dist);
                }
            }

            // 현재 조합에서의 '도시의 치킨 거리' 구하기 (모든 집의 치킨 거리 합)
            int tmp = 0;
            for(int s=0; s<house.size(); s++) {
                tmp+= distance[s];
            }

            // 전체 조합 중 가장 작은 '도시의 치킨 거리'를 정답으로 갱신
            answer = Math.min(answer, tmp);
        }
        System.out.println(answer);
    }


    // 백트래킹을 이용한 조합(Combination) 구현 함수
    // start: 탐색 시작 인덱스, n: 더 뽑아야 할 개수, now: 현재 뽑힌 인덱스 리스트
    static void combination(int start, int n, ArrayList<Integer> now) {
        // M개를 모두 뽑았으면 결과 리스트(comb)에 추가하고 종료
        if (n == 0) {
            comb.add(new ArrayList<>(now));
            return;
        }

        // 모든 치킨집을 다 탐색했으면 종료
        if (start == store.size()) {
            return;
        }

        // 1. 현재 치킨집(start)을 선택하는 경우
        now.add(start);
        combination(start + 1, n - 1, now);

        // 백트래킹 (상태 복구): 방금 추가한 치킨집을 제거
        now.remove(now.size() - 1);

        // 2. 현재 치킨집(start)을 선택하지 않고 다음으로 넘어가는 경우
        combination(start + 1, n, now);
    }
}