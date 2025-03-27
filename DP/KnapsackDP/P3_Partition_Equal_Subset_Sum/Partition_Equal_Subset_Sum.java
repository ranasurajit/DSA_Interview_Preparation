package DP.KnapsackDP.P3_Partition_Equal_Subset_Sum;

import java.util.Arrays;

public class Partition_Equal_Subset_Sum {
    public static void main(String[] args) {
        int arr[] = { 1, 5, 11, 5 };

        boolean hasEqualSumPartitionRecursion = equalPartitionRecursion(arr);
        System.out.println(hasEqualSumPartitionRecursion);

        boolean hasEqualSumPartitionMemoization = equalPartitionMemoization(arr);
        System.out.println(hasEqualSumPartitionMemoization);

        boolean hasEqualSumPartitionTabulation = equalPartitionTabulation(arr);
        System.out.println(hasEqualSumPartitionTabulation);

        boolean hasEqualSumPartitionOptimization = equalPartitionOptimization(arr);
        System.out.println(hasEqualSumPartitionOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(N x T)
     * SC: O(2 x T) ~ O(T)
     */
    static boolean equalPartitionOptimization(int arr[]) {
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += arr[i];
        }
        if (sum % 2 != 0) {
            // odd sum cannot be partioned to form two equal sum subsets
            return false;
        }
        int target = sum / 2;
        // Initialization
        boolean[] prev = new boolean[target + 1]; // SC: O(T)
        prev[0] = true;
        // Iterative Calls - convert dp[i] as current and dp[i - 1] as prev
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            boolean[] current = new boolean[target + 1]; // SC: O(T)
            current[0] = true;
            for (int j = 1; j < target + 1; j++) { // TC: O(T)
                if (arr[i - 1] <= j) {
                    current[j] = prev[j - arr[i - 1]] || prev[j];
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[target];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O((T + N) + N x T) ~ O(N x T)
     * SC: O(N x T)
     */
    static boolean equalPartitionTabulation(int arr[]) {
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += arr[i];
        }
        if (sum % 2 != 0) {
            // odd sum cannot be partioned to form two equal sum subsets
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[n + 1][target + 1]; // SC: O(N x T)
        // Initialization
        for (int j = 1; j < target + 1; j++) { // TC: O(T)
            // if number of elements is 0 then sum > 0 is not possible
            dp[0][j] = false;
        }
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            // if sum is 0 then with any number of elements it is possible to get sum 0
            dp[i][0] = true;
        }
        // Iterative Calls - convert (n, target) to (i, j)
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < target + 1; j++) { // TC: O(T)
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
     * Approach II : Using Memoization Approach
     *
     * TC: O(N + N x T) ~ O(N x T)
     * SC: O(N x T + N)
     */
    static boolean equalPartitionMemoization(int arr[]) {
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += arr[i];
        }
        if (sum % 2 != 0) {
            // odd sum cannot be partioned to form two equal sum subsets
            return false;
        }
        int target = sum / 2;
        int[][] memo = new int[n + 1][target + 1]; // SC: O(N x T)
        for (int[] memoItem : memo) {
            Arrays.fill(memoItem, -1);
        }
        // now the problem gets converted to subset sum problem where target = sum / 2
        return solveMemoization(n, arr, target, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x T)
     * SC: O(N)
     */
    private static boolean solveMemoization(int n, int[] arr, int target, int[][] memo) {
        // Base Case
        if (target == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        // Memoization Check
        if (memo[n][target] != -1) {
            return memo[n][target] == 1;
        }
        // Recursive Calls
        if (arr[n - 1] <= target) {
            // we have options to pick or not pick
            Boolean pick = solveMemoization(n - 1, arr, target - arr[n - 1], memo);
            Boolean notpick = solveMemoization(n - 1, arr, target, memo);
            Boolean result = pick || notpick;
            memo[n][target] = result ? 1 : 0;
            return result;
        } else {
            // we don't have an option to pick anyways
            Boolean result = solveMemoization(n - 1, arr, target, memo);
            memo[n][target] = result ? 1 : 0;
            return result;
        }
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(N + 2 ^ N) ~ O(2 ^ N)
     * SC: O(N)
     */
    static boolean equalPartitionRecursion(int arr[]) {
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += arr[i];
        }
        if (sum % 2 != 0) {
            // odd sum cannot be partioned to form two equal sum subsets
            return false;
        }
        int target = sum / 2;
        // now the problem gets converted to subset sum problem where target = sum / 2
        return solveRecursion(n, arr, target);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private static boolean solveRecursion(int n, int[] arr, int target) {
        // Base Case
        if (target == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        // Recursive Calls
        if (arr[n - 1] <= target) {
            // we have options to pick or not pick
            Boolean pick = solveRecursion(n - 1, arr, target - arr[n - 1]);
            Boolean notpick = solveRecursion(n - 1, arr, target);
            return pick || notpick;
        } else {
            // we don't have an option to pick anyways
            return solveRecursion(n - 1, arr, target);
        }
    }
}
