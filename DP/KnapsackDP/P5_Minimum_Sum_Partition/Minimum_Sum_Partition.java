package DP.KnapsackDP.P5_Minimum_Sum_Partition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Minimum_Sum_Partition {
    public static void main(String[] args) {
        Minimum_Sum_Partition solution = new Minimum_Sum_Partition();

        int[] arr = { 9, 2, 2, 3 };

        int minDiffMemo = solution.minDifferenceMemoization(arr);
        System.out.println(minDiffMemo);

        int minDiffTab = solution.minDifferenceTabulation(arr);
        System.out.println(minDiffTab);
    }

    /**
     * Using Tabulation
     * 
     * TC: O(N x R + 2 x N) ~ O(N x R)
     * SC: O(N x R)
     * 
     * where R = sum of array 'arr' elements
     * 
     * @param arr
     * @return
     */
    public int minDifferenceTabulation(int arr[]) {
        int n = arr.length;
        int range = 0;
        for (int i = 0; i < n; i++) {
            range += arr[i];
        }
        List<Integer> vec = solveTabulation(arr, n, range);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < vec.size(); i++) {
            minDiff = Math.min(minDiff, Math.abs(range - (2 * vec.get(i))));
        }
        return minDiff;
    }

    private List<Integer> solveTabulation(int[] arr, int n, int range) {
        boolean[][] dp = new boolean[n + 1][range + 1];

        // initialization
        Arrays.fill(dp[0], false);
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }

        // iterative call
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < range + 1; j++) {
                // convert (n, range) to (i, j)
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        List<Integer> vec = new ArrayList<Integer>();

        for (int i = 0; i < range + 1; i++) {
            if (dp[n][i]) {
                vec.add(i);
            }
        }
        return vec;
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x R + 2 x N) ~ O(N x R)
     * SC: O(N x R + N)
     * 
     * where R = sum of array 'arr' elements
     * 
     * @param arr
     * @return
     */
    public int minDifferenceMemoization(int arr[]) {
        int n = arr.length;
        int range = 0;
        for (int i = 0; i < n; i++) {
            range += arr[i];
        }
        List<Integer> vec = new ArrayList<Integer>();
        Boolean[][] dp = new Boolean[n + 1][range + 1];
        solveMemoization(arr, n, range / 2, dp, vec);
        System.out.println(vec);

        int minDiff = Integer.MAX_VALUE;
        for (Integer it : vec) {
            minDiff = Math.min(minDiff, Math.abs(range - 2 * it));
        }
        return minDiff;
    }

    /**
     * Using Memoization
     * 
     * TC: O(N x R)
     * SC: O(N x R + N)
     * 
     * @param arr
     * @param n
     * @param range
     * @param dp
     * @param vec
     * @return
     */
    private boolean solveMemoization(int[] arr, int n, int range,
            Boolean[][] dp, List<Integer> vec) {
        if (range == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        // memoization call
        if (dp[n][range] != null) {
            return dp[n][range];
        }
        // recursion call
        boolean optionPick = false;
        boolean optionNotPick = false;
        if (arr[n - 1] <= range) {
            optionPick = solveMemoization(arr, n - 1, range - arr[n - 1], dp, vec) ||
                    solveMemoization(arr, n - 1, range, dp, vec);
        } else {
            optionNotPick = solveMemoization(arr, n - 1, range, dp, vec);
        }
        dp[n][range] = optionPick || optionNotPick;

        if (dp[n][range] && !vec.contains(range)) {
            vec.add(range);
        }
        return dp[n][range];
    }
}
