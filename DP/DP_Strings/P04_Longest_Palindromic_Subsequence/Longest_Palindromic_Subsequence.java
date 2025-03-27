package DP.DP_Strings.P04_Longest_Palindromic_Subsequence;

import java.util.Arrays;

public class Longest_Palindromic_Subsequence {
    public static void main(String[] args) {
        Longest_Palindromic_Subsequence solution = new Longest_Palindromic_Subsequence();

        String s = "bbbab";

        int maxLengthRecursion = solution.longestPalindromeSubseqRecursion(s);
        System.out.println(maxLengthRecursion);

        int maxLengthMemoization = solution.longestPalindromeSubseqMemoization(s);
        System.out.println(maxLengthMemoization);

        int maxLengthTabulation = solution.longestPalindromeSubseqTabulation(s);
        System.out.println(maxLengthTabulation);

        int maxLengthOptimization = solution.longestPalindromeSubseqOptimization(s);
        System.out.println(maxLengthOptimization);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(N + 2 ^ (2 x N)) ~ O(4 ^ N)
     * SC: O(3 x N) ~ O(N)
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseqRecursion(String s) {
        int n = s.length();
        String s1 = reverse(s, n);
        return lcsRecursion(s, s1, n, n);
    }

    private int lcsRecursion(String s1, String s2, int m, int n) {
        // Base Case
        if (m == 0 || n == 0) {
            return 0;
        }
        // Recursive Calls
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return 1 + lcsRecursion(s1, s2, m - 1, n - 1);
        } else {
            return Math.max(lcsRecursion(s1, s2, m - 1, n),
                    lcsRecursion(s1, s2, m, n - 1));
        }
    }

    /**
     * TC: O(N / 2) ~ O(N)
     * SC: O(N)
     */
    private String reverse(String s, int n) {
        int start = 0;
        int end = n - 1;
        char[] ch = s.toCharArray();
        while (start < end) {
            char temp = ch[end];
            ch[end] = ch[start];
            ch[start] = temp;
            start++;
            end--;
        }
        return String.valueOf(ch);
    }

    /**
     * Approach II : Using Memoization Approach
     *
     * TC: O(N + N x N) ~ O(N X N)
     * SC: O(3 x N + N x N) ~ O(N x N + N)
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseqMemoization(String s) {
        int n = s.length();
        String s1 = reverse(s, n);
        int[][] memo = new int[n + 1][n + 1]; // SC: O(N x N)
        for (int[] memoItem : memo) {
            Arrays.fill(memoItem, -1);
        }
        return lcsMemoization(s, s1, n, n, memo);
    }

    private int lcsMemoization(String s1, String s2, int m, int n, int[][] memo) {
        // Base Case
        if (m == 0 || n == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        // Recursive Calls
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return memo[m][n] = 1 + lcsMemoization(s1, s2, m - 1, n - 1, memo);
        } else {
            return memo[m][n] = Math.max(lcsMemoization(s1, s2, m - 1, n, memo),
                    lcsMemoization(s1, s2, m, n - 1, memo));
        }
    }

    /**
     * Approach III : Using Tabulation Approach
     *
     * TC: O(N + N x N) ~ O(N X N)
     * SC: O(2 x N + N x N) ~ O(N x N)
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseqTabulation(String s) {
        int n = s.length();
        String s1 = reverse(s, n);
        int[][] dp = new int[n + 1][n + 1]; // SC: O(N x N)
        // initialization not needed as 0th index row of dp is by-default zero
        // iterative call
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s.charAt(i - 1) == s1.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }

    /**
     * Approach III : Using Space Optimization Approach
     *
     * TC: O(N + N x N) ~ O(N X N)
     * SC: O(3 x N) ~ O(N)
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseqOptimization(String s) {
        int n = s.length();
        String s1 = reverse(s, n); // TC: O(N), SC: O(N)
        int[] current = new int[n + 1]; // SC: O(N)
        int[] previous = new int[n + 1]; // SC: O(N)
        // initialization not needed as 0th index row of dp is by-default zero
        // iterative call - convert dp[i] as current and dp[i - 1] as previous
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s.charAt(i - 1) == s1.charAt(j - 1)) {
                    current[j] = 1 + previous[j - 1];
                } else {
                    current[j] = Math.max(previous[j], current[j - 1]);
                }
            }
            previous = current.clone();
        }
        return previous[n];
    }
}
