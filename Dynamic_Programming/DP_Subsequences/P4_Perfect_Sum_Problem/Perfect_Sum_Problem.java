package Dynamic_Programming.DP_Subsequences.P4_Perfect_Sum_Problem;

import java.util.Arrays;

public class Perfect_Sum_Problem {
    public static void main(String[] args) {
        Perfect_Sum_Problem solution = new Perfect_Sum_Problem();

        int[] nums = { 28, 4, 3, 27, 0, 24, 26 };
        int target = 24;

        int subsetsCountRecur = solution.perfectSumRecursion(nums, target);
        System.out.println(subsetsCountRecur);

        int subsetsCountMemo = solution.perfectSumMemoization(nums, target);
        System.out.println(subsetsCountMemo);

        int subsetsCountTab = solution.perfectSumTabulation(nums, target);
        System.out.println(subsetsCountTab);

        int subsetsCountOpt = solution.perfectSumSpaceOptimization(nums, target);
        System.out.println(subsetsCountOpt);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x S) + O(N)
     * SC: O(S) + O(S) ~ O(S)
     *
     * Accepted (1111 / 1111 testcases passed)
     */
    public int perfectSumSpaceOptimization(int[] nums, int target) {
        int n = nums.length;
        // Initialization
        int[] prev = new int[target + 1]; // SC: O(S)
        /**
         * If n == 0, then for any target, prev = 0
         */
        Arrays.fill(prev, 0);
        /**
         * If target == 0, then for number of elements in nums, dp[i][0] = 1
         */
        prev[0] = 1;
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            int[] current = new int[target + 1]; // SC: O(S)
            current[0] = 1;
            // starting with j = 0 here as target at any point can be zero too
            for (int j = 0; j < target + 1; j++) { // TC: O(S)
                if (nums[i - 1] == 0 || nums[i - 1] <= j) {
                    current[j] = prev[j - nums[i - 1]] + prev[j];
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[target];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x S) + O(N)
     * SC: O(N x S)
     *
     * Accepted (1111 / 1111 testcases passed)
     */
    public int perfectSumTabulation(int[] nums, int target) {
        int n = nums.length;
        // Initialization
        int[][] dp = new int[n + 1][target + 1]; // SC: O(N x S)
        /**
         * If n == 0, then for any target, dp[0] = 0
         */
        Arrays.fill(dp[0], 0);
        /**
         * If target == 0, then for number of elements in nums, dp[i][0] = 1
         */
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            dp[i][0] = 1;
        }
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            // starting with j = 0 here as target at any point can be zero too
            for (int j = 0; j < target + 1; j++) { // TC: O(S)
                if (nums[i - 1] == 0 || nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x S)
     * SC: O(N x S) + O(N)
     *
     * Accepted (1111 / 1111 testcases passed)
     */
    public int perfectSumMemoization(int[] nums, int target) {
        int n = nums.length;
        int[][] memo = new int[n + 1][target + 1]; // O(N x S)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(n - 1, nums, target, memo); // TC: O(N x S), SC: O(N)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x S)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int[] nums, int target, int[][] memo) {
        // Base Case
        if (idx < 0) {
            return target == 0 ? 1 : 0;
        }
        // Memoization Check
        if (memo[idx][target] != -1) {
            return memo[idx][target];
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (nums[idx] <= target) {
            // we have option to pick or skip
            pick = solveMemoization(idx - 1, nums, target - nums[idx], memo);
            skip = solveMemoization(idx - 1, nums, target, memo);
        } else {
            // we cannot pick nums[idx]
            skip = solveMemoization(idx - 1, nums, target, memo);
        }
        return memo[idx][target] = pick + skip;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (1010 / 1111 testcases passed)
     */
    public int perfectSumRecursion(int[] nums, int target) {
        int n = nums.length;
        return solveRecursion(n - 1, nums, target); // TC: O(2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int[] nums, int target) {
        // Base Case
        if (idx < 0) {
            return target == 0 ? 1 : 0;
        }
        // Recursion Calls
        int pick = 0;
        int skip = 0;
        if (nums[idx] <= target) {
            // we have option to pick or skip
            pick = solveRecursion(idx - 1, nums, target - nums[idx]);
            skip = solveRecursion(idx - 1, nums, target);
        } else {
            // we cannot pick nums[idx]
            skip = solveRecursion(idx - 1, nums, target);
        }
        return pick + skip;
    }
}
