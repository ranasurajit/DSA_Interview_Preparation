package Line_Sweep.P3_My_Calendar_III;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class My_Calendar_III {
    public static void main(String[] args) {
        String[] operations = { "MyCalendarThree", "book", "book", "book", "book", "book", "book" };
        int[][] values = { {}, { 10, 20 }, { 50, 60 }, { 10, 40 }, { 5, 15 }, { 5, 10 }, { 25, 55 } };

        MyCalendarThree calendar = null;
        ArrayList<Object> result = new ArrayList<Object>();

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("MyCalendarThree")) {
                calendar = new MyCalendarThree();
                result.add(null);
            } else if (operation.equals("book")) {
                result.add(calendar.book(values[i][0], values[i][1]));
            }
        }
        System.out.println(result);
    }

    static class MyCalendarThree {
        Map<Integer, Integer> eventsMap = null;

        /**
         * TC: O(1)
         * SC: O(Q)
         */
        public MyCalendarThree() {
            eventsMap = new TreeMap<Integer, Integer>();
        }

        /**
         * TC: O(Q x log(Q) + 2 x log(Q)) ~ O(Q x log(Q))
         * SC: O(1)
         */
        public int book(int startTime, int endTime) {
            eventsMap.put(startTime, eventsMap.getOrDefault(startTime, 0) + 1); // TC: O(log(Q))
            /**
             * as endTime is not included as [startTime, endTime)
             * is half-open interval i.e. startTime <= x < endTime
             */
            eventsMap.put(endTime, eventsMap.getOrDefault(endTime, 0) - 1); // TC: O(log(Q))
            int sum = 0;
            int maxEvents = 0;
            for (Integer key : eventsMap.keySet()) { // TC: O(Q)
                sum += eventsMap.get(key); // TC: O(log(Q))
                // capture the maximum overlapping booking in maxEvents
                maxEvents = Math.max(maxEvents, sum);
            }
            return maxEvents;
        }
    }

    /**
     * Your MyCalendarThree object will be instantiated and called as such:
     * MyCalendarThree obj = new MyCalendarThree();
     * int param_1 = obj.book(startTime,endTime);
     */
}
