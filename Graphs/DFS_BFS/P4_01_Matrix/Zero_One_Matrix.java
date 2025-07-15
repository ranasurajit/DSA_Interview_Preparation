package Graphs.DFS_BFS.P4_01_Matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Zero_One_Matrix {

    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) {
        Zero_One_Matrix solution = new Zero_One_Matrix();

        int[][] mat = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
        int[][] distance = solution.updateMatrix(mat);
        System.out.println(Arrays.deepToString(distance));
    }

    /**
     * Approach : Using BFS Approach (BFS approach as we need nearest/shortest
     * distance)
     *
     * TC: O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(M x N)
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] result = new int[m][n];
        // we will be storing { row, col, distance } in the Queue
        Queue<int[]> queue = new LinkedList<int[]>(); // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (mat[i][j] == 0) {
                    queue.offer(new int[] { i, j, 0 });
                    result[i][j] = 0;
                } else {
                    result[i][j] = -1;
                }
            }
        }
        while (!queue.isEmpty()) { // TC: O(M x N) as all cells are visited exactly once
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];
            for (int[] dir : directions) { // TC: O(4)
                int effRow = row + dir[0];
                int effCol = col + dir[1];
                if (effRow >= 0 && effRow < m && effCol >= 0 && effCol < n &&
                        result[effRow][effCol] == -1) {
                    // result[effRow][effCol] == -1 means it is not yet visited
                    result[effRow][effCol] = mat[effRow][effCol] == 0 ? 0 : dist + 1;
                    queue.offer(new int[] { effRow, effCol, dist + 1 });
                }
            }
        }
        return result;
    }
}
