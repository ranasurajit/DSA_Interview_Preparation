package DP.DP_Strings.P02_Print_Longest_Common_Subsequence;

import java.util.Arrays;

public class Print_Longest_Common_Subsequence {
    public static void main(String[] args) {
        Print_Longest_Common_Subsequence solution = new Print_Longest_Common_Subsequence();

        int n = 5;
        int m = 4;
        String s1 = "abcab";
        String s2 = "cbab";

        String lcsString1 = solution.findLCSMemoization(n, m, s1, s2);
        System.out.println(lcsString1);

        n = 3;
        m = 3;
        String s3 = "xyz";
        String s4 = "abc";

        String lcsString2 = solution.findLCSMemoization(n, m, s3, s4);
        System.out.println(lcsString2);

        n = 6;
        m = 5;
        String s5 = "abcdef";
        String s6 = "abhef";

        String lcsString3 = solution.findLCSTabulation(n, m, s5, s6);
        System.out.println(lcsString3);

        n = 3;
        m = 3;
        String s7 = "xyz";
        String s8 = "pqr";
        String lcsString4 = solution.findLCSTabulation(n, m, s7, s8);
        System.out.println(lcsString4);
    }

    /**
     * Approach II : Using Tabulation + Two Pointers Approach
     *
     * TC: O(N x M + (N + M) + Max(N, M)) ~ O(N x M + Max(N, M))
     * SC: O(N x M)
     * 
     * @param n
     * @param m
     * @param s1
     * @param s2
     * @return
     */
    public String findLCSTabulation(int n, int m, String s1, String s2) {
        // Initialization
        int[][] dp = new int[n + 1][m + 1]; // SC: O(N x M)
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            dp[i][0] = 0;
        }
        for (int j = 0; j < m + 1; j++) { // TC: O(M)
            dp[0][j] = 0;
        }
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < m + 1; j++) { // TC: O(M)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // Printing the LCS Using Two Pointers Approach
        StringBuilder sb = new StringBuilder();
        int i = n;
        int j = m;
        while (i > 0 && j > 0) { // TC: O(Max(N, M))
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.insert(0, s1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return sb.toString();
    }

    /**
     * Approach I : Using Memoization + Two Pointers Approach
     *
     * TC: O(N x M + Max(N, M))
     * SC: O(N x M + (N + M))
     */
    public String findLCSMemoization(int n, int m, String s1, String s2) {
        int[][] memo = new int[n + 1][m + 1];
        for (int[] memoItem : memo) {
            Arrays.fill(memoItem, -1);
        }
        solveMemoization(n, m, s1, s2, memo);
        // Printing the LCS Using Two Pointers Approach
        StringBuilder sb = new StringBuilder();
        int i = n;
        int j = m;
        while (i > 0 && j > 0) { // TC: O(Max(N, M))
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (memo[i - 1][j] > memo[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return sb.reverse().toString();
    }

    /**
     * Using Memoization
     *
     * TC: O(N x M)
     * SC: O(N x M + (N + M))
     */
    private int solveMemoization(int n, int m, String s1, String s2, int[][] memo) {
        // Base Case
        if (m == 0 || n == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[n][m] != -1) {
            return memo[n][m];
        }
        // Recursion Calls
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return memo[n][m] = 1 + solveMemoization(n - 1, m - 1, s1, s2, memo);
        } else {
            return memo[n][m] = Math.max(
                    solveMemoization(n - 1, m, s1, s2, memo),
                    solveMemoization(n, m - 1, s1, s2, memo));
        }
    }
}
