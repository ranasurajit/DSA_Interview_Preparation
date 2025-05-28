package Backtracking.P1_Permutations_Of_A_String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Permutations_Of_A_String {
    public static void main(String[] args) {
        Permutations_Of_A_String solution = new Permutations_Of_A_String();

        String s1 = "ABSG";
        ArrayList<String> permutations1 = solution.findPermutationApproachI(s1);
        System.out.println(permutations1);

        String s2 = "AAA";
        ArrayList<String> permutations2 = solution.findPermutationApproachII(s2);
        System.out.println(permutations2);
    }

    /**
     * Approach II : Using Recursion + Backtracking (Optimal) Approach
     * 
     * TC: O(N x N!)
     * SC: O(N x N! + 2 x N) ~ O(N x N!)
     * 
     * Accepted (Test Cases Passed: 1026 /1026)
     */
    public ArrayList<String> findPermutationApproachII(String s) {
        Set<String> resultSet = new HashSet<String>(); // SC: O(N)
        Set<String> used = new HashSet<String>(); // SC: O(N)
        backtrack(0, s.toCharArray(), used, resultSet); // TC: O(N x N!), SC: O(N x N! + N)
        return new ArrayList<String>(resultSet);
    }

    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(N x N!)
     * SC: O(N x N! + N)
     */
    private void backtrack(int index, char[] chars, Set<String> used, Set<String> resultSet) {
        // Base Case
        if (index == chars.length) {
            resultSet.add(String.valueOf(chars));
            return;
        }
        // Recursion Calls
        for (int i = index; i < chars.length; i++) { // TC: O(N)
            String key = chars[index] + "-" + index;
            if (!used.contains(key)) {
                used.add(key);
                // swap the character at index with character at i
                swap(chars, index, i);
                backtrack(index + 1, chars, used, resultSet);
                // backtrack
                swap(chars, index, i); // undo the swap operation to explore other possibilities
                used.remove(key);
            }
        }
    }

    /**
     * Swapping Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private void swap(char[] chars, int a, int b) {
        char temp = chars[b];
        chars[b] = chars[a];
        chars[a] = temp;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(N x N!)
     * SC: O(N x N! + 4 x N) ~ O(N x N!)
     * 
     * Time limit exceeded (Test Cases Passed: 1015 /1026)
     */
    public ArrayList<String> findPermutationApproachI(String s) {
        Set<String> resultSet = new HashSet<String>(); // SC: O(N)
        Set<String> used = new HashSet<String>(); // SC: O(N)
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        solveRecursion(s, sb, resultSet, used); // TC: O(N x N!), SC: O(N x N! + N)
        return new ArrayList<String>(resultSet);
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(N x N!)
     * SC: O(N x N! + N)
     */
    private void solveRecursion(String s, StringBuilder sb, Set<String> resultSet,
            Set<String> used) {
        // Base Case
        if (sb.length() == s.length()) {
            resultSet.add(sb.toString());
            return;
        }
        // Recursion Calls
        for (int i = 0; i < s.length(); i++) { // TC: O(N)
            char ch = s.charAt(i);
            String key = ch + "-" + i;
            if (!used.contains(key)) {
                used.add(key);
                sb.append(ch);
                solveRecursion(s, sb, resultSet, used);
                // backtrack sb and used to explore other paths/choices
                sb.setLength(sb.length() - 1);
                used.remove(key);
            }
        }
    }
}
