package Stacks_Queues.P2_Next_Greater_Element_To_Left;

import java.util.ArrayList;
import java.util.Stack;

public class Next_Greater_Element_To_Left {
    public static void main(String[] args) {
        Next_Greater_Element_To_Left solution = new Next_Greater_Element_To_Left();

        int[] arr1 = { 6, 8, 0, 1, 3 };
        ArrayList<Integer> nge1 = solution.nextLargerElementOptimal(arr1);
        System.out.println(nge1);

        int[] arr2 = { 50, 40, 30, 10 };
        ArrayList<Integer> nge2 = solution.nextLargerElementOptimal(arr2);
        System.out.println(nge2);
    }

    /**
     * Approach : Using Stack Approach
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
        for (int i = 0; i < n; i++) {
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
        for (int i = 0; i < n; i++) {
            nge.add(result[i]);
        }
        return nge;
    }
}
