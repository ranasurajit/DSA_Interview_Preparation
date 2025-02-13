package DP.Unbounded_Knapsack.P02_Rod_Cutting;

import java.util.Arrays;

public class Rod_Cutting {
    public static void main(String[] args) {
        Rod_Cutting solution = new Rod_Cutting();

        int price[] = { 3, 5, 8, 9, 10, 17, 17, 20 };

        int maximumValueRecursion = solution.cutRodRecursion(price);
        System.out.println(maximumValueRecursion);

        int maximumValueMemoization = solution.cutRodMemoization(price);
        System.out.println(maximumValueMemoization);

        int maximumValueTabulation = solution.cutRodTabulation(price);
        System.out.println(maximumValueTabulation);
    }

    /**
     * Using Tabulation
     * 
     * TC: O(N x W + N)
     * SC: O(N x W)
     * 
     * @param price
     * @return
     */
    public int cutRodTabulation(int[] price) {
        int n = price.length;
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) {
            wt[i] = i + 1;
        }
        int w = n;
        // initialization - 0th row and 0th column should be zero so no changes needed
        int[][] dp = new int[n + 1][w + 1];
        // iterative code
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < w + 1; j++) {
                // convert (n, w) to (i, j)
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
     * Using Memoization
     * 
     * TC: O(N x W + N)
     * SC: O(N x W + N)
     * 
     * @param price
     * @return
     */
    public int cutRodMemoization(int[] price) {
        int n = price.length;
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) {
            wt[i] = i + 1;
        }
        int w = n;
        int[][] memo = new int[n + 1][w + 1];
        for (int[] memoItem : memo) {
            Arrays.fill(memoItem, -1);
        }
        return solveMemoization(wt, price, w, n, memo);
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x W)
     * SC: O(N x W + N)
     * 
     * @param wt
     * @param val
     * @param w
     * @param n
     * @param memo
     * @return
     */
    private int solveMemoization(int[] wt, int[] val, int w, int n, int[][] memo) {
        // Base case
        if (n == 0 || w == 0) {
            return 0;
        }
        // Memoization call
        if (memo[n][w] != -1) {
            return memo[n][w];
        }
        // Recursion call
        if (wt[n - 1] <= w) {
            // we have two options whether to pick or not pick
            int pick = val[n - 1] + solveMemoization(wt, val, w - wt[n - 1], n, memo);
            int notpick = solveMemoization(wt, val, w, n - 1, memo);
            return memo[n][w] = Math.max(pick, notpick);
        } else {
            return memo[n][w] = solveMemoization(wt, val, w, n - 1, memo);
        }
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     * 
     * @param price
     * @return
     */
    public int cutRodRecursion(int[] price) {
        int n = price.length;
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) {
            wt[i] = i + 1;
        }
        int w = n;
        return solveRecursion(wt, price, w, n);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     * 
     * @param wt
     * @param val
     * @param w
     * @param n
     * @return
     */
    private int solveRecursion(int[] wt, int[] val, int w, int n) {
        // Base case
        if (n == 0 || w == 0) {
            return 0;
        }
        // Recursion call
        if (wt[n - 1] <= w) {
            // we have two options whether to pick or not pick
            int pick = val[n - 1] + solveRecursion(wt, val, w - wt[n - 1], n);
            int notpick = solveRecursion(wt, val, w, n - 1);
            return Math.max(pick, notpick);
        } else {
            return solveRecursion(wt, val, w, n - 1);
        }
    }
}
