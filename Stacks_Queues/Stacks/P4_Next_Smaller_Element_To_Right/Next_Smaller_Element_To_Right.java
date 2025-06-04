package Stacks_Queues.Stacks.P4_Next_Smaller_Element_To_Right;

import java.util.ArrayList;
import java.util.Stack;

import Arrays.Utils.ArrayUtils;

public class Next_Smaller_Element_To_Right {
    public static void main(String[] args) {
        Next_Smaller_Element_To_Right solution = new Next_Smaller_Element_To_Right();

        int[] nums1 = { 2, 1, 4, 3 };
        int n1 = 4;
        ArrayList<Integer> arr1 = ArrayUtils.convert1DArayToArrayList(nums1);

        ArrayList<Integer> nse1 = solution.nextSmallerElementBruteForce(arr1, n1);
        System.out.println(nse1);

        int[] nums2 = { 1, 2, 3, 4 };
        int n2 = 4;
        ArrayList<Integer> arr2 = ArrayUtils.convert1DArayToArrayList(nums2);

        ArrayList<Integer> nse2 = solution.nextSmallerElementOptimal(arr2, n2);
        System.out.println(nse2);
    }

    /**
     * Approach II : Using Stack Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     */
    private ArrayList<Integer> nextSmallerElementOptimal(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int[] nse = new int[n]; // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            if (st.isEmpty()) {
                nse[i] = -1;
            } else {
                while (!st.isEmpty() && arr.get(i) <= st.peek()) {
                    st.pop();
                }
                nse[i] = st.isEmpty() ? -1 : st.peek();
            }
            st.push(arr.get(i));
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            result.add(nse[i]);
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(N ^ 2 + N) ~ O(N ^ 2)
     * SC: O(N)
     */
    private ArrayList<Integer> nextSmallerElementBruteForce(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int[] nse = new int[n]; // SC: O(N)
        nse[n - 1] = -1;
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            boolean isFound = false;
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                if (arr.get(j) < arr.get(i)) {
                    nse[i] = arr.get(j);
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                nse[i] = -1;
            }
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            result.add(nse[i]);
        }
        return result;
    }
}
