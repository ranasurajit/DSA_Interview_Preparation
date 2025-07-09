package Graphs_Backup.Basics.P2_Print_Adjacency_List;

import java.util.ArrayList;
import java.util.List;

public class Print_Adjacency_List {
    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {
                { 0, 1 }, { 0, 4 }, { 4, 1 },
                { 4, 3 }, { 1, 3 }, { 1, 2 },
                { 3, 2 }
        };
        List<List<Integer>> adjList = printGraph(V, edges);
        System.out.println(adjList);
    }

    /**
     * TC: O(V + E)
     * SC: O(V + E)
     */
    public static List<List<Integer>> printGraph(int V, int edges[][]) {
        List<List<Integer>> adj = new ArrayList<List<Integer>>(); // SC: O(V + E)
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
