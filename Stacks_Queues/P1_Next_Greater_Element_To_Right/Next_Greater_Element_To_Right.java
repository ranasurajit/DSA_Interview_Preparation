package Stacks_Queues.P1_Next_Greater_Element_To_Right;

import java.util.ArrayList;
import java.util.Stack;

public class Next_Greater_Element_To_Right {
    public static void main(String[] args) {
        Next_Greater_Element_To_Right solution = new Next_Greater_Element_To_Right();

        int[] arr1 = { 6, 8, 0, 1, 3 };
        ArrayList<Integer> nge1 = solution.nextLargerElementBruteForce(arr1);
        System.out.println(nge1);

        int[] arr2 = { 50, 40, 30, 10 };
        ArrayList<Integer> nge2 = solution.nextLargerElementBruteForce(arr2);
        System.out.println(nge2);
    }

    /**
     * Approach II : Using Stack Approach
     * 
     * As we have inner loop j which is dependent on i as j starts from (i + 1) to n
     * so we can reduce the time complexity to Linear O(N) by using Stack
     * data-structure
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public ArrayList<Integer> nextLargerElementOptimal(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> nge = new ArrayList<Integer>();
        int[] result = new int[n]; // SC: O(N)
        Stack<Integer> st = new Stack<Integer>();
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            if (st.isEmpty()) {
                result[i] = -1;
            } else {
                while (!st.isEmpty() && st.peek() <= arr[i]) {
                    st.pop();
                }
                if (st.isEmpty()) {
                    result[i] = -1;
                } else {
                    result[i] = st.peek();
                }
            }
            st.push(arr[i]);
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            nge.add(result[i]);
        }
        return nge;
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(N ^ 2)
     * SC: O(1)
     */
    public ArrayList<Integer> nextLargerElementBruteForce(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> nge = new ArrayList<Integer>();
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            boolean isFound = false;
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                if (arr[j] > arr[i]) {
                    nge.add(arr[j]);
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                nge.add(-1);
            }
        }
        nge.add(-1);
        return nge;
    }
}
