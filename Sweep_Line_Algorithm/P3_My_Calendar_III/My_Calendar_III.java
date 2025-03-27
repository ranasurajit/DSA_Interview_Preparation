package Sweep_Line_Algorithm.P3_My_Calendar_III;

import java.util.ArrayList;
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

    /**
     * Using Sweep-Line Algorithm
     * 
     * TC: O(N x log(N))
     * SC: O(N)
     */
    static class MyCalendarThree {

        private TreeMap<Integer, Integer> events;

        public MyCalendarThree() {
            events = new TreeMap<Integer, Integer>();
        }

        /**
         * TC: O(N x log(N))
         * SC: O(1)
         * 
         * @param startTime
         * @param endTime
         * @return
         */
        public int book(int startTime, int endTime) {
            events.put(startTime, events.getOrDefault(startTime, 0) + 1);
            events.put(endTime, events.getOrDefault(endTime, 0) - 1);
            int activeEvents = 0;
            int maxActiveEvents = Integer.MIN_VALUE;
            for (Integer key : events.keySet()) {
                activeEvents += events.get(key);
                maxActiveEvents = Math.max(maxActiveEvents, activeEvents);
            }
            return activeEvents;
        }
    }

    /**
     * Your MyCalendarThree object will be instantiated and called as such:
     * MyCalendarThree obj = new MyCalendarThree();
     * int param_1 = obj.book(startTime,endTime);
     */
}
