package Segment_Trees.P1_Range_Sum_Queries;

import java.util.ArrayList;
import java.util.List;

public class Range_Sum_Queries {
    public static void main(String[] args) {
        int N = 6;
        int[] arr = { 1, 3, 5, 7, 9, 11 };

        String[] queries = { "getSum", "updateValue", "getSum" };
        int[][] params = { { 0, 2 }, { 3, 17 }, { 0, 5 } };

        List<Object> result = new ArrayList<Object>();
        long[] st = new long[4 * N];

        RangeSumQuery rangeSumQuery = new RangeSumQuery();
        rangeSumQuery.buildSegmentTree(st, 0, arr, 0, N - 1);
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (query.equals("getSum")) {
                result.add(rangeSumQuery.getSum(st, N, params[i][0], params[i][1]));
            } else {
                rangeSumQuery.updateValue(params[0], st, N, N, i);
                result.add(null);
            }
        }
        System.out.println(result);
    }
}

class RangeSumQuery {
    // arr : given array
    // n : size of arr
    // index : need to update
    // new_val : given value to which we need to update index
    // st : constructed segment-tree

    // Function to update a value in input array and segment tree.
    /**
     * Using Segment Tree Approach
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    public void updateValue(int arr[], long st[], int n, int index, int val) {
        updateSegmentTree(arr, index, val, st, 0, n - 1, 0);
    }

    // Function to return sum of elements in range from index qs (query start)
    // to qe (query end).
    /**
     * Using Segment Tree Approach
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    public long getSum(long st[], int n, int l, int r) {
        return queryRangeSum(l, r, st, 0, n - 1, 0);
    }

    /**
     * Using Segment Tree Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(log(N))
     */
    public void buildSegmentTree(long[] st, int segIdx, int[] arr, int left, int right) {
        // Base Case
        if (left == right) {
            st[segIdx] = arr[left];
            return;
        }
        // Recursion Leap of Faith
        int mid = left + (right - left) / 2;
        buildSegmentTree(st, segIdx * 2 + 1, arr, left, mid);
        buildSegmentTree(st, segIdx * 2 + 2, arr, mid + 1, right);
        st[segIdx] = st[2 * segIdx + 1] + st[2 * segIdx + 2];
    }

    /**
     * Using Segment Tree Approach
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private static void updateSegmentTree(int[] arr, int index, int val,
            long[] st, int left, int right, int segIdx) {
        // Base Case
        if (left == right) {
            st[segIdx] = val;
            return;
        }
        // Recursion Leap of Faith
        int mid = left + (right - left) / 2;
        if (index <= mid) {
            updateSegmentTree(arr, index, val, st, left, mid, segIdx * 2 + 1);
        } else {
            updateSegmentTree(arr, index, val, st, mid + 1, right, segIdx * 2 + 2);
        }
        st[segIdx] = st[segIdx * 2 + 1] + st[segIdx * 2 + 2];
    }

    /**
     * Using Segment Tree Approach
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private static long queryRangeSum(int start, int end, long[] st,
            int left, int right, int segIdx) {
        // Base case
        if (left > end || right < start) {
            // out of bounds
            return 0L;
        }
        if (left >= start && right <= end) {
            // full overlap
            return st[segIdx];
        }
        // Recursion Leap of Faith - partial overlap
        int mid = left + (right - left) / 2;
        return queryRangeSum(start, end, st, left, mid, segIdx * 2 + 1) +
                queryRangeSum(start, end, st, mid + 1, right, segIdx * 2 + 2);
    }
}
