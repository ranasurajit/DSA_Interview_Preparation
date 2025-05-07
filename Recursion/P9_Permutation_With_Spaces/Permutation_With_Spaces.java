package Recursion.P9_Permutation_With_Spaces;

import java.util.ArrayList;
import java.util.Collections;

public class Permutation_With_Spaces {
    public static void main(String[] args) {
        Permutation_With_Spaces solution = new Permutation_With_Spaces();

        String s = "ABC";
        ArrayList<String> result = solution.permutation(s);
        System.out.println(result);
    }

    /**
     * Approach : Using Recursion and Sorting
     * 
     * TC: O(N x 2 ^ N)
     * SC: O(N x 2 ^ N)
     */
    private ArrayList<String> permutation(String s) {
        int n = s.length();
        ArrayList<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder(); // SC: O(2 ^ N)
        solveRecursion(0, s, n, sb, result); // TC: O(2 ^ N), SC: O(N)
        Collections.sort(result); // TC: O(2 ^ N x log(2 ^ N)) ~ O(N x 2 ^ N)
        return result;
    }

    /**
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(int idx, String s, int n, StringBuilder current,
            ArrayList<String> result) {
        // Base Case
        if (idx == n) {
            if (current.charAt(current.length() - 1) != ' ') {
                result.add(current.toString());
            }
            return;
        }
        // Recursion Calls
        // not take space
        current.append(s.charAt(idx));
        solveRecursion(idx + 1, s, n, current, result);
        // take space
        current.append(' ');
        solveRecursion(idx + 1, s, n, current, result);
        // backtrack to explore all other possibilities
        current.setLength(current.length() - 2); // reverted both character and space
    }
}
