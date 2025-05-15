package Two_Pointers_Sliding_Window.Two_Pointers.P12_Count_The_Number_Of_Possible_Triangles;

import java.util.Arrays;

public class Count_The_Number_Of_Possible_Triangles {
    public static void main(String[] args) {
        Count_The_Number_Of_Possible_Triangles solution = new Count_The_Number_Of_Possible_Triangles();

        int[] arr = { 10, 21, 22, 100, 101, 200, 300 };
        int counts = solution.countTriangles(arr);
        System.out.println(counts);
    }

    /**
     * Approach : Using Two Pointers and Sorting Approach
     *
     * TC: O(N ^ 2)
     * SC: O(1)
     */
    private int countTriangles(int arr[]) {
        int n = arr.length;
        Arrays.sort(arr);
        /**
         * for possible triangles, we need to find pairs (i, j, k)
         * such that arr[i] < arr[j] + arr[k]
         */
        int count = 0;
        for (int i = n - 1; i >= 2; i--) { // TC: O(N)
            int largest = arr[i];
            int p = 0; // start index
            int q = i - 1;
            while (p < q) { // TC: O(N)
                if (arr[p] + arr[q] > largest) {
                    // as all numbers in range [p...q] will form pairs
                    count += (q - p);
                    q--;
                } else {
                    p++;
                }
            }
        }
        return count;
    }
}
