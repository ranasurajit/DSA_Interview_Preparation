package Heaps.P15_Maximal_Score_After_Applying_K_Operations;

import java.util.PriorityQueue;

public class Maximal_Score_After_Applying_K_Operations {
    public static void main(String[] args) {
        Maximal_Score_After_Applying_K_Operations solution = new Maximal_Score_After_Applying_K_Operations();

        int[] nums1 = { 10, 10, 10, 10, 10 };
        int k1 = 5;

        long maxScore1 = solution.maxKelements(nums1, k1);
        System.out.println(maxScore1);

        int[] nums2 = { 1, 10, 3, 3, 3 };
        int k2 = 3;

        long maxScore2 = solution.maxKelements(nums2, k2);
        System.out.println(maxScore2);
    }

    /**
     * Approach : Using Max-Heap (PriorityQueues) Approach
     *
     * TC: O(N x log(N)) + O(K x log(N)) ~ O((N + K) x log(N))
     * SC: O(N)
     */
    public long maxKelements(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            pq.offer(nums[i]); // TC: O(log(N))
        }
        long score = 0L;
        while (!pq.isEmpty() && k > 0) { // TC: O(K)
            long current = (long) pq.poll();
            score += current;
            pq.offer((int) Math.ceil(current / 3.0)); // TC: O(log(N))
            k--;
        }
        return score;
    }
}
