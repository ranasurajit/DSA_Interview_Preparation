package Greedy.P2_Maximum_Units_On_A_Truck;

import java.util.PriorityQueue;

public class Maximum_Units_On_A_Truck {
    public static void main(String[] args) {
        int[][] boxTypes = { { 5, 10 }, { 2, 5 }, { 4, 7 }, { 3, 9 } };
        int truckSize = 10;
        int maxUnits = maximumUnits(boxTypes, truckSize);
        System.out.println(maxUnits);
    }

    /**
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        int n = boxTypes.length;
        /**
         * We need to maximize the total number of units so we need to
         * pick those boxTypes having maximum units / box
         * creating a Max-Heap for the same to store { box, units }
         */
        // SC: O(N)
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> {
            return Integer.compare(q[1], p[1]);
        });
        for (int i = 0; i < n; i++) { // TC: O(N)
            pq.offer(new int[] { boxTypes[i][0], boxTypes[i][1] });
        }
        int maxUnits = 0;
        int currentSize = 0;
        while (!pq.isEmpty()) { // TC: O(N)
            int[] current = pq.poll();
            if (currentSize + current[0] <= truckSize) {
                currentSize += current[0];
                maxUnits += current[0] * current[1];
            } else {
                double remainingBoxes = truckSize - currentSize;
                double fracUnits = current[1] * remainingBoxes;
                maxUnits += fracUnits;
                break;
            }
        }
        return maxUnits;
    }
}
