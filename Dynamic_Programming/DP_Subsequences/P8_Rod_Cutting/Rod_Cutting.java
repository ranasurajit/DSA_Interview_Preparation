package Dynamic_Programming.DP_Subsequences.P8_Rod_Cutting;

import java.util.Arrays;

public class Rod_Cutting {
    public static void main(String[] args) {
        Rod_Cutting solution = new Rod_Cutting();

        int price[] = { 1, 5, 8, 9, 10, 17, 17, 20 };

        int maximumValueRecursion = solution.cutRodRecursion(price);
        System.out.println(maximumValueRecursion);

        int maximumValueMemoization = solution.cutRodMemoization(price);
        System.out.println(maximumValueMemoization);

        int maximumValueTabulation = solution.cutRodTabulation(price);
        System.out.println(maximumValueTabulation);

        int maximumValueOptimization = solution.cutRodSpaceOptimization(price);
        System.out.println(maximumValueOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x W) + O(N)
     * SC: O(W) + O(W)
     * 
     * - O(W) - prev and current array memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int cutRodSpaceOptimization(int[] price) {
        int n = price.length;
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(N)
            wt[i] = (i + 1);
        }
        int w = n;
        // Initialization
        int[] prev = new int[w + 1]; // SC: O(W)
        /**
         * if n == 0, w > 0 we can get profit 0 as we cannot add anything to
         * knapsack
         * dp[0][0] to dp[0][w] = 0
         * if w == 0 we can get profit 0 as we cannot add anything to knapsack
         * dp[0][0] to dp[n][0] = 0
         */
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            int[] current = new int[w + 1]; // SC: O(W)
            for (int j = 1; j < w + 1; j++) { // TC: O(W)
                if (wt[i - 1] <= j) {
                    current[j] = Math.max(price[i - 1] + current[j - wt[i - 1]], prev[j]);
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[w];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x W) + O(N)
     * SC: O(N x W)
     * 
     * - O(N x W) - memoization array memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int cutRodTabulation(int[] price) {
        int n = price.length;
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(N)
            wt[i] = (i + 1);
        }
        int w = n;
        // Initialization
        int[][] dp = new int[n + 1][w + 1]; // SC: O(N x W)
        /**
         * if n == 0, w > 0 we can get profit 0 as we cannot add anything to
         * knapsack
         * dp[0][0] to dp[0][w] = 0
         * if w == 0 we can get profit 0 as we cannot add anything to knapsack
         * dp[0][0] to dp[n][0] = 0
         */
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < w + 1; j++) { // TC: O(W)
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(price[i - 1] + dp[i][j - wt[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][w];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x W) + O(N x W) + O(N)
     * SC: O(N x W) + O(N)
     * 
     * - O(N x W) - memoization array memory
     * - O(N) - recursion stack
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int cutRodMemoization(int[] price) {
        int n = price.length;
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(N)
            wt[i] = (i + 1);
        }
        int w = n;
        int[][] memo = new int[n + 1][w + 1]; // SC: O(N x W)
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(W)
        }
        return solveMemoization(n - 1, w, price, wt, memo); // TC: O(N x W), SC: O(N)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x W)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int w, int[] price, int[] wt, int[][] memo) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][w] != -1) {
            return memo[idx][w];
        }
        // Recursive Calls
        int pick = 0;
        int skip = 0;
        if (wt[idx] <= w) {
            // there is unlimited supply i.e. rod can be cut into same length multiple times
            pick = price[idx] + solveMemoization(idx, w - wt[idx], price, wt, memo);
            skip = solveMemoization(idx - 1, w, price, wt, memo);
        } else {
            // we cannot cut the rod anymore
            skip = solveMemoization(idx - 1, w, price, wt, memo);
        }
        return memo[idx][w] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * 
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (10 / 1111 testcases passed)
     */
    public int cutRodRecursion(int[] price) {
        int n = price.length;
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) {
            wt[i] = (i + 1);
        }
        int w = n;
        return solveRecursion(n - 1, w, price, wt);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int w, int[] price, int[] wt) {
        // Base Case
        if (idx < 0) {
            return 0;
        }
        // Recursive Calls
        int pick = 0;
        int skip = 0;
        if (wt[idx] <= w) {
            // there is unlimited supply i.e. rod can be cut into same length multiple times
            pick = price[idx] + solveRecursion(idx, w - wt[idx], price, wt);
            skip = solveRecursion(idx - 1, w, price, wt);
        } else {
            // we cannot cut the rod anymore
            skip = solveRecursion(idx - 1, w, price, wt);
        }
        return Math.max(pick, skip);
    }
}
