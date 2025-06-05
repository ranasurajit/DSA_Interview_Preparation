package Stacks_Queues.Utils;

import java.util.LinkedList;
import java.util.Queue;

public class QueueUtils {
    public static Queue<Integer> convert1DArayToQueue(int[] arr) {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int val : arr) {
            queue.offer(val);
        }
        return queue;
    }
}
