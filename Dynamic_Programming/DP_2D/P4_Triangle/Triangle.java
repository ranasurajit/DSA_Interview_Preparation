package Dynamic_Programming.DP_2D.P4_Triangle;

import java.util.ArrayList;
import java.util.Arrays;

import Arrays.Utils.ArrayUtils;

public class Triangle {
    public static void main(String[] args) {
        Triangle solution = new Triangle();

        int[][] triangleData = { { 2 }, { 3, 4 }, { 6, 5, 7 }, { 4, 1, 8, 3 } };
        ArrayList<ArrayList<Integer>> triangle = ArrayUtils.convert2DArayToArrayList(triangleData);

        int minimumTotalRecursion = solution.minimumTotalRecursion(triangle);
        System.out.println(minimumTotalRecursion);

        int minimumTotalMemoization = solution.minimumTotalMemoization(triangle);
        System.out.println(minimumTotalMemoization);

        int minimumTotalTabulation = solution.minimumTotalTabulation(triangle);
        System.out.println(minimumTotalTabulation);

        int minimumTotalSpaceOptimization = solution.minimumTotalSpaceOptimization(triangle);
        System.out.println(minimumTotalSpaceOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O(N x M)
     * SC: O(N) + O(N)
     * - O(N) - for each next and current memory
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int minimumTotalSpaceOptimization(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();
        // we will start from index 0 till we reach the last row (n - 1)
        int m = triangle.get(n - 1).size();
        // Initialization
        int[] next = new int[m]; // SC: O(N)
        for (int j = 0; j < m; j++) {
            next[j] = triangle.get(n - 1).get(j);
        }
        // Iterative Calls
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            int[] current = new int[m]; // SC: O(N)
            for (int j = 0; j < triangle.get(i).size(); j++) { // TC: O(M)
                current[j] = triangle.get(i).get(j) + Math.min(next[j], next[j + 1]);
            }
            next = current.clone();
        }
        return next[0];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     *
     * TC: O(N x M)
     * SC: O(N x M)
     * - O(N x M) - dp table memory
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int minimumTotalTabulation(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();
        // we will start from index 0 till we reach the last row (n - 1)
        int m = triangle.get(n - 1).size();
        // Initialization
        int[][] dp = new int[n][m]; // SC: O(N x M)
        for (int j = 0; j < m; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }
        // Iterative Calls
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            for (int j = 0; j < triangle.get(i).size(); j++) { // TC: O(M)
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     *
     * TC: O(N x M)
     * SC: O(N x M) + O(N)
     * - O(N x M) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (45 / 45 testcases passed)
     */
    public int minimumTotalMemoization(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();
        // we will start from index 0 till we reach the last row (n - 1)
        int[][] memo = new int[n][triangle.get(n - 1).size()]; // SC: O(N x M)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(0, 0, n - 1, triangle, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveMemoization(int row, int col, int n, ArrayList<ArrayList<Integer>> triangle, int[][] memo) {
        // Base Case
        if (row == n) {
            return triangle.get(row).get(col);
        }
        // Memoization Check
        if (memo[row][col] != -1) {
            return memo[row][col];
        }
        // Recursion Calls
        int sum = triangle.get(row).get(col);
        // from any index we can go down in col -> (col) and (col + 1)
        int downSum = sum + solveMemoization(row + 1, col, n, triangle, memo);
        int diagSum = sum + solveMemoization(row + 1, col + 1, n, triangle, memo);
        return memo[row][col] = Math.min(downSum, diagSum);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) - recursion stack
     *
     * Time Limit Exceeded (42 / 45 testcases passed)
     */
    public int minimumTotalRecursion(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();
        // we will start from index 0 till we reach the last row (n - 1)
        return solveRecursion(0, 0, n - 1, triangle);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int row, int col, int n, ArrayList<ArrayList<Integer>> triangle) {
        // Base Case
        if (row == n) {
            return triangle.get(row).get(col);
        }
        // Recursion Calls
        int sum = triangle.get(row).get(col);
        // from any index we can go down in col -> (col) and (col + 1)
        int downSum = sum + solveRecursion(row + 1, col, n, triangle);
        int diagSum = sum + solveRecursion(row + 1, col + 1, n, triangle);
        return Math.min(downSum, diagSum);
    }
}
