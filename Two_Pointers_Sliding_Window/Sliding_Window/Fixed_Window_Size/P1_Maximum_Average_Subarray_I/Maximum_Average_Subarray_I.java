package Two_Pointers_Sliding_Window.Sliding_Window.Fixed_Window_Size.P1_Maximum_Average_Subarray_I;

public class Maximum_Average_Subarray_I {
    public static void main(String[] args) {
        Maximum_Average_Subarray_I solution = new Maximum_Average_Subarray_I();

        int[] nums = { 1, 12, -5, -6, 50, 3 };
        int k = 4;
        double maximumAvg = solution.findMaxAverage(nums, k);
        System.out.println(maximumAvg);
    }

    /**
     * Approach : Using Sliding Window (Fixed Length) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        double maxSum = (double) Integer.MIN_VALUE;
        double sum = 0d;
        while (j < n) { // TC: O(N)
            sum += nums[j];
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                // sliding window length is met
                maxSum = Math.max(maxSum, sum);
                // remove computation from index 'i';
                sum -= nums[i];
                // slide the window to next valid window
                i++;
                j++;
            }
        }
        return maxSum / k;
    }
}
