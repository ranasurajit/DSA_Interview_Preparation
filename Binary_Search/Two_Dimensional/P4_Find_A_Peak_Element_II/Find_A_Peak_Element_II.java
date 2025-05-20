package Binary_Search.Two_Dimensional.P4_Find_A_Peak_Element_II;

import java.util.Arrays;

public class Find_A_Peak_Element_II {
    public static void main(String[] args) {
        Find_A_Peak_Element_II solution = new Find_A_Peak_Element_II();

        int[][] mat = { { 10, 20, 15 }, { 21, 30, 14 }, { 7, 16, 32 } };

        int[] peakCoordinate = solution.findPeakGrid(mat);
        System.out.println(Arrays.toString(peakCoordinate));
    }

    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(N x log(M))
     * SC: O(1)
     */
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            int rowIdx = findMaxInColumnGrid(mat, m, mid); // TC: O(M)
            // now the problem is reduced to 1-D array at row index 'rowIdx' to find peak
            // validate for mid - 1 and mid + 1 column indices
            int leftValue = mid - 1 >= 0 ? mat[rowIdx][mid - 1] : -1;
            int rightValue = mid + 1 < n ? mat[rowIdx][mid + 1] : -1;
            if (mat[rowIdx][mid] > leftValue && mat[rowIdx][mid] > rightValue) {
                // got a peak
                return new int[] { rowIdx, mid };
            } else if (mat[rowIdx][mid] < leftValue) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[] { -1, -1 };
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(M)
     * SC: O(1)
     */
    private int findMaxInColumnGrid(int[][] mat, int m, int colIdx) {
        int maxIdx = -1;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            if (maxValue < mat[i][colIdx]) {
                maxValue = mat[i][colIdx];
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}
