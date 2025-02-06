package Stacks.P8_Validate_Stack_Sequences;

import java.util.Stack;

public class Validate_Stack_Sequences {
    public static void main(String[] args) {
        Validate_Stack_Sequences solution = new Validate_Stack_Sequences();

        int[] pushed = { 1, 2, 3, 4, 5 };
        int[] popped = { 4, 5, 3, 2, 1 };

        boolean isValid = solution.validateStackSequences(pushed, popped);
        System.out.println(isValid);
    }

    /**
     * TC: O(N)
     * SC: O(N)
     * 
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length; // pushed and popped lengths are same
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        int p = 0; // pointer for array 'pushed'
        int q = 0; // pointer for array 'popped'
        while (p < n && q < n) { // TC: O(N)
            // intially stack is empty
            st.push(pushed[p++]);
            while (!st.isEmpty() && q < n && popped[q] == st.peek()) {
                st.pop();
                q++;
            }
        }
        return st.isEmpty();
    }
}
