package Binary_Search.Two_Dimensional.P1_Row_With_Max_1s;

import java.util.ArrayList;

import Arrays.Utils.ArrayUtils;

public class Row_With_Max_1s {
    public static void main(String[] args) {
        Row_With_Max_1s solution = new Row_With_Max_1s();

        int[][] mat = {
                { 1, 1, 1 },
                { 0, 0, 1 },
                { 0, 0, 0 }
        };

        int n = 3;
        int m = 3;
        ArrayList<ArrayList<Integer>> matrix = ArrayUtils.convert2DArayToArrayList(mat);
        int rowIndexWithMax1s = solution.maximumOnesRow(matrix, n, m);
        System.out.println(rowIndexWithMax1s);
    }

    /**
     * Approach : Using Binary Search Approach
     *
     * TC: O(N x log(M))
     * SC: O(1)
     */
    public int maximumOnesRow(ArrayList<ArrayList<Integer>> matrix, int n, int m) {
        int max1s = 0;
        int rowIndex = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            /**
             * to find row with maximum 1's we need to find the
             * upperbound and return row index having least
             * upper bound value of 0
             */
            int count1s = m - upperbound(matrix.get(i), m, 0); // TC: O(log(M))
            if (max1s < count1s) {
                max1s = count1s;
                rowIndex = i;
            }
        }
        return rowIndex;
    }

    /**
     * Find Upper Bound in row such that row.get(i) > 0
     *
     * TC: O(log(M))
     * SC: O(1)
     */
    private int upperbound(ArrayList<Integer> row, int m, int x) {
        int low = 0;
        int high = m - 1;
        while (low <= high) { // TC: O(log(M))
            int mid = low + (high - low) / 2;
            if (row.get(mid) > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
