package Backtracking.P6_Word_Search;

public class Word_Search {
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private int m;
    private int n;

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
     * Approach : Using Recursion + Backtracking Approach
     *
     * TC: O(M x N x 3 ^ L)
     * SC: O(L)
     *
     * where L = Length(word)
     */
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (board[i][j] == word.charAt(0) && solveBacktrack(i, j, 0, board, word)) { // TC: O(3 ^ L)
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Using Recursion + Backtracking Approach
     *
     * TC: O(3 ^ L)
     * SC: O(L)
     *
     * where L = Length(word)
     */
    private boolean solveBacktrack(int x, int y, int index, char[][] board, String word) {
        // Base Case
        if (index == word.length()) {
            // we found the word in board
            return true;
        }
        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != word.charAt(index)) {
            // invalid cases
            return false;
        }
        // Recursion Calls
        char temp = board[x][y];
        board[x][y] = '$'; // marking it as visited
        // explore
        for (int[] dir : directions) {
            int effX = x + dir[0];
            int effY = y + dir[1];
            if (solveBacktrack(effX, effY, index + 1, board, word)) {
                return true;
            }
        }
        // backtrack
        board[x][y] = temp;
        return false;
    }
}
