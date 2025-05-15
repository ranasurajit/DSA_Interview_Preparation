package Two_Pointers_Sliding_Window.Two_Pointers.P13_Indexes_Of_Subarray_Sum;

import java.util.ArrayList;

public class Indexes_Of_Subarray_Sum {
    public static void main(String[] args) {
        Indexes_Of_Subarray_Sum solution = new Indexes_Of_Subarray_Sum();

        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int target = 15;

        ArrayList<Integer> indices = solution.subarraySum(arr, target);
        System.out.println(indices);
    }

    /**
     * Approach : Using Two Pointers and Sliding Window Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private ArrayList<Integer> subarraySum(int[] arr, int target) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int n = arr.length;
        int i = 0; // start pointer of window
        int j = 0; // end pointer of window
        int sum = 0;
        while (j < n) { // TC: O(N)
            sum += arr[j];
            while (sum > target) {
                // remove computation of index 'i'
                sum -= arr[i];
                i++;
            }
            if (sum == target) {
                result.add(i + 1);
                result.add(j + 1);
                break;
            }
            j++;
        }
        if (result.isEmpty()) {
            result.add(-1);
        }
        return result;
    }
}
