package Sliding_Window.Variable_Size.P1_Longest_Subarray_With_Sum_K_And_Positives;

public class P1_Longest_Subarray_With_Sum_K_And_Positives {
    public static void main(String[] args) {
        int k = 3;
        int[] a = { 1, 2, 3, 1, 1, 1, 1 };
        int maxLength = longestSubarrayWithSumK(a, k);
        System.out.println(maxLength);
    }

    /**
     * Took two pointers i and j = 0 and increment j till sum reaches 'k'
     * 
     * Window size: (j - i + 1)
     * 
     * when sum < k, j++
     * when sum = k, compare and store the maximum window size (result), j++
     * till sum > k, we run a while loop and remove arr[i] from sum incrementing i,
     * j++
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public static int longestSubarrayWithSumK(int[] a, long k) {
        int n = a.length;
        int i = 0; // pointer at start of sliding window
        int j = 0; // pointer at end of sliding window
        long sum = 0L;
        int maxLength = Integer.MIN_VALUE;
        while (j < n) { // TC: O(N)
            sum += a[j];
            while (sum > k) {
                sum = sum - a[i];
                i++;
            }
            if (sum == k) {
                // comparing and storing maximum window size (current window size = j - i + 1)
                maxLength = Math.max(maxLength, j - i + 1);
            }
            j++;
        }
        return maxLength;
    }
}
