package Backtracking.P7_Solve_The_Sudoku;

import java.util.Arrays;

public class Solve_The_Sudoku {
    public static void main(String[] args) {
        Solve_The_Sudoku solution = new Solve_The_Sudoku();

        int[][] mat = {
                { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
        };
        solution.solveSudoku(mat);
        System.out.println(Arrays.deepToString(mat));
    }

    // Function to find a solved Sudoku.
    /**
     * Using Recursion and Backtracking
     * 
     * TC: O(1)
     * SC: O(1)
     * 
     * @param mat
     */
    private void solveSudoku(int[][] mat) {
        solve(mat);
    }

    /**
     * TC: O(27) ~ O(1)
     * SC: O(1)
     * 
     * @param matrix
     * @return
     */
    private boolean solve(int[][] matrix) {
        for (int i = 0; i < 9; i++) { // TC: O(9)
            for (int j = 0; j < 9; j++) { // TC: O(9)
                if (matrix[i][j] == 0) {
                    // we need to fill numbers from 1-9 so try all possibilities
                    for (int num = 1; num <= 9; num++) { // TC: O(9)
                        if (isValidSudoku(matrix, i, j, num)) {
                            // set the value
                            matrix[i][j] = num;
                            // explore all other possibilities
                            if (solve(matrix)) { // TC: O(1), SC: O(1)
                                return true;
                            }
                            // backtrack
                            matrix[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * TC: O(27) ~ O(1)
     * SC: O(1)
     * 
     * @param matrix
     * @param i
     * @param j
     * @param num
     * @return
     */
    private boolean isValidSudoku(int[][] matrix, int i, int j, int num) {
        // validate rows
        for (int col = 0; col < 9; col++) { // TC: O(9)
            if (matrix[i][col] == num) {
                return false;
            }
        }
        // validate columns
        for (int row = 0; row < 9; row++) { // TC: O(9)
            if (matrix[row][j] == num) {
                return false;
            }
        }
        // validate 3 x 3 boxes
        // find start (srow, scol) of 3 x 3 box
        int srow = i - i % 3;
        int scol = j - j % 3;
        for (int row = 0; row < 3; row++) { // TC: O(3)
            for (int col = 0; col < 3; col++) { // TC: O(3)
                if (matrix[srow + row][scol + col] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
