package Graphs.Shortest_Path_Algorithms.P2_Shortest_Path_In_Directed_Acyclic_Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Shortest_Path_In_Directed_Acyclic_Graph {
    public static void main(String[] args) {
        Shortest_Path_In_Directed_Acyclic_Graph solution = new Shortest_Path_In_Directed_Acyclic_Graph();

        int V1 = 4;
        int E1 = 2;
        int[][] edges1 = { { 0, 1, 2 }, { 0, 2, 1 } };

        int[] shortestPath1 = solution.shortestPath(V1, E1, edges1);
        System.out.println(Arrays.toString(shortestPath1));

        int V2 = 6;
        int E2 = 7;
        int[][] edges2 = { { 0, 1, 2 }, { 0, 4, 1 }, { 4, 5, 4 }, { 4, 2, 2 }, { 1, 2, 3 }, { 2, 3, 6 }, { 5, 3, 1 } };

        int[] shortestPath2 = solution.shortestPath(V2, E2, edges2);
        System.out.println(Arrays.toString(shortestPath2));
    }

    /**
     * Approach : Using Dijkstra's Algorithm Approach
     * 
     * TC: O(E) + O(E x log(V)) + O(V) ~ O(E x log(V))
     * SC: O(V + E) + O(V) + O(E) ~ O(V + E)
     */
    public int[] shortestPath(int V, int E, int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = createGraph(edges); // TC: O(E), SC: O(V + E)
        int[] minDist = new int[V]; // SC: O(V)
        Arrays.fill(minDist, (int) 1e9);
        int src = 0;
        minDist[src] = 0; // as 0 is the src
        // we will be storing <distance, node> in the Min-Heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        pq.offer(new int[] { 0, src }); // TC: O(1) as 0 element was present initially
        while (!pq.isEmpty()) { // TC: O(E)
            int[] current = pq.poll();
            int weight = current[0];
            int u = current[1];
            if (weight > minDist[u]) {
                // no shortest path can be achieved further
                continue;
            }
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (weight + edgeWeight < minDist[v]) {
                    minDist[v] = weight + edgeWeight;
                    pq.offer(new int[] { weight + edgeWeight, v }); // TC: O(log(V))
                }
            }
        }
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (minDist[i] == (int) 1e9) {
                minDist[i] = -1;
            }
        }
        return minDist;
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = new HashMap<Integer, ArrayList<int[]>>();
        for (int[] edge : edges) { // TC: O(E)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<int[]>()).add(new int[] { edge[1], edge[2] });
        }
        return adj;
    }
}
