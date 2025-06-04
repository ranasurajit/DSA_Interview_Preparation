package Stacks_Queues.Stacks.P8_Histogram_Max_Rectangular_Area;

import java.util.Stack;

public class Histogram_Max_Rectangular_Area {
    public static void main(String[] args) {
        Histogram_Max_Rectangular_Area solution = new Histogram_Max_Rectangular_Area();

        int[] arr = { 60, 20, 50, 40, 10, 50, 60 };
        int maxArea = solution.getMaxArea(arr);
        System.out.println(maxArea);
    }

    /**
     * Approach : Using Monotonic Stack Approach
     * 
     * At every height, we need to check how much the height can span
     * (can span only if height is same or more in its left and right side
     * bars)
     * 
     * so maximum area = difference (nearest small element(right - left - 1) *
     * height[i])
     * 
     * TC: O(3 x N) ~ O(N)
     * SC: O(4 x N) ~ O(N)
     */
    public int getMaxArea(int arr[]) {
        int n = arr.length;
        Stack<int[]> st = new Stack<int[]>(); // TC: O(2 x N)
        int[] nsel = nearestSmallerElementIndexToLeft(arr, n, st); // TC: O(N), SC: O(N)
        st.clear();
        int[] nser = nearestSmallerElementIndexToRight(arr, n, st); // TC: O(N), SC: O(N)
        int maxArea = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            maxArea = Math.max(maxArea, (nser[i] - nsel[i] - 1) * arr[i]);
        }
        return maxArea;
    }

    /**
     * Using Stack (Nearest Smaller Element to Left) Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int[] nearestSmallerElementIndexToLeft(int[] arr, int n, Stack<int[]> st) {
        int[] nse = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && st.peek()[0] >= arr[i]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? -1 : st.peek()[1];
            st.push(new int[] { arr[i], i });
        }
        return nse;
    }

    /**
     * Using Stack (Nearest Smaller Element to Right) Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int[] nearestSmallerElementIndexToRight(int[] arr, int n, Stack<int[]> st) {
        int[] nse = new int[n]; // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && st.peek()[0] >= arr[i]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek()[1];
            st.push(new int[] { arr[i], i });
        }
        return nse;
    }
}
