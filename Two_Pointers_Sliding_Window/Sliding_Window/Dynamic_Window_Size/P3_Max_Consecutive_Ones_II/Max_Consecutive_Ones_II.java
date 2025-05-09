package Two_Pointers_Sliding_Window.Sliding_Window.Dynamic_Window_Size.P3_Max_Consecutive_Ones_II;

public class Max_Consecutive_Ones_II {
    public static void main(String[] args) {
        Max_Consecutive_Ones_II solution = new Max_Consecutive_Ones_II();

        int[] nums = { 1, 0, 1, 1, 0 };
        int maxOnes = solution.findMaxConsecutiveOnesWithZeroFlip(nums);
        System.out.println(maxOnes);
    }

    /**
     * Approach : Using Sliding Window (Variable Length) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int findMaxConsecutiveOnesWithZeroFlip(int[] nums) {
        int n = nums.length;
        /**
         * This problem can be transformed into a problem where
         * we need to find maximum length of sub-array with
         * at-most 1 zero
         */
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int zeroes = 0;
        int maxLength = 0;
        while (j < n) { // TC: O(N)
            if (nums[j] == 0) {
                zeroes++;
            }
            while (zeroes > 1) {
                // remove computation of index 'i'
                if (nums[i] == 0) {
                    zeroes--;
                }
                // shrink the window
                i++;
            }
            if (zeroes <= 1) {
                maxLength = Math.max(maxLength, j - i + 1);
            }
            j++;
        }
        return maxLength;
    }
}
