package Hashing.P1_Two_Sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Two_Sum {
    public static void main(String[] args) {
        Two_Sum solution = new Two_Sum();

        int target = 3;
        int[] nums = { 1, 2, 2, 1, 2, 1 };
        int[] result = solution.twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[] result = new int[] { -1, -1 };
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                result[0] = map.get(diff);
                result[1] = i;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}
