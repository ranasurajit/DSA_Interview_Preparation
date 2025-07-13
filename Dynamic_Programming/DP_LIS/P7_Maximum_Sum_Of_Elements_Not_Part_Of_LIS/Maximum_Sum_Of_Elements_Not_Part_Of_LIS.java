package Dynamic_Programming.DP_LIS.P7_Maximum_Sum_Of_Elements_Not_Part_Of_LIS;

public class Maximum_Sum_Of_Elements_Not_Part_Of_LIS {
    public static void main(String[] args) {
        Maximum_Sum_Of_Elements_Not_Part_Of_LIS solution = new Maximum_Sum_Of_Elements_Not_Part_Of_LIS();

        int[] arr1 = { 4, 6, 1, 2, 3, 8 };
        int nonLisMaxSum1 = solution.nonLisMaxSum(arr1);
        System.out.println(nonLisMaxSum1);

        int[] arr2 = { 5, 4, 3, 2, 1 };
        int nonLisMaxSum2 = solution.nonLisMaxSum(arr2);
        System.out.println(nonLisMaxSum2);
    }

    /**
     * Approach : Using Tabulation Optimized DP
     * 
     * TC: O(N x N) + O(N) ~ O(N x N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int nonLisMaxSum(int[] arr) {
        int n = arr.length;
        // we need to track Maximum Length of LIS in dp array
        int[] dp = new int[n];
        // we need to also track the sums of all LIS elements having maximum length
        // (LIS)
        int[] sumDP = new int[n];
        // minimum length of LIS is 1 (every element in array is an increasing
        // subsequence by itself)
        int maxLIS = 1;
        int totalSum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            dp[i] = 1; // minimum increasing subsequence length = 1
            sumDP[i] = arr[i];
            totalSum += arr[i];
            for (int prevIdx = 0; prevIdx < i; prevIdx++) { // TC: O(N)
                if (arr[i] > arr[prevIdx] && dp[i] <= dp[prevIdx] + 1) {
                    // we used <= as there can be more than 1 LIS
                    dp[i] = dp[prevIdx] + 1;
                    sumDP[i] = sumDP[prevIdx] + arr[i];
                }
                maxLIS = Math.max(maxLIS, dp[i]);
            }
        }
        // to maximum the sum of elements not part of LIS we need to get minimum sum of
        // LIS
        // we need to compare and get the minimum LIS sum from sumDP
        int minSumLIS = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (dp[i] == maxLIS) {
                // we filter all LIS having maximum length and compare to get minimum sum of LIS
                minSumLIS = Math.min(minSumLIS, sumDP[i]);
            }
        }
        // return the maximum sum of elements not part of LIS
        return totalSum - minSumLIS;
    }
}
