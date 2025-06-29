package Dynamic_Programming.DP_2D.P7_Ninjas_Training;

import java.util.Arrays;

public class Ninjas_Training {
    public static void main(String[] args) {
        Ninjas_Training solution = new Ninjas_Training();

        int n = 3;
        int[][] points = { { 1, 2, 5 }, { 3, 1, 1 }, { 3, 3, 3 } };

        int maxPointsRecursion = solution.ninjaTrainingRecursion(n, points);
        System.out.println(maxPointsRecursion);

        int maxPointsMemoization = solution.ninjaTrainingMemoization(n, points);
        System.out.println(maxPointsMemoization);

        int maxPointsTabulation = solution.ninjaTrainingTabulation(n, points);
        System.out.println(maxPointsTabulation);

        int maxPointsOptimization = solution.ninjaTrainingSpaceOptimization(n, points);
        System.out.println(maxPointsOptimization);
    }

    /**
     * Approach IV : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O(N x 4 x 3)
     * SC: O(4) + O(4)
     *
     * - O(4) - dp optimized memory
     */
    public int ninjaTrainingSpaceOptimization(int n, int points[][]) {
        // prev index can be 0, 1, 2, 4 where 3 means no task done
        // Initialization
        int[] prev = new int[4]; // SC: O(4)
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0],
                Math.max(points[0][1], points[0][2]));
        // Iterative Calls
        for (int day = 1; day < n; day++) { // TC: O(N)
            int[] current = new int[4]; // SC: O(4)
            for (int last = 0; last <= 3; last++) { // TC: O(4)
                current[last] = 0;
                for (int task = 0; task <= 2; task++) { // TC: O(3)
                    if (task != last) {
                        current[last] = Math.max(current[last],
                                points[day][task] + prev[task]);
                    }
                }
            }
            prev = current.clone();
        }
        return prev[3];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up DP) Approach
     *
     * TC: O(N x 4 x 3)
     * SC: O(N x 4)
     *
     * - O(N x 4) - dp memory
     */
    public int ninjaTrainingTabulation(int n, int points[][]) {
        // prev index can be 0, 1, 2, 4 where 3 means no task done
        // Initialization
        int[][] dp = new int[n][4]; // SC: O(N x 4)
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0],
                Math.max(points[0][1], points[0][2]));
        // Iterative Calls
        for (int day = 1; day < n; day++) { // TC: O(N)
            for (int last = 0; last <= 3; last++) { // TC: O(4)
                dp[day][last] = 0;
                for (int task = 0; task <= 2; task++) { // TC: O(3)
                    if (task != last) {
                        dp[day][last] = Math.max(dp[day][last],
                                points[day][task] + dp[day - 1][task]);
                    }
                }
            }
        }
        return dp[n - 1][3];
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     *
     * TC: O(N x 4 x 3)
     * SC: O(N x 4) + O(N)
     *
     * - O(N x 4) - memoization memory
     * - O(N) - recursion stack
     */
    public int ninjaTrainingMemoization(int n, int points[][]) {
        // prev index can be 0, 1, 2, 4 where 3 means no task done
        int[][] memo = new int[n][4];
        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return solveMemoization(n - 1, 3, points, memo);
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x 4 x 3)
     * SC: O(N)
     */
    private int solveMemoization(int idx, int prevIndex,
            int[][] points, int[][] memo) {
        // Base case
        if (idx < 0) {
            return 0;
        }
        // Memoization Check
        if (idx >= 0 && memo[idx][prevIndex] != -1) {
            return memo[idx][prevIndex];
        }
        // Recursion Calls
        int maxPoints = 0;
        for (int i = 0; i < 3; i++) { // TC: O(3) ~ O(1)
            if (i != prevIndex && idx >= 0) {
                int currentPoints = points[idx][i] +
                        solveMemoization(idx - 1, i, points, memo);
                maxPoints = Math.max(maxPoints, currentPoints);
            }
        }
        return memo[idx][prevIndex] = maxPoints;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(3 ^ N)
     * SC: O(N)
     */
    public int ninjaTrainingRecursion(int n, int points[][]) {
        // prev index can be -1, 0, 1, 2 where -1 means no task done
        return solveRecursion(n - 1, -1, n, points);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(3 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int prevIndex, int n, int[][] points) {
        // Base case
        if (idx == 0) {
            int max = 0;
            for (int i = 0; i < 3; i++) { // TC: O(3) ~ O(1)
                if (i != prevIndex) {
                    max = Math.max(max, points[0][i]);
                }
            }
            return max;
        }
        // Recursion Calls
        int maxPoints = 0;
        for (int i = 0; i < 3; i++) { // TC: O(3) ~ O(1)
            int currentPoints = 0;
            if (i != prevIndex) {
                currentPoints += points[idx][i] + solveRecursion(idx - 1, i, n, points);
            }
            maxPoints = Math.max(maxPoints, currentPoints);
        }
        return maxPoints;
    }
}
