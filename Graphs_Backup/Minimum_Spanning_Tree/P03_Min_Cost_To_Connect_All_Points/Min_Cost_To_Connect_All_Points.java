package Graphs_Backup.Minimum_Spanning_Tree.P03_Min_Cost_To_Connect_All_Points;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Min_Cost_To_Connect_All_Points {
    public static void main(String[] args) {
        Min_Cost_To_Connect_All_Points solution = new Min_Cost_To_Connect_All_Points();

        int[][] points = { { 0, 0 }, { 2, 2 }, { 3, 10 }, { 5, 2 }, { 7, 0 } };

        int minimumCostPrims = solution.minCostConnectPointsPrimsAlgorithm(points);
        System.out.println(minimumCostPrims);

        int minimumCostKruskals = solution.minCostConnectPointsKruskalsAlgorithm(points);
        System.out.println(minimumCostKruskals);
    }

    /**
     * Approach II : Using Kruskal's Algorithm for Minimum Spanning Tree
     *
     * TC: O(N ^ 2 + N ^ 2 x log(N ^ 2) + N ^ 2 x α(N)) ~ O(N ^ 2 x log(N))
     * SC: O(N ^ 2 + 2 x N) ~ O(N ^ 2)
     */
    public int minCostConnectPointsKruskalsAlgorithm(int[][] points) {
        int n = points.length;
        int minCost = 0;
        // pre-requisites to perform DSU operation
        int[] parent = new int[n]; // SC: O(N)
        int[] rank = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            parent[i] = i;
            rank[i] = 1;
        }
        // forming the edges
        List<int[]> edges = createEdges(points, n); // TC: O(N ^ 2), SC: O(N ^ 2)
        // sorting the edges in order of edgeWeights
        edges.sort(Comparator.comparingInt(a -> a[2])); // TC: O(E x log(E))
        for (int[] edge : edges) { // TC: O(E)
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            if (find(parent, u) != find(parent, v)) { // TC: O(α(V)), SC: O(V)
                unionByRank(u, v, parent, rank); // TC: O(α(V)), SC: O(1)
                minCost += cost;
            }
        }
        return minCost;
    }

    /**
     * Find by Path Compression (Using Disjoint Set Union) Approach
     *
     * TC: O(α(V))
     * SC: O(V)
     */
    private int find(int[] parent, int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }

    /**
     * Union by Rank (Using Disjoint Set Union) Approach
     *
     * TC: O(α(2 x V)) ~ O(α(V))
     * SC: O(1)
     */
    private void unionByRank(int x, int y, int[] parent, int[] rank) {
        int xParent = find(parent, x); // TC: O(α(V))
        int yParent = find(parent, y); // TC: O(α(V))
        if (xParent == yParent) {
            return;
        }
        if (rank[xParent] > rank[yParent]) {
            // make xParent as parent of yParent
            parent[yParent] = xParent;
        } else if (rank[xParent] < rank[yParent]) {
            // make yParent as parent of xParent
            parent[xParent] = yParent;
        } else {
            // make xParent as parent of yParent
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }

    /**
     * Creating Edges List
     *
     * TC: O(N ^ 2)
     * SC: O(N ^ 2)
     */
    private List<int[]> createEdges(int[][] points, int n) {
        List<int[]> edges = new ArrayList<int[]>();
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                int weight = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                edges.add(new int[] { i, j, weight });
            }
        }
        return edges;
    }

    /**
     * Approach I : Using Prim's Algorithm for Minimum Spanning Tree
     *
     * TC: O(N ^ 2 + N ^ 2 x log(N ^ 2)) ~ O(N ^ 2 x log(N))
     * SC: O(3 x N ^ 2 + N) ~ O(N ^ 2)
     */
    public int minCostConnectPointsPrimsAlgorithm(int[][] points) {
        int n = points.length;
        int minCost = 0;
        Map<Integer, ArrayList<int[]>> adj = createAdjacencyList(points, n); // TC: O(E), SC: O(2 x E)
        boolean[] visited = new boolean[n]; // SC: O(N)
        // we would need a PriorityQueue (Min-Heap) to store edges in order of edgeCosts
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(E)
        pq.offer(new int[] { 0, 0 }); // TC: O(log(E))
        while (!pq.isEmpty()) { // TC: O(E)
            int[] current = pq.poll(); // TC: O(log(E))
            int cost = current[0];
            int u = current[1];
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            minCost += cost;
            for (int[] ngbr : adj.getOrDefault(u, new ArrayList<int[]>())) { // TC: O(E)
                int v = ngbr[0];
                int edgeCost = ngbr[1];
                if (!visited[v]) {
                    pq.offer(new int[] { edgeCost, v }); // TC: O(log(E))
                }
            }
        }
        return minCost;
    }

    /**
     * Creating AdjacencyList
     *
     * TC: O(N ^ 2)
     * SC: O(2 x N ^ 2)
     */
    private Map<Integer, ArrayList<int[]>> createAdjacencyList(int[][] points, int n) {
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
