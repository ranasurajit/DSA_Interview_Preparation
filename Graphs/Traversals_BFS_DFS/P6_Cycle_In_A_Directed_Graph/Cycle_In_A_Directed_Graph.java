package Graphs.Traversals_BFS_DFS.P6_Cycle_In_A_Directed_Graph;

import java.util.ArrayList;
import java.util.HashMap;

public class Cycle_In_A_Directed_Graph {
    public static void main(String[] args) {
        Cycle_In_A_Directed_Graph solution = new Cycle_In_A_Directed_Graph();

        int V = 5;
        int[][] edges = { { 0, 1 }, { 3, 0 }, { 1, 3 }, { 2, 3 }, { 4, 1 }, { 0, 2 } };
        boolean hasCycleDFS = solution.isCyclic(V, edges);

        System.out.println(hasCycleDFS);
    }

    // Function to detect cycle in a directed graph.
    /**
     * Using DFS Approach
     *
     * TC: O(2 x V + E) ~ O(V + E)
     * SC: O(V)
     */
    public boolean isCyclic(int V, int[][] edges) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
        }

        boolean[] visited = new boolean[V]; // SC: O(V)
        boolean[] inRecursion = new boolean[V]; // SC: O(V)

        for (int i = 0; i < V; i++) { // TC: O(V)
            if (!visited[i] && hasCycleDFS(i, adj, visited, inRecursion)) {
                return true; // TC: O(V + E), SC: O(V)
            }
        }
        return false;
    }

    /**
     * TC: O(V + E)
     * SC: O(V)
     */
    private static boolean hasCycleDFS(int u, HashMap<Integer, ArrayList<Integer>> adj,
            boolean[] visited, boolean[] inRecursion) {
        visited[u] = true;
        inRecursion[u] = true;
        for (Integer v : adj.get(u)) {
            if (!visited[v] && hasCycleDFS(v, adj, visited, inRecursion)) {
                return true;
            } else if (inRecursion[v]) {
                return true;
            }
        }
        inRecursion[u] = false;
        return false;
    }
}
