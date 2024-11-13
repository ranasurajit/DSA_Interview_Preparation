package DP.DP_Grids.P1_Ninjas_Training;

import java.util.Arrays;

public class Geeks_Training {
    public static void main(String[] args) {
        int n = 3;
        int[][] arr = { { 1, 2, 5 }, { 3, 1, 1 }, { 3, 3, 3 } };

        int maxPointsRecursion = maximumPointsRecursion(arr, n);
        System.out.println(maxPointsRecursion);

        int maxPointsMemoization = maximumPointsMemoization(arr, n);
        System.out.println(maxPointsMemoization);

        int maxPointsTabulation = maximumPointsTabulation(arr, n);
        System.out.println(maxPointsTabulation);

        int maxPointsOptimization = maximumPointsSpaceOptimization(arr, n);
        System.out.println(maxPointsOptimization);
    }

    /**
     * Using Recursion
     * 
     * @param arr
     * @param N
     * @return
     */
    public static int maximumPointsRecursion(int[][] arr, int N) {
        /*
         * last is 3 - tasks types (0, 1, 2 and 3 is for no task
         * performed on Nth day (out of bound))
         */
        return solve(N - 1, 3, arr);
    }

    private static int solve(int index, int last, int[][] arr) {
        // Base case
        if (index == 0) {
            int maxPoints = 0;
            // 0th day when last is the index performed on 1st day
            for (int i = 0; i <= 2; i++) { // i denotes the type of task (0, 1 or 2)
                if (i != last) {
                    int points = arr[0][i];
                    maxPoints = Math.max(maxPoints, points);
                }
            }
            return maxPoints;
        }
        int maxPoints = 0;
        for (int task = 0; task <= 2; task++) {
            if (task != last) {
                int points = arr[index][task] + solve(index - 1, task, arr);
                maxPoints = Math.max(maxPoints, points);
            }
        }
        return maxPoints;
    }

    /**
     * Using Memoization
     * 
     * TC: O(3 x 4 x N)
     * SC: O(4 x N + R), where R is the Recursive stack space where R = N
     * 
     * @param arr
     * @param N
     * @return
     */
    public static int maximumPointsMemoization(int[][] arr, int N) {
        /**
         * We need a dp array of size N x 4 (N days and 3 tasks (0, 1, 2) + 1 No task
         * (3))
         */
        int[][] dp = new int[N][4]; // SC: O(4 x N)
        for (int[] dp1D : dp) { // TC: O(4 x N)
            Arrays.fill(dp1D, -1);
        }
        /*
         * last is 3 - tasks types (0, 1, 2 and 3 is for no task
         * performed on Nth day (out of bound))
         */
        return solveMemoization(N - 1, 3, arr, dp);
    }

    private static int solveMemoization(int index, int last, int[][] arr, int[][] dp) {
        // Base case
        if (index == 0) {
            int maxPoints = 0;
            // 0th day when last is the index performed on 1st day
            for (int i = 0; i <= 2; i++) { // i denotes the type of task (0, 1 or 2) // TC: O(3)
                if (i != last) {
                    int points = arr[0][i];
                    maxPoints = Math.max(maxPoints, points);
                }
            }
            return maxPoints;
        }
        if (dp[index][last] != -1) {
            return dp[index][last];
        }
        int maxPoints = 0;
        for (int task = 0; task <= 2; task++) { // TC: O(3)
            if (task != last) {
                int points = arr[index][task] + solveMemoization(index - 1, task, arr, dp); // TC: O(4 x N)
                maxPoints = Math.max(maxPoints, points);
            }
        }
        dp[index][last] = maxPoints;
        return dp[index][last];
    }

    /**
     * Using Tabulation
     * 
     * TC: O(3 x 4 x N)
     * SC: O(4 x N)
     * 
     * @param arr
     * @param N
     * @return
     */
    public static int maximumPointsTabulation(int[][] arr, int N) {
        /**
         * We need a dp array of size N x 4 (N days and 3 tasks (0, 1, 2) + 1 No task
         * (3))
         */
        int[][] dp = new int[N][4]; // SC: O(4 x N)
        dp[0][0] = Math.max(arr[0][1], arr[0][2]); // if task 0 was chosen as last task
        dp[0][1] = Math.max(arr[0][0], arr[0][2]); // if task 1 was chosen as last task
        dp[0][2] = Math.max(arr[0][0], arr[0][1]); // if task 2 was chosen as last task
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2])); // if task 2 was chosen as last task

        for (int day = 1; day < N; day++) { // TC: O(N)
            for (int last = 0; last <= 3; last++) { // TC: O(4)
                dp[day][last] = 0;
                for (int task = 0; task <= 2; task++) { // TC: O(3)
                    if (task != last) {
                        int points = arr[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], points);
                    }
                }
            }
        }
        return dp[N - 1][3];
    }

    /**
     * Using Space Optimization
     * TC: O(3 x 4 x N)
     * SC: O(4) ~ O(1)
     * 
     * @param arr
     * @param N
     * @return
     */
    public static int maximumPointsSpaceOptimization(int[][] arr, int N) {
        /**
         * We need to store a prev array of size 4 (3 tasks (0, 1, 2) + 1 No task (3))
         */
        int[] prev = new int[4]; // SC: O(4)
        prev[0] = Math.max(arr[0][1], arr[0][2]); // if task 0 was chosen as last task
        prev[1] = Math.max(arr[0][0], arr[0][2]); // if task 1 was chosen as last task
        prev[2] = Math.max(arr[0][0], arr[0][1]); // if task 2 was chosen as last task
        prev[3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2])); // if task 2 was chosen as last task

        for (int day = 1; day < N; day++) { // TC: O(N)
            int[] current = new int[4]; // SC: O(4)
            for (int last = 0; last <= 3; last++) { // TC: O(4)
                current[last] = 0;
                for (int task = 0; task <= 2; task++) { // TC: O(3)
                    if (task != last) {
                        int points = arr[day][task] + prev[task];
                        current[last] = Math.max(current[last], points);
                    }
                }
            }
            prev = current;
        }
        return prev[3];
    }
}
