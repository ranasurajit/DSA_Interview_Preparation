package Dynamic_Programming.DP_1D.P3_Frog_Jump_With_K_Distances;

import java.util.Arrays;

public class Frog_Jump_With_K_Distances {
    public static void main(String[] args) {
        Frog_Jump_With_K_Distances solution = new Frog_Jump_With_K_Distances();

        int[] heights1 = { 10, 5, 20, 0, 15 };
        int k1 = 2;
        int minCostRecursion = solution.frogJumpRecursion(heights1, k1);
        System.out.println(minCostRecursion);

        int[] heights2 = { 10, 5, 20, 0, 15 };
        int k2 = 2;
        int minCostMemoization = solution.frogJumpMemoization(heights2, k2);
        System.out.println(minCostMemoization);

        int[] heights3 = { 15, 4, 1, 14, 15 };
        int k3 = 3;
        int minCostTabulation = solution.frogJumpTabulation(heights3, k3);
        System.out.println(minCostTabulation);
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N x K) - N subproblems, K choices per subproblem
     * SC: O(N)
     *
     * Accepted (1115 / 1115 testcases passed)
     */
    public int frogJumpTabulation(int[] heights, int k) {
        int n = heights.length;
        int[] dp = new int[n]; // SC: O(N)
        dp[0] = 0;
        for (int i = 1; i < n; i++) { // TC: O(N)
            int minCost = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) { // TC: O(K)
                int currentCost = Integer.MAX_VALUE;
                if (j <= i) {
                    currentCost = Math.abs(heights[i] - heights[i - j]) + dp[i - j];
                }
                minCost = Math.min(minCost, currentCost);
            }
            dp[i] = minCost;
        }
        return dp[n - 1];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N x K) - N subproblems, K choices per subproblem
     * SC: O(N + N)
     * 
     * SC: O(N) for memoization array and O(N) recursion stack depth in worst case
     *
     * Accepted (1115 / 1115 testcases passed)
     */
    public int frogJumpMemoization(int[] heights, int k) {
        int n = heights.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return solveMemoization(n - 1, heights, k, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x K)
     * SC: O(N)
     */
    private int solveMemoization(int n, int[] heights, int k, int[] memo) {
        // Base Case
        if (n == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[n] != -1) {
            return memo[n];
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            int currentCost = Integer.MAX_VALUE;
            if (i <= n) {
                currentCost = Math.abs(heights[n] - heights[n - i]) +
                        solveMemoization(n - i, heights, k, memo);
            }
            minCost = Math.min(minCost, currentCost);
        }
        return memo[n] = minCost;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(K ^ N) — Exponential due to k choices at each step
     * SC: O(N)
     *
     * Time Limit Exceeded (10 / 1115 testcases passed)
     */
    public int frogJumpRecursion(int[] heights, int k) {
        int n = heights.length;
        return solveRecursion(n - 1, heights, k);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(K ^ N) — Exponential due to k choices at each step
     * SC: O(N)
     */
    private int solveRecursion(int n, int[] heights, int k) {
        // Base Case
        if (n == 0) {
            return 0;
        }
        // Recursion Calls
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            int currentCost = Integer.MAX_VALUE;
            if (i <= n) {
                currentCost = Math.abs(heights[n] - heights[n - i]) +
                        solveRecursion(n - i, heights, k);
            }
            minCost = Math.min(minCost, currentCost);
        }
        return minCost;
    }
}
