package Graphs.Traversals_BFS_DFS.P2_Maximum_Number_Of_Fish_In_A_Grid;

import java.util.LinkedList;
import java.util.Queue;

public class Maximum_Number_Of_Fish_In_A_Grid {

    private int m;
    private int n;
    private int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public static void main(String[] args) {
        Maximum_Number_Of_Fish_In_A_Grid solution = new Maximum_Number_Of_Fish_In_A_Grid();

        int[][] gridDFS = { { 0, 2, 1, 0 }, { 4, 0, 0, 3 }, { 1, 0, 0, 4 }, { 0, 3, 2, 0 } };
        int maxFishDFS = solution.findMaxFishDFS(gridDFS);
        System.out.println(maxFishDFS);

        int[][] gridBFS = { { 0, 2, 1, 0 }, { 4, 0, 0, 3 }, { 1, 0, 0, 4 }, { 0, 3, 2, 0 } };
        int maxFishBFS = solution.findMaxFishBFS(gridBFS);
        System.out.println(maxFishBFS);
    }

    /**
     * Using DFS Approach
     *
     * TC: O(2 x M x N) ~ O(M x N)
     * SC: O(M x N)
     * 
     * @param grid
     * @return
     */
    public int findMaxFishDFS(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int maxFish = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] > 0) {
                    int[] currentFish = { 0 };
                    dfsGraph(i, j, grid, visited, currentFish, m, n);
                    maxFish = Math.max(maxFish, currentFish[0]);
                }
            }
        }
        return maxFish;
    }

    /**
     * Using DFS Approach
     *
     * TC: O(M x N)
     * SC: O(M x N)
     * 
     * @param row
     * @param col
     * @param grid
     * @param visited
     * @param currentFish
     * @param m
     * @param n
     */
    private void dfsGraph(int row, int col, int[][] grid, boolean[][] visited,
            int[] currentFish, int m, int n) {
        // validate row and col and if unvisited
        if (row < 0 || row >= m || col < 0 || col >= n ||
                visited[row][col] || grid[row][col] == 0) {
            return;
        }
        visited[row][col] = true;
        currentFish[0] += grid[row][col];
        // navigate to all 4 directions
        dfsGraph(row, col + 1, grid, visited, currentFish, m, n); // right
        dfsGraph(row, col - 1, grid, visited, currentFish, m, n); // left
        dfsGraph(row + 1, col, grid, visited, currentFish, m, n); // down
        dfsGraph(row - 1, col, grid, visited, currentFish, m, n); // up
    }

    /**
     * Using BFS Approach
     *
     * TC: O(2 x M x N) ~ O(M x N)
     * SC: O(M x N)
     * 
     * @param grid
     * @return
     */
    public int findMaxFishBFS(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int maxFish = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (!visited[i][j] && grid[i][j] > 0) {
                    maxFish = Math.max(maxFish, bfsGraph(i, j, grid, visited));
                }
            }
        }
        return maxFish;
    }

    /**
     * Using BFS Approach
     *
     * TC: O(M x N)
     * SC: O(M x N)
     * 
     * @param row
     * @param col
     * @param grid
     * @param visited
     * @return
     */
    private int bfsGraph(int row, int col, int[][] grid, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] { row, col });
        visited[row][col] = true;
        // counting the fishes encountered in cell(row, col)
        int fishCount = grid[row][col];
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int _row = current[0];
            int _col = current[1];
            for (int[] direction : directions) {
                int effRow = _row + direction[0];
                int effCol = _col + direction[1];
                // validating cells (effRow, effCol) and if unvisited and water cell
                if (effRow >= 0 && effRow < m && effCol >= 0 && effCol < n &&
                        !visited[effRow][effCol] && grid[effRow][effCol] > 0) {
                    visited[effRow][effCol] = true;
                    // adding current cell (effRow, effCol) value to fishCount
                    fishCount += grid[effRow][effCol];
                    queue.offer(new int[] { effRow, effCol });
                }
            }
        }
        return fishCount;
    }
}
