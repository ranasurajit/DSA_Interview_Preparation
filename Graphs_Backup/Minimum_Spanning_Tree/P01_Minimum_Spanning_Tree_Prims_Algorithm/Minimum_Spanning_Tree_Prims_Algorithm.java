package Graphs_Backup.Minimum_Spanning_Tree.P01_Minimum_Spanning_Tree_Prims_Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Minimum_Spanning_Tree_Prims_Algorithm {
    public static void main(String[] args) {
        int V = 3;
        int E = 3;
        int[][] edges = { { 0, 1, 5 }, { 1, 2, 3 }, { 0, 2, 1 } };

        Minimum_Spanning_Tree_Prims_Algorithm solution = new Minimum_Spanning_Tree_Prims_Algorithm();
        List<List<int[]>> adj = solution.createGraph(V, edges);
        int minimumSum = solution.spanningTree(V, E, adj);
        System.out.println(minimumSum);
    }

    /**
     * Approach : Prim's Algorithm Approach
     * 
     * TC: O(2 x E x log(V)) ~ O(E x log(V))
     * SC: O(2 x V + E) ~ O(V + E)
     */
    private int spanningTree(int V, int E, List<List<int[]>> adj) {
        boolean[] visited = new boolean[V]; // SC: O(V)
        int[] parent = new int[V]; // SC: O(V)
        Arrays.fill(parent, -1);
        // we will be storing int[] { weight, node, parent } in Min-Heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        pq.offer(new int[] { 0, 0, -1 }); // TC: O(log(V))
        int sumWeights = 0;
        while (!pq.isEmpty()) { // TC: O(E)
            int[] current = pq.poll();
            int w = current[0];
            int u = current[1];
            int par = current[2];
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            parent[u] = par;
            sumWeights += w;
            for (int[] ngbr : adj.get(u)) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (!visited[v]) {
                    pq.offer(new int[] { edgeWeight, v, u }); // TC: O(log(V))
                }
            }
        }
        System.out.println("Minimum Spanning Tree : " + Arrays.toString(parent));
        return sumWeights;
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
