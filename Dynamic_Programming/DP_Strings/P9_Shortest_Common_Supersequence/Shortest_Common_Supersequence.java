package Dynamic_Programming.DP_Strings.P9_Shortest_Common_Supersequence;

import java.util.Arrays;

public class Shortest_Common_Supersequence {
    public static void main(String[] args) {
        Shortest_Common_Supersequence solution = new Shortest_Common_Supersequence();

        String str1 = "abac";
        String str2 = "cab";

        String scsMemoization = solution.shortestCommonSupersequenceMemoization(str1, str2);
        System.out.println(scsMemoization);

        String scsTabulation = solution.shortestCommonSupersequenceTabulation(str1, str2);
        System.out.println(scsTabulation);
    }

    /**
     * Approach II : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(M x N) + O(M + N) ~ O(M x N)
     * SC: O(M x N) + O(M + N)
     * 
     * - O(M x N) - memoization memory
     * - O(M + N) - StringBuilder memory
     *
     * Accepted (50 / 50 testcases passed)
     */
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder(); // SC: O(M + N)
        // Using Two Pointers Approach
        int i = m;
        int j = n;
        while (i > 0 && j > 0) { // TC: O(M + N)
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    sb.append(str2.charAt(j - 1));
                    j--;
                } else {
                    sb.append(str1.charAt(i - 1));
                    i--;
                }
            }
        }
        while (i > 0) {
            sb.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            sb.append(str2.charAt(j - 1));
            j--;
        }
        return sb.reverse().toString();
    }

    /**
     * Approach II : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(M x N) + O(M + N) ~ O(M x N)
     * SC: O(M x N) + O(M + N)
     * 
     * - O(M x N) - memoization memory
     * - O(M + N) - StringBuilder memory
     *
     * Accepted (50 / 50 testcases passed)
     */
    public String shortestCommonSupersequenceTabulation(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder(); // SC: O(M + N)
        // Using Two Pointers Approach
        int i = m;
        int j = n;
        while (i > 0 && j > 0) { // TC: O(M + N)
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    sb.append(str2.charAt(j - 1));
                    j--;
                } else {
                    sb.append(str1.charAt(i - 1));
                    i--;
                }
            }
        }
        while (i > 0) {
            sb.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            sb.append(str2.charAt(j - 1));
            j--;
        }
        return sb.reverse().toString();
    }

    /**
     * Approach I : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(M x N) + O(M x N) + O(M + N) ~ O(M x N)
     * SC: O(M x N) + O(M + N) + O(M + N)
     * 
     * - O(M x N) - memoization memory
     * - O(M + N) - recursion stack
     * - O(M + N) - StringBuilder memory
     *
     * Accepted (50 / 50 testcases passed)
     */
    public String shortestCommonSupersequenceMemoization(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] memo = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int[] mem : memo) { // TC: O(M)
            Arrays.fill(mem, -1); // TC: O(N)
        }
        solveMemoization(str1, str2, m, n, memo); // TC: O(M x N), SC: O(M + N)
        StringBuilder sb = new StringBuilder(); // SC: O(M + N)
        // Using Two Pointers Approach
        int i = m;
        int j = n;
        while (i > 0 && j > 0) { // TC: O(M + N)
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (memo[i][j - 1] > memo[i - 1][j]) {
                    sb.append(str2.charAt(j - 1));
                    j--;
                } else {
                    sb.append(str1.charAt(i - 1));
                    i--;
                }
            }
        }
        while (i > 0) {
            sb.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            sb.append(str2.charAt(j - 1));
            j--;
        }
        return sb.reverse().toString();
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M + N)
     */
    private int solveMemoization(String str1, String str2, int m, int n, int[][] memo) {
        // Base Case
        if (m == 0 || n == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        // Recursion Calls
        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return memo[m][n] = 1 + solveMemoization(str1, str2, m - 1, n - 1, memo);
        } else {
            return memo[m][n] = Math.max(
                    solveMemoization(str1, str2, m - 1, n, memo),
                    solveMemoization(str1, str2, m, n - 1, memo));
        }
    }
}
