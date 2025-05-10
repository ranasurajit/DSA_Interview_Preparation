package Arrays.SubArrays.P1_Subarray_Sum_Equals_K;

import java.util.HashMap;
import java.util.Map;

public class Subarray_Sum_Equals_K {
    public static void main(String[] args) {
        Subarray_Sum_Equals_K solution = new Subarray_Sum_Equals_K();

        int[] nums = { 3, 9, -2, 4, 1, -7, 2, 6, -5, 8, -3, -7, 6, 2, 1 };
        int k = 5;
        int count = solution.subarraySum(nums, k);
        System.out.println(count);
    }

    /**
     * Approach : Using Hashing Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // Map to store the frequencies of prefix-sum { prefixSum, count }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        map.put(0, 1);
        int prefixSum = 0;
        int count = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            prefixSum += nums[i];
            int reqSum = prefixSum - k;
            if (map.containsKey(reqSum)) {
                count += map.get(reqSum);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
