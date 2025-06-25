package Dynamic_Programming.DP_1D.P1_Climbing_Stairs;

import java.util.Arrays;

public class Climbing_Stairs {
    public static void main(String[] args) {
        Climbing_Stairs solution = new Climbing_Stairs();

        int n1 = 3;
        int numWaysRecursion = solution.climbStairsRecursion(n1);
        System.out.println(numWaysRecursion);

        int n2 = 5;
        int numWaysMemoization = solution.climbStairsMemoization(n2);
        System.out.println(numWaysMemoization);

        int n3 = 30;
        int numWaysTabulation = solution.climbStairsTabulation(n3);
        System.out.println(numWaysTabulation);

        int n4 = 45;
        int numWaysOptimization = solution.climbStairsSpaceOptimization(n4);
        System.out.println(numWaysOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(N)
     * SC: O(1)
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int climbStairsSpaceOptimization(int n) {
        int prev1 = 1;
        int prev2 = 1;
        for (int i = 2; i < n + 1; i++) { // TC: O(N)
            int current = prev1 + prev2;
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
     * Accepted (45 / 45 testcases passed)
     */
    public int climbStairsTabulation(int n) {
        int[] dp = new int[n + 1]; // SC: O(N)
        dp[0] = 1; // ways to reach 0th step
        dp[1] = 1; // ways to reach 1st step
        for (int i = 2; i < n + 1; i++) { // TC: O(N)
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N + N) ~ O(N)
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int climbStairsMemoization(int n) {
        int[] memo = new int[n + 1]; // SC: O(N)
        Arrays.fill(memo, -1);
        return solveMemoization(n, memo); // TC: O(N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int solveMemoization(int n, int[] memo) {
        // Base Case
        if (n == 0) {
            // number of ways to reach 0th stair from position 0
            return 1;
        }
        // Memoization Check
        if (memo[n] != -1) {
            return memo[n];
        }
        // Recursion Calls
        // ways to reach nth step can be contributed from ways of reaching (n - 1)th
        // stair and do 1 jump
        int ways1Step = solveMemoization(n - 1, memo);
        // ways to reach nth step can be contributed from ways of reaching (n - 2)th
        // stair and do 2 jumps
        int ways2Step = 0;
        if (n > 1) {
            ways2Step = solveMemoization(n - 2, memo);
        }
        return memo[n] = ways1Step + ways2Step;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (21 / 45 testcases passed)
     */
    public int climbStairsRecursion(int n) {
        return solveRecursion(n);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int n) {
        // Base Case
        if (n == 0) {
            // number of ways to reach 0th stair from position 0
            return 1;
        }
        // Recursion Calls
        // ways to reach nth step can be contributed from ways of reaching (n - 1)th
        // stair and do 1 jump
        int ways1Step = solveRecursion(n - 1);
        // ways to reach nth step can be contributed from ways of reaching (n - 2)th
        // stair and do 2 jumps
        int ways2Step = 0;
        if (n > 1) {
            ways2Step = solveRecursion(n - 2);
        }
        return ways1Step + ways2Step;
    }
}
