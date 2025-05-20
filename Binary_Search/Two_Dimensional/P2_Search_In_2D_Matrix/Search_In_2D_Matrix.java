package Binary_Search.Two_Dimensional.P2_Search_In_2D_Matrix;

public class Search_In_2D_Matrix {
    public static void main(String[] args) {
        Search_In_2D_Matrix solution = new Search_In_2D_Matrix();

        int[][] matrix1 = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };
        int target1 = 3;

        boolean isFound1 = solution.searchMatrixApproachI(matrix1, target1);
        System.out.println(isFound1);

        int[][] matrix2 = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };
        int target2 = 12;

        boolean isFound2 = solution.searchMatrixApproachI(matrix2, target2);
        System.out.println(isFound2);
    }

    /**
     * Approach II : Using Binary Search On Cells Approach
     *
     * TC: TC: O(log(M x N))
     * SC: O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0;
        int high = m * n - 1;
        while (low <= high) { // TC: O(log(M x N))
            int mid = low + (high - low) / 2;
            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                high = mid - 1;
            }
            if (matrix[row][col] < target) {
                low = mid + 1;
            }
        }
        return false;
    }

    /**
     * Approach I : Using Binary Search On Rows Approach
     *
     * TC: O(M x log(N))
     * SC: O(1)
     */
    public boolean searchMatrixApproachI(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) { // TC: O(M)
            if (target >= matrix[i][0] && target <= matrix[i][n - 1] &&
                    searchElement(matrix[i], n, target)) { // TC: O(log(N))
                return true;
            }
        }
        return false;
    }

    /**
     * Using Binary Search Approach on Row
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    private boolean searchElement(int[] row, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
}
