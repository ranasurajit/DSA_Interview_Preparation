package Heaps.P2_Kth_Smallest;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Kth_Smallest {
    public static void main(String[] args) {
        Kth_Smallest solution = new Kth_Smallest();

        int[] arr1 = { 7, 10, 4, 3, 20, 15 };
        int k1 = 3;
        int kthSmallest1 = solution.kthSmallestUsingHeaps(arr1, k1);
        System.out.println(kthSmallest1);

        int[] arr2 = { 2, 3, 1, 20, 15 };
        int k2 = 4;
        int kthSmallest2 = solution.kthSmallestUsingHeaps(arr2, k2);
        System.out.println(kthSmallest2);
    }

    /**
     * Approach II : Using Sorting Approach
     *
     * TC: O(N x log(N))
     * SC: O(1)
     */
    public int kthSmallest(int[] arr, int k) {
        Arrays.sort(arr); // TC: O(N x log(N))
        return arr[k - 1];
    }

    /**
     * Approach I : Using PriorityQueue (Max-Heap) Approach
     *
     * TC: O(N x log(N))
     * SC: O(K)
     */
    public int kthSmallestUsingHeaps(int[] arr, int k) {
        int n = arr.length;
        // we will insert elements to Max-Heap (PriorityQueue)
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((p, q) -> q - p); // SC: O(K)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (pq.size() < k) {
                pq.offer(arr[i]); // TC: O(log(N))
            } else {
                if (!pq.isEmpty() && arr[i] < pq.peek()) {
                    pq.poll();
                    pq.offer(arr[i]); // TC: O(log(N))
                }
            }
        }
        return pq.peek();
    }
}
