package Stacks_Queues.Queues.P2_Time_Needed_To_Buy_Tickets;

import java.util.LinkedList;
import java.util.Queue;

public class Time_Needed_To_Buy_Tickets {
    public static void main(String[] args) {
        Time_Needed_To_Buy_Tickets solution = new Time_Needed_To_Buy_Tickets();

        int[] tickets1 = { 2, 3, 2 };
        int k1 = 2;
        int timeLapsed1 = solution.timeRequiredToBuyUsingQueue(tickets1, k1);
        System.out.println(timeLapsed1);

        int[] tickets2 = { 5, 1, 1, 1 };
        int k2 = 0;
        int timeLapsed2 = solution.timeRequiredToBuyOptimal(tickets2, k2);
        System.out.println(timeLapsed2);
    }

    /**
     * Approach II : Using Queue Property + Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int timeRequiredToBuyOptimal(int[] tickets, int k) {
        int n = tickets.length;
        int value = tickets[k];
        int time = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            /**
             * persons standing infront of kth person including him will
             * buy at maximum of tickets[k] and rest (tickets[k] - 1)
             */
            int max = i <= k ? value : value - 1;
            time += tickets[i] > max ? max : tickets[i];
        }
        return time;
    }

    /**
     * Approach I : Using Queue Approach
     *
     * TC: O(N + K), where K = Sum(tickets)
     * SC: O(N)
     */
    public int timeRequiredToBuyUsingQueue(int[] tickets, int k) {
        int n = tickets.length;
        Queue<int[]> queue = new LinkedList<int[]>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            queue.offer(new int[] { tickets[i], i });
        }
        int timeLapsed = 0;
        while (!queue.isEmpty()) { // TC: O(K)
            int[] current = queue.poll();
            if (current[0] - 1 > 0) {
                queue.offer(new int[] { current[0] - 1, current[1] });
            } else if (current[0] == 1 && current[1] == k) {
                // here the kth person will finish buying tickets
                break;
            }
            timeLapsed++;
        }
        // increased timeLapsed by 1 as we broke the loop without incrementing the time
        return timeLapsed + 1;
    }
}
