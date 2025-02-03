package Stacks.P3_Online_Stock_Span;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Online_Stock_Span {
    public static void main(String[] args) {
        String[] operations = { "StockSpanner", "next", "next", "next", "next", "next", "next", "next", "next", "next",
                "next" };
        int[][] values = { {}, { 28 }, { 14 }, { 28 }, { 35 }, { 46 }, { 53 }, { 66 }, { 80 }, { 87 }, { 88 } };

        List<Object> result = new ArrayList<Object>();

        StockSpanner stockSpanner = null;
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("StockSpanner")) {
                stockSpanner = new StockSpanner();
                result.add(null);
            } else {
                result.add(stockSpanner.next(values[i][0]));
            }
        }
        System.out.println(result);
    }

    /**
     * Using Stack Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    static class StockSpanner {

        private static Stack<int[]> st;

        public StockSpanner() {
            st = new Stack<int[]>();
        }

        /**
         * TC: O(N)
         * SC: O(1)
         * 
         * @param price
         * @return
         */
        public int next(int price) {
            int span = 1;
            while (!st.isEmpty() && price >= st.peek()[0]) { // TC: O(N)
                span += st.pop()[1];
            }
            st.push(new int[] { price, span });
            return span;
        }
    }

    /**
     * Your StockSpanner object will be instantiated and called as such:
     * StockSpanner obj = new StockSpanner();
     * int param_1 = obj.next(price);
     */
}
