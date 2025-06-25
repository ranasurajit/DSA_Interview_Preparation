package Dynamic_Programming.DP_1D.P2_Frog_Jump;

import java.util.Arrays;

public class Frog_Jump {
    public static void main(String[] args) {
        Frog_Jump solution = new Frog_Jump();

        int[] heights1 = { 20, 30, 40, 20 };
        int minCostRecursion = solution.minCostRecursion(heights1);
        System.out.println(minCostRecursion);

        int[] heights2 = { 20, 30, 40, 20 };
        int minCostMemoization = solution.minCostMemoization(heights2);
        System.out.println(minCostMemoization);

        int[] heights3 = { 30, 20, 50, 10, 40 };
        int minCostTabulation = solution.minCostTabulation(heights3);
        System.out.println(minCostTabulation);

        int[] heights4 = { 30, 20, 50, 10, 40 };
        int minCostOptimization = solution.minCostSpaceOptimization(heights4);
        System.out.println(minCostOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * Accepted (1115 / 1115 testcases passed)
     */
    int minCostSpaceOptimization(int[] height) {
        int n = height.length;
        if (n == 1) {
            return 0;
        }
        int prev2 = 0;
        int prev1 = Math.abs(height[1] - height[0]) + prev2;
        for (int i = 2; i < n; i++) { // TC: O(N)
            int current = Math.min(
                    Math.abs(height[i] - height[i - 1]) + prev1,
                    Math.abs(height[i] - height[i - 2]) + prev2);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N)
     * SC: O(N)
     *
     * Accepted (1115 / 1115 testcases passed)
     */
    int minCostTabulation(int[] height) {
        int n = height.length;
        if (n == 1) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = Math.abs(height[1] - height[0]) + dp[0];
        for (int i = 2; i < n; i++) { // TC: O(N)
            dp[i] = Math.min(
                    Math.abs(height[i] - height[i - 1]) + dp[i - 1],
                    Math.abs(height[i] - height[i - 2]) + dp[i - 2]);
        }
        return dp[n - 1];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N + N)
     *
     * Accepted (1115 / 1115 testcases passed)
     */
    int minCostMemoization(int[] height) {
        int n = height.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return solveMemoization(n - 1, height, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int solveMemoization(int n, int[] height, int[] memo) {
        // Base Case
        if (n == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[n] != -1) {
            return memo[n];
        }
        // Recursion Calls
        int cost1Step = Math.abs(height[n] - height[n - 1]) +
                solveMemoization(n - 1, height, memo);
        int cost2Step = Integer.MAX_VALUE;
        if (n > 1) {
            cost2Step = Math.abs(height[n] - height[n - 2]) +
                    solveMemoization(n - 2, height, memo);
        }
        return memo[n] = Math.min(cost1Step, cost2Step);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (10 / 1115 testcases passed)
     */
    int minCostRecursion(int[] height) {
        int n = height.length;
        return solveRecursion(n - 1, height);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int n, int[] height) {
        // Base Case
        if (n == 0) {
            return 0;
        }
        // Recursion Calls
        int cost1Step = Math.abs(height[n] - height[n - 1]) +
                solveRecursion(n - 1, height);
        int cost2Step = Integer.MAX_VALUE;
        if (n > 1) {
            cost2Step = Math.abs(height[n] - height[n - 2]) +
                    solveRecursion(n - 2, height);
        }
        return Math.min(cost1Step, cost2Step);
    }
}
