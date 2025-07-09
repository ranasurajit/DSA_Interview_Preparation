package Graphs_Backup.Shortest_Path_Algorithms.P4_Bellman_Ford;

import java.util.Arrays;

public class Bellman_Ford {
    public static void main(String[] args) {
        int V = 3;
        int[][] edges = { { 0, 1, 5 }, { 1, 0, 3 }, { 1, 2, -1 }, { 2, 0, 1 } };
        int src = 2;
        int[] minDist = bellmanFord(V, edges, src);
        System.out.println(Arrays.toString(minDist));
    }

    /**
     * Using Bellman-Ford Algorithm
     * 
     * TC: O(V x E + V + E) ~ O(V x E)
     * SC: O(1)
     * 
     * @param V
     * @param edges
     * @param src
     * @return
     */
    private static int[] bellmanFord(int V, int[][] edges, int src) {
        int[] minDist = new int[V];
        Arrays.fill(minDist, (int) 1e8); // TC: O(V)
        minDist[src] = 0;

        /**
         * relaxing the edges V - 1 times as per Bellman-Ford Algorithm
         * to get the shortest distance from source node/vertex to all
         * other nodes/verices
         */
        for (int i = 0; i <= V - 1; i++) { // TC: O(V)
            for (int[] edge : edges) { // TC: O(E)
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if (minDist[u] != (int) 1e8 && minDist[u] + w < minDist[v]) {
                    minDist[v] = minDist[u] + w;
                }
            }
        }
        /**
         * Bellman-Ford Algorithm helps to detect negative cycles as well
         * so let's relax the edges once-more to see if distance further
         * gets reduced then if so, then it indicates negative cycle in the
         * graph so would return { -1 } as per ask
         */
        for (int[] edge : edges) { // TC: O(E)
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (minDist[u] != (int) 1e8 && minDist[u] + w < minDist[v]) {
                return new int[] { -1 };
            }
        }
        return minDist;
    }
}
