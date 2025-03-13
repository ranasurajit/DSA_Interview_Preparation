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

        boolean hasSubsetSumOptimization = isSubsetSumOptimization(arr, target);
        System.out.println(hasSubsetSumOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(N x T)
     * SC: O(2 x T) ~ O(T)
     * 
     * where T = target
     */
    static Boolean isSubsetSumOptimization(int arr[], int target) {
        int n = arr.length;
        // Initialization
        Boolean[] prev = new Boolean[target + 1]; // SC: O(T)
        Arrays.fill(prev, false);
        prev[0] = true;
        // Iterative Calls - convert dp[i] to current and dp[i - 1] to prev
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            Boolean[] current = new Boolean[target + 1]; // SC: O(T)
            Arrays.fill(current, false);
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

    static boolean subsetSumToK(int n, int k, int[] arr) {
        // Create an array to store the previous row of the DP table
        boolean prev[] = new boolean[k + 1];

        // Initialize the first row of the DP table
        prev[0] = true;

        // Initialize the first column of the DP table
        if (arr[0] <= k) {
            prev[arr[0]] = true;
        }

        // Fill in the DP table using bottom-up approach
        for (int ind = 1; ind < n; ind++) {
            // Create an array to store the current row of the DP table
            boolean cur[] = new boolean[k + 1];

            // Initialize the first column of the current row
            cur[0] = true;

            for (int target = 1; target <= k; target++) {
                // Calculate if the current target can be achieved without taking the current
                // element
                boolean notTaken = prev[target];

                // Calculate if the current target can be achieved by taking the current element
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = prev[target - arr[ind]];
                }

                // Store the result in the current row of the DP table
                cur[target] = notTaken || taken;
            }

            // Update the previous row with the current row
            prev = cur;
        }

        // The final result is stored in the last cell of the previous row
        return prev[k];
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O((N + T) + N x T) ~ O(N x T)
     * SC: O(N x T)
     * 
     * where T = target
     */
    static Boolean isSubsetSumTabulation(int arr[], int target) {
        int n = arr.length;
        Boolean[][] dp = new Boolean[n + 1][target + 1]; // SC: O(N x T)
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
     * TC: O(N x T)
     * SC: O(N x T + N)
     * 
     * where T = target
     */
    static Boolean isSubsetSumMemoization(int arr[], int target) {
        int n = arr.length;
        int[][] memo = new int[n + 1][target + 1]; // SC: O(N x T)
        for (int[] memoItem : memo) {
            Arrays.fill(memoItem, -1);
        }
        return solveMemoization(n, arr, target, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x T)
     * SC: O(N)
     */
    private static Boolean solveMemoization(int n, int[] arr, int target, int[][] memo) {
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
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    static Boolean isSubsetSumRecursion(int arr[], int target) {
        int n = arr.length;
        return solveRecursion(n, arr, target);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private static Boolean solveRecursion(int n, int[] arr, int target) {
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
