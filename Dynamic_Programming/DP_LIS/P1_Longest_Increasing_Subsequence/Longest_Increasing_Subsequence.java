package Dynamic_Programming.DP_LIS.P1_Longest_Increasing_Subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Longest_Increasing_Subsequence {
    public static void main(String[] args) {
        Longest_Increasing_Subsequence solution = new Longest_Increasing_Subsequence();

        int nums[] = { 10, 9, 2, 5, 3, 7, 101, 18 };

        int lengthOfLISRecursion = solution.lengthOfLISRecursion(nums);
        System.out.println(lengthOfLISRecursion);

        int lengthOfLISMemoization = solution.lengthOfLISMemoization(nums);
        System.out.println(lengthOfLISMemoization);

        int lengthOfLISTabulation = solution.lengthOfLISTabulation(nums);
        System.out.println(lengthOfLISTabulation);

        int lengthOfLISSpaceOptimization = solution.lengthOfLISSpaceOptimization(nums);
        System.out.println(lengthOfLISSpaceOptimization);

        int lengthOfLISTabulationOptimal = solution.lengthOfLISTabulationOptimal(nums);
        System.out.println(lengthOfLISTabulationOptimal);

        int lengthOfLISBinarySearch = solution.lengthOfLISBinarySearch(nums);
        System.out.println(lengthOfLISBinarySearch);
    }

    /**
     * Approach VI : Using Binary Search (Most Optimized) Approach
     * 
     * TC: O(N x log(N))
     * SC: O(N)
     * 
     * - O(N) - sorted ArrayList 'track' memory
     *
     * Accepted (55 / 55 testcases passed)
     */
    public int lengthOfLISBinarySearch(int[] nums) {
        int n = nums.length;
        List<Integer> track = new ArrayList<Integer>(); // SC: O(N) - sorted ArrayList
        track.add(nums[0]);
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (nums[i] > track.get(track.size() - 1)) {
                track.add(nums[i]);
            } else {
                // we need to substitute the nums[i] at the right position in sorted ArrayList
                // 'track'
                int idx = lowerBound(track, nums[i]); // TC: O(log(N)), SC: O(1)
                track.set(idx, nums[i]);
            }
        }
        return track.size();
    }

    /**
     * Using Binary Search to find Lower Bound Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    private int lowerBound(List<Integer> track, int x) {
        int low = 0;
        int high = track.size() - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (track.get(mid) >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Approach V : Using Tabulation (Bottom-Up DP) + Optimized Approach
     * 
     * TC: O(N x N)
     * SC: O(N)
     * 
     * - O(N) - dp array memory
     *
     * Accepted (55 / 55 testcases passed)
     */
    public int lengthOfLISTabulationOptimal(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1]; // SC: O(N)
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for (int i = 1; i < n; i++) { // TC: O(N)
            for (int prev = 0; prev < i; prev++) { // TC: O(N)
                if (nums[prev] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[prev]);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x N)
     * SC: O(N) + O(N)
     * 
     * - O(N) - next and current array memory
     *
     * Accepted (55 / 55 testcases passed)
     */
    public int lengthOfLISSpaceOptimization(int[] nums) {
        int n = nums.length;
        int[] next = new int[n + 1]; // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            int[] current = new int[n + 1]; // SC: O(N)
            for (int j = i - 1; j >= -1; j--) { // TC: O(N)
                int skip = next[j + 1]; // j + 1 is used instead of j as j can be -1 too
                int pick = 0;
                if (j == -1 || nums[i] > nums[j]) {
                    pick = 1 + next[i + 1]; // i + 1 is used instead of i as j can be -1 too
                }
                current[j + 1] = Math.max(pick, skip);
            }
            next = current.clone();
        }
        return next[0];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x N)
     * SC: O(N x N)
     * 
     * - O(N x N) - dp array memory
     *
     * Accepted (55 / 55 testcases passed)
     */
    public int lengthOfLISTabulation(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1]; // SC: O(N x N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int j = i - 1; j >= -1; j--) { // TC: O(N)
                int skip = dp[i + 1][j + 1]; // j + 1 is used instead of j as j can be -1 too
                int pick = 0;
                if (j == -1 || nums[i] > nums[j]) {
                    pick = 1 + dp[i + 1][i + 1]; // i + 1 is used instead of i as j can be -1 too
                }
                dp[i][j + 1] = Math.max(pick, skip);
            }
        }
        return dp[0][0];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x N) + O(N x N) ~ O(N x N)
     * SC: O(N x N) + O(N)
     * 
     * - O(N x N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (55 / 55 testcases passed)
     */
    public int lengthOfLISMemoization(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n + 1][n + 1]; // SC: O(N x N)
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(N)
        }
        return solveMemoization(0, -1, n, nums, memo); // TC: O(N x N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int prevIdx, int n, int[] nums, int[][] memo) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Memoization Check
        if (memo[idx][prevIdx + 1] != -1) {
            return memo[idx][prevIdx + 1];
        }
        // Recursion Calls
        // we can opt to pick or skip
        // skip
        int skip = solveMemoization(idx + 1, prevIdx, n, nums, memo);
        // pick
        int pick = 0;
        if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
            // since we picked so we increase the length of LIS by 1
            pick = 1 + solveMemoization(idx + 1, idx, n, nums, memo);
        }
        return memo[idx][prevIdx + 1] = Math.max(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (22 / 55 testcases passed)
     */
    public int lengthOfLISRecursion(int[] nums) {
        int n = nums.length;
        return solveRecursion(0, -1, n, nums); // TC: O(2 ^ N)), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N))
     * SC: O(N)
     */
    private int solveRecursion(int idx, int prevIdx, int n, int[] nums) {
        // Base Case
        if (idx == n) {
            return 0;
        }
        // Recursion Calls
        // we can opt to pick or skip
        // skip
        int skip = solveRecursion(idx + 1, prevIdx, n, nums);
        // pick
        int pick = 0;
        if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
            // since we picked so we increase the length of LIS by 1
            pick = 1 + solveRecursion(idx + 1, idx, n, nums);
        }
        return Math.max(pick, skip);
    }
}
