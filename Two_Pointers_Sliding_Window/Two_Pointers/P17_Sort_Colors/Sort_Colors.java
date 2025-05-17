package Two_Pointers_Sliding_Window.Two_Pointers.P17_Sort_Colors;

import java.util.Arrays;

public class Sort_Colors {
    public static void main(String[] args) {
        Sort_Colors solution = new Sort_Colors();

        int[] nums1 = { 2, 0, 2, 1, 1, 0 };
        solution.sortColorsHashing(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = { 2, 0, 1, 2, 1, 1, 0, 2, 1, 1, 0 };
        solution.sortColorsOptimal(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    /**
     * Approach II : Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public void sortColorsOptimal(int[] nums) {
        int n = nums.length;
        int low = 0;
        int mid = 0;
        int high = n - 1;
        while (mid <= high) { // TC: O(N)
            if (nums[mid] == 0) {
                // if mid pointer is pointing at value 0, swap it with value at low pointer
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // mid pointer for sorted array is likely to be pointing at 1, so increment mid
                mid++;
            } else {
                // if mid pointer is pointing at value 2, swap it with value at high pointer
                swap(nums, high, mid);
                high--;
            }
        }
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    private void swap(int[] nums, int p, int q) {
        int temp = nums[q];
        nums[q] = nums[p];
        nums[p] = temp;
    }

    /**
     * Approach I : Using Hashing Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(3) ~ O(1)
     */
    public void sortColorsHashing(int[] nums) {
        int n = nums.length;
        int[] map = new int[3]; // SC: O(3) ~ O(1)
        for (int i = 0; i < n; i++) { // TC: O(N)
            map[nums[i]]++;
        }
        int index = 0;
        for (int i = 0; i < 3; i++) { // TC: O(3) ~ O(1)
            for (int j = 0; j < map[i]; j++) { // TC: O(N)
                nums[index++] = i;
            }
        }
    }
}
