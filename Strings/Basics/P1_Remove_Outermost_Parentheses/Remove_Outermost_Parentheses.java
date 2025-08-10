package Strings.Basics.P1_Remove_Outermost_Parentheses;

public class Remove_Outermost_Parentheses {
    public static void main(String[] args) {
        Remove_Outermost_Parentheses solution = new Remove_Outermost_Parentheses();

        String s1 = "(()())(())";
        String result1 = solution.removeOuterParentheses(s1);
        System.out.println(result1);

        String s2 = "(()())(())(()(()))";
        String result2 = solution.removeOuterParentheses(s2);
        System.out.println(result2);

        String s3 = "()()";
        String result3 = solution.removeOuterParentheses(s3);
        System.out.println(result3);
    }

    /**
     * Approach : Using StringBuilder Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public String removeOuterParentheses(String s) {
        int n = s.length();
        int counter = 0;
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            if (ch == ')') {
                counter--;
            }
            if (counter != 0) {
                sb.append(ch);
            }
            if (ch == '(') {
                counter++;
            }
        }
        return sb.toString();
    }
}
