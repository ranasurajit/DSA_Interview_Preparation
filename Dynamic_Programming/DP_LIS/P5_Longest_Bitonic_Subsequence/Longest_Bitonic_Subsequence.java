package Dynamic_Programming.DP_LIS.P5_Longest_Bitonic_Subsequence;

import java.util.Arrays;

public class Longest_Bitonic_Subsequence {
    public static void main(String[] args) {
        Longest_Bitonic_Subsequence solution = new Longest_Bitonic_Subsequence();

        int n = 8;
        int[] nums = { 1, 11, 2, 10, 4, 5, 2, 1 };
        int lbs = solution.longestBitonicSequence(n, nums);
        System.out.println(lbs);
    }

    /**
     * Approach : Using Tabulation (Bottom-Up DP) + Optimized Approach
     * 
     * TC: O(N x N) + O(N x N) + O(N) ~ O(N x N)
     * SC: O(N) + O(N)
     * 
     * - O(N) - dpLeft and dpRight numsay memory
     *
     * Accepted (55 / 55 testcases passed)
     */
    public int longestBitonicSequence(int n, int[] nums) {
        // we have to pre-compute LIS from left
        int[] dpLeft = new int[n];
        Arrays.fill(dpLeft, 1);
        for (int i = 1; i < n; i++) { // TC: O(N)
            for (int prevIdx = 0; prevIdx < i; prevIdx++) { // TC: O(N)
                if (nums[i] > nums[prevIdx] && dpLeft[prevIdx] + 1 > dpLeft[i]) {
                    dpLeft[i] = dpLeft[prevIdx] + 1;
                }
            }
        }
        // we have to pre-compute LIS from right
        int[] dpRight = new int[n];
        Arrays.fill(dpRight, 1);
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            for (int prevIdx = n - 1; prevIdx > i; prevIdx--) { // TC: O(N)
                if (nums[i] > nums[prevIdx] && dpRight[prevIdx] + 1 > dpRight[i]) {
                    dpRight[i] = dpRight[prevIdx] + 1;
                }
            }
        }
        // so now we have pre-computed LIS from left and right so we can now compute LBS
        // using both
        int maxLength = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            // 1 is subtracted as it is common in both left and right LIS
            if (dpLeft[i] > 1 && dpRight[i] > 1) {
                maxLength = Math.max(maxLength, dpLeft[i] + dpRight[i] - 1);
            }
        }
        return maxLength;
    }
}
