package DP.DP_Stocks.P3_Best_Time_To_Buy_And_Sell_Stock_III;

import java.util.Arrays;

public class Best_Time_To_Buy_And_Sell_Stock_III {
    public static void main(String[] args) {
        Best_Time_To_Buy_And_Sell_Stock_III solution = new Best_Time_To_Buy_And_Sell_Stock_III();

        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };

        int maxRecursive = solution.maxProfitRecursion(prices);
        System.out.println(maxRecursive);

        int maxMemoization = solution.maxProfitMemoization(prices);
        System.out.println(maxMemoization);

        int maxTabulation = solution.maxProfitTabulation(prices);
        System.out.println(maxTabulation);

        int maxOptimization = solution.maxProfitOptimization(prices);
        System.out.println(maxOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(6 x N) ~ O(N)
     * SC: O(2) ~ O(1)
     */
    public int maxProfitOptimization(int[] prices) {
        int n = prices.length;
        int t = 2;
        // buy = 1 then you can buy else you can sell
        int[][] next = new int[2][t + 1]; // SC: O(6) ~ O(1)
        // Initialization - when k = 0 or index = n return 0 - redundant

        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            int[][] current = new int[2][t + 1]; // SC: O(6) ~ O(1)
            for (int buy = 0; buy < 2; buy++) { // TC: O(2)
                for (int k = 1; k < t + 1; k++) { // TC: O(3)
                    if (buy == 1) {
                        // buy
                        current[buy][k] = Math.max(next[1][k], -1 * prices[i] + next[0][k]);
                    } else {
                        // sell
                        current[buy][k] = Math.max(next[0][k], prices[i] + next[1][k - 1]);
                    }
                }
            }
            next = current;
        }
        // answer will be at buy = 1, k = 2
        return next[1][2];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(6 x N) ~ O(N)
     * SC: O(6 x N) ~ O(N)
     */
    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int t = 2;
        // buy = 1 then you can buy else you can sell
        int[][][] dp = new int[n + 1][2][t + 1]; // SC: O(6 x N)
        // Initialization - when k = 0 or index = n return 0
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            for (int j = 0; j < 2; j++) { // TC: O(2)
                for (int k = 0; k < t + 1; k++) { // TC: O(3)
                    if (i == n || k == 0) {
                        dp[i][j][k] = 0;
                    }
                }
            }
        }
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int buy = 0; buy < 2; buy++) { // TC: O(2)
                for (int k = 1; k < t + 1; k++) { // TC: O(3)
                    if (buy == 1) {
                        // buy
                        dp[i][buy][k] = Math.max(
                                dp[i + 1][1][k],
                                -1 * prices[i] + dp[i + 1][0][k]);
                    } else {
                        // sell
                        dp[i][buy][k] = Math.max(
                                dp[i + 1][0][k],
                                prices[i] + dp[i + 1][1][k - 1]);
                    }
                }
            }
        }
        // answer will be at i = 0, buy = 1, k = 2
        return dp[0][1][2];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(6 x N) ~ O(N)
     * SC: O(6 x N + N) ~ O(N + N)
     */
    public int maxProfitMemoization(int[] prices) {
        int n = prices.length;
        int k = 2;
        // buy = 1 then you can buy else you can sell
        int[][][] memo = new int[n + 1][2][3]; // SC: O(6 x N)
        for (int[][] memoItem : memo) {
            for (int[] mem : memoItem) {
                Arrays.fill(mem, -1);
            }
        }
        return solveMemoization(0, n, prices, 1, k, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(6 x N) ~ O(N)
     * SC: O(6 x N) ~ O(N)
     */
    private int solveMemoization(int index, int n, int[] prices,
            int buy, int k, int[][][] memo) {
        // Base Case
        if (index == n) {
            return 0;
        }
        if (k == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[index][buy][k] != -1) {
            return memo[index][buy][k];
        }
        // Recursion Calls
        int pick = 0;
        int notpick = 0;
        if (buy == 1) {
            // buy
            pick = -1 * prices[index] + solveMemoization(index + 1, n, prices, 0, k, memo);
            notpick = solveMemoization(index + 1, n, prices, 1, k, memo);
        } else {
            // sell
            pick = prices[index] + solveMemoization(index + 1, n, prices, 1, k - 1, memo);
            notpick = solveMemoization(index + 1, n, prices, 0, k, memo);
        }
        return memo[index][buy][k] = Math.max(pick, notpick);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    public int maxProfitRecursion(int[] prices) {
        int n = prices.length;
        int k = 2;
        // buy = 1 then you can buy else you can sell
        return solveRecursion(0, n, prices, 1, k);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int index, int n, int[] prices, int buy, int k) {
        // Base Case
        if (index == n) {
            return 0;
        }
        if (k == 0) {
            return 0;
        }
        // Recursion Calls
        int pick = 0;
        int notpick = 0;
        if (buy == 1) {
            // buy
            pick = -1 * prices[index] + solveRecursion(index + 1, n, prices, 0, k);
            notpick = solveRecursion(index + 1, n, prices, 1, k);
        } else {
            // sell
            pick = prices[index] + solveRecursion(index + 1, n, prices, 1, k - 1);
            notpick = solveRecursion(index + 1, n, prices, 0, k);
        }
        return Math.max(pick, notpick);
    }
}
