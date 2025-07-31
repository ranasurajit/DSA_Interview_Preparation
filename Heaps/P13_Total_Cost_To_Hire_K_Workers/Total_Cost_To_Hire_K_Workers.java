package Heaps.P13_Total_Cost_To_Hire_K_Workers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Total_Cost_To_Hire_K_Workers {
    public static void main(String[] args) {
        Total_Cost_To_Hire_K_Workers solution = new Total_Cost_To_Hire_K_Workers();

        int[] costs1 = { 17, 12, 10, 2, 7, 2, 11, 20, 8 };
        int k1 = 3;
        int candidates1 = 4;

        long totalCost1 = solution.totalCostUsingOneHeap(costs1, k1, candidates1);
        System.out.println(totalCost1);

        int[] costs2 = { 1, 2, 4, 1 };
        int k2 = 3;
        int candidates2 = 3;
        long totalCost2 = solution.totalCostUsingOneHeap(costs2, k2, candidates2);
        System.out.println(totalCost2);
    }

    /**
     * Approach II : Using 2 Min Heaps (PriorityQueues) + Two Pointers Approach
     *
     * TC: O((K + C) x log(C))
     * SC: O(2 x C) ~ O(C)
     *
     * Time Limit Exceeded (133 / 163 testcases passed)
     */
    public long totalCostUsingTwoHeaps(int[] costs, int k, int candidates) {
        int n = costs.length;
        // Using Two Pointers
        int i = 0;
        int j = n - 1;
        int sessions = 0;
        long totalCost = 0L;
        PriorityQueue<Integer> pqLeft = new PriorityQueue<Integer>(); // SC: O(C)
        PriorityQueue<Integer> pqRight = new PriorityQueue<Integer>(); // SC: O(C)
        while (sessions < k) { // TC: O(K)
            while (pqLeft.size() < candidates && i <= j) { // TC: O(C)
                pqLeft.offer(costs[i]); // TC: O(log(C))
                i++;
            }
            while (pqRight.size() < candidates && i <= j) { // TC: O(C)
                pqRight.offer(costs[j]); // TC: O(log(C))
                j--;
            }
            int minLeft = pqLeft.isEmpty() ? Integer.MAX_VALUE : pqLeft.peek();
            int minRight = pqRight.isEmpty() ? Integer.MAX_VALUE : pqRight.peek();
            if (minLeft <= minRight) {
                totalCost += minLeft;
                pqLeft.poll();
            } else {
                totalCost += minRight;
                pqRight.poll();
            }
            sessions++;
        }
        return totalCost;
    }

    /**
     * Approach I : Using Min Heap (PriorityQueues) + Hashing Approach
     *
     * TC: O(N) + O(K x C x log(C))
     * SC: O(N) + O(2 x C) + O(2 x C) ~ O(N + C)
     *
     * Time Limit Exceeded (133 / 163 testcases passed)
     */
    public long totalCostUsingOneHeap(int[] costs, int k, int candidates) {
        int n = costs.length;
        List<int[]> costList = new ArrayList<int[]>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            costList.add(new int[] { costs[i], i });
        }
        long totalCost = 0L;
        while (k > 0) { // TC: O(K)
            /**
             * We need a Min-Heap to store the candidates number of workers
             * from beginning or end of costs array and pick the one with
             * lowest cost in every k sessions
             *
             * we will store { costs[i], i } in the Min-Heap
             */
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> {
                if (p[0] == q[0]) {
                    return p[1] - q[1];
                }
                return p[0] - q[0];
            }); // SC: O(2 x C)
            // int m = Math.min(candidates, costList.size() / 2);
            Set<Integer> set = new HashSet<Integer>(); // SC: O(2 x C)
            for (int i = 0; i < candidates; i++) { // TC: O(C)
                set.add(i);
                if (i < costList.size()) {
                    pq.offer(new int[] { costList.get(i)[0], i }); // TC: O(log(C))
                }
                int endIndex = costList.size() - 1 - i;
                if (endIndex >= 0 && endIndex < costList.size()) {
                    if (!set.contains(endIndex)) {
                        pq.offer(new int[] { costList.get(endIndex)[0], endIndex }); // TC: O(log(C))
                    }
                }
            }
            costList.remove(pq.peek()[1]);
            totalCost += pq.poll()[0];
            k--;
        }
        return totalCost;
    }
}
