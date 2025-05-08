package Recursion.P17_Letter_Combinations_Of_A_Phone_Number;

import java.util.ArrayList;
import java.util.List;

public class Letter_Combinations_Of_A_Phone_Number {

    private static final String[] keyPad = {
            "", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"
    };

    public static void main(String[] args) {
        Letter_Combinations_Of_A_Phone_Number solution = new Letter_Combinations_Of_A_Phone_Number();

        String digits = "23";
        List<String> combinations = solution.letterCombinations(digits);
        System.out.println(combinations);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N x 4 ^ N)
     * SC: O(N)
     */
    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        List<String> result = new ArrayList<String>();
        if (digits.equals("")) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        solveRecursion(0, n, digits, sb, result);
        return result;
    }

    /**
     * TC: O(N x 4 ^ N)
     *
     * as for a keypad key maximum 4 loops happen
     * For example keypad 9 has 4 options (w, x, y and z)
     * 
     * SC: O(N)
     */
    private void solveRecursion(int idx, int n, String digits,
            StringBuilder sb, List<String> result) {
        // Base Case
        if (idx == n) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        char ch = digits.charAt(idx);
        String keys = keyPad[ch - '0'];
        if (keys.length() > 0) {
            for (char c : keys.toCharArray()) {
                sb.append(c);
                solveRecursion(idx + 1, n, digits, sb, result);
                sb.setLength(sb.length() - 1); // backtrack to explore other possibilities
            }
        }
    }
}
