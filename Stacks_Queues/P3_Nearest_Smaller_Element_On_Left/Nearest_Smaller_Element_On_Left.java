package Stacks_Queues.P3_Nearest_Smaller_Element_On_Left;

import java.util.Stack;

public class Nearest_Smaller_Element_On_Left {
    public static void main(String[] args) {
        Nearest_Smaller_Element_On_Left solution = new Nearest_Smaller_Element_On_Left();

        int[] arr1 = { 1, 5, 0, 3, 4, 5 };
        int[] nse1 = solution.leftSmallerBruteForce(arr1);
        System.out.println(nse1);

        int[] arr2 = { 1, 6, 2 };
        int[] nse2 = solution.leftSmallerBruteForce(arr2);
        System.out.println(nse2);
    }

    /**
     * Approach II : Using Stack Approach
     * 
     * As we have inner loop j which is dependent on i as j starts backwards
     * from (i - 1) to 0 so we can reduce the time complexity to
     * Linear O(N) by using Stack data-structure
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public int[] leftSmallerOptimal(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (st.isEmpty()) {
                nse[i] = -1;
            } else {
                while (!st.isEmpty() && arr[i] <= st.peek()) {
                    st.pop();
                }
                if (st.isEmpty()) {
                    nse[i] = -1;
                } else {
                    nse[i] = st.peek();
                }
            }
            st.push(arr[i]);
        }
        return nse;
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(N ^ 2)
     * SC: O(1)
     */
    public int[] leftSmallerBruteForce(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        nse[0] = -1;
        for (int i = 1; i < n; i++) { // TC: O(N)
            boolean isFound = false;
            for (int j = i - 1; j >= 0; j--) { // TC: O(N)
                if (arr[j] < arr[i]) {
                    isFound = true;
                    nse[i] = arr[j];
                    break;
                }
            }
            if (!isFound) {
                nse[i] = -1;
            }
        }
        return nse;
    }
}
