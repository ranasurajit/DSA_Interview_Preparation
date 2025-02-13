package DP.Unbounded_Knapsack.P03_Coin_Change_Count_Ways;

import java.util.Arrays;

public class Coin_Change_Count_Ways {
    public static void main(String[] args) {
        Coin_Change_Count_Ways solution = new Coin_Change_Count_Ways();

        int[] coins = { 2, 5, 3, 6 };
        int sum = 10;

        int maximumValueRecursion = solution.countRecursion(coins, sum);
        System.out.println(maximumValueRecursion);

        int maximumValueMemoization = solution.countMemoization(coins, sum);
        System.out.println(maximumValueMemoization);

        int maximumValueTabulation = solution.countTabulation(coins, sum);
        System.out.println(maximumValueTabulation);
    }

    /**
     * Using Tabulation
     * 
     * TC: O(N x W + N)
     * SC: O(N x W)
     * 
     * @param coins
     * @param sum
     * @return
     */
    public int countTabulation(int coins[], int sum) {
        int n = coins.length;
        int[][] dp = new int[n + 1][sum + 1];
        // initialization
        /**
         * When n = 0, for any weight != 0 we have 0 ways
         */
        for (int i = 1; i < sum + 1; i++) {
            dp[0][i] = 0;
        }
        /**
         * When sum = 0, for any count n of coins, there will be exactly 1 way
         */
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        // iterative code
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                // convert (n, sum) as (i, j)
                if (coins[i - 1] <= j) {
                    dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // return the last cell of dp
        return dp[n][sum];
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x W + N)
     * SC: O(N x W + N)
     * 
     * @param coins
     * @param sum
     * @return
     */
    public int countMemoization(int coins[], int sum) {
        int n = coins.length;
        int[][] memo = new int[n + 1][sum + 1];
        for (int[] memoItem : memo) {
            Arrays.fill(memoItem, -1);
        }
        return solveMemoization(coins, sum, n, memo);
    }

    /**
     * Using Memoization
     * 
     * This problem is similar to knapsack subset-sum problem
     * 
     * TC: O(N x W)
     * SC: O(N x W + N)
     * 
     * @param coins
     * @param w
     * @param n
     * @paran memo
     * @return
     */
    private int solveMemoization(int[] coins, int w, int n, int[][] memo) {
        // Base case
        if (n == 0 && w == 0) {
            return 1;
        } else if (n == 0 && w != 0) {
            return 0;
        } else if (n != 0 && w == 0) {
            return 1;
        }
        // Memoization call
        if (memo[n][w] != -1) {
            return memo[n][w];
        }
        // Recursion calls
        if (coins[n - 1] <= w) {
            // we have two choices whether to pick or not pick a coin
            int pick = solveMemoization(coins, w - coins[n - 1], n, memo);
            // we can pick multiple times any coin
            int notpick = solveMemoization(coins, w, n - 1, memo);
            return memo[n][w] = pick + notpick;
        } else {
            // we cannot pick this coin
            return memo[n][w] = solveMemoization(coins, w, n - 1, memo);
        }
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     * 
     * @param coins
     * @param sum
     * @return
     */
    public int countRecursion(int coins[], int sum) {
        int n = coins.length;
        return solveRecursion(coins, sum, n);
    }

    /**
     * Using Recursion
     * 
     * This problem is similar to knapsack subset-sum problem
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     */
    private int solveRecursion(int[] coins, int w, int n) {
        // Base case
        if (n == 0 && w == 0) {
            return 1;
        } else if (n == 0 && w != 0) {
            return 0;
        } else if (n != 0 && w == 0) {
            return 1;
        }
        // Recursion calls
        if (coins[n - 1] <= w) {
            // we have two choices whether to pick or not pick a coin
            int pick = solveRecursion(coins, w - coins[n - 1], n);
            // we can pick multiple times any coin
            int notpick = solveRecursion(coins, w, n - 1);
            return pick + notpick;
        } else {
            // we cannot pick this coin
            return solveRecursion(coins, w, n - 1);
        }
    }
}
