package Dynamic_Programming.DP_1D.P5_House_Robber_II;

import java.util.Arrays;

public class House_Robber_II {
    public static void main(String[] args) {
        House_Robber_II solution = new House_Robber_II();

        int[] nums1 = { 2, 3, 2 };
        int maxRobbedRecursion = solution.robRecursion(nums1);
        System.out.println(maxRobbedRecursion);

        int[] nums2 = { 1, 2, 3, 1 };
        int maxRobbedMemoization = solution.robMemoization(nums2);
        System.out.println(maxRobbedMemoization);

        int[] nums3 = { 2, 7, 9, 3, 1 };
        int maxRobbedTabulation = solution.robTabulation(nums3);
        System.out.println(maxRobbedTabulation);

        int[] nums4 = { 1, 2, 3 };
        int maxRobbedOptimization = solution.robSpaceOptimization(nums4);
        System.out.println(maxRobbedOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O(N)
     * - Two subproblems of size (n - 1), each solved in O(N)
     * - Array copy takes O(N)
     * - Total: O(N)
     * SC: O(N)
     * - O(N) for temporary arrays (nums1, nums2)
     * - O(1) extra space for DP
     *
     * Accepted (75 / 75 testcases passed)
     */
    public int robSpaceOptimization(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] nums1 = new int[n - 1]; // SC: O(N)
        int[] nums2 = new int[n - 1]; // SC: O(N)
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            nums1[i] = nums[i];
            nums2[i] = nums[i + 1];
        }
        return Math.max(solveOptimization(nums1),
                solveOptimization(nums2)); // TC: O(N + N), SC: O(N)
    }

    /**
     * Using Space Optimization (Optimized DP)
     *
     * TC: O(N) - each house processed once
     * SC: O(1) - only two variables used
     */
    public int solveOptimization(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            // Edge case handled as per constraints, 1 <= nums.length <= 100
            return nums[0];
        }
        int prev2 = 0;
        int prev1 = nums[0];
        for (int i = 1; i < n; i++) {
            // rob nth house and then try with (n - 2)th house
            int option1 = nums[i];
            if (i > 1) {
                option1 += prev2;
            }
            // do not rob nth house so try with (n - 1)th house
            int option2 = prev1;
            int current = Math.max(option1, option2);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     *
     * TC: O(N)
     * - Two subproblems of size (n - 1), each solved in O(N)
     * - Array copy takes O(N)
     * - Total: O(N)
     * SC: O(N)
     * - O(N) for temporary arrays (nums1, nums2)
     * - O(N) for DP array
     *
     * Accepted (75 / 75 testcases passed)
     */
    public int robTabulation(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] nums1 = new int[n - 1]; // SC: O(N)
        int[] nums2 = new int[n - 1]; // SC: O(N)
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            nums1[i] = nums[i];
            nums2[i] = nums[i + 1];
        }
        return Math.max(solveTabulation(nums1),
                solveTabulation(nums2)); // TC: O(N + N), SC: O(N)
    }

    /**
     * Using Tabulation (Bottom-Up DP)
     *
     * TC: O(N)
     * - Two subproblems of size (n - 1), each solved in O(N)
     * - Array copy takes O(N)
     * - Total: O(N)
     * SC: O(N)
     * - dp[] array of size N
     */
    private int solveTabulation(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            // rob nth house and then try with (n - 2)th house
            int option1 = nums[i];
            if (i > 1) {
                option1 += dp[i - 2];
            }
            // do not rob nth house so try with (n - 1)th house
            int option2 = dp[i - 1];
            dp[i] = Math.max(option1, option2);
        }
        return dp[len - 1];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     *
     * TC: O(N)
     * - Two subproblems of size (n - 1), each solved in O(N)
     * - Array copy takes O(N)
     * - Total: O(N)
     * SC: O(N)
     * - O(N) for recursion stack
     * - O(N) for each of the two temporary arrays (nums1, nums2)
     *
     * Accepted (75 / 75 testcases passed)
     */
    public int robMemoization(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] nums1 = new int[n - 1]; // SC: O(N)
        int[] nums2 = new int[n - 1]; // SC: O(N)
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            nums1[i] = nums[i];
            nums2[i] = nums[i + 1];
        }
        int[] memo1 = new int[n - 1];
        int[] memo2 = new int[n - 1];
        Arrays.fill(memo1, -1);
        Arrays.fill(memo2, -1);
        return Math.max(solveMemoization(n - 2, nums1, memo1),
                solveMemoization(n - 2, nums2, memo2)); // TC: O(N + N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N) - Each index is computed once and memoized
     * SC: O(N) - Due to recursion stack depth
     */
    private int solveMemoization(int n, int[] nums, int[] memo) {
        // Base Case
        if (n == 0) {
            return nums[0];
        }
        // Memoization Check
        if (memo[n] != -1) {
            return memo[n];
        }
        // Recursion Calls
        // rob nth house and then try with (n - 2)th house
        int option1 = nums[n];
        if (n > 1) {
            option1 += solveMemoization(n - 2, nums, memo);
        }
        // do not rob nth house so try with (n - 1)th house
        int option2 = solveMemoization(n - 1, nums, memo);
        return memo[n] = Math.max(option1, option2);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * - For each subarray (n - 1 houses), exponential recursion
     * - Total TC = 2 x O(2 ^ N) + O(N) for array copy → O(2 ^ N)
     * SC: O(N)
     * - O(N) for recursion stack
     * - O(N) for each of the two temporary arrays (nums1, nums2)
     *
     * Time Limit Exceeded (62 / 75 testcases passed)
     */
    public int robRecursion(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] nums1 = new int[n - 1]; // SC: O(N)
        int[] nums2 = new int[n - 1]; // SC: O(N)
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            nums1[i] = nums[i];
            nums2[i] = nums[i + 1];
        }
        return Math.max(solveRecursion(n - 2, nums1),
                solveRecursion(n - 2, nums2)); // TC: O(2 x 2 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N) - At each index, you decide to rob or skip → exponential
     * branching
     * SC: O(N) - Due to recursion stack depth
     */
    private int solveRecursion(int n, int[] nums) {
        // Base Case
        if (n == 0) {
            return nums[0];
        }
        // Recursion Calls
        // rob nth house and then try with (n - 2)th house
        int option1 = nums[n];
        if (n > 1) {
            option1 += solveRecursion(n - 2, nums);
        }
        // do not rob nth house so try with (n - 1)th house
        int option2 = solveRecursion(n - 1, nums);
        return Math.max(option1, option2);
    }
}
