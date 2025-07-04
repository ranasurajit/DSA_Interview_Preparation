package Dynamic_Programming.DP_Stocks.P5_Best_Time_To_Buy_And_Sell_Stock_With_Cooldown;

import java.util.Arrays;

public class Best_Time_To_Buy_And_Sell_Stock_With_Cooldown {
    public static void main(String[] args) {
        Best_Time_To_Buy_And_Sell_Stock_With_Cooldown solution = new Best_Time_To_Buy_And_Sell_Stock_With_Cooldown();

        int[] prices = { 1, 2, 3, 0, 2 };

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
     * SC: O(2) + O(2) + O(2) ~ O(2)
     * 
     * - O(2) - front1, front2 and current array memory
     *
     * Accepted (210 / 210 testcases passed)
     */
    public int maxProfitSpaceOptimization(int[] prices) {
        int n = prices.length;
        int buy = 1;
        // Initialization
        int[] front2 = new int[buy + 1]; // SC: O(2) buy flag - 0 or 1
        int[] front1 = new int[buy + 1]; // SC: O(2)
        int[] current = new int[buy + 1]; // SC: O(2)

        front2[0] = front2[1] = 0;
        front1[0] = front1[1] = 0;
        current[0] = current[1] = 0;
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = 0; j < 2; j++) { // TC: O(2)
                if (j == 1) {
                    current[j] = Math.max(-1 * prices[i] + front1[0], front1[1]);
                } else {
                    current[j] = Math.max(prices[i] + front2[1], front1[0]);
                }
            }
            front2 = front1.clone();
            front1 = current.clone();
        }
        return front1[1];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x 2)
     * SC: O(N x 2)
     * 
     * - O(N x 2) - dp array memory
     *
     * Accepted (210 / 210 testcases passed)
     */
    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int buy = 1;
        // Initialization
        int[][] dp = new int[n + 2][buy + 1]; // SC: O(N x 2) buy flag - 0 or 1
        dp[n][0] = dp[n][1] = 0;
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = 0; j < 2; j++) { // TC: O(2)
                if (j == 1) {
                    dp[i][j] = Math.max(-1 * prices[i] + dp[i + 1][0], dp[i + 1][1]);
                } else {
                    dp[i][j] = Math.max(prices[i] + dp[i + 2][1], dp[i + 1][0]);
                }
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
     * - O(N x 2) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (210 / 210 testcases passed)
     */
    public int maxProfitMemoization(int[] prices) {
        int n = prices.length;
        int buy = 1;
        int[][] memo = new int[n + 1][buy + 1]; // SC: O(N x 2) buy flag - 0 or 1
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(2)
        }
        // we need to start from index 0 only as we must buy first and then only sell
        return solveMemoization(0, n, prices, 1, memo); // TC: O(N x 2), SC: O(N)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x 2)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int[] prices, int buy, int[][] memo) {
        // Base Case
        if (idx >= n) {
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
             * you can opt to buy stock at index 'idx' and move to next index or
             * skip to next index
             */
            profit = Math.max(-1 * prices[idx] + solveMemoization(idx + 1, n, prices, 0, memo),
                    solveMemoization(idx + 1, n, prices, 1, memo));
        } else {
            /**
             * you can opt to sell stock at index 'idx' and move to (next + 1) index to
             * allow a cooldown
             * period of 1 day or skip to next index
             */
            profit = Math.max(prices[idx] + solveMemoization(idx + 2, n, prices, 1, memo),
                    solveMemoization(idx + 1, n, prices, 0, memo));
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
     * Time Limit Exceeded (208 / 210 testcases passed)
     */
    public int maxProfitRecursion(int[] prices) {
        int n = prices.length;
        int buy = 1;
        // we need to start from index 0 only as we must buy first and then only sell
        return solveRecursion(0, n, prices, buy); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int[] prices, int buy) {
        // Base Case
        if (idx >= n) {
            return 0;
        }
        // Recursion Calls
        int profit = 0;
        if (buy == 1) {
            /**
             * you can opt to buy stock at index 'idx' and move to next index or
             * skip to next index
             */
            profit = Math.max(-1 * prices[idx] + solveRecursion(idx + 1, n, prices, 0),
                    solveRecursion(idx + 1, n, prices, 1));
        } else {
            /**
             * you can opt to sell stock at index 'idx' and move to (next + 1) index to
             * allow a cooldown
             * period of 1 day or skip to next index
             */
            profit = Math.max(prices[idx] + solveRecursion(idx + 2, n, prices, 1),
                    solveRecursion(idx + 1, n, prices, 0));
        }
        return profit;
    }
}
