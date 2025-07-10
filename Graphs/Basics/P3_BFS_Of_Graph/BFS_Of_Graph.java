package Graphs.Basics.P3_BFS_Of_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Graphs.Utils.GraphUtils;

public class BFS_Of_Graph {
    public static void main(String[] args) {
        BFS_Of_Graph solution = new BFS_Of_Graph();

        int[][] graphData = { { 2, 3, 1 }, { 0 }, { 0, 4 }, { 0 }, { 2 } };
        ArrayList<ArrayList<Integer>> adj = GraphUtils.buildAdjList(graphData);
        ArrayList<Integer> dfsPath = solution.bfs(adj);
        System.out.println(dfsPath);
    }

    /**
     * Approach : Using BFS Approach
     * 
     * TC: O(V + 2 x E) ~ O(V + E)
     * SC: O(V) + O(V) ~ O(V)
     */
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        ArrayList<Integer> path = new ArrayList<Integer>();
        boolean[] visited = new boolean[n]; // SC: O(V)
        Queue<Integer> queue = new LinkedList<Integer>(); // SC: O(V)
        int start = 0;
        queue.offer(start);
        while (!queue.isEmpty()) { // TC: O(V)
            int size = queue.size();
            for (int i = 0; i < size; i++) { // TC: O(E)
                Integer u = queue.poll();
                if (visited[u]) {
                    continue;
                }
                visited[u] = true;
                path.add(u);
                for (Integer v : adj.get(u)) { // TC: O(E)
                    if (!visited[v]) {
                        queue.offer(v);
                    }
                }
            }
        }
        return path;
    }
}
