package Dynamic_Programming.DP_Subsequences.P2_Subset_Sum_Problem;

import java.util.Arrays;

public class Subset_Sum_Problem {
    public static void main(String[] args) {
        Subset_Sum_Problem solution = new Subset_Sum_Problem();

        int arr[] = { 3, 34, 4, 12, 5, 2 };
        int target = 9;
        boolean hasSubsetSumRecursion = solution.isSubsetSumRecursion(arr, target);
        System.out.println(hasSubsetSumRecursion);

        boolean hasSubsetSumMemoization = solution.isSubsetSumMemoization(arr, target);
        System.out.println(hasSubsetSumMemoization);

        boolean hasSubsetSumTabulation = solution.isSubsetSumTabulation(arr, target);
        System.out.println(hasSubsetSumTabulation);

        boolean hasSubsetSumOptimization = solution.isSubsetSumOptimization(arr, target);
        System.out.println(hasSubsetSumOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x S)
     * SC: O(S) + O(S) ~ O(S)
     */
    private Boolean isSubsetSumOptimization(int arr[], int sum) {
        int n = arr.length;
        boolean[] prev = new boolean[sum + 1]; // SC: O(S)
        // Initialization
        /**
         * dp[0][i] will all be false as n = 0 how much ever the
         * sum is, we cannot take anything in subset
         */
        Arrays.fill(prev, false);
        /**
         * similarly, dp[i][0] will always be true as if sum = 0, with
         * selecting no elements we can form the sum = 0
         */
        prev[0] = true;
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            boolean[] current = new boolean[sum + 1]; // SC: O(S)
            current[0] = true;
            for (int j = 1; j < sum + 1; j++) { // TC: O(S)
                if (arr[i - 1] <= j) {
                    current[j] = prev[j - arr[i - 1]] || prev[j];
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[sum];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x S) + O(N)
     * SC: O(N x S)
     */
    private Boolean isSubsetSumTabulation(int arr[], int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1]; // SC: O(N x S)
        // Initialization
        /**
         * dp[0][i] will all be false as n = 0 how much ever the
         * sum is, we cannot take anything in subset
         */
        Arrays.fill(dp[0], false);
        /**
         * similarly, dp[i][0] will always be true as if sum = 0, with
         * selecting no elements we can form the sum = 0
         */
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            dp[i][0] = true;
        }
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < sum + 1; j++) { // TC: O(S)
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x S)
     * SC: O(N x S) + O(N)
     */
    private Boolean isSubsetSumMemoization(int arr[], int sum) {
        int n = arr.length;
        int[][] memo = new int[n + 1][sum + 1]; // SC: O(N x S)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(n - 1, sum, arr, memo);
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x S)
     * SC: O(N)
     */
    private boolean solveMemoization(int idx, int sum, int[] arr, int[][] memo) {
        // Base Case
        if (sum == 0) {
            return true;
        }
        if (idx < 0) {
            return false;
        }
        // Memoization Check
        if (memo[idx][sum] != -1) {
            return memo[idx][sum] == 1;
        }
        // Recursion Calls
        // we can take or skip arr[idx] based upon limit of total sum
        boolean result = false;
        if (arr[idx] <= sum) {
            // we can decide to take or skip
            // take
            boolean take = solveMemoization(idx - 1, sum - arr[idx], arr, memo);
            // skip
            boolean skip = solveMemoization(idx - 1, sum, arr, memo);
            result = take || skip;
        } else {
            // we cannot take arr[idx]
            result = solveMemoization(idx - 1, sum, arr, memo);
        }
        memo[idx][sum] = result ? 1 : 0;
        return result;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private Boolean isSubsetSumRecursion(int arr[], int sum) {
        int n = arr.length;
        return solveRecursion(n - 1, sum, arr);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private boolean solveRecursion(int idx, int sum, int[] arr) {
        // Base Case
        if (sum == 0) {
            return true;
        }
        if (idx < 0) {
            return false;
        }
        // Recursion Calls
        // we can take or skip arr[idx] based upon limit of total sum
        if (arr[idx] <= sum) {
            // we can decide to take or skip
            // take
            boolean take = solveRecursion(idx - 1, sum - arr[idx], arr);
            // skip
            boolean skip = solveRecursion(idx - 1, sum, arr);
            return take || skip;
        } else {
            // we cannot take arr[idx]
            return solveRecursion(idx - 1, sum, arr);
        }
    }
}
