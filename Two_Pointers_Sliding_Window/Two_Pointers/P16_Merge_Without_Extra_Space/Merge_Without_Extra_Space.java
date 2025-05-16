package Two_Pointers_Sliding_Window.Two_Pointers.P16_Merge_Without_Extra_Space;

import java.util.Arrays;

public class Merge_Without_Extra_Space {
    public static void main(String[] args) {
        Merge_Without_Extra_Space solution = new Merge_Without_Extra_Space();

        int[] a = { 1, 5, 9, 10, 15, 20 };
        int[] b = { 2, 3, 8, 12 };

        solution.mergeArraysApproachI(a, b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        int[] c = { 2, 4, 7, 10 };
        int[] d = { 2, 3 };

        solution.mergeArraysApproachII(c, d);
        System.out.println(Arrays.toString(c));
        System.out.println(Arrays.toString(d));
    }

    /**
     * Approach II : Using Two Pointers and GAP Method/Shell Sort Approach
     * 
     * TC: O((M + N)x log(M + N))
     * SC: O(1)
     */
    public void mergeArraysApproachII(int a[], int b[]) {
        int m = a.length;
        int n = b.length;
        int len = m + n;
        int gap = (len / 2) + (len % 2);
        while (gap > 0) { // TC: O(Gap) ~ O((M + N)x log(M + N))
            int left = 0;
            int right = left + gap;
            while (right < len) {
                if (right < m) {
                    // both left and right pointers in array 'a'
                    swapIfGreater(a, a, left, right);
                } else if (left < m && right >= m) {
                    // both left and right pointers in array 'a' and 'b' respectively
                    swapIfGreater(a, b, left, right - m);
                } else if (left >= m) {
                    // both left and right pointers in array 'b'
                    swapIfGreater(b, b, left - m, right - m);
                }
                left++;
                right++;
            }
            if (gap == 1) {
                break;
            }
            gap = (gap / 2) + (gap % 2);
        }
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    private void swapIfGreater(int[] arr1, int[] arr2, int p, int q) {
        if (arr1[p] < arr2[q]) {
            return;
        }
        // swap
        int temp = arr2[q];
        arr2[q] = arr1[p];
        arr1[p] = temp;
    }

    /**
     * Approach I : Using Two Pointers and Extra Space
     * 
     * TC: O(2 x (M + N)) ~ O(M + N)
     * SC: O(M + N)
     */
    public void mergeArraysApproachI(int a[], int b[]) {
        int m = a.length;
        int n = b.length;
        int[] result = new int[m + n]; // SC: O(M + N)
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            if (a[i] < b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < m) {
            result[k++] = a[i++];
        }
        while (j < n) {
            result[k++] = b[j++];
        }
        int p = 0;
        for (p = 0; p < m; p++) { // TC: O(M)
            a[p] = result[p];
        }
        for (int q = 0; q < n; q++) { // TC: O(N)
            b[q] = result[m + q];
        }
    }
}
