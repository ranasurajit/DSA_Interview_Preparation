package DP.DP_Grids.P3_Unique_Paths_With_Obstacles;

import java.util.Arrays;

public class Unique_Paths_II {
    public static void main(String[] args) {
        int[][] obstacleGrid = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };

        int uniquePathsRecursion = uniquePathsWithObstaclesRecursion(obstacleGrid);
        System.out.println(uniquePathsRecursion);

        int uniquePathsMemoization = uniquePathsWithObstaclesMemoization(obstacleGrid);
        System.out.println(uniquePathsMemoization);

        int uniquePathsTabulation = uniquePathsWithObstaclesTabulation(obstacleGrid);
        System.out.println(uniquePathsTabulation);

        int uniquePathsOptimization = uniquePathsWithObstaclesSpaceOptimization(obstacleGrid);
        System.out.println(uniquePathsOptimization);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ (M x N))
     * SC: O((M - 1) + (N - 1))
     * 
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstaclesRecursion(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        return solveRecursion(m - 1, n - 1, obstacleGrid);
    }

    private static int solveRecursion(int m, int n, int[][] grid) {
        // Base case
        if (m >= 0 && n >= 0 && grid[m][n] == 1) {
            return 0;
        }
        if (m == 0 && n == 0) {
            return 1;
        }
        if (m < 0 || n < 0) {
            return 0;
        }
        int up = solveRecursion(m - 1, n, grid);
        int left = solveRecursion(m, n - 1, grid);
        return up + left;
    }

    /**
     * Using Memoization
     * 
     * TC: O(M x N)
     * SC: O((M - 1) + (N - 1) + (M x N))
     * 
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstaclesMemoization(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int[] dp1D : dp) {
            Arrays.fill(dp1D, -1);
        }
        return solveMemoization(m - 1, n - 1, obstacleGrid, dp);
    }

    private static int solveMemoization(int m, int n, int[][] grid, int[][] dp) {
        // Base case
        if (m >= 0 && n >= 0 && grid[m][n] == 1) {
            return 0;
        }
        if (m == 0 && n == 0) {
            return 1;
        }
        if (m < 0 || n < 0) {
            return 0;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        int up = solveMemoization(m - 1, n, grid, dp);
        int left = solveMemoization(m, n - 1, grid, dp);
        dp[m][n] = up + left;
        return dp[m][n];
    }

    /**
     * Using Tabulation
     * 
     * TC: O(M x N)
     * SC: O(M x N)
     * 
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstaclesTabulation(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= 0 && j >= 0 && obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if (i > 0) {
                    up = dp[i - 1][j];
                }
                if (j > 0) {
                    left = dp[i][j - 1];
                }
                dp[i][j] = up + left;
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
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstaclesSpaceOptimization(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] prev = new int[n]; // SC: O(N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            int[] current = new int[n]; // SC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (i >= 0 && j >= 0 && obstacleGrid[i][j] == 1) {
                    current[j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    current[j] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if (i > 0) {
                    up = prev[j];
                }
                if (j > 0) {
                    left = current[j - 1];
                }
                current[j] = up + left;
            }
            prev = current;
        }
        return prev[n - 1];
    }
}
