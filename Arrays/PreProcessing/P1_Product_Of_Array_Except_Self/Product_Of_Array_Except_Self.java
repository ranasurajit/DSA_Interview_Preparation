package Arrays.PreProcessing.P1_Product_Of_Array_Except_Self;

import java.util.Arrays;

public class Product_Of_Array_Except_Self {
    public static void main(String[] args) {
        Product_Of_Array_Except_Self solution = new Product_Of_Array_Except_Self();

        int[] nums = { -1, 1, 0, -3, 3 };
        int[] result = solution.productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Using Array Pre-processing technique
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // calculating suffixProduct from right to left
        int[] suffix = new int[n]; // SC: O(N)
        suffix[n - 1] = nums[n - 1] * 1;
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            suffix[i] = suffix[i + 1] * nums[i];
        }
        /**
         * now we will not create the prefixProduct to save
         * space complexity we will calculate on the fly
         * iterating from left to right
         */
        int prefix = 1;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i < n - 1) {
                res[i] = prefix * suffix[i + 1];
            } else {
                res[i] = prefix * 1;
            }
            // preparing for next iteration
            prefix = prefix * nums[i];
        }
        return res;
    }
}
