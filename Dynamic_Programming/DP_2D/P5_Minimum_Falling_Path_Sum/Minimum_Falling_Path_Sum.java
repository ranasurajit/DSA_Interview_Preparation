package Dynamic_Programming.DP_2D.P5_Minimum_Falling_Path_Sum;

import java.util.Arrays;

public class Minimum_Falling_Path_Sum {
    public static void main(String[] args) {
        Minimum_Falling_Path_Sum solution = new Minimum_Falling_Path_Sum();

        int[][] matrix = { { 2, 1, 3 }, { 6, 5, 4 }, { 7, 8, 9 } };

        int minFallingPathSumRecursion = solution.minFallingPathSumRecursion(matrix);
        System.out.println(minFallingPathSumRecursion);

        int minFallingPathSumMemoization = solution.minFallingPathSumMemoization(matrix);
        System.out.println(minFallingPathSumMemoization);

        int minFallingPathSumTabulation = solution.minFallingPathSumTabulation(matrix);
        System.out.println(minFallingPathSumTabulation);

        int minFallingPathSumSpaceOptimization = solution.minFallingPathSumSpaceOptimization(matrix);
        System.out.println(minFallingPathSumSpaceOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O(N ^ 2) + O(N) + O(N) ~ O(N ^ 2)
     * SC: O(N) + O(N)
     *
     * - O(N) - dp table memory
     *
     * Accepted (52 / 52 testcases passed)
     */
    public int minFallingPathSumSpaceOptimization(int[][] matrix) {
        int n = matrix.length;
        int[] next = new int[n]; // SC: O(N x N)
        for (int j = 0; j < n; j++) { // TC: O(N)
            next[j] = matrix[n - 1][j];
        }
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            int[] current = new int[n];
            for (int j = 0; j < n; j++) { // TC: O(N)
                int up = matrix[i][j] + next[j];
                int leftDiag = (int) 1e9;
                if (j > 0) {
                    leftDiag = matrix[i][j] + next[j - 1];
                }
                int rightDiag = (int) 1e9;
                if (j < n - 1) {
                    rightDiag = matrix[i][j] + next[j + 1];
                }
                current[j] = Math.min(up, Math.min(leftDiag, rightDiag));
            }
            next = current.clone();
        }
        int minSum = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) { // TC: O(N)
            minSum = Math.min(minSum, next[j]);
        }
        return minSum;
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     *
     * TC: O(N ^ 2) + O(N) + O(N) ~ O(N ^ 2)
     * SC: O(N ^ 2)
     *
     * - O(N x N) - dp table memory
     *
     * Accepted (52 / 52 testcases passed)
     */
    public int minFallingPathSumTabulation(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n]; // SC: O(N x N)
        for (int j = 0; j < n; j++) { // TC: O(N)
            dp[n - 1][j] = matrix[n - 1][j];
        }
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                int up = matrix[i][j] + dp[i + 1][j];
                int leftDiag = (int) 1e9;
                if (j > 0) {
                    leftDiag = matrix[i][j] + dp[i + 1][j - 1];
                }
                int rightDiag = (int) 1e9;
                if (j < n - 1) {
                    rightDiag = matrix[i][j] + dp[i + 1][j + 1];
                }
                dp[i][j] = Math.min(up, Math.min(leftDiag, rightDiag));
            }
        }
        int minSum = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) { // TC: O(N)
            minSum = Math.min(minSum, dp[0][j]);
        }
        return minSum;
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     *
     * TC: O(N ^ 3)
     * SC: O(N ^ 2) + O(N)
     *
     * - O(N x N) - memoization memory run for N times
     * - O(N) - recursion stack (reused)
     *
     * Time Limit Exceeded (49 / 52 testcases passed)
     */
    public int minFallingPathSumMemoization(int[][] matrix) {
        int n = matrix.length;
        int minSum = Integer.MAX_VALUE;
        int[][] memo = new int[n][n]; // SC: O(N x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        for (int j = 0; j < n; j++) { // TC: O(N)
            minSum = Math.min(minSum, solveMemoization(0, j, n, matrix, memo)); // TC: O(N x N), SC: O(N)
        }
        return minSum;
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    private int solveMemoization(int row, int col, int n, int[][] matrix, int[][] memo) {
        // Base Case
        if (col < 0 || col >= n) {
            return (int) 1e9;
        }
        if (row == n - 1) {
            return matrix[row][col];
        }
        // Memoization Check
        if (memo[row][col] != -1) {
            return memo[row][col];
        }
        // Recursion Calls
        int op1 = solveMemoization(row + 1, col, n, matrix, memo);
        int op2 = (int) 1e9;
        if (col > 0) {
            op2 = solveMemoization(row + 1, col - 1, n, matrix, memo);
        }
        int op3 = (int) 1e9;
        if (col < n - 1) {
            op3 = solveMemoization(row + 1, col + 1, n, matrix, memo);
        }
        return memo[row][col] = matrix[row][col] + Math.min(op1, Math.min(op2, op3));
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(N x 3 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (38 / 52 testcases passed)
     */
    public int minFallingPathSumRecursion(int[][] matrix) {
        int n = matrix.length;
        int minSum = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) { // TC: O(N)
            minSum = Math.min(minSum, solveRecursion(0, j, n, matrix));
        }
        return minSum;
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(3 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int row, int col, int n, int[][] matrix) {
        // Base Case
        if (col < 0 || col >= n) {
            return (int) 1e9;
        }
        if (row == n - 1) {
            return matrix[row][col];
        }
        // Recursion Calls
        int op1 = solveRecursion(row + 1, col, n, matrix);
        int op2 = (int) 1e9;
        if (col > 0) {
            op2 = solveRecursion(row + 1, col - 1, n, matrix);
        }
        int op3 = (int) 1e9;
        if (col < n - 1) {
            op3 = solveRecursion(row + 1, col + 1, n, matrix);
        }
        return matrix[row][col] + Math.min(op1, Math.min(op2, op3));
    }
}
