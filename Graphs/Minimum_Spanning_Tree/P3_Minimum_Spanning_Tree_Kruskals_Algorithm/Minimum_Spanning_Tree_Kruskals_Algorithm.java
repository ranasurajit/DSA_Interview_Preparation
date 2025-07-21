package Graphs.Minimum_Spanning_Tree.P3_Minimum_Spanning_Tree_Kruskals_Algorithm;

import java.util.Arrays;

public class Minimum_Spanning_Tree_Kruskals_Algorithm {
    public static void main(String[] args) {
        Minimum_Spanning_Tree_Kruskals_Algorithm solution = new Minimum_Spanning_Tree_Kruskals_Algorithm();

        int V1 = 3;
        int[][] edges1 = { { 0, 1, 5 }, { 1, 2, 3 }, { 0, 2, 1 } };
        int mstSum1 = solution.kruskalsMST(V1, edges1);
        System.out.println(mstSum1);

        int V2 = 2;
        int[][] edges2 = { { 0, 1, 5 } };
        int mstSum2 = solution.kruskalsMST(V2, edges2);
        System.out.println(mstSum2);
    }

    /**
     * Approach : Using Kruskal's Algorithm (MST) Approach
     * 
     * TC: O(E x log(E)) + O(V) + TC: O(2 x E x α(V)) ~ O(V + E x log(E) + E x α(V))
     * SC: O(V) + O(E) + O(V) ~ O(V + E)
     */
    private int kruskalsMST(int V, int[][] edges) {
        Arrays.sort(edges, (a, b) -> a[2] - b[2]); // TC: O(E x log(E))
        int[] parents = new int[V]; // SC: O(V)
        for (int i = 0; i < V; i++) { // TC: O(V)
            parents[i] = i;
        }
        int[] rank = new int[V]; // SC: O(V)
        int sumMST = 0;
        for (int[] edge : edges) { // TC: O(E)
            int uParent = find(parents, edge[0]); // TC: O(α(V)), SC: O(V)
            int vParent = find(parents, edge[1]); // TC: O(α(V)), SC: O(V)
            if (uParent == vParent) {
                continue;
            }
            sumMST += edge[2];
            unionByRank(uParent, vParent, parents, rank); // TC: O(1)
        }
        return sumMST;
    }

    /**
     * Using Find By Path Compression Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private void unionByRank(int x, int y, int[] parents, int[] rank) {
        if (rank[x] > rank[y]) {
            // make x as parent of y
            parents[y] = x;
        } else if (rank[y] > rank[x]) {
            // make y as parent of x
            parents[x] = y;
        } else {
            // make anyone as parent increasing it's rank
            // make x as parent of y
            parents[y] = x;
            rank[x]++;
        }
    }

    /**
     * Using Find By Path Compression Approach
     * 
     * TC: O(α(V))
     * SC: O(V)
     */
    private int find(int[] parents, int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents, parents[x]);
    }
}
