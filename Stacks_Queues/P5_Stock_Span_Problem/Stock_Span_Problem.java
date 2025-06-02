package Stacks_Queues.P5_Stock_Span_Problem;

import java.util.ArrayList;
import java.util.Stack;

public class Stock_Span_Problem {
    public static void main(String[] args) {
        Stock_Span_Problem solution = new Stock_Span_Problem();

        int[] arr1 = { 100, 80, 60, 70, 60, 75, 85 };
        ArrayList<Integer> spanList1 = solution.calculateSpanBruteForce(arr1);
        System.out.println(spanList1);

        int[] arr2 = { 10, 4, 5, 90, 120, 80 };
        ArrayList<Integer> spanList2 = solution.calculateSpanOptimal(arr2);
        System.out.println(spanList2);
    }

    /**
     * Approach II : Using Stacks Approach
     * 
     * The problem is similar to finding the index of nearest greater elemnt to left
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     * 
     * Accepted. Test Cases Passed: (1120 /1120)
     */
    public ArrayList<Integer> calculateSpanOptimal(int[] arr) {
        ArrayList<Integer> spanList = new ArrayList<Integer>();
        int n = arr.length;
        int[] result = new int[n]; // SC: O(N)
        // we will be storing { nearest greater element, index } in Stack
        Stack<int[]> st = new Stack<int[]>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (st.isEmpty()) {
                result[i] = -1;
            } else {
                while (!st.isEmpty() && arr[i] >= st.peek()[0]) {
                    // compared with pair's value from stack
                    st.pop();
                }
                if (st.isEmpty()) {
                    result[i] = -1;
                } else {
                    result[i] = st.peek()[1]; // setting index from stack
                }
            }
            st.push(new int[] { arr[i], i });
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            spanList.add(i - result[i]);
        }
        return spanList;
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(N ^ 2 + N) ~ O(N ^ 2)
     * SC: O(N)
     * 
     * Time limit exceeded. Test Cases Passed: (1110 /1120)
     */
    public ArrayList<Integer> calculateSpanBruteForce(int[] arr) {
        ArrayList<Integer> spanList = new ArrayList<Integer>();
        int n = arr.length;
        int[] span = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int count = 0;
            for (int j = i; j >= 0; j--) { // TC: O(N)
                if (arr[j] <= arr[i]) {
                    count++;
                } else {
                    break;
                }
            }
            span[i] = count;
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            spanList.add(span[i]);
        }
        return spanList;
    }
}
