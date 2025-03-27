package DP.DP_Stocks.P2_Best_Time_To_Buy_And_Sell_Stock_Multiple_Times;

import java.util.Arrays;

public class Best_Time_To_Buy_And_Sell_Stock_II {
    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };

        int maxRecursive = maxProfitUsingRecursion(prices);
        System.out.println(maxRecursive);

        int maxMemoization = maxProfitUsingMemoization(prices);
        System.out.println(maxMemoization);

        int maxTabulation = maxProfitUsingTabulation(prices);
        System.out.println(maxTabulation);

        int maxOptimization = maxProfitUsingSpaceOptimization(prices);
        System.out.println(maxOptimization);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * 
     * @param prices
     * @return
     */
    public static int maxProfitUsingRecursion(int[] prices) {
        /*
         * buy param is 1 to buy and 0 to sell, so in index = 0,
         * we can only buy so buy = 1
         */
        return solveRecursion(0, 1, prices);
    }

    private static int solveRecursion(int i, int buy, int[] prices) {
        // Base case
        if (i == prices.length) {
            // canot make profit on day at index = n (prices.length)
            return 0;
        }
        int profit = 0;
        if (buy == 1) {
            // Max(if I decide to buy or not buy on day at index i)
            profit = Math.max(-1 * prices[i] + solveRecursion(i + 1, 0, prices),
                    0 + solveRecursion(i + 1, 1, prices));
        } else {
            // Max(if I decide to sell or not sell on day at index i)
            profit = Math.max(prices[i] + solveRecursion(i + 1, 1, prices),
                    0 + solveRecursion(i + 1, 0, prices));
        }
        return profit;
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x 2) ~ O(N)
     * SC: O(N x 2) + O(N) ~ O(N x 3) ~ O(N)
     * 
     * @param prices
     * @return
     */
    public static int maxProfitUsingMemoization(int[] prices) {
        /*
         * buy param is 1 to buy and 0 to sell, so in index = 0,
         * we can only buy so buy = 1
         */
        // states are index and (buy/sell i.e. 1 or 0)
        int n = prices.length;
        int[][] dp = new int[n][2]; // SC: O(N x 2)
        for (int[] dp1D : dp) {
            Arrays.fill(dp1D, -1);
        }
        return solveMemoization(0, 1, prices, dp);
    }

    private static int solveMemoization(int i, int buy, int[] prices, int[][] dp) {
        // Base case
        if (i == prices.length) {
            // canot make profit on day at index = n (prices.length)
            return 0;
        }
        if (dp[i][buy] != -1) {
            return dp[i][buy];
        }
        int profit = 0;
        if (buy == 1) {
            // Max(if I decide to buy or not buy on day at index i)
            profit = Math.max(-1 * prices[i] + solveMemoization(i + 1, 0, prices, dp),
                    0 + solveMemoization(i + 1, 1, prices, dp));
        } else {
            // Max(if I decide to sell or not sell on day at index i)
            profit = Math.max(prices[i] + solveMemoization(i + 1, 1, prices, dp),
                    0 + solveMemoization(i + 1, 0, prices, dp));
        }
        dp[i][buy] = profit;
        return dp[i][buy];
    }

    /**
     * Using Tabulation
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     * 
     * @param prices
     * @return
     */
    public static int maxProfitUsingTabulation(int[] prices) {
        /*
         * buy param is 1 to buy and 0 to sell, so in index = 0,
         * we can only buy so buy = 1
         */
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        dp[n][0] = dp[n][1] = 0; // Base case
        for (int idx = n - 1; idx >= 0; idx--) { // TC: O(N)
            for (int buy = 0; buy < 2; buy++) { // TC: O(2)
                int profit = 0;
                if (buy == 1) {
                    // Max(if I decide to buy or not buy on day at index i)
                    profit = Math.max(-1 * prices[idx] + dp[idx + 1][0],
                            0 + dp[idx + 1][1]);
                } else {
                    // Max(if I decide to sell or not sell on day at index i)
                    profit = Math.max(prices[idx] + dp[idx + 1][1],
                            0 + dp[idx + 1][0]);
                }
                dp[idx][buy] = profit;
            }
        }
        return dp[0][1];
    }

    /**
     * Using Space Optimization
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 + 2) ~ O(1)
     * 
     * @param prices
     * @return
     */
    public static int maxProfitUsingSpaceOptimization(int[] prices) {
        /*
         * buy param is 1 to buy and 0 to sell, so in index = 0,
         * we can only buy so buy = 1
         */
        int n = prices.length;
        int[] prev = new int[2]; // SC: O(2)
        int[] curr = new int[2]; // SC: O(2)
        prev[0] = prev[1] = 0; // Base case
        for (int idx = n - 1; idx >= 0; idx--) { // TC: O(N)
            for (int buy = 0; buy < 2; buy++) { // TC: O(2)
                int profit = 0;
                if (buy == 1) {
                    // Max(if I decide to buy or not buy on day at index i)
                    profit = Math.max(-1 * prices[idx] + prev[0],
                            0 + prev[1]);
                } else {
                    // Max(if I decide to sell or not sell on day at index i)
                    profit = Math.max(prices[idx] + prev[1],
                            0 + prev[0]);
                }
                curr[buy] = profit;
            }
            prev = curr;
        }
        return prev[1];
    }
}
