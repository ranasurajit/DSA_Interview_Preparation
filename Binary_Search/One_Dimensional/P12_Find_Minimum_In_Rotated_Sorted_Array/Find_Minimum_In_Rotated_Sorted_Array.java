package Binary_Search.One_Dimensional.P12_Find_Minimum_In_Rotated_Sorted_Array;

public class Find_Minimum_In_Rotated_Sorted_Array {
    public static void main(String[] args) {
        Find_Minimum_In_Rotated_Sorted_Array solution = new Find_Minimum_In_Rotated_Sorted_Array();

        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int minimumValue = solution.findMin(nums);
        System.out.println(minimumValue);
    }

    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int minIndex = getMinIndex(nums, n); // TC: O(log(N))
        return nums[minIndex];
    }

    /**
     * Using Binary Search Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int getMinIndex(int[] nums, int n) {
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            if (nums[low] <= nums[high]) {
                // array 'nums' is sorted and 0 times rotated
                return low;
            }
            int mid = low + (high - low) / 2;
            int prev = (mid - 1 + n) % n;
            int next = (mid + 1) % n;
            if (nums[mid] <= nums[prev] && nums[mid] <= nums[next]) {
                // minimum value found
                return mid;
            } else if (nums[mid] >= nums[low]) {
                // left part is sorted so, minimum value lies in right portion
                low = mid + 1;
            } else if (nums[mid] <= nums[high]) {
                // right part is sorted so, minimum value lies in left portion
                high = mid - 1;
            }
        }
        return 0;
    }
}
