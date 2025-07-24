package Greedy_Algorithms.P9_Jump_Game;

public class Jump_Game {
    public static void main(String[] args) {
        Jump_Game solution = new Jump_Game();

        int[] nums1 = { 2, 3, 1, 1, 4 };
        boolean canJump1 = solution.canJump(nums1);
        System.out.println(canJump1);

        int[] nums2 = { 3, 2, 1, 0, 4 };
        boolean canJump2 = solution.canJump(nums2);
        System.out.println(canJump2);
    }

    /**
     * Approach : Using Greedy Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxPosition = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i > maxPosition) {
                return false;
            }
            // for any index 'i', we can jump from (i + 1) to (i + nums[i]) position
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (maxPosition >= n - 1) {
                return true;
            }
        }
        return true;
    }
}
