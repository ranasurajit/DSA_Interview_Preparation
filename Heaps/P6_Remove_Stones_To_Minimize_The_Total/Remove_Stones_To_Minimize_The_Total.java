package Heaps.P6_Remove_Stones_To_Minimize_The_Total;

import java.util.PriorityQueue;

public class Remove_Stones_To_Minimize_The_Total {
    public static void main(String[] args) {
        Remove_Stones_To_Minimize_The_Total solution = new Remove_Stones_To_Minimize_The_Total();

        int[] piles1 = { 5, 4, 9 };
        int k1 = 2;
        int minimumStonesRemaining1 = solution.minStoneSum(piles1, k1);
        System.out.println(minimumStonesRemaining1);

        int[] piles2 = { 4, 3, 6, 7 };
        int k2 = 3;
        int minimumStonesRemaining2 = solution.minStoneSum(piles2, k2);
        System.out.println(minimumStonesRemaining2);
    }

    /**
     * Approach : Using Max Heaps (PriorityQueues) Approach
     *
     * TC: O(N x log(N)) + O(K x log(N)) ~ O((N + K) x log(N))
     * SC: O(N)
     */
    public int minStoneSum(int[] piles, int k) {
        int n = piles.length;
        /**
         * To minimize the remaining stones after 'k' operations,
         * we should chose the piles[i] which has maximum number of
         * stones so that ceil(piles[i] / 2) can be maximized
         *
         * We will be using a Max-Heap to store the piles[i]
         * i.e. (remaining stones)
         */
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((p, q) -> q - p); // SC: O(N)
        int totalStones = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            pq.offer(piles[i]); // TC: O(log(N))
            totalStones += piles[i];
        }
        while (!pq.isEmpty() && k > 0) { // TC: O(K)
            int current = pq.poll();
            int removed = (current / 2);
            totalStones -= removed; // removing stones from totalStones
            current -= removed;
            if (current > 0) {
                pq.offer(current); // TC: O(log(N))
            }
            k--;
        }
        return totalStones;
    }
}
