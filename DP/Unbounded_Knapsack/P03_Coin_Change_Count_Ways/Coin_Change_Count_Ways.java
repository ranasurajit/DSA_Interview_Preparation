package DP.Unbounded_Knapsack.P03_Coin_Change_Count_Ways;

import java.util.Arrays;

public class Coin_Change_Count_Ways {
    public static void main(String[] args) {
        Coin_Change_Count_Ways solution = new Coin_Change_Count_Ways();

        int[] coins = { 2, 5, 3, 6 };
        int sum = 10;

        int maximumValueRecursion = solution.countRecursion(coins, sum);
        System.out.println(maximumValueRecursion);

        int maximumValueMemoization = solution.countMemoization(coins, sum);
        System.out.println(maximumValueMemoization);

        int maximumValueTabulation = solution.countTabulation(coins, sum);
        System.out.println(maximumValueTabulation);

        int maximumValueOptimization = solution.countOptimization(coins, sum);
        System.out.println(maximumValueOptimization);
    }

    /**
     * Approach IV : Using Space Optimization Approach
     * 
     * TC: O(N x S)
     * SC: O(2 x S) ~ O(S)
     * 
     * where S = sum
     */
    public int countOptimization(int coins[], int sum) {
        int n = coins.length;
        // Initialization
        int[] prev = new int[sum + 1];
        prev[0] = 1;
        // Iterative Calls - convert dp[i] as current and dp[i - 1] as prev
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            int[] current = new int[sum + 1];
            current[0] = 1;
            for (int j = 1; j < sum + 1; j++) { // TC: O(S)
                if (coins[i - 1] <= j) {
                    current[j] = current[j - coins[i - 1]] + prev[j];
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[sum];
    }

    /**
     * Approach III : Using Tabulation Approach
     * 
     * TC: O((N + S) + N x S) ~ O(N x S)
     * SC: O(N x S)
     * 
     * where S = sum
     */
    public int countTabulation(int coins[], int sum) {
        int n = coins.length;
        // Initialization
        int[][] dp = new int[n + 1][sum + 1]; // SC: O(N x S)
        for (int j = 1; j < sum + 1; j++) { // TC: O(S)
            dp[0][j] = 0;
        }
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            dp[i][0] = 1;
        }
        // Iterative Calls - convert (n, sum) to (i, j)
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 1; j < sum + 1; j++) { // TC: O(S)
                if (coins[i - 1] <= j) {
                    dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * Approach II : Using Memoization Approach
     * 
     * TC: O(N x S)
     * SC: O(N x S + N)
     * 
     * where S = sum
     */
    public int countMemoization(int coins[], int sum) {
        int n = coins.length;
        int[][] memo = new int[n + 1][sum + 1]; // SC: O(N x S)
        for (int[] memoItem : memo) {
            Arrays.fill(memoItem, -1);
        }
        return solveMemoization(n, coins, sum, memo);
    }

    /**
     * Using Memoization
     * 
     * This problem is similar to unbounded knapsack problem
     * 
     * TC: O(N x S)
     * SC: O(N)
     */
    private int solveMemoization(int n, int[] coins, int sum, int[][] memo) {
        // Base Case
        if (sum == 0 && n == 0) {
            return 1;
        }
        if (sum == 0 && n != 0) {
            return 1;
        }
        if (sum != 0 && n == 0) {
            return 0;
        }
        // Memoization Check
        if (memo[n][sum] != -1) {
            return memo[n][sum];
        }
        // Recursion Calls
        if (coins[n - 1] <= sum) {
            // we have two choices - pick and not pick
            int pick = solveMemoization(n, coins, sum - coins[n - 1], memo); // infinite supply
            int notpick = solveMemoization(n - 1, coins, sum, memo);
            return memo[n][sum] = pick + notpick;
        } else {
            // we don't have an option to pick here
            return memo[n][sum] = solveMemoization(n - 1, coins, sum, memo);
        }
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    public int countRecursion(int coins[], int sum) {
        int n = coins.length;
        return solveRecursion(n, coins, sum);
    }

    /**
     * Using Recursion
     * 
     * This problem is similar to unbounded knapsack problem
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int n, int[] coins, int sum) {
        // Base Case
        if (sum == 0 && n == 0) {
            return 1;
        }
        if (sum == 0 && n != 0) {
            return 1;
        }
        if (sum != 0 && n == 0) {
            return 0;
        }
        // Recursion Calls
        if (coins[n - 1] <= sum) {
            // we have two choices - pick and not pick
            int pick = solveRecursion(n, coins, sum - coins[n - 1]); // infinite supply
            int notpick = solveRecursion(n - 1, coins, sum);
            return pick + notpick;
        } else {
            // we don't have an option to pick here
            return solveRecursion(n - 1, coins, sum);
        }
    }
}
