package DP.DP_Strings.P02_Print_Longest_Common_Subsequence;

public class Print_Longest_Common_Subsequence {
    public static void main(String[] args) {
        Print_Longest_Common_Subsequence solution = new Print_Longest_Common_Subsequence();

        int n = 5;
        int m = 4;
        String s1 = "abcab";
        String s2 = "cbab";

        String lcsString1 = solution.findLCS(n, m, s1, s2);
        System.out.println(lcsString1);

        n = 3;
        m = 3;
        String s3 = "xyz";
        String s4 = "abc";

        String lcsString2 = solution.findLCS(n, m, s3, s4);
        System.out.println(lcsString2);
    }

    /**
     * Using DP Bottom-Up (Tabulation) + Two-Pointers Approach
     * 
     * TC: O((N x M) + (N + M))
     * SC: O((N x M) + (N + M))
     * 
     * @param n
     * @param m
     * @param s1
     * @param s2
     * @return
     */
    public String findLCS(int n, int m, String s1, String s2) {
        // initialization
        int[][] dp = new int[n + 1][m + 1]; // SC: O(N x M)
        // if m = 0, then dp[m][0 to n] = 0
        for (int i = 0; i < m + 1; i++) {
            dp[0][i] = 0;
        }
        // if n = 0, then dp[0 to m][n] = 0
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }
        // iterative call
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < m + 1; j++) { // TC: O(M)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // Using Two-Pointers
        StringBuilder sb = new StringBuilder(); // SC: O(N + M)
        int i = n;
        int j = m;
        while (i > 0 && j > 0) { // TC: O(N + M)
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i - 1][j] < dp[i][j - 1]) {
                    sb.append(s1.charAt(i - 1));
                    i--;
                } else {
                    sb.append(s2.charAt(j - 1));
                    j--;
                }
            }
        }
        while (i > 0) {
            sb.append(s1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            sb.append(s2.charAt(j - 1));
            j--;
        }
        return sb.reverse().toString();
    }
}
