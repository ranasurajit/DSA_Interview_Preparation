package DP.DP_Grids.P2_Unique_Paths;

import java.util.Arrays;

public class Unique_Paths {
    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        int pathRecursion = uniquePathsRecursive(m, n);
        System.out.println(pathRecursion);

        int pathMemoization = uniquePathsMemoize(m, n);
        System.out.println(pathMemoization);

        int pathTabulation = uniquePathsTabulation(m, n);
        System.out.println(pathTabulation);

        int pathOptimize = uniquePathsSpaceOptimization(m, n);
        System.out.println(pathOptimize);
    }

    public static int uniquePathsRecursive(int m, int n) {
        return uniquePathsRecursion(m - 1, n - 1);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ (M x N))
     * SC: O((M - 1) + (N - 1))
     * 
     * @param m
     * @param n
     * @return
     */
    public static int uniquePathsRecursion(int m, int n) {
        if (m == 0 && n == 0) {
            return 1; // reached destination
        }
        if (m < 0 || n < 0) {
            return 0; // reached out of bounds of grid so returned 0
        }
        int upPaths = uniquePathsRecursion(m - 1, n);
        int leftPaths = uniquePathsRecursion(m, n - 1);
        return upPaths + leftPaths;
    }

    public static int uniquePathsMemoize(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] dp1D : dp) {
            Arrays.fill(dp1D, -1);
        }
        return uniquePathsMemoization(m - 1, n - 1, dp);
    }

    /**
     * Using Memoization
     * 
     * TC: O(M x N)
     * SC: O((M - 1) + (N - 1) + (M x N))
     * 
     * @param m
     * @param n
     * @return
     */
    public static int uniquePathsMemoization(int m, int n, int[][] dp) {
        if (m == 0 && n == 0) {
            return 1; // reached destination
        }
        if (m < 0 || n < 0) {
            return 0; // reached out of bounds of grid so returned 0
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        int upPaths = uniquePathsMemoization(m - 1, n, dp);
        int leftPaths = uniquePathsMemoization(m, n - 1, dp);
        dp[m][n] = upPaths + leftPaths;
        return dp[m][n];
    }

    /**
     * Using Tabulation
     * 
     * TC: O(M x N)
     * SC: O(M x N)
     * 
     * @param m
     * @param n
     * @return
     */
    public static int uniquePathsTabulation(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int left = 0;
                    int up = 0;
                    if (i > 0) {
                        up = dp[i - 1][j];
                    }
                    if (j > 0) {
                        left = dp[i][j - 1];
                    }
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * Using Space Optimization
     * 
     * TC: O(M x N)
     * SC: O(2N) ~ O(N)
     * 
     * @param m
     * @param n
     * @return
     */
    public static int uniquePathsSpaceOptimization(int m, int n) {
        int[] prev = new int[n]; // SC: O(N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            int[] current = new int[n]; // SC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (i == 0 && j == 0) {
                    current[j] = 1;
                } else {
                    int left = 0;
                    int up = 0;
                    if (i > 0) {
                        up = prev[j];
                    }
                    if (j > 0) {
                        left = current[j - 1];
                    }
                    current[j] = up + left;
                }
            }
            prev = current;
        }
        return prev[n - 1];
    }
}
