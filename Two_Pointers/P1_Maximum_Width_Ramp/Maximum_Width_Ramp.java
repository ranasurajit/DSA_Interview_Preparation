package Two_Pointers.P1_Maximum_Width_Ramp;

public class Maximum_Width_Ramp {
    public static void main(String[] args) {
        int[] nums = { 9, 8, 1, 0, 1, 9, 4, 0, 4, 1 };

        int maxWidthBruteForce = maxWidthRampBruteForce(nums);
        System.out.println(maxWidthBruteForce);

        int maxWidthBetter = maxWidthRampBetter(nums);
        System.out.println(maxWidthBetter);

        int maxWidthOptimal = maxWidthRampOptimal(nums);
        System.out.println(maxWidthOptimal);
    }

    /**
     * Brute-Force Approach
     * 
     * TC: O(N ^ 2)
     * SC: O(1)
     * 
     * @param nums
     * @return
     */
    public static int maxWidthRampBruteForce(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++) { // O(N)
            for (int j = i + 1; j < n; j++) { // O(N)
                if (nums[i] <= nums[j]) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }

    /**
     * Better Approach
     * 
     * TC: O(N ^ 2)
     * SC: O(1)
     * 
     * @param nums
     * @return
     */
    public static int maxWidthRampBetter(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++) { // O(N)
            for (int j = n - 1; j >= i; j--) { // O(N)
                if (nums[i] <= nums[j]) {
                    max = Math.max(max, j - i);
                    break;
                }
            }
        }
        return max;
    }

    /**
     * Optimal Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     * 
     * @param nums
     * @return
     */
    public static int maxWidthRampOptimal(int[] nums) {
        int n = nums.length;
        // pre-process max from right
        int[] rightMax = new int[n]; // SC: O(N)
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        int max = 0;
        int i = 0; // pointer for 'nums' array from left
        int j = 0; // pointer for 'rightMax' array from left
        while (j < n) { // TC: O(N)
            if (i < j && nums[i] > rightMax[j]) {
                i++;
            }
            max = Math.max(max, j - i);
            j++;
        }
        return max;
    }
}
