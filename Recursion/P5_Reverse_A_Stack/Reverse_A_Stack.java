package Recursion.P5_Reverse_A_Stack;

import java.util.Stack;

public class Reverse_A_Stack {
    public static void main(String[] args) {
        Reverse_A_Stack solution = new Reverse_A_Stack();

        int[] nums = { 11, 2, 32, 3, 41 };
        Stack<Integer> s = new Stack<Integer>();
        for (int num : nums) {
            s.push(num);
        }
        System.out.println(s);
        solution.reverse(s);
        System.out.println(s);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N ^ 2)
     * SC: O(N)
     */
    private void reverse(Stack<Integer> s) {
        solveRecursion(s, s.size());
    }

    /**
     * TC: O(N ^ 2)
     * SC: O(N)
     */
    private void solveRecursion(Stack<Integer> s, int n) {
        // Base Case
        if (n == 1) {
            // it is reversed already
            return;
        }
        // Hypothesis - we assume that recursion method will already deduce reverse of
        // stack of size [0, (n - 1)]
        int lastValue = s.pop(); // Stack size is reduced
        solveRecursion(s, n - 1); // we get a reversed Stack here
        // Induction
        insertInReversedStack(s, lastValue); // TC: O(N)
    }

    /**
     * TC: O(N)
     * SC: O(N)
     */
    private void insertInReversedStack(Stack<Integer> s, int element) {
        // Base Case
        if (s.isEmpty()) {
            s.push(element);
            return;
        }
        // Hypothesis - we assume that recursion will insert element with Stack size
        // (size - 1)
        int last = s.pop();
        insertInReversedStack(s, element);
        // Induction
        s.push(last); // as Stack s is already revered for (size - 1)
    }
}
