package Stacks_Queues.Queues.P3_Reverse_First_K_Of_A_Queue;

import java.util.Queue;
import java.util.Stack;

import Stacks_Queues.Utils.QueueUtils;

public class Reverse_First_K_Of_A_Queue {
    public static void main(String[] args) {
        Reverse_First_K_Of_A_Queue solution = new Reverse_First_K_Of_A_Queue();

        int[] arr = { 1, 2, 3, 4, 5 };
        int k = 3;

        Queue<Integer> q = QueueUtils.convert1DArayToQueue(arr);
        Queue<Integer> reversedQueue = solution.reverseFirstK(q, k);
        System.out.println(reversedQueue);
    }

    /**
     * Approach : Using Queue Approach
     * 
     * TC: O(N + K)
     * SC: O(K)
     */
    public Queue<Integer> reverseFirstK(Queue<Integer> q, int k) {
        if (q.size() < k) {
            return q;
        }
        int count = q.size() - k;
        Stack<Integer> st = new Stack<Integer>(); // SC: O(K)
        while (k > 0) { // TC: O(K)
            st.push(q.poll());
            k--;
        }
        while (!st.isEmpty()) { // TC: O(K)
            q.offer(st.pop());
        }
        while (count > 0) { // TC: O(N - K)
            q.offer(q.poll());
            count--;
        }
        return q;
    }
}
