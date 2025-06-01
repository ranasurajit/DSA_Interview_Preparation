package Line_Sweep.P2_My_Calendar_II;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class My_Calendar_II {
    public static void main(String[] args) {
        String[] operations = { "MyCalendarTwo", "book", "book", "book", "book", "book", "book" };
        int[][] values = { {}, { 10, 20 }, { 50, 60 }, { 10, 40 }, { 5, 15 }, { 5, 10 }, { 25, 55 } };

        MyCalendarTwo calendar = null;
        ArrayList<Object> result = new ArrayList<Object>();

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("MyCalendarTwo")) {
                calendar = new MyCalendarTwo();
                result.add(null);
            } else if (operation.equals("book")) {
                result.add(calendar.book(values[i][0], values[i][1]));
            }
        }
        System.out.println(result);
    }

    static class MyCalendarTwo {
        Map<Integer, Integer> eventsMap = null;

        /**
         * TC: O(1)
         * SC: O(Q)
         */
        public MyCalendarTwo() {
            eventsMap = new TreeMap<Integer, Integer>();
        }

        /**
         * TC: O(Q x log(Q))
         * SC: O(1)
         */
        public boolean book(int startTime, int endTime) {
            eventsMap.put(startTime, eventsMap.getOrDefault(startTime, 0) + 1); // TC: O(log(Q))
            /**
             * as endTime is not included as [startTime, endTime)
             * is half-open interval i.e. startTime <= x < endTime
             */
            eventsMap.put(endTime, eventsMap.getOrDefault(endTime, 0) - 1); // TC: O(log(Q))
            int sum = 0;
            for (Integer key : eventsMap.keySet()) { // TC: O(Q)
                sum += eventsMap.get(key); // TC: O(log(Q))
                if (sum > 2) {
                    /**
                     * condition for triple booking is encountered so
                     * undo map insertion for interval
                     */
                    eventsMap.put(startTime, eventsMap.get(startTime) - 1); // TC: O(log(Q))
                    if (eventsMap.get(startTime) == 0) {
                        eventsMap.remove(startTime);
                    }
                    eventsMap.put(endTime, eventsMap.get(endTime) + 1); // TC: O(log(Q))
                    if (eventsMap.get(endTime) == 0) {
                        eventsMap.remove(endTime);
                    }
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Your MyCalendarTwo object will be instantiated and called as such:
     * MyCalendarTwo obj = new MyCalendarTwo();
     * boolean param_1 = obj.book(startTime,endTime);
     */
}
