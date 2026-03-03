package ish.template;

import java.io.*;
import java.util.*;

public class Main { // BOJ 1916 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,M,a,b,c;
    static int[] dist;
    static List<List<Node>> adj = new ArrayList<>();

    static class Node implements Comparable<Node>{
        int to;
        int dist;
        Node(int to, int dist){
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    public static void main(String[] args) throws IOException{

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for(int i=0;i<=N;i++){
            adj.add(new ArrayList<>());
        }

        dist = new int[N+1];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            adj.get(a).add(new Node(b,c)); // 인접리스트
        }

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        Arrays.fill(dist,Integer.MAX_VALUE); // 초기화
        dist[a] = 0;

        // 다익스트라
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(a,0));

        while(!pq.isEmpty()){
            Node curr = pq.poll();
            int currTo = curr.to;
            int currD = curr.dist;

            if(dist[currTo]<currD) continue; // 이미 처리된 노드라면 건너뜀
            for(Node next: adj.get(currTo)){
                if(dist[next.to]>dist[currTo]+next.dist){
                    dist[next.to] = dist[currTo]+next.dist;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }        

        System.out.println(dist[b]);
    }


}
