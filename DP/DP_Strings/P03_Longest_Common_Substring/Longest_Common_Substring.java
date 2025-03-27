package DP.DP_Strings.P03_Longest_Common_Substring;

import java.util.HashMap;

public class Longest_Common_Substring {
    public static void main(String[] args) {
        Longest_Common_Substring solution = new Longest_Common_Substring();

        String s1 = "ABCDGH";
        String s2 = "ACDGHR";

        int maxLengthRecursion = solution.longestCommonSubstrRecursion(s1, s2);
        System.out.println(maxLengthRecursion);

        int maxLengthMemoization = solution.longestCommonSubstrMemoization(s1, s2);
        System.out.println(maxLengthMemoization);

        int maxLengthTabulation = solution.longestCommonSubstrTabulation(s1, s2);
        System.out.println(maxLengthTabulation);

        int maxLengthOptimization = solution.longestCommonSubstrOptimization(s1, s2);
        System.out.println(maxLengthOptimization);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ (M + N))
     * SC: O(M + N)
     * 
     * @param s1
     * @param s2
     * @return
     */
    public int longestCommonSubstrRecursion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        return solveRecursion(s1, s2, m, n, 0);
    }

    private int solveRecursion(String s1, String s2, int m, int n, int maxLength) {
        // Base Case
        if (m == 0 || n == 0) {
            // return the value captured in maxLength
            return maxLength;
        }
        // Recursive Calls
        int include = maxLength;
        int excludeS1 = 0;
        int excludeS2 = 0;
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            include = solveRecursion(s1, s2, m - 1, n - 1, maxLength + 1);
        } else {
            // reset maxLength = 0
            excludeS1 = solveRecursion(s1, s2, m - 1, n, 0);
            excludeS2 = solveRecursion(s1, s2, m, n - 1, 0);
        }
        return Math.max(include, Math.max(excludeS1, excludeS2));
    }

    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(M x N)
     * SC: O((M x N) + (M + N))
     *
     * @param s1
     * @param s2
     * @return
     */
    public int longestCommonSubstrMemoization(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        HashMap<String, Integer> memo = new HashMap<String, Integer>();
        return solveMemoization(s1, s2, m, n, 0, memo);
    }

    private int solveMemoization(String s1, String s2, int m, int n,
            int maxLength, HashMap<String, Integer> memo) {
        // Base Case
        if (m == 0 || n == 0) {
            // return the value captured in maxLength
            return maxLength;
        }
        String key = m + "!!" + n + "@@" + maxLength;
        // Memoization Check
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // Recursive Calls
        int include = maxLength;
        int excludeS1 = 0;
        int excludeS2 = 0;
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            include = solveMemoization(s1, s2, m - 1, n - 1, maxLength + 1, memo);
        } else {
            // reset maxLength = 0
            excludeS1 = solveMemoization(s1, s2, m - 1, n, 0, memo);
            excludeS2 = solveMemoization(s1, s2, m, n - 1, 0, memo);
        }
        int result = Math.max(include, Math.max(excludeS1, excludeS2));
        memo.put(key, result);
        return result;
    }

    /**
     * Approach III : Using Tabulation Approach
     * 
     * TC: O(M x N)
     * SC: O(M x N)
     *
     * @param s1
     * @param s2
     * @return
     */
    public int longestCommonSubstrTabulation(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int maxLength = 0;
        int[][] dp = new int[m + 1][n + 1];
        // no need to initialize 0th row as it is by-default zero
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    // reset value to zero to start a fresh count of maxLength
                    dp[i][j] = 0;
                }
            }
        }
        return maxLength;
    }

    /**
     * Approach IV : Using Space Optimization Approach
     * 
     * TC: O(M x N)
     * SC: O(2 x N) ~ O(N)
     *
     * @param s1
     * @param s2
     * @return
     */
    public int longestCommonSubstrOptimization(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int maxLength = 0;
        int[] current = new int[n + 1];
        int[] previous = new int[n + 1];
        // no need to initialize 0th row as it is by-default zero
        // replace dp[i] with current and dp[i - 1] as previous
        for (int i = 1; i < m + 1; i++) { // TC: O(M)
            for (int j = 1; j < n + 1; j++) { // TC: O(N)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    current[j] = 1 + previous[j - 1];
                    maxLength = Math.max(maxLength, current[j]);
                } else {
                    // reset value to zero to start a fresh count of maxLength
                    current[j] = 0;
                }
            }
            previous = current.clone();
        }
        return maxLength;
    }
}
