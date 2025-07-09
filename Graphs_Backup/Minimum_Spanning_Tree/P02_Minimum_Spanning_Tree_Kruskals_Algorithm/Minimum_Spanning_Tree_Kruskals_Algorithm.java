package Graphs_Backup.Minimum_Spanning_Tree.P02_Minimum_Spanning_Tree_Kruskals_Algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Minimum_Spanning_Tree_Kruskals_Algorithm {
    public static void main(String[] args) {
        int V = 3;
        int E = 3;
        int[][] edges = { { 0, 1, 5 }, { 1, 2, 3 }, { 0, 2, 1 } };

        Minimum_Spanning_Tree_Kruskals_Algorithm solution = new Minimum_Spanning_Tree_Kruskals_Algorithm();
        List<List<int[]>> adj = solution.createGraph(V, edges);
        int minimumSum = solution.spanningTree(V, E, adj);
        System.out.println(minimumSum);
    }

    /**
     * Approach : Kruskal's Algorithm Approach
     * 
     * TC: O(E + V + E + E x log(E) + 3 x E x α(V)) ~ O(E x log(E) + E x α(V))
     * SC: O(2 x V + E) ~ O(V + E)
     */
    private int spanningTree(int V, int E, List<List<int[]>> adj) {
        int[] parent = new int[V]; // SC: O(V)
        int[] rank = new int[V]; // SC: O(V)
        List<int[]> edges = new ArrayList<int[]>(); // SC: O(E)
        for (int i = 0; i < V; i++) { // TC: O(V)
            for (int[] ngbr : adj.get(i)) { // TC: O(E)
                edges.add(new int[] { i, ngbr[0], ngbr[1] });
            }
            parent[i] = i;
            rank[i] = 1;
        }
        // Sorting edges in ascending order of edgeWeights
        edges.sort(Comparator.comparingInt(a -> a[2])); // TC: O(E x log(E))
        // iterating over edges
        int sumWeights = 0;
        for (int[] edge : edges) { // TC: O(E)
            if (find(edge[0], parent) != find(edge[1], parent)) { // TC: O(2 x α(V))
                unionByRank(edge[0], edge[1], parent, rank); // TC: O(α(V))
                sumWeights += edge[2];
            }
        }
        return sumWeights;
    }

    /**
     * Using DSU Approach - Find by Path Compression
     * 
     * TC: O(α(V))
     * SC: O(V)
     */
    private int find(int x, int[] parent) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x], parent);
    }

    /**
     * Using DSU Approach - Union By Rank
     * 
     * TC: O(2 x α(V)) ~ O(α(V))
     * SC: O(V)
     */
    private void unionByRank(int x, int y, int[] parent, int[] rank) {
        int xParent = find(x, parent); // TC: O(α(V)), SC: O(V)
        int yParent = find(y, parent); // TC: O(α(V)), SC: O(V)
        if (xParent == yParent) {
            return;
        }
        if (rank[xParent] > rank[yParent]) {
            // make x parent of y
            parent[yParent] = xParent;
        } else if (rank[xParent] < rank[yParent]) {
            // make y parent of x
            parent[xParent] = yParent;
        } else {
            // make x parent of y
            parent[yParent] = xParent;
            rank[x]++;
        }
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
