package Graphs.Topological_Sort.P2_Topological_Sort_BFS_Kahns_Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import Arrays.Utils.ArrayUtils;

public class Topological_Sort_BFS_Kahns_Algorithm {
    public static void main(String[] args) {
        Topological_Sort_BFS_Kahns_Algorithm solution = new Topological_Sort_BFS_Kahns_Algorithm();

        int[][] edgesData = { { 1, 3 }, { 2, 3 }, { 4, 1 }, { 4, 0 }, { 5, 0 }, { 5, 2 } };
        ArrayList<ArrayList<Integer>> edges = ArrayUtils.convert2DArayToArrayList(edgesData);
        int v = 6;
        int e = 6;

        ArrayList<Integer> sortedList = solution.topologicalSort(edges, v, e);
        System.out.println(sortedList);
    }

    /**
     * Approach : Using Topological Sort + BFS (Kahn's Algorithm) Approach
     * 
     * TC: O(V + E) + O(V) + O(V + E) ~ O(V + E)
     * SC: O(V + E) + O(V) ~ O(V + E)
     */
    public ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> edges, int v, int e) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(V + E)
        int[] indegrees = new int[v]; // SC: O(V)
        for (int i = 0; i < v; i++) { // TC: O(V)
            adj.put(i, new ArrayList<Integer>());
        }
        for (ArrayList<Integer> edge : edges) { // TC: O(E)
            int src = edge.get(0);
            int dest = edge.get(1);
            adj.get(src).add(dest);
            indegrees[dest]++;
        }
        ArrayList<Integer> sortList = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>(); // SC: O(V)
        for (int i = 0; i < v; i++) { // TC: O(V)
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) { // TC: O(V)
            Integer u = queue.poll();
            sortList.add(u);
            for (Integer ngbr : adj.getOrDefault(u, new ArrayList<Integer>())) { // TC: O(E)
                indegrees[ngbr]--;
                if (indegrees[ngbr] == 0) {
                    queue.offer(ngbr);
                }
            }
        }
        return sortList;
    }
}
