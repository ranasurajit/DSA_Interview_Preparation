package DP.KnapsackDP.P1_Zero_One_Knapsack_Problem;

public class Zero_One_Knapsack_Problem {
    public static void main(String[] args) {
        int capacity = 7;
        int val[] = { 10, 8, 6 };
        int wt[] = { 1, 7, 9 };
        int maximumValueRecursion = knapSackRecursion(capacity, val, wt);
        System.out.println(maximumValueRecursion);

        int maximumValueMemoization = knapSackMemoization(capacity, val, wt);
        System.out.println(maximumValueMemoization);

        int maximumValueTabulation = knapSackTabulation(capacity, val, wt);
        System.out.println(maximumValueTabulation);
    }

    /**
     * Using Tabulation
     * 
     * TC: O(N x W)
     * SC: O(N x W)
     */
    private static int knapSackTabulation(int capacity, int val[], int wt[]) {
        int n = wt.length;
        // Initialization - replicating Base case below
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }
        // Replicating Iteration of below Recursion call and interchange (n, maxWeight)
        // as (i, j)
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                if (wt[i - 1] <= j) {
                    int pick = val[i - 1] + dp[i - 1][j - wt[i - 1]];
                    int notpick = dp[i - 1][j];
                    dp[i][j] = Math.max(pick, notpick);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][capacity];
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x W)
     * SC: O(N x W + N)
     */
    private static int knapSackMemoization(int capacity, int val[], int wt[]) {
        int n = wt.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return solveMemoization(wt, val, n, capacity, dp);
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x W)
     * SC: O(N x W + N)
     */
    private static int solveMemoization(int[] wt, int[] val, int n, int w, int[][] dp) {
        // Base case
        if (n == 0 || w == 0) {
            return 0;
        }
        if (dp[n][w] != -1) {
            return dp[n][w];
        }
        // Recursion call
        if (wt[n - 1] <= w) {
            // here we can decide to include or exclude the (n - 1)th item
            int pick = val[n - 1] + solveMemoization(wt, val, n - 1, w - wt[n - 1], dp);
            int notpick = solveMemoization(wt, val, n - 1, w, dp);
            return dp[n][w] = Math.max(pick, notpick);
        } else {
            // we cannot include (n - 1)th item as it exceeds maxWeight/capacity of knapsack
            return dp[n][w] = solveMemoization(wt, val, n - 1, w, dp);
        }
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     */
    private static int knapSackRecursion(int capacity, int val[], int wt[]) {
        int n = wt.length;
        return solveRecursion(wt, val, n, capacity);
    }

    /**
     * Using Recursion
     * 
     * TC: O(2 ^ N)
     * SC: O(2 ^ N)
     */
    private static int solveRecursion(int[] wt, int[] val, int n, int w) {
        // Base case
        if (n == 0 || w == 0) {
            return 0;
        }
        // Recursion call
        if (wt[n - 1] <= w) {
            // here we can decide to include or exclude the (n - 1)th item
            int pick = val[n - 1] + solveRecursion(wt, val, n - 1, w - wt[n - 1]);
            int notpick = solveRecursion(wt, val, n - 1, w);
            return Math.max(pick, notpick);
        } else {
            // we cannot include (n - 1)th item as it exceeds maxWeight/capacity of knapsack
            return solveRecursion(wt, val, n - 1, w);
        }
    }
}
