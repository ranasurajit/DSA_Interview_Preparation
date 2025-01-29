package Graphs.Traversals_BFS_DFS.P5_Course_Schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course_Schedule {
    public static void main(String[] args) {
        Course_Schedule solution = new Course_Schedule();

        int numCourses = 2;
        int[][] prerequisites = { { 1, 0 }, { 0, 1 } };

        boolean canFinishCourse = solution.canFinish(numCourses, prerequisites);
        System.out.println(canFinishCourse);
    }

    /**
     * Using DFS Approach - cannot finish courses if graph has cycle
     * So, determine if the graph is DAG or not
     * 
     * If DAG then return true
     * 
     * TC: O(2 x V + 2 x E) ~ O(V + E)
     * SC: O(3 x V) ~ O(V)
     * 
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // creating adjacency list
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] edge : prerequisites) {
            // dependency is between b[i] -> a[i]
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
        }
        boolean[] visited = new boolean[numCourses]; // SC: O(V)
        boolean[] inRecursion = new boolean[numCourses]; // SC: O(V)
        for (int i = 0; i < numCourses; i++) { // TC: O(V)
            if (!visited[i] && hasCycleDFS(i, adj, visited, inRecursion)) {
                return false; // TC: O(V + 2 x E), SC: O(V)
            }
        }
        return true;
    }

    /**
     * DFS Approach
     * 
     * TC: O(V + 2 x E)
     * SC: O(V)
     * 
     * @param u
     * @param adj
     * @param visited
     * @param inRecursion
     * @return
     */
    private boolean hasCycleDFS(int u, Map<Integer, ArrayList<Integer>> adj,
            boolean[] visited, boolean[] inRecursion) {
        visited[u] = true;
        inRecursion[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v] && hasCycleDFS(v, adj, visited, inRecursion)) {
                return true;
            } else if (inRecursion[v]) {
                return true;
            }
        }
        // reverting to false for next DFS calls (previous DFS call ends here)
        inRecursion[u] = false;
        return false;
    }
}
