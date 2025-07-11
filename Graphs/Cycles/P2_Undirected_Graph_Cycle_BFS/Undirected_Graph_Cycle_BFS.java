package Graphs.Cycles.P2_Undirected_Graph_Cycle_BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Undirected_Graph_Cycle_BFS {
    public static void main(String[] args) {
        Undirected_Graph_Cycle_BFS solution = new Undirected_Graph_Cycle_BFS();

        int V = 4;
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 2, 3 } };

        boolean hasCycleInGraphBFS = solution.isCycle(V, edges);
        System.out.println(hasCycleInGraphBFS);
    }

    /**
     * Approach : Using BFS Approach
     * 
     * TC: O(2 x E) + O(2 x V + 2 x E) ~ O(V + E)
     * SC: O(V) + O(V + E) + O(V) ~ O(V + E)
     */
    public boolean isCycle(int V, int[][] edges) {
        boolean[] visited = new boolean[V]; // SC: O(V)
        Map<Integer, ArrayList<Integer>> adj = createGraph(edges); // TC: O(2 x E), SC: O(V + E)
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (!visited[i] && bfsGraph(i, visited, adj)) { // TC: O(V + 2 x E), SC: O(V)
                return true;
            }
        }
        return false;
    }

    /**
     * Using BFS Approach
     * 
     * TC: O(V + 2 x E)
     * SC: O(V)
     */
    private boolean bfsGraph(int src, boolean[] visited, Map<Integer, ArrayList<Integer>> adj) {
        // we will be storing { vertex, parent(vertex) } in the Queue
        Queue<int[]> queue = new LinkedList<int[]>(); // SC: O(V)
        visited[src] = true;
        queue.offer(new int[] { src, -1 });
        while (!queue.isEmpty()) { // TC: O(V)
            int[] current = queue.poll();
            int u = current[0];
            int parent = current[1];
            for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) { // TC: O(E)
                if (v == parent) {
                    // wrong vertex reached
                    continue;
                }
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(new int[] { v, u });
                } else {
                    // here visited[v] is true
                    if (v != parent) {
                        return true;
                    }
                }
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
