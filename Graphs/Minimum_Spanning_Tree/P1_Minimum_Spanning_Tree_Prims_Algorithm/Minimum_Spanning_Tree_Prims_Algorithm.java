package Graphs.Minimum_Spanning_Tree.P1_Minimum_Spanning_Tree_Prims_Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Minimum_Spanning_Tree_Prims_Algorithm {
    public static void main(String[] args) {
        Minimum_Spanning_Tree_Prims_Algorithm solution = new Minimum_Spanning_Tree_Prims_Algorithm();

        int V = 3;
        int E = 3;
        int[][] edges = { { 0, 1, 5 }, { 1, 2, 3 }, { 0, 2, 1 } };

        List<List<int[]>> adj = solution.createGraph(V, edges);
        int minimumSum = solution.spanningTree(V, E, adj);
        System.out.println(minimumSum);
    }

    /**
     * Approach : Using Prim's Algorithm Approach
     * 
     * TC: O(V) + O(E x log(V)) ~ O(E x log(V))
     * SC: O(V) + O(E)
     */
    private int spanningTree(int V, int E, List<List<int[]>> adj) {
        boolean[] inMST = new boolean[V]; // SC: O(V)
        // we will be storing { weight, node, parent (optional) } in the Min-Heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        int src = -1;
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (!inMST[i]) {
                src = i;
                break;
            }
        }
        pq.offer(new int[] { 0, src }); // distance from src to src = 0
        int sum = 0;
        while (!pq.isEmpty()) { // TC: O(E)
            int[] current = pq.poll();
            int weight = current[0];
            int u = current[1];
            if (inMST[u]) {
                continue;
            }
            inMST[u] = true;
            sum += weight;
            for (int[] ngbr : adj.get(u)) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (!inMST[v]) {
                    pq.offer(new int[] { edgeWeight, v }); // TC: O(log(V))
                }
            }
        }
        return sum;
    }

    private List<List<int[]>> createGraph(int V, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        // Initialize the adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        // Populate the adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adj.get(u).add(new int[] { v, wt });
            adj.get(v).add(new int[] { u, wt }); // since the graph is undirected
        }
        return adj;
    }
}
