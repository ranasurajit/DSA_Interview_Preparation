package Two_Pointers_Sliding_Window.Two_Pointers.P3_Remove_Duplicates_From_Sorted_Array;

public class Remove_Duplicates_From_Sorted_Array {
    public static void main(String[] args) {
        Remove_Duplicates_From_Sorted_Array solution = new Remove_Duplicates_From_Sorted_Array();

        int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        int uniques = solution.removeDuplicates(nums);
        System.out.println(uniques);
    }

    /**
     * Approach : Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int p = 0; // points to unique element
        int q = 1; // finds the unique element
        while (q < n) { // TC: O(N)
            if (nums[p] != nums[q]) {
                nums[p + 1] = nums[q];
                p++;
                q++;
            } else {
                q++;
            }
        }
        return p + 1;
    }
}
