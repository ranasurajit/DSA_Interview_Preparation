package Heaps.P16_Minimum_Operations_To_Exceed_Threshold_Value_II;

import java.util.PriorityQueue;

public class Minimum_Operations_To_Exceed_Threshold_Value_II {
    public static void main(String[] args) {
        Minimum_Operations_To_Exceed_Threshold_Value_II solution = new Minimum_Operations_To_Exceed_Threshold_Value_II();

        int[] nums1 = { 2, 11, 10, 1, 3 };
        int k1 = 10;
        int minOperations1 = solution.minOperations(nums1, k1);
        System.out.println(minOperations1);

        int[] nums2 = { 1, 1, 2, 4, 9 };
        int k2 = 20;
        int minOperations2 = solution.minOperations(nums2, k2);
        System.out.println(minOperations2);
    }

    /**
     * Approach : Using Min-Heap (PriorityQueues) Approach
     *
     * TC: O(N x log(N)) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N)
     */
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        // we will be adding elements from array 'nums' in Min-Heap
        PriorityQueue<Long> pq = new PriorityQueue<Long>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            pq.offer((long) nums[i]); // TC: O(log(N))
        }
        int operations = 0;
        long x = 0L;
        long y = 0L;
        while (!pq.isEmpty() && pq.peek() < k) { // TC: O(N)
            if (pq.size() >= 2 && pq.peek() < k) {
                x = pq.poll();
                y = pq.poll();
                pq.offer((2 * x) + y); // // TC: O(log(N))
                operations++;
            } else {
                break;
            }
        }
        return operations;
    }
}
