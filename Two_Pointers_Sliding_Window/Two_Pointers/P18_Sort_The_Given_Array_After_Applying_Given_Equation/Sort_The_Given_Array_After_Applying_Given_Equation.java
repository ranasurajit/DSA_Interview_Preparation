package Two_Pointers_Sliding_Window.Two_Pointers.P18_Sort_The_Given_Array_After_Applying_Given_Equation;

import java.util.ArrayList;
import java.util.Collections;

public class Sort_The_Given_Array_After_Applying_Given_Equation {
    public static void main(String[] args) {
        Sort_The_Given_Array_After_Applying_Given_Equation solution = new Sort_The_Given_Array_After_Applying_Given_Equation();

        int[] arr = { -4, -2, 0, 2, 4 };
        int A = 1;
        int B = 3;
        int C = 5;

        ArrayList<Integer> sortedList1 = solution.sortArrayBruteForce(arr, A, B, C);
        System.out.println(sortedList1);

        ArrayList<Integer> sortedList2 = solution.sortArray(arr, A, B, C);
        System.out.println(sortedList2);
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(N + N x log(N)) ~ O(N x log(N))
     * SC: O(1)
     * 
     * Time limit exceeded (1110 /1120 testcases passed)
     * 
     * @param arr
     * @param A
     * @param B
     * @param C
     * @return
     */
    public ArrayList<Integer> sortArrayBruteForce(int[] arr, int A, int B, int C) {
        int n = arr.length;
        ArrayList<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            sorted.add(computeEquation(arr[i], A, B, C));
        }
        Collections.sort(sorted); // TC: O(N x log(N))
        return sorted;
    }

    /**
     * Approach II : Using Two Pointers Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     * 
     * Accepted (1120 /1120 testcases passed)
     * 
     * @param arr
     * @param A
     * @param B
     * @param C
     * @return
     */
    public ArrayList<Integer> sortArray(int[] arr, int A, int B, int C) {
        int n = arr.length;
        ArrayList<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            sorted.add(0);
        }
        int index = A >= 0 ? n - 1 : 0;
        int i = 0; // left pointer at array 'arr'
        int j = n - 1; // right pointer at array 'arr'
        while (i <= j) { // TC: O(N)
            int leftEq = computeEquation(arr[i], A, B, C);
            int rightEq = computeEquation(arr[j], A, B, C);
            if (A >= 0) {
                if (leftEq >= rightEq) {
                    sorted.set(index, leftEq);
                    i++;
                    index--;
                } else {
                    sorted.set(index, rightEq);
                    j--;
                    index--;
                }
            } else {
                if (leftEq <= rightEq) {
                    sorted.set(index, leftEq);
                    i++;
                    index++;
                } else {
                    sorted.set(index, rightEq);
                    j--;
                    index++;
                }
            }
        }
        return sorted;
    }

    /**
     * TC: O(1)
     * SC: O(1)
     * 
     * @param x
     * @param a
     * @param b
     * @param c
     * @return
     */
    private int computeEquation(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
