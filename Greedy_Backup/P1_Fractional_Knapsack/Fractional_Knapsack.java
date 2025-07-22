package Greedy_Backup.P1_Fractional_Knapsack;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Fractional_Knapsack {
    public static void main(String[] args) {
        List<Integer> val = Arrays.asList(8, 2, 10, 1, 9, 7, 2, 6, 4, 9);
        List<Integer> wt = Arrays.asList(10, 1, 7, 7, 5, 1, 8, 6, 8, 7);
        int capacity = 21;
        double maxValue = fractionalKnapsack(val, wt, capacity);
        System.out.println(maxValue);
    }

    // Function to get the maximum total value in the knapsack.
    private static double fractionalKnapsack(List<Integer> val, List<Integer> wt, int capacity) {
        int n = val.size();
        /**
         * Fractional Knapsack is a greedy problem we need to
         * greedily pick the items based upon the value / weight
         * ratio.
         * 
         * So we need to create Max-Heap (Priority Queue)
         * of value / weight ratio
         * 
         * we will store int[] as { val/wt, val, wt } in the Max-heap
         */
        // SC: O(N)
        PriorityQueue<double[]> pq = new PriorityQueue<double[]>((double[] p, double[] q) -> {
            return Double.compare(q[0], p[0]);
        });
        for (int i = 0; i < n; i++) { // TC: O(N)
            pq.offer(new double[] {
                    ((double) val.get(i) / wt.get(i)),
                    val.get(i),
                    wt.get(i) });
        }
        double currentWeight = 0.0;
        double maxVal = 0.0;
        while (!pq.isEmpty()) { // TC: O(N)
            double[] current = pq.poll();
            if (currentWeight + current[2] <= capacity) {
                currentWeight += current[2];
                maxVal += current[1];
            } else {
                double leftWeight = capacity - currentWeight;
                double fracValue = leftWeight * current[0];
                maxVal += fracValue;
                break;
            }
        }
        return maxVal;
    }
}
