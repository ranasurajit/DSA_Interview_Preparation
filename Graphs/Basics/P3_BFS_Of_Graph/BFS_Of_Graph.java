package Graphs.Basics.P3_BFS_Of_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_Of_Graph {
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
        ArrayList<Integer> traversal = bfsOfGraph(V, adj);
        System.out.println(traversal);
    }

    /**
     * TC: O(V + E)
     * SC: O(2 x V) ~ O(V)
     */
    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V]; // SC: O(V)
        Queue<Integer> queue = new LinkedList<Integer>(); // SC: O(V)
        queue.offer(0);
        visited[0] = true;
        ArrayList<Integer> traversal = new ArrayList<Integer>();
        while (!queue.isEmpty()) { // TC: O(V)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer u = queue.poll();
                traversal.add(u);
                for (Integer v : adj.get(u)) { // TC: O(E)
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.offer(v);
                    }
                }
            }
        }
        return traversal;
    }
}
