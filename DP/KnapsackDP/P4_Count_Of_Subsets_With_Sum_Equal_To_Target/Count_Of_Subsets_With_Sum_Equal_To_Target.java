package DP.KnapsackDP.P4_Count_Of_Subsets_With_Sum_Equal_To_Target;

import java.util.Arrays;

public class Count_Of_Subsets_With_Sum_Equal_To_Target {
    public static void main(String[] args) {
        Count_Of_Subsets_With_Sum_Equal_To_Target solution = new Count_Of_Subsets_With_Sum_Equal_To_Target();

        int[] arr1 = { 1, 2, 3, 3 };
        int target1 = 6;

        int[] arr2 = { 1, 1, 1, 1 };
        int target2 = 1;

        int subsetsCountRecur1 = solution.perfectSumRecursion(arr1, target1);
        System.out.println(subsetsCountRecur1);

        int subsetsCountRecur2 = solution.perfectSumRecursion(arr2, target2);
        System.out.println(subsetsCountRecur2);

        int subsetsCountMemo1 = solution.perfectSumMemoization(arr1, target1);
        System.out.println(subsetsCountMemo1);

        int subsetsCountMemo2 = solution.perfectSumMemoization(arr2, target2);
        System.out.println(subsetsCountMemo2);

        int subsetsCountTab1 = solution.perfectSumTabulation(arr1, target1);
        System.out.println(subsetsCountTab1);

        int subsetsCountTab2 = solution.perfectSumTabulation(arr2, target2);
        System.out.println(subsetsCountTab2);
    }

    /**
     * Using Tabulation Approach
     * 
     * TC: O(N x T)
     * SC: O(N x T)
     * 
     * where T = target
     * 
     * @param nums
     * @param target
     * @return
     */
    public int perfectSumTabulation(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];

        // initialization

        // set row index 1 values cells as 0 which is by default
        // set column index 1 cells as 1
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }

        // iterative code
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < target + 1; j++) {
                // convert (n, target) as (i , j)
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][target];
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x T)
     * SC: O(N x T + N)
     * 
     * where T = target
     * 
     * @param nums
     * @param target
     * @return
     */
    public int perfectSumMemoization(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];
        for (int[] dp1D : dp) {
            Arrays.fill(dp1D, -1);
        }
        return solveMemoization(nums, target, n, dp);
    }

    private int solveMemoization(int[] nums, int target, int n, int[][] dp) {
        // Base case
        if (target == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        // memoization check
        if (dp[n][target] != -1) {
            return dp[n][target];
        }
        // recursion calls
        if (nums[n - 1] <= target) {
            // we have a choice to pick or not pick
            int pick = solveMemoization(nums, target - nums[n - 1], n - 1, dp);
            int notpick = solveMemoization(nums, target, n - 1, dp);
            return dp[n][target] = pick + notpick;
        } else {
            // we cannot pick the item
            return dp[n][target] = solveMemoization(nums, target, n - 1, dp);
        }
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     * 
     * @param nums
     * @param target
     * @return
     */
    public int perfectSumRecursion(int[] nums, int target) {
        int n = nums.length;
        return solveRecursion(nums, target, n);
    }

    /**
     * TC: O(2 ^ N)
     * SC: O(N)
     * 
     * @param nums
     * @param target
     * @param n
     * @return
     */
    private int solveRecursion(int[] nums, int target, int n) {
        // Base case
        if (target == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        // recursion calls
        if (nums[n - 1] <= target) {
            // we have a choice to pick or not pick
            int pick = solveRecursion(nums, target - nums[n - 1], n - 1);
            int notpick = solveRecursion(nums, target, n - 1);
            return pick + notpick;
        } else {
            // we cannot pick the item
            return solveRecursion(nums, target, n - 1);
        }
    }
}
