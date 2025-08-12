package Segment_Trees.P3_Range_Minimum_Query;

public class Range_Minimum_Query {

    public static void main(String[] args) {
        Range_Minimum_Query solution = new Range_Minimum_Query();

        int[] arr = { 1, 2, 3, 4 };
        int n = 4;

        int[] segTree = solution.constructST(arr, n);

        int rangeMinQuery1 = solution.RMQ(segTree, n, 0, 2);
        int rangeMinQuery2 = solution.RMQ(segTree, n, 2, 3);
        System.out.println(rangeMinQuery1 + ", " + rangeMinQuery2);
    }

    public int[] constructST(int arr[], int n) {
        int[] segTree = new int[4 * n];
        buildSegmentTree(arr, segTree, 0, 0, n - 1);
        return segTree;
    }

    private void buildSegmentTree(int[] arr, int[] segTree, int segIdx,
            int left, int right) {
        // Base Case
        if (left == right) {
            segTree[segIdx] = arr[left];
            return;
        }
        // Recursion Leap of Faith
        int mid = left + (right - left) / 2;
        // left sub-tree
        buildSegmentTree(arr, segTree, 2 * segIdx + 1, left, mid);
        // right sub-tree
        buildSegmentTree(arr, segTree, 2 * segIdx + 2, mid + 1, right);
        segTree[segIdx] = Math.min(segTree[2 * segIdx + 1], segTree[2 * segIdx + 2]);
    }

    /*
     * The functions returns the
     * min element in the range
     * from l and r
     */
    public int RMQ(int st[], int n, int l, int r) {
        return getMinRangeQuery(l, r, st, 0, 0, n - 1);
    }

    private int getMinRangeQuery(int start, int end, int[] st, int segIdx,
            int left, int right) {
        // Base Case
        if (left > end || right < start) {
            // out of bound
            return Integer.MAX_VALUE;
        }
        if (left >= start && right <= end) {
            // full overlap
            return st[segIdx];
        }
        // Recursion
        // partial overlap
        int mid = left + (right - left) / 2;
        return Math.min(
                getMinRangeQuery(start, end, st, 2 * segIdx + 1, left, mid),
                getMinRangeQuery(start, end, st, 2 * segIdx + 2, mid + 1, right));
    }
}
