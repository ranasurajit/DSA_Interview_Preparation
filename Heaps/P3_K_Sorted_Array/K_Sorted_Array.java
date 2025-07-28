package Heaps.P3_K_Sorted_Array;

import java.util.Arrays;

public class K_Sorted_Array {
    public static void main(String[] args) {
        K_Sorted_Array solution = new K_Sorted_Array();

        int n1 = 6;
        int[] arr1 = { 3, 2, 1, 5, 6, 4 };
        int k1 = 2;
        String isKSorted1 = solution.isKSortedArray(arr1, n1, k1);
        System.out.println(isKSorted1);

        int n2 = 7;
        int[] arr2 = { 13, 8, 10, 7, 15, 14, 12 };
        int k2 = 1;
        String isKSorted2 = solution.isKSortedArray(arr2, n2, k2);
        System.out.println(isKSorted2);
    }

    /**
     * Approach : Using Sorting + Simulation Approach
     * 
     * TC: O(N) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(2 x N) ~ O(N)
     */
    private String isKSortedArray(int arr[], int n, int k) {
        int[][] indexArr = new int[n][2]; // SC: O(2 x N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            indexArr[i] = new int[] { arr[i], i };
        }
        Arrays.sort(indexArr, (a, b) -> a[0] - b[0]); // TC: O(N x log(N))
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (Math.abs(i - indexArr[i][1]) > k) {
                return "No";
            }
        }
        return "Yes";
    }
}
