package Heaps.P1_Kth_Largest_Element_In_An_Array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Kth_Largest_Element_In_An_Array {
    public static void main(String[] args) {
        Kth_Largest_Element_In_An_Array solution = new Kth_Largest_Element_In_An_Array();

        int[] nums1 = { 3, 2, 1, 5, 6, 4 };
        int k1 = 2;
        int kthLargest1 = solution.findKthLargest(nums1, k1);
        System.out.println(kthLargest1);

        int[] nums2 = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        int k2 = 4;
        int kthLargest2 = solution.findKthLargest(nums2, k2);
        System.out.println(kthLargest2);
    }

    /**
     * Approach II : Using Sorting Approach
     *
     * TC: O(N x log(N))
     * SC: O(1)
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums); // TC: O(N x log(N))
        return nums[n - k];
    }

    /**
     * Approach I : Using PriorityQueue (Min-Heap) Approach
     *
     * TC: O(N x log(N))
     * SC: O(K)
     */
    public int findKthLargestUsingHeaps(int[] nums, int k) {
        int n = nums.length;
        // we will insert elements to Min-Heap (PriorityQueue)
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // SC: O(K)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (pq.size() < k) {
                pq.offer(nums[i]); // TC: O(log(N))
            } else {
                if (!pq.isEmpty() && nums[i] > pq.peek()) {
                    pq.poll();
                    pq.offer(nums[i]); // TC: O(log(N))
                }
            }
        }
        return pq.peek();
    }
}
