package Dynamic_Programming.DP_Strings.P2_Print_Longest_Common_Subsequence;

import java.util.Arrays;

public class Print_Longest_Common_Subsequence {
    public static void main(String[] args) {
        Print_Longest_Common_Subsequence solution = new Print_Longest_Common_Subsequence();

        int n = 5;
        int m = 6;
        String s1 = "ababa";
        String s2 = "cbbcad";

        String lcsMemoization = solution.findLCSMemoization(n, m, s1, s2);
        System.out.println(lcsMemoization);

        String lcsTabulation = solution.findLCSTabulation(n, m, s1, s2);
        System.out.println(lcsTabulation);
    }

    /**
     * Approach II : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x M) + O(Min(N, M)) ~ O(N x M)
     * SC: O(N x M)
     * 
     * - O(N x M) - dp array memory
     *
     * Accepted (30 / 30 testcases passed)
     */
    public String findLCSTabulation(int n, int m, String s1, String s2) {
        int[][] dp = new int[n + 1][m + 1]; // SC: O(N x M)
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < m + 1; j++) { // TC: O(M)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        /**
         * now we have the dp table array from which
         * we need to figure out the path and get the LCS string
         */
        // Using Two Pointers Approach
        int i = n;
        int j = m;
        // i and j are pointers of coordinate of memo[n][n]
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) { // TC: O(Min(N, M))
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j - 1] > dp[i - 1][j]) {
                j--;
            } else {
                i--;
            }
        }
        return sb.reverse().toString(); // TC: O(Min(N, M))
    }

    /**
     * Approach I : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x M) + O(N x M) + O(Min(N, M)) ~ O(N x M)
     * SC: O(N x M) + O(N + M)
     * 
     * - O(N x M) - memoization memory
     * - O(N + M) - recursion stack
     *
     * Accepted (30 / 30 testcases passed)
     */
    public String findLCSMemoization(int n, int m, String s1, String s2) {
        int[][] memo = new int[n + 1][m + 1]; // SC: O(N x M)
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(M)
        }
        solveMemoization(s1, s2, n, m, memo); // TC: O(N x M), SC: O(N + M)
        /**
         * now we have the memoized table array from which
         * we need to figure out the path and get the LCS string
         */
        // Using Two Pointers Approach
        int i = n;
        int j = m;
        // i and j are pointers of coordinate of memo[n][n]
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) { // TC: O(Min(N, M))
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (memo[i][j - 1] > memo[i - 1][j]) {
                j--;
            } else {
                i--;
            }
        }
        return sb.reverse().toString(); // TC: O(Min(N, M))
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x M)
     * SC: O(N + M)
     */
    private int solveMemoization(String s1, String s2, int n, int m, int[][] memo) {
        // Base Case
        if (n == 0 || m == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[n][m] != -1) {
            return memo[n][m];
        }
        // Recursion Calls
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return memo[n][m] = 1 + solveMemoization(s1, s2, n - 1, m - 1, memo);
        } else {
            return memo[n][m] = Math.max(solveMemoization(s1, s2, n - 1, m, memo),
                    solveMemoization(s1, s2, n, m - 1, memo));
        }
    }
}
