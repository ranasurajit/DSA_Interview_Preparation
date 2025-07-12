package Graphs.Topological_Sort.P1_Topological_Sort_DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Topological_Sort_DFS {
    public static void main(String[] args) {
        Topological_Sort_DFS solution = new Topological_Sort_DFS();

        int V = 6;
        int[][] edges = { { 1, 3 }, { 2, 3 }, { 4, 1 }, { 4, 0 }, { 5, 0 }, { 5, 2 } };

        ArrayList<Integer> sortedList = solution.topoSort(V, edges);
        System.out.println(sortedList);
    }

    /**
     * Approach : Using Topological Sort + DFS Approach
     * 
     * TC: O(2 x V + E) + O(V) ~ O(V + E)
     * SC: O(V + E) + O(V) + O(V) + O(V) ~ O(V + E)
     */
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = createGraph(edges); // TC: O(E), SC: O(V + E)
        boolean[] visited = new boolean[V]; // SC: O(V)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(V)
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (!visited[i]) {
                dfsGraph(i, visited, adj, st); // TC: O(V + E), SC: O(V)
            }
        }
        ArrayList<Integer> sortList = new ArrayList<Integer>();
        while (!st.isEmpty()) { // TC: O(V)
            sortList.add(st.pop());
        }
        return sortList;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private void dfsGraph(int u, boolean[] visited, Map<Integer, ArrayList<Integer>> adj, Stack<Integer> st) {
        visited[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v]) {
                dfsGraph(v, visited, adj, st);
            }
        }
        st.push(u);
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] edge : edges) { // TC: O(E)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
        }
        return adj;
    }
}
