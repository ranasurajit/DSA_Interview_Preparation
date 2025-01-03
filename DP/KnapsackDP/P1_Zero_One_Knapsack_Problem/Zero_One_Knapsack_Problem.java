package DP.KnapsackDP.P1_Zero_One_Knapsack_Problem;

import java.util.Arrays;

public class Zero_One_Knapsack_Problem {
    public static void main(String[] args) {
        int capacity = 7;
        int val[] = { 10, 8, 6 };
        int wt[] = { 1, 7, 9 };
        int maximumValueRecursion = knapSackRecursion(capacity, val, wt);
        System.out.println(maximumValueRecursion);

        int maximumValueMemoization = knapSackMemoization(capacity, val, wt);
        System.out.println(maximumValueMemoization);

        int maximumValueTabulation = knapSackTabulation(capacity, val, wt);
        System.out.println(maximumValueTabulation);
    }

    /**
     * Using Tabulation
     * 
     * TC: O(N x W)
     * SC: O(N x W)
     */
    private static int knapSackTabulation(int w, int val[], int wt[]) {
        int n = wt.length;
        // return solveRecursion(n - 1, capacity, val, wt);
        int[][] dp = new int[n + 1][w + 1];
        // base case in Recursion is converted to Initialization in Tabulation
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < w + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < w + 1; j++) {
                // convert index to (i - 1) and w to j
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
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
     * TC: O(N x W)
     * SC: O(N x W + N)
     */
    private static int knapSackMemoization(int capacity, int val[], int wt[]) {
        int n = wt.length;
        // return solveRecursion(n - 1, capacity, val, wt);
        int[][] dp = new int[n + 1][capacity + 1];
        for (int[] dp1D : dp) {
            Arrays.fill(dp1D, -1);
        }
        return solveMemoization(n - 1, capacity, val, wt, dp);
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x W)
     * SC: O(N x W + N)
     */
    private static int solveMemoization(int index, int w, int val[], int wt[], int[][] dp) {
        // base case - minimum valid testcase
        if (w == 0) {
            return 0;
        }
        if (index == 0) {
            return wt[0] <= w ? val[0] : 0;
        }
        if (dp[index][w] != -1) {
            return dp[index][w];
        }
        /**
         * if wt[index] <= w, i.e current weight is less than
         * capacity 'w' then we have two choices
         * 1. to pick
         * 2. not to pick
         * 
         * so we need to take maximum of them to fill the knapsack
         */
        if (wt[index] <= w) {
            return dp[index][w] = Math.max(
                    val[index] + solveMemoization(index - 1, w - wt[index], val, wt, dp),
                    solveMemoization(index - 1, w, val, wt, dp));
        } else {
            // we have no option to pick this
            return dp[index][w] = solveMemoization(index - 1, w, val, wt, dp);
        }
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     */
    private static int knapSackRecursion(int capacity, int val[], int wt[]) {
        int n = wt.length;
        return solveRecursion(n - 1, capacity, val, wt);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     */
    private static int solveRecursion(int index, int w, int val[], int wt[]) {
        // base case - minimum valid testcase
        if (w == 0) {
            return 0;
        }
        if (index == 0) {
            return wt[0] <= w ? val[0] : 0;
        }
        /**
         * if wt[index] <= w, i.e current weight is less than
         * capacity 'w' then we have two choices
         * 1. to pick
         * 2. not to pick
         * 
         * so we need to take maximum of them to fill the knapsack
         */
        if (wt[index] <= w) {
            return Math.max(
                    val[index] + solveRecursion(index - 1, w - wt[index], val, wt),
                    solveRecursion(index - 1, w, val, wt));
        } else {
            // we have no option to pick this
            return solveRecursion(index - 1, w, val, wt);
        }
    }
}
