package jji.template;

import java.io.*;
import java.util.*;

public class Main {
	
	static class Bus {
		int to;
		int cost;
		public Bus(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine().trim());
		int M = Integer.parseInt(br.readLine().trim());
		
		List<List<Bus>> city = new ArrayList<>(N+1);
		
		for (int i=0; i<=N;i++) {
			city.add(new ArrayList<>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<M; i++) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			city.get(from).add(new Bus(to, cost));
			
			st = new StringTokenizer(br.readLine());
		}
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 우선순위큐. 거리가 작은게 먼저나옴.
		PriorityQueue<Bus> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
		
		// 일단 최댓값으로 채우기
		int[] totalcost = new int[N+1];
		Arrays.fill(totalcost, Integer.MAX_VALUE);
		
		// 시작도시는 거리 0으로 설정해서 시작
		pq.offer(new Bus(start,0));
		totalcost[start] = 0;
		
		while (!pq.isEmpty()) {
			Bus cur = pq.poll();
			int now = cur.to;
			int cost = cur.cost;
			
			// 지금 값보다 작으면 굳이 볼 필요 없다
			if (totalcost[now] < cost) {
				continue;
			}
			
			// 지금 목적지인 도시에서 갈 수 있는 버스들 확인 후, 기존 비용과 이 도시를 들렸다 가는 비용을 비교하여 더 작은값으로 업데이트
			for (Bus next : city.get(now)) {
				// cost는 항상 이 도시까지의 최소 비용을 담게된다.
				int newCost = cost + next.cost;
				
				// 이 도시를 들렸다 가는 비용이 기존의 다른 경로로 온 비용 또는 초깃값(INF)보다 작은지 확인 후 갱신
				if (totalcost[next.to] > newCost) {
					totalcost[next.to] = newCost;
					
					// 이 과정을 통해 항상 start에서 목표도시까지의 최소 거리가 우선순위큐에 들어간다.
					pq.offer(new Bus(next.to, newCost));
					
				}
			}
		}
		
		// 완성된 다익스트라에서 목표도시까지의 최소거리 출력
		System.out.println(totalcost[end]);
	}
}