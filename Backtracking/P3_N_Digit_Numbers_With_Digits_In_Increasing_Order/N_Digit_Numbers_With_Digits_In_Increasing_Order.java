package Backtracking.P3_N_Digit_Numbers_With_Digits_In_Increasing_Order;

import java.util.ArrayList;

public class N_Digit_Numbers_With_Digits_In_Increasing_Order {
    public static void main(String[] args) {
        N_Digit_Numbers_With_Digits_In_Increasing_Order solution = new N_Digit_Numbers_With_Digits_In_Increasing_Order();

        int n1 = 1;
        ArrayList<Integer> numberList1 = solution.increasingNumbersApproachI(n1);
        System.out.println(numberList1);

        int n2 = 2;
        ArrayList<Integer> numberList2 = solution.increasingNumbersApproachI(n2);
        System.out.println(numberList2);
    }

    /**
     * Approach II : Using Recursion + Backtracking Approach
     * 
     * TC: O(9 ^ N)
     * SC: O(N)
     */
    public ArrayList<Integer> increasingNumbersApproachII(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (n == 1) {
            for (int i = 0; i <= 9; i++) { // TC: O(1)
                result.add(i);
            }
            return result;
        }
        int[] num = { 0 };
        solve(0, n, num, result); // TC: O(9 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(9 ^ N)
     * SC: O(N)
     */
    private void solve(int lastDigit, int n, int[] num, ArrayList<Integer> result) {
        // Base Case
        if (n == 0) {
            result.add(num[0]);
            return;
        }
        // Recursion Calls
        for (int i = lastDigit + 1; i <= 9; i++) {
            // modify
            num[0] = num[0] * 10 + i;
            // explore
            solve(i, n - 1, num, result);
            // backtrack
            num[0] = num[0] / 10;
        }
    }

    /**
     * Approach I : Using Recursion + Backtracking Approach
     * 
     * TC: O(9 ^ N)
     * SC: O(N)
     */
    public ArrayList<Integer> increasingNumbersApproachI(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (n == 1) {
            for (int i = 0; i <= 9; i++) { // TC: O(1)
                result.add(i);
            }
            return result;
        }
        solveBacktrack(0, -1, n, new StringBuilder(), result); // TC: O(9 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(9 ^ N)
     * SC: O(N)
     */
    private void solveBacktrack(int index, int prev, int n, StringBuilder sb,
            ArrayList<Integer> result) {
        // Base Case
        if (index == n) {
            if (sb.length() == n) {
                result.add(Integer.valueOf(sb.toString()));
            }
            return;
        }
        // Recursion Calls
        for (int i = 1; i <= 9; i++) {
            if (prev == -1 || i > prev) {
                sb.append(i);
                // explore
                solveBacktrack(index + 1, i, n, sb, result);
                // backtrack
                sb.setLength(sb.length() - 1);
            }
        }
    }
}
