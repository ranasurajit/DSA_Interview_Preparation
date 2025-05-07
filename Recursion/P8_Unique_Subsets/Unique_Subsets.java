package Recursion.P8_Unique_Subsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Unique_Subsets {
    public static void main(String[] args) {
        Unique_Subsets solution = new Unique_Subsets();

        int N = 3;
        int[] arr = { 2, 1, 2 };

        ArrayList<ArrayList<Integer>> uniques = solution.AllSubsets(arr, N);
        System.out.println(uniques);
    }

    /**
     * Approach : Using Recursion and Sorting Approach
     * 
     * TC: O(2 x N x 2 ^ N) ~ O(N x 2 ^ N)
     * SC: O(N x 2 ^ N)
     */
    public ArrayList<ArrayList<Integer>> AllSubsets(int arr[], int n) {
        ArrayList<ArrayList<Integer>> uniques = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> current = new ArrayList<Integer>();
        solveRecursion(0, arr, n, current, uniques); // TC: O(2 ^ N), SC: O(N)
        for (ArrayList<Integer> list : uniques) { // TC: O(2 ^ N)
            Collections.sort(list); // TC: O(log(2 ^ N)) ~ O(N)
        }
        // removing duplicate lists
        Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>(uniques);
        uniques = new ArrayList<ArrayList<Integer>>(set);
        // sorting result lexicographically
        uniques.sort((a, b) -> { // TC: O(2 ^ N x log(2 ^ N)) ~ O(N x 2 ^ N)
            int len = Math.min(a.size(), b.size());
            for (int i = 0; i < len; i++) {
                int comp = Integer.compare(a.get(i), b.get(i));
                if (comp != 0) {
                    return comp;
                }
            }
            return a.size() - b.size();
        });
        return uniques;
    }

    /**
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(int idx, int[] arr, int n, ArrayList<Integer> current,
            ArrayList<ArrayList<Integer>> uniques) {
        // Base Case
        if (idx == n) {
            uniques.add(new ArrayList<Integer>(current));
            return;
        }
        // Recursion Calls
        // not take
        solveRecursion(idx + 1, arr, n, current, uniques);
        // take
        current.add(arr[idx]);
        solveRecursion(idx + 1, arr, n, current, uniques);
        // backtrack to explore all other possibilities
        current.remove(current.size() - 1);
    }
}
