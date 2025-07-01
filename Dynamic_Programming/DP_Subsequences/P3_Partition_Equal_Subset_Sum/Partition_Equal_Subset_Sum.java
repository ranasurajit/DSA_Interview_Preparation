package Dynamic_Programming.DP_Subsequences.P3_Partition_Equal_Subset_Sum;

import java.util.Arrays;

public class Partition_Equal_Subset_Sum {
    public static void main(String[] args) {
        Partition_Equal_Subset_Sum solution = new Partition_Equal_Subset_Sum();

        int arr[] = { 1, 5, 11, 5 };

        boolean hasEqualSumPartitionRecursion = solution.canPartitionRecursion(arr);
        System.out.println(hasEqualSumPartitionRecursion);

        boolean hasEqualSumPartitionMemoization = solution.canPartitionMemoization(arr);
        System.out.println(hasEqualSumPartitionMemoization);

        boolean hasEqualSumPartitionTabulation = solution.canPartitionTabulation(arr);
        System.out.println(hasEqualSumPartitionTabulation);

        boolean hasEqualSumPartitionOptimization = solution.canPartitionSpaceOptimization(arr);
        System.out.println(hasEqualSumPartitionOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x S) + O(N) + O(N)
     * SC: O(N x S)
     *
     * Accepted (147 / 147 testcases passed)
     */
    public boolean canPartitionSpaceOptimization(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) { // TC: O(N)
            sum += num;
        }
        if ((sum & 1) != 0) {
            // for odd sum, we cannot partition array into two equal sum subsets
            return false;
        }
        // now the problem is reduced to checking if the array has a subset sum of
        // target = sum / 2
        int target = sum / 2;
        // Initialization
        boolean[] prev = new boolean[target + 1]; // SC: O(S)
        /**
         * when n == 0, whatever the target value is, we cannot find a valid subset so
         * prev[j] = false
         */
        Arrays.fill(prev, false);
        /**
         * when target == 0, we can find a valid subset with no elements, so prev[0] =
         * true
         */
        prev[0] = true;
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            boolean[] current = new boolean[target + 1]; // SC: O(S)
            current[0] = true;
            for (int j = 1; j < target + 1; j++) { // TC: O(S)
                if (nums[i - 1] <= j) {
                    current[j] = prev[j - nums[i - 1]] || prev[j];
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
     * TC: O(N x S) + O(N) + O(N)
     * SC: O(N x S)
     *
     * Accepted (147 / 147 testcases passed)
     */
    public boolean canPartitionTabulation(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) { // TC: O(N)
            sum += num;
        }
        if ((sum & 1) != 0) {
            // for odd sum, we cannot partition array into two equal sum subsets
            return false;
        }
        // now the problem is reduced to checking if the array has a subset sum of
        // target = sum / 2
        int target = sum / 2;
        // Initialization
        boolean[][] dp = new boolean[n + 1][target + 1]; // SC: O(N x S)
        /**
         * when n == 0, whatever the target value is, we cannot find a valid subset so
         * dp[0][j] = false
         */
        Arrays.fill(dp[0], false);
        /**
         * when target == 0, we can find a valid subset with no elements, so dp[i][0] =
         * true
         */
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            dp[i][0] = true;
        }
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < target + 1; j++) { // TC: O(S)
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
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
     * TC: O(N x S) + O(N)
     * SC: O(N x S) + O(N)
     *
     * Accepted (147 / 147 testcases passed)
     */
    public boolean canPartitionMemoization(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) { // TC: O(N)
            sum += num;
        }
        if ((sum & 1) != 0) {
            // for odd sum, we cannot partition array into two equal sum subsets
            return false;
        }
        // now the problem is reduced to checking if the array has a subset sum of
        // target = sum / 2
        int target = sum / 2;
        int[][] memo = new int[n + 1][target + 1]; // SC: O(N x S)
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
    private boolean solveMemoization(int idx, int[] nums, int target, int[][] memo) {
        // Base Case
        if (target == 0) {
            return true;
        }
        if (idx < 0) {
            return false;
        }
        // Memoization Check
        if (memo[idx][target] != -1) {
            return memo[idx][target] == 1;
        }
        // Recursion Calls
        boolean take = false;
        boolean skip = false;
        if (nums[idx] <= target) {
            // we have an option to take or skip
            take = solveMemoization(idx - 1, nums, target - nums[idx], memo);
            skip = solveMemoization(idx - 1, nums, target, memo);
        } else {
            // we cannot take nums[idx] at all
            skip = solveMemoization(idx - 1, nums, target, memo);
        }
        boolean result = take || skip;
        memo[idx][target] = result ? 1 : 0;
        return result;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (39 / 147 testcases passed)
     */
    public boolean canPartitionRecursion(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) != 0) {
            // for odd sum, we cannot partition array into two equal sum subsets
            return false;
        }
        // now the problem is reduced to checking if the array has a subset sum of
        // target = sum / 2
        int target = sum / 2;
        return solveRecursion(n - 1, nums, target);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private boolean solveRecursion(int idx, int[] nums, int target) {
        // Base Case
        if (target == 0) {
            return true;
        }
        if (idx < 0) {
            return false;
        }
        // Recursion Calls
        boolean take = false;
        boolean skip = false;
        if (nums[idx] <= target) {
            // we have an option to take or skip
            take = solveRecursion(idx - 1, nums, target - nums[idx]);
            skip = solveRecursion(idx - 1, nums, target);
        } else {
            // we cannot take nums[idx] at all
            skip = solveRecursion(idx - 1, nums, target);
        }
        return take || skip;
    }
}
