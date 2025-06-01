package Line_Sweep.P5_Determine_If_Two_Events_Have_Conflict;

import java.util.Map;
import java.util.TreeMap;

public class Determine_If_Two_Events_Have_Conflict {
    public static void main(String[] args) {
        Determine_If_Two_Events_Have_Conflict solution = new Determine_If_Two_Events_Have_Conflict();

        String[] event11 = { "01:15", "02:00" };
        String[] event12 = { "02:00", "03:00" };

        boolean hasConflict1 = solution.haveConflictLineSweep(event11, event12);
        System.out.println(hasConflict1);

        String[] event21 = { "10:00", "11:00" };
        String[] event22 = { "14:00", "15:00" };

        boolean hasConflict2 = solution.haveConflictStringComparison(event21, event22);
        System.out.println(hasConflict2);
    }

    /**
     * Approach I : Using Line Sweep Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public boolean haveConflictLineSweep(String[] event1, String[] event2) {
        int[] interval1 = convertTimeToInteger(event1); // TC: O(1)
        int[] interval2 = convertTimeToInteger(event2); // TC: O(1)
        // Adding intervals to Sorted Map
        Map<Integer, Integer> eventMap = new TreeMap<Integer, Integer>(); // SC: O(4)
        eventMap.put(interval1[0],
                eventMap.getOrDefault(interval1[0], 0) + 1); // TC: O(4 x log(4)) ~ O(1)
        eventMap.put(interval1[1] + 1,
                eventMap.getOrDefault(interval1[1] + 1, 0) - 1); // TC: O(4 x log(4)) ~ O(1)
        eventMap.put(interval2[0],
                eventMap.getOrDefault(interval2[0], 0) + 1); // TC: O(4 x log(4)) ~ O(1)
        eventMap.put(interval2[1] + 1,
                eventMap.getOrDefault(interval2[1] + 1, 0) - 1); // TC: O(4 x log(4)) ~ O(1)

        int overlap = 0;
        for (Integer key : eventMap.keySet()) { // TC: O(4)
            overlap += eventMap.get(key); // TC: O(log(4))
            if (overlap > 1) {
                // there is an overlap
                return true;
            }
        }
        return false;
    }

    /**
     * TC: O(1) as we have only 5 length words that needs to be processed by this
     * method
     * SC: O(1)
     */
    private int[] convertTimeToInteger(String[] event) {
        String eventStart = event[0];
        String eventEnd = event[1];
        int start = Integer.valueOf(eventStart.substring(0, 2) + eventStart.substring(3));
        int end = Integer.valueOf(eventEnd.substring(0, 2) + eventEnd.substring(3));
        return new int[] { start, end };
    }

    /**
     * Approach II : Using String Comparison Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public boolean haveConflictStringComparison(String[] event1, String[] event2) {
        if (event1[1].compareTo(event2[0]) < 0 || event2[1].compareTo(event1[0]) < 0) {
            return false;
        }
        return true;
    }
}
