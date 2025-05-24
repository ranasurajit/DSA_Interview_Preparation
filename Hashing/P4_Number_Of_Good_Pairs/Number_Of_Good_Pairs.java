package Hashing.P4_Number_Of_Good_Pairs;

import java.util.HashMap;
import java.util.Map;

public class Number_Of_Good_Pairs {
    public static void main(String[] args) {
        Number_Of_Good_Pairs solution = new Number_Of_Good_Pairs();

        int[] nums1 = { 1, 2, 3, 1, 1, 3 };
        int pairs1 = solution.numIdenticalPairsApproachI(nums1);
        System.out.println(pairs1);

        int[] nums2 = { 1, 1, 1, 1 };
        int pairs2 = solution.numIdenticalPairsApproachII(nums2);
        System.out.println(pairs2);
    }

    /**
     * Approach II : Using Hashing Approach (Single Pass Approach)
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int numIdenticalPairsApproachII(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(N)
        int count = 0;
        for (int num : nums) { // TC: O(N)
            count += freqMap.getOrDefault(num, 0);
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        return count;
    }

    /**
     * Approach I : Using Hashing Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public int numIdenticalPairsApproachI(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int num : nums) { // TC: O(N)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int count = 0;
        for (Integer key : freqMap.keySet()) { // TC: O(N)
            int freq = freqMap.get(key);
            if (freq > 1) {
                count += (freq * (freq - 1)) / 2; // pairs = nC2 = (n * (n - 1)) / 2
            }
        }
        return count;
    }
}
