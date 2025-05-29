package Backtracking.P4_Rat_In_A_Maze_Problem_I;

import java.util.ArrayList;
import java.util.Collections;

public class Rat_In_A_Maze_Problem_I {
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private static final char[] directionValues = { 'D', 'U', 'R', 'L' };

    public static void main(String[] args) {
        Rat_In_A_Maze_Problem_I solution = new Rat_In_A_Maze_Problem_I();

        int[][] mat = {
                { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 }
        };
        ArrayList<String> paths = solution.ratInMaze(mat);
        System.out.println(paths);
    }

    /**
     * Approach : Using Recursion + Backtracking Approach
     * 
     * TC: O(4 ^ (N x N) + (N x N)) ~ O(4 ^ (N x N))
     * SC: O(2 x N x N) ~ O(N x N)
     */
    public ArrayList<String> ratInMaze(int[][] maze) {
        int n = maze.length;
        if (maze[0][0] == 0) {
            return new ArrayList<String>();
        }
        ArrayList<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder(); // SC: (N x N)
        maze[0][0] = 0; // marking the start coordinate as visited
        solveBacktrack(maze, n, 0, 0, sb, result); // TC: O(4 ^ (N x N)), SC: (N x N)
        // as we need to sort the result in lexicographically smallest order
        Collections.sort(result); // TC: O(N x N x log(N))
        return new ArrayList<String>(result);
    }

    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(4 ^ (N x N))
     * SC: O(N x N)
     */
    private void solveBacktrack(int[][] maze, int n, int x, int y, StringBuilder sb,
            ArrayList<String> result) {
        // Base Case
        if (x == n - 1 && y == n - 1) {
            // rat found the exit
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        for (int i = 0; i < directions.length; i++) { // TC: O(4)
            int[] dir = directions[i];
            int effX = x + dir[0];
            int effY = y + dir[1];
            // validate if it is in bounds and non-zero
            if (effX < 0 || effX >= n || effY < 0 || effY >= n || maze[effX][effY] == 0) {
                // invalid move
                continue;
            }
            int temp = maze[effX][effY];
            // modify
            maze[effX][effY] = 0; // marking the current coordinate as visited
            sb.append(directionValues[i]);
            // explore
            solveBacktrack(maze, n, effX, effY, sb, result);
            // backtrack
            maze[effX][effY] = temp;
            sb.setLength(sb.length() - 1);
        }
    }
}
