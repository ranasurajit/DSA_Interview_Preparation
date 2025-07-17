package Graphs.Topological_Sort.P5_Find_Eventual_Safe_States;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Find_Eventual_Safe_States {
    public static void main(String[] args) {
        Find_Eventual_Safe_States solution = new Find_Eventual_Safe_States();

        int[][] graph1 = { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };
        List<Integer> eventualSafeNodesDFS = solution.eventualSafeNodesDFS(graph1);
        System.out.println(eventualSafeNodesDFS);

        int[][] graph2 = { { 1, 2, 3, 4 }, { 1, 2 }, { 3, 4 }, { 0, 4 }, {} };
        List<Integer> eventualSafeNodesBFS = solution.eventualSafeNodesBFS(graph2);
        System.out.println(eventualSafeNodesBFS);
    }

    /**
     * Approach II : Using BFS (Kahn's Algorithm) Approach
     * 
     * TC: O(V + E) + O(V) + O(V + E) + O(V) ~ O(V + E)
     * SC: O(V + E) + O(V) + O(V) + O(V) ~ O(V + E)
     */
    public List<Integer> eventualSafeNodesBFS(int[][] graph) {
        int n = graph.length;
        /**
         * As we need to exclude the nodes which are stuck in cycle
         * so, for BFS approach we need to add nodes with indegree 0
         * in the Queue, but few nodes cannot go back to find all the
         * nodes in the cycle so we need to reverse the directions of
         * edges and form a new adjacency list with node indegrees
         */
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(V + E)
        int[] indegrees = new int[n]; // SC: O(V)
        for (int u = 0; u < n; u++) { // TC: O(V)
            for (int v : graph[u]) { // TC: O(E)
                adj.computeIfAbsent(v, k -> new ArrayList<Integer>()).add(u);
                indegrees[u]++;
            }
        }
        /**
         * We will apply Kahn's Algorithm (BFS), so we need a Queue and we need to push
         * all the nodes having indegree = 0 in the Queue
         */
        Queue<Integer> queue = new LinkedList<Integer>(); // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        /**
         * store all safe nodes where the nodes get popped out of Queue
         * (as nodes involve in cycle cannot come out of Queue as their
         * indegrees cannot be zero)
         */
        boolean[] isSafeNode = new boolean[n]; // SC: O(V)
        while (!queue.isEmpty()) { // TC: O(V)
            Integer u = queue.poll();
            isSafeNode[u] = true;
            for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) { // TC: O(E)
                indegrees[v]--;
                if (indegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        List<Integer> safeNodes = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (isSafeNode[i]) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }

    /**
     * Approach I : Using DFS Approach
     * 
     * TC: O(2 x V + E) + O(V) ~ O(V + E)
     * SC: O(V) + O(V) ~ O(V)
     */
    public List<Integer> eventualSafeNodesDFS(int[][] graph) {
        int n = graph.length;
        /**
         * A node which is a part of any cycle cannot only end up in a terminal node
         * so, Safe nodes will be the one which are not a part of any cycle
         * DFS Approach : we need to exclude those nodes where inRecursion is false
         */
        boolean[] visited = new boolean[n]; // SC: O(V)
        boolean[] inRecursion = new boolean[n]; // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (!visited[i]) {
                dfsGraph(i, visited, inRecursion, graph); // TC: O(V + E), SC: O(V)
            }
        }
        // store all safe nodes where inRecursion[i] = false
        List<Integer> safeNodes = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (!inRecursion[i]) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private boolean dfsGraph(int u, boolean[] visited, boolean[] inRecursion, int[][] graph) {
        visited[u] = true;
        inRecursion[u] = true;
        for (int v : graph[u]) {
            if (!visited[v] && dfsGraph(v, visited, inRecursion, graph)) {
                return true;
            } else if (inRecursion[v]) {
                return true;
            }
        }
        inRecursion[u] = false;
        return false;
    }
}
