package Graphs.Topological_Sort.P4_Course_Schedule_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Course_Schedule_II {
    public static void main(String[] args) {
        Course_Schedule_II solution = new Course_Schedule_II();

        int numCourses1 = 2;
        int[][] prerequisites1 = { { 1, 0 } };
        int[] topoList1 = solution.findOrderUsingDFS(numCourses1, prerequisites1);
        System.out.println(Arrays.toString(topoList1));

        int numCourses2 = 4;
        int[][] prerequisites2 = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
        int[] topoList2 = solution.findOrderUsingDFS(numCourses2, prerequisites2);
        System.out.println(Arrays.toString(topoList2));
    }

    /**
     * Approach II : Using BFS (Kahn's Algorithm) Approach
     * 
     * TC: O(E) + O(V + E) + O(V) + O(V + E) ~ O(V + E)
     * SC: O(V) + O(V + E) + O(V) + O(V) ~ O(V + E)
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // creating Adjacency List
        int[] indegrees = new int[numCourses]; // SC: O(V)
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(V + E)
        for (int[] edge : prerequisites) { // TC: O(E)
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
            indegrees[edge[0]]++;
        }
        /**
         * we can finish all courses if and only if there's no cyclic dependency,
         * so we need to check, if this Directed Graph is cyclic in nature
         */
        // using DFS Approach to find if Graph has cycle
        boolean hasCycle = doesGraphHasCycle(adj, numCourses); // TC: O(V + E), SC: O(V)
        if (hasCycle) {
            return new int[] {};
        }
        // using BFS Approach to get the topological sorted array of courses
        Queue<Integer> queue = new LinkedList<Integer>(); // SC: O(V)
        for (int i = 0; i < numCourses; i++) { // TC: O(V)
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int[] order = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) { // TC: O(V)
            Integer u = queue.poll();
            order[index++] = u;
            for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) { // TC: O(E)
                indegrees[v]--;
                if (indegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return order;
    }

    /**
     * Approach I : Using DFS Approach
     * 
     * TC: O(E) + O(V + E) + O(2 x V + E) + O(V) ~ O(V + E)
     * SC: O(V + E) + O(V) + O(V) + O(V) ~ O(V + E)
     */
    public int[] findOrderUsingDFS(int numCourses, int[][] prerequisites) {
        // creating Adjacency List
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(V + E)
        for (int[] edge : prerequisites) { // TC: O(E)
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
        }
        /**
         * we can finish all courses if and only if there's no cyclic dependency,
         * so we need to check, if this Directed Graph is cyclic in nature
         */
        // using DFS Approach
        boolean hasCycle = doesGraphHasCycle(adj, numCourses); // TC: O(V + E), SC: O(V)
        if (hasCycle) {
            return new int[] {};
        }
        Stack<Integer> st = new Stack<Integer>(); // SC: O(V)
        boolean[] visited = new boolean[numCourses]; // SC: O(V)
        for (int i = 0; i < numCourses; i++) { // TC: O(V)
            if (!visited[i]) { // TC: O(V + E), SC: O(V)
                dfsTopoGraph(i, visited, adj, st);
            }
        }
        int[] order = new int[numCourses];
        int index = 0;
        while (!st.isEmpty()) { // TC: O(V)
            order[index++] = st.pop();
        }
        return order;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private void dfsTopoGraph(int u, boolean[] visited, Map<Integer, ArrayList<Integer>> adj,
            Stack<Integer> st) {
        visited[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v]) {
                dfsTopoGraph(v, visited, adj, st);
            }
        }
        st.push(u);
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(2 x V + E) ~ O(V + E)
     * SC: O(V) + O(V) ~ O(V)
     */
    private boolean doesGraphHasCycle(Map<Integer, ArrayList<Integer>> adj, int n) {
        boolean[] visited = new boolean[n]; // SC: O(V)
        boolean[] inRecursion = new boolean[n]; // SC: O(V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (!visited[i] && hasCycle(i, visited, inRecursion, adj)) { // TC: O(V + E), SC: O(V)
                // this indicates that there is a cycle in graph
                return true;
            }
        }
        return false;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private boolean hasCycle(int u, boolean[] visited, boolean[] inRecursion,
            Map<Integer, ArrayList<Integer>> adj) {
        visited[u] = true;
        inRecursion[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v] && hasCycle(v, visited, inRecursion, adj)) {
                return true;
            } else if (inRecursion[v]) {
                return true;
            }
        }
        inRecursion[u] = false;
        return false;
    }
}
