package Two_Pointers.P4_Trapping_Rain_Water;

public class Trapping_Rain_Water {
    public static void main(String[] args) {
        Trapping_Rain_Water solution = new Trapping_Rain_Water();

        int[] heights = { 3, 0, 1, 0, 4, 0, 2 };

        int maxWaterBF = solution.maxWaterBruteForce(heights);
        System.out.println(maxWaterBF);

        int maxWaterOpt = solution.maxWaterOptimal(heights);
        System.out.println(maxWaterOpt);
    }

    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param arr
     * @return
     */
    public int maxWaterOptimal(int arr[]) {
        int n = arr.length;
        int i = 0; // left pointer
        int j = n - 1; // right pointer
        int leftMax = arr[0];
        int rightMax = arr[n - 1];
        int waterCollected = 0;

        while (i < j) { // TC: O(N)
            leftMax = Math.max(leftMax, arr[i]);
            rightMax = Math.max(rightMax, arr[j]);
            if (arr[i] < arr[j]) {
                waterCollected += leftMax - arr[i];
                i++;
            } else {
                waterCollected += rightMax - arr[j];
                j--;
            }
        }
        return waterCollected;
    }

    /**
     * Using Array Pre-processing Approach
     * 
     * TC: O(3 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     * 
     * @param arr
     * @return
     */
    private int maxWaterBruteForce(int arr[]) {
        int n = arr.length;
        /**
         * we need to calculate the maximum height of buildings
         * from either sides for an index
         */
        int[] leftMax = new int[n]; // SC: O(N)
        int[] rightMax = new int[n]; // SC: O(N)
        // left to right
        leftMax[0] = arr[0];
        for (int i = 1; i < rightMax.length; i++) { // TC: O(N)
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }
        // right to left
        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
        }
        // water collected at any index = Min(leftMax[i], rightMax[i]) - height[i]
        int waterCollected = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            waterCollected += Math.min(leftMax[i], rightMax[i]) - arr[i];
        }
        return waterCollected;
    }
}
