package Dynamic_Programming.DP_Stocks.P4_Best_Time_To_Buy_And_Sell_Stock_IV;

import java.util.Arrays;

public class Best_Time_To_Buy_And_Sell_Stock_IV {
    public static void main(String[] args) {
        Best_Time_To_Buy_And_Sell_Stock_IV solution = new Best_Time_To_Buy_And_Sell_Stock_IV();

        int[] prices = { 3, 2, 6, 5, 0, 3 };
        int k = 2;

        int maxRecursive = solution.maxProfitRecursion(k, prices);
        System.out.println(maxRecursive);

        int maxMemoization = solution.maxProfitMemoization(k, prices);
        System.out.println(maxMemoization);

        int maxTabulation = solution.maxProfitTabulation(k, prices);
        System.out.println(maxTabulation);

        int maxOptimization = solution.maxProfitSpaceOptimization(k, prices);
        System.out.println(maxOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x K x 2) + O(K x 2) ~ O(N x K)
     * SC: O(K x 2) + O(K x 2) ~ O(K) + O(K)
     * 
     * - O(K) - next and current array memory
     *
     * Accepted (210 / 210 testcases passed)
     */
    public int maxProfitSpaceOptimization(int k, int[] prices) {
        int n = prices.length;
        int buy = 1;
        // Initialization
        int[][] next = new int[k + 1][buy + 1]; // SC: O(K x 2) buy flag - 0 or 1
        int[][] current = new int[k + 1][buy + 1]; // SC: O(K x 2) buy flag - 0 or 1
        for (int j = 0; j < k + 1; j++) { // TC: O(K)
            for (int b = 0; b < buy + 1; b++) { // TC: O(2)
                if (j == 0) {
                    next[j][b] = 0;
                    current[j][b] = 0;
                }
            }
        }
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = 1; j < k + 1; j++) { // TC: O(K)
                for (int b = 0; b < buy + 1; b++) { // TC: O(2)
                    if (b == 1) {
                        current[j][b] = Math.max(-1 * prices[i] + next[j][0], next[j][1]);
                    } else {
                        current[j][b] = Math.max(prices[i] + next[j - 1][1], next[j][0]);
                    }
                }
            }
            next = current;
        }
        return next[k][1];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x K x 2) + O(K x 2) ~ O(N x K)
     * SC: O(N x K x 2) ~ O(N x K)
     * 
     * - O(N x K x 2) - dp table memory
     *
     * Accepted (210 / 210 testcases passed)
     */
    public int maxProfitTabulation(int k, int[] prices) {
        int n = prices.length;
        int buy = 1;
        // Initialization
        int[][][] dp = new int[n + 1][k + 1][buy + 1]; // SC: O(N x K x 2) buy flag - 0 or 1
        for (int j = 0; j < k + 1; j++) { // TC: O(K)
            for (int b = 0; b < buy + 1; b++) { // TC: O(2)
                if (j == 0) {
                    dp[n][j][b] = 0;
                }
            }
        }
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = 1; j < k + 1; j++) { // TC: O(K)
                for (int b = 0; b < buy + 1; b++) { // TC: O(2)
                    if (b == 1) {
                        dp[i][j][b] = Math.max(-1 * prices[i] + dp[i + 1][j][0], dp[i + 1][j][1]);
                    } else {
                        dp[i][j][b] = Math.max(prices[i] + dp[i + 1][j - 1][1], dp[i + 1][j][0]);
                    }
                }
            }
        }
        return dp[0][k][1];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x K x 2) + O(N x K x 2) ~ O(N x K)
     * SC: O(N x K x 2) + O(N) ~ O(N x K) + O(N)
     * 
     * - O(N x K x 2) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (210 / 210 testcases passed)
     */
    public int maxProfitMemoization(int k, int[] prices) {
        int n = prices.length;
        int buy = 1;
        int[][][] memo = new int[n + 1][k + 1][buy + 1]; // SC: O(N x K x 2) buy flag - 0 or 1
        for (int[][] mem : memo) { // TC: O(N)
            for (int[] m : mem) { // TC: O(K)
                Arrays.fill(m, -1); // TC: O(2)
            }
        }
        // we need to start from index 0 only as we must buy first and then only sell
        return solveMemoization(0, n, prices, k, buy, memo); // TC: O(N x K x 2), SC: O(N)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x K x 2)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int[] prices, int k, int buy, int[][][] memo) {
        // Base Case
        if (k == 0 || idx == n) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][k][buy] != -1) {
            return memo[idx][k][buy];
        }
        // Recursion Calls
        int profit = 0;
        if (buy == 1) {
            // we can opt to buy or skip
            profit = Math.max(-1 * prices[idx] + solveMemoization(idx + 1, n, prices, k, 0, memo),
                    solveMemoization(idx + 1, n, prices, k, 1, memo));
        } else {
            // we can opt to sell or skip
            profit = Math.max(prices[idx] + solveMemoization(idx + 1, n, prices, k - 1, 1, memo),
                    solveMemoization(idx + 1, n, prices, k, 0, memo));
        }
        return memo[idx][k][buy] = profit;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (206 / 210 testcases passed)
     */
    public int maxProfitRecursion(int k, int[] prices) {
        int n = prices.length;
        int buy = 1;
        // we need to start from index 0 only as we must buy first and then only sell
        return solveRecursion(0, n, prices, k, buy); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int[] prices, int k, int buy) {
        // Base Case
        if (k == 0 || idx == n) {
            return 0;
        }
        // Recursion Calls
        int profit = 0;
        if (buy == 1) {
            // we can opt to buy or skip
            profit = Math.max(-1 * prices[idx] + solveRecursion(idx + 1, n, prices, k, 0),
                    solveRecursion(idx + 1, n, prices, k, 1));
        } else {
            // we can opt to sell or skip
            profit = Math.max(prices[idx] + solveRecursion(idx + 1, n, prices, k - 1, 1),
                    solveRecursion(idx + 1, n, prices, k, 0));
        }
        return profit;
    }
}
