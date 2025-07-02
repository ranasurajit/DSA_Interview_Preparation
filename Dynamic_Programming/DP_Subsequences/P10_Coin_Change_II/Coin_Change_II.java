package Dynamic_Programming.DP_Subsequences.P10_Coin_Change_II;

import java.util.Arrays;

public class Coin_Change_II {
    public static void main(String[] args) {
        Coin_Change_II solution = new Coin_Change_II();

        int amount = 5;
        int[] coins = { 1, 2, 5 };

        int minCoinsRecursion = solution.changeRecursion(amount, coins);
        System.out.println(minCoinsRecursion);

        int minCoinsMemoization = solution.changeMemoization(amount, coins);
        System.out.println(minCoinsMemoization);

        int minCoinsTabulation = solution.changeTabulation(amount, coins);
        System.out.println(minCoinsTabulation);

        int minCoinsOptimization = solution.changeSpaceOptimization(amount, coins);
        System.out.println(minCoinsOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x T)
     * SC: O(T) + O(T)
     * 
     * - O(T) - prev and current array memory
     * 
     * Accepted (30 / 30 testcases passed)
     */
    public int changeSpaceOptimization(int amount, int[] coins) {
        int n = coins.length;
        int[] prev = new int[amount + 1]; // SC: O(T)
        prev[0] = 1;
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            int[] current = new int[amount + 1]; // SC: O(T)
            for (int j = 0; j < amount + 1; j++) { // TC: O(T)
                if (j == 0) {
                    current[j] = 1;
                    continue;
                }
                int pick = 0;
                if (coins[i - 1] <= j) {
                    pick = current[j - coins[i - 1]];
                }
                int skip = prev[j];
                current[j] = pick + skip;
            }
            prev = current.clone();
        }
        return prev[amount];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x T)
     * SC: O(N x T)
     * 
     * - O(N x T) - dp array memory
     * 
     * Accepted (30 / 30 testcases passed)
     */
    public int changeTabulation(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1]; // SC: O(N x T)
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            for (int j = 0; j < amount + 1; j++) { // TC: O(T)
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int pick = 0;
                if (coins[i - 1] <= j) {
                    pick = dp[i][j - coins[i - 1]];
                }
                int skip = dp[i - 1][j];
                dp[i][j] = pick + skip;
            }
        }
        return dp[n][amount];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x T) + O(N x T) ~ O(N x T)
     * SC: O(N x T) + O(T)
     * 
     * - O(N x T) - memoization array memory
     * - O(T) - stack built up till amount becomes zero
     * 
     * Accepted (30 / 30 testcases passed)
     */
    public int changeMemoization(int amount, int[] coins) {
        int n = coins.length;
        int[][] memo = new int[n + 1][amount + 1]; // SC: O(N x T)
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(T)
        }
        return solveMemoization(n - 1, amount, coins, memo); // TC: O(N x T), SC: O(T)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x T)
     * SC: O(T)
     */
    private int solveMemoization(int idx, int amount, int[] coins, int[][] memo) {
        // Base Case
        if (idx == 0) {
            return (amount % coins[idx]) == 0 ? 1 : 0;
        }
        // Memoization Check
        if (memo[idx][amount] != -1) {
            return memo[idx][amount];
        }
        // Recursion Calls
        int skip = solveMemoization(idx - 1, amount, coins, memo);
        int pick = 0;
        if (coins[idx] <= amount) {
            // we can pick coin at index 'idx'
            // index is at the same as we have infinite supply of a coin denomination
            pick = solveMemoization(idx, amount - coins[idx], coins, memo);
        }
        return memo[idx][amount] = pick + skip;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: >> O(2 ^ N)
     * SC: O(T)
     * 
     * - O(T) - stack built up till amount becomes zero
     * 
     * Time Limit Exceeded (15 / 30 testcases passed)
     */
    public int changeRecursion(int amount, int[] coins) {
        int n = coins.length;
        return solveRecursion(n - 1, amount, coins);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: >> O(2 ^ N)
     * SC: >> O(N) ~ O(T)
     */
    private int solveRecursion(int idx, int amount, int[] coins) {
        // Base Case
        if (idx == 0) {
            return (amount % coins[idx]) == 0 ? 1 : 0;
        }
        // Recursion Calls
        int skip = solveRecursion(idx - 1, amount, coins);
        int pick = 0;
        if (coins[idx] <= amount) {
            // we can pick coin at index 'idx'
            // index is at the same as we have infinite supply of a coin denomination
            pick = solveRecursion(idx, amount - coins[idx], coins);
        }
        return pick + skip;
    }
}
