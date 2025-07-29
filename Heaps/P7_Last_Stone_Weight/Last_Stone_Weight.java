package Heaps.P7_Last_Stone_Weight;

import java.util.PriorityQueue;

public class Last_Stone_Weight {
    public static void main(String[] args) {
        Last_Stone_Weight solution = new Last_Stone_Weight();

        int[] stones1 = { 2, 7, 4, 1, 8, 1 };
        int lastStoneWeight1 = solution.lastStoneWeight(stones1);
        System.out.println(lastStoneWeight1);

        int[] stones2 = { 2, 2 };
        int lastStoneWeight2 = solution.lastStoneWeight(stones2);
        System.out.println(lastStoneWeight2);

        int[] stones3 = { 1 };
        int lastStoneWeight3 = solution.lastStoneWeight(stones3);
        System.out.println(lastStoneWeight3);
    }

    /**
     * Approach : Using Max Heaps (PriorityQueues) Approach
     *
     * TC: O(N x log(N)) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N)
     */
    public int lastStoneWeight(int[] stones) {
        int n = stones.length;
        /**
         * To smash the heaviest two stones, we will be using a Max-Heap
         * to store the stones[i] and poll two heaviest and offer the
         * remaining result after smashing them
         */
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((p, q) -> q - p); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            pq.offer(stones[i]); // TC: O(log(N))
        }
        while (pq.size() > 1) { // TC: O(N)
            // the loop should run till we have only one stone left
            int first = pq.poll();
            int second = pq.poll();
            if (first - second > 0) {
                pq.offer(first - second); // TC: O(log(N))
            }
        }
        if (pq.size() == 1) {
            return pq.peek();
        }
        return 0;
    }
}
