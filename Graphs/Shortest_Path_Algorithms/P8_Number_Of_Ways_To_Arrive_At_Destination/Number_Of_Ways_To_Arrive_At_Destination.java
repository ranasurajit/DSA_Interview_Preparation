package Graphs.Shortest_Path_Algorithms.P8_Number_Of_Ways_To_Arrive_At_Destination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Number_Of_Ways_To_Arrive_At_Destination {
    private static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        Number_Of_Ways_To_Arrive_At_Destination solution = new Number_Of_Ways_To_Arrive_At_Destination();

        int n1 = 7;
        int[][] roads1 = { { 0, 6, 7 }, { 0, 1, 2 }, { 1, 2, 3 }, { 1, 3, 3 }, { 6, 3, 3 }, { 3, 5, 1 }, { 6, 5, 1 },
                { 2, 5, 1 }, { 0, 4, 5 }, { 4, 6, 2 } };
        int countPaths1 = solution.countPaths(n1, roads1);
        System.out.println(countPaths1);

        int n2 = 2;
        int[][] roads2 = { { 1, 0, 10 } };
        int countPaths2 = solution.countPaths(n2, roads2);
        System.out.println(countPaths2);
    }

    /**
     * Approach : Using Dijkstra's Algorithm Approach
     *
     * TC: O(2 x E) + O(4 x E x log(E)) ~ O(E x log(V))
     * SC: O(V + E) + O(2 x E) + O(V) ~ O(V + E)
     */
    public int countPaths(int n, int[][] roads) {
        Map<Integer, ArrayList<int[]>> adj = createGraph(roads); // TC: O(2 x E), SC: O(V + E)
        int src = 0;
        int dest = n - 1;
        long[] minDuration = new long[n]; // SC: O(V)
        Arrays.fill(minDuration, Long.MAX_VALUE);
        minDuration[src] = 0;
        // we will be storing { duration, node } in the Min-Heap
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>((p, q) -> Long.compare(p[0], q[0])); // SC: O(2 x E)
        pq.offer(new long[] { 0, src });
        int[] countMinPaths = new int[n]; // SC: O(V)
        countMinPaths[0] = 1;
        while (!pq.isEmpty()) { // TC: O(2 x E)
            long[] current = pq.poll();
            long duration = current[0];
            int u = (int) current[1];
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(2 x E)
                int v = ngbr[0];
                long edgeDuration = ngbr[1];
                if (duration + edgeDuration < minDuration[v]) {
                    minDuration[v] = duration + edgeDuration;
                    pq.offer(new long[] { duration + edgeDuration, v }); // TC: O(log(V))
                    countMinPaths[v] = countMinPaths[u] % MOD;
                } else if (duration + edgeDuration == minDuration[v]) {
                    countMinPaths[v] = (countMinPaths[v] + countMinPaths[u] % MOD) % MOD;
                }
            }
        }
        return countMinPaths[dest] % MOD;
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(2 x E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] roads) {
        Map<Integer, ArrayList<int[]>> adj = new HashMap<Integer, ArrayList<int[]>>();
        for (int[] edge : roads) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<int[]>())
                    .add(new int[] { edge[1], edge[2] });
            adj.computeIfAbsent(edge[1], k -> new ArrayList<int[]>())
                    .add(new int[] { edge[0], edge[2] });
        }
        return adj;
    }
}
