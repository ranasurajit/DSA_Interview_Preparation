package Two_Pointers.P2_Remove_Duplicates_From_Sorted_Array_InPlace;

import java.util.Arrays;

public class Remove_Duplicates_From_Sorted_Array_InPlace {
    public static void main(String[] args) {
        int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        int uniques = removeDuplicates(nums);

        System.out.println(Arrays.toString(nums));
        System.out.println(uniques);
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0; // pointer at 0th index of nums
        int j = 1; // pointer at 1st index of nums
        while (j < n) { // TC: O(N)
            // we will have only index 'i' pointing to uniques
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
            j++;
        }
        return i + 1; // number of unique elements
    }
}
