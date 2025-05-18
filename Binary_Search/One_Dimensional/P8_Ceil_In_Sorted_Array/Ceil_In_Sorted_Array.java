package Binary_Search.One_Dimensional.P8_Ceil_In_Sorted_Array;

public class Ceil_In_Sorted_Array {
    public static void main(String[] args) {
        Ceil_In_Sorted_Array solution = new Ceil_In_Sorted_Array();

        int[] arr = { 1, 2, 8, 10, 11, 12, 19 };
        int x = 5;

        int ceilIndex = solution.findCeil(arr, x);
        System.out.println(ceilIndex);
    }

    /**
     * Approach : Using Binary Search Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public int findCeil(int[] arr, int x) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        int ceil = Integer.MAX_VALUE;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                ceil = Math.min(ceil, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceil == Integer.MAX_VALUE ? -1 : ceil;
    }
}
