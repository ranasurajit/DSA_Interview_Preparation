package Greedy_Algorithms.P10_Jump_Game_II;

import java.util.Arrays;

public class Jump_Game_II {
    public static void main(String[] args) {
        Jump_Game_II solution = new Jump_Game_II();

        int[] nums1 = { 2, 3, 1, 1, 4 };
        int jumpCount1 = solution.jumpRecursion(nums1);
        System.out.println(jumpCount1);

        int[] nums2 = { 1, 2, 3, 4, 5 };
        int jumpCount2 = solution.jumpMemoization(nums2);
        System.out.println(jumpCount2);

        int[] nums3 = { 2, 3, 0, 1, 4 };
        int jumpCount3 = solution.jumpGreedy(nums3);
        System.out.println(jumpCount3);
    }

    /**
     * Approach III : Using Greedy Approach
     * 
     * TC: O(N)
     * SC: O(1)
     *
     * Accepted (110 / 110 testcases passed)
     */
    public int jumpGreedy(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        int jumps = 0;
        while (right < n - 1) { // TC: O(N)
            int farthest = 0;
            for (int i = left; i <= right; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            left = right + 1;
            right = farthest;
            jumps++;
        }
        return jumps;
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x N)
     * SC: O(N x N) + O(N)
     * 
     * - O(N x N) - memoization array memory
     * - O(N) - recursion stack
     * 
     * Accepted (110 / 110 testcases passed)
     */
    public int jumpMemoization(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoization(0, n, nums, memo); // TC: O(N x N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int[] nums, int[] memo) {
        // Base Case
        if (idx >= n - 1) {
            return 0;
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        int jumps = nums[idx];
        int minJumps = n;
        for (int i = 1; i <= jumps; i++) { // TC: O(N)
            if (idx + i < n) {
                minJumps = Math.min(minJumps, 1 + solveMemoization(idx + i, n, nums, memo));
            }
        }
        return memo[idx] = minJumps;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (74 / 110 testcases passed)
     */
    public int jumpRecursion(int[] nums) {
        int n = nums.length;
        return solveRecursion(0, n, nums); // TC: O(N x 2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int[] nums) {
        // Base Case
        if (idx >= n - 1) {
            return 0;
        }
        // Recursion Calls
        int jumps = nums[idx];
        int minJumps = n;
        for (int i = 1; i <= jumps; i++) { // TC: O(N)
            if (idx + i < n) {
                minJumps = Math.min(minJumps, 1 + solveRecursion(idx + i, n, nums));
            }
        }
        return minJumps;
    }
}
