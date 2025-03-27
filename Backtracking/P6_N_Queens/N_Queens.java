package Backtracking.P6_N_Queens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class N_Queens {

    private Set<Integer> colSet = new HashSet<Integer>();
    private Set<Integer> diagSet = new HashSet<Integer>();
    private Set<Integer> antiDiagSet = new HashSet<Integer>();

    public static void main(String[] args) {
        N_Queens solution = new N_Queens();

        int n = 4;
        List<List<String>> solvedBoard = solution.solveNQueens(n);
        System.out.println(solvedBoard);
    }

    /**
     * Using Recursion and Backtracking Approach
     *
     * TC: O(N! + N ^ 2) ~ O(N!)
     * SC: O(N + N ^ 2) ~ O(N ^ 2)
     */
    public List<List<String>> solveNQueens(int n) {
        // create the board
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) { // TC: O(N)
                row.append(".");
            }
            board.add(row.toString());
        }
        List<List<String>> result = new ArrayList<List<String>>();
        solve(board, 0, result, n);
        return result;
    }

    /**
     * Using Recursion and Backtracking Approach
     *
     * TC: O(N!)
     * SC: O(N)
     */
    private void solve(List<String> board, int row, List<List<String>> result, int n) {
        if (row >= n) {
            result.add(new ArrayList<String>(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            int colId = col;
            int diagId = row + col;
            int antiDiagId = row - col;
            // check if Queen will be attacked at (row, col)
            if (colSet.contains(colId) || diagSet.contains(diagId) ||
                    antiDiagSet.contains(antiDiagId)) {
                continue;
            }
            // post this, Queen will not be attacked at (row, col)
            // locking the cell for Queen
            colSet.add(colId);
            diagSet.add(diagId);
            antiDiagSet.add(antiDiagId);
            // set the value as 'Q'
            StringBuilder modifiedRow = new StringBuilder(board.get(row));
            modifiedRow.setCharAt(col, 'Q');
            board.set(row, modifiedRow.toString());

            // explore
            solve(board, row + 1, result, n);

            // backtrack
            // remove locks for the cell for Queen
            colSet.remove(colId);
            diagSet.remove(diagId);
            antiDiagSet.remove(antiDiagId);
            modifiedRow.setCharAt(col, '.');
            board.set(row, modifiedRow.toString());
        }
    }
}
