package Graphs.Traversals_BFS_DFS.P1_Number_Of_Provinces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Number_Of_Provinces {
    public static void main(String[] args) {
        int[][] isConnected = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };

        int provincesAdj = findCircleNumWithAdjacencyList(isConnected);
        System.out.println(provincesAdj);

        int provinces = findCircleNum(isConnected);
        System.out.println(provinces);
    }

    /**
     * TC: O(2 x (V + E)) ~ O(V + E)
     * SC: O(2 x V) ~ O(V)
     */
    public static int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        int n = isConnected[0].length;
        boolean[] visited = new boolean[m]; // SC: O(V)
        int provinces = 0;
        for (int i = 0; i < m; i++) { // TC: O(V)
            if (!visited[i]) {
                dfs(i, visited, isConnected, n); // TC: O(V + 2 x E), SC: O(V)
                provinces++;
            }
        }
        return provinces;
    }

    /**
     * TC: O(V + 2E)
     * SC: O(V)
     */
    private static void dfs(int u, boolean[] visited, int[][] isConnected, int n) {
        visited[u] = true;
        for (int v = 0; v < n; v++) { // TC: O(N)
            if (v != u && isConnected[u][v] == 1 && !visited[v]) {
                dfs(v, visited, isConnected, n);
            }
        }
    }

    /**
     * TC: O(V x V + V x E) ~ O(V x (V + E))
     * SC: O(2 x V) ~ O(V)
     */
    public static int findCircleNumWithAdjacencyList(int[][] isConnected) {
        int n = isConnected.length;
        // TC: O(V ^ 2), SC: O(V)
        Map<Integer, ArrayList<Integer>> adj = createGraph(isConnected, n);
        boolean[] visited = new boolean[n]; // SC: O(V)
        int provinces = 0;
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (!visited[i]) {
                dfsGraph(i, visited, adj); // TC: O(E)
                provinces++;
            }
        }
        return provinces;
    }

    /**
     * TC: O(E)
     * SC: O(E)
     */
    private static void dfsGraph(int u, boolean[] visited, Map<Integer, ArrayList<Integer>> adj) {
        visited[u] = true;
        for (Integer v : adj.get(u)) {
            if (!visited[v]) {
                dfsGraph(v, visited, adj);
            }
        }
    }

    /**
     * TC: O(V ^ 2 + V) ~ O(V ^ 2)
     * SC: O(V)
     */
    private static Map<Integer, ArrayList<Integer>> createGraph(int[][] isConnected, int n) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < n; i++) { // TC: O(V)
            adj.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < n; i++) { // TC: O(V)
            for (int j = 0; j < n; j++) { // TC: O(V)
                if (i != j && isConnected[i][j] == 1) {
                    adj.get(i).add(j);
                }
            }
        }
        return adj;
    }
}
