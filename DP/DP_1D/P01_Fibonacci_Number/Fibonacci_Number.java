package DP.DP_1D.P01_Fibonacci_Number;

import java.util.Arrays;

public class Fibonacci_Number {
    public static void main(String[] args) {
        Fibonacci_Number solution = new Fibonacci_Number();

        int n = 30;

        int fibRecur = solution.fibRecursion(n);
        System.out.println(fibRecur);

        int fibMemo = solution.fibMemoization(n);
        System.out.println(fibMemo);

        int fibTab = solution.fibTabulation(n);
        System.out.println(fibTab);

        int fibOpt = solution.fibOptimization(n);
        System.out.println(fibOpt);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    public int fibRecursion(int n) {
        return solveRecursion(n);
    }

    private int solveRecursion(int n) {
        // Base Case
        if (n == 0 || n == 1) {
            return n;
        }
        // Recursion Calls
        return solveRecursion(n - 1) + solveRecursion(n - 2);
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N + N) ~ O(N)
     */
    public int fibMemoization(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return solveMemoization(n, memo);
    }

    private int solveMemoization(int n, int[] memo) {
        // Base Case
        if (n == 0 || n == 1) {
            return n;
        }
        // Memoization Check
        if (memo[n] != -1) {
            return memo[n];
        }
        // Recursion Calls
        return memo[n] = solveMemoization(n - 1, memo) + solveMemoization(n - 2, memo);
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int fibTabulation(int n) {
        int[] dp = new int[n + 1]; // SC: O(N)
        // initialization
        dp[0] = 0;
        if (n > 0) {
            dp[1] = 1;
        }
        // iterative call
        for (int i = 2; i < n + 1; i++) { // TC: O(N)
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Approach IV : Using Space Optimization Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int fibOptimization(int n) {
        int current = 0;
        int prev = 1;
        int prev2 = 0;
        if (n <= 1) {
            return n;
        }
        /**
         * iterative call
         * replace dp[i] with current, dp[i - 1] with prev and dp[i - 2] as prev2
         */
        for (int i = 2; i < n + 1; i++) { // TC: O(N)
            current = prev + prev2;
            prev2 = prev;
            prev = current;
        }
        return prev;
    }
}
