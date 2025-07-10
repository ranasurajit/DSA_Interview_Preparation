package Graphs.Utils;

import java.util.ArrayList;

public class GraphUtils {
    /**
     * Converts a 2D array of neighbors to an adjacency list.
     *
     * @param input 2D array where input[i] contains all nodes adjacent to i
     * @return Adjacency list as ArrayList<ArrayList<Integer>>
     */
    public static ArrayList<ArrayList<Integer>> buildAdjList(int[][] input) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            ArrayList<Integer> neighbors = new ArrayList<>();
            for (int neighbor : input[i]) {
                neighbors.add(neighbor);
            }
            adjList.add(neighbors);
        }

        return adjList;
    }
}
