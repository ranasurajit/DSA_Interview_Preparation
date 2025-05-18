package Binary_Search.One_Dimensional.P6_Search_Insert_Position;

public class Search_Insert_Position {
    public static void main(String[] args) {
        Search_Insert_Position solution = new Search_Insert_Position();

        int[] nums = { 1, 3, 5, 6 };
        int target = 2;

        int position = solution.searchInsert(nums, target);
        System.out.println(position);
    }

    /**
     * Approach : Using Binary Search Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public int searchInsert(int[] nums, int target) {
        return lowerBound(nums, target);
    }

    /**
     * Using Binary Search Approach to Find Lower Bound
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    int lowerBound(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
