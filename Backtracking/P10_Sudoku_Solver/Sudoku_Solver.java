package Backtracking.P10_Sudoku_Solver;

import java.util.Arrays;

public class Sudoku_Solver {
    public static void main(String[] args) {
        Sudoku_Solver solution = new Sudoku_Solver();

        char[][] board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        solution.solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }

    /**
     * Approach : Using Recursion + Backtracking Approach
     *
     * TC: O(9 ^ M) as each cell has 9 options to be filled and M = count(empty
     * cells) <<< 81
     * SC: O(M)
     */
    public void solveSudoku(char[][] board) {
        solveBacktrack(board);
    }

    /**
     * Using Recursion + Backtracking Approach
     *
     * TC: O(9 ^ M) as each cell has 9 options to be filled and M = count(empty
     * cells) <<< 81
     * SC: O(M)
     */
    private boolean solveBacktrack(char[][] board) {
        for (int i = 0; i < 9; i++) { // TC: O(9)
            for (int j = 0; j < 9; j++) { // TC: O(9)
                if (board[i][j] == '.') {
                    // here at this cell (i, j) we need to find all possibilities by choosing 1 to 9
                    for (char ch = '1'; ch <= '9'; ch++) { // TC: O(9)
                        if (isValidBoard(board, i, j, ch)) { // TC: O(9)
                            board[i][j] = ch;
                            if (solveBacktrack(board)) {
                                return true;
                            } else {
                                // backtrack
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(9)
     * SC: O(1)
     */
    private boolean isValidBoard(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            // validate in same row
            if (board[row][i] == ch) {
                return false;
            }
            // validate in same column
            if (board[i][col] == ch) {
                return false;
            }
            // validate in 3 x 3 grid
            int effRow = 3 * (row / 3) + (i / 3);
            int effCol = 3 * (col / 3) + (i % 3);
            if (board[effRow][effCol] == ch) {
                return false;
            }
        }
        return true;
    }
}
