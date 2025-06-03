package Stacks_Queues.P9_Valid_Parentheses;

import java.util.Stack;

public class Valid_Parentheses {
    public static void main(String[] args) {
        Valid_Parentheses solution = new Valid_Parentheses();

        String s1 = "()[]{}";
        boolean isBalancedString1 = solution.isValid(s1);
        System.out.println(isBalancedString1);

        String s2 = "(]";
        boolean isBalancedString2 = solution.isValid(s2);
        System.out.println(isBalancedString2);
    }

    /**
     * Approach : Using Stack Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public boolean isValid(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<Character>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            if (isOpenBracket(ch)) {
                st.push(ch);
            } else {
                if (st.isEmpty()) {
                    return false;
                } else {
                    if (getOpenBracketFor(ch) == st.peek()) {
                        st.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        if (!st.isEmpty()) {
            // still stack contains un-balanced bracket
            return false;
        }
        return true;
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    private char getOpenBracketFor(char ch) {
        if (ch == ')') {
            return '(';
        } else if (ch == '}') {
            return '{';
        } else {
            return '[';
        }
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isOpenBracket(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }
}
