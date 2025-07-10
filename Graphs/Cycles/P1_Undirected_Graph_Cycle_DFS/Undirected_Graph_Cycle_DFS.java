package Graphs.Cycles.P1_Undirected_Graph_Cycle_DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Undirected_Graph_Cycle_DFS {
    public static void main(String[] args) {
        Undirected_Graph_Cycle_DFS solution = new Undirected_Graph_Cycle_DFS();

        int V = 4;
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 2, 3 } };

        boolean hasCycleInGraphDFS = solution.isCycle(V, edges);
        System.out.println(hasCycleInGraphDFS);
    }

    /**
     * Approach : Using DFS Approach
     * 
     * TC: O(2 x E) + O(2 x V + E) ~ O(V + E)
     * SC: O(V) + O(V + E) + O(V) ~ O(V + E)
     */
    public boolean isCycle(int V, int[][] edges) {
        boolean[] visited = new boolean[V]; // SC: O(V)
        Map<Integer, ArrayList<Integer>> adj = createGraph(edges); // TC: O(2 x E), SC: O(V + E)
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (!visited[i] && dfsGraph(i, -1, visited, adj)) { // TC: O(V + E), SC: O(V)
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
    private boolean dfsGraph(int u, int parent, boolean[] visited, Map<Integer, ArrayList<Integer>> adj) {
        visited[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (v == parent) {
                continue;
            }
            if (visited[v]) {
                return true;
            }
            if (dfsGraph(v, u, visited, adj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(2 x E) ~ O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
        }
        return adj;
    }
}
