package Stacks.P10_Get_Min_From_Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Get_Min_From_Stack {
    public static void main(String[] args) {
        int q = 7;
        int[][] queries = { { 1, 2 }, { 1, 3 }, { 3 }, { 2 }, { 4 }, { 1, 1 }, { 4 } };

        Solution solution = new Solution();
        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < q; i++) {
            int[] query = queries[i];
            if (query[0] == 1) {
                solution.push(query[1]);
            } else if (query[0] == 2) {
                solution.pop();
            } else if (query[0] == 3) {
                result.add(solution.peek());
            } else if (query[0] == 4) {
                result.add(solution.getMin());
            }
        }

        System.out.println(result);
    }

    private static class Solution {

        private final Stack<Integer> st;
        private int minValue;

        /**
         * TC: O(1)
         * SC: O(1)
         */
        public Solution() {
            st = new Stack<Integer>();
            minValue = Integer.MAX_VALUE;
        }

        // Add an element to the top of Stack
        /**
         * TC: O(1)
         * SC: O(1)
         * 
         * @param x
         */
        public void push(int x) {
            if (st.isEmpty()) {
                minValue = x;
            } else {
                if (x < minValue) {
                    st.push(minValue);
                    minValue = x;
                }
            }
            st.push(x);
        }

        // Remove the top element from the Stack
        /**
         * TC: O(1)
         * SC: O(1)
         */
        public void pop() {
            if (st.isEmpty()) {
                return;
            }
            int peek = st.peek();
            st.pop();
            if (minValue == peek && !st.isEmpty()) {
                minValue = st.pop();
            }
        }

        // Returns top element of the Stack
        /**
         * TC: O(1)
         * SC: O(1)
         * 
         * @return
         */
        public int peek() {
            if (st.isEmpty()) {
                return -1;
            }
            return st.peek();
        }

        // Finds minimum element of Stack
        /**
         * TC: O(1)
         * SC: O(1)
         * 
         * @return
         */
        public int getMin() {
            if (st.isEmpty()) {
                return -1;
            }
            return minValue;
        }
    }
}
