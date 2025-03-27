package DP.Unbounded_Knapsack.P01_Knapsack_With_Duplicate_Items;

import java.util.Arrays;

public class Knapsack_With_Duplicate_Items {
    public static void main(String[] args) {
        int val[] = { 6, 1, 7, 7 };
        int wt[] = { 1, 3, 4, 5 };
        int capacity = 8;

        int maximumValueRecursion = knapSackRecursion(val, wt, capacity);
        System.out.println(maximumValueRecursion);

        int maximumValueMemoization = knapSackMemoization(val, wt, capacity);
        System.out.println(maximumValueMemoization);

        int maximumValueTabulation = knapSackTabulation(val, wt, capacity);
        System.out.println(maximumValueTabulation);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     * 
     * @param val
     * @param wt
     * @param capacity
     * @return
     */
    static int knapSackRecursion(int val[], int wt[], int capacity) {
        int n = wt.length;
        return solveRecursion(val, wt, capacity, n);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     * 
     * @param val
     * @param wt
     * @param w
     * @param n
     * @return
     */
    private static int solveRecursion(int[] val, int[] wt, int w, int n) {
        // Base case
        if (n == 0 || w == 0) {
            return 0;
        }
        // Recursion calls
        if (wt[n - 1] <= w) {
            // we have options to pick or not pick
            // pick - we can pick as many times of item at index (n - 1)
            int pick = val[n - 1] + solveRecursion(val, wt, w - wt[n - 1], n);
            int notpick = solveRecursion(val, wt, w, n - 1);
            return Math.max(pick, notpick);
        } else {
            // cannot pick item at index (n - 1)
            return solveRecursion(val, wt, w, n - 1);
        }
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x W)
     * SC: O(N x W + N)
     * 
     * @param val
     * @param wt
     * @param capacity
     * @return
     */
    static int knapSackMemoization(int val[], int wt[], int capacity) {
        int n = wt.length;
        int[][] memo = new int[n + 1][capacity + 1];
        for (int[] memoItems : memo) {
            Arrays.fill(memoItems, -1);
        }
        return solveMemoization(val, wt, capacity, n, memo);
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x W)
     * SC: O(N x W + N)
     * 
     * @param val
     * @param wt
     * @param w
     * @param n
     * @param memo
     * @return
     */
    private static int solveMemoization(int[] val, int[] wt, int w, int n, int[][] memo) {
        // Base case
        if (n == 0 || w == 0) {
            return 0;
        }
        // Memoization
        if (memo[n][w] != -1) {
            return memo[n][w];
        }
        // Recursion calls
        if (wt[n - 1] <= w) {
            // we have options to pick or not pick
            // pick - we can pick as many times of item at index (n - 1)
            int pick = val[n - 1] + solveRecursion(val, wt, w - wt[n - 1], n);
            int notpick = solveRecursion(val, wt, w, n - 1);
            return memo[n][w] = Math.max(pick, notpick);
        } else {
            // cannot pick item at index (n - 1)
            return memo[n][w] = solveRecursion(val, wt, w, n - 1);
        }
    }

    /**
     * Using Tabulation
     * 
     * TC: O(N x W)
     * SC: O(N x W)
     * 
     * @param val
     * @param wt
     * @param capacity
     * @return
     */
    static int knapSackTabulation(int val[], int wt[], int capacity) {
        int n = wt.length;
        int[][] dp = new int[n + 1][capacity + 1];
        // intialization
        /**
         * all the cells of row 0 and all cells of column 0
         * are zero by default so initialization not needed
         */

        // iterative call
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                // convert (n, capacity) as (i, j)
                if (wt[i - 1] <= j) {
                    // we have options to pick or not pick
                    // pick - we can pick as many times of item at index (n - 1)
                    dp[i][j] = Math.max(val[i - 1] + dp[i][j - wt[i - 1]],
                            dp[i - 1][j]);
                } else {
                    // cannot pick item at index (n - 1)
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][capacity];
    }
}
