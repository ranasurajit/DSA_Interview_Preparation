package BitManipulation.Medium.P3_Single_Number_III;

import java.util.Arrays;

public class Single_Number_III {
    public static void main(String[] args) {
        Single_Number_III solution = new Single_Number_III();

        int[] nums1 = { 1, 2, 1, 3, 2, 5 };
        int[] singleNumbers1 = solution.singleNumber(nums1);
        System.out.println(Arrays.toString(singleNumbers1));

        int[] nums2 = { 0, -1 };
        int[] singleNumbers2 = solution.singleNumber(nums2);
        System.out.println(Arrays.toString(singleNumbers2));
    }

    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(1)
     */
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) { // TC: O(N)
            xor ^= num;
        }
        /**
         * xor will be having xor of two unique numbers, now we need
         * to separate them out using buckets and since they are unique
         * so they would be having atleast 1 bit difference, so we
         * need to find the right most set bit
         */
        int rightMostSetBit = (xor & -xor);
        int bucket1 = 0;
        int bucket2 = 0;
        for (int num : nums) { // TC: O(N)
            if ((num & rightMostSetBit) != 0) {
                bucket1 ^= num;
            } else {
                bucket2 ^= num;
            }
        }
        return new int[] { bucket1, bucket2 };
    }
}
