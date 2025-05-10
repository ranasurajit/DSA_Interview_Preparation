package Two_Pointers_Sliding_Window.Sliding_Window.Dynamic_Window_Size.P7_Binary_Subarrays_With_Sum;

import java.util.HashMap;
import java.util.Map;

public class Binary_Subarrays_With_Sum {
    public static void main(String[] args) {
        Binary_Subarrays_With_Sum solution = new Binary_Subarrays_With_Sum();

        int[] nums = { 1, 0, 1, 0, 1 };
        int goal = 2;

        int countSolution1 = solution.numSubarraysWithSumHashing(nums, goal);
        System.out.println(countSolution1);

        int countSolution2 = solution.numSubarraysWithSumSlidingWindow(nums, goal);
        System.out.println(countSolution2);
    }

    /**
     * Approach I : Using Hashing Approach
     * 
     * Problem is similar to count sub-arrays with sum = k (goal)
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param nums
     * @param goal
     * @return
     */
    public int numSubarraysWithSumHashing(int[] nums, int goal) {
        int n = nums.length;
        // storing { prefixSum , frequency } in HashMap
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        map.put(0, 1);
        int prefixSum = 0;
        int count = 0;
        int i = 0;
        while (i < n) { // TC: O(N)
            prefixSum += nums[i];
            int reqSum = prefixSum - goal;
            if (map.containsKey(reqSum)) {
                count += map.get(reqSum);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
            i++;
        }
        return count;
    }

    /**
     * Approach II : Using Sliding Window (Variable Size) Approach
     * 
     * We can find countSubArraysWithSum(<= goal) - We can find
     * countSubArraysWithSum(<= goal - 1)
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     * 
     * @param nums
     * @param goal
     * @return
     */
    public int numSubarraysWithSumSlidingWindow(int[] nums, int goal) {
        int n = nums.length;
        return countSubArraysWithSumLessThanEqualsK(nums, n, goal) -
                countSubArraysWithSumLessThanEqualsK(nums, n, goal - 1);
    }

    /**
     * Using Sliding Window (Variable Size) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param nums
     * @param n
     * @param k
     * @return
     */
    private int countSubArraysWithSumLessThanEqualsK(int[] nums, int n, int k) {
        // as we are looking for sum > 0 so we return 0 if goal < 0
        if (k < 0) {
            return 0;
        }
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int sum = 0;
        int count = 0;
        while (j < n) { // TC: O(N)
            sum += nums[j];
            while (sum > k) {
                // remove the computation from index 'i'
                sum -= nums[i];
                i++;
            }
            count += (j - i + 1);
            j++;
        }
        return count;
    }
}
