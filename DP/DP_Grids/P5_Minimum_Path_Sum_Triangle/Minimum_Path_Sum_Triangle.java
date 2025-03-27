package DP.DP_Grids.P5_Minimum_Path_Sum_Triangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Minimum_Path_Sum_Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        List<Integer> row1 = Arrays.asList(2);
        List<Integer> row2 = Arrays.asList(3, 4);
        List<Integer> row3 = Arrays.asList(6, 5, 7);
        List<Integer> row4 = Arrays.asList(4, 1, 8, 3);
        triangle.add(row1);
        triangle.add(row2);
        triangle.add(row3);
        triangle.add(row4);

        int minSumRecursion = minimumTotalRecursion(triangle);
        System.out.println(minSumRecursion);

        int minSumMemoization = minimumTotalMemoization(triangle);
        System.out.println(minSumMemoization);

        int minSumTabulation = minimumTotalTabulation(triangle);
        System.out.println(minSumTabulation);

        int minSumOptimization = minimumTotalSpaceOptimization(triangle);
        System.out.println(minSumOptimization);
    }

    /**
     * Using Recursion
     * 
     * @param triangle
     * @return
     */
    public static int minimumTotalRecursion(List<List<Integer>> triangle) {
        return solveRecursion(0, 0, triangle);
    }

    private static int solveRecursion(int m, int n, List<List<Integer>> triangle) {
        if (m == triangle.size()) {
            return 0;
        }
        int down = triangle.get(m).get(n) + solveRecursion(m + 1, n, triangle);
        int diag = triangle.get(m).get(n) + solveRecursion(m + 1, n + 1, triangle);
        return Math.min(down, diag);
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x N)
     * SC: O(N x N + N)
     * 
     * @param triangle
     * @return
     */
    public static int minimumTotalMemoization(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n + 1]; // SC: O(N x N)
        for (int[] dp1D : dp) { // TC: O(N x N)
            Arrays.fill(dp1D, -1);
        }
        return solveMemoization(0, 0, triangle, dp);
    }

    private static int solveMemoization(int m, int n, List<List<Integer>> triangle, int[][] dp) {
        if (m == triangle.size()) {
            return 0;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        int down = triangle.get(m).get(n) + solveMemoization(m + 1, n, triangle, dp);
        int diag = triangle.get(m).get(n) + solveMemoization(m + 1, n + 1, triangle, dp);
        dp[m][n] = Math.min(down, diag);
        return dp[m][n];
    }

    /**
     * Using Tabulation
     * 
     * TC: O(N x N + N) ~ O(N x N)
     * SC: O(N x N)
     * 
     * @param triangle
     * @return
     */
    public static int minimumTotalTabulation(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n + 1]; // SC: O(N x N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            dp[n - 1][i] = triangle.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            for (int j = i; j >= 0; j--) { // TC: O(N)
                int down = triangle.get(i).get(j) + dp[i + 1][j];
                int diag = triangle.get(i).get(j) + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, diag);
            }
        }
        return dp[0][0];
    }

    /**
     * Using Space Optimization
     * 
     * TC: O(N x N + N) ~ O(N x N)
     * SC: O(2N) ~ O(N)
     * 
     * @param triangle
     * @return
     */
    public static int minimumTotalSpaceOptimization(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] front = new int[n]; // SC: O(N)
        int[] current = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            front[i] = triangle.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            for (int j = i; j >= 0; j--) { // TC: O(N)
                int down = triangle.get(i).get(j) + front[j];
                int diag = triangle.get(i).get(j) + front[j + 1];
                current[j] = Math.min(down, diag);
            }
            front = current.clone();
        }
        return front[0];
    }
}
