package Heaps.P01_K_Largest_Elements;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class K_Largest_Elements {
    public static void main(String[] args) {
        K_Largest_Elements solution = new K_Largest_Elements();

        int[] arr = { 1, 23, 12, 9, 30, 2, 50 };
        int k = 3;
        ArrayList<Integer> kMax = solution.kLargest(arr, k);
        System.out.println(kMax);
    }

    /**
     * Using Min-Heap (PriorityQueue) Approach
     * 
     * TC: O(K x log(K) + 2 x (N - K) x log(K) + K x log(K)) ~ O(N x log(K))
     * SC: O(K)
     * 
     * @param arr
     * @param k
     * @return
     */
    public ArrayList<Integer> kLargest(int[] arr, int k) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<Integer>();
        // Using a min-heap to store always k elements in Heap
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < k; i++) { // TC: O(K)
            pq.offer(arr[i]); // TC: O(log(K))
        }
        for (int i = k; i < n; i++) { // TC: O(N - K)
            if (arr[i] > pq.peek()) {
                pq.poll(); // TC: O(log(K))
                pq.offer(arr[i]); // TC: O(log(K))
            }
        }
        while (!pq.isEmpty()) { // TC: O(K)
            result.add(0, pq.poll()); // TC: O(log(K))
        }
        return result;
    }
}
