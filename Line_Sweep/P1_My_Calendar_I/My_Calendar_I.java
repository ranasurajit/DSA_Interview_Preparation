package Line_Sweep.P1_My_Calendar_I;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class My_Calendar_I {

    public static void main(String[] args) {
        String[] operations = { "MyCalendar", "book", "book", "book" };
        int[][] values = { {}, { 10, 20 }, { 15, 25 }, { 20, 30 } };

        MyCalendar calendar = null;
        ArrayList<Object> result = new ArrayList<Object>();

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("MyCalendar")) {
                calendar = new MyCalendar();
                result.add(null);
            } else if (operation.equals("book")) {
                result.add(calendar.book(values[i][0], values[i][1]));
            }
        }
        System.out.println(result);
    }

    static class MyCalendar {
        Map<Integer, Integer> eventsMap = null;

        /**
         * TC: O(1)
         * SC: O(Q)
         */
        public MyCalendar() {
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
                if (sum > 1) {
                    // condition for double booking is encountered so undo map insertion for
                    // interval
                    eventsMap.put(startTime, eventsMap.get(startTime) - 1); // TC: O(log(Q))
                    eventsMap.put(endTime, eventsMap.get(endTime) + 1); // TC: O(log(Q))
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Your MyCalendar object will be instantiated and called as such:
     * MyCalendar obj = new MyCalendar();
     * boolean param_1 = obj.book(startTime,endTime);
     */
}
