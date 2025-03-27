package Stacks.P9_Asteroid_Collision;

import java.util.Arrays;
import java.util.Stack;

public class Asteroid_Collision {
    public static void main(String[] args) {
        Asteroid_Collision solution = new Asteroid_Collision();

        int[] asteroids1 = { 5, 10, -5 };
        int[] result1 = solution.asteroidCollision(asteroids1);
        System.out.println(Arrays.toString(result1));

        int[] asteroids2 = { -2, -1, 1, 2 };
        int[] result2 = solution.asteroidCollision(asteroids2);
        System.out.println(Arrays.toString(result2));
    }

    /**
     * Using Stack Approach
     * 
     * TC: O(N + K), where K is the size of resultant
     * SC: O(N)
     * 
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            while (!st.isEmpty() && st.peek() > 0 && asteroids[i] < 0) {
                int sum = st.peek() + asteroids[i];
                if (sum == 0) {
                    st.pop();
                    asteroids[i] = 0;
                } else if (sum < 0) {
                    st.pop();
                } else {
                    asteroids[i] = 0;
                }
            }
            if (asteroids[i] != 0) {
                st.push(asteroids[i]);
            }
        }
        int k = st.size();
        int[] result = new int[k];
        while (!st.isEmpty()) { // TC: O(K)
            result[--k] = st.pop();
        }
        return result;
    }
}
