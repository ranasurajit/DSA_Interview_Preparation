package Dynamic_Programming.DP_Subsequences.P7_Knapsack_With_Duplicate_Items;

import java.util.Arrays;

public class Knapsack_With_Duplicate_Items {
    public static void main(String[] args) {
        Knapsack_With_Duplicate_Items solution = new Knapsack_With_Duplicate_Items();

        int val[] = { 6, 1, 7, 7 };
        int wt[] = { 1, 3, 4, 5 };
        int capacity = 8;

        int maximumValueRecursion = solution.knapSackRecursion(val, wt, capacity);
        System.out.println(maximumValueRecursion);

        int maximumValueMemoization = solution.knapSackMemoization(val, wt, capacity);
        System.out.println(maximumValueMemoization);

        int maximumValueTabulation = solution.knapSackTabulation(val, wt, capacity);
        System.out.println(maximumValueTabulation);

        int maximumValueOptimization = solution.knapSackSpaceOptimization(val, wt, capacity);
        System.out.println(maximumValueOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x W)
     * SC: O(W) + O(W)
     * 
     * - O(W) - prev and current array memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    private int knapSackSpaceOptimization(int val[], int wt[], int capacity) {
        int n = wt.length;
        // Initialization
        int[] prev = new int[capacity + 1]; // SC: O(W)
        /**
         * if n == 0, capacity > 0 we can get profit 0 as we cannot add anything to
         * knapsack
         * dp[0][0] to dp[0][capacity] = 0
         * if capacity == 0 we can get profit 0 as we cannot add anything to knapsack
         * dp[0][0] to dp[n][0] = 0
         */
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            int[] current = new int[capacity + 1]; // SC: O(W)
            for (int j = 1; j < capacity + 1; j++) { // TC: O(W)
                if (wt[i - 1] <= j) {
                    current[j] = Math.max(val[i - 1] + current[j - wt[i - 1]], prev[j]);
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[capacity];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x W) + O(N x W)
     * SC: O(N x W)
     * 
     * - O(N x W) - dp tabulation array memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    private int knapSackTabulation(int val[], int wt[], int capacity) {
        int n = wt.length;
        // Initialization
        int[][] dp = new int[n + 1][capacity + 1]; // SC: O(N x W)
        /**
         * if n == 0, capacity > 0 we can get profit 0 as we cannot add anything to
         * knapsack
         * dp[0][0] to dp[0][capacity] = 0
         * if capacity == 0 we can get profit 0 as we cannot add anything to knapsack
         * dp[0][0] to dp[n][0] = 0
         */
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < capacity + 1; j++) { // TC: O(W)
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(val[i - 1] + dp[i][j - wt[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][capacity];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x W) + O(N x W)
     * SC: O(N x W) + O(N)
     * 
     * - O(N x W) - memoization array memory
     * - O(N) - recursion stack
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    private int knapSackMemoization(int val[], int wt[], int capacity) {
        int n = wt.length;
        int[][] memo = new int[n + 1][capacity + 1]; // SC: O(N x W)
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(W)
        }
        return solveMemoization(n - 1, val, wt, capacity, memo); // TC: O(N x W), SC: O(N)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x W)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int[] val, int[] wt,
            int capacity, int[][] memo) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][capacity] != -1) {
            return memo[idx][capacity];
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (wt[idx] <= capacity) {
            // we have pick or skip weight at index 'idx'
            // we need not decrease idx as we have unlimited supply of wt[idx]
            pick = val[idx] + solveMemoization(idx, val, wt, capacity - wt[idx], memo);
            skip = solveMemoization(idx - 1, val, wt, capacity, memo);
        } else {
            // we cannot pick weight at index 'idx'
            skip = solveMemoization(idx - 1, val, wt, capacity, memo);
        }
        return memo[idx][capacity] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * 
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (1010 / 1111 testcases passed)
     */
    private int knapSackRecursion(int val[], int wt[], int capacity) {
        int n = wt.length;
        return solveRecursion(n - 1, val, wt, capacity);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int[] val, int[] wt, int capacity) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (wt[idx] <= capacity) {
            // we have pick or skip weight at index 'idx'
            // we need not decrease idx as we have unlimited supply of wt[idx]
            pick = val[idx] + solveRecursion(idx, val, wt, capacity - wt[idx]);
            skip = solveRecursion(idx - 1, val, wt, capacity);
        } else {
            // we cannot pick weight at index 'idx'
            skip = solveRecursion(idx - 1, val, wt, capacity);
        }
        return Math.max(pick, skip);
    }
}
