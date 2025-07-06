package Dynamic_Programming.DP_Strings.P11_Wildcard_Matching;

import java.util.Arrays;

public class Wildcard_Matching {
    public static void main(String[] args) {
        Wildcard_Matching solution = new Wildcard_Matching();

        String s = "adceb";
        String p = "*a*b";

        boolean isMatchRecursion = solution.isMatchRecursion(s, p);
        System.out.println(isMatchRecursion);

        boolean isMatchMemoization = solution.isMatchMemoization(s, p);
        System.out.println(isMatchMemoization);

        boolean isMatchTabulation = solution.isMatchTabulation(s, p);
        System.out.println(isMatchTabulation);

        boolean isMatchSpaceOptimization = solution.isMatchSpaceOptimization(s, p);
        System.out.println(isMatchSpaceOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(M x N) + O(N x N)
     * SC: O(N) + O(N) ~ O(N)
     * 
     * - O(N) - prev and current array memory
     *
     * Accepted (1811 / 1811 testcases passed)
     */
    public boolean isMatchSpaceOptimization(String s, String p) {
        int m = s.length();
        int n = p.length();
        // Initialization
        boolean[] prev = new boolean[n + 1]; // SC: O(N)
        prev[0] = true;
        for (int j = 1; j < n + 1; j++) { // TC: O(N)
            prev[j] = isAllStars(p, j); // TC: O(N)
        }
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            boolean[] current = new boolean[n + 1]; // SC: O(N)
            current[0] = false;
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                current[j] = false;
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    current[j] = prev[j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    current[j] = current[j - 1] || prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[n];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(M x N) + O(N x N) + O(M)
     * SC: O(M x N)
     * 
     * - O(M x N) - dp array memory
     *
     * Accepted (1811 / 1811 testcases passed)
     */
    public boolean isMatchTabulation(String s, String p) {
        int m = s.length();
        int n = p.length();
        // Initialization
        boolean[][] dp = new boolean[m + 1][n + 1]; // SC: O(M x N)
        dp[0][0] = true;
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            dp[i][0] = false;
        }
        for (int j = 1; j < n + 1; j++) { // TC: O(N)
            dp[0][j] = isAllStars(p, j); // TC: O(N)
        }
        // Iterative Calls
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                dp[i][j] = false;
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(J)
     * SC: O(1)
     */
    private boolean isAllStars(String p, int j) {
        for (int k = 1; k <= j; k++) {
            if (p.charAt(k - 1) != '*') {
                return false;
            }
        }
        return true;
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
     * Accepted (1811 / 1811 testcases passed)
     */
    public boolean isMatchMemoization(String s, String p) {
        int m = s.length();
        int n = p.length();
        int[][] memo = new int[m + 1][n + 1]; // SC: O(M x N)
        for (int[] mem : memo) { // TC: O(M)
            Arrays.fill(mem, -1); // TC: O(N)
        }
        return solveMemoization(s, p, m - 1, n - 1, memo); // TC: O(M x N), SC: O(M + N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(M x N)
     * SC: O(M + N)
     */
    private boolean solveMemoization(String s, String p, int m, int n, int[][] memo) {
        // Base Case
        if (m < 0 && n < 0) {
            // we exhaused both so there is certainly a Match found in Strings s and p
            return true;
        }
        if (n < 0) {
            // we still have few characters in String s left so we don't have a Match
            return false;
        }
        if (m < 0) {
            // we still have characters in String p left so Match will happen if all
            // characters left is *
            for (int k = 0; k <= n; k++) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }
        // Memoization Check
        if (memo[m][n] != -1) {
            return memo[m][n] == 1;
        }
        // Recursion Calls
        if (s.charAt(m) == p.charAt(n) || p.charAt(n) == '?') {
            // we have a match and we can further check in both Strings shrinking their size
            boolean result = solveMemoization(s, p, m - 1, n - 1, memo);
            memo[m][n] = result ? 1 : 0;
            return result;
        }
        if (p.charAt(n) == '*') {
            /**
             * case 1: we can think as if * denotes nothing i.e. '' and shrink n index
             * case 2: we can think as if * denotes character of any length so we can
             * shrink m keeping n index intact
             */
            boolean result = solveMemoization(s, p, m, n - 1, memo) || solveMemoization(s, p, m - 1, n, memo);
            memo[m][n] = result ? 1 : 0;
            return result;
        }
        // default return
        memo[m][n] = 0;
        return false;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     *
     * - O(M + N) - recursion stack
     *
     * Time Limit Exceeded (1639 / 1811 testcases passed)
     */
    public boolean isMatchRecursion(String s, String p) {
        int m = s.length();
        int n = p.length();
        return solveRecursion(s, p, m - 1, n - 1); // TC: O(2 ^ (M + N)), SC: O(M + N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     */
    private boolean solveRecursion(String s, String p, int m, int n) {
        // Base Case
        if (m < 0 && n < 0) {
            // we exhaused both so there is certainly a Match found in Strings s and p
            return true;
        }
        if (n < 0) {
            // we still have few characters in String s left so we don't have a Match
            return false;
        }
        if (m < 0) {
            // we still have characters in String p left so Match will happen if all
            // characters left is *
            for (int k = 0; k <= n; k++) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }
        // Recursion Calls
        if (s.charAt(m) == p.charAt(n) || p.charAt(n) == '?') {
            // we have a match and we can further check in both Strings shrinking their size
            return solveRecursion(s, p, m - 1, n - 1);
        }
        if (p.charAt(n) == '*') {
            /**
             * case 1: we can think as if * denotes nothing i.e. '' and shrink n index
             * case 2: we can think as if * denotes character of any length so we can
             * shrink m keeping n index intact
             */
            return solveRecursion(s, p, m, n - 1) || solveRecursion(s, p, m - 1, n);
        }
        // default return
        return false;
    }
}
