package Dynamic_Programming.DP_2D.P2_Unique_Paths_II;

import java.util.Arrays;

public class Unique_Paths_II {
    public static void main(String[] args) {
        Unique_Paths_II solution = new Unique_Paths_II();

        int[][] obstacleGrid = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };

        int uniquePathsRecursion = solution.uniquePathsWithObstaclesRecursion(obstacleGrid);
        System.out.println(uniquePathsRecursion);

        int uniquePathsMemoization = solution.uniquePathsWithObstaclesMemoization(obstacleGrid);
        System.out.println(uniquePathsMemoization);

        int uniquePathsTabulation = solution.uniquePathsWithObstaclesTabulation(obstacleGrid);
        System.out.println(uniquePathsTabulation);

        int uniquePathsOptimization = solution.uniquePathsWithObstaclesSpaceOptimization(obstacleGrid);
        System.out.println(uniquePathsOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O((M x N) + N) ~ O(M x N)
     * SC: O(N) + O(N) ~ O(N)
     * - O(N) + O(N) - memoization memory
     *
     * Accepted (42 / 42 testcases passed)
     */
    public int uniquePathsWithObstaclesSpaceOptimization(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            // when grid cell start (0, 0) has obstacle we can never reach grid[m - 1][n -
            // 1]
            return 0;
        }
        int[] prev = new int[n]; // SC: O(N)
        for (int j = 0; j < n; j++) { // TC: O(N)
            if (obstacleGrid[0][j] == 1) {
                // if obstacle found we cannot go horizontally right any more
                prev[j] = 0;
                break;
            } else {
                prev[j] = 1;
            }
        }
        for (int i = 1; i < m; i++) { // TC: O(M)
            int[] current = new int[n]; // SC: O(N)
            current[0] = obstacleGrid[i][0] == 1 || prev[0] == 0 ? 0 : 1;
            for (int j = 1; j < n; j++) { // TC: O(N)
                if (obstacleGrid[i][j] == 1) {
                    current[j] = 0;
                    continue;
                }
                int upPaths = prev[j];
                int leftPaths = current[j - 1];
                current[j] = upPaths + leftPaths;
            }
            prev = current.clone();
        }
        return prev[n - 1];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     *
     * TC: O((M x N) + (M + N))
     * SC: O(M x N)
     * - O((M x N) - memoization memory
     *
     * Accepted (42 / 42 testcases passed)
     */
    public int uniquePathsWithObstaclesTabulation(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            // when grid cell start (0, 0) has obstacle we can never reach grid[m - 1][n -
            // 1]
            return 0;
        }
        int[][] dp = new int[m][n]; // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            if (obstacleGrid[i][0] == 1) {
                // if obstacle found we cannot go vertically down any more
                dp[i][0] = 0;
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < n; j++) { // TC: O(N)
            if (obstacleGrid[0][j] == 1) {
                // if obstacle found we cannot go horizontally right any more
                dp[0][j] = 0;
                break;
            } else {
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) { // TC: O(M)
            for (int j = 1; j < n; j++) { // TC: O(N)
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                int upPaths = dp[i - 1][j];
                int leftPaths = dp[i][j - 1];
                dp[i][j] = upPaths + leftPaths;
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     *
     * TC: O(M x N)
     * SC: O(((M - 1) x (N - 1)) + (M - 1) + (N - 1))
     * - O((M - 1) x (N - 1)) - memoization memory
     * - O((M - 1) + (N - 1)) - recursion stack
     *
     * Accepted (42 / 42 testcases passed)
     */
    public int uniquePathsWithObstaclesMemoization(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            // when grid cell start (0, 0) has obstacle we can never reach grid[m - 1][n -
            // 1]
            return 0;
        }
        int[][] memo = new int[m][n]; // SC: O(M x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(m - 1, n - 1, obstacleGrid, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O((M - 1) x (N - 1))
     */
    private int solveMemoization(int m, int n, int[][] grid, int[][] memo) {
        // Base Case
        if (m == 0 && n == 0) {
            // number of paths to (0, 0) is 1
            return 1;
        }
        if (m < 0 || n < 0) {
            // out of bounds
            // number of paths to (0 , -ve) or (-ve, 0) is 0
            return 0;
        }
        if (grid[m][n] == 1) {
            // when grid cell has obstacle then number of paths through it is 0
            return 0;
        }
        // Memoization Check
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        // Recursion Calls
        int upPaths = solveMemoization(m - 1, n, grid, memo);
        int leftPaths = solveMemoization(m, n - 1, grid, memo);
        return memo[m][n] = upPaths + leftPaths;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M x N)) - from every cell we can move to 2 directions x total
     * cells(M x N)
     * SC: O((M - 1) x (N - 1)) - recursion stack
     *
     * Time Limit Exceeded (31 / 42 testcases passed)
     */
    public int uniquePathsWithObstaclesRecursion(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            // when grid cell start (0, 0) has obstacle we can never reach grid[m - 1][n -
            // 1]
            return 0;
        }
        return solveRecursion(m - 1, n - 1, obstacleGrid);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M x N))
     * SC: O((M - 1) x (N - 1))
     */
    private int solveRecursion(int m, int n, int[][] grid) {
        // Base Case
        if (m == 0 && n == 0) {
            // number of paths to (0, 0) is 1
            return 1;
        }
        if (m < 0 || n < 0) {
            // out of bounds
            // number of paths to (0 , -ve) or (-ve, 0) is 0
            return 0;
        }
        if (grid[m][n] == 1) {
            // when grid cell has obstacle then number of paths through it is 0
            return 0;
        }
        // Recursion Calls
        int upPaths = solveRecursion(m - 1, n, grid);
        int leftPaths = solveRecursion(m, n - 1, grid);
        return upPaths + leftPaths;
    }
}
