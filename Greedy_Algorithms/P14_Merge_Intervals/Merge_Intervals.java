package Greedy_Algorithms.P14_Merge_Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Merge_Intervals {
    public static void main(String[] args) {
        Merge_Intervals solution = new Merge_Intervals();

        int[][] intervals1 = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        int[][] mergedIntervals1 = solution.merge(intervals1);
        System.out.println(Arrays.deepToString(mergedIntervals1));

        int[][] intervals2 = { { 1, 4 }, { 4, 5 } };
        int[][] mergedIntervals2 = solution.merge(intervals2);
        System.out.println(Arrays.deepToString(mergedIntervals2));
    }

    /**
     * Approach : Using Greedy Approach
     *
     * TC: O(N x log(N)) + O(N) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // TC: O(N x log(N))
        List<int[]> mergedIntervals = new ArrayList<int[]>(); // SC: O(N)
        int[] current = intervals[0];
        mergedIntervals.add(current);
        for (int i = 1; i < n; i++) { // TC: O(N)
            int[] next = intervals[i];
            if (next[0] <= current[1]) {
                // overlap
                current[1] = Math.max(current[1], next[1]);
            } else {
                current = next;
                mergedIntervals.add(current);
            }
        }
        int size = mergedIntervals.size();
        int[][] merged = new int[size][2];
        for (int i = 0; i < size; i++) { // TC: O(N)
            merged[i] = mergedIntervals.get(i);
        }
        return merged;
    }
}
