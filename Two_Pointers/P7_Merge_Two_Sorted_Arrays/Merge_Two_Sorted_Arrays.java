package Two_Pointers.P7_Merge_Two_Sorted_Arrays;

import java.util.Arrays;

public class Merge_Two_Sorted_Arrays {
    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        int[] arr1 = { 1, 10 };
        int[] arr2 = { 3, 4, 6 };

        int[] merged = ninjaAndSortedArrays(arr1, arr2, m, n);
        System.out.println(Arrays.toString(merged));
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(M + N)
     * SC: O(1)
     */
    public static int[] ninjaAndSortedArrays(int arr1[], int arr2[], int m, int n) {
        int[] merged = new int[m + n];
        int p = 0; // pointer for the array 'arr1'
        int q = 0; // pointer for the array 'arr2'
        int k = 0; // pointer for the array 'merged'
        while (p < m && q < n) { // TC: O(M + N)
            if (arr1[p] < arr2[q]) {
                merged[k++] = arr1[p++];
            } else {
                merged[k++] = arr2[q++];
            }
        }
        if (p < m) {
            while (p < m) {
                merged[k++] = arr1[p++];
            }
        }
        if (q < n) {
            while (q < n) {
                merged[k++] = arr2[q++];
            }
        }
        return merged;
    }
}
