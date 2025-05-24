package Hashing.P7_Restore_The_Array_From_Adjacent_Pairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Restore_The_Array_From_Adjacent_Pairs {
    public static void main(String[] args) {
        Restore_The_Array_From_Adjacent_Pairs solution = new Restore_The_Array_From_Adjacent_Pairs();

        int[][] adjacentPairs1 = { { 2, 1 }, { 3, 4 }, { 3, 2 } };

        int[] nums1 = solution.restoreArray(adjacentPairs1);
        System.out.println(Arrays.toString(nums1));

        int[][] adjacentPairs2 = { { 4, -2 }, { 1, 4 }, { -3, 1 } };

        int[] nums2 = solution.restoreArray(adjacentPairs2);
        System.out.println(Arrays.toString(nums2));
    }

    /**
     * Approach : Using Hashing + Graph Approach
     *
     * TC: O(3 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     *
     * where L = average count of digits in nums i.e. 1 <= L <= 10
     */
    public int[] restoreArray(int[][] adjacentPairs) {
        /**
         * This problem looks like a Graph where adjacentPairs denotes
         * that there is an edge between u --> v
         * so, we need to use HashMap to create the adjacency list
         */
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(N)
        for (int[] edge : adjacentPairs) { // TC: O(N)
            adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
        }
        int startNode = -1;
        for (Integer u : adj.keySet()) { // TC: O(N)
            if (adj.get(u).size() == 1) {
                startNode = u;
                break;
            }
        }
        int[] path = new int[adjacentPairs.length + 1];
        dfsGraph(startNode, Integer.MAX_VALUE, adj, 0, path); // TC: O(N), SC: O(N)
        return path;
    }

    /**
     * TC: O(N)
     * SC: O(N)
     */
    private void dfsGraph(int u, int prev, Map<Integer, ArrayList<Integer>> adj, int index, int[] path) {
        path[index] = u;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (v != prev) {
                dfsGraph(v, u, adj, index + 1, path);
            }
        }
    }
}
