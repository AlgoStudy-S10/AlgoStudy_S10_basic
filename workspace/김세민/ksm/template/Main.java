package ksm.template;
import java.util.*;
import java.io.*;


public class Main {
    public static class Line{
        int weight;
        int next;
        Line(int weight, int next){
            this.weight = weight;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        ArrayList<Line>[] tree = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree[start].add(new Line(weight, end));
            tree[end].add(new Line(weight, start));
        }
        int answer = 0;
        int lastNode = bfs(tree, 1)[0];
        answer = bfs(tree, lastNode)[1];

        System.out.println(answer);
    }


    public static int[] bfs(ArrayList<Line>[] tree, int startNode) {
        Deque<Integer> q = new ArrayDeque<>();
        Deque<Integer> w = new ArrayDeque<>();

        q.offer(startNode);
        w.offer(0);
        int answer = 0;
        int lastNode = 1;
        boolean[] visited = new boolean[tree.length];
        visited[startNode] = true;

        while(!q.isEmpty()) {
            int nowNode = q.pollFirst();
            int nowWeight = w.pollFirst();
            for(int i=0; i<tree[nowNode].size(); i++) {
                if(!visited[tree[nowNode].get(i).next]) {
                    visited[tree[nowNode].get(i).next] = true;
                    q.offerLast(tree[nowNode].get(i).next);
                    w.offerLast(nowWeight+tree[nowNode].get(i).weight);
                    if(answer<nowWeight+tree[nowNode].get(i).weight) {
                        answer = nowWeight+tree[nowNode].get(i).weight;
                        lastNode = tree[nowNode].get(i).next;
                    }
                }
            }
        }
        return new int[] {lastNode, answer};
    }
}