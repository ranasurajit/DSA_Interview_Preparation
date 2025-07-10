package Graphs.Basics.P4_Print_Adjacency_List;

import java.util.ArrayList;
import java.util.List;

public class Print_Adjacency_List {
    public static void main(String[] args) {
        Print_Adjacency_List solution = new Print_Adjacency_List();

        int V = 5;
        int[][] edges = { { 0, 1 }, { 0, 4 }, { 4, 1 }, { 4, 3 }, { 1, 3 }, { 1, 2 }, { 3, 2 } };
        List<List<Integer>> adj = solution.printGraph(V, edges);
        System.out.println(adj);
    }

    /**
     * Approach : Using ArrayList Approach
     * 
     * TC: O(V + E)
     * SC: O(1)
     */
    public List<List<Integer>> printGraph(int V, int edges[][]) {
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        for (int i = 0; i < V; i++) { // TC: O(V)
            adj.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) { // TC: O(E)
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return adj;
    }
}
