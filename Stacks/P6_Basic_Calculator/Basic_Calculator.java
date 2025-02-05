package Stacks.P6_Basic_Calculator;

import java.util.Stack;

public class Basic_Calculator {
    public static void main(String[] args) {
        Basic_Calculator solution = new Basic_Calculator();

        String s = "14 + 7 - (5 + 8) + (1 + 8)";
        int result = solution.calculate(s);
        System.out.println(result);
    }

    /**
     * Using Stack Approach
     *
     * TC: O(N)
     * SC: O(1)
     * 
     * @param s
     * @return
     */
    public int calculate(String s) {
        int n = s.length();
        int result = 0;
        int num = 0;
        int sign = 1;
        Stack<Integer> st = new Stack<Integer>(); // SC: O(2)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                // keep forming the num
                num = num * 10 + (ch - '0');
            } else if (ch == '+') {
                // store the formed num to result and reset num
                result += (num * sign);
                num = 0;
                sign = 1;
            } else if (ch == '-') {
                // store the formed num to result and reset num
                result += (num * sign);
                num = 0;
                sign = -1;
            } else if (ch == '(') {
                /**
                 * store the current result and sign to Stack and
                 * reset other values to compute new result
                 */
                st.push(result);
                st.push(sign);
                result = 0;
                num = 0;
                sign = 1;
            } else if (ch == ')') {
                result += (num * sign);
                num = 0;
                int lastSign = st.pop();
                int lastResult = st.pop();
                result = result * lastSign;
                result += lastResult;
            } else {
                continue;
            }
        }
        if (num > 0) {
            result += (num * sign);
        }
        return result;
    }
}
