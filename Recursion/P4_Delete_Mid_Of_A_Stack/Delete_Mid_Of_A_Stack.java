package Recursion.P4_Delete_Mid_Of_A_Stack;

import java.util.Stack;

public class Delete_Mid_Of_A_Stack {
    public static void main(String[] args) {
        Delete_Mid_Of_A_Stack solution = new Delete_Mid_Of_A_Stack();

        int[] nums = { 11, 2, 32, 3, 41 };
        Stack<Integer> s = new Stack<Integer>();
        for (int num : nums) {
            s.push(num);
        }
        System.out.println(s);
        solution.deleteMid(s);
        System.out.println(s);
    }

    /**
     * Approach : Using Recursion Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public void deleteMid(Stack<Integer> s) {
        int k = (int) Math.floor((s.size() + 1) / 2);
        // 1 based indexing
        k = s.size() % 2 == 0 ? k : k - 1;
        solveRecursion(s, k); // TC: O(N)
    }

    /**
     * TC: O(N / 2) ~ O(N)
     * SC: O(N / 2) ~ O(N)
     */
    private void solveRecursion(Stack<Integer> s, int k) {
        // Base Case
        if (k == 0) {
            // deleting mid of Stack
            s.pop();
            return;
        }
        // Hypothesis - we assume that recursion will delete mid of Stack s [0...(n -
        // 1)]so k decreases by 1
        int lastValue = s.pop();
        solveRecursion(s, k - 1);
        // Induction
        s.push(lastValue);
    }
}
