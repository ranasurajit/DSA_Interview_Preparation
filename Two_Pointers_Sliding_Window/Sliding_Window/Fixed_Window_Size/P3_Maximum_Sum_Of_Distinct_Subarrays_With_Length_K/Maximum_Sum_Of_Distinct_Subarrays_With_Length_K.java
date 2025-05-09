package Two_Pointers_Sliding_Window.Sliding_Window.Fixed_Window_Size.P3_Maximum_Sum_Of_Distinct_Subarrays_With_Length_K;

import java.util.HashMap;
import java.util.Map;

public class Maximum_Sum_Of_Distinct_Subarrays_With_Length_K {
    public static void main(String[] args) {
        Maximum_Sum_Of_Distinct_Subarrays_With_Length_K solution = new Maximum_Sum_Of_Distinct_Subarrays_With_Length_K();

        int[] nums = { 1, 5, 4, 2, 9, 9, 9 };
        int k = 3;
        long maxSum = solution.maximumSubarraySum(nums, k);
        System.out.println(maxSum);
    }

    /**
     * Approach : Using Sliding Window (Fixed Length) Approach
     *
     * TC: O(N)
     * SC: O(K)
     */
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(K)
        long maxSum = 0L;
        long sum = 0L;
        while (j < n) { // TC: O(N)
            sum += nums[j];
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                if (map.size() == k) {
                    maxSum = Math.max(maxSum, sum);
                }
                // remove computation from index 'i' to slide to next window
                sum -= nums[i];
                int freq = map.get(nums[i]);
                if (freq == 1) {
                    map.remove(nums[i]);
                } else {
                    map.put(nums[i], map.get(nums[i]) - 1);
                }
                // slide to next window
                i++;
                j++;
            }
        }
        return maxSum;
    }
}
