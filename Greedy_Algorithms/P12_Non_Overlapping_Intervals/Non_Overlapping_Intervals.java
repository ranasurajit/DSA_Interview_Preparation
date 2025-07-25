package Greedy_Algorithms.P12_Non_Overlapping_Intervals;

import java.util.Arrays;

public class Non_Overlapping_Intervals {
    public static void main(String[] args) {
        Non_Overlapping_Intervals solution = new Non_Overlapping_Intervals();

        int[][] intervals1 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };
        int countOverlappingIntervals1 = solution.eraseOverlapIntervals(intervals1);
        System.out.println(countOverlappingIntervals1);

        int[][] intervals2 = { { 1, 2 }, { 1, 2 }, { 1, 2 } };
        int countOverlappingIntervals2 = solution.eraseOverlapIntervals(intervals2);
        System.out.println(countOverlappingIntervals2);
    }

    /**
     * Approach : Using Sorting + Simulation Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        // sorting the intervals in ascending order of end time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // TC: O(N x log(N))
        int countNonOverlaps = 1;
        int lastEndTime = intervals[0][1];
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (intervals[i][0] >= lastEndTime) {
                countNonOverlaps++;
                lastEndTime = intervals[i][1];
            }
        }
        return n - countNonOverlaps;
    }
}
