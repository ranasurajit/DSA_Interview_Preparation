package Recursion.P10_Letter_Case_Permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Letter_Case_Permutation {
    public static void main(String[] args) {
        Letter_Case_Permutation solution = new Letter_Case_Permutation();

        String s = "a1b2";
        List<String> result = solution.letterCasePermutation(s);
        System.out.println(result);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(2 x N x 2 ^ N + N) ~ O(N x 2 ^ N)
     */
    public List<String> letterCasePermutation(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(); // SC: O(N x 2 ^ N)
        Set<String> set = new HashSet<String>(); // SC: O(N x 2 ^ N)
        solveRecursion(0, s, n, sb, set);
        return new ArrayList<String>(set);
    }

    /**
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(int idx, String s, int n, StringBuilder current,
            Set<String> set) {
        // Base Case
        if (idx == n) {
            set.add(current.toString()); // TC: O(N)
            return;
        }
        // Recursion Calls
        char ch = s.charAt(idx);
        if (!Character.isDigit(ch)) {
            // use lowercase
            current.append(Character.toLowerCase(ch));
            solveRecursion(idx + 1, s, n, current, set);
            current.setLength(current.length() - 1); // backtrack
            // use uppercase
            current.append(Character.toUpperCase(ch));
            solveRecursion(idx + 1, s, n, current, set);
            current.setLength(current.length() - 1); // backtrack
        } else {
            // we have only choice to add it
            current.append(ch);
            solveRecursion(idx + 1, s, n, current, set);
            current.setLength(current.length() - 1); // backtrack
        }
    }
}
