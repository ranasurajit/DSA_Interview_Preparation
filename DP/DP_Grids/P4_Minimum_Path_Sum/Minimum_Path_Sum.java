package DP.DP_Grids.P4_Minimum_Path_Sum;

import java.util.Arrays;

public class Minimum_Path_Sum {
    public static void main(String[] args) {
        int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        int minSumRecursion = minPathSumRecursion(grid);
        System.out.println(minSumRecursion);

        int minSumMemoization = minPathSumMemoization(grid);
        System.out.println(minSumMemoization);

        int minSumTabulation = minPathSumTabulation(grid);
        System.out.println(minSumTabulation);

        int minSumOptimization = minPathSumSpaceOptimization(grid);
        System.out.println(minSumOptimization);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2^ (M x N))
     * SC: O((M - 1) + (N - 1))
     * 
     * @param m
     * @param n
     * @param grid
     * @return
     */
    public static int minPathSumRecursion(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return solveRecursion(m - 1, n - 1, grid);
    }

    private static int solveRecursion(int m, int n, int[][] grid) {
        // Base case
        if (m == 0 && n == 0) {
            return grid[0][0];
        }
        if (m < 0 || n < 0) {
            return (int) 1e9;
        }
        int up = grid[m][n] + solveRecursion(m - 1, n, grid);
        int left = grid[m][n] + solveRecursion(m, n - 1, grid);
        return Math.min(up, left);
    }

    /**
     * Using Memoization
     * 
     * TC: O(M x N)
     * SC: O((M - 1) + (N - 1) + (M x N))
     * 
     * @param m
     * @param n
     * @param grid
     * @return
     */
    public static int minPathSumMemoization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] dp1D : dp) {
            Arrays.fill(dp1D, -1);
        }
        return solveMemoization(m - 1, n - 1, grid, dp);
    }

    private static int solveMemoization(int m, int n, int[][] grid, int[][] dp) {
        // Base case
        if (m == 0 && n == 0) {
            return grid[0][0];
        }
        if (m < 0 || n < 0) {
            return (int) 1e9;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        int up = grid[m][n] + solveMemoization(m - 1, n, grid, dp);
        int left = grid[m][n] + solveMemoization(m, n - 1, grid, dp);
        dp[m][n] = Math.min(up, left);
        return dp[m][n];
    }

    /**
     * Using Tabulation
     * 
     * TC: O(M x N)
     * SC: O(M x N)
     * 
     * @param m
     * @param n
     * @param grid
     * @return
     */
    public static int minPathSumTabulation(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else {
                    int up = grid[i][j];
                    if (i > 0) {
                        up += dp[i - 1][j];
                    } else {
                        up += (int) 1e9;
                    }
                    int left = grid[i][j];
                    if (j > 0) {
                        left += dp[i][j - 1];
                    } else {
                        left += (int) 1e9;
                    }
                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * Using Space Optimization
     * 
     * TC: O(M x N)
     * SC: O(2N) ~ O(N)
     * 
     * @param m
     * @param n
     * @param grid
     * @return
     */
    public static int minPathSumSpaceOptimization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] prev = new int[n]; // SC: O(N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            int[] curr = new int[n]; // SC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (i == 0 && j == 0) {
                    curr[j] = grid[i][j];
                } else {
                    int up = grid[i][j];
                    if (i > 0) {
                        up += prev[j];
                    } else {
                        up += (int) 1e9;
                    }
                    int left = grid[i][j];
                    if (j > 0) {
                        left += curr[j - 1];
                    } else {
                        left += (int) 1e9;
                    }
                    curr[j] = Math.min(up, left);
                }
            }
            prev = curr;
        }
        return prev[n - 1];
    }
}
