package Dynamic_Programming.DP_Subsequences.P1_Zero_One_Knapsack_Problem;

import java.util.Arrays;

public class Zero_One_Knapsack_Problem {
    public static void main(String[] args) {
        Zero_One_Knapsack_Problem solution = new Zero_One_Knapsack_Problem();

        int W = 7;
        int val[] = { 10, 8, 6 };
        int wt[] = { 1, 7, 9 };
        int maximumValueRecursion = solution.knapsackRecursion(W, val, wt);
        System.out.println(maximumValueRecursion);

        int maximumValueMemoization = solution.knapsackMemoization(W, val, wt);
        System.out.println(maximumValueMemoization);

        int maximumValueTabulation = solution.knapsackTabulation(W, val, wt);
        System.out.println(maximumValueTabulation);

        int maximumValueOptimization = solution.knapsackOptimization(W, val, wt);
        System.out.println(maximumValueOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x W)
     * SC: O(W) + O(W) ~ O(W)
     */
    private int knapsackOptimization(int W, int val[], int wt[]) {
        int n = wt.length;
        int[] prev = new int[W + 1]; // SC: O(W)
        // Initialization
        /**
         * prev will all be zero as n = 0 how much ever the
         * weights are we cannot put anything in knapsack
         * 
         * similarly, prev[0] = 0 and current[0] = 0 as if W = 0, how many ever
         * weights we have we cannot put anything in knapsack
         */
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            int[] current = new int[W + 1]; // SC: O(W)
            current[0] = 0;
            for (int j = 1; j < W + 1; j++) { // TC: O(W)
                if (wt[i - 1] <= j) {
                    current[j] = Math.max(val[i - 1] + prev[j - wt[i - 1]], prev[j]);
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[W];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x W)
     * SC: O(N x W)
     */
    private int knapsackTabulation(int W, int val[], int wt[]) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1]; // SC: O(N x W)
        // Initialization
        /**
         * dp[0][i] will all be zero as n = 0 how much ever the
         * weights are we cannot put anything in knapsack
         * 
         * similarly, dp[i][0] = 0 as if W = 0, how many ever
         * weights we have we cannot put anything in knapsack
         */
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < W + 1; j++) { // TC: O(W)
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][W];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x W)
     * SC: O(N x W) + O(N)
     */
    private int knapsackMemoization(int W, int val[], int wt[]) {
        int n = wt.length;
        int[][] memo = new int[n + 1][W + 1]; // SC: O(N x W)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(n - 1, W, val, wt, memo);
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x W)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int W, int[] val, int[] wt, int[][] memo) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][W] != -1) {
            return memo[idx][W];
        }
        // Recursion Calls
        // we can take or skip wt[idx] based upon limit of total weight W
        if (wt[idx] <= W) {
            // we can decide to take or skip
            // take
            int take = val[idx] + solveMemoization(idx - 1, W - wt[idx], val, wt, memo);
            // skip
            int skip = solveMemoization(idx - 1, W, val, wt, memo);
            return memo[idx][W] = Math.max(take, skip);
        } else {
            // we cannot take wt[idx]
            return memo[idx][W] = solveMemoization(idx - 1, W, val, wt, memo);
        }
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int knapsackRecursion(int W, int val[], int wt[]) {
        int n = wt.length;
        return solveRecursion(n - 1, W, val, wt);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int W, int[] val, int[] wt) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Recursion Calls
        // we can take or skip wt[idx] based upon limit of total weight W
        if (wt[idx] <= W) {
            // we can decide to take or skip
            // take
            int take = val[idx] + solveRecursion(idx - 1, W - wt[idx], val, wt);
            // skip
            int skip = solveRecursion(idx - 1, W, val, wt);
            return Math.max(take, skip);
        } else {
            // we cannot take wt[idx]
            return solveRecursion(idx - 1, W, val, wt);
        }
    }
}
