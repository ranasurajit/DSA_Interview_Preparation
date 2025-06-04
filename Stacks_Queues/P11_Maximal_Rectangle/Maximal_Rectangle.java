package Stacks_Queues.P11_Maximal_Rectangle;

import java.util.Stack;

public class Maximal_Rectangle {
    public static void main(String[] args) {
        Maximal_Rectangle solution = new Maximal_Rectangle();

        char[][] matrix = {
                { '1', '0', '1', '0', '0' },
                { '1', '0', '1', '1', '1' },
                { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' }
        };
        int maximumRectangleArea = solution.maximalRectangle(matrix);
        System.out.println(maximumRectangleArea);
    }

    /**
     * Approach : Using Monotonic Stack Approach
     *
     * TC: O(2 x M x N) ~ O(M x N)
     * SC: O(M x N + N) ~ O(M x N)
     */
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] height = new int[cols]; // SC: O(N)
        int maxArea = 0;
        for (int i = 0; i < rows; i++) { // TC: O(M)
            for (int j = 0; j < cols; j++) { // TC: O(N)
                if (matrix[i][j] == '0') {
                    height[j] = 0;
                } else {
                    height[j] += matrix[i][j] - '0';
                }
            }
            // we have prepared the current 1-D histogram so we need to find the maximum
            // area of it
            int currentMaxArea = getMaximumAreaOfHistogram(height, cols); // TC: O(N), SC: O(N)
            maxArea = Math.max(maxArea, currentMaxArea);
        }
        return maxArea;
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(3 x N) ~ O(N)
     * SC: O(4 x N) ~ O(N)
     */
    private int getMaximumAreaOfHistogram(int[] height, int n) {
        // we will be storing { element, index } in the Stack
        Stack<int[]> st = new Stack<int[]>(); // SC: O(2 x N)
        int[] nsel = nearestSmallerElementIndexLeft(height, n, st); // TC: O(N), SC: O(N)
        // clearing the Stack for next operation
        st.clear();
        int[] nser = nearestSmallerElementIndexRight(height, n, st); // TC: O(N), SC: O(N)
        int maxArea = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            maxArea = Math.max(maxArea, (nser[i] - nsel[i] - 1) * height[i]);
        }
        return maxArea;
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int[] nearestSmallerElementIndexLeft(int[] height, int n, Stack<int[]> st) {
        int[] result = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && st.peek()[0] >= height[i]) {
                st.pop();
            }
            result[i] = st.isEmpty() ? -1 : st.peek()[1];
            st.push(new int[] { height[i], i });
        }
        return result;
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int[] nearestSmallerElementIndexRight(int[] height, int n, Stack<int[]> st) {
        int[] result = new int[n]; // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && st.peek()[0] >= height[i]) {
                st.pop();
            }
            result[i] = st.isEmpty() ? n : st.peek()[1];
            st.push(new int[] { height[i], i });
        }
        return result;
    }
}
