package Greedy_Algorithms.P13_Insert_Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Insert_Interval {
    public static void main(String[] args) {
        Insert_Interval solution = new Insert_Interval();

        int[][] intervals1 = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        int[] newInterval1 = { 4, 8 };
        int[][] result1 = solution.insert(intervals1, newInterval1);
        System.out.println(Arrays.deepToString(result1));

        int[][] intervals2 = { { 1, 3 }, { 6, 9 } };
        int[] newInterval2 = { 2, 5 };
        int[][] result2 = solution.insert(intervals2, newInterval2);
        System.out.println(Arrays.deepToString(result2));
    }

    /**
     * Approach : Using Greedy Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int i = 0;
        List<int[]> intervalList = new ArrayList<int[]>(); // SC: O(N)
        // adding the non-overlapping left intervals
        while (i < n && intervals[i][1] < newInterval[0]) {
            intervalList.add(intervals[i]);
            i++;
        }
        // merging and adding overlapping middle intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        intervalList.add(newInterval);
        // adding the non-overlapping right intervals
        while (i < n) {
            intervalList.add(intervals[i]);
            i++;
        }
        int size = intervalList.size();
        int[][] result = new int[size][2];
        for (i = 0; i < size; i++) { // TC: O(N)
            result[i] = intervalList.get(i);
        }
        return result;
    }
}
