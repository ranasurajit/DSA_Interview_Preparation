package Binary_Search.Two_Dimentional_Arrays.P1_Row_With_Max_Ones;

public class Row_With_Max_Ones {
    public static void main(String[] args) {
        Row_With_Max_Ones solution = new Row_With_Max_Ones();
        int[][] arr = { { 0, 1, 1, 1 }, { 0, 0, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } };
        int rowMax1s = solution.rowWithMax1s(arr);
        System.out.println(rowMax1s);
    }

    /**
     * Using Binary Search Approach
     * 
     * TC: O(M x log(N))
     * SC: O(1)
     * 
     * @param arr
     * @return
     */
    public int rowWithMax1s(int arr[][]) {
        int maxIndex = -1;
        int m = arr.length;
        int n = arr[0].length;
        int currentCount = 0;
        int maxCount = 0;
        for (int i = 0; i < m; i++) { // TC: O(M)
            int lBound = lowerBound(arr[i], n, 1); // TC: O(log(N))
            if (lBound == n) {
                continue;
            }
            int uBound = upperBound(arr[i], n, 1); // TC: O(log(N))
            currentCount = uBound - lBound;
            if (maxCount < currentCount) {
                maxCount = currentCount;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param row
     * @param n
     * @param x
     * @return
     */
    private int lowerBound(int[] row, int n, int x) {
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (row[mid] >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param row
     * @param n
     * @param x
     * @return
     */
    private int upperBound(int[] row, int n, int x) {
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (row[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
