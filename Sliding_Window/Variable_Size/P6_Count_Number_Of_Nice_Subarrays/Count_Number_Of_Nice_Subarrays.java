package Sliding_Window.Variable_Size.P6_Count_Number_Of_Nice_Subarrays;

public class Count_Number_Of_Nice_Subarrays {
    public static void main(String[] args) {
        int[] nums = { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 };
        int k = 2;
        int countSubArrays = numberOfSubarrays(nums, k);
        System.out.println(countSubArrays);
    }

    /**
     * Took two pointers i and j = 0
     * 
     * Window size: (j - i + 1)
     * 
     * Used a oddCount variable to count the number of odds encountered for index j
     * when nums[j] is odd, then we increase oddCount and reset prevCount to
     * recapture
     * the number of valid sub-arrays across that valid window
     * 
     * Used a prevCount variable to track the previous number of valid sub-arrays
     * 
     * when HashMap had s.charAt(j), we decremented the frequency from HashMap and
     * decremented count if frequency = 0
     * 
     * till oddCount = k, increment the prevCount variable and shrink the window
     * and before incrementing i, decrease oddCount if nums[i] is odd
     * 
     * Finally add prevCount to count and return
     * 
     * TC: O(N)
     * SC: O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    private static int numberOfSubarrays(int[] nums, int k) {
        int count = 0;
        int n = nums.length;
        int i = 0; // pointer at the start of sliding window
        int j = 0; // pointer at the end of sliding window
        int prevCount = 0;
        int oddCount = 0;
        while (j < n) { // TC: O(N)
            if (nums[j] % 2 != 0) {
                // its an odd number
                oddCount++;
                // reset prevCount so that it is re-calculated for a chunk of valid sub-arrays
                prevCount = 0;
            }
            while (oddCount == k) {
                prevCount++;
                // shrinking the window size and removing calculations done with nums[i]
                if (nums[i] % 2 != 0) {
                    oddCount--;
                }
                i++;
            }
            count += prevCount;
            j++;
        }
        return count;
    }
}
