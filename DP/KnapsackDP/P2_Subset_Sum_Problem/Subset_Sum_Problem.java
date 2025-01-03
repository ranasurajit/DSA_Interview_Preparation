package DP.KnapsackDP.P2_Subset_Sum_Problem;

import java.util.Arrays;

public class Subset_Sum_Problem {
    public static void main(String[] args) {
        int arr[] = { 3, 34, 4, 12, 5, 2 };
        int target = 9;
        boolean hasSubsetSumRecursion = isSubsetSumRecursion(arr, target);
        System.out.println(hasSubsetSumRecursion);

        boolean hasSubsetSumMemoization = isSubsetSumMemoization(arr, target);
        System.out.println(hasSubsetSumMemoization);

        boolean hasSubsetSumTabulation = isSubsetSumTabulation(arr, target);
        System.out.println(hasSubsetSumTabulation);
    }

    /**
     * Using Tabulation
     *
     * TC: O(N x K)
     * SC: O(N x K)
     * where K = target
     */
    private static boolean isSubsetSumTabulation(int arr[], int target) {
        int n = arr.length;
        /**
         * creating dp array with states 'n' and 'target'
         * as they are variables denoting state changes
         */
        boolean[][] dp = new boolean[n + 1][target + 1];

        // convert base case into initialization of 'dp' array
        /**
         * For row i = 0, where i denotes number of elements of array 'arr'
         * for any sum we cannot have any subsets that can fulfill the rule
         * so dp[0][0 to target] = false
         * 
         * For col j = 0, we need target = 0 for any number of elements of array 'arr'
         * so that can be done by {} empty array
         * so dp[0 to n][0] = true
         */
        for (int j = 0; j < target + 1; j++) {
            dp[0][j] = false;
        }
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }
        /**
         * Now let's fill out the rest of the 'dp' array with values from recursive
         * code to iterative code
         * 
         * Note: 'index' is transformed to 'i - 1' and 'target' to 'j'
         */
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (arr[i - 1] <= j) {
                    // pick || not pick
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    // we have no option to pick it
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    private static boolean isSubsetSumMemoization(int arr[], int target) {
        int n = arr.length;
        /**
         * creating dp array with states 'n' and 'target'
         * as they are variables denoting state changes
         * and intializing all with -1
         */
        int[][] dp = new int[n + 1][target + 1];
        for (int[] dp1D : dp) {
            Arrays.fill(dp1D, -1);
        }
        return solveMemoization(n - 1, arr, target, dp);
    }

    /**
     * Using Memoization
     *
     * TC: O(N x K)
     * SC: O(N x K + N)
     * where K = target
     */
    private static Boolean solveMemoization(int index, int arr[], int target, int[][] dp) {
        // base case
        if (target == 0) {
            return true;
        }
        if (index == 0) {
            return arr[0] == target;
        }
        if (dp[index][target] != -1) {
            return dp[index][target] == 1;
        }
        /**
         * if element at index of arr is <= target then only we have two
         * choices
         * 
         * 1. pick
         * 2. not pick
         */
        if (arr[index] <= target) {
            // pick || not pick
            boolean twoOptions = solveMemoization(index - 1, arr, target - arr[index], dp) ||
                    solveMemoization(index - 1, arr, target, dp);
            dp[index][target] = twoOptions ? 1 : 0;
            return twoOptions;
        } else {
            // we have no option to pick it
            boolean noOption = solveMemoization(index - 1, arr, target, dp);
            dp[index][target] = noOption ? 1 : 0;
            return noOption;
        }
    }

    private static boolean isSubsetSumRecursion(int arr[], int target) {
        int n = arr.length;
        return solveRecursion(n - 1, arr, target);
    }

    /**
     * Using Recursion
     *
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     */
    private static Boolean solveRecursion(int index, int arr[], int target) {
        // base case
        if (target == 0) {
            return true;
        }
        if (index == 0) {
            return arr[0] == target;
        }
        /**
         * if element at index of arr is <= target then only we have two
         * choices
         * 
         * 1. pick
         * 2. not pick
         */
        if (arr[index] <= target) {
            // pick || not pick
            return solveRecursion(index - 1, arr, target - arr[index]) ||
                    solveRecursion(index - 1, arr, target);
        } else {
            // we have no option to pick it
            return solveRecursion(index - 1, arr, target);
        }
    }
}
