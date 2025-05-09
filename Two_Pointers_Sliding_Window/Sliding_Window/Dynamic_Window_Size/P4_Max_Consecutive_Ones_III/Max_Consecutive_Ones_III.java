package Two_Pointers_Sliding_Window.Sliding_Window.Dynamic_Window_Size.P4_Max_Consecutive_Ones_III;

public class Max_Consecutive_Ones_III {
    public static void main(String[] args) {
        Max_Consecutive_Ones_III solution = new Max_Consecutive_Ones_III();

        int[] nums = { 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 };
        int k = 3;
        int maxOnes = solution.longestOnes(nums, k);
        System.out.println(maxOnes);
    }

    /**
     * Approach : Using Sliding Window (Variable Length) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int longestOnes(int[] nums, int k) {
        /**
         * This problem can be transformed into a problem where
         * we need to find maximum length of sub-array with
         * at-most k zeros
         */
        int n = nums.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int zerocount = 0;
        int maxLength = 0;
        while (j < n) { // TC: O(N)
            zerocount += nums[j] == 0 ? 1 : 0;
            while (zerocount > k) {
                // remove computation from index 'i'
                if (nums[i] == 0) {
                    zerocount--;
                }
                i++;
            }
            if (zerocount <= k) {
                maxLength = Math.max(maxLength, j - i + 1);
            }
            j++;
        }
        return maxLength;
    }
}
