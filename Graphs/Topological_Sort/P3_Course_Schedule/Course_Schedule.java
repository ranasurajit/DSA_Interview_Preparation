package Graphs.Topological_Sort.P3_Course_Schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Course_Schedule {
    public static void main(String[] args) {
        Course_Schedule solution = new Course_Schedule();

        int numCourses1 = 2;
        int[][] prerequisites1 = { { 1, 0 } };

        boolean canFinishUsingDFS = solution.canFinishUsingDFS(numCourses1, prerequisites1);
        System.out.println(canFinishUsingDFS);

        int numCourses2 = 2;
        int[][] prerequisites2 = { { 1, 0 }, { 0, 1 } };

        boolean canFinishUsingBFS = solution.canFinishUsingBFS(numCourses2, prerequisites2);
        System.out.println(canFinishUsingBFS);
    }

    /**
     * Approach II : Using BFS (Kahn's Algorithm) Approach
     * 
     * TC: O(E) + O(V) + O(V + E) ~ O(V + E)
     * SC: O(V + E) + O(V) + O(V) ~ O(V + E)
     */
    public boolean canFinishUsingBFS(int numCourses, int[][] prerequisites) {
        // creating Adjacency List
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(V + E)
        int[] indegrees = new int[numCourses]; // SC: O(V)
        for (int[] edge : prerequisites) { // TC: O(E)
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
            indegrees[edge[0]]++;
        }
        /**
         * we can finish all courses if and only if there's no cyclic dependency,
         * so we need to check, if this Directed Graph is cyclic in nature
         */
        // using BFS Approach
        Queue<Integer> queue = new LinkedList<Integer>(); // SC: O(V)
        for (int i = 0; i < numCourses; i++) { // TC: O(V)
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) { // TC: O(V)
            Integer u = queue.poll();
            count++;
            for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) { // TC: O(E)
                indegrees[v]--;
                if (indegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return count == numCourses;
    }

    /**
     * Approach I : Using DFS Approach
     * 
     * TC: O(E) + O(2 x V + E) ~ O(V + E)
     * SC: O(V + E) + O(V) + O(V) + O(V) ~ O(V + E)
     */
    public boolean canFinishUsingDFS(int numCourses, int[][] prerequisites) {
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
        boolean[] visited = new boolean[numCourses]; // SC: O(V)
        boolean[] inRecursion = new boolean[numCourses]; // SC: O(V)
        for (int i = 0; i < numCourses; i++) { // TC: O(V)
            if (!visited[i] && dfsGraph(i, visited, inRecursion, adj)) { // TC: O(V + E), SC: O(V)
                // this indicates that there is a cycle in graph
                return false;
            }
        }
        return true;
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private boolean dfsGraph(int u, boolean[] visited, boolean[] inRecursion,
            Map<Integer, ArrayList<Integer>> adj) {
        visited[u] = true;
        inRecursion[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v] && dfsGraph(v, visited, inRecursion, adj)) {
                return true;
            } else if (inRecursion[v]) {
                return true;
            }
        }
        inRecursion[u] = false;
        return false;
    }
}
