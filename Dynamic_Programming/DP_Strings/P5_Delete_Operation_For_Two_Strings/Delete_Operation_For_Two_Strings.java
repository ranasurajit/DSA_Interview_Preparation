package Dynamic_Programming.DP_Strings.P5_Delete_Operation_For_Two_Strings;

public class Delete_Operation_For_Two_Strings {
    public static void main(String[] args) {
        Delete_Operation_For_Two_Strings solution = new Delete_Operation_For_Two_Strings();

        String word1 = "leetcode";
        String word2 = "etco";

        int minInsertDeleteTabulation = solution.minDistanceTabulation(word1, word2);
        System.out.println(minInsertDeleteTabulation);

        int minInsertDeleteSpaceOptimization = solution.minDistanceSpaceOptimization(word1, word2);
        System.out.println(minInsertDeleteSpaceOptimization);
    }

    /**
     * Approach II : Using Space Optimization Approach
     * 
     * TC: O(M x N)
     * SC: O(N) + O(N)
     * 
     * - O(N) - prev and current array memory
     * 
     * Accepted (1306 / 1306 testcases passed)
     */
    public int minDistanceSpaceOptimization(String word1, String word2) {
        /**
         * we can convert this problem into LCS problem as
         * number of steps needed to delete from word1 and
         * word2 = word1.length() - length(LCS) +
         * word2.length() - length(LCS)
         */
        int m = word1.length();
        int n = word2.length();
        // Initialization
        int[] prev = new int[n + 1]; // SC: O(N)
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            int[] current = new int[n + 1]; // SC: O(N)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    current[j] = 1 + prev[j - 1];
                } else {
                    current[j] = Math.max(prev[j], current[j - 1]);
                }
            }
            prev = current.clone();
        }
        return m + n - 2 * prev[n];
    }

    /**
     * Approach I : Using Tabulation Approach
     * 
     * TC: O(M x N)
     * SC: O(M x N)
     * 
     * - O(M x N) - dp array memory
     * 
     * Accepted (1306 / 1306 testcases passed)
     */
    public int minDistanceTabulation(String word1, String word2) {
        /**
         * we can convert this problem into LCS problem as
         * number of steps needed to delete from word1 and
         * word2 = word1.length() - length(LCS) +
         * word2.length() - length(LCS)
         */
        int m = word1.length();
        int n = word2.length();
        // Initialization
        int[][] dp = new int[m + 1][n + 1]; // SC: O(M x N)
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m + n - 2 * dp[m][n];
    }
}
