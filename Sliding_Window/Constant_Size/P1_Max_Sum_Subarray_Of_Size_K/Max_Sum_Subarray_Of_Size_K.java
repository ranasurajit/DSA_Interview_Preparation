package Sliding_Window.Constant_Size.P1_Max_Sum_Subarray_Of_Size_K;

public class Max_Sum_Subarray_Of_Size_K {
    public static void main(String[] args) {
        int arr[] = { 100, 200, 300, 400 };
        int k = 2;
        int maxSum = maximumSumSubarray(arr, k);
        System.out.println(maxSum);
    }

    /**
     * Took two pointers i and j = 0 and increment j till window size of 'k' is
     * reached
     * 
     * Window size: (j - i + 1)
     * 
     * when window size < k, then keep increasing j
     * when window size = k, remove arr[i] from sum and
     * maintain this, by incrementing both i an j
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public static int maximumSumSubarray(int[] arr, int k) {
        int n = arr.length;
        int i = 0; // start index of sliding window
        int j = 0; // end index of sliding window
        long sum = 0L;
        long maxSum = Long.MIN_VALUE;
        // j will reach till last index of array 'arr'
        while (j < n) { // TC: O(N)
            sum += arr[j];
            if (j - i + 1 < k) {
                // window size of k is not achieved yet
                j++;
            } else if (j - i + 1 == k) {
                // window size of k is achieved
                maxSum = Math.max(maxSum, sum);
                // exclude arr[i] from sum
                sum = sum - arr[i];
                // increment i so that window is moved to start index i++
                i++;
                j++;
            }
        }
        return (int) maxSum;
    }
}
