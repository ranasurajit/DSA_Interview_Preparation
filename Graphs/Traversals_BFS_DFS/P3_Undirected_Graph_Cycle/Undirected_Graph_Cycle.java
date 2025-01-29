package Graphs.Traversals_BFS_DFS.P3_Undirected_Graph_Cycle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Undirected_Graph_Cycle {
    public static void main(String[] args) {
        Undirected_Graph_Cycle solution = new Undirected_Graph_Cycle();

        int[][] graph = { { 1 }, { 0, 2, 4 }, { 1, 3 }, { 2, 4 }, { 1, 3 } };

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < graph.length; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                adj.get(i).add(graph[i][j]);
            }
        }

        boolean isCyclePresentDFS = solution.isCycleDFS(adj);
        System.out.println(isCyclePresentDFS);

        boolean isCyclePresentBFS = solution.isCycleBFS(adj);
        System.out.println(isCyclePresentBFS);
    }

    /**
     * DFS Approach
     * 
     * TC: O(2 x V + 2 x E) ~ O(V + E)
     * SC: O(2 x V) ~ O(V)
     * 
     * @param adj
     * @return
     */
    public boolean isCycleDFS(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        boolean[] visited = new boolean[n]; // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (!visited[i] && hasCycleInGraphDFS(i, -1, adj, visited)) {
                return true; // TC: O(V + 2 x E), SC: O(V)
            }
        }
        return false;
    }

    /**
     * DFS Approach
     * 
     * TC: O(V + 2 x E)
     * SC: O(V)
     * 
     * @param u
     * @param parent
     * @param adj
     * @param visited
     * @return
     */
    private boolean hasCycleInGraphDFS(int u, int parent,
            ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[u] = true;
        for (Integer v : adj.get(u)) {
            if (v == parent) {
                continue;
            }
            if (visited[v]) {
                return true;
            }
            if (hasCycleInGraphDFS(v, u, adj, visited)) {
                return true;
            }
        }
        return false;
    }

    /**
     * BFS Approach
     * 
     * TC: O(2 x V + 2 x E) ~ O(V + E)
     * SC: O(2 x V) ~ O(V)
     * 
     * @param adj
     * @return
     */
    public boolean isCycleBFS(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        boolean[] visited = new boolean[n]; // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (!visited[i] && hasCycleInGraphBFS(i, adj, visited)) {
                return true; // TC: O(V + 2 x E), SC: O(V)
            }
        }
        return false;
    }

    /**
     * BFS Approach
     * 
     * TC: O(V + 2 x E)
     * SC: O(V)
     * 
     * @param source
     * @param adj
     * @param visited
     * @return
     */
    private static boolean hasCycleInGraphBFS(int source,
            ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        // storing the (node, parent) in queue
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] { source, -1 });
        visited[source] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int u = current[0];
            int parent = current[1];
            for (Integer v : adj.get(u)) {
                if (v == parent) {
                    continue;
                }
                if (!visited[v]) {
                    queue.offer(new int[] { v, u });
                    visited[v] = true;
                } else if (v != parent) {
                    return true;
                }
            }
        }
        return false;
    }
}
