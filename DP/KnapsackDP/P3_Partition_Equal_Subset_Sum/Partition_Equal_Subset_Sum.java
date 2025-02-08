package DP.KnapsackDP.P3_Partition_Equal_Subset_Sum;

import java.util.Arrays;

public class Partition_Equal_Subset_Sum {
    public static void main(String[] args) {
        int arr[] = { 1, 5, 11, 5 };

        boolean hasEqualSumPartitionMemoization = equalPartitionMemoization(arr);
        System.out.println(hasEqualSumPartitionMemoization);

        boolean hasEqualSumPartitionTabulation = equalPartitionTabulation(arr);
        System.out.println(hasEqualSumPartitionTabulation);
    }

    /**
     * Using DP Approach
     *
     * TC: O(N x T + N)
     * SC: O(N x T)
     * where T = sum
     * 
     * @param arr
     * @return
     */
    private static boolean equalPartitionTabulation(int arr[]) {
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += arr[i];
        }
        if (sum % 2 != 0) {
            // sum is odd so equal partition is not possible
            return false;
        }
        int target = sum / 2;
        return isSubsetSumTabulation(arr, n, target); // TC: O(N x T), SC: O(N x T)
    }

    /**
     * Using DP Approach (Tabulation)
     *
     * TC: O(N x T)
     * SC: O(N x T)
     * where T = sum
     * 
     * @param arr
     * @param n
     * @param sum
     * @return
     */
    private static boolean isSubsetSumTabulation(int[] arr, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // intialization
        /**
         * 0th row all cells are by-default false so we need to
         * intialize all cells of 0th column as true
         */
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            dp[i][0] = true;
        }

        // iterative call - sub-problems
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                // transform (n, sum) as (i , j)
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // return the answer of problem
        return dp[n][sum];
    }

    /**
     * Using DP Approach
     *
     * TC: O(N x T + N)
     * SC: O(N x T + N)
     * where T = sum
     * 
     * @param arr
     * @return
     */
    static boolean equalPartitionMemoization(int arr[]) {
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += arr[i];
        }
        if (sum % 2 != 0) {
            // sum is odd so equal partition is not possible
            return false;
        }
        int target = sum / 2;
        return isSubsetSumMemoization(arr, n, target); // TC: O(N x T), SC: O(N x T + N)
    }

    /**
     * Using DP Approach (Memoization)
     *
     * TC: O(N x T)
     * SC: O(N x T + N)
     * where T = sum
     * 
     * @param arr
     * @param n
     * @param sum
     * @return
     */
    private static boolean isSubsetSumMemoization(int[] arr, int n, int sum) {
        int[][] dp = new int[n + 1][sum + 1];
        for (int[] dp1D : dp) {
            Arrays.fill(dp1D, -1);
        }
        return solveMemoization(arr, n, sum, dp);
    }

    /**
     * Using Memoization
     *
     * TC: O(N x T)
     * SC: O(N x T + N)
     * where T = target
     * 
     * @param arr
     * @param n
     * @param sum
     * @param dp
     * @return
     */
    private static boolean solveMemoization(int[] arr, int n, int sum, int[][] dp) {
        // Base case
        if (sum == 0) {
            return true;
        }
        if (n == 0 && sum != 0) {
            return false;
        }
        // memoization call
        if (dp[n][sum] != -1) {
            return dp[n][sum] == 1;
        }
        // recursion call
        if (arr[n - 1] <= sum) {
            // we have two options whether to pick or notpick
            boolean optionOne = solveMemoization(arr, n - 1, sum - arr[n - 1], dp) ||
                    solveMemoization(arr, n - 1, sum, dp);
            dp[n][sum] = optionOne ? 1 : 0;
            return optionOne;
        } else {
            // we cannot choose this option
            boolean optionTwo = solveMemoization(arr, n - 1, sum, dp);
            dp[n][sum] = optionTwo ? 1 : 0;
            return optionTwo;
        }
    }
}
