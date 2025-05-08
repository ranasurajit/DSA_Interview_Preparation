package Recursion.P15_Print_N_Bit_Binary_Numbers_Having_More_Ones_Than_Zeroes;

import java.util.ArrayList;

public class Print_N_Bit_Binary_Numbers_Having_More_Ones_Than_Zeroes {
    public static void main(String[] args) {
        Print_N_Bit_Binary_Numbers_Having_More_Ones_Than_Zeroes solution = new Print_N_Bit_Binary_Numbers_Having_More_Ones_Than_Zeroes();

        int n = 3;
        ArrayList<String> binaryStrings = solution.NBitBinary(n);
        System.out.println(binaryStrings);
    }

    /**
     * Approach : Using Recursion Approach
     * 
     * TC: O((N + 1) x 2 ^ N) ~ O(N x 2 ^ N)
     * SC: O(2 x N) ~ O(N)
     */
    private ArrayList<String> NBitBinary(int N) {
        ArrayList<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        int ones = 0;
        int zeroes = 0;
        solveRecursion(ones, zeroes, N, sb, result); // TC: O(2 ^ N), SC: O(N)
        // TC: O(2 ^ N x log(2 ^ N)) ~ O(N x 2 ^ N)
        result.sort((a, b) -> Double.compare(Double.parseDouble(b), Double.parseDouble(a)));
        return result;
    }

    /**
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(int ones, int zeroes, int n, StringBuilder sb,
            ArrayList<String> result) {
        // Base Case
        if (n == 0) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        // we may choose 1 or 0 (if ones > zeroes)
        // choose 0
        if (ones > zeroes) {
            sb.append('0');
            solveRecursion(ones, zeroes + 1, n - 1, sb, result);
            sb.setLength(sb.length() - 1); // backtrack
        }
        // choose 1
        sb.append('1');
        solveRecursion(ones + 1, zeroes, n - 1, sb, result);
        sb.setLength(sb.length() - 1); // backtrack
    }
}
