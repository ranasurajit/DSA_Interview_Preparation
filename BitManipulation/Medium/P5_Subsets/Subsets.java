package BitManipulation.Medium.P5_Subsets;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        Subsets solution = new Subsets();

        int[] nums1 = { 1, 2, 3 };
        List<List<Integer>> subsets1 = solution.subsetsUsingRecursion(nums1);
        System.out.println(subsets1);

        int[] nums2 = { 1, 2, 3, 1 };
        List<List<Integer>> subsets2 = solution.subsetsUsingPowerSet(nums2);
        System.out.println(subsets2);
    }

    /**
     * Approach II : Using PowerSet Approach
     *
     * TC: O(N x 2 ^ N)
     * SC: O(N)
     */
    public List<List<Integer>> subsetsUsingPowerSet(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int combinations = (int) Math.pow(2, n); // number of possible subsets
        for (int i = 0; i < combinations; i++) { // TC: O(2 ^ N)
            List<Integer> current = new ArrayList<Integer>(); // SC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (((i >> j) & 1) == 1) {
                    // jth bit is set in Integer i
                    current.add(nums[j]);
                }
            }
            result.add(current);
        }
        return result;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public List<List<Integer>> subsetsUsingRecursion(int[] nums) {
        int n = nums.length;
        List<Integer> current = new ArrayList<Integer>(); // SC: O(N)
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        solveRecursion(0, n, nums, current, result); // TC: O(2 ^ N), SC: O(N)
        return result;
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(int idx, int n, int[] nums, List<Integer> current,
            List<List<Integer>> result) {
        // Base Case
        if (idx == n) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        // Recursion Case
        // we can opt to pick or skip
        // pick
        current.add(nums[idx]);
        solveRecursion(idx + 1, n, nums, current, result);
        // skip
        current.remove(current.size() - 1); // backtrack
        solveRecursion(idx + 1, n, nums, current, result);
    }
}
