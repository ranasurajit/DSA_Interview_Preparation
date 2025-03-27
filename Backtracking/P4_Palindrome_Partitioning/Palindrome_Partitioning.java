package Backtracking.P4_Palindrome_Partitioning;

import java.util.ArrayList;
import java.util.List;

public class Palindrome_Partitioning {
    public static void main(String[] args) {
        Palindrome_Partitioning solution = new Palindrome_Partitioning();

        String s = "aab";
        List<List<String>> partitionList = solution.partition(s);
        System.out.println(partitionList);
    }

    /**
     * Using Recursion + Backtracking Approach
     * 
     * TC: O(N x 2 ^ N)
     * SC: O(2 x N) ~ O(N)
     * 
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> current = new ArrayList<String>(); // SC: O(N)
        backtrack(0, s, n, current, result);
        return result;
    }

    /**
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     * 
     * @param index
     * @param s
     * @param n
     * @param current
     * @param result
     */
    private void backtrack(int index, String s, int n, List<String> current,
            List<List<String>> result) {
        if (index == n) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < n; i++) { // TC: O(N)
            if (isPalindrome(s, index, i)) {
                // include
                current.add(s.substring(index, i + 1));
                backtrack(i + 1, s, n, current, result);
                // backtrack
                current.remove(current.size() - 1);
            }
        }
    }

    /**
     * TC: O(N / 2)
     * SC: O(1)
     * 
     * @param s
     * @param start
     * @param end
     * @return
     */
    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) { // TC: O(N / 2)
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
