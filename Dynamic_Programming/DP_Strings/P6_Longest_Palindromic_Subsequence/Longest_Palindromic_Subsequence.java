package Dynamic_Programming.DP_Strings.P6_Longest_Palindromic_Subsequence;

public class Longest_Palindromic_Subsequence {
    public static void main(String[] args) {
        Longest_Palindromic_Subsequence solution = new Longest_Palindromic_Subsequence();

        String s = "bbbab";

        int maxLengthTabulation = solution.longestPalindromeSubseqTabulation(s);
        System.out.println(maxLengthTabulation);

        int maxLengthOptimization = solution.longestPalindromeSubseqOptimization(s);
        System.out.println(maxLengthOptimization);
    }

    /**
     * Approach II : Using Space Optimization Approach
     * 
     * TC: O(M x N) + O(N)
     * SC: O(N) + O(N) + O(N) ~ O(N)
     * 
     * - O(N) - prev and current array memory
     * - O(N) - reverse the String s
     * 
     * Accepted (86 / 86 testcases passed)
     */
    public int longestPalindromeSubseqOptimization(String s) {
        /**
         * this problem can be reduced to finding the LCS
         * of String s and reverse of String s i.e. reverse(s)
         */
        String p = reverse(s); // TC: O(N), SC: O(N)
        int n = s.length();
        // Using Tabulation Approach
        // Initialization
        int[] prev = new int[n + 1]; // SC: O(N)
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            int[] current = new int[n + 1]; // SC: O(N)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s.charAt(i - 1) == p.charAt(j - 1)) {
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
     * Approach I : Using Tabulation Approach
     * 
     * TC: O(N x N) + O(N)
     * SC: O(N x N) + O(N)
     * 
     * - O(N x N) - dp array memory
     * - O(N) - reverse the String s
     * 
     * Accepted (86 / 86 testcases passed)
     */
    public int longestPalindromeSubseqTabulation(String s) {
        /**
         * this problem can be reduced to finding the LCS
         * of String s and reverse of String s i.e. reverse(s)
         */
        String p = reverse(s); // TC: O(N), SC: O(N)
        int n = s.length();
        // Using Tabulation Approach
        // Initialization
        int[][] dp = new int[n + 1][n + 1]; // SC: O(N x N)
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private String reverse(String s) {
        char[] ch = s.toCharArray();
        int start = 0;
        int end = ch.length - 1;
        while (start < end) { // TC: O(N / 2)
            char temp = ch[end];
            ch[end] = ch[start];
            ch[start] = temp;
            start++;
            end--;
        }
        return String.valueOf(ch);
    }
}
