package Binary_Search_Backup.One_Dimentional_Arrays.P7_Search_Insert_Position;

public class Search_Insert_Position {
    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 6 };
        int target = 2;

        Search_Insert_Position solution = new Search_Insert_Position();

        int position = solution.searchInsert(nums, target);
        System.out.println(position);
    }

    /**
     * Using Binary Search Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    private int searchInsert(int[] nums, int target) {
        return lowerbound(nums, target);
    }

    /**
     * Lower bound => nums[i] >= x
     * 
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param nums
     * @param target
     * @return
     */
    private int lowerbound(int[] nums, int target) {
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
