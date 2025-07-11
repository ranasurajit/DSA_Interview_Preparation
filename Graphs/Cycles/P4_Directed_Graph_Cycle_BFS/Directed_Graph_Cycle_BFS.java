package Graphs.Cycles.P4_Directed_Graph_Cycle_BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Directed_Graph_Cycle_BFS {
    public static void main(String[] args) {
        Directed_Graph_Cycle_BFS solution = new Directed_Graph_Cycle_BFS();

        int V = 4;
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 2, 0 }, { 2, 3 } };
        boolean hasCycleBFS = solution.isCyclic(V, edges);
        System.out.println(hasCycleBFS);
    }

    /**
     * Approach II : Using Kahn's Algorithm + BFS Approach
     * 
     * TC: O(E) + O(V) + O(V + E) ~ O(V + E)
     * SC: O(V) + O(V + E) + O(V) ~ O(V + E)
     */
    public boolean isCyclic(int V, int[][] edges) {
        int[] indegrees = new int[V]; // SC: O(V)
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(V + E)
        for (int[] edge : edges) { // TC: O(E)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
            indegrees[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>(); // SC: O(V)
        for (int i = 0; i < V; i++) { // TC: O(V)
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int countNodes = 0;
        while (!queue.isEmpty()) { // TC: O(V)
            int u = queue.poll();
            countNodes++;
            for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) { // TC: O(E)
                indegrees[v]--;
                if (indegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return countNodes != V;
    }
}
