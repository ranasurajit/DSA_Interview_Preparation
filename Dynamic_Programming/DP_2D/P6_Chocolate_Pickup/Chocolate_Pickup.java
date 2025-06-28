package Dynamic_Programming.DP_2D.P6_Chocolate_Pickup;

import java.util.Arrays;

public class Chocolate_Pickup {
    public static void main(String[] args) {
        Chocolate_Pickup solution = new Chocolate_Pickup();

        int r = 3;
        int c = 4;
        int[][] grid = {
                { 2, 3, 1, 2 },
                { 3, 4, 2, 2 },
                { 5, 6, 3, 5 }
        };

        int maximumChocolatesRecursion = solution.maximumChocolatesRecursion(r, c, grid);
        System.out.println(maximumChocolatesRecursion);

        int maximumChocolatesMemoization = solution.maximumChocolatesMemoization(r, c, grid);
        System.out.println(maximumChocolatesMemoization);

        int maximumChocolatesTabulation = solution.maximumChocolatesTabulation(r, c, grid);
        System.out.println(maximumChocolatesTabulation);

        int maximumChocolatesSpaceOptimization = solution.maximumChocolatesSpaceOptimization(r, c, grid);
        System.out.println(maximumChocolatesSpaceOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O(C x C) + O(9 x R x C x C) ~ O(R x C x C)
     * SC: O(C x C) + O(C x C)
     * - O(C x C) - dp optimized memory
     *
     * Accepted (11 / 11 testcases passed)
     */
    public int maximumChocolatesSpaceOptimization(int r, int c, int[][] grid) {
        int[][] next = new int[c][c];
        // Initialization - in last row of dp
        for (int j1 = 0; j1 < c; j1++) {
            for (int j2 = 0; j2 < c; j2++) {
                if (j1 == j2) {
                    /**
                     * both Alice and Bob reached the same cell at
                     * the destination row, so we need to consider
                     * the cell's value only once
                     */
                    next[j1][j2] = grid[r - 1][j1];
                } else {
                    next[j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
                }
            }
        }
        int[][] current = new int[c][c];
        // Iterative Calls
        for (int i = r - 2; i >= 0; i--) { // TC: O(R)
            for (int j1 = 0; j1 < c; j1++) { // TC: O(C)
                for (int j2 = 0; j2 < c; j2++) { // TC: O(C)
                    int maxSum = (int) -1e9;
                    for (int a = -1; a <= 1; a++) { // TC: O(3) - Alice's movements
                        for (int b = -1; b <= 1; b++) { // TC: O(3) - Bob's movements
                            int currentSum = 0;
                            if (j1 == j2) {
                                currentSum += grid[i][j1];
                            } else {
                                currentSum += grid[i][j1] + grid[i][j2];
                            }
                            if (j1 + a < 0 || j1 + a >= c || j2 + b < 0 || j2 + b >= c) {
                                currentSum = (int) -1e9;
                            } else {
                                currentSum += next[j1 + a][j2 + b];
                            }
                            maxSum = Math.max(maxSum, currentSum);
                        }
                    }
                    current[j1][j2] = maxSum;
                }
            }
            for (int p = 0; p < c; p++) { // TC: O(C)
                next[p] = current[p].clone();
            }
        }
        return next[0][c - 1];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     *
     * TC: O(C x C) + O(9 x R x C x C) ~ O(R x C x C)
     * SC: O(R x C x C)
     * - O(R x C x C) - dp memory
     *
     * Accepted (11 / 11 testcases passed)
     */
    public int maximumChocolatesTabulation(int r, int c, int[][] grid) {
        int[][][] dp = new int[r][c][c]; // SC: O(R x C x C)
        // Initialization - in last row of dp
        for (int j1 = 0; j1 < c; j1++) { // TC: O(C)
            for (int j2 = 0; j2 < c; j2++) { // TC: O(C)
                if (j1 == j2) {
                    /**
                     * both Alice and Bob reached the same cell at
                     * the destination row, so we need to consider
                     * the cell's value only once
                     */
                    dp[r - 1][j1][j2] = grid[r - 1][j1];
                } else {
                    dp[r - 1][j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
                }
            }
        }
        // Iterative Calls - start row from (r - 2) to 0
        for (int i = r - 2; i >= 0; i--) { // TC: O(R)
            for (int j1 = 0; j1 < c; j1++) { // TC: O(C)
                for (int j2 = 0; j2 < c; j2++) { // TC: O(C)
                    int maxSum = (int) -1e9;
                    for (int a = -1; a <= 1; a++) { // TC: O(3) - Alice's movements
                        for (int b = -1; b <= 1; b++) { // TC: O(3) - Bob's movements
                            int currentSum = 0;
                            if (j1 == j2) {
                                // add grid[i][j1] or grid[i][j2]
                                currentSum += grid[i][j1];
                            } else {
                                currentSum += grid[i][j1] + grid[i][j2];
                            }
                            if (j1 + a < 0 || j1 + a >= c || j2 + b < 0 || j2 + b >= c) {
                                currentSum = (int) -1e9;
                            } else {
                                currentSum += dp[i + 1][j1 + a][j2 + b];
                            }
                            maxSum = Math.max(maxSum, currentSum);
                        }
                    }
                    dp[i][j1][j2] = maxSum;
                }
            }
        }
        return dp[0][0][c - 1];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     *
     * TC: O(R x C x C)
     * SC: O(R x C x C) + O(R)
     * - O(R x C x C) - memoization memory
     * - O(R) - recursion stack
     *
     * Accepted (11 / 11 testcases passed)
     */
    public int maximumChocolatesMemoization(int r, int c, int[][] grid) {
        int[][][] memo = new int[r][c][c]; // SC: O(R x C x C)
        for (int[][] mem : memo) {
            for (int[] m : mem) {
                Arrays.fill(m, -1);
            }
        }
        return solveMemoization(0, 0, c - 1, r, c, grid, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(R x C x C)
     * SC: O(R)
     */
    private int solveMemoization(int i, int j1, int j2,
            int r, int c, int[][] grid, int[][][] memo) {
        // Base Case
        if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) {
            // Out of bounds
            return (int) -1e9;
        }
        if (i == r - 1) {
            if (j1 == j2) {
                /**
                 * both Alice and Bob reached the same cell at
                 * the destination row, so we need to consider
                 * the cell's value only once
                 */
                return grid[i][j1];
            } else {
                // sum up chocolates collected by Alice at (i, j1) and Bob (i, j2)
                return grid[i][j1] + grid[i][j2];
            }
        }
        // Memoization Check
        if (memo[i][j1][j2] != -1) {
            return memo[i][j1][j2];
        }
        // Recursion Calls
        int maxSum = (int) -1e9;
        for (int a = -1; a <= 1; a++) { // TC: O(3) - Alice's movements
            for (int b = -1; b <= 1; b++) { // TC: O(3) - Bob's movements
                int currentSum = 0;
                if (j1 == j2) {
                    // add grid[i][j1] or grid[i][j2]
                    currentSum += grid[i][j1];
                } else {
                    currentSum += grid[i][j1] + grid[i][j2];
                }
                currentSum += solveMemoization(i + 1, j1 + a, j2 + b, r, c, grid, memo);
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return memo[i][j1][j2] = maxSum;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(3 ^ R) x O(3 ^ R)
     * SC: O(R)
     *
     * Time Limit Exceeded (8 / 11 testcases passed)
     */
    public int maximumChocolatesRecursion(int r, int c, int[][] grid) {
        return solveRecursion(0, 0, c - 1, r, c, grid);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(3 ^ R) x O(3 ^ R)
     * SC: O(R)
     */
    private int solveRecursion(int i, int j1, int j2,
            int r, int c, int[][] grid) {
        // Base Case
        if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) {
            // Out of bounds
            return (int) -1e9;
        }
        if (i == r - 1) {
            if (j1 == j2) {
                /**
                 * both Alice and Bob reached the same cell at
                 * the destination row, so we need to consider
                 * the cell's value only once
                 */
                return grid[i][j1];
            } else {
                // sum up chocolates collected by Alice at (i, j1) and Bob (i, j2)
                return grid[i][j1] + grid[i][j2];
            }
        }
        // Recursion Calls
        int maxSum = (int) -1e9;
        for (int a = -1; a <= 1; a++) { // TC: O(3) - Alice's movements
            for (int b = -1; b <= 1; b++) { // TC: O(3) - Bob's movements
                int currentSum = 0;
                if (j1 == j2) {
                    // add grid[i][j1] or grid[i][j2]
                    currentSum += grid[i][j1];
                } else {
                    currentSum += grid[i][j1] + grid[i][j2];
                }
                currentSum += solveRecursion(i + 1, j1 + a, j2 + b, r, c, grid);
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }
}
