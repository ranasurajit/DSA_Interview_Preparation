package Backtracking.P8_Non_Decreasing_Subsequences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Non_Decreasing_Subsequences {
    public static void main(String[] args) {
        Non_Decreasing_Subsequences solution = new Non_Decreasing_Subsequences();

        int[] nums1 = { 4, 6, 7, 7 };
        List<List<Integer>> subsequences1 = solution.findSubsequencesApproachI(nums1);
        System.out.println(subsequences1);

        int[] nums2 = { 4, 4, 3, 2, 1 };
        List<List<Integer>> subsequences2 = solution.findSubsequencesApproachII(nums2);
        System.out.println(subsequences2);
    }

    /**
     * Approach II : Using Recursion + Backtracking (Standard Backtracking Template)
     * Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N x 2 ^ N + 2 x N) ~ O(N x 2 ^ N) - (2^N subsequences x N (length of
     * each subsequence))
     */
    public List<List<Integer>> findSubsequencesApproachII(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> result = new HashSet<List<Integer>>(); // SC: O(N x 2 ^ N)
        List<Integer> current = new ArrayList<Integer>(); // SC: O(N)
        solveBacktrack(0, n, nums, current, result); // TC: O(2 ^ N), SC: O(N)
        return new ArrayList<List<Integer>>(result);
    }

    /**
     * Using Recursion + Backtracking (Standard Backtracking Template) Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solveBacktrack(int index, int n, int[] nums, List<Integer> current,
            Set<List<Integer>> result) {
        // Base Case
        if (current.size() > 1) {
            result.add(new ArrayList<Integer>(current));
        }
        // Recursion Calls
        Set<Integer> set = new HashSet<Integer>();
        for (int i = index; i < n; i++) {
            if ((current.isEmpty() || nums[i] >= current.get(current.size() - 1)) &&
                    !set.contains(nums[i])) {
                // modify
                current.add(nums[i]);
                // explore
                solveBacktrack(i + 1, n, nums, current, result);
                // backtrack
                current.remove(current.size() - 1);
                set.add(nums[i]);
            }
        }
    }

    /**
     * Approach I : Using Recursion + Backtracking (Knapsack Recursion Template)
     * Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N x 2 ^ N + 2 x N) ~ O(N x 2 ^ N) - (2^N subsequences x N (length of
     * each subsequence))
     */
    public List<List<Integer>> findSubsequencesApproachI(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> result = new HashSet<List<Integer>>(); // SC: O(N x 2 ^ N)
        List<Integer> current = new ArrayList<Integer>(); // SC: O(N)
        solveRecursion(0, -1, n, nums, current, result); // TC: O(2 ^ N), SC: O(N)
        return new ArrayList<List<Integer>>(result);
    }

    /**
     * Using Recursion + Backtracking Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(int index, int prevIndex, int n, int[] nums, List<Integer> current,
            Set<List<Integer>> result) {
        // Base Case
        if (index == n) {
            if (current.size() > 1) {
                result.add(new ArrayList<Integer>(current));
            }
            return;
        }
        // we have options to take or skip
        // skip
        solveRecursion(index + 1, prevIndex, n, nums, current, result);
        // take
        if (prevIndex == -1 || nums[index] >= nums[prevIndex]) {
            // modify
            current.add(nums[index]);
            // explore
            solveRecursion(index + 1, index, n, nums, current, result);
            // backtrack
            current.remove(current.size() - 1);
        }
    }
}
