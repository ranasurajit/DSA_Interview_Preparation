package Binary_Search.Two_Dimensional.P5_Median_In_A_Row_Wise_Sorted_Matrix;

public class Median_In_A_Row_Wise_Sorted_Matrix {
    public static void main(String[] args) {
        Median_In_A_Row_Wise_Sorted_Matrix solution = new Median_In_A_Row_Wise_Sorted_Matrix();

        int[][] mat = { { 1, 3, 5 }, { 2, 6, 9 }, { 3, 6, 9 } };

        int medianValue = solution.median(mat);
        System.out.println(medianValue);
    }

    /**
     * Approach : Using Binary Search On Answers Approach
     * 
     * TC: O(M x log(N) x log(K))
     * SC: O(1)
     * 
     * where K = High - Low
     */
    int median(int mat[][]) {
        int m = mat.length;
        int n = mat[0].length;
        /**
         * since m and n both are odd so number of elements =
         * m x n is always odd so we have only 1 median value
         */
        // we need to calculate low and high from 0th column and (n - 1)th column of
        // matrix respectively
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            low = Math.min(low, mat[i][0]);
            high = Math.max(high, mat[i][n - 1]);
        }
        int elementsCountReqd = (m * n) / 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int countLessThanMid = getCountOfNumbersLessThanMid(mat, m, n, mid);
            if (countLessThanMid <= elementsCountReqd) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * We need to find total count of numbers less than mid
     * so, upper bound values of mid in each rows will
     * give the maximum index at which mid exceeds any element
     * 
     * TC: O(M x log(N))
     * SC: O(1)
     */
    private int getCountOfNumbersLessThanMid(int[][] mat, int m, int n, int mid) {
        int count = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            count += upperBound(mat[i], n, mid); // TC: O(log(N))
        }
        return count;
    }

    /**
     * Using Binary Search for Upper Bound
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int upperBound(int[] arr, int n, int x) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
