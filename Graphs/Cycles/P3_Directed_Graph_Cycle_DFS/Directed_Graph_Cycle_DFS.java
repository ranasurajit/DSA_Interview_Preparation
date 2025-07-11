package Graphs.Cycles.P3_Directed_Graph_Cycle_DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Directed_Graph_Cycle_DFS {
    public static void main(String[] args) {
        Directed_Graph_Cycle_DFS solution = new Directed_Graph_Cycle_DFS();

        int V = 4;
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 2, 0 }, { 2, 3 } };
        boolean hasCycleDFS = solution.isCyclic(V, edges);
        System.out.println(hasCycleDFS);
    }

    /**
     * Approach : Using DFS Approach
     * 
     * TC: O(E) + O(V + E) ~ O(V + E)
     * SC: O(V + E) + O(V) + O(V) + O(V) ~ O(V + E)
     */
    public boolean isCyclic(int V, int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = createGraph(edges); // TC: O(E), SC: O(V + E)
        boolean[] visited = new boolean[V]; // SC: O(V)
        boolean[] inRecursion = new boolean[V]; // SC: O(V)
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (!visited[i] && dfsGraph(i, visited, inRecursion, adj)) { // TC: O(V + E), SC: O(V)
                return true;
            }
        }
        return false;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private boolean dfsGraph(int u, boolean[] visited,
            boolean[] inRecursion, Map<Integer, ArrayList<Integer>> adj) {
        visited[u] = true;
        inRecursion[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v] && dfsGraph(v, visited, inRecursion, adj)) {
                return true;
            } else if (inRecursion[v]) {
                return true;
            }
        }
        inRecursion[u] = false;
        return false;
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
        }
        return adj;
    }
}
