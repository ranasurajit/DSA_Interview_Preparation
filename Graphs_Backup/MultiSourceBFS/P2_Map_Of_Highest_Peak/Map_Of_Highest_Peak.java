package Graphs_Backup.MultiSourceBFS.P2_Map_Of_Highest_Peak;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Map_Of_Highest_Peak {
    public static void main(String[] args) {
        Map_Of_Highest_Peak solution = new Map_Of_Highest_Peak();

        int[][] isWater = { { 0, 0, 1 }, { 1, 0, 0 }, { 0, 0, 0 } };
        int[][] peakMatrix = solution.highestPeak(isWater);
        System.out.println(Arrays.deepToString(peakMatrix));
    }

    /**
     * Using Multi-Source BFS Approach
     *
     * TC: O(2 x M x N) ~ O(M x N)
     * SC: O(2 x M x N) ~ O(M x N)
     */
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] heights = new int[m][n];
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        // using a queue to initiate multi-source bfs approach
        Queue<int[]> queue = new LinkedList<int[]>(); // SC: O(M x N)
        boolean[][] visited = new boolean[m][n]; // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                heights[i][j] = isWater[i][j] == 1 ? 0 : 1;
                if (isWater[i][j] == 1) {
                    queue.offer(new int[] { heights[i][j], i, j });
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) { // TC: O(M x N)
            int[] current = queue.poll();
            int value = current[0];
            int row = current[1];
            int col = current[2];
            for (int[] direction : directions) {
                int effRow = row + direction[0];
                int effCol = col + direction[1];
                // validate if cell (effRow, effCol) is within bounds and unvisited
                if (effRow >= 0 && effRow < m && effCol >= 0 && effCol < n &&
                        !visited[effRow][effCol]) {
                    visited[effRow][effCol] = true; // marking cell visited
                    heights[effRow][effCol] = value + 1;
                    queue.offer(new int[] { value + 1, effRow, effCol });
                }
            }
        }
        return heights;
    }
}
