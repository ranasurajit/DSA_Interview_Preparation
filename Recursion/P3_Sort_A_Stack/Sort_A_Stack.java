package Recursion.P3_Sort_A_Stack;

import java.util.Stack;

public class Sort_A_Stack {
    public static void main(String[] args) {
        Sort_A_Stack solution = new Sort_A_Stack();

        int n = 5;
        int[] nums = { 11, 2, 32, 3, 41 };
        Stack<Integer> s = new Stack<Integer>();
        for (int num : nums) {
            s.push(num);
        }
        System.out.println(s);
        solution.sortStack(s, n);
        System.out.println(s);
    }

    /**
     * Approach : Using Recursion Approach
     * 
     * TC: O(N ^ 2)
     * SC: O(N)
     */
    public Stack<Integer> sort(Stack<Integer> s) {
        sortStack(s, s.size());
        return s;
    }

    /**
     * TC: O(N ^ 2)
     * SC: O(N)
     */
    private void sortStack(Stack<Integer> s, int n) {
        // Base Case
        if (n == 1) {
            // Stack ha sone element and is self-sorted
            return;
        }
        // Hypothesis - we assume that recursion will give a sorted Stack with length
        // [0, n - 1]
        int lastValue = s.pop(); // decreased Stack's size by 1
        sortStack(s, n - 1);
        // Induction - Here we need to insert the lastValue at appropriate position in
        // sorted order
        insertInSortedStack(s, lastValue); // TC: O(N)
    }

    /**
     * TC: O(N)
     * SC: O(N)
     */
    private void insertInSortedStack(Stack<Integer> s, int element) {
        // Base Case
        if (s.isEmpty() || element >= s.peek()) {
            s.push(element);
            return;
        }
        // Hypothesis - we assume that recursion will insert element into Stack of size
        // [0, size - 1]
        int last = s.pop(); // decreased Stack's size by 1
        insertInSortedStack(s, element);
        // Induction - we need to add the last element (already sorted)
        s.push(last);
    }
}
