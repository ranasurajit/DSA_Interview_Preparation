package Dynamic_Programming.DP_Strings.P10_Distinct_Subsequences;

import java.util.Arrays;

public class Distinct_Subsequences {
    public static void main(String[] args) {
        Distinct_Subsequences solution = new Distinct_Subsequences();

        String s = "babgbag";
        String t = "bag";

        int distinctSubsequencesRecursive = solution.numDistinctRecursion(s, t);
        System.out.println(distinctSubsequencesRecursive);

        int distinctSubsequencesMemoization = solution.numDistinctMemoization(s, t);
        System.out.println(distinctSubsequencesMemoization);

        int distinctSubsequencesTabulation = solution.numDistinctTabulation(s, t);
        System.out.println(distinctSubsequencesTabulation);

        int distinctSubsequencesOptimization = solution.numDistinctSpaceOptimization(s, t);
        System.out.println(distinctSubsequencesOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(M x N)
     * SC: O(N) + O(N) ~ O(N)
     * 
     * - O(N) - prev and current array memory
     *
     * Accepted (66 / 66 testcases passed)
     */
    public int numDistinctSpaceOptimization(String s, String t) {
        int m = s.length();
        int n = t.length();
        // Initialization
        int[] prev = new int[n + 1]; // SC: O(N)
        prev[0] = 1;
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            int[] current = new int[n + 1]; // SC: O(N)
            current[0] = 1;
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    current[j] = prev[j - 1] + prev[j];
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[n];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(M x N) + O(M) + O(N) ~ O(M x N)
     * SC: O(M x N)
     * 
     * - O(M x N) - dp array memory
     *
     * Accepted (66 / 66 testcases passed)
     */
    public int numDistinctTabulation(String s, String t) {
        int m = s.length();
        int n = t.length();
        // Initialization
        int[][] dp = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int i = 0; i < m + 1; i++) { // TC: O(M)
            dp[i][0] = 1;
        }
        for (int j = 0; j < n + 1; j++) { // TC: O(N)
            dp[0][j] = 0;
        }
        dp[0][0] = 1;
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(M x N) + O(M + N)
     * 
     * - O(M x N) - memoization memory
     * - O(M + N) - recursion stack
     *
     * Accepted (66 / 66 testcases passed)
     */
    public int numDistinctMemoization(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] memo = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int[] mem : memo) { // TC: O(M)
            Arrays.fill(mem, -1); // TC: O(N)
        }
        return solveMemoization(s, t, m - 1, n - 1, memo); // TC: O(M x N), SC: O(M + N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M + N)
     */
    private int solveMemoization(String s, String t, int m, int n, int[][] memo) {
        // Base Case
        if (n < 0) {
            // we found a way /distinct subsequence
            return 1;
        }
        if (m < 0) {
            // we are exhaused with String s so we return 0 as n != 0
            return 0;
        }
        // Memoization Check
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        // Recursion Calls
        if (s.charAt(m) == t.charAt(n)) {
            /**
             * we can either take or not take the Character at index m
             * If we take, then we reduce indices from both,
             * else we reduce index of String s only
             */
            return memo[m][n] = solveMemoization(s, t, m - 1, n - 1, memo) +
                    solveMemoization(s, t, m - 1, n, memo);
        } else {
            return memo[m][n] = solveMemoization(s, t, m - 1, n, memo);
        }
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     *
     * - O(M + N) - recursion stack
     *
     * Time Limit Exceeded (55 / 66 testcases passed)
     */
    public int numDistinctRecursion(String s, String t) {
        int m = s.length();
        int n = t.length();
        return solveRecursion(s, t, m - 1, n - 1); // TC: O(2 ^ (M + N)), SC: O(M + N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     */
    private int solveRecursion(String s, String t, int m, int n) {
        // Base Case
        if (n < 0) {
            // we found a way /distinct subsequence
            return 1;
        }
        if (m < 0) {
            // we are exhaused with String s so we return 0 as n != 0
            return 0;
        }
        // Recursion Calls
        if (s.charAt(m) == t.charAt(n)) {
            /**
             * we can either take or not take the Character at index m
             * If we take, then we reduce indices from both,
             * else we reduce index of String s only
             */
            return solveRecursion(s, t, m - 1, n - 1) + solveRecursion(s, t, m - 1, n);
        } else {
            return solveRecursion(s, t, m - 1, n);
        }
    }
}
