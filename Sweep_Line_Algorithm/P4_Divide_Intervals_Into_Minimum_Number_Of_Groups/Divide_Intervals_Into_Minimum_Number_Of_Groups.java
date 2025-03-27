package Sweep_Line_Algorithm.P4_Divide_Intervals_Into_Minimum_Number_Of_Groups;

import java.util.TreeMap;

public class Divide_Intervals_Into_Minimum_Number_Of_Groups {
    public static void main(String[] args) {
        Divide_Intervals_Into_Minimum_Number_Of_Groups solution = new Divide_Intervals_Into_Minimum_Number_Of_Groups();

        int[][] intervals = { { 5, 10 }, { 6, 8 }, { 1, 5 }, { 2, 3 }, { 1, 10 } };
        int groups = solution.minGroups(intervals);
        System.out.println(groups);
    }

    /**
     * Using Sweep-Line Algorithm
     *
     * TC: O(N x log(N))
     * SC: O(N)
     * 
     * @param intervals
     * @return
     */
    public int minGroups(int[][] intervals) {
        TreeMap<Integer, Integer> events = new TreeMap<Integer, Integer>();
        // minimum number of groups is the maximum number of overlapping intervals
        return getMaximumOverlaps(intervals, events);
    }

    /**
     * TC: O(N x log(N))
     * SC: O(1)
     * 
     * @param intervals
     * @param events
     * @return
     */
    private int getMaximumOverlaps(int[][] intervals,
            TreeMap<Integer, Integer> events) {
        for (int[] interval : intervals) { // TC: O(N)
            events.put(interval[0],
                    events.getOrDefault(interval[0], 0) + 1); // TC: O(log(N))
            events.put(interval[1] + 1,
                    events.getOrDefault(interval[1] + 1, 0) - 1); // TC: O(log(N))
        }
        int maxGroups = 0;
        int currentGroups = 0;
        for (Integer key : events.keySet()) { // TC: O(2 x N)
            currentGroups += events.get(key); // TC: O(log(N))
            maxGroups = Math.max(maxGroups, currentGroups);
        }
        return maxGroups;
    }
}
