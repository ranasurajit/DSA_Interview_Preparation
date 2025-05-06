package Recursion.P2_Sort_An_Array;

import java.util.ArrayList;

public class Sort_An_Array {
    public static void main(String[] args) {
        Sort_An_Array solution = new Sort_An_Array();

        int[] nums = { 14, 2, 7, 18, 2, 1, 1, -9, 77, 44 };

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int num : nums) {
            list.add(num);
        }
        solution.sortArray(list, list.size());
        System.out.println(list);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N ^ 2)
     * SC: O(N)
     */
    public void sortArray(ArrayList<Integer> arr, int n) {
        // Base Case
        if (n == 1) {
            // single element is self-sorted
            return;
        }
        // Hypothesis - assume that this method will return sorted arr between indices
        // [0...(n - 2)]
        int lastValue = arr.remove(n - 1);
        sortArray(arr, n - 1);
        // Induction - we need to insert lastValue to its sorted position
        insertValueInSortedArray(arr, lastValue); // TC: O(N)
    }

    /**
     * TC: O(N)
     * SC: O(N)
     */
    private void insertValueInSortedArray(ArrayList<Integer> arr, int element) {
        // Base Case
        if (arr.size() == 0 || element >= arr.get(arr.size() - 1)) {
            arr.add(element);
            return;
        }
        // Hypothesis - assume that this method will insert element for arr between
        // indices [0...(n - 3)]
        int last = arr.remove(arr.size() - 1);
        insertValueInSortedArray(arr, element);
        // Induction - we need to add last
        arr.add(last);
    }
}
