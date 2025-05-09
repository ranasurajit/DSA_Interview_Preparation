package Two_Pointers_Sliding_Window.Sliding_Window.Dynamic_Window_Size.P2_Max_Consecutive_Ones;

public class Max_Consecutive_Ones {
    public static void main(String[] args) {
        Max_Consecutive_Ones solution = new Max_Consecutive_Ones();

        int[] nums = { 1, 1, 0, 1, 1, 1 };
        int maxOnes = solution.findMaxConsecutiveOnes(nums);
        System.out.println(maxOnes);
    }

    /**
     * Approach : Using Sliding Window (Variable Length) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int zeroes = 0;
        int maxLength = 0;
        while (j < n) { // TC: O(N)
            if (nums[j] == 0) {
                zeroes++;
            }
            while (zeroes > 0) {
                // remove computation of index 'i'
                if (nums[i] == 0) {
                    zeroes--;
                }
                // shrink the window
                i++;
            }
            if (zeroes == 0) {
                maxLength = Math.max(maxLength, j - i + 1);
            }
            j++;
        }
        return maxLength;
    }
}
