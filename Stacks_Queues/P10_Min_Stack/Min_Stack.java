package Stacks_Queues.P10_Min_Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Min_Stack {
    public static void main(String[] args) {
        String[] operations = { "MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin" };
        int[][] params = { {}, { -2 }, { 0 }, { -3 }, {}, {}, {}, {} };

        MinStack minStack = null;
        List<Object> result = new ArrayList<Object>();

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("MinStack")) {
                minStack = new MinStack();
                result.add(null);
            } else if (operation.equals("push")) {
                minStack.push(params[i][0]);
                result.add(null);
            } else if (operation.equals("pop")) {
                minStack.pop();
                result.add(null);
            } else if (operation.equals("top")) {
                result.add(minStack.top());
            } else {
                result.add(minStack.getMin());
            }
        }

        System.out.println(result);
    }

    static class MinStack {

        Stack<Integer> st;
        Stack<Integer> minSt;

        /**
         * TC: O(1)
         * SC: O(2 x N) ~ O(N)
         */
        public MinStack() {
            st = new Stack<Integer>();
            minSt = new Stack<Integer>();
        }

        /**
         * TC: O(1)
         * SC: O(1)
         */
        public void push(int val) {
            st.push(val);
            if (minSt.isEmpty()) {
                minSt.push(val);
            } else {
                if (val < minSt.peek()) {
                    minSt.push(val);
                } else {
                    // pushed the same element from peek as to have previously pushed min value as
                    // history
                    minSt.push(minSt.peek());
                }
            }
        }

        /**
         * TC: O(1)
         * SC: O(1)
         */
        public void pop() {
            st.pop();
            minSt.pop();
        }

        /**
         * TC: O(1)
         * SC: O(1)
         */
        public int top() {
            return st.peek();
        }

        /**
         * TC: O(1)
         * SC: O(1)
         */
        public int getMin() {
            return minSt.peek();
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
}
