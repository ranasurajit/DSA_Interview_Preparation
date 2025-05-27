package Backtracking_Backup.P5_Permutations_Of_A_String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Permutations_Of_A_String {
    public static void main(String[] args) {
        Permutations_Of_A_String solution = new Permutations_Of_A_String();

        String s1 = "ABC";
        ArrayList<String> result1 = solution.findPermutationApproachI(s1);
        System.out.println(result1);

        String s2 = "ABSG";
        ArrayList<String> result2 = solution.findPermutationApproachII(s2);
        System.out.println(result2);
    }

    /**
     * Approach I
     * 
     * TC: O(N x N!)
     * SC: O(N)
     * 
     * @param s
     * @return
     */
    public ArrayList<String> findPermutationApproachI(String s) {
        Set<String> result = new HashSet<String>();
        Set<String> set = new HashSet<String>();
        StringBuilder sb = new StringBuilder();
        solve(s, sb, set, result);
        return new ArrayList<>(result);
    }

    private void solve(String s, StringBuilder sb, Set<String> set,
            Set<String> result) {
        // base case when sb.length() = s.length()
        if (sb.length() == s.length()) {
            result.add(sb.toString());
            return;
        }
        // we need to always begin from start index 0
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String key = ch + "-" + i;
            if (!set.contains(key)) {
                // choose option
                sb.append(ch);
                set.add(ch + "-" + i);
                // explore further
                solve(s, sb, set, result);
                // backtrack
                sb.setLength(sb.length() - 1);
                set.remove(ch + "-" + i);
            }
        }
    }

    /**
     * Approach II - Using Character swapping
     * 
     * TC: O(N x N!)
     * SC: O(1)
     * 
     * @param s
     * @return
     */
    public ArrayList<String> findPermutationApproachII(String s) {
        Set<String> result = new HashSet<String>();
        solveSwap(0, s, result);
        return new ArrayList<>(result);
    }

    private void solveSwap(int idx, String s, Set<String> result) {
        if (idx == s.length()) {
            result.add(s);
        }
        for (int i = idx; i < s.length(); i++) {
            // swap characters at index i with idx
            s = swap(s, idx, i);
            // explore
            solveSwap(idx + 1, s, result);
            // backtrack
            s = swap(s, idx, i);
        }
    }

    private String swap(String s, int from, int to) {
        char[] ch = s.toCharArray();
        char temp = ch[to];
        ch[to] = ch[from];
        ch[from] = temp;
        return String.valueOf(ch);
    }
}
