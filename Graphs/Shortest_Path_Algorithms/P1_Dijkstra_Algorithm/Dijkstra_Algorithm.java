package Graphs.Shortest_Path_Algorithms.P1_Dijkstra_Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra_Algorithm {
    public static void main(String[] args) {
        Dijkstra_Algorithm solution = new Dijkstra_Algorithm();

        int V1 = 3;
        int[][] edges1 = { { 0, 1, 1 }, { 1, 2, 3 }, { 0, 2, 6 } };
        int src1 = 2;

        int[] shortestPath1 = solution.dijkstra(V1, edges1, src1);
        System.out.println(Arrays.toString(shortestPath1));

        int V2 = 5;
        int[][] edges2 = { { 0, 1, 4 }, { 0, 2, 8 }, { 1, 4, 6 }, { 2, 3, 2 }, { 3, 4, 10 } };
        int src2 = 0;
        int[] shortestPath2 = solution.dijkstra(V2, edges2, src2);
        System.out.println(Arrays.toString(shortestPath2));
    }

    /**
     * Using Dijkstra's Algorithm Approach
     * 
     * TC: O(E) + O(E x log(V)) ~ O(E x log(V))
     * SC: O(V + E) + O(V) + O(E) ~ O(V + E)
     */
    public int[] dijkstra(int V, int[][] edges, int src) {
        Map<Integer, ArrayList<int[]>> adj = createGraph(edges); // TC: O(E), SC: O(V + E)
        int[] minDist = new int[V]; // SC: O(V)
        Arrays.fill(minDist, (int) 1e9);
        minDist[src] = 0; // distance from src to src = 0
        // we will be storing <distance, node> in the Min-Heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        pq.offer(new int[] { 0, src }); // TC: O(1)) for 1st entry
        while (!pq.isEmpty()) { // TC: O(E)
            int[] current = pq.poll();
            int w = current[0];
            int u = current[1];
            if (w > minDist[u]) {
                continue;
            }
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (edgeWeight + w < minDist[v]) {
                    minDist[v] = edgeWeight + w;
                    pq.offer(new int[] { edgeWeight + w, v }); // TC: O(log(V))
                }
            }
        }
        return minDist;
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(2 x E) ~ O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = new HashMap<Integer, ArrayList<int[]>>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<int[]>()).add(new int[] { edge[1], edge[2] });
            adj.computeIfAbsent(edge[1], k -> new ArrayList<int[]>()).add(new int[] { edge[0], edge[2] });
        }
        return adj;
    }
}
