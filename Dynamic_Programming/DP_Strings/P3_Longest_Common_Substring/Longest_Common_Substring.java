package Dynamic_Programming.DP_Strings.P3_Longest_Common_Substring;

public class Longest_Common_Substring {
    public static void main(String[] args) {
        Longest_Common_Substring solution = new Longest_Common_Substring();

        String s1 = "ABCDGH";
        String s2 = "ACDGHR";

        int maxLengthRecursion = solution.longestCommonSubstrRecursion(s1, s2);
        System.out.println(maxLengthRecursion);

        int maxLengthTabulation = solution.longestCommonSubstrTabulation(s1, s2);
        System.out.println(maxLengthTabulation);

        int maxLengthOptimization = solution.longestCommonSubstrSpaceOptimization(s1, s2);
        System.out.println(maxLengthOptimization);
    }

    /**
     * Approach III : Using Space Optimization Approach
     * 
     * TC: O(M x N)
     * SC: O(N) + O(N)
     * 
     * - O(N) - prev and current array memory
     * 
     * Accepted (219 / 219 testcases passed)
     */
    public int longestCommonSubstrSpaceOptimization(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int maxLength = 0;
        // Initialization
        int[] prev = new int[n + 1]; // SC: O(N)
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            int[] current = new int[n + 1]; // SC: O(N)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    current[j] = 1 + prev[j - 1];
                } else {
                    current[j] = 0;
                }
                maxLength = Math.max(maxLength, current[j]);
            }
            prev = current.clone();
        }
        return maxLength;
    }

    /**
     * Approach II : Using Tabulation Approach
     * 
     * TC: O(M x N)
     * SC: O(M x N)
     * 
     * - O(M x N) - dp array memory
     * 
     * Accepted (219 / 219 testcases passed)
     */
    public int longestCommonSubstrTabulation(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int maxLength = 0;
        // Initialization
        int[][] dp = new int[m + 1][n + 1]; // SC: O(M x N)
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 0;
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     * 
     * - O(M + N) - recursion stack memory
     * 
     * Time Limit Exceeded (0 / 219 testcases passed)
     */
    public int longestCommonSubstrRecursion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        return solveRecursion(s1, s2, m - 1, n - 1, 0); // TC: O(2 ^ (M + N)), SC: O(M + N)
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     */
    private int solveRecursion(String s1, String s2, int m, int n, int maxLength) {
        // Base Case
        if (m < 0 || n < 0) {
            return maxLength;
        }
        // Recursion Calls
        int include = maxLength;
        int exclude1 = 0;
        int exclude2 = 0;
        if (s1.charAt(m) == s2.charAt(n)) {
            include = solveRecursion(s1, s2, m - 1, n - 1, maxLength + 1);
        } else {
            // reset maxLength = 0
            exclude1 = solveRecursion(s1, s2, m - 1, n, 0);
            exclude2 = solveRecursion(s1, s2, m, n - 1, 0);
        }
        return Math.max(include, Math.max(exclude1, exclude2));
    }
}
