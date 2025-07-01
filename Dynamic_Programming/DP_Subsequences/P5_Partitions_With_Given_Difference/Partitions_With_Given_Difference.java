package Dynamic_Programming.DP_Subsequences.P5_Partitions_With_Given_Difference;

import java.util.Arrays;

public class Partitions_With_Given_Difference {
    public static void main(String[] args) {
        Partitions_With_Given_Difference solution = new Partitions_With_Given_Difference();

        int[] arr = { 1, 2, 1, 0, 1, 3, 3 };
        int d = 11;

        int countPartitionsRecursion = solution.countPartitionsRecursion(arr, d);
        System.out.println(countPartitionsRecursion);

        int countPartitionsMemoization = solution.countPartitionsMemoization(arr, d);
        System.out.println(countPartitionsMemoization);

        int countPartitionsTabulation = solution.countPartitionsTabulation(arr, d);
        System.out.println(countPartitionsTabulation);

        int countPartitionsOptimization = solution.countPartitionsSpaceOptimization(arr, d);
        System.out.println(countPartitionsOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     * 
     * TC: O(N x S) + O(N)
     * SC: O(S) + O(S) ~ O(S)
     *
     * Accepted (1111 / 1111 testcases passed)
     */
    int countPartitionsSpaceOptimization(int[] arr, int d) {
        int n = arr.length;
        int total = 0;
        for (int num : arr) { // TC: O(N)
            total += num;
        }
        /**
         * we have to find two partitions such that:
         * |s1 - s2| = d
         * s1 + s2 = total
         * so s1 = (d + total) / 2, so the problem
         * reduces to find the count of subsets with target = s1 = (d + total) / 2
         */
        int calculation = d + total;
        if ((calculation & 1) != 0) {
            return 0;
        }
        int target = calculation / 2;
        // Initialization
        int[] prev = new int[target + 1]; // SC: O(S)
        /**
         * for n = 0, for any target value, count = 0, so prev = 0
         */
        Arrays.fill(prev, 0);
        /**
         * for target = 0, for any number of elements, count = 1, so prev[0] = 1
         */
        prev[0] = 1;
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            int[] current = new int[target + 1]; // SC: O(S)
            current[0] = 1;
            // target can be zero too so starting j from 0 to (target + 1)
            for (int j = 0; j < target + 1; j++) { // TC: O(S)
                if (arr[i - 1] == 0 || arr[i - 1] <= j) {
                    current[j] = prev[j - arr[i - 1]] + prev[j];
                } else {
                    current[j] = prev[j];
                }
            }
            prev = current.clone();
        }
        return prev[target];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     * 
     * TC: O(N x S) + O(N) + O(N)
     * SC: O(N x S)
     *
     * Accepted (1111 / 1111 testcases passed)
     */
    int countPartitionsTabulation(int[] arr, int d) {
        int n = arr.length;
        int total = 0;
        for (int num : arr) { // TC: O(N)
            total += num;
        }
        /**
         * we have to find two partitions such that:
         * |s1 - s2| = d
         * s1 + s2 = total
         * so s1 = (d + total) / 2, so the problem
         * reduces to find the count of subsets with target = s1 = (d + total) / 2
         */
        int calculation = d + total;
        if ((calculation & 1) != 0) {
            return 0;
        }
        int target = calculation / 2;
        // Initialization
        int[][] dp = new int[n + 1][target + 1]; // SC: O(N x S)
        /**
         * for n = 0, for any target value, count = 0, so dp[0] = 0
         */
        Arrays.fill(dp[0], 0);
        /**
         * for target = 0, for any number of elements, count = 1, so dp[i][0] = 1
         */
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            dp[i][0] = 1;
        }
        // Iterative Calls
        for (int i = 1; i < n + 1; i++) { // TC: O(N)
            // target can be zero too so starting j from 0 to (target + 1)
            for (int j = 0; j < target + 1; j++) { // TC: O(S)
                if (arr[i - 1] == 0 || arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x S) + O(N)
     * SC: O(N x S) + O(N)
     *
     * Accepted (1111 / 1111 testcases passed)
     */
    int countPartitionsMemoization(int[] arr, int d) {
        int n = arr.length;
        int total = 0;
        for (int num : arr) { // TC: O(N)
            total += num;
        }
        /**
         * we have to find two partitions such that:
         * |s1 - s2| = d
         * s1 + s2 = total
         * so s1 = (d + total) / 2, so the problem
         * reduces to find the count of subsets with target = s1 = (d + total) / 2
         */
        int calculation = d + total;
        if ((calculation & 1) != 0) {
            return 0;
        }
        int target = calculation / 2;
        int[][] memo = new int[n + 1][target + 1]; // SC: O(N x S)
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(n - 1, arr, target, memo); // TC: O(N x S), SC: O(N)
    }

    /**
     * Using Memoization Approach
     * 
     * TC: O(N x S)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int[] arr, int target, int[][] memo) {
        // Base Case
        if (idx < 0) {
            return target == 0 ? 1 : 0;
        }
        // Memoization Check
        if (memo[idx][target] != -1) {
            return memo[idx][target];
        }
        // Recursive Calls
        int pick = 0;
        int skip = 0;
        if (arr[idx] <= target) {
            // we can choose to pick or skip
            pick = solveMemoization(idx - 1, arr, target - arr[idx], memo);
            skip = solveMemoization(idx - 1, arr, target, memo);
        } else {
            // we cannot pick at all
            skip = solveMemoization(idx - 1, arr, target, memo);
        }
        return memo[idx][target] = pick + skip;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (1010 / 1111 testcases passed)
     */
    int countPartitionsRecursion(int[] arr, int d) {
        int n = arr.length;
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        /**
         * we have to find two partitions such that:
         * |s1 - s2| = d
         * s1 + s2 = total
         * so s1 = (d + total) / 2, so the problem
         * reduces to find the count of subsets with target = s1 = (d + total) / 2
         */
        int calculation = d + total;
        if ((calculation & 1) != 0) {
            return 0;
        }
        int target = calculation / 2;
        return solveRecursion(n - 1, arr, target);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int[] arr, int target) {
        // Base Case
        if (idx < 0) {
            return target == 0 ? 1 : 0;
        }
        // Recursive Calls
        int pick = 0;
        int skip = 0;
        if (arr[idx] <= target) {
            // we can choose to pick or skip
            pick = solveRecursion(idx - 1, arr, target - arr[idx]);
            skip = solveRecursion(idx - 1, arr, target);
        } else {
            // we cannot pick at all
            skip = solveRecursion(idx - 1, arr, target);
        }
        return pick + skip;
    }
}
