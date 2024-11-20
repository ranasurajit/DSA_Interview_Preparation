package DP.DP_Stocks.P1_Best_Time_To_Buy_And_Sell_Stock_Once;

public class Best_Time_To_Buy_And_Sell_Stock_Once {
    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int max = maxProfit(prices);
        System.out.println(max);
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int max = 0;
        int minimum = prices[0];
        for (int i = 1; i < n; i++) { // TC: O(N)
            int currentProfit = prices[i] - minimum;
            max = Math.max(max, currentProfit);
            // tracking minimum till previous of index i
            minimum = Math.min(minimum, prices[i]);
        }
        return max;
    }
}
