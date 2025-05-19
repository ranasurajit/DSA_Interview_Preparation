package Binary_Search.One_Dimensional.P16_Single_Element_In_A_Sorted_Array;

public class Single_Element_In_A_Sorted_Array {
    public static void main(String[] args) {
        Single_Element_In_A_Sorted_Array solution = new Single_Element_In_A_Sorted_Array();

        int[] nums = { 3, 3, 7, 7, 10, 11, 11 };
        int singleElement = solution.singleNonDuplicate(nums);
        System.out.println(singleElement);
    }

    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        // handle array of size 1
        if (n == 1) {
            return nums[0];
        }
        // handle extreme ends
        if (nums[0] != nums[1]) {
            return nums[0];
        }
        if (nums[n - 1] != nums[n - 2]) {
            return nums[n - 1];
        }
        // Apply Binary Search on the range nums [1...(n - 2)]
        int low = 1;
        int high = n - 2;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (nums[mid] == nums[mid - 1]) {
                mid--;
            }
            if ((mid & 1) == 0) {
                /**
                 * when mid is pointing to even index there are two possibilities
                 * 1. It is pointing to start of duplicate number
                 * 2. It is pointing to the single element
                 */
                if (nums[mid] != nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                    // this is our single element
                    return nums[mid];
                } else {
                    // as (mid + 1) will point to the duplicate element
                    low = mid + 2;
                }
            } else {
                // mid is pointing to odd index so our single element lies in the left side
                high = mid - 1;
            }
        }
        return -1;
    }
}
