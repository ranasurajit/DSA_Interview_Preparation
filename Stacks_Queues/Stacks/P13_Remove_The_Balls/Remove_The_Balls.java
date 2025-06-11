package Stacks_Queues.Stacks.P13_Remove_The_Balls;

import java.util.Stack;

public class Remove_The_Balls {
    public static void main(String[] args) {
        Remove_The_Balls solution = new Remove_The_Balls();

        int[] color1 = { 2, 3, 5 };
        int[] radius1 = { 3, 3, 5 };
        int remainingBalls1 = solution.findLength(color1, radius1);
        System.out.println(remainingBalls1);

        int[] color2 = { 1, 2, 2, 1 };
        int[] radius2 = { 1, 3, 3, 1 };
        int remainingBalls2 = solution.findLength(color2, radius2);
        System.out.println(remainingBalls2);
    }

    /**
     * Approach : Using Stack Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public int findLength(int[] color, int[] radius) {
        int n = color.length;
        Stack<int[]> st = new Stack<int[]>(); // TC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && st.peek()[0] == color[i] && st.peek()[1] == radius[i]) {
                st.pop();
                color[i] = 0;
                radius[i] = 0;
            }
            if (color[i] != 0 && radius[i] != 0) {
                st.push(new int[] { color[i], radius[i] });
            }
        }
        return st.size();
    }
}
