package BitManipulation.Medium.P7_Find_The_Original_Array_Of_Prefix_XOR;

import java.util.Arrays;

public class Find_The_Original_Array_Of_Prefix_XOR {
    public static void main(String[] args) {
        Find_The_Original_Array_Of_Prefix_XOR solution = new Find_The_Original_Array_Of_Prefix_XOR();

        int[] pref = { 5, 2, 0, 3, 1 };
        int[] arr = solution.findArray(pref);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int[] findArray(int[] pref) {
        int n = pref.length;
        int[] arr = new int[n];
        arr[0] = pref[0];
        for (int i = 1; i < n; i++) { // TC: O(N)
            arr[i] = pref[i] ^ pref[i - 1];
        }
        return arr;
    }
}
