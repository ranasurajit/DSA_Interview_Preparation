package Recursion.P11_Generate_Parentheses;

import java.util.ArrayList;
import java.util.List;

public class Generate_Parentheses {
    public static void main(String[] args) {
        Generate_Parentheses solution = new Generate_Parentheses();

        int n = 3;
        List<String> result = solution.generateParenthesis(n);
        System.out.println(result);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(2n * Catalan Number) ~ O(4^n / √n)
     * (Only valid sequences are generated = Catalan Number)
     * SC: O(3 x N) ~ O(N)
     */
    public List<String> generateParenthesis(int n) {
        int open = n;
        int close = n;
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        solveRecursion(open, close, sb, result);
        return result;
    }

    /**
     * TC: O(2n * Catalan Number) ~ O(4^n / √n)
     * (Only valid sequences are generated = Catalan Number)
     * SC: O(2 x N)
     */
    private void solveRecursion(int open, int close, StringBuilder current, List<String> result) {
        // Base Case
        if (open == 0 && close == 0) {
            result.add(current.toString()); // TC: O(N)
            return;
        }
        // Recursion Calls
        /**
         * we have two choices to use open '(' or close ')' brackets
         * (close to be used when open < close)
         */
        // use open
        if (open > 0) {
            current.append('(');
            solveRecursion(open - 1, close, current, result);
            current.setLength(current.length() - 1); // backtrack
        }
        if (open < close) {
            current.append(')');
            solveRecursion(open, close - 1, current, result);
            current.setLength(current.length() - 1); // backtrack
        }
    }
}
