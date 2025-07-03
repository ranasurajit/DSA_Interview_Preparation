package Dynamic_Programming.DP_Stocks.P3_Best_Time_To_Buy_And_Sell_Stock_III;

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

        int maxOptimization = solution.maxProfitSpaceOptimization(prices);
        System.out.println(maxOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x 3 x 2) + O(N x 3 x 2) ~ O(N x 6)
     * SC: O(3 x 2) + O(3 x 2) ~ O(3 x 2) ~ O(1)
     * 
     * - O(3 x 2) - next and current array memory
     *
     * Accepted (214 / 214 testcases passed)
     */
    public int maxProfitSpaceOptimization(int[] prices) {
        int n = prices.length;
        int count = 2;
        int buy = 1;
        int[][] next = new int[count + 1][buy + 1]; // SC: O(3 x 2) buy flag - 0 or 1
        int[][] current = new int[count + 1][buy + 1]; // SC: O(3 x 2) buy flag - 0 or 1
        for (int j = 0; j < count + 1; j++) { // TC: O(3)
            for (int k = 0; k < buy + 1; k++) { // TC: O(2)
                if (j == 0) {
                    next[j][k] = 0;
                    current[j][k] = 0;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = 1; j < count + 1; j++) { // TC: O(3)
                for (int k = 0; k < buy + 1; k++) { // TC: O(N)
                    if (k == 1) {
                        // buy = 1
                        current[j][k] = Math.max(-1 * prices[i] + next[j][0], next[j][1]);
                    } else {
                        // buy = 0
                        current[j][k] = Math.max(prices[i] + next[j - 1][1], next[j][0]);
                    }
                }
            }
            next = current;
        }
        return next[2][1];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x 3 x 2) + O(N x 3 x 2) ~ O(N x 6)
     * SC: O(N x 3 x 2)
     * 
     * - O(N x 3 x 2) - dp array memory
     *
     * Accepted (214 / 214 testcases passed)
     */
    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int count = 2;
        int buy = 1;
        int[][][] dp = new int[n + 1][count + 1][buy + 1]; // SC: O(N x 3 x 2) buy flag - 0 or 1
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            for (int j = 0; j < count + 1; j++) { // TC: O(3)
                for (int k = 0; k < buy + 1; k++) { // TC: O(2)
                    if (i == n || j == 0) {
                        dp[i][j][k] = 0;
                    }
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = 1; j < count + 1; j++) { // TC: O(3)
                for (int k = 0; k < buy + 1; k++) { // TC: O(N)
                    if (k == 1) {
                        // buy = 1
                        dp[i][j][k] = Math.max(-1 * prices[i] + dp[i + 1][j][0], dp[i + 1][j][1]);
                    } else {
                        // buy = 0
                        dp[i][j][k] = Math.max(prices[i] + dp[i + 1][j - 1][1], dp[i + 1][j][0]);
                    }
                }
            }
        }
        return dp[0][2][1];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x 3 x 2) + O(N x 3 x 2) ~ O(N x 6)
     * SC: O(N x 3 x 2) + O(N)
     * 
     * - O(N x 3 x 2) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (214 / 214 testcases passed)
     */
    public int maxProfitMemoization(int[] prices) {
        int n = prices.length;
        int count = 2;
        int buy = 1;
        int[][][] memo = new int[n + 1][count + 1][buy + 1]; // SC: O(N x 3 x 2) buy flag - 0 or 1
        for (int[][] mem : memo) { // TC: O(N)
            for (int[] m : mem) { // TC: O(3)
                Arrays.fill(m, -1); // TC: O(2)
            }
        }
        // we need to start from index 0 only as we must buy first and then only sell
        return solveMemoization(0, n, prices, 1, count, memo); // TC: O(N x 3 x 2), SC: O(N)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x 3 x 2)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int[] prices, int buy, int count, int[][][] memo) {
        // Base Case
        if (count == 0 || idx == n) {
            // either the transactions or index exhaused
            return 0;
        }
        // Memoization Check
        if (memo[idx][count][buy] != -1) {
            return memo[idx][count][buy];
        }
        // Recursion Calls
        int profit = 0;
        if (buy == 1) {
            /**
             * we need to move to next index and if bought, mark buy = 0 and
             * count remains same as transaction will be completed only after
             * selling the stock or we decide not to buy the idx index stock
             */
            profit = Math.max(-1 * prices[idx] + solveMemoization(idx + 1, n, prices, 0, count, memo),
                    solveMemoization(idx + 1, n, prices, 1, count, memo));
        } else {
            /**
             * we need to move to next index and if sold, mark buy = 1 and
             * count = count - 1 as transaction will be completed if sold
             * or we decide not to sell the idx index stock retaining count as is
             */
            profit = Math.max(prices[idx] + solveMemoization(idx + 1, n, prices, 1, count - 1, memo),
                    solveMemoization(idx + 1, n, prices, 0, count, memo));
        }
        return memo[idx][count][buy] = profit;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (201 / 214 testcases passed)
     */
    public int maxProfitRecursion(int[] prices) {
        int n = prices.length;
        int count = 2;
        // we need to start from index 0 only as we must buy first and then only sell
        return solveRecursion(0, n, prices, 1, count); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int[] prices, int buy, int count) {
        // Base Case
        if (count == 0 || idx == n) {
            // either the transactions or index exhaused
            return 0;
        }
        // Recursion Calls
        int profit = 0;
        if (buy == 1) {
            /**
             * we need to move to next index and if bought, mark buy = 0 and
             * count remains same as transaction will be completed only after
             * selling the stock or we decide not to buy the idx index stock
             */
            profit = Math.max(-1 * prices[idx] + solveRecursion(idx + 1, n, prices, 0, count),
                    solveRecursion(idx + 1, n, prices, 1, count));
        } else {
            /**
             * we need to move to next index and if sold, mark buy = 1 and
             * count = count - 1 as transaction will be completed if sold
             * or we decide not to sell the idx index stock retaining count as is
             */
            profit = Math.max(prices[idx] + solveRecursion(idx + 1, n, prices, 1, count - 1),
                    solveRecursion(idx + 1, n, prices, 0, count));
        }
        return profit;
    }
}
