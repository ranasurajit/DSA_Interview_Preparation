package Stacks.P11_Decode_The_String;

import java.util.Stack;

public class Decode_The_String {
    public static void main(String[] args) {
        Decode_The_String solution = new Decode_The_String();

        String s = "3[b2[ca]]";
        String decoded = solution.decodeString(s);
        System.out.println(decoded);
    }

    /**
     * TC: O(K x N)
     * SC: O(N)
     */
    private String decodeString(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<Character>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            if (ch != ']') {
                st.push(ch);
            } else {
                StringBuilder temp = new StringBuilder();
                while (!st.isEmpty() && st.peek() != '[') {
                    temp.insert(0, st.pop());
                }
                st.pop(); // remove '[' from stack
                StringBuilder num = new StringBuilder();
                while (!st.isEmpty() && Character.isDigit(st.peek())) {
                    num.insert(0, st.pop());
                }
                int k = Integer.valueOf(num.toString());
                StringBuilder word = new StringBuilder();
                while (k-- > 0) {
                    word.append(temp);
                }
                for (int j = 0; j < word.length(); j++) {
                    st.push(word.charAt(j));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.insert(0, st.pop());
        }
        return sb.toString();
    }
}
