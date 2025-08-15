package Strings.Hard.P1_Minimum_Add_To_Make_Parentheses_Valid;

import java.util.Stack;

public class Minimum_Add_To_Make_Parentheses_Valid {
    public static void main(String[] args) {
        Minimum_Add_To_Make_Parentheses_Valid solution = new Minimum_Add_To_Make_Parentheses_Valid();

        String s1 = "())";
        int minBrackets1 = solution.minAddToMakeValidUsingStack(s1);
        System.out.println(minBrackets1);

        String s2 = "(((";
        int minBrackets2 = solution.minAddToMakeValidUsingStack(s2);
        System.out.println(minBrackets2);

        String s3 = "()))((";
        int minBrackets3 = solution.minAddToMakeValidUsingStack(s3);
        System.out.println(minBrackets3);
    }

    /**
     * Approach II : Using String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minAddToMakeValidOptimal(String s) {
        int n = s.length();
        int open = 0;
        int close = 0;
        int i = 0;
        while (i < n) { // TC: O(N)
            char ch = s.charAt(i);
            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
            i++;
        }
        return open + close;
    }

    /**
     * Approach I : Using Stack Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int minAddToMakeValidUsingStack(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<Character>(); // TC: O(N)
        int i = 0;
        while (i < n) { // TC: O(N)
            char ch = s.charAt(i);
            if (!st.isEmpty() && ch == ')' && st.peek() == '(') {
                // remove the last insert element if it balances the paranthesis
                st.pop();
            } else {
                st.push(ch);
            }
            i++;
        }
        return st.size();
    }
}
