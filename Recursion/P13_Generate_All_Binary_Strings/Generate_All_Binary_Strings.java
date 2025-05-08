package Recursion.P13_Generate_All_Binary_Strings;

import java.util.ArrayList;
import java.util.List;

public class Generate_All_Binary_Strings {
    public static void main(String[] args) {
        Generate_All_Binary_Strings solution = new Generate_All_Binary_Strings();

        int n = 3;
        List<String> binaryStrings = solution.generateBinaryStrings(n);
        System.out.println(binaryStrings);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(2 x N) ~ O(N)
     */
    public List<String> generateBinaryStrings(int n) {
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        solveRecursion(0, n, sb, result); // TC: O(2 ^ N), SC: O(N)
        return result;
    }

    /**
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(int idx, int n, StringBuilder sb, List<String> result) {
        // Base Case
        if (idx == n) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        // take 0 or 1 (condition based)
        // take 0
        sb.append('0');
        solveRecursion(idx + 1, n, sb, result);
        sb.setLength(sb.length() - 1); // backtrack
        // take 1
        if (sb.length() == 0 || sb.charAt(idx - 1) != '1') {
            sb.append('1');
            solveRecursion(idx + 1, n, sb, result);
            sb.setLength(sb.length() - 1); // backtrack
        }
    }
}
