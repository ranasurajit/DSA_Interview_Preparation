package Graphs_Backup.Shortest_Path_Algorithms.P5_Floyd_Warshall;

import java.util.Arrays;

public class Floyd_Warshall {
    public static void main(String[] args) {
        Floyd_Warshall solution = new Floyd_Warshall();

        int[][] mat = { { 0, 1, 43 }, { 1, 0, 6 }, { -1, -1, 0 } };

        solution.shortestDistance(mat);
        System.out.println(Arrays.deepToString(mat));
    }

    /**
     * Using Floyd Warshall Algorithm
     * 
     * TC: O(N ^ 3 + 2 x N ^ 2) ~ O(N ^ 3)
     * SC: O(1)
     * 
     * @param mat
     */
    public void shortestDistance(int[][] mat) {
        int n = mat.length; // n x n matrix
        /**
         * adding bigger value than one mentioned in constraints
         * against (i, j) which has no edge
         */
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (mat[i][j] == -1) {
                    mat[i][j] = 1001; // as -1 <= mat[i][j] <= 1000
                }
            }
        }
        // calculating shortest path by Floyd Warshall Algorithm
        for (int via = 0; via < n; via++) { // TC: O(N)
            for (int i = 0; i < n; i++) { // TC: O(N)
                for (int j = 0; j < n; j++) { // TC: O(N)
                    mat[i][j] = Math.min(mat[i][j], mat[i][via] + mat[via][j]);
                }
            }
        }
        // reverting back the values for (i, j) which has no edge
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (mat[i][j] == 1001) {
                    mat[i][j] = -1;
                }
            }
        }
    }
}
