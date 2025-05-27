package Backtracking_Backup.P2_Unique_Paths_III;

public class Unique_Paths_III {
    private int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    private int m = 0;
    private int n = 0;
    private int emptyCells = 0;
    private int result = 0;

    public static void main(String[] args) {
        Unique_Paths_III solution = new Unique_Paths_III();

        int[][] grid = { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 2, -1 } };
        int countPaths = solution.uniquePathsIII(grid);
        System.out.println(countPaths);
    }

    /**
     * TC: O((M x N) + 4 ^ (M x N))
     * SC: O(M x N)
     * 
     * @param grid
     * @return
     */
    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int startX = 0;
        int startY = 0;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                    emptyCells++;
                } else if (grid[i][j] == 0) {
                    emptyCells++;
                }
            }
        }

        int[] count = { 0 };
        backtrack(startX, startY, grid, visited, count);
        return result;
    }

    /**
     * TC: O(4 ^ (M x N))
     * SC: O(M x N)
     * 
     * @param i
     * @param j
     * @param grid
     * @param visited
     * @param count
     */
    private void backtrack(int i, int j, int[][] grid, boolean[][] visited, int[] count) {
        // grid cell validation for out of bound coordinates or if visited already
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] == -1) {
            return;
        }
        if (grid[i][j] == 2) {
            if (count[0] == emptyCells) {
                result++;
            }
            return;
        }
        // marking cell (i, j) as visited
        visited[i][j] = true;

        // exploring the neighbours in all 4 directions
        for (int[] direction : directions) { // TC: O(4)
            int effRow = i + direction[0];
            int effCol = j + direction[1];
            backtrack(effRow, effCol, grid, visited, new int[] { count[0] + 1 });
        }
        // marking cell (i, j) as unvisited
        visited[i][j] = false;
    }
}
