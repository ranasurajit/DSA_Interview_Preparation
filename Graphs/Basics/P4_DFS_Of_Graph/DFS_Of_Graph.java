package Graphs.Basics.P4_DFS_Of_Graph;

import java.util.ArrayList;

public class DFS_Of_Graph {
    public static void main(String[] args) {
        int[][] graphData = { { 2, 3, 1 }, { 0 }, { 0, 4 }, { 0 }, { 2 } };
        int V = graphData.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < V; i++) {
            int[] vertices = graphData[i];
            adj.add(new ArrayList<Integer>());
            for (int vertex : vertices) {
                adj.get(i).add(vertex);
            }
        }
        ArrayList<Integer> traversal = dfsOfGraph(adj);
        System.out.println(traversal);
    }

    /**
     * TC: O(V + E)
     * TC: O(V)
     */
    public static ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        boolean[] visited = new boolean[n]; // SC: O(V)
        ArrayList<Integer> traversal = new ArrayList<Integer>();
        int start = 0;
        dfsGraph(start, visited, adj, traversal); // TC: O(V + E)
        return traversal;
    }

    /**
     * TC: O(V + E)
     * SC: O(1)
     */
    private static void dfsGraph(int u, boolean[] visited,
            ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> traversal) {
        visited[u] = true;
        traversal.add(u);
        for (Integer v : adj.get(u)) {
            if (!visited[v]) {
                dfsGraph(v, visited, adj, traversal);
            }
        }
    }
}
