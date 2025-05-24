package Hashing.P3_Equal_Row_And_Column_Pairs;

import java.util.HashMap;
import java.util.Map;

public class Equal_Row_And_Column_Pairs {
    public static void main(String[] args) {
        Equal_Row_And_Column_Pairs solution = new Equal_Row_And_Column_Pairs();

        int[][] grid = { { 3, 1, 2, 2 }, { 1, 4, 4, 5 }, { 2, 4, 2, 2 }, { 2, 4, 2, 2 } };
        int pairs = solution.equalPairs(grid);
        System.out.println(pairs);
    }

    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(2 x N ^ 2) ~ O(N ^ 2)
     * SC: O(N ^ 2 + N) ~ O(N ^ 2)
     */
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> map = new HashMap<String, Integer>(); // SC: O(N)
        for (int[] row : grid) { // TC: O(N)
            StringBuilder sb = new StringBuilder(); // SC: O(N)
            for (int col : row) { // TC: O(N)
                sb.append(col);
                sb.append('-');
            }
            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int count = 0;
        for (int j = 0; j < n; j++) { // TC: O(N)
            StringBuilder sb = new StringBuilder(); // SC: O(N)
            for (int i = 0; i < n; i++) { // TC: O(N)
                sb.append(grid[i][j]);
                sb.append('-');
            }
            String key = sb.toString();
            if (map.containsKey(key)) {
                count += map.get(key);
            }
        }
        return count;
    }
}
