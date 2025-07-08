package Binary_Search.One_Dimensional.P18_Longest_Increasing_Subsequence;

import java.util.ArrayList;
import java.util.List;

public class Longest_Increasing_Subsequence {
    public static void main(String[] args) {
        Longest_Increasing_Subsequence solution = new Longest_Increasing_Subsequence();

        int nums[] = { 10, 9, 2, 5, 3, 7, 101, 18 };

        int lengthOfLIS = solution.lengthOfLIS(nums);
        System.out.println(lengthOfLIS);
    }

    /**
     * Approach VI : Using Binary Search (Most Optimized) Approach
     * 
     * TC: O(N x log(N))
     * SC: O(N)
     * 
     * - O(N) - sorted ArrayList 'track' memory
     *
     * Accepted (55 / 55 testcases passed)
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        List<Integer> track = new ArrayList<Integer>(); // SC: O(N) - sorted ArrayList
        track.add(nums[0]);
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (nums[i] > track.get(track.size() - 1)) {
                track.add(nums[i]);
            } else {
                // we need to substitute the nums[i] at the right position in sorted ArrayList
                // 'track'
                int idx = lowerBound(track, nums[i]); // TC: O(log(N)), SC: O(1)
                track.set(idx, nums[i]);
            }
        }
        return track.size();
    }

    /**
     * Using Binary Search to find Lower Bound Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    private int lowerBound(List<Integer> track, int x) {
        int low = 0;
        int high = track.size() - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (track.get(mid) >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
