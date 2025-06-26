package Dynamic_Programming.DP_2D.P1_Unique_Paths;

import java.util.Arrays;

public class Unique_Paths {
    public static void main(String[] args) {
        Unique_Paths solution = new Unique_Paths();
        int m = 3;
        int n = 7;

        int pathRecursion = solution.uniquePathsRecursion(m, n);
        System.out.println(pathRecursion);

        int pathMemoization = solution.uniquePathsMemoization(m, n);
        System.out.println(pathMemoization);

        int pathTabulation = solution.uniquePathsTabulation(m, n);
        System.out.println(pathTabulation);

        int pathOptimize = solution.uniquePathsSpaceOptimization(m, n);
        System.out.println(pathOptimize);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O(M x N)
     * SC: O(N) + O(N) ~ O(N)
     * - O(N) - memory
     *
     * Accepted (63 / 63 testcases passed)
     */
    public int uniquePathsSpaceOptimization(int m, int n) {
        int[] prev = new int[n]; // SC: O(N)
        Arrays.fill(prev, 1);
        for (int i = 1; i < m; i++) { // TC: O(M)
            int[] current = new int[n]; // SC: O(N)
            current[0] = 1;
            for (int j = 1; j < n; j++) { // TC: O(N)
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
     * - O(M x N) - memory
     *
     * Accepted (63 / 63 testcases passed)
     */
    public int uniquePathsTabulation(int m, int n) {
        int[][] dp = new int[m][n]; // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) { // TC: O(N)
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) { // TC: O(M)
            for (int j = 1; j < n; j++) { // TC: O(M)
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
     * Accepted (63 / 63 testcases passed)
     */
    public int uniquePathsMemoization(int m, int n) {
        int[][] memo = new int[m][n]; // SC: O(M x N)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(m - 1, n - 1, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O((M - 1) + (N - 1)) - recursion stack
     */
    private int solveMemoization(int m, int n, int[][] memo) {
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
        // Memoization Check
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        // Recursion Calls
        // Hypothesis
        int upPaths = solveMemoization(m - 1, n, memo);
        int leftPaths = solveMemoization(m, n - 1, memo);
        // Induction
        return memo[m][n] = upPaths + leftPaths;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M x N)) - from every cell we can move to 2 directions x total
     * cells(M x N)
     * SC: O((M - 1) x (N - 1)) - recursion stack
     *
     * Time Limit Exceeded (37 / 63 testcases passed)
     */
    public int uniquePathsRecursion(int m, int n) {
        return solveRecursion(m - 1, n - 1);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M x N))
     * SC: O((M - 1) x (N - 1))
     */
    private int solveRecursion(int m, int n) {
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
        // Recursion Calls
        // Hypothesis
        int upPaths = solveRecursion(m - 1, n);
        int leftPaths = solveRecursion(m, n - 1);
        // Induction
        return upPaths + leftPaths;
    }
}
