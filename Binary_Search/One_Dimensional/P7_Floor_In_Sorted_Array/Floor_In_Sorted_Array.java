package Binary_Search.One_Dimensional.P7_Floor_In_Sorted_Array;

public class Floor_In_Sorted_Array {
    public static void main(String[] args) {
        Floor_In_Sorted_Array solution = new Floor_In_Sorted_Array();

        int[] arr = { 1, 2, 8, 10, 10, 12, 19 };
        int x = 5;

        int floorIndex = solution.findFloor(arr, x);
        System.out.println(floorIndex);
    }

    /**
     * Approach : Using Binary Search Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    private int findFloor(int[] arr, int x) {
        return upperBound(arr, x) - 1;
    }

    /**
     * Using Binary Search Approach to find Upper Bound
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    private int upperBound(int[] arr, int target) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
