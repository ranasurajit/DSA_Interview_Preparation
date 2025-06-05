package Stacks_Queues.Queues.P1_Number_Of_Recent_Calls;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Number_Of_Recent_Calls {
    public static void main(String[] args) {
        String[] operations = { "RecentCounter", "ping", "ping", "ping", "ping" };
        int[][] params = { {}, { 1 }, { 100 }, { 3001 }, { 3002 } };

        RecentCounter counter = null;
        List<Object> result = new ArrayList<Object>();

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("RecentCounter")) {
                counter = new RecentCounter();
                result.add(null);
            } else if (operation.equals("ping")) {
                result.add(counter.ping(params[i][0]));
            }
        }

        System.out.println(result);
    }

    /**
     * Approach : Using Queue Approach
     *
     * TC: O(1)
     * SC: O(N)
     */
    static class RecentCounter {
        Queue<Integer> queue = null;

        /**
         * TC: O(1)
         * SC: O(N)
         */
        public RecentCounter() {
            queue = new LinkedList<Integer>(); // SC: O(N)
        }

        /**
         * TC: O(1)
         * SC: O(1)
         */
        public int ping(int t) {
            queue.offer(t);
            while (queue.peek() < t - 3000) {
                /**
                 * we poll it as we don't need it again as it is
                 * guaranteed that every call to ping uses a strictly
                 * larger value of t than the previous call
                 */
                queue.poll();
            }
            return queue.size();
        }
    }

    /**
     * Your RecentCounter object will be instantiated and called as such:
     * RecentCounter obj = new RecentCounter();
     * int param_1 = obj.ping(t);
     */
}
