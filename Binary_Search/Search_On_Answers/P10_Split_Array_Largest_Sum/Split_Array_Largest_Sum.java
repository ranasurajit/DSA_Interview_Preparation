package Binary_Search.Search_On_Answers.P10_Split_Array_Largest_Sum;

public class Split_Array_Largest_Sum {
    public static void main(String[] args) {
        Split_Array_Largest_Sum solution = new Split_Array_Largest_Sum();

        int[] nums = { 7, 2, 5, 10, 8 };
        int k = 2;

        int minLargestSum = solution.splitArray(nums, k);
        System.out.println(minLargestSum);
    }

    /**
     * Approach : Using Binary Search on Answers Approach
     * 
     * TC: O(N + N x log(P)) ~ O(N x log(P))
     * SC: O(1)
     * 
     * where P = Sum(nums) - Max(nums)
     * 
     * @param nums
     * @param k
     * @return
     */
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        long low = 0L;
        long high = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            low = Math.max(low, (long) nums[i]);
            high += (long) nums[i];
        }
        // Applying Binary Search
        while (low <= high) { // TC: O(log(P))
            long mid = low + (high - low) / 2;
            long countSubArrays = getCountOfPossibleSubArrays(nums, n, mid); // TC: O(N)
            if (countSubArrays <= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int) low;
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param nums
     * @param n
     * @param mid
     * @return
     */
    private long getCountOfPossibleSubArrays(int[] nums, int n, long mid) {
        long sum = 0L;
        long count = 1L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (sum + (long) nums[i] <= mid) {
                sum += (long) nums[i];
            } else {
                count++;
                sum = (long) nums[i];
            }
        }
        return count;
    }
}
