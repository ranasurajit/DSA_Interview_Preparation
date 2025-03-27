package Arrays.Difference_Arrays.P1_Range_Addition;

import java.util.Arrays;

public class Range_Addition {
    public static void main(String[] args) {
        int length = 5;
        int[][] updates = { { 1, 3, 2 }, { 2, 4, 3 }, { 0, 2, -2 } };
        int[] range = rangeAddition(length, updates);
        System.out.println(Arrays.toString(range));
    }

    /**
     * Using Difference Arrays Approach
     * 
     * TC: O(N + Q)
     * SC: O(1)
     * 
     * @param n
     * @param updates
     * @return
     */
    private static int[] rangeAddition(int n, int[][] updates) {
        int[] result = new int[n];
        for (int[] range : updates) { // TC: O(Q)
            int start = range[0];
            int end = range[1];
            int increment = range[2];
            result[start] += increment;
            if (end + 1 < n) {
                result[end + 1] -= increment;
            }
        }
        for (int i = 1; i < n; i++) { // TC: O(N)
            result[i] = result[i - 1] + result[i];
        }
        return result;
    }
}
