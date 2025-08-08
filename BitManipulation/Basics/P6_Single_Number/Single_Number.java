package BitManipulation.Basics.P6_Single_Number;

public class Single_Number {
    public static void main(String[] args) {
        Single_Number solution = new Single_Number();

        int[] nums1 = { 2, 2, 1 };
        int singleNumber1 = solution.singleNumber(nums1);
        System.out.println(singleNumber1);

        int[] nums2 = { 4, 1, 2, 1, 2 };
        int singleNumber2 = solution.singleNumber(nums2);
        System.out.println(singleNumber2);
    }

    /**
     * Approach : Using Bit-Manipulation (Brute-Force) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int singleNumber(int[] nums) {
        /**
         * we know that if we do XOR of same numbers we cancel it to zero
         */
        int xor = 0;
        for (int num : nums) { // TC: O(N)
            xor = xor ^ num;
        }
        return xor;
    }
}
