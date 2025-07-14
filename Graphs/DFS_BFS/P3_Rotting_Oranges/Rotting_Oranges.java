package Graphs.DFS_BFS.P3_Rotting_Oranges;

import java.util.LinkedList;
import java.util.Queue;

public class Rotting_Oranges {

    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) {
        Rotting_Oranges solution = new Rotting_Oranges();

        int[][] grid1 = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
        int time1 = solution.orangesRotting(grid1);
        System.out.println(time1);

        int[][] grid2 = { { 2, 1, 1 }, { 0, 1, 1 }, { 1, 0, 1 } };
        int time2 = solution.orangesRotting(grid2);
        System.out.println(time2);
    }

    /**
     * Approach: Using Multi-Source BFS Approach
     *
     * TC: O(M x N) + O(M x N) ~ O(M x N)
     * - One full traversal of grid to count oranges.
     * - Each cell is visited at most once during BFS.
     *
     * SC: O(M x N)
     * - BFS queue can hold all oranges in worst-case.
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int freshOranges = 0;
        Queue<Pair> queue = new LinkedList<Pair>(); // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (grid[i][j] == 1) {
                    freshOranges++;
                } else if (grid[i][j] == 2) {
                    // rotten oranges - push them to queue
                    queue.offer(new Pair(i, j, 0));
                }
            }
        }
        if (freshOranges == 0) {
            // no fresh oranges found
            return 0;
        }
        if (queue.isEmpty() && freshOranges > 0) {
            // no rotten oranges found
            return -1;
        }
        // using Multi-source BFS approach
        int maxTime = 0;
        while (!queue.isEmpty()) { // TC: O(M x N)
            Pair pair = queue.poll();
            int row = pair.row;
            int col = pair.col;
            int time = pair.time;
            maxTime = Math.max(maxTime, time);
            for (int[] dir : directions) { // TC: O(4)
                int effRow = row + dir[0];
                int effCol = col + dir[1];
                if (effRow >= 0 && effRow < m && effCol >= 0 && effCol < n &&
                        grid[effRow][effCol] == 1) {
                    grid[effRow][effCol] = 2;
                    freshOranges--;
                    queue.offer(new Pair(effRow, effCol, time + 1));
                }
            }
        }
        if (freshOranges != 0) {
            return -1;
        }
        return maxTime;
    }

    private static class Pair {
        int row;
        int col;
        int time;

        public Pair(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
