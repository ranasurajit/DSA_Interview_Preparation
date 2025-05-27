package Hashing.P20_Contains_Duplicate_II;

import java.util.HashMap;
import java.util.Map;

public class Contains_Duplicate_II {
    public static void main(String[] args) {
        Contains_Duplicate_II solution = new Contains_Duplicate_II();

        int[] nums1 = { 1, 2, 3, 1 };
        int k1 = 3;
        boolean containsDuplicate1 = solution.containsNearbyDuplicate(nums1, k1);
        System.out.println(containsDuplicate1);

        int[] nums2 = { 1, 2, 3, 1, 2, 3 };
        int k2 = 2;
        boolean containsDuplicate2 = solution.containsNearbyDuplicate(nums2, k2);
        System.out.println(containsDuplicate2);
    }

    /**
     * Approach : Using Hashing Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        // we will store {nums[i], i} in the HashMap
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (map.containsKey(nums[i])) {
                int diff = Math.abs(map.get(nums[i]) - i);
                if (diff <= k) {
                    return true;
                } else {
                    /**
                     * if diff > k, then we will override the index with a
                     * hope that we will encounter the same value as nums[i]
                     * with an index - currentIndex <= k
                     */
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }
}
