package Graphs.DFS_BFS.P7_Cheapest_Flights_Within_K_Stops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Cheapest_Flights_Within_K_Stops {
    public static void main(String[] args) {
        Cheapest_Flights_Within_K_Stops solution = new Cheapest_Flights_Within_K_Stops();

        int n1 = 4;
        int[][] flights1 = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 } };
        int src1 = 0;
        int dst1 = 3;
        int k1 = 1;
        int cheapestPrice1 = solution.findCheapestPrice(n1, flights1, src1, dst1, k1);
        System.out.println(cheapestPrice1);

        int n2 = 3;
        int[][] flights2 = { { 0, 1, 100 }, { 1, 2, 100 }, { 0, 2, 500 } };
        int src2 = 0;
        int dst2 = 2;
        int k2 = 0;
        int cheapestPrice2 = solution.findCheapestPrice(n2, flights2, src2, dst2, k2);
        System.out.println(cheapestPrice2);
    }

    /**
     * Approach : Using BFS Traversal Approach
     *
     * TC: O(E) + O(V + E) ~ O(V + E)
     * SC: O(V + E) + O(V) + O(V) ~ O(V + E)
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, ArrayList<int[]>> adj = createGraph(flights); // TC: O(E), SC: O(V + E)
        // we need to find the cheapest (shortest) price with a constraint so we can
        // proceed with BFS
        // also we need a minCost array to track the minimum cost for every node
        int[] minCost = new int[n]; // SC: O(V)
        Arrays.fill(minCost, (int) 1e8);
        minCost[src] = 0; // cost from src -> src = 0
        // we will be storing { node, cost } in the Queue
        Queue<int[]> queue = new LinkedList<int[]>(); // SC: O(V)
        queue.offer(new int[] { src, 0 });
        int level = 0;
        while (!queue.isEmpty() && level <= k) { // TC: O(V)
            int size = queue.size();
            while (size-- > 0) {
                int[] current = queue.poll();
                int u = current[0];
                int cost = current[1];
                for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(E)
                    int v = ngbr[0];
                    int edgeCost = ngbr[1];
                    if (cost + edgeCost < minCost[v]) {
                        minCost[v] = cost + edgeCost;
                        queue.offer(new int[] { v, cost + edgeCost });
                    }
                }
            }
            level++;
        }
        return minCost[dst] == (int) 1e8 ? -1 : minCost[dst];
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] flights) {
        Map<Integer, ArrayList<int[]>> adj = new HashMap<Integer, ArrayList<int[]>>(); // SC: O(V + E)
        for (int[] edge : flights) { // TC: O(E)
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            adj.computeIfAbsent(u, k -> new ArrayList<int[]>()).add(new int[] { v, weight });
        }
        return adj;
    }
}
