package Sweep_Line_Algorithm.P5_Determine_If_Two_Events_Have_Conflict;

import java.util.TreeMap;

public class Determine_If_Two_Events_Have_Conflict {
    public static void main(String[] args) {
        Determine_If_Two_Events_Have_Conflict solution = new Determine_If_Two_Events_Have_Conflict();

        String[] event1 = { "01:15", "02:00" };
        String[] event2 = { "02:00", "03:00" };
        boolean isOverlapped = solution.haveConflict(event1, event2);

        System.out.println(isOverlapped);
    }

    /**
     * Using Sweep-Line Algorithm
     *
     * TC: O(N x log(N))
     * SC: O(N)
     * 
     * @param event1
     * @param event2
     * @return
     */
    public boolean haveConflict(String[] event1, String[] event2) {
        int[] eventLeft = convertTimeToInteger(event1); // TC: O(N), SC: O(1)
        int[] eventRight = convertTimeToInteger(event2); // TC: O(N), SC: O(1)

        return doesOverlap(eventLeft, eventRight);
    }

    /**
     * TC: O(N x log(N))
     * SC: O(N)
     * 
     * @param eventLeft
     * @param eventRight
     * @return
     */
    private boolean doesOverlap(int[] eventLeft, int[] eventRight) {
        TreeMap<Integer, Integer> events = new TreeMap<Integer, Integer>(); // SC: O(2 x N)
        events.put(eventLeft[0],
                events.getOrDefault(eventLeft[0], 0) + 1); // TC: O(log(N))
        events.put(eventLeft[1] + 1,
                events.getOrDefault(eventLeft[1] + 1, 0) - 1); // TC: O(log(N))
        events.put(eventRight[0],
                events.getOrDefault(eventRight[0], 0) + 1); // TC: O(log(N))
        events.put(eventRight[1] + 1,
                events.getOrDefault(eventRight[1] + 1, 0) - 1); // TC: O(log(N))

        int actualEvents = 0;
        for (Integer key : events.keySet()) { // TC: O(N), SC: O(1)
            actualEvents += events.get(key); // TC: O(log(N))
            if (actualEvents > 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * TC: O(N)
     * SC: O(1)
     * 
     * @param event
     * @return
     */
    private int[] convertTimeToInteger(String[] event) {
        int[] eventData = new int[2];
        eventData[0] = Integer.valueOf(event[0].substring(0, 2) +
                event[0].substring(3, 5));
        eventData[1] = Integer.valueOf(event[1].substring(0, 2) +
                event[1].substring(3, 5));
        return eventData;
    }
}
