package Dynamic_Programming.DP_Subsequences.P11_Minimum_Sum_Partition;

import java.util.Arrays;

public class Minimum_Sum_Partition {
    public static void main(String[] args) {
        Minimum_Sum_Partition solution = new Minimum_Sum_Partition();

        int[] arr = { 1, 6, 11, 5 };

        int minDiffMemoization = solution.minDifferenceMemoization(arr);
        System.out.println(minDiffMemoization);

        int minDiffTabulation = solution.minDifferenceTabulation(arr);
        System.out.println(minDiffTabulation);

        int minDiffOptimization = solution.minDifferenceSpaceOptimization(arr);
        System.out.println(minDiffOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x T) + O(T) + O(N) ~ O(N x T)
     * SC: O(T) + O(T) ~ O(T)
     * 
     * - O(T) - prev and current array memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int minDifferenceSpaceOptimization(int arr[]) {
        int n = arr.length;
        if (n == 1) {
            return arr[0];
        }
        int total = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            total += arr[i];
        }
        /**
         * We need to divide the array 'arr' into two subsets with minimum sum
         * difference
         * 
         * S2 - S1 = D where S2 > S1 and S2 + S1 = total, so we can deduce, S2 = total -
         * S1
         * so, total - 2 x S1 = D, so we need to minimize |total - 2 x S1|
         */
        // Initialization
        boolean[] prev = new boolean[total + 1]; // SC: O(T)
        prev[0] = true;
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            boolean[] current = new boolean[total + 1]; // SC: O(T)
            current[0] = true;
            for (int j = 1; j < total + 1; j++) { // TC: O(T)
                if (arr[i - 1] <= j) {
                    current[j] = prev[j - arr[i - 1]] || prev[j];
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        // comparison with dp[n]
        int minDiff = Integer.MAX_VALUE;
        for (int j = 0; j <= total / 2; j++) { // TC: O(T)
            if (prev[j]) {
                int currentDiff = Math.abs(total - 2 * j);
                minDiff = Math.min(minDiff, currentDiff);
            }
        }
        return minDiff;
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x T) + O(T) + O(N) + O(N) ~ O(N x T)
     * SC: O(N x T)
     * 
     * - O(N x T) - dp table array memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int minDifferenceTabulation(int arr[]) {
        int n = arr.length;
        if (n == 1) {
            return arr[0];
        }
        int total = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            total += arr[i];
        }
        /**
         * We need to divide the array 'arr' into two subsets with minimum sum
         * difference
         * 
         * S2 - S1 = D where S2 > S1 and S2 + S1 = total, so we can deduce, S2 = total -
         * S1
         * so, total - 2 x S1 = D, so we need to minimize |total - 2 x S1|
         */
        boolean[][] dp = new boolean[n + 1][total + 1]; // SC: O(N x T)
        // Initialization
        /**
         * For n = 0 (no elements), dp[0][0] = true and and rest dp[0][1] tp
         * dp[0][total] = false
         * For total = 0, dp[i][0] = true
         */
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            dp[i][0] = true;
        }
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < total + 1; j++) { // TC: O(T)
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // comparison with dp[n]
        int minDiff = Integer.MAX_VALUE;
        for (int j = 0; j <= total / 2; j++) { // TC: O(T)
            if (dp[n][j]) {
                int currentDiff = Math.abs(total - 2 * j);
                minDiff = Math.min(minDiff, currentDiff);
            }
        }
        return minDiff;
    }

    /**
     * Approach I : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x T x T) + O(N x T) + O(N) ~ O(N x T x T)
     * SC: O(N x T) + O(N)
     * 
     * - O(N x T) - memoization array memory
     * - O(N) - stack memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int minDifferenceMemoization(int arr[]) {
        int n = arr.length;
        if (n == 1) {
            return arr[0];
        }
        int total = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            total += arr[i];
        }
        /**
         * We need to divide the array 'arr' into two subsets with minimum sum
         * difference
         * 
         * S2 - S1 = D where S2 > S1 and S2 + S1 = total, so we can deduce, S2 = total -
         * S1
         * so, total - 2 x S1 = D, so we need to minimize |total - 2 x S1|
         */
        int[][] memo = new int[n][total + 1]; // SC: O(N x T)
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(T)
        }
        int minDiff = Integer.MAX_VALUE;
        for (int j = 0; j <= total / 2; j++) { // TC: O(T)
            solveMemoization(n - 1, arr, j, memo); // TC: O(N x T), SC: O(N)
            // comparison with memo[n - 1]
            if (memo[n - 1][j] == 1) {
                int currentDiff = Math.abs(total - 2 * j);
                minDiff = Math.min(minDiff, currentDiff);
            }
        }
        return minDiff;
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x T)
     * SC: O(N)
     */
    private boolean solveMemoization(int idx, int[] arr, int total, int[][] memo) {
        // Base Case
        if (total == 0) {
            return true;
        }
        if (idx < 0) {
            return false;
        }
        // Memoization Check
        if (memo[idx][total] != -1) {
            return memo[idx][total] == 1;
        }
        // Recursion Calls
        boolean skip = solveMemoization(idx - 1, arr, total, memo);
        boolean pick = false;
        if (arr[idx] <= total) {
            pick = solveMemoization(idx - 1, arr, total - arr[idx], memo);
        }
        boolean result = pick || skip;
        memo[idx][total] = result ? 1 : 0;
        return result;
    }
}
