package Dynamic_Programming.DP_Strings.P8_Edit_Distance;

import java.util.Arrays;

public class Edit_Distance {
    public static void main(String[] args) {
        Edit_Distance solution = new Edit_Distance();

        String word1 = "intention";
        String word2 = "execution";

        int minDistanceRecursive = solution.minDistanceRecursion(word1, word2);
        System.out.println(minDistanceRecursive);

        int minDistanceMemoization = solution.minDistanceMemoization(word1, word2);
        System.out.println(minDistanceMemoization);

        int minDistanceTabulation = solution.minDistanceTabulation(word1, word2);
        System.out.println(minDistanceTabulation);

        int minDistanceOptimization = solution.minDistanceSpaceOptimization(word1, word2);
        System.out.println(minDistanceOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(M x N) + O(N) ~ O(M x N)
     * SC: O(N) + O(N) ~ O(N)
     * 
     * - O(N) - prev and current array memory
     *
     * Accepted (1147 / 1147 testcases passed)
     */
    public int minDistanceSpaceOptimization(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // Initialization
        int[] prev = new int[n + 1]; // SC: O(N)
        for (int j = 0; j < n + 1; j++) { // TC: O(N)
            prev[j] = j;
        }
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            int[] current = new int[n + 1]; // SC: O(N)
            current[0] = i;
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    current[j] = prev[j - 1];
                } else {
                    current[j] = 1 + Math.min(prev[j - 1], Math.min(prev[j], current[j - 1]));
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
     * - O(M x N) - dp table memory
     *
     * Accepted (1147 / 1147 testcases passed)
     */
    public int minDistanceTabulation(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // Initialization
        int[][] dp = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int i = 0; i < m + 1; i++) { // TC: O(M)
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) { // TC: O(N)
            dp[0][j] = j;
        }
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
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
     * Accepted (1147 / 1147 testcases passed)
     */
    public int minDistanceMemoization(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] memo = new int[m][n]; // SC: O(M x N)
        for (int[] mem : memo) { // TC: O(M)
            Arrays.fill(mem, -1); // TC: O(N)
        }
        return solveMemoization(word1, word2, m - 1, n - 1, memo); // TC: O(M x N), SC: O(M + N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M + N)
     */
    private int solveMemoization(String word1, String word2, int m, int n, int[][] memo) {
        // Base Case
        if (m < 0) {
            /**
             * we have exhaused all Characters from word1 so we need
             * add remaining Characters from word2 from 0 to n index
             */
            return n + 1;
        }
        if (n < 0) {
            /**
             * we have exhaused all Characters from word2 so we need
             * delete remaining Characters from word1 from 0 to m index
             */
            return m + 1;
        }
        // Memoization Check
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        // Recursion Calls
        if (word1.charAt(m) == word2.charAt(n)) {
            // as the Characters match, so no need to perform any operation
            return memo[m][n] = solveMemoization(word1, word2, m - 1, n - 1, memo);
        }
        // here the Characters do not match at index n1 and n2
        int insertOperations = 1 + solveMemoization(word1, word2, m, n - 1, memo);
        int deleteOperations = 1 + solveMemoization(word1, word2, m - 1, n, memo);
        int replaceOperations = 1 + solveMemoization(word1, word2, m - 1, n - 1, memo);
        return memo[m][n] = Math.min(insertOperations, Math.min(deleteOperations, replaceOperations));
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ Max(M, N))
     * SC: O(M + N)
     *
     * - O(M + N) - recursion stack
     *
     * Time Limit Exceeded (26 / 1147 testcases passed)
     */
    public int minDistanceRecursion(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        return solveRecursion(word1, word2, m - 1, n - 1);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ Max(M, N))
     * SC: O(M + N)
     */
    private int solveRecursion(String word1, String word2, int m, int n) {
        // Base Case
        if (m < 0) {
            /**
             * we have exhaused all Characters from word1 so we need
             * add remaining Characters from word2 from 0 to n index
             */
            return n + 1;
        }
        if (n < 0) {
            /**
             * we have exhaused all Characters from word2 so we need
             * delete remaining Characters from word1 from 0 to m index
             */
            return m + 1;
        }
        // Recursion Calls
        if (word1.charAt(m) == word2.charAt(n)) {
            // as the Characters match, so no need to perform any operation
            return solveRecursion(word1, word2, m - 1, n - 1);
        }
        // here the Characters do not match at index n1 and n2
        int insertOperations = 1 + solveRecursion(word1, word2, m, n - 1);
        int deleteOperations = 1 + solveRecursion(word1, word2, m - 1, n);
        int replaceOperations = 1 + solveRecursion(word1, word2, m - 1, n - 1);
        return Math.min(insertOperations, Math.min(deleteOperations, replaceOperations));
    }
}
