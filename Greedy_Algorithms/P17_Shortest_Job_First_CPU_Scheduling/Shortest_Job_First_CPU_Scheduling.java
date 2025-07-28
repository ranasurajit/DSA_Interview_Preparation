package Greedy_Algorithms.P17_Shortest_Job_First_CPU_Scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Shortest_Job_First_CPU_Scheduling {
    public static void main(String[] args) {
        Shortest_Job_First_CPU_Scheduling solution = new Shortest_Job_First_CPU_Scheduling();

        int n = 3;
        int[] arrivalTime = { 0, 0, 0 };
        int[] burstTime = { 4, 2, 3 };

        ArrayList<Double> averageTimes = solution.averageTimes(n, arrivalTime, burstTime);
        System.out.println(averageTimes);
    }

    /**
     * Using Greedy Approach
     * 
     * TC: O(N) + O(N x log(N)) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N) + O(N) ~ O(N)
     */
    public ArrayList<Double> averageTimes(int n, int[] arrivalTime,
            int[] burstTime) {
        ArrayList<Double> result = new ArrayList<Double>();
        List<Process> processes = new ArrayList<Process>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            processes.add(new Process(arrivalTime[i], burstTime[i], i));
        }
        // we will be sorting the process based upon the arrivalTime
        Collections.sort(processes, (a, b) -> a.arrival - b.arrival); // TC: O(N x log(N))
        /**
         * now as per Shortest job first (SJF) scheduling policy, we will create
         * a Min-heap that will sort the processes based upon the burstTime
         */
        PriorityQueue<Process> pq = new PriorityQueue<Process>((p, q) -> p.burst - q.burst); // SC: O(N)
        int index = 0;
        int time = 0;
        double wTime = 0.0d;
        double tATime = 0.0d;
        while (index < n || !pq.isEmpty()) { // TC: O(N)
            while (index < n && processes.get(index).arrival <= time) {
                pq.offer(processes.get(index)); // TC: O(log(N))
                index++;
            }
            if (pq.isEmpty()) {
                time = processes.get(index).arrival;
                continue;
            }
            Process current = pq.poll();
            int completionTime = time + current.burst;
            int turnAroundTime = completionTime - current.arrival;
            int waitingTime = turnAroundTime - current.burst;
            tATime += (double) turnAroundTime;
            wTime += (double) waitingTime;
            time = completionTime;
        }
        result.add(wTime / n);
        result.add(tATime / n);
        return result;
    }

    class Process {
        int arrival;
        int burst;
        int index;

        public Process(int arrival, int burst, int index) {
            this.arrival = arrival;
            this.burst = burst;
            this.index = index;
        }
    }
}
