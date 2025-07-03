package Dynamic_Programming.DP_Stocks.P1_Best_Time_To_Buy_And_Sell_Stock;

public class Best_Time_To_Buy_And_Sell_Stock {
    public static void main(String[] args) {
        Best_Time_To_Buy_And_Sell_Stock solution = new Best_Time_To_Buy_And_Sell_Stock();

        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int maximumProfit = solution.maxProfit(prices);
        System.out.println(maximumProfit);
    }

    /**
     * Approach : Using Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int maxProfit(int[] prices) {
        int maxP = 0;
        int minValue = prices[0];
        for (int i = 1; i < prices.length; i++) { // TC: O(N)
            minValue = Math.min(minValue, prices[i - 1]);
            maxP = Math.max(maxP, prices[i] - minValue);
        }
        return maxP;
    }
}
