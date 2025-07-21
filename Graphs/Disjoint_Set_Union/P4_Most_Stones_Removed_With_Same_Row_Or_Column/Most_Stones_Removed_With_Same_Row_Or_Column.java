package Graphs.Disjoint_Set_Union.P4_Most_Stones_Removed_With_Same_Row_Or_Column;

public class Most_Stones_Removed_With_Same_Row_Or_Column {
    public static void main(String[] args) {
        Most_Stones_Removed_With_Same_Row_Or_Column solution = new Most_Stones_Removed_With_Same_Row_Or_Column();

        int[][] stones1 = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 2, 2 } };
        int removedStones1 = solution.removeStonesUsingDFS(stones1);
        System.out.println(removedStones1);

        int[][] stones2 = { { 0, 0 }, { 0, 2 }, { 1, 1 }, { 2, 0 }, { 2, 2 } };
        int removedStones2 = solution.removeStonesUsingDSU(stones2);
        System.out.println(removedStones2);
    }

    /**
     * Approach II : Using Disjoint-Set (Union By Rank and Find By Path Compression)
     * 
     * TC: O(N) + O(N x N x α(N)) + O(N) ~ O(N x N x α(N))
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public int removeStonesUsingDSU(int[][] stones) {
        int n = stones.length;
        int[] parents = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            parents[i] = i;
        }
        int[] rank = new int[n]; // SC: O(N)
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                // check if two stones share the same row/column
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    unionByRank(i, j, parents, rank); // TC: O(α(N)), SC: O(N)
                }
            }
        }
        int components = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i == parents[i]) {
                components++;
            }
        }
        return n - components;
    }

    /**
     * Using Find By Path Compression Approach
     * 
     * TC: O(α(N))
     * SC: O(N)
     */
    private void unionByRank(int x, int y, int[] parents, int[] rank) {
        int xParent = find(parents, x); // TC: O(α(N)), SC: O(N)
        int yParent = find(parents, y); // TC: O(α(N)), SC: O(N)
        if (xParent == yParent) {
            return;
        }
        if (rank[x] > rank[y]) {
            // make x as parent of y
            parents[yParent] = xParent;
        } else if (rank[y] > rank[x]) {
            // make y as parent of x
            parents[xParent] = yParent;
        } else {
            // make anyone as parent increasing it's rank
            // make x as parent of y
            parents[yParent] = xParent;
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

    /**
     * Approach I : Using DFS Approach
     *
     * TC: O(N x N) ~ O(N ^ 2)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int removeStonesUsingDFS(int[][] stones) {
        int n = stones.length;
        boolean[] visited = new boolean[n]; // SC: O(N)
        int components = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (visited[i]) {
                continue;
            }
            dfsGraph(i, n, visited, stones); // TC: O(N), SC: O(N)
            components++;
        }
        return n - components;
    }

    /**
     * Using DFS Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private void dfsGraph(int index, int n, boolean[] visited, int[][] stones) {
        visited[index] = true;
        int row = stones[index][0];
        int col = stones[index][1];
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (!visited[i] && (stones[i][0] == row || stones[i][1] == col)) {
                dfsGraph(i, n, visited, stones);
            }
        }
    }
}
