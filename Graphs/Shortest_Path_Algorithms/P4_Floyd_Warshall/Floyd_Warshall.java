package Graphs.Shortest_Path_Algorithms.P4_Floyd_Warshall;

import java.util.Arrays;

public class Floyd_Warshall {
    public static void main(String[] args) {
        Floyd_Warshall solution = new Floyd_Warshall();

        int[][] dist = { { 0, 4, (int) 1e8, 5, (int) 1e8 }, { (int) 1e8, 0, 1, (int) 1e8, 6 },
                { 2, (int) 1e8, 0, 3, (int) 1e8 }, { (int) 1e8, (int) 1e8, 1, 0, 2 },
                { 1, (int) 1e8, (int) 1e8, 4, 0 } };
        int[][] shortestDistance = solution.floydWarshall(dist);
        System.out.println(Arrays.deepToString(shortestDistance));
    }

    /**
     * Approach : Floyd Warshall Algorithm
     * 
     * TC: O(N ^ 3)
     * SC: O(1)
     */
    public int[][] floydWarshall(int[][] dist) {
        int n = dist.length;
        for (int via = 0; via < n; via++) { // TC: O(N)
            for (int i = 0; i < n; i++) { // TC: O(N)
                for (int j = 0; j < n; j++) { // TC: O(N)
                    if (dist[i][via] != (int) 1e8 && dist[via][j] != (int) 1e8) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                    }
                }
            }
        }
        /**
         * as said in the problem, that graph will not contain any negative cycles
         * so, the optimal calculation in dist matrix is feasible
         */
        return dist;
    }
}
