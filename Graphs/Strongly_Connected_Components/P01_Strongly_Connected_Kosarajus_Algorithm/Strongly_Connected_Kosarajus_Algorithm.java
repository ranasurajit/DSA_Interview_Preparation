package Graphs.Strongly_Connected_Components.P01_Strongly_Connected_Kosarajus_Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Strongly_Connected_Kosarajus_Algorithm {
    public static void main(String[] args) {
        Strongly_Connected_Kosarajus_Algorithm solution = new Strongly_Connected_Kosarajus_Algorithm();

        int[][] adjArray = { { 2, 3 }, { 0 }, { 1 }, { 4 }, {} };

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int[] item : adjArray) {
            ArrayList<Integer> items = new ArrayList<Integer>();
            for (int i = 0; i < item.length; i++) {
                items.add(item[i]);
            }
            adj.add(items);
        }

        int connectedComponents = solution.kosaraju(adj);
        System.out.println(connectedComponents);
    }

    // Function to find number of strongly connected components in the graph.
    /**
     * Approach: Using Kosaraju's Algorithm Approach
     * 
     * TC: O(3 x (V + E)) ~ O(V + E)
     * SC: O(3 x V + E) ~ O(V + E)
     */
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        // Step 1: Get the Topological Sort by DFS and populate the Stack
        Stack<Integer> st = new Stack<Integer>();
        boolean[] visited = new boolean[n]; // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(1)
            if (!visited[i]) {
                dfsTopoSortGraph(i, visited, adj, st); // TC: O(V + E)
            }
        }
        // Step 2: Reverse the directions of directed graph adjacency list 'adj'
        Map<Integer, ArrayList<Integer>> revAdj = reverseDirections(adj); // TC: O(V + E), SC: O(V + E)
        // Step 3 Perform DFS on reversed Adjacency List in LIFO order from Stack 'st'
        visited = new boolean[n]; // SC: O(V)
        int countComponents = 0;
        while (!st.isEmpty()) { // TC: O(V)
            int u = st.pop();
            if (!visited[u]) {
                dfsGraph(u, visited, revAdj); // TC: O(V + E), SC: O(V)
                countComponents++;
            }
        }
        return countComponents;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private void dfsGraph(int u, boolean[] visited, Map<Integer, ArrayList<Integer>> revAdj) {
        visited[u] = true;
        for (Integer v : revAdj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v]) {
                dfsGraph(v, visited, revAdj);
            }
        }
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(V + E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> reverseDirections(ArrayList<ArrayList<Integer>> adj) {
        Map<Integer, ArrayList<Integer>> revAdj = new HashMap<Integer, ArrayList<Integer>>();
        for (int u = 0; u < adj.size(); u++) {
            for (Integer v : adj.get(u)) {
                revAdj.computeIfAbsent(v, k -> new ArrayList<Integer>()).add(u);
            }
        }
        return revAdj;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private void dfsTopoSortGraph(int u, boolean[] visited,
            ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        visited[u] = true;
        for (Integer v : adj.get(u)) {
            if (!visited[v]) {
                dfsTopoSortGraph(v, visited, adj, st);
            }
        }
        st.push(u);
    }
}
