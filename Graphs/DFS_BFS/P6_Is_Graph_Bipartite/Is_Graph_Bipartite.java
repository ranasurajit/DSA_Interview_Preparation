package Graphs.DFS_BFS.P6_Is_Graph_Bipartite;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Is_Graph_Bipartite {
    public static void main(String[] args) {
        Is_Graph_Bipartite solution = new Is_Graph_Bipartite();

        int[][] graph1 = { { 1, 2, 3 }, { 0, 2 }, { 0, 1, 3 }, { 0, 2 } };
        boolean isBipartiteDFS = solution.isBipartiteDFS(graph1);
        System.out.println(isBipartiteDFS);

        int[][] graph2 = { { 1, 3 }, { 0, 2 }, { 1, 3 }, { 0, 2 } };
        boolean isBipartiteBFS = solution.isBipartiteBFS(graph2);
        System.out.println(isBipartiteBFS);
    }

    /**
     * Approach II : Using BFS Approach
     *
     * TC: O(2 x V + E) ~ O(V + E)
     * SC: O(V) + O(V)
     */
    public boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; // SC: O(V)
        Arrays.fill(colors, -1); // initially no color is assigned
        for (int i = 0; i < n; i++) { // TC: O(V)
            // say we have two colors 0 and 1 and we start with 0
            if (colors[i] == -1 && !bfsGraph(i, graph, colors, 0)) { // TC: O(V + E), SC: O(V)
                return false;
            }
        }
        return true;
    }

    /**
     * Using BFS Approach
     *
     * TC: O(V + E)
     * SC: O(V)
     */
    private boolean bfsGraph(int src, int[][] graph, int[] colors, int currentColor) {
        // we will be storing { node, color } in Queue
        Queue<int[]> queue = new LinkedList<int[]>(); // SC: O(V)
        queue.offer(new int[] { src, currentColor });
        while (!queue.isEmpty()) { // TC: O(V)
            int[] current = queue.poll();
            int u = current[0];
            int color = current[1];
            colors[u] = color;
            for (int v : graph[u]) { // TC: O(E)
                if (colors[v] == colors[u]) {
                    return false;
                }
                if (colors[v] == -1) {
                    queue.offer(new int[] { v, 1 - color });
                }
            }
        }
        return true;
    }

    /**
     * Approach I : Using DFS Approach
     *
     * TC: O(2 x V + E) ~ O(V + E)
     * SC: O(V) + O(V)
     */
    public boolean isBipartiteDFS(int[][] graph) {
        int v = graph.length;
        int[] colors = new int[v]; // SC: O(V)
        Arrays.fill(colors, -1); // initially no color is assigned
        for (int i = 0; i < v; i++) { // TC: O(V)
            // say we have two colors 0 and 1 and we start with 0
            if (colors[i] == -1 && !dfsGraph(i, graph, colors, 0)) { // TC: O(V + E), SC: O(V)
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
    private boolean dfsGraph(int u, int[][] graph, int[] colors, int currentColor) {
        colors[u] = currentColor;
        for (int v : graph[u]) {
            if (colors[v] == colors[u]) {
                // cannot have same color in neighbours
                return false;
            }
            if (colors[v] == -1 && !dfsGraph(v, graph, colors, 1 - currentColor)) {
                return false;
            }
        }
        return true;
    }
}
