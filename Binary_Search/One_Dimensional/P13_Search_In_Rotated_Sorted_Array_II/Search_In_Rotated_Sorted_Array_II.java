package Binary_Search.One_Dimensional.P13_Search_In_Rotated_Sorted_Array_II;

public class Search_In_Rotated_Sorted_Array_II {
    public static void main(String[] args) {
        Search_In_Rotated_Sorted_Array_II solution = new Search_In_Rotated_Sorted_Array_II();

        int[] nums1 = { 1, 0, 1, 1, 1 };
        int target1 = 0;

        boolean isFound1 = solution.search(nums1, target1);
        System.out.println(isFound1);

        int[] nums2 = { 2, 5, 6, 0, 0, 1, 2 };
        int target2 = 0;

        boolean isFound2 = solution.search(nums2, target2);
        System.out.println(isFound2);

        int[] nums3 = { 2, 5, 6, 0, 0, 1, 2 };
        int target3 = 3;

        boolean isFound3 = solution.search(nums3, target3);
        System.out.println(isFound3);
    }

    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            // eliminating duplicates at left
            while (low < high && nums[low] == nums[low + 1]) {
                low++;
            }
            // eliminating duplicates at right
            while (low < high && nums[high] == nums[high - 1]) {
                high--;
            }
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] >= nums[low]) {
                // left part is sorted
                if (target >= nums[low] && target <= nums[mid]) {
                    // target lies in left
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] <= nums[high]) {
                // right part is sorted
                if (target >= nums[mid] && target <= nums[high]) {
                    // target lies in right
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }
}
