package Dynamic_Programming.DP_Stocks.P2_Best_Time_To_Buy_And_Sell_Stock_II;

import java.util.Arrays;

public class Best_Time_To_Buy_And_Sell_Stock_II {
    public static void main(String[] args) {
        Best_Time_To_Buy_And_Sell_Stock_II solution = new Best_Time_To_Buy_And_Sell_Stock_II();

        int[] prices = { 7, 1, 5, 3, 6, 4 };

        int maxRecursive = solution.maxProfitRecursion(prices);
        System.out.println(maxRecursive);

        int maxMemoization = solution.maxProfitMemoization(prices);
        System.out.println(maxMemoization);

        int maxTabulation = solution.maxProfitTabulation(prices);
        System.out.println(maxTabulation);

        int maxOptimization = solution.maxProfitSpaceOptimization(prices);
        System.out.println(maxOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x 2)
     * SC: O(2) + O(2) ~ O(1)
     * 
     * - O(2) ~ O(1) - next and current memory
     *
     * Accepted (202 / 202 testcases passed)
     */
    public int maxProfitSpaceOptimization(int[] prices) {
        int n = prices.length;
        int[] next = new int[2]; // SC: O(2) - buy flag - 0 or 1
        next[0] = next[1] = 0;
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            int[] current = new int[2]; // SC: O(2) - buy flag - 0 or 1
            for (int j = 0; j < 2; j++) { // TC: O(2)
                int profit = 0;
                if (j == 1) {
                    profit = Math.max(-1 * prices[i] + next[0], next[1]);
                } else {
                    profit = Math.max(prices[i] + next[1], next[0]);
                }
                current[j] = profit;
            }
            next = current;
        }
        return next[1];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x 2)
     * SC: O(N x 2)
     * 
     * - O(N x 2) - tabulation memory
     *
     * Accepted (202 / 202 testcases passed)
     */
    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2]; // SC: O(N x 2) - buy flag - 0 or 1
        dp[n][0] = dp[n][1] = 0;
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = 0; j < 2; j++) { // TC: O(2)
                int profit = 0;
                if (j == 1) {
                    profit = Math.max(-1 * prices[i] + dp[i + 1][0], dp[i + 1][1]);
                } else {
                    profit = Math.max(prices[i] + dp[i + 1][1], dp[i + 1][0]);
                }
                dp[i][j] = profit;
            }
        }
        return dp[0][1];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x 2) + O(N x 2) ~ O(N x 2)
     * SC: O(N x 2) + O(N)
     * 
     * - O(N x 2) - tabulation memory
     * - O(N) - recursion stack
     *
     * Accepted (202 / 202 testcases passed)
     */
    public int maxProfitMemoization(int[] prices) {
        int n = prices.length;
        int[][] memo = new int[n + 1][2]; // buy flag - 0 or 1
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(2)
        }
        // we need to start from index 0 only as we must buy first and then only sell
        return solveMemoization(0, prices, 1, memo); // TC: O(N x 2), SC: O(N)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int[] prices, int buy, int[][] memo) {
        // Base Case
        if (idx == prices.length) {
            // this will contribute 0 to profit
            return 0;
        }
        // Memoization Check
        if (memo[idx][buy] != -1) {
            return memo[idx][buy];
        }
        // Recursion Calls
        int profit = 0;
        if (buy == 1) {
            /**
             * we may buy the stock and set buy flag to 0 so that it
             * will not be bought again or skip buying the stock
             */
            profit = Math.max(-1 * prices[idx] + solveMemoization(idx + 1, prices, 0, memo),
                    solveMemoization(idx + 1, prices, 1, memo));
        } else {
            /**
             * we may sell the stock and set buy flag to 1 so that it
             * will not be bought again or skip selling the stock
             */
            profit = Math.max(prices[idx] + solveMemoization(idx + 1, prices, 1, memo),
                    solveMemoization(idx + 1, prices, 0, memo));
        }
        return memo[idx][buy] = profit;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (198 / 202 testcases passed)
     */
    public int maxProfitRecursion(int[] prices) {
        // we need to start from index 0 only as we must buy first and then only sell
        return solveRecursion(0, prices, 1); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int[] prices, int buy) {
        // Base Case
        if (idx == prices.length) {
            // this will contribute 0 to profit
            return 0;
        }
        // Recursion Calls
        int profit = 0;
        if (buy == 1) {
            /**
             * we may buy the stock and set buy flag to 0 so that it
             * will not be bought again or skip buying the stock
             */
            profit = Math.max(-1 * prices[idx] + solveRecursion(idx + 1, prices, 0),
                    solveRecursion(idx + 1, prices, 1));
        } else {
            /**
             * we may sell the stock and set buy flag to 1 so that it
             * will not be bought again or skip selling the stock
             */
            profit = Math.max(prices[idx] + solveRecursion(idx + 1, prices, 1),
                    solveRecursion(idx + 1, prices, 0));
        }
        return profit;
    }
}
