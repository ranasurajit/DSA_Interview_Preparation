package Segment_Trees.P2_Sum_Of_Query_II;

import java.util.ArrayList;
import java.util.List;

public class Sum_Of_Query_II {
    public static void main(String[] args) {
        Sum_Of_Query_II solution = new Sum_Of_Query_II();

        int n1 = 4;
        int[] arr1 = { 1, 2, 3, 4 };
        int q1 = 2;
        int[] queries1 = { 1, 4, 2, 3 };

        List<Integer> querySum1 = solution.querySum(n1, arr1, q1, queries1);
        System.out.println(querySum1);

        int n2 = 5;
        int[] arr2 = { 26, 30, 48, 29, 8 };
        int q2 = 2;
        int[] queries2 = { 4, 4, 2, 3 };
        List<Integer> querySum2 = solution.querySum(n2, arr2, q2, queries2);
        System.out.println(querySum2);
    }

    /**
     * Approach : Using Segment Tree Approach
     * 
     * TC: O(N) + O(Q x log(N)) ~ O(N + Q x log(N))
     * SC: O(N) + O(log(N))
     */
    List<Integer> querySum(int n, int arr[], int q, int queries[]) {
        long[] segTree = new long[4 * n]; // SC: O(4 x N)
        buildSegmentTree(segTree, 0, n - 1, arr, 0); // TC: O(N), SC: O(log(N))
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < q; i++) { // TC: O(Q)
            // 0-based indexing
            int start = queries[2 * i] - 1;
            int end = queries[2 * i + 1] - 1;
            result.add((int) getRangeQuerySum(segTree,
                    0, 0, n - 1, start, end)); // TC: O(log(N)), SC: O(log(N))
        }
        return result;
    }

    /**
     * Using Segment Tree Approach
     * 
     * TC: O(2 x N) as each node is visited exactly twice ~ O(N)
     * SC: O(log(N))
     */
    private void buildSegmentTree(long[] segTree, int left, int right, int[] arr, int segIdx) {
        // Base Case
        if (left == right) {
            segTree[segIdx] = arr[left];
            return;
        }
        // Recursion Leap of Faith
        int mid = left + (right - left) / 2;
        // left sub-tree
        buildSegmentTree(segTree, left, mid, arr, segIdx * 2 + 1);
        // right sub-tree
        buildSegmentTree(segTree, mid + 1, right, arr, segIdx * 2 + 2);
        segTree[segIdx] = segTree[segIdx * 2 + 1] + segTree[segIdx * 2 + 2];
    }

    /**
     * Using Segment Tree Approach
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private long getRangeQuerySum(long[] segTree, int segIdx,
            int left, int right, int start, int end) {
        // Base Case
        if (left > end || right < start) {
            // out of bound
            return 0L;
        }
        if (left >= start && right <= end) {
            // full overlap
            return segTree[segIdx];
        }
        // Recursion Calls
        // partial overlap
        int mid = left + (right - left) / 2;
        return getRangeQuerySum(segTree, 2 * segIdx + 1, left, mid, start, end) +
                getRangeQuerySum(segTree, 2 * segIdx + 2, mid + 1, right, start, end);
    }
}
