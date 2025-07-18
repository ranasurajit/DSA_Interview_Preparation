package Graphs.Shortest_Path_Algorithms.P5_Network_Delay_Time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Network_Delay_Time {
    public static void main(String[] args) {
        Network_Delay_Time solution = new Network_Delay_Time();

        int[][] times1 = { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
        int n1 = 4;
        int k1 = 2;

        int delayTime1 = solution.networkDelayTime(times1, n1, k1);
        System.out.println(delayTime1);

        int[][] times2 = { { 1, 2, 1 } };
        int n2 = 2;
        int k2 = 1;
        int delayTime2 = solution.networkDelayTime(times2, n2, k2);
        System.out.println(delayTime2);
    }

    /**
     * Approach : Using Dijkstra's Algorithm Approach
     *
     * TC: O(E) + O(E x log(V)) + O(V) ~ O(E x log(V))
     * SC: O(V + E) + O(V) + O(E) ~ O(V + E)
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, ArrayList<int[]>> adj = createGraph(times); // TC: O(E), SC: O(V + E)
        int[] minDist = new int[n + 1]; // SC: O(V)
        Arrays.fill(minDist, (int) 1e9);
        minDist[k] = 0; // as k node is the source
        // we will store { weight, node } in Min-Heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        pq.offer(new int[] { 0, k });
        while (!pq.isEmpty()) { // TC: O(E)
            int[] current = pq.poll();
            int weight = current[0];
            int u = current[1];
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (weight + edgeWeight < minDist[v]) {
                    minDist[v] = weight + edgeWeight;
                    pq.offer(new int[] { weight + edgeWeight, v }); // TC: O(log(V))
                }
            }
        }
        int maxTime = 0;
        for (int i = 1; i <= n; i++) { // TC: O(V)
            maxTime = Math.max(maxTime, minDist[i]);
        }
        return maxTime == (int) 1e9 ? -1 : maxTime;
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] times) {
        Map<Integer, ArrayList<int[]>> adj = new HashMap<Integer, ArrayList<int[]>>();
        for (int[] edge : times) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<int[]>())
                    .add(new int[] { edge[1], edge[2] });
        }
        return adj;
    }
}
