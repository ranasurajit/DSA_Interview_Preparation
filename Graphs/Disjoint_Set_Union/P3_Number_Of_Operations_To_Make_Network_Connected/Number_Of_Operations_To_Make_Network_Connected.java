package Graphs.Disjoint_Set_Union.P3_Number_Of_Operations_To_Make_Network_Connected;

public class Number_Of_Operations_To_Make_Network_Connected {
    public static void main(String[] args) {
        Number_Of_Operations_To_Make_Network_Connected solution = new Number_Of_Operations_To_Make_Network_Connected();

        int n1 = 4;
        int[][] connections1 = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
        int connectionsNeeded1 = solution.makeConnected(n1, connections1);
        System.out.println(connectionsNeeded1);

        int n2 = 6;
        int[][] connections2 = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } };
        int connectionsNeeded2 = solution.makeConnected(n2, connections2);
        System.out.println(connectionsNeeded2);

        int n3 = 6;
        int[][] connections3 = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 2 } };
        int connectionsNeeded3 = solution.makeConnected(n3, connections3);
        System.out.println(connectionsNeeded3);
    }

    /**
     * Approach : Using Disjoint-Set (Union By Rank and Find By Path Compression)
     * 
     * TC: O(V) + O(E x α(V)) ~ O(V + E x α(V))
     * SC: O(V) + O(V) + O(V) ~ O(V)
     */
    public int makeConnected(int n, int[][] connections) {
        int e = connections.length;
        if (e < n - 1) {
            // we need atleast (V - 1) edges to connect V nodes / computers
            return -1;
        }
        /**
         * we will be using DSU Approach to solve this
         * this problem is similar to finding out disconnected components
         * which needs (disconected components - 1) edges to connect all
         */
        int[] parents = new int[n]; // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            parents[i] = i;
        }
        int[] rank = new int[n]; // SC: O(V)
        int components = n;
        for (int[] edge : connections) { // TC: O(E)
            int uParent = find(parents, edge[0]); // TC: O(α(V)), SC: O(V)
            int vParent = find(parents, edge[1]); // TC: O(α(V)), SC: O(V)
            if (uParent == vParent) {
                // having same parent
                continue;
            }
            unionByRank(uParent, vParent, parents, rank); // TC: O(1), SC: O(1)
            components--;
        }
        // so we have the count stored in components = number of disconnected components
        return components - 1;
    }

    /**
     * Using Find By Path Compression
     * 
     * TC: O(α(V))
     * SC: O(V)
     */
    private int find(int[] parents, int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents, parents[x]);
    }

    /**
     * Using Union By Rank
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
            // make anyone as parent increasing the rank of parent
            // make x as parent of y
            parents[y] = x;
            rank[x]++;
        }
    }
}
