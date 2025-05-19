package Binary_Search.One_Dimensional.P14_Search_In_An_Almost_Sorted_Array;

public class Search_In_An_Almost_Sorted_Array {
    public static void main(String[] args) {
        Search_In_An_Almost_Sorted_Array solution = new Search_In_An_Almost_Sorted_Array();

        int[] arr1 = { 10, 3, 40, 20, 50, 80, 70 };
        int target1 = 40;

        int targetIndex1 = solution.findTarget(arr1, target1);
        System.out.println(targetIndex1);

        int[] arr2 = { 10, 3, 40, 20, 50, 80, 70 };
        int target2 = 90;

        int targetIndex2 = solution.findTarget(arr2, target2);
        System.out.println(targetIndex2);
    }

    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    public int findTarget(int arr[], int target) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            // in below three condition we check target with (mid, (mid - 1) and (mid + 1))
            // index
            if (arr[mid] == target) {
                return mid;
            } else if (mid > low && arr[mid - 1] == target) {
                return mid - 1;
            } else if (mid < high && arr[mid + 1] == target) {
                return mid + 1;
            } else if (arr[mid] > target) {
                // range to consider till (mid - 1) instead of mid so high = (mid - 1) - 1
                high = mid - 2;
            } else {
                // range to consider till (mid + 1) instead of mid so high = (mid + 1) + 1
                low = mid + 2;
            }
        }
        return -1;
    }
}
