package Graphs.Shortest_Path_Algorithms.P3_Network_Delay_Time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Network_Delay_Time {
    public static void main(String[] args) {
        Network_Delay_Time solution = new Network_Delay_Time();

        int[][] times = { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
        int n = 4;
        int k = 2;

        int delayTime = solution.networkDelayTime(times, n, k);
        System.out.println(delayTime);
    }

    /**
     * Using Dijkstra's Algorithm
     * 
     * TC: O((V + E) x log(V))
     * SC: O(V + E)
     * 
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // create adjacency list
        Map<Integer, ArrayList<int[]>> adj = new HashMap<Integer, ArrayList<int[]>>();
        for (int[] edge : times) { // TC: O(V + E), SC: O(V + E)
            adj.computeIfAbsent(edge[0],
                    p -> new ArrayList<int[]>()).add(new int[] { edge[1], edge[2] });
        }
        // as node starts from 1 to n
        int[] minDist = new int[n + 1]; // SC: O(V)
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[k] = 0;
        // creating min-heap to store (weight, node) - // SC: O(V)
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[1] - q[1]);
        pq.offer(new int[] { k, 0 });
        while (!pq.isEmpty()) { // TC: O(V)
            int[] current = pq.poll(); // TC: O(log(V))
            int u = current[0];
            int time = current[1];
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(E)
                int v = ngbr[0];
                int edgeTime = ngbr[1];
                if (time + edgeTime < minDist[v]) {
                    minDist[v] = time + edgeTime;
                    pq.offer(new int[] { v, time + edgeTime }); // TC: O(log(V))
                }
            }
        }
        int maxTime = 0;
        for (int i = 1; i <= n; i++) { // TC: O(V)
            maxTime = Math.max(maxTime, minDist[i]);
        }
        return maxTime == Integer.MAX_VALUE ? -1 : maxTime;
    }
}
