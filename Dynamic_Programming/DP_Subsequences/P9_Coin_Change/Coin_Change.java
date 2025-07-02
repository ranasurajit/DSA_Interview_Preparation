package Dynamic_Programming.DP_Subsequences.P9_Coin_Change;

import java.util.Arrays;

public class Coin_Change {
    public static void main(String[] args) {
        Coin_Change solution = new Coin_Change();

        int[] coins = { 1, 2, 5 };
        int amount = 11;

        int minCoinsRecursion = solution.coinChangeRecursion(coins, amount);
        System.out.println(minCoinsRecursion);

        int minCoinsMemoization = solution.coinChangeMemoization(coins, amount);
        System.out.println(minCoinsMemoization);

        int minCoinsTabulation = solution.coinChangeTabulation(coins, amount);
        System.out.println(minCoinsTabulation);

        int minCoinsOptimization = solution.coinChangeSpaceOptimization(coins, amount);
        System.out.println(minCoinsOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x T) + O(T)
     * SC: O(T) + O(T)
     * 
     * - O(T) - prev and current array memory
     * 
     * Accepted (189 / 189 testcases passed)
     */
    public int coinChangeSpaceOptimization(int[] coins, int amount) {
        int n = coins.length;
        // Initialization
        int[] prev = new int[amount + 1]; // SC: O(T)
        prev[0] = 0;
        for (int j = 1; j < amount + 1; j++) { // TC: O(T)
            prev[j] = j % coins[0] == 0 ? (j / coins[0]) : (int) 1e9;
        }
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            int[] current = new int[amount + 1]; // SC: O(T)
            current[0] = 0;
            for (int j = 0; j < amount + 1; j++) { // TC: O(T)
                if (j == 0) {
                    current[j] = 0;
                    continue;
                }
                int skip = prev[j];
                int pick = (int) 1e9;
                if (coins[i - 1] <= j) {
                    pick = 1 + current[j - coins[i - 1]];
                }
                current[j] = Math.min(pick, skip);
            }
            prev = current.clone();
        }
        int result = prev[amount];
        return result == (int) 1e9 ? -1 : result;
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x T) + O(T)
     * SC: O(N x T)
     * 
     * - O(N x T) - dp array memory
     * 
     * Accepted (189 / 189 testcases passed)
     */
    public int coinChangeTabulation(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1]; // SC: O(N x T)
        // Initialization
        dp[0][0] = 0;
        for (int j = 1; j < amount + 1; j++) { // TC: O(T)
            dp[0][j] = j % coins[0] == 0 ? (j / coins[0]) : (int) 1e9;
        }
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 0; j < amount + 1; j++) { // TC: O(T)
                if (j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                int skip = dp[i - 1][j];
                int pick = (int) 1e9;
                if (coins[i - 1] <= j) {
                    pick = 1 + dp[i][j - coins[i - 1]];
                }
                dp[i][j] = Math.min(pick, skip);
            }
        }
        int result = dp[n][amount];
        return result == (int) 1e9 ? -1 : result;
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x T) + O(N x T)
     * SC: O(N x T) + O(T)
     * 
     * - O(N x T) - memoization array memory
     * - O(N) - recursion stack
     * 
     * Accepted (189 / 189 testcases passed)
     */
    public int coinChangeMemoization(int[] coins, int amount) {
        int n = coins.length;
        int[][] memo = new int[n + 1][amount + 1]; // SC: O(N x T)
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(T)
        }
        int minCoins = solveMemoization(n - 1, coins, amount, memo); // TC: O(N x T), SC: O(T)
        return minCoins >= (int) 1e9 ? -1 : minCoins;
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x T)
     * SC: O(T)
     */
    private int solveMemoization(int idx, int[] coins, int amount, int[][] memo) {
        // Base Case
        if (idx == 0) {
            if (amount > 0 && amount % coins[idx] == 0) {
                return amount / coins[idx];
            } else if (amount == 0) {
                return 0;
            }
            return (int) 1e9;
        }
        // Memoization Check
        if (memo[idx][amount] != -1) {
            return memo[idx][amount];
        }
        // Recursion Calls
        int skip = solveMemoization(idx - 1, coins, amount, memo);
        int pick = (int) 1e9;
        if (coins[idx] <= amount) {
            // 1 coin picked
            pick = 1 + solveMemoization(idx, coins, amount - coins[idx], memo);
        }
        return memo[idx][amount] = Math.min(pick, skip);
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: >> O(2 ^ N)
     * SC: >> O(N) ~ O(T)
     * 
     * - O(N) - recursion stack
     * 
     * Time Limit Exceeded (99 / 189 testcases passed)
     */
    public int coinChangeRecursion(int[] coins, int amount) {
        int n = coins.length;
        int minCoins = solveRecursion(n - 1, coins, amount);
        return minCoins >= (int) 1e9 ? -1 : minCoins;
    }

    /**
     * Using Recursion Approach
     * 
     * TC: >> O(2 ^ N)
     * SC: >> O(N) ~ O(T)
     */
    private int solveRecursion(int idx, int[] coins, int amount) {
        // Base Case
        if (idx == 0) {
            if (amount > 0 && amount % coins[idx] == 0) {
                return amount / coins[idx];
            } else if (amount == 0) {
                return 0;
            }
            return (int) 1e9;
        }
        // Recursion Calls
        int skip = solveRecursion(idx - 1, coins, amount);
        int pick = (int) 1e9;
        if (coins[idx] <= amount) {
            // 1 coin picked
            pick = 1 + solveRecursion(idx, coins, amount - coins[idx]);
        }
        return Math.min(pick, skip);
    }
}
