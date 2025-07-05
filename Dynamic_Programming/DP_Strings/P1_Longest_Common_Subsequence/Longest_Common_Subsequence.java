package Dynamic_Programming.DP_Strings.P1_Longest_Common_Subsequence;

import java.util.Arrays;

public class Longest_Common_Subsequence {
    public static void main(String[] args) {
        Longest_Common_Subsequence solution = new Longest_Common_Subsequence();

        String text1 = "abcde";
        String text2 = "ace";

        int lcsRecursive = solution.longestCommonSubsequenceRecursion(text1, text2);
        System.out.println(lcsRecursive);

        int lcsMemoization = solution.longestCommonSubsequenceMemoization(text1, text2);
        System.out.println(lcsMemoization);

        int lcsTabulation = solution.longestCommonSubsequenceTabulation(text1, text2);
        System.out.println(lcsTabulation);

        int lcsOptimization = solution.longestCommonSubsequenceOptimization(text1, text2);
        System.out.println(lcsOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(M x N)
     * SC: O(N) + O(N) ~ O(N)
     * 
     * - O(N) - prev and current array memory
     *
     * Accepted (47 / 47 testcases passed)
     */
    public int longestCommonSubsequenceOptimization(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // Initialization
        int[] prev = new int[n + 1]; // SC: O(N)
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            int[] current = new int[n + 1]; // SC: O(N)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    current[j] = 1 + prev[j - 1];
                } else {
                    current[j] = Math.max(prev[j], current[j - 1]);
                }
            }
            prev = current.clone();
        }
        return prev[n];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(M x N)
     * SC: O(M x N)
     * 
     * - O(M x N) - dp array memory
     *
     * Accepted (47 / 47 testcases passed)
     */
    public int longestCommonSubsequenceTabulation(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // Initialization
        int[][] dp = new int[m + 1][n + 1]; // SC: O(M x N)
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
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
     * Accepted (47 / 47 testcases passed)
     */
    public int longestCommonSubsequenceMemoization(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] memo = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int[] mem : memo) { // TC: O(M)
            Arrays.fill(mem, -1); // TC: O(N)
        }
        return solveMemoization(text1, text2, m - 1, n - 1, memo); // TC: O(M x N), SC: O(M + N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M + N)
     */
    private int solveMemoization(String text1, String text2, int m, int n, int[][] memo) {
        // Base Case
        if (m < 0 || n < 0) {
            return 0;
        }
        // Memoization Check
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        // Recursion Calls
        if (text1.charAt(m) == text2.charAt(n)) {
            return memo[m][n] = 1 + solveMemoization(text1, text2, m - 1, n - 1, memo);
        } else {
            return memo[m][n] = Math.max(solveMemoization(text1, text2, m - 1, n, memo),
                    solveMemoization(text1, text2, m, n - 1, memo));
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
     * Time Limit Exceeded (17 / 47 testcases passed)
     */
    public int longestCommonSubsequenceRecursion(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        return solveRecursion(text1, text2, m - 1, n - 1);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     */
    private int solveRecursion(String text1, String text2, int m, int n) {
        // Base Case
        if (m < 0 || n < 0) {
            return 0;
        }
        // Recursion Calls
        if (text1.charAt(m) == text2.charAt(n)) {
            return 1 + solveRecursion(text1, text2, m - 1, n - 1);
        } else {
            return Math.max(solveRecursion(text1, text2, m - 1, n),
                    solveRecursion(text1, text2, m, n - 1));
        }
    }
}
