package Dynamic_Programming.DP_2D.P3_Minimum_Path_Sum;

import java.util.Arrays;

public class Minimum_Path_Sum {
    public static void main(String[] args) {
        Minimum_Path_Sum solution = new Minimum_Path_Sum();

        int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        int minSumRecursion = solution.minPathSumRecursion(grid);
        System.out.println(minSumRecursion);

        int minSumMemoization = solution.minPathSumMemoization(grid);
        System.out.println(minSumMemoization);

        int minSumTabulation = solution.minPathSumTabulation(grid);
        System.out.println(minSumTabulation);

        int minSumOptimization = solution.minPathSumSpaceOptimization(grid);
        System.out.println(minSumOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O(M x N + N) ~ O(M x N)
     * SC: O(N) + O(N) ~ O(N)
     *
     * Accepted (66 / 66 testcases passed)
     */
    public int minPathSumSpaceOptimization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] prev = new int[n];
        int[] current = new int[n];
        prev[0] = grid[0][0];
        current[0] = grid[0][0];
        for (int j = 1; j < n; j++) { // TC: O(N)
            prev[j] = prev[j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) { // TC: O(M)
            current[0] += grid[i][0];
            for (int j = 1; j < n; j++) { // TC: O(N)
                int pathSumTop = prev[j];
                int pathSumLeft = current[j - 1];
                current[j] = grid[i][j] + Math.min(pathSumTop, pathSumLeft);
            }
            prev = current.clone();
        }
        return prev[n - 1];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     *
     * TC: O(M x N + (M + N))
     * SC: O(M x N)
     *
     * Accepted (66 / 66 testcases passed)
     */
    public int minPathSumTabulation(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) { // TC: O(M)
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) { // TC: O(N)
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) { // TC: O(M)
            for (int j = 1; j < n; j++) { // TC: O(N)
                int pathSumTop = dp[i - 1][j];
                int pathSumLeft = dp[i][j - 1];
                dp[i][j] = grid[i][j] + Math.min(pathSumTop, pathSumLeft);
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     *
     * TC: O(M x N)
     * SC: O((M x N) + (M - 1) + (N - 1))
     *
     * Accepted (66 / 66 testcases passed)
     */
    public int minPathSumMemoization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(m - 1, n - 1, grid, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O((M x N) + (M - 1) + (N - 1))
     */
    private int solveMemoization(int m, int n, int[][] grid, int[][] memo) {
        // Base Case
        if (m == 0 && n == 0) {
            return grid[0][0];
        }
        if (m < 0 || n < 0) {
            // as we need minimum value so invalid case is returned with a higher value
            return (int) 1e9;
        }
        // Memoization Check
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        // Recursion Calls
        // Hypothesis
        int pathSumTop = grid[m][n] + solveMemoization(m - 1, n, grid, memo);
        int pathSumLeft = grid[m][n] + solveMemoization(m, n - 1, grid, memo);
        // Induction
        return memo[m][n] = Math.min(pathSumTop, pathSumLeft);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ ((M - 1) x (N - 1)))
     * SC: O((M - 1) + (N - 1))
     *
     * Time Limit Exceeded (25 / 66 testcases passed)
     */
    public int minPathSumRecursion(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return solveRecursion(m - 1, n - 1, grid);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ ((M - 1) x (N - 1)))
     * SC: O((M - 1) + (N - 1))
     */
    private int solveRecursion(int m, int n, int[][] grid) {
        // Base Case
        if (m == 0 && n == 0) {
            return grid[0][0];
        }
        if (m < 0 || n < 0) {
            // as we need minimum value so invalid case is returned with a higher value
            return (int) 1e9;
        }
        // Recursion Calls
        // Hypothesis
        int pathSumTop = grid[m][n] + solveRecursion(m - 1, n, grid);
        int pathSumLeft = grid[m][n] + solveRecursion(m, n - 1, grid);
        // Induction
        return Math.min(pathSumTop, pathSumLeft);
    }
}
