package Graphs.Basics.P2_DFS_Of_Graph;

import java.util.ArrayList;

import Graphs.Utils.GraphUtils;

public class DFS_Of_Graph {
    public static void main(String[] args) {
        DFS_Of_Graph solution = new DFS_Of_Graph();

        int[][] graphData = { { 2, 3, 1 }, { 0 }, { 0, 4 }, { 0 }, { 2 } };
        ArrayList<ArrayList<Integer>> adj = GraphUtils.buildAdjList(graphData);
        ArrayList<Integer> dfsPath = solution.dfs(adj);
        System.out.println(dfsPath);
    }

    /**
     * Approach : Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V) + O(V) ~ O(V)
     */
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        boolean[] visited = new boolean[n]; // SC: O(V)
        ArrayList<Integer> path = new ArrayList<Integer>();
        dfsGraph(0, visited, adj, path); // TC: O(V + E), SC: O(V)
        return path;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private void dfsGraph(int u, boolean[] visited, ArrayList<ArrayList<Integer>> adj,
            ArrayList<Integer> path) {
        visited[u] = true;
        path.add(u);
        for (Integer v : adj.get(u)) {
            if (!visited[v]) {
                dfsGraph(v, visited, adj, path);
            }
        }
    }
}
