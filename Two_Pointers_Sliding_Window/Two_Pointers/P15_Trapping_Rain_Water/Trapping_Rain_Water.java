package Two_Pointers_Sliding_Window.Two_Pointers.P15_Trapping_Rain_Water;

public class Trapping_Rain_Water {
    public static void main(String[] args) {
        Trapping_Rain_Water solution = new Trapping_Rain_Water();

        int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

        int trappedWaterApproach1 = solution.trapApproachI(height);
        System.out.println(trappedWaterApproach1);

        int trappedWaterApproach2 = solution.trapApproachII(height);
        System.out.println(trappedWaterApproach2);
    }

    /**
     * Approach II : Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int trapApproachII(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int leftMax = 0;
        int rightMax = 0;
        int trapped = 0;
        while (left <= right) { // TC: O(N)
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    trapped += (leftMax - height[left]);
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    trapped += (rightMax - height[right]);
                }
                right--;
            }
        }
        return trapped;
    }

    /**
     * Approach I : Using Array Pre-processing Approach
     *
     * TC: O(3 x N) ~ O(N)
     * SC: O(2 x N)
     */
    public int trapApproachI(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n]; // SC: O(N)
        int[] rightMax = new int[n]; // SC: O(N)
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) { // TC: O(N)
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int trapped = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            trapped += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return trapped;
    }
}
