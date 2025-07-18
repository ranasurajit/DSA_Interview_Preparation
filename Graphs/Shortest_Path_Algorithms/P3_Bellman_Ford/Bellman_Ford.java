package Graphs.Shortest_Path_Algorithms.P3_Bellman_Ford;

import java.util.Arrays;

public class Bellman_Ford {
    public static void main(String[] args) {
        Bellman_Ford solution = new Bellman_Ford();

        int V1 = 5;
        int[][] edges1 = { { 1, 3, 2 }, { 4, 3, -1 }, { 2, 4, 1 }, { 1, 2, 1 }, { 0, 1, 5 } };
        int src1 = 0;
        int[] shortestDistance1 = solution.bellmanFord(V1, edges1, src1);
        System.out.println(Arrays.toString(shortestDistance1));

        int V2 = 4;
        int[][] edges2 = { { 0, 1, 4 }, { 1, 2, -6 }, { 2, 3, 5 }, { 3, 1, -2 } };
        int src2 = 0;
        int[] shortestDistance2 = solution.bellmanFord(V2, edges2, src2);
        System.out.println(Arrays.toString(shortestDistance2));
    }

    /**
     * Approach : Bellman-Ford Algorithm
     * 
     * TC: O((V x E) + E) ~ O(V x E)
     * SC: O(V)
     */
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int[] minDist = new int[V]; // SC: O(V)
        Arrays.fill(minDist, (int) 1e8);
        minDist[src] = 0;
        // relaxing the edges (V - 1) times
        for (int i = 1; i <= V - 1; i++) { // TC: O(V)
            for (int[] edge : edges) { // TC: O(E)
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if (minDist[u] != (int) 1e8 && w + minDist[u] < minDist[v]) {
                    minDist[v] = w + minDist[u];
                }
            }
        }
        // relaxing the edge once more to detect 'negative weight cycle'
        for (int[] edge : edges) { // TC: O(E)
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (minDist[u] != (int) 1e8 && w + minDist[u] < minDist[v]) {
                return new int[] { -1 };
            }
        }
        return minDist;
    }
}
