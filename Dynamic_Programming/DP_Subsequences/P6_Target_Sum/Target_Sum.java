package Dynamic_Programming.DP_Subsequences.P6_Target_Sum;

import java.util.Arrays;

public class Target_Sum {
    public static void main(String[] args) {
        Target_Sum solution = new Target_Sum();

        int[] nums = { 1, 1, 1, 1, 1 };
        int target = 3;

        int countPartitionsRecursion = solution.findTargetSumWaysRecursion(nums, target);
        System.out.println(countPartitionsRecursion);

        int countPartitionsMemoization = solution.findTargetSumWaysMemoization(nums, target);
        System.out.println(countPartitionsMemoization);

        int countPartitionsTabulation = solution.findTargetSumWaysTabulation(nums, target);
        System.out.println(countPartitionsTabulation);

        int countPartitionsOptimization = solution.findTargetSumWaysSpaceOptimization(nums, target);
        System.out.println(countPartitionsOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x S) + O(N)
     * SC: O(S) + O(S) ~ O(S)
     *
     * Accepted (141 / 141 testcases passed)
     */
    public int findTargetSumWaysSpaceOptimization(int[] nums, int target) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) { // TC: O(N)
            total += num;
        }
        /**
         * we have to find two partitions such that: partition 1 has all
         * +ve symbol elements and partition 2 has all -ve symbol elements
         * |s1 - s2| = target
         * s1 + s2 = total
         * so s1 = (target + total) / 2, so the problem or s2 = (total - target) / 2
         * reduces to find the count of subsets with target = s2 = (total - target) / 2
         */
        // Checking for edge cases - not possible to get such partition
        if (target > total) {
            return 0;
        }
        if (((total - target) & 1) != 0) {
            return 0;
        }
        // so now target becomes = calculation / 2
        target = (total - target) / 2;
        int[] prev = new int[target + 1]; // SC: O(S)
        /**
         * 2 because we consider both pick and skip for condition target = 0
         * 1 because nums[0] != 0 so we should only skip
         */
        prev[0] = nums[0] == 0 ? 2 : 1;
        if (nums[0] != 0 && nums[0] <= target) {
            prev[nums[0]] = 1; // in case of pick
        }
        // Iterative Calls
        for (int i = 1; i < n; i++) {
            int[] current = new int[target + 1]; // SC: O(S)
            // target can be zero too, so j should be looped from 0 to (target + 1)
            for (int j = 0; j < target + 1; j++) {
                if (nums[i] <= j) {
                    current[j] = prev[j - nums[i]] + prev[j];
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
     * Accepted (141 / 141 testcases passed)
     */
    public int findTargetSumWaysTabulation(int[] nums, int target) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) { // TC: O(N)
            total += num;
        }
        /**
         * we have to find two partitions such that: partition 1 has all
         * +ve symbol elements and partition 2 has all -ve symbol elements
         * |s1 - s2| = target
         * s1 + s2 = total
         * so s1 = (target + total) / 2, so the problem or s2 = (total - target) / 2
         * reduces to find the count of subsets with target = s2 = (total - target) / 2
         */
        // Checking for edge cases - not possible to get such partition
        if (target > total) {
            return 0;
        }
        if (((total - target) & 1) != 0) {
            return 0;
        }
        // so now target becomes = calculation / 2
        target = (total - target) / 2;
        int[][] dp = new int[n][target + 1]; // SC: O(N x S)
        /**
         * 2 because we consider both pick and skip for condition target = 0
         * 1 because nums[0] != 0 so we should only skip
         */
        dp[0][0] = nums[0] == 0 ? 2 : 1;
        if (nums[0] != 0 && nums[0] <= target) {
            dp[0][nums[0]] = 1; // in case of pick
        }
        // Iterative Calls
        for (int i = 1; i < n; i++) {
            // target can be zero too, so j should be looped from 0 to (target + 1)
            for (int j = 0; j < target + 1; j++) {
                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x S) + O(N)
     * SC: O(N x S) + O(N)
     *
     * Accepted (141 / 141 testcases passed)
     */
    public int findTargetSumWaysMemoization(int[] nums, int target) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) { // TC: O(N)
            total += num;
        }
        /**
         * we have to find two partitions such that: partition 1 has all
         * +ve symbol elements and partition 2 has all -ve symbol elements
         * |s1 - s2| = target
         * s1 + s2 = total
         * so s1 = (target + total) / 2, so the problem or s2 = (total - target) / 2
         * reduces to find the count of subsets with target = s2 = (total - target) / 2
         */
        // Checking for edge cases - not possible to get such partition
        if (target > total) {
            return 0;
        }
        if (((total - target) & 1) != 0) {
            return 0;
        }
        // so now target becomes = calculation / 2
        target = (total - target) / 2;
        int[][] memo = new int[n + 1][target + 1]; // SC: O(N x S)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(n - 1, nums, target, memo);
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
            // we can pick or skip
            pick = solveMemoization(idx - 1, nums, target - nums[idx], memo);
            skip = solveMemoization(idx - 1, nums, target, memo);
        } else {
            // we cannot pick at all
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
     * Accepted (141 / 141 testcases passed)
     */
    public int findTargetSumWaysRecursion(int[] nums, int target) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        /**
         * we have to find two partitions such that: partition 1 has all
         * +ve symbol elements and partition 2 has all -ve symbol elements
         * |s1 - s2| = target
         * s1 + s2 = total
         * so s1 = (target + total) / 2, so the problem
         * reduces to find the count of subsets with target = s1 = (target + total) / 2
         */
        int calculation = target + total;
        if ((calculation & 1) != 0) {
            // not possible to get such partition
            return 0;
        }
        // so now target becomes = calculation / 2
        target = calculation / 2;
        return solveRecursion(n - 1, nums, target);
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
            // we can pick or skip
            pick = solveRecursion(idx - 1, nums, target - nums[idx]);
            skip = solveRecursion(idx - 1, nums, target);
        } else {
            // we cannot pick at all
            skip = solveRecursion(idx - 1, nums, target);
        }
        return pick + skip;
    }
}
