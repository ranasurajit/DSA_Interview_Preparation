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
     * Using DP Approach (Tabulation)
     *
     * TC: O(N x T)
     * SC: O(N x T)
     * where T = target
     */
    private static boolean isSubsetSumTabulation(int arr[], int target) {
        int n = arr.length;
        /**
         * creating dp array with states 'n' and 'target'
         * as they are variables denoting state changes
         */
        // initialization
        Boolean[][] dp = new Boolean[n + 1][target + 1];

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
        // fill 0th row cells as False
        Arrays.fill(dp[0], false);
        // fill 0th column cells as True
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }
        /**
         * Now let's fill out the rest of the 'dp' array with values from recursive
         * code to iterative code
         * 
         * Note: transform (n, target) as (i , j)
         */
        // iterative code
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < target + 1; j++) {
                // transform (n, target) as (i , j)
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    /**
     * Using DP Approach (Memoization)
     *
     * TC: O(N x T)
     * SC: O(N x T + N)
     * where T = target
     */
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
        return solveMemoization(arr, target, n, dp);
    }

    /**
     * Using Memoization
     *
     * TC: O(N x T)
     * SC: O(N x T + N)
     * where T = target
     */
    private static Boolean solveMemoization(int[] arr, int target, int n, int[][] dp) {
        // Base case
        if (target == 0) {
            return true;
        }
        if (n == 0 && target != 0) {
            return false;
        }
        // memoization call
        if (dp[n][target] != -1) {
            return dp[n][target] == 1;
        }
        // recursion call
        if (arr[n - 1] <= target) {
            // we have choice to pick or not pick
            Boolean optionOne = (solveMemoization(arr, target - arr[n - 1], n - 1, dp)
                    ||
                    solveMemoization(arr, target, n - 1, dp));
            dp[n][target] = optionOne ? 1 : 0;
            return optionOne;
        } else {
            Boolean optionTwo = solveMemoization(arr, target, n - 1, dp);
            dp[n][target] = optionTwo ? 1 : 0;
            return optionTwo;
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
