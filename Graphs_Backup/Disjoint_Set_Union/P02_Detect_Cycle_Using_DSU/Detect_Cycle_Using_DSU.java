package Graphs_Backup.Disjoint_Set_Union.P02_Detect_Cycle_Using_DSU;

import java.util.ArrayList;
import java.util.Arrays;

public class Detect_Cycle_Using_DSU {
    public static void main(String[] args) {
        Detect_Cycle_Using_DSU solution = new Detect_Cycle_Using_DSU();

        int V = 5;
        int[][] graph = { { 2, 3, 4 }, { 3 }, { 0, 4 }, { 0, 1 }, { 0, 2 } };

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < graph.length; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                adj.get(i).add(graph[i][j]);
            }
        }
        boolean hasCycle = solution.detectCycle(V, adj) == 1;
        System.out.println(hasCycle);
    }

    // Function to detect cycle using DSU in an undirected graph.
    /**
     * DSU - Union By Rank and Path Compression Approach
     * 
     * TC: O(3 x V + E) ~ O(V + E)
     * SC: O(3 x V) ~ O(V)
     * 
     * @param V
     * @param adj
     * @return
     */
    public int detectCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] parent = new int[V]; // SC: O(V)
        for (int i = 0; i < V; i++) { // TC: O(V)
            parent[i] = i;
        }
        int[] rank = new int[V]; // SC: O(V)
        Arrays.fill(rank, 1);

        // iterating over the adjacency list
        for (int u = 0; u < V; u++) { // TC: O(V)
            for (Integer v : adj.get(u)) { // TC: O(E)
                if (u < v) {
                    int uParent = find(parent, u); // TC: O(V), SC: O(1)
                    int vParent = find(parent, v); // TC: O(V), SC: O(1)
                    if (uParent == vParent) {
                        return 1;
                    } else {
                        union(parent, rank, u, v); // TC: O(1), SC: O(1)
                    }
                }
            }
        }
        return 0;
    }

    /**
     * DSU - Find Path by Compression Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param parent
     * @param x
     * @return
     */
    private int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }

    /**
     * DSU - Union By Rank Approach
     * TC: O(1)
     * SC: O(1)
     * 
     * @param parent
     * @param rank
     * @param x
     * @param y
     */
    private void union(int[] parent, int[] rank, int x, int y) {
        int xParent = find(parent, x);
        int yParent = find(parent, y);
        if (xParent == yParent) {
            // the nodes are already in same set so do nothing
            return;
        }
        if (rank[x] > rank[y]) {
            // make x as parent of y
            parent[yParent] = xParent;
        } else if (rank[y] > rank[x]) {
            // make y as parent of x
            parent[xParent] = yParent;
        } else {
            // make any of them as parent incrementing its rank
            parent[yParent] = xParent;
            rank[x]++;
        }
    }
}
