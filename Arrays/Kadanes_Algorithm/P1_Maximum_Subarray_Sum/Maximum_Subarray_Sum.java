package Arrays.Kadanes_Algorithm.P1_Maximum_Subarray_Sum;

public class Maximum_Subarray_Sum {
    public static void main(String[] args) {
        int n = 9;
        int[] arr = { 1, 2, 7, -4, 3, 2, -10, 9, 1 };
        long maxSum = maxSubarraySum(arr, n);
        System.out.println(maxSum);
    }

    /**
     * Using Kadane's Algorithm Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public static long maxSubarraySum(int[] arr, int n) {
        long maxSum = 0L;
        long currentSum = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            currentSum += arr[i];
            if (currentSum < 0) {
                currentSum = 0;
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
