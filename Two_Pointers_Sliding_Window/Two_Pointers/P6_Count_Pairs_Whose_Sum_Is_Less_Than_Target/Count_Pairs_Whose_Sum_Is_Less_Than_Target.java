package Two_Pointers_Sliding_Window.Two_Pointers.P6_Count_Pairs_Whose_Sum_Is_Less_Than_Target;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Count_Pairs_Whose_Sum_Is_Less_Than_Target {
    public static void main(String[] args) {
        Count_Pairs_Whose_Sum_Is_Less_Than_Target solution = new Count_Pairs_Whose_Sum_Is_Less_Than_Target();

        int[] arr = { 5, 2, 3, 2, 4, 1 };
        int target = 5;

        int countBruteForce = solution.countPairsBruteForce(arr, target);
        System.out.println(countBruteForce);

        int countOptimal = solution.countPairsOptimal(arr, target);
        System.out.println(countOptimal);
    }

    /**
     * Approach II : Using Two Pointers and Sorting Approach
     * 
     * TC: O(N + N x log(N)) ~ O(N x log(N))
     * SC: O(1)
     */
    int countPairsOptimal(int arr[], int target) {
        int n = arr.length;
        // Since we need pairs so order does not matter so we can sort array 'arr'
        Arrays.sort(arr); // TC: O(N x log(N))
        int p = 0; // start pointer
        int q = n - 1; // end pointer
        int count = 0;
        while (p < q) { // TC: O(N)
            int sum = arr[p] + arr[q];
            if (sum < target) {
                count += (q - p);
                p++;
            } else {
                q--;
            }
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force Approach
     * 
     * TC: O(N ^ 2)
     * SC: O(N ^ 2)
     */
    int countPairsBruteForce(int arr[], int target) {
        int n = arr.length;
        Set<int[]> set = new HashSet<int[]>(); // SC: O(N ^ 2)
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            for (int j = i + 1; j < n; j++) { // TC: O(N)
                int sum = arr[i] + arr[j];
                if (sum < target) {
                    set.add(new int[] { arr[i], arr[j] });
                }
            }
        }
        return set.size();
    }
}
