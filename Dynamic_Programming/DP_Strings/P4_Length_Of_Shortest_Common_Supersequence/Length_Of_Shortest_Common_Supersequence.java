package Dynamic_Programming.DP_Strings.P4_Length_Of_Shortest_Common_Supersequence;

public class Length_Of_Shortest_Common_Supersequence {
    public static void main(String[] args) {
        Length_Of_Shortest_Common_Supersequence solution = new Length_Of_Shortest_Common_Supersequence();

        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        int lengthOfSmallestSuperSeqTabulation = solution.shortestCommonSupersequenceTabulation(s1, s2);
        System.out.println(lengthOfSmallestSuperSeqTabulation);

        int lengthOfSmallestSuperSeqOptimization = solution.shortestCommonSupersequenceSpaceOptimization(s1, s2);
        System.out.println(lengthOfSmallestSuperSeqOptimization);
    }

    /**
     * Approach II : Using Space Optimization Approach
     * 
     * TC: O(M x N)
     * SC: O(N) + O(N)
     * 
     * - O(N) - prev and current array memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int shortestCommonSupersequenceSpaceOptimization(String s1, String s2) {
        /**
         * we can get the length of Shortest Common Supersequence by calculating
         * length of Longest Common Sub-sequence (LCS)
         * 
         * So, result = s1.length() + s2.length() - LCS
         * 
         * as LCS is the length which is common to both String s1 and s2 so we need
         * to use this length only once to form the shortest
         */
        int m = s1.length();
        int n = s2.length();
        // Initialization
        int[] prev = new int[n + 1]; // SC: O(N)
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            int[] current = new int[n + 1]; // SC: O(N)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    current[j] = 1 + prev[j - 1];
                } else {
                    current[j] = Math.max(prev[j], current[j - 1]);
                }
            }
            prev = current.clone();
        }
        return m + n - prev[n];
    }

    /**
     * Approach I : Using Tabulation Approach
     * 
     * TC: O(M x N)
     * SC: O(M x N)
     * 
     * - O(M x N) - dp array memory
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    public int shortestCommonSupersequenceTabulation(String s1, String s2) {
        /**
         * we can get the length of Shortest Common Supersequence by calculating
         * length of Longest Common Sub-sequence (LCS)
         * 
         * So, result = s1.length() + s2.length() - LCS
         * 
         * as LCS is the length which is common to both String s1 and s2 so we need
         * to use this length only once to form the shortest
         */
        int m = s1.length();
        int n = s2.length();
        // Initialization
        int[][] dp = new int[m + 1][n + 1]; // SC: O(M x N)
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m + n - dp[m][n];
    }
}
