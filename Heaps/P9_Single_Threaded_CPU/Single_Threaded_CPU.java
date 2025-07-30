package Heaps.P9_Single_Threaded_CPU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Single_Threaded_CPU {
    public static void main(String[] args) {
        Single_Threaded_CPU solution = new Single_Threaded_CPU();

        int[][] tasks1 = { { 1, 2 }, { 2, 4 }, { 3, 2 }, { 4, 1 } };
        int[] taskOrder1 = solution.getOrder(tasks1);
        System.out.println(Arrays.toString(taskOrder1));

        int[][] tasks2 = { { 7, 10 }, { 7, 12 }, { 7, 5 }, { 7, 4 }, { 7, 2 } };
        int[] taskOrder2 = solution.getOrder(tasks2);
        System.out.println(Arrays.toString(taskOrder2));
    }

    /**
     * Approach : Using Min Heap (PriorityQueues) + Sorting Approach
     *
     * TC: O(N) + O(N x log(N)) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N) + O(N) ~ O(N)
     */
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        List<Process> taskList = new ArrayList<Process>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            taskList.add(new Process(tasks[i][0], tasks[i][1], i));
        }
        /**
         * We need to sort the tasks based upon the enqueueTime
         */
        Collections.sort(taskList, (a, b) -> a.startTime - b.startTime); // TC: O(N x log(N))
        /**
         * We need to use a PriorityQueue (Min-Heap) to find which task would CPU
         * be preferring, when multiple tasks are given to it (when CPU is idle)
         */
        PriorityQueue<Process> pq = new PriorityQueue<Process>((p, q) -> {
            if (p.duration == q.duration) {
                return p.index - q.index;
            }
            return p.duration - q.duration;
        }); // SC: O(N)
        int i = 0;
        int tIdx = 0;
        int time = 0;
        int[] taskOrder = new int[n];
        while (!pq.isEmpty() || i < n) { // TC: O(N)
            if (pq.isEmpty() && time < taskList.get(i).startTime) {
                time = taskList.get(i).startTime;
            }
            while (i < n && time >= taskList.get(i).startTime) {
                // CPU pooling
                pq.offer(taskList.get(i)); // TC: O(log(N))
                i++;
            }
            // poll the elements out of PriorityQueue
            Process current = pq.poll();
            time += current.duration;
            taskOrder[tIdx++] = current.index;
        }
        return taskOrder;
    }

    class Process {
        int startTime;
        int duration;
        int index;

        public Process(int startTime, int duration, int index) {
            this.startTime = startTime;
            this.duration = duration;
            this.index = index;
        }
    }
}
