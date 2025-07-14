package Graphs.DFS_BFS.P1_Number_Of_Provinces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Number_Of_Provinces {
    public static void main(String[] args) {

    }

    /**
     * Approach II : Using DFS Approach
     *
     * TC: O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(M x N) + O(N)
     */
    public int findCircleNumDFSMatrix(int[][] isConnected) {
        int m = isConnected.length;
        int n = isConnected[0].length;
        boolean[] visited = new boolean[n]; // SC: O(N)
        int provinces = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            if (!visited[i]) {
                dfsMatrixGraph(i, visited, isConnected, n); // TC: O(N ^ 2), SC: O(N)
                provinces++;
            }
        }
        return provinces;
    }

    /**
     * Using DFS Approach
     *
     * TC: O(N ^ 2)
     * SC: O(N)
     */
    private void dfsMatrixGraph(int u, boolean[] visited, int[][] isConnected, int n) {
        visited[u] = true;
        for (int v = 0; v < n; v++) { // TC: O(N)
            if (u != v && isConnected[u][v] == 1 && !visited[v]) {
                dfsMatrixGraph(v, visited, isConnected, n);
            }
        }
    }

    /**
     * Approach I : Using DFS Approach
     *
     * TC: O(N ^ 2) + O(N ^ 2) ~ O(N ^ 2)
     * SC: O(N ^ 2) + O(N)
     */
    public int findCircleNumDFSAdjList(int[][] isConnected) {
        int n = isConnected.length;
        Map<Integer, ArrayList<Integer>> adj = createGraph(isConnected, n); // TC: O(N ^ 2), SC: O(N ^ 2)
        boolean[] visited = new boolean[n]; // SC: O(N)
        int provinces = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (!visited[i]) {
                dfsGraph(i, visited, adj); // TC: O(N ^ 2), SC: O(N)
                provinces++;
            }
        }
        return provinces;
    }

    /**
     * Using DFS Approach
     *
     * TC: O(V + E) ~ O(N ^ 2)
     * SC: O(N)
     */
    private void dfsGraph(int u, boolean[] visited, Map<Integer, ArrayList<Integer>> adj) {
        visited[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v]) {
                dfsGraph(v, visited, adj);
            }
        }
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(N x N) ~ O(N ^ 2)
     * SC: O(N x N) ~ O(N ^ 2)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] isConnected, int n) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(N x N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (i != j && isConnected[i][j] == 1) {
                    adj.computeIfAbsent(i, k -> new ArrayList<Integer>()).add(j);
                    adj.computeIfAbsent(j, k -> new ArrayList<Integer>()).add(i);
                }
            }
        }
        return adj;
    }
}
