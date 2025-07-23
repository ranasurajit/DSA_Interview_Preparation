package Greedy_Algorithms.P4_Valid_Parenthesis_String;

import java.util.Arrays;

public class Valid_Parenthesis_String {
    public static void main(String[] args) {
        Valid_Parenthesis_String solution = new Valid_Parenthesis_String();

        String s1 = "()";
        boolean isValid1 = solution.checkValidStringRecursion(s1);
        System.out.println(isValid1);

        String s2 = "(*)";
        boolean isValid2 = solution.checkValidStringMemoization(s2);
        System.out.println(isValid2);

        String s3 = "(*))";
        boolean isValid3 = solution.checkValidStringGreedy(s3);
        System.out.println(isValid3);
    }

    /**
     * Approach III : Using Greedy Approach
     * 
     * TC: O(N)
     * SC: O(1)
     *
     * Accepted (83 / 83 testcases passed)
     */
    public boolean checkValidStringGreedy(String s) {
        int n = s.length();
        // created a range to explore the result of all possibilities
        int min = 0;
        int max = 0;

        for (int i = 0; i < n; i++) { // TC: O(N)
            if (s.charAt(i) == '(') {
                min = min + 1;
                max = max + 1;
            } else if (s.charAt(i) == ')') {
                min = min - 1;
                max = max - 1;
            } else {
                /**
                 * we have an asterisk - we can have three possibilities
                 * -1 if * is replaced by ')'
                 * 0 if * is replaced by ''
                 * +1 if * is replaced by '('
                 */
                min = min - 1;
                max = max + 1;
            }
            if (max < 0) {
                // count < 0 is not possible which indicates s has started or has ')' in the
                // beginning
                return false;
            }
            if (min < 0) {
                // if min becomes negative then we reset it to 0
                min = 0;
            }
        }
        return min == 0;
    }

    /**
     * Approach II : Using Memoization (Top-Down DP) Approach
     * 
     * TC: O(N x N) + O(N x N) ~ O(N x N)
     * SC: O(N x N) + O(N)
     * 
     * - O(N x N) - memoization memory
     * - O(N) - recursion stack
     *
     * Accepted (83 / 83 testcases passed)
     */
    public boolean checkValidStringMemoization(String s) {
        int n = s.length();
        int[] count = { 0 };
        // index and count are the states that we need to memoize
        int[][] memo = new int[n][n]; // SC: O(N x N)
        for (int[] mem : memo) { // TC: O(N)
            Arrays.fill(mem, -1); // TC: O(N)
        }
        return solveMemoization(0, n, count, s, memo); // TC: O(N x N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N x N)
     * SC: O(N)
     */
    private boolean solveMemoization(int idx, int n, int[] count, String s, int[][] memo) {
        // Base Case
        if (count[0] < 0) {
            return false;
        }
        if (idx == n) {
            return count[0] == 0;
        }
        // Memoization Check
        if (memo[idx][count[0]] != -1) {
            return memo[idx][count[0]] == 1;
        }
        // Recursion Calls
        boolean result = false;
        if (s.charAt(idx) == '(') {
            result = solveMemoization(idx + 1, n, new int[] { count[0] + 1 }, s, memo);
        } else if (s.charAt(idx) == ')') {
            result = solveMemoization(idx + 1, n, new int[] { count[0] - 1 }, s, memo);
        } else {
            // we can replace '*' with '('
            boolean replaceWithOpen = solveMemoization(idx + 1, n, new int[] { count[0] + 1 }, s, memo);
            // we can replace '*' with ')'
            boolean replaceWithClose = solveMemoization(idx + 1, n, new int[] { count[0] - 1 }, s, memo);
            // we can replace '*' with ''
            boolean replaceWithEmptyString = solveMemoization(idx + 1, n, count, s, memo);
            result = replaceWithOpen || replaceWithClose || replaceWithEmptyString;
        }
        memo[idx][count[0]] = result ? 1 : 0;
        return result;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(3 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (79 / 83 testcases passed)
     */
    public boolean checkValidStringRecursion(String s) {
        int n = s.length();
        int[] count = { 0 };
        return solveRecursion(0, n, count, s); // TC: O(3 ^ N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(3 ^ N)
     * SC: O(N)
     */
    private boolean solveRecursion(int idx, int n, int[] count, String s) {
        // Base Case
        if (count[0] < 0) {
            return false;
        }
        if (idx == n) {
            return count[0] == 0;
        }
        // Recursion Calls
        if (s.charAt(idx) == '(') {
            return solveRecursion(idx + 1, n, new int[] { count[0] + 1 }, s);
        } else if (s.charAt(idx) == ')') {
            return solveRecursion(idx + 1, n, new int[] { count[0] - 1 }, s);
        } else {
            // we can replace '*' with '('
            boolean replaceWithOpen = solveRecursion(idx + 1, n, new int[] { count[0] + 1 }, s);
            // we can replace '*' with ')'
            boolean replaceWithClose = solveRecursion(idx + 1, n, new int[] { count[0] - 1 }, s);
            // we can replace '*' with ''
            boolean replaceWithEmptyString = solveRecursion(idx + 1, n, count, s);
            return replaceWithOpen || replaceWithClose || replaceWithEmptyString;
        }
    }
}
