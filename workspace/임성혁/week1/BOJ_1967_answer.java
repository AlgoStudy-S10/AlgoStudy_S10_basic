package week1;

import java.io.*;
import java.util.*;

public class BOJ_1967_answer { // 클래스명을 Main으로 변경해야 제출 시 오류가 나지 않습니다.
    static int N;
    static ArrayList<Node>[] list;
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;

    static class Node {
        int target, weight;
        Node(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N == 1) { // 노드가 1개일 예외 처리
            System.out.println(0);
            return;
        }

        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[parent].add(new Node(child, weight));
            list[child].add(new Node(parent, weight));
        }

        // 1. 임의의 노드(1)에서 가장 먼 노드를 찾음
        visited = new boolean[N + 1];
        dfs(1, 0);

        // 2. 찾은 가장 먼 노드(farthestNode)에서 다시 가장 먼 노드까지의 거리를 구함
        visited = new boolean[N + 1];
        maxDist = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int current, int dist) {
        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = current;
        }

        visited[current] = true;

        for (Node next : list[current]) {
            if (!visited[next.target]) {
                dfs(next.target, dist + next.weight);
            }
        }
    }
}