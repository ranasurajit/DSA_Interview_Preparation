package Recursion.P14_Generate_Binary_Strings_Without_Adjacent_Zeros;

import java.util.ArrayList;
import java.util.List;

public class Generate_Binary_Strings_Without_Adjacent_Zeros {
    public static void main(String[] args) {
        Generate_Binary_Strings_Without_Adjacent_Zeros solution = new Generate_Binary_Strings_Without_Adjacent_Zeros();

        int n = 3;
        List<String> binaryStrings = solution.validStrings(n);
        System.out.println(binaryStrings);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(2 x N) ~ O(N)
     */
    public List<String> validStrings(int n) {
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        solveRecursion(0, n, sb, result); // TC: O(2 ^ N), SC: O(N)
        return result;
    }

    /**
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private static void solveRecursion(int idx, int n, StringBuilder sb, List<String> result) {
        // Base Case
        if (idx == n) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        // take 1 or 0 (condition based)
        // take 1
        sb.append('1');
        solveRecursion(idx + 1, n, sb, result);
        sb.setLength(sb.length() - 1); // backtrack
        // take 1
        if (sb.length() == 0 || sb.charAt(idx - 1) != '0') {
            sb.append('0');
            solveRecursion(idx + 1, n, sb, result);
            sb.setLength(sb.length() - 1); // backtrack
        }
    }
}
