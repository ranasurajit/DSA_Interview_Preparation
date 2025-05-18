package Binary_Search_Backup.One_Dimentional_Arrays.P1_Binary_Search;

public class Binary_Search {
    public static void main(String[] args) {
        int[] nums = { -1, 0, 3, 5, 9, 12 };
        int target = 9;
        int index = search(nums, target);
        System.out.println(index);
    }

    /**
     * TC: O(log(N) base 2)
     * SC: O(1)
     * 
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N) base 2)
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
