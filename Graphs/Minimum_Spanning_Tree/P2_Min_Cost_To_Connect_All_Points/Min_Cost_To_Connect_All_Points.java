package Graphs.Minimum_Spanning_Tree.P2_Min_Cost_To_Connect_All_Points;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Min_Cost_To_Connect_All_Points {
    public static void main(String[] args) {
        Min_Cost_To_Connect_All_Points solution = new Min_Cost_To_Connect_All_Points();

        int[][] points1 = { { 0, 0 }, { 2, 2 }, { 3, 10 }, { 5, 2 }, { 7, 0 } };
        int minCost1 = solution.minCostConnectPoints(points1);
        System.out.println(minCost1);

        int[][] points2 = { { 3, 12 }, { -2, 5 }, { -4, 1 } };
        int minCost2 = solution.minCostConnectPoints(points2);
        System.out.println(minCost2);
    }

    /**
     * Approach : Using Prim's Algorithm Approach
     * 
     * TC: O(N ^ 2) + O(E x log(E)) ~ O(N ^ 2 + N ^ 2 x log(N ^ 2)) ~ O(N ^ 2 x
     * log(N))
     * SC: O(N ^ 2) + O(N) + O(N ^ 2) ~ O(N ^ 2)
     *
     * Here E = N ^ 2
     */
    public int minCostConnectPoints(int[][] points) {
        Map<Integer, ArrayList<int[]>> adj = createGraph(points); // TC: O(N ^ 2), SC: O(N ^ 2)
        int V = adj.size();
        boolean[] inMST = new boolean[V]; // SC: O(N)
        // we need to store { weights, node } to Min-Heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        for (int i = 0; i < V; i++) { // TC: O(1) - will run only once
            if (!inMST[i]) {
                pq.offer(new int[] { 0, i });
                break;
            }
        }
        int cost = 0;
        while (!pq.isEmpty()) { // TC: O(E)
            int[] current = pq.poll();
            int weight = current[0];
            int u = current[1];
            if (inMST[u]) {
                continue;
            }
            inMST[u] = true;
            cost += weight;
            for (int[] ngbr : adj.get(u)) { // TC: O(E)
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (!inMST[v]) {
                    pq.offer(new int[] { edgeWeight, v }); // TC: O(log(E))
                }
            }
        }
        return cost;
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(N x N)
     * SC: O(2 x N x N)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] points) {
        int n = points.length;
        Map<Integer, ArrayList<int[]>> adj = new HashMap<Integer, ArrayList<int[]>>();
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                int weight = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                adj.computeIfAbsent(i, k -> new ArrayList<int[]>()).add(new int[] { j, weight });
                adj.computeIfAbsent(j, k -> new ArrayList<int[]>()).add(new int[] { i, weight });
            }
        }
        return adj;
    }
}
