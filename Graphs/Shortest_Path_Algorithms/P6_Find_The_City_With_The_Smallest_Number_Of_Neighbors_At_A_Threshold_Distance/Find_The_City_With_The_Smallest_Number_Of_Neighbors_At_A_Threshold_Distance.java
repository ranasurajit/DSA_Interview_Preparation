package Graphs.Shortest_Path_Algorithms.P6_Find_The_City_With_The_Smallest_Number_Of_Neighbors_At_A_Threshold_Distance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Find_The_City_With_The_Smallest_Number_Of_Neighbors_At_A_Threshold_Distance {
    public static void main(String[] args) {
        Find_The_City_With_The_Smallest_Number_Of_Neighbors_At_A_Threshold_Distance solution = new Find_The_City_With_The_Smallest_Number_Of_Neighbors_At_A_Threshold_Distance();

        int n1 = 4;
        int[][] edges1 = { { 0, 1, 3 }, { 1, 2, 1 }, { 1, 3, 4 }, { 2, 3, 1 } };
        int distanceThreshold1 = 4;
        int cityWithLeastConnections1 = solution.findTheCityFloydWarshall(n1, edges1, distanceThreshold1);
        System.out.println(cityWithLeastConnections1);

        int n2 = 5;
        int[][] edges2 = { { 0, 1, 2 }, { 0, 4, 8 }, { 1, 2, 3 }, { 1, 4, 2 }, { 2, 3, 1 }, { 3, 4, 1 } };
        int distanceThreshold2 = 2;
        int cityWithLeastConnections2 = solution.findTheCityDijkstrasAlgorithm(n2, edges2, distanceThreshold2);
        System.out.println(cityWithLeastConnections2);
    }

    /**
     * Approach II : Using Dijkstra's Algorithm Approach
     * 
     * TC: O(E) + O(V ^ 2 x E x log(V)) + O(V) ~ O(V ^ 2 x E x log(V))
     * SC: O(V + E)
     */
    public int findTheCityDijkstrasAlgorithm(int n, int[][] edges, int distanceThreshold) {
        Map<Integer, ArrayList<int[]>> adj = createGraph(edges); // TC: O(E), SC: O(V + E)
        int[] connections = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(V)
            int[] dist = dijktrasDistance(i, n, adj); // TC: O(E x log(V))
            for (int j = 0; j < n; j++) { // TC: O(V)
                if (i != j && dist[j] <= distanceThreshold) {
                    connections[i]++;
                }
            }
        }
        int minConnection = n;
        int result = -1;
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (minConnection >= connections[i]) {
                minConnection = connections[i];
                result = i;
            }
        }
        return result;
    }

    /**
     * Using Dijkstra's Algorithm Approach
     * 
     * TC: O(E x log(V))
     * SC: O(V + E)
     */
    private int[] dijktrasDistance(int src, int n, Map<Integer, ArrayList<int[]>> adj) {
        int[] minDist = new int[n]; // SC: O(V)
        Arrays.fill(minDist, (int) 1e8);
        minDist[src] = 0;
        // we will store { weight, node } in the Min-Heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        pq.offer(new int[] { 0, src });
        while (!pq.isEmpty()) { // TC: O(E)
            int[] current = pq.poll(); // TC: O(log(V))
            int weight = current[0];
            int u = current[1];
            if (weight > minDist[u]) {
                continue;
            }
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) {
                int v = ngbr[0];
                int edgeWeight = ngbr[1];
                if (weight + edgeWeight < minDist[v]) {
                    minDist[v] = weight + edgeWeight;
                    pq.offer(new int[] { weight + edgeWeight, v }); // TC: O(log(V))
                }
            }
        }
        return minDist;
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(E)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<int[]>> createGraph(int[][] edges) {
        Map<Integer, ArrayList<int[]>> adj = new HashMap<Integer, ArrayList<int[]>>();
        for (int[] edge : edges) { // TC: O(E)
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            adj.computeIfAbsent(u, k -> new ArrayList<int[]>()).add(new int[] { v, weight });
            adj.computeIfAbsent(v, k -> new ArrayList<int[]>()).add(new int[] { u, weight });
        }
        return adj;
    }

    /**
     * Approach I : Using Floyd-Warshall Algorithm Approach
     *
     * TC: O(V) + O(E) + O(V ^ 3) + O(V ^ 2) + O(V) ~ O(V ^ 3)
     * SC: O(V ^ 2)
     */
    public int findTheCityFloydWarshall(int n, int[][] edges, int distanceThreshold) {
        int[][] graph = new int[n][n]; // SC: O(V x V)
        for (int i = 0; i < n; i++) { // TC: O(V)
            Arrays.fill(graph[i], (int) 1e8);
            graph[i][i] = 0;
        }
        for (int[] edge : edges) { // TC: O(E)
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            graph[u][v] = wt;
            graph[v][u] = wt;
        }
        // Applying Floyd Warshall Algorithm
        for (int via = 0; via < n; via++) { // TC: O(V)
            for (int i = 0; i < n; i++) { // TC: O(V)
                for (int j = 0; j < n; j++) { // TC: O(V)
                    graph[i][j] = Math.min(graph[i][j], graph[i][via] + graph[via][j]);
                }
            }
        }
        int[] connections = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(V)
            for (int j = 0; j < n; j++) { // TC: O(V)
                if (i != j && graph[i][j] <= distanceThreshold) {
                    connections[i]++;
                }
            }
        }
        int minConnection = n;
        int result = -1;
        for (int i = 0; i < n; i++) { // TC: O(V)
            if (minConnection >= connections[i]) {
                minConnection = connections[i];
                result = i;
            }
        }
        return result;
    }
}
