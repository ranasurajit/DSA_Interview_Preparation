package Two_Pointers_Sliding_Window.Two_Pointers.P10_Three_Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Three_Sum {
    public static void main(String[] args) {
        Three_Sum solution = new Three_Sum();

        int[] nums = { -1, 0, 1, 2, -1, -4 };

        List<List<Integer>> tripletsBruteForce = solution.threeSumBruteForce(nums);
        System.out.println(tripletsBruteForce);

        List<List<Integer>> tripletsOptimal = solution.threeSumOptimal(nums);
        System.out.println(tripletsOptimal);
    }

    /**
     * Approach I : Using Simulation Approach
     *
     * TC: O(N ^ 3)
     * SC: O(1)
     *
     * Time Limit Exceeded (309 / 314 testcases passed)
     */
    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> result = new HashSet<List<Integer>>();
        for (int i = 0; i < n - 2; i++) { // TC: O(N)
            for (int j = i + 1; j < n - 1; j++) { // TC: O(N)
                for (int k = j + 1; k < n; k++) { // TC: O(N)
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(triplet); // TC: O(3 x log(3)) ~ O(1)
                        result.add(triplet);
                    }
                }
            }
        }
        return new ArrayList<List<Integer>>(result);
    }

    /**
     * Approach II : Using Two Pointers and Sorting Approach
     *
     * TC: O(N ^ 2 + N x log(N)) ~ O(N ^ 2)
     * SC: O(1)
     *
     * Accepted (314 / 314 testcases passed)
     */
    public List<List<Integer>> threeSumOptimal(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // as index is not needed so order does not matter
        Arrays.sort(nums); // TC: O(N x log(N))
        for (int i = 0; i < n - 2; i++) { // TC: O(N)
            // nums[i] + nums[j] + nums[k] == 0 implies nums[j] + nums[k] == -1 * nums[i]
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -1 * nums[i];
            // find the other two numbers of triplet
            twoSum(nums, target, i + 1, result); // TC: O(N)
        }
        return result;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private void twoSum(int[] nums, int target, int start, List<List<Integer>> result) {
        int p = start;
        int q = nums.length - 1;
        while (p < q) { // TC: O(N)
            if (nums[p] + nums[q] == target) {
                result.add(Arrays.asList(-1 * target, nums[p], nums[q]));
                // remove duplicate elements
                while (p < q && nums[p] == nums[p + 1]) {
                    p++;
                }
                // remove duplicate elements
                while (p < q && nums[q] == nums[q - 1]) {
                    q--;
                }
                p++;
                q--;
            } else if (nums[p] + nums[q] < target) {
                p++;
            } else {
                q--;
            }
        }
    }
}
