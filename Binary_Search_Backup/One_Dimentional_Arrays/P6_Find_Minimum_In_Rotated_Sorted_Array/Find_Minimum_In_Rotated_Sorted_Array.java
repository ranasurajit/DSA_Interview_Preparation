package Binary_Search_Backup.One_Dimentional_Arrays.P6_Find_Minimum_In_Rotated_Sorted_Array;

public class Find_Minimum_In_Rotated_Sorted_Array {
    public static void main(String[] args) {
        Find_Minimum_In_Rotated_Sorted_Array solution = new Find_Minimum_In_Rotated_Sorted_Array();

        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int min = solution.findMin(nums);
        System.out.println(min);
    }

    /**
     * Using Binary Search
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low < high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
