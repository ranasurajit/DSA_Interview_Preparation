package Graphs.Topological_Sorting.P2_Topological_Sort_BFS_Kahns_Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Topological_Sort_BFS_Kahns_Algorithm {
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
     * Topological Sort using BFS (Kahn's Algorithm)
     * 
     * TC: O(3 x V + 2 x E) ~ O(V + E)
     * SC: O(2 x V) ~ O(V)
     * 
     * @param adj
     * @return
     */
    private static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> topoList = new ArrayList<Integer>();
        int n = adj.size();
        // we need to calculate the indegrees of each vertices
        int[] indegrees = new int[n]; // SC: O(V)
        for (ArrayList<Integer> u : adj) { // TC: O(V)
            for (Integer v : u) { // TC: O(E)
                indegrees[v]++;
            }
        }
        // Now we need to push the vertices with indegree value as 0 in the Queue
        Queue<Integer> queue = new LinkedList<Integer>(); // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        // starting the BFS
        while (!queue.isEmpty()) { // TC: O(V + E)
            Integer u = queue.poll();
            topoList.add(u);
            for (Integer v : adj.get(u)) {
                indegrees[v]--;
                if (indegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return topoList;
    }
}
