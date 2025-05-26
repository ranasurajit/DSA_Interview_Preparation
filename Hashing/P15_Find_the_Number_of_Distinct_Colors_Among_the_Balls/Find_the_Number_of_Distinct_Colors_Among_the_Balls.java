package Hashing.P15_Find_the_Number_of_Distinct_Colors_Among_the_Balls;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Find_the_Number_of_Distinct_Colors_Among_the_Balls {
    public static void main(String[] args) {
        Find_the_Number_of_Distinct_Colors_Among_the_Balls solution = new Find_the_Number_of_Distinct_Colors_Among_the_Balls();

        int limit = 4;
        int[][] queries = { { 0, 1 }, { 1, 2 }, { 2, 2 }, { 3, 4 }, { 4, 5 } };

        int[] result = solution.queryResults(limit, queries);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Approach : Using Hashing (2 HashMaps) Approach
     * 
     * TC: O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public int[] queryResults(int limit, int[][] queries) {
        int n = queries.length;
        // we will store Map with data { ballIndex, color }
        Map<Integer, Integer> ballMap = new HashMap<Integer, Integer>(); // SC: O(N)
        Map<Integer, Integer> colorMap = new HashMap<Integer, Integer>(); // SC: O(N)
        int[] result = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(N)
            int[] query = queries[i];
            if (ballMap.containsKey(query[0])) {
                int color = ballMap.get(query[0]);
                int freq = colorMap.get(color);
                if (freq == 1) {
                    colorMap.remove(color);
                } else {
                    colorMap.put(color, freq - 1);
                }
            }
            ballMap.put(query[0], query[1]); // marking ball (query[0]) with color (query[1])
            colorMap.put(query[1], colorMap.getOrDefault(query[1], 0) + 1);
            /**
             * The minimum value of both Map and Set will decide cancel out two scanarios
             * 1. If same ball is repainted with a color then colro count will
             * increase but distinct colors remain the same
             * 2. If same color is used to paint multiple balls, then we need only distinct
             * colors
             */
            result[i] = colorMap.size();
        }
        return result;
    }
}
