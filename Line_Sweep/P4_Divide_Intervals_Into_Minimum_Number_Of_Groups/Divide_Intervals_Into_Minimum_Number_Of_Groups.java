package Line_Sweep.P4_Divide_Intervals_Into_Minimum_Number_Of_Groups;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Divide_Intervals_Into_Minimum_Number_Of_Groups {
    public static void main(String[] args) {
        Divide_Intervals_Into_Minimum_Number_Of_Groups solution = new Divide_Intervals_Into_Minimum_Number_Of_Groups();

        int[][] intervals1 = { { 5, 10 }, { 6, 8 }, { 1, 5 }, { 2, 3 }, { 1, 10 } };
        int groups1 = solution.minGroupsLineSweep(intervals1);
        System.out.println(groups1);

        int[][] intervals2 = { { 1, 3 }, { 5, 6 }, { 8, 10 }, { 11, 13 } };
        int groups2 = solution.minGroupsHeaps(intervals2);
        System.out.println(groups2);
    }

    /**
     * Approach I : Using Line Sweep Approach
     *
     * TC: O(2 x N x log(N) + 2 x N x log(2 x N)) ~ O(N x log(N))
     * SC: O(2 x N) ~ O(N)
     */
    public int minGroupsLineSweep(int[][] intervals) {
        Map<Integer, Integer> eventsMap = new TreeMap<Integer, Integer>(); // SC: O(2 x N)
        for (int[] time : intervals) { // TC: O(N)
            int start = time[0];
            int end = time[1];
            eventsMap.put(start, eventsMap.getOrDefault(start, 0) + 1); // TC: O(log(N))
            /**
             * as endTime is included as [startTime, endTime]
             * is full-open interval i.e. startTime <= x <= endTime
             */
            eventsMap.put(end + 1, eventsMap.getOrDefault(end + 1, 0) - 1); // TC: O(log(N))
        }
        int currentOverlaps = 0;
        int maxOverlaps = 0;
        for (Integer key : eventsMap.keySet()) { // TC: O(2 x N)
            currentOverlaps += eventsMap.get(key); // TC: O(log(2 x N))
            maxOverlaps = Math.max(maxOverlaps, currentOverlaps);
        }
        /**
         * maximum overlaps will tell how many minimum groups are needed
         * so that no two intervals that are in the same group intersect each other
         */
        return maxOverlaps;
    }

    /**
     * Approach II : Using Min-Heap + Sorting Approach
     *
     * TC: O(3 x N x log(N)) ~ O(N x log(N))
     * SC: O(N)
     */
    public int minGroupsHeaps(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // TC: O(N x log(N))
        // Using a Min-Heap to insert end time of each interval
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // SC: O(N)
        for (int[] time : intervals) { // TC: O(N)
            int start = time[0];
            int end = time[1];
            // if heaps top's end time < start then we can form a group so we should remove
            // top from heap
            if (!pq.isEmpty() && pq.peek() < start) {
                pq.poll(); // TC: O(log(N))
            }
            pq.offer(end); // TC: O(log(N))
        }
        // we have the size of the PriorityQueue which denotes the minimum number of
        // groups
        return pq.size();
    }
}
