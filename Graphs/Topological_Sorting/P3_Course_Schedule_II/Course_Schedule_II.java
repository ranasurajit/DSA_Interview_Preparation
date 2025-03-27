package Graphs.Topological_Sorting.P3_Course_Schedule_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Course_Schedule_II {
    public static void main(String[] args) {
        Course_Schedule_II solution = new Course_Schedule_II();

        int numCourses = 4;
        int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };

        int[] schedule = solution.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(schedule));
    }

    /**
     * Using Topological Sort Approach
     *
     * TC: O(V + E)
     * SC: O(V + E)
     * 
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // create adjacency list
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(V + E)
        for (int[] edge : prerequisites) {
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
        }
        boolean hasCycle = doesGraphHasCycle(adj, numCourses); // TC: O(V + E), SC: O(V)
        if (hasCycle) {
            return new int[] {};
        }
        Stack<Integer> st = new Stack<Integer>(); // SC: O(V)
        boolean[] visited = new boolean[numCourses]; // SC: O(V)
        for (int i = 0; i < numCourses; i++) { // TC: O(V)
            if (!visited[i]) {
                dfsGraph(i, adj, visited, st); // TC: O(V + E), SC: O(V)
            }
        }
        int[] topoSort = new int[numCourses];
        int index = 0;
        System.out.println(st.size());
        while (!st.isEmpty()) { // TC: O(V)
            topoSort[index++] = st.pop();
        }
        return topoSort;
    }

    /**
     * TC: O(2 x V + E) ~ O(V + E)
     * SC: O(3 x V) ~ O(V)
     * 
     * @param adj
     * @param n
     * @return
     */
    private boolean doesGraphHasCycle(Map<Integer, ArrayList<Integer>> adj, int n) {
        boolean[] visited = new boolean[n]; // SC: O(V)
        boolean[] inRecursion = new boolean[n]; // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (!visited[i] && dfsGraphCycle(i, adj, visited, inRecursion)) {
                return true; // TC: O(V + E), SC: O(V)
            }
        }
        return false;
    }

    /**
     * TC: O(V + E)
     * SC: O(V)
     * 
     * @param u
     * @param adj
     * @param visited
     * @param inRecursion
     * @return
     */
    private boolean dfsGraphCycle(int u, Map<Integer, ArrayList<Integer>> adj,
            boolean[] visited, boolean[] inRecursion) {
        visited[u] = true;
        inRecursion[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v] && dfsGraphCycle(v, adj, visited, inRecursion)) {
                return true;
            } else if (inRecursion[v]) {
                return true;
            }
        }
        inRecursion[u] = false;
        return false;
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
    private void dfsGraph(int u, Map<Integer, ArrayList<Integer>> adj,
            boolean[] visited, Stack<Integer> st) {
        visited[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v]) {
                dfsGraph(v, adj, visited, st);
            }
        }
        st.push(u);
    }
}
