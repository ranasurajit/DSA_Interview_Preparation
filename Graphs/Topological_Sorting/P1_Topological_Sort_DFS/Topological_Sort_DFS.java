package Graphs.Topological_Sorting.P1_Topological_Sort_DFS;

import java.util.ArrayList;
import java.util.Stack;

public class Topological_Sort_DFS {
    public static void main(String[] args) {
        int[][] adj = { {}, { 3 }, { 3 }, {}, { 0, 1 }, { 0, 2 } };
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for (int[] adj1D : adj) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int element : adj1D) {
                list.add(element);
            }
            adjList.add(list);
        }

        ArrayList<Integer> topoList = topologicalSort(adjList);
        System.out.println(topoList);
    }

    /**
     * Topological Sort using DFS
     * 
     * TC: O(3 x V + E) ~ O(V + E)
     * SC: O(3 x V) ~ O(V)
     * 
     * @param adj
     * @return
     */
    private static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> topoList = new ArrayList<Integer>();
        int v = adj.size();
        boolean[] visited = new boolean[v]; // SC: O(V)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(V)
        for (int i = 0; i < v; i++) { // TC: O(V)
            if (!visited[i]) {
                dfsGraph(i, adj, visited, st); // TC: O(V + E), SC: O(V)
            }
        }
        while (!st.isEmpty()) { // TC: O(V)
            topoList.add(st.pop());
        }
        return topoList;
    }

    /**
     * TC: O(V + E)
     * SC: O(V)
     * 
     * @param u
     * @param adj
     * @param visited
     * @param st
     */
    private static void dfsGraph(int u, ArrayList<ArrayList<Integer>> adj,
            boolean[] visited, Stack<Integer> st) {
        visited[u] = true;
        for (Integer v : adj.get(u)) {
            if (!visited[v]) {
                dfsGraph(v, adj, visited, st);
            }
        }
        st.push(u);
    }
}
