package Hashing.P16_Count_Number_Of_Bad_Pairs;

import java.util.HashMap;
import java.util.Map;

public class Count_Number_Of_Bad_Pairs {
    public static void main(String[] args) {
        Count_Number_Of_Bad_Pairs solution = new Count_Number_Of_Bad_Pairs();

        int[] nums1 = { 1, 2, 3, 4, 5 };
        long badPairs1 = solution.countBadPairsBruteForce(nums1);
        System.out.println(badPairs1);

        int[] nums2 = { 4, 1, 3, 3 };
        long badPairs2 = solution.countBadPairsOptimal(nums2);
        System.out.println(badPairs2);
    }

    /**
     * Approach II : Using Hashing Approach
     * 
     * TC: O(N)
     * SC: O(N)
     *
     * Accepted (65 / 65 testcases passed)
     */
    public long countBadPairsOptimal(int[] nums) {
        int n = nums.length;
        /**
         * we will convert the formula j - i != nums[j] - nums[i] into
         * nums[i] - i != nums[j] - j
         * so we will modify array 'nums'into nums[i] = (nums[i] - i)
         */
        Map<Integer, Long> freqMap = new HashMap<Integer, Long>(); // SC: O(N)
        long badPairs = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int diff = nums[i] - i;
            // good pairs will be how many diff values we encountered to left of index 'i'
            long goodPairs = freqMap.getOrDefault(diff, 0L);
            badPairs += (i - goodPairs);
            freqMap.put(diff, goodPairs + 1);
        }
        return badPairs;
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(N ^ 2)
     * SC: O(1)
     *
     * Time Limit Exceeded (59 / 65 testcases passed)
     */
    public long countBadPairsBruteForce(int[] nums) {
        int n = nums.length;
        long count = 0L;
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                if (j - i != nums[j] - nums[i]) {
                    count++;
                }
            }
        }
        return count;
    }
}
