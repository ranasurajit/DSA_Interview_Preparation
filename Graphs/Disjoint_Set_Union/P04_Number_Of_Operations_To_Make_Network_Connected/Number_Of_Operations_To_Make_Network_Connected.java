package Graphs.Disjoint_Set_Union.P04_Number_Of_Operations_To_Make_Network_Connected;

import java.util.Arrays;

public class Number_Of_Operations_To_Make_Network_Connected {
    public static void main(String[] args) {
        Number_Of_Operations_To_Make_Network_Connected solution = new Number_Of_Operations_To_Make_Network_Connected();
        int n = 6;
        int[][] connections = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } };

        int minEdges = solution.makeConnected(n, connections);
        System.out.println(minEdges);
    }

    /**
     * Using DSU Approach
     *
     * TC: O(2 x V + E) ~ O(V + E)
     * SC: O(3 x V) ~ O(V)
     * 
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected(int n, int[][] connections) {
        int k = connections.length;
        if (k < n - 1) {
            // noway we can have all computers connected
            return -1;
        }
        int[] rank = new int[n]; // SC: O(V)
        Arrays.fill(rank, 1);
        int[] parent = new int[n]; // SC: O(V)
        // initially all nodes are parent itself
        for (int i = 0; i < n; i++) { // TC: O(V)
            parent[i] = i;
        }
        int[] components = { n };
        for (int[] edge : connections) { // TC: O(E)
            // create connections between edge nodes and decrease components
            union(parent, rank, edge[0], edge[1], components); // TC: O(V), SC: O(V)
        }
        // number of edges needed to alter/add
        return components[0] - 1;
    }

    /**
     * DSU - Find Path by Compression Approach
     * 
     * TC: O(V)
     * SC: O(V)
     * 
     * @param parent
     * @param x
     * @return
     */
    private int find(int[] parent, int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }

    /**
     * DSU - Union By Rank Approach
     *
     * TC: O(V)
     * SC: O(V)
     * 
     * @param parent
     * @param rank
     * @param x
     * @param y
     */
    private void union(int[] parent, int[] rank, int x, int y, int[] components) {
        int xParent = find(parent, x); // TC: O(V)
        int yParent = find(parent, y); // TC: O(V)
        if (xParent == yParent) {
            // nodes are already in same set so no need to do anything
            return;
        }
        if (rank[xParent] > rank[yParent]) {
            // make x as parent of y
            parent[yParent] = xParent;
        } else if (rank[xParent] < rank[yParent]) {
            // make y as parent of x
            parent[xParent] = yParent;
        } else {
            // make anyone as parent and increase it's rank
            parent[yParent] = xParent;
            rank[xParent]++;
        }
        components[0]--;
    }
}
