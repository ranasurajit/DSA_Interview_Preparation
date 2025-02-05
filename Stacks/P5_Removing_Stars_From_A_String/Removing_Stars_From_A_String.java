package Stacks.P5_Removing_Stars_From_A_String;

import java.util.Stack;

public class Removing_Stars_From_A_String {
    public static void main(String[] args) {
        Removing_Stars_From_A_String solution = new Removing_Stars_From_A_String();
        String s = "leet**cod*e";
        String result = solution.removeStars(s);
        System.out.println(result);
    }

    /**
     * Using Stack Approach
     *
     * TC: O(N)
     * SC: O(N)
     * 
     * @param s
     * @return
     */
    public String removeStars(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<Character>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            if (!st.isEmpty() && ch == '*') {
                st.pop();
            } else {
                st.push(ch);
            }
        }
        String result = "";
        while (!st.isEmpty()) { // TC: O(N)
            result = st.pop() + result;
        }
        return result;
    }
}
