package Graphs.Shortest_Path_Algorithms.P1_Dijkstra_Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra_Algorithm {
    public static void main(String[] args) {
        Dijkstra_Algorithm solution = new Dijkstra_Algorithm();

        int[][][] adj = { { { 1, 1 }, { 2, 6 } }, { { 2, 3 }, { 0, 1 } }, { { 1, 3 }, { 0, 6 } } };
        int src = 2;

        ArrayList<ArrayList<iPair>> adjList = new ArrayList<ArrayList<iPair>>();
        for (int[][] item : adj) {
            ArrayList<iPair> items = new ArrayList<iPair>();
            for (int[] pairItem : item) {
                iPair pairs = new iPair(pairItem[0], pairItem[1]);
                items.add(pairs);
            }
            adjList.add(items);
        }

        ArrayList<Integer> minPathDistance = solution.dijkstra(adjList, src);
        System.out.println(minPathDistance);
    }

    /**
     * Dijkstra Algorithm Approach
     * 
     * TC: O((V + E) x log(V) + V) ~ O((V + E) x log(V))
     * SC: O(2 x V) ~ O(V)
     * 
     * @param adj
     * @param src
     * @return
     */
    private ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        ArrayList<Integer> minDistList = new ArrayList<Integer>();
        int n = adj.size();
        int[] minDist = new int[n]; // SC: O(V)
        Arrays.fill(minDist, Integer.MAX_VALUE);
        // distance from src to src is 0
        minDist[src] = 0;
        // Create a PriorityQueue (min-heap) in order of distance
        // SC: O(V)
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]);
        pq.offer(new int[] { 0, src }); // TC: O(log(1))
        while (!pq.isEmpty()) { // TC: O(V)
            int[] current = pq.poll();
            int weight = current[0];
            int u = current[1];
            for (iPair neighbour : adj.get(u)) { // TC: O(E)
                int v = neighbour.first;
                int edgeWeight = neighbour.second;
                if (weight + edgeWeight < minDist[v]) {
                    minDist[v] = weight + edgeWeight;
                    pq.offer(new int[] { weight + edgeWeight, v }); // TC: O(log(V))
                }
            }
        }
        for (int item : minDist) { // TC: O(V)
            minDistList.add(item);
        }
        return minDistList;
    }

    static class iPair {
        int first, second;

        iPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
