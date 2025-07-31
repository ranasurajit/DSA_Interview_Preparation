package Heaps.P14_Find_K_Pairs_With_Smallest_Sums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Find_K_Pairs_With_Smallest_Sums {
    public static void main(String[] args) {
        Find_K_Pairs_With_Smallest_Sums solution = new Find_K_Pairs_With_Smallest_Sums();

        int[] nums11 = { 1, 7, 11 };
        int[] nums12 = { 2, 4, 6 };
        int k1 = 3;

        List<List<Integer>> kSmallestPairs1 = solution.kSmallestPairsBruteForce(nums11, nums12, k1);
        System.out.println(kSmallestPairs1);

        int[] nums21 = { 1, 1, 2 };
        int[] nums22 = { 1, 2, 3 };
        int k2 = 2;

        List<List<Integer>> kSmallestPairs2 = solution.kSmallestPairsOptimal(nums21, nums22, k2);
        System.out.println(kSmallestPairs2);
    }

    /**
     * Approach II : Using Optimal Approach with Min-Heap (PriorityQueues) Hashing
     * and DFS Approach
     *
     * TC: O(K x log(K))
     * SC: O(K) + O(K) ~ O(K)
     *
     * Accepted (31 / 31 testcases passed)
     */
    public List<List<Integer>> kSmallestPairsOptimal(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (m == 0 || n == 0 || k == 0) {
            return result;
        }
        Set<String> visited = new HashSet<String>(); // SC: O(K)
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(K)
        pq.offer(new int[] { nums1[0] + nums2[0], 0, 0 });
        visited.add("0,0");
        while (!pq.isEmpty() && result.size() < k) { // TC: O(K)
            int[] current = pq.poll();
            int i = current[1];
            int j = current[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));
            if (i + 1 < m && !visited.contains((i + 1) + "," + j)) {
                pq.offer(new int[] { nums1[i + 1] + nums2[j], i + 1, j }); // TC: O(log(K))
                visited.add((i + 1) + "," + j);
            }
            if (j + 1 < n && !visited.contains(i + "," + (j + 1))) {
                pq.offer(new int[] { nums1[i] + nums2[j + 1], i, j + 1 }); // TC: O(log(K))
                visited.add(i + "," + (j + 1));
            }
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force with Max-Heap (PriorityQueues) Approach
     *
     * TC: O(M x N x log(K)) + O(K) ~ O(M x N x log(K))
     * SC: O(K)
     *
     * Accepted (31 / 31 testcases passed)
     */
    public List<List<Integer>> kSmallestPairsBruteForce(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> q[2] - p[2]); // SC: O(K)
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (pq.size() < k) {
                    pq.offer(new int[] { nums1[i], nums2[j], nums1[i] + nums2[j] }); // TC: O(log(K))
                } else if (nums1[i] + nums2[j] < pq.peek()[2]) {
                    pq.poll();
                    pq.offer(new int[] { nums1[i], nums2[j], nums1[i] + nums2[j] }); // TC: O(log(K))
                } else {
                    // nums1[i] + nums2[j] >= pq.peek()[2] so break the loop
                    break;
                }
            }
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        while (!pq.isEmpty()) { // TC: O(K)
            List<Integer> list = new ArrayList<Integer>();
            int[] current = pq.poll();
            list.add(current[0]);
            list.add(current[1]);
            result.add(list);
        }
        return result;
    }
}
