package Backtracking.P5_Palindrome_Partitioning;

import java.util.ArrayList;
import java.util.List;

public class Palindrome_Partitioning {
    public static void main(String[] args) {
        Palindrome_Partitioning solution = new Palindrome_Partitioning();

        String s = "aab";
        List<List<String>> list = solution.partition(s);
        System.out.println(list);
    }

    /**
     * Using Recursion + Backtracking Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(2 x N) ~ O(N)
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> current = new ArrayList<String>(); // SC: O(N) - reused
        solveBacktrack(0, n, s, current, result); // TC: O(N x 2 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Recursion + Backtracking Approach
     *
     * TC: O(N ^ 2 x 2 ^ N) ~ O(N x 2 ^ N) due to pruning
     * SC: O(N)
     */
    private void solveBacktrack(int index, int n, String s, List<String> current,
            List<List<String>> result) {
        // Base Case
        if (index == n) {
            result.add(new ArrayList<String>(current));
            return;
        }
        // Recursion Calls
        for (int i = index; i < n; i++) { // TC: O(N)
            String temp = s.substring(index, i + 1);
            if (isPalindrome(temp)) { // TC: O(N)
                // modify
                current.add(temp);
                // explore
                solveBacktrack(i + 1, n, s, current, result);
                // backtrack
                current.remove(current.size() - 1);
            }
        }
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     */
    private boolean isPalindrome(String s) {
        int p = 0;
        int q = s.length() - 1;
        while (p < q) {
            if (s.charAt(p) != s.charAt(q)) {
                return false;
            }
            p++;
            q--;
        }
        return true;
    }
}
