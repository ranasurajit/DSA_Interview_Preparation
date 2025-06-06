package Stacks_Queues.Queues.P6_Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_Or_Equal_To_Limit;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_Or_Equal_To_Limit {
    public static void main(String[] args) {
        Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_Or_Equal_To_Limit solution = new Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_Or_Equal_To_Limit();

        int[] nums1 = { 10, 1, 2, 4, 7, 2 };
        int limit1 = 5;

        int longestSubarray1 = solution.longestSubarrayUsingHeaps(nums1, limit1);
        System.out.println(longestSubarray1);

        int[] nums2 = { 8, 2, 4, 7 };
        int limit2 = 4;

        int longestSubarray2 = solution.longestSubarrayUsingDeque(nums2, limit2);
        System.out.println(longestSubarray2);
    }

    /**
     * Approach II : Using Sliding Window + Deque Approach
     *
     * TC: O(N)
     * SC: O(2 x N) ~ O(N)
     *
     * Accepted (63 / 63 testcases passed)
     */
    public int longestSubarrayUsingDeque(int[] nums, int limit) {
        int n = nums.length;

        /**
         * we will use two Deque's to store minimum and maximum value's indices
         * at the beginning of it in any variable size window
         */
        Deque<Integer> minDeque = new ArrayDeque<Integer>(); // SC: O(N)
        Deque<Integer> maxDeque = new ArrayDeque<Integer>(); // SC: O(N)

        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int maxLength = 0;

        while (j < n) { // TC: O(N)
            while (!minDeque.isEmpty() && nums[j] < nums[minDeque.peekLast()]) {
                minDeque.pollLast();
            }
            while (!maxDeque.isEmpty() && nums[j] > nums[maxDeque.peekLast()]) {
                maxDeque.pollLast();
            }
            minDeque.addLast(j); // TC: O(1)
            maxDeque.addLast(j); // TC: O(1)
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                // remove computation from index i
                if (minDeque.peekFirst() == i) {
                    minDeque.pollFirst(); // TC: O(1)
                }
                if (maxDeque.peekFirst() == i) {
                    maxDeque.pollFirst(); // TC: O(1)
                }
                i++;
            }
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
        }
        return maxLength;
    }

    /**
     * Approach I : Using Sliding Window + PriorityQueue Approach
     *
     * TC: O(2 x N x log(N) + 2 x N x N) ~ O(N ^ 2)
     * SC: O(2 x N) ~ O(N)
     *
     * Time Limit Exceeded (62 / 63 testcases passed)
     */
    public int longestSubarrayUsingHeaps(int[] nums, int limit) {
        int n = nums.length;
        PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>(); // SC: O(N)
        PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>((p, q) -> q - p); // SC: O(N)
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int maxLength = 0;
        while (j < n) { // TC: O(N)
            minPQ.offer(nums[j]); // TC: O(log(N))
            maxPQ.offer(nums[j]); // TC: O(log(N))
            while (maxPQ.peek() - minPQ.peek() > limit) {
                // remove computation from index i
                minPQ.remove(nums[i]); // TC: O(N)
                maxPQ.remove(nums[i]); // TC: O(N)
                i++;
            }
            if (maxPQ.peek() - minPQ.peek() <= limit) {
                maxLength = Math.max(maxLength, j - i + 1);
            }
            j++;
        }
        return maxLength;
    }
}
