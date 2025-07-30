package Heaps.P11_Kth_Largest_Element_In_A_Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Kth_Largest_Element_In_A_Stream {
    public static void main(String[] args) {
        String[] queries = { "KthLargest", "add", "add", "add", "add", "add" };
        int k = 3;
        int[] nums = { 4, 5, 8, 2 };
        int[][] params = { { 3 }, { 5 }, { 10 }, { 9 }, { 4 } };

        List<Object> result = new ArrayList<Object>();
        KthLargest kthLargest = null;
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (query.equals("KthLargest")) {
                kthLargest = new KthLargest(k, nums);
                result.add(null);
            } else if (query.equals("add")) {
                result.add(kthLargest.add(params[i - 1][0]));
            }
        }
        System.out.println(result);
    }
}

/**
 * Approach : Using Min Heap (PriorityQueues) Approach
 * 
 * TC: O(N x log(K)) + O(P x log(K))
 * SC: O(K)
 *
 * where P = total number of 'add' operations
 */
class KthLargest {
    PriorityQueue<Integer> pq = null;
    private int k;

    /**
     * TC: O(N x log(K))
     * SC: O(1)
     */
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>(); // SC: O(K)
        for (int num : nums) { // TC: O(N)
            pq.offer(num); // TC: O(log(K))
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }

    /**
     * TC: O(log(K))
     * SC: O(1)
     */
    public int add(int val) {
        pq.offer(val); // TC: O(log(K))
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
