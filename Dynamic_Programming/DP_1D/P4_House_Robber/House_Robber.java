package Dynamic_Programming.DP_1D.P4_House_Robber;

import java.util.Arrays;

public class House_Robber {
    public static void main(String[] args) {
        House_Robber solution = new House_Robber();

        int[] nums1 = { 1, 2, 3, 1 };
        int maxRobbedRecursion = solution.robRecursion(nums1);
        System.out.println(maxRobbedRecursion);

        int[] nums2 = { 1, 2, 3, 1 };
        int maxRobbedMemoization = solution.robMemoization(nums2);
        System.out.println(maxRobbedMemoization);

        int[] nums3 = { 2, 7, 9, 3, 1 };
        int maxRobbedTabulation = solution.robTabulation(nums3);
        System.out.println(maxRobbedTabulation);

        int[] nums4 = { 2, 7, 9, 3, 1 };
        int maxRobbedOptimization = solution.robSpaceOptimization(nums4);
        System.out.println(maxRobbedOptimization);
    }

    /**
     * Approach IV: Using Space Optimization (Optimized DP)
     *
     * TC: O(N) - Each house is processed once
     * SC: O(1) - - Constant space used (no extra DP array)
     *
     * Accepted (70 / 70 testcases passed)
     */
    public int robSpaceOptimization(int[] nums) {
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
     * Approach III: Using Tabulation (Bottom-Up DP)
     *
     * TC: O(N) - Each house is processed once
     * SC: O(N)
     * - dp[] array of size N
     *
     * Accepted (70 / 70 testcases passed)
     */
    public int robTabulation(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            // rob nth house and then try with (n - 2)th house
            int option1 = nums[i];
            if (i > 1) {
                option1 += dp[i - 2];
            }
            // do not rob nth house so try with (n - 1)th house
            int option2 = dp[i - 1];
            dp[i] = Math.max(option1, option2);
        }
        return dp[n - 1];
    }

    /**
     * Approach II: Using Memoization (Top-Down DP)
     *
     * TC: O(N) - Each subproblem (index) is solved once
     * SC: O(N + N)
     * - O(N) for memo array
     * - O(N) recursion stack depth in worst case
     *
     * Accepted (70 / 70 testcases passed)
     */
    public int robMemoization(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return solveMemoization(n - 1, nums, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N)
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
     * TC: O(2 ^ N) - At each index, you decide to rob or skip → exponential
     * branching
     * SC: O(N) - Due to recursion stack depth
     *
     * Time Limit Exceeded (55 / 70 testcases passed)
     */
    public int robRecursion(int[] nums) {
        int n = nums.length;
        return solveRecursion(n - 1, nums);
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
