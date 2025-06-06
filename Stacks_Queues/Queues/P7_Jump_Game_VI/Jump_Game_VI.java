package Stacks_Queues.Queues.P7_Jump_Game_VI;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Jump_Game_VI {
    public static void main(String[] args) {
        Jump_Game_VI solution = new Jump_Game_VI();

        int[] nums1 = { 1, -1, -2, 4, -7, 3 };
        int k1 = 2;
        int maxResult1 = solution.maxResultRecursion(nums1, k1);
        System.out.println(maxResult1);

        int[] nums2 = { 10, -5, -2, 4, 0, 3 };
        int k2 = 3;
        int maxResult2 = solution.maxResultMemoization(nums2, k2);
        System.out.println(maxResult2);

        int[] nums3 = { 1, -5, -20, 4, -1, 3, -6, -3 };
        int k3 = 2;

        int maxResult3 = solution.maxResultUsingDeque(nums3, k3);
        System.out.println(maxResult3);
    }

    /**
     * Approach III : Using Sliding Window + Deque Approach
     *
     * TC: O(N)
     * SC: O(N + K)
     *
     * Accepted (73 / 73 testcases passed)
     */
    public int maxResultUsingDeque(int[] nums, int k) {
        int n = nums.length;
        // we will be maintaining monotonic decreasing order of values' indices in Deque
        Deque<Integer> deque = new ArrayDeque<Integer>(); // SC: O(K)
        int[] dp = new int[n]; // SC: O(N)
        int j = 0;
        while (j < n) { // TC: O(N)
            while (!deque.isEmpty() && deque.peekFirst() < j - k) {
                // remove computation from index 'i'
                deque.pollFirst();
            }
            dp[j] = nums[j];
            if (!deque.isEmpty()) {
                dp[j] += dp[deque.peekFirst()];
            }
            while (!deque.isEmpty() && dp[j] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(j);
            j++;
        }
        return dp[n - 1];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(K x N)
     * SC: O(2 x N)
     *
     * Time Limit Exceeded (58 / 73 testcases passed)
     *
     * with higher given constraints, 1 <= nums.length, k <= 105
     * we cannot solve this problem by using Dynamic Programming
     */
    public int maxResultMemoization(int[] nums, int k) {
        int n = nums.length;
        int[] memo = new int[n + 1]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoization(0, n, nums, k, memo);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(K x N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int n, int[] nums, int k, int[] memo) {
        // Base Case
        if (idx == n - 1) {
            return nums[idx];
        }
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        int maxScore = Integer.MIN_VALUE;
        for (int i = idx + 1; i <= Math.min(idx + k, n - 1); i++) { // TC: O(K)
            maxScore = Math.max(maxScore, solveMemoization(i, n, nums, k, memo));
        }
        return memo[idx] = nums[idx] + maxScore;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(K ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (5 / 73 testcases passed)
     */
    public int maxResultRecursion(int[] nums, int k) {
        int n = nums.length;
        return solveRecursion(0, n, nums, k);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(K ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int n, int[] nums, int k) {
        // Base Case
        if (idx == n - 1) {
            return nums[idx];
        }
        // Recursion Calls
        int maxScore = Integer.MIN_VALUE;
        for (int i = idx + 1; i <= Math.min(idx + k, n - 1); i++) { // TC: O(K)
            maxScore = Math.max(maxScore, solveRecursion(i, n, nums, k));
        }
        return nums[idx] + maxScore;
    }
}
