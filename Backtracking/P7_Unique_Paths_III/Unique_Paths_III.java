package Backtracking.P7_Unique_Paths_III;

public class Unique_Paths_III {
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private int m;
    private int n;
    private int nonObstacleCells = 0;
    private int result = 0;

    public static void main(String[] args) {
        Unique_Paths_III solution = new Unique_Paths_III();

        int[][] grid = { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 2, -1 } };
        int countPaths = solution.uniquePathsIII(grid);
        System.out.println(countPaths);
    }

    /**
     * Approach : Using Recursion + Backtracking Approach
     *
     * TC: O(3 ^ (M x N) + (M x N)) ~ O(3 ^ (M x N))
     * SC: O(M x N)
     */
    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int x = -1;
        int y = -1;
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (grid[i][j] == 0) {
                    nonObstacleCells++;
                } else if (grid[i][j] == 1) {
                    nonObstacleCells++;
                    x = i;
                    y = j;
                }
            }
        }
        int[] count = { 0 };
        solveBacktrack(x, y, grid, count); // TC: O(3 ^ (M x N)), SC: O(M x N)
        return result;
    }

    /**
     * Using Recursion + Backtracking Approach
     *
     * TC: O(3 ^ (M x N))
     * SC: O(M x N)
     */
    private void solveBacktrack(int x, int y, int[][] grid, int[] count) {
        // Base Case
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == -1) {
            return;
        }
        if (grid[x][y] == 2) {
            if (count[0] == nonObstacleCells) {
                result++;
            }
            return;
        }
        // Recursion Calls
        // modify
        int temp = grid[x][y];
        grid[x][y] = -1;
        count[0]++;
        // explore
        for (int[] dir : directions) {
            int effX = x + dir[0];
            int effY = y + dir[1];
            solveBacktrack(effX, effY, grid, count);
        }
        // backtrack
        grid[x][y] = temp;
        count[0]--;
    }
}
