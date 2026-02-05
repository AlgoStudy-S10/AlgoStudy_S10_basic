package lkh.template;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Map<Integer, List<Edge>> tree = new HashMap<>();
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        if (N == 1) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            tree.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, w));
            tree.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, w));
        }
        
        // 1. 루트 노드에서 가장 먼 노드 찾기
        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, 0);
        
        // 2. 1번에서 찾은 가장 먼 노드에서 다시 제일 먼 곳 찾기
        visited = new boolean[N + 1];
        visited[farthestNode] = true;
        maxDist = 0; // 거리 초기화
        dfs(farthestNode, 0);
        
        System.out.println(maxDist);
    }
    
    public static void dfs(int node, int currentDist) {
        if (currentDist > maxDist) {
            maxDist = currentDist;
            farthestNode = node;
        }
        
        if (!tree.containsKey(node)) return;

        for (Edge next : tree.get(node)) {
            if (!visited[next.to]) {
                visited[next.to] = true;
                dfs(next.to, currentDist + next.weight);
                // 돌아올 때 visited=false 안 해도 됨 (지름 구하기 2번 돌리니까)
            }
        }
    }
    
    static class Edge {
        int to, weight;
        
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}