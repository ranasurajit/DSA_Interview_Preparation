package Heaps.P8_Make_Array_Zero_By_Subtracting_Equal_Amounts;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Make_Array_Zero_By_Subtracting_Equal_Amounts {
    public static void main(String[] args) {
        Make_Array_Zero_By_Subtracting_Equal_Amounts solution = new Make_Array_Zero_By_Subtracting_Equal_Amounts();

        int[] nums1 = { 1, 5, 0, 3, 5 };
        int minimumOperations1 = solution.minimumOperationsUsingHeaps(nums1);
        System.out.println(minimumOperations1);

        int[] nums2 = { 0 };
        int minimumOperations2 = solution.minimumOperationsUsingSet(nums2);
        System.out.println(minimumOperations2);
    }

    /**
     * Approach II : Using Hashing Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int minimumOperationsUsingSet(int[] nums) {
        int n = nums.length;
        /**
         * Minimum operations needed to make array elements zero
         * = number of non-zero unique elements
         */
        Set<Integer> set = new HashSet<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (nums[i] > 0) {
                set.add(nums[i]);
            }
        }
        return set.size();
    }

    /**
     * Approach I : Using Min Heaps (PriorityQueues) Approach
     *
     * TC: O(N x log(N)) + O(N x N x log(N)) ~ O((N ^ 2 x log(N))
     * SC: O(N)
     */
    public int minimumOperationsUsingHeaps(int[] nums) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (nums[i] > 0) {
                pq.offer(nums[i]); // TC: O(log(N))
            }
        }
        int count = 0;
        while (!pq.isEmpty()) { // TC: O(N)
            int x = pq.poll();
            pq.clear();
            count++;
            for (int i = 0; i < n; i++) { // TC: O(N)
                nums[i] -= x;
                if (nums[i] > 0) {
                    pq.offer(nums[i]); // TC: O(log(N))
                }
            }
        }
        return count;
    }
}
