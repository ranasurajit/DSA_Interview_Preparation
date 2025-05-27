package Backtracking_Backup.P1_Word_Search;

public class Word_Search {
    public static void main(String[] args) {
        Word_Search solution = new Word_Search();

        char[][] board = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word = "ABCCED";

        boolean doesWordExist = solution.exist(board, word);
        System.out.println(doesWordExist);
    }

    /**
     * Using Recursion and Backtracking Approach
     * 
     * TC: O(M x N x 3 ^ L)
     * SC: O(L)
     * 
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (board[i][j] == word.charAt(0) &&
                        findWord(0, i, j, board, word, m, n)) { // TC: O(3 ^ L)
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * TC: O(3 ^ L)
     * SC: O(L)
     * 
     * @param index
     * @param i
     * @param j
     * @param board
     * @param word
     * @param m
     * @param n
     * @return
     */
    private boolean findWord(int index, int i, int j, char[][] board,
            String word, int m, int n) {
        // if all characters in word matches and reached end then return true
        if (index == word.length()) {
            return true;
        }
        // validate the grid cell coordinates if out of bounds or match with String word
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(index)) {
            return false;
        }
        // store the current grid character to backtrack later
        char temp = board[i][j];
        // marking it as visited till it's neighbouring cells are explored
        board[i][j] = '$';

        // exploring the cell's neighbouring cells in all 4 directions
        // Left, Right, Bottom, Top
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int[] direction : directions) { // TC: O(4)
            int effRow = i + direction[0];
            int effCol = j + direction[1];
            if (findWord(index + 1, effRow, effCol, board, word, m, n)) {
                return true;
            }
        }
        // backtrack to unvisit the current cell for further exploration
        board[i][j] = temp;
        return false;
    }
}
