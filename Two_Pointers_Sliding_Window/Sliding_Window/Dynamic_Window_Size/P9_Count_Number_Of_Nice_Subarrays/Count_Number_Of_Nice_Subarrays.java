package Two_Pointers_Sliding_Window.Sliding_Window.Dynamic_Window_Size.P9_Count_Number_Of_Nice_Subarrays;

public class Count_Number_Of_Nice_Subarrays {
    public static void main(String[] args) {
        Count_Number_Of_Nice_Subarrays solution = new Count_Number_Of_Nice_Subarrays();

        int[] nums = { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 };
        int k = 2;

        int countSubArrays = solution.numberOfSubarrays(nums, k);
        System.out.println(countSubArrays);
    }

    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     * 
     * We can find countSubArraysWithLessThanEqualsKOddNumbers(<= goal) - We can
     * find
     * countSubArraysWithLessThanEqualsKOddNumbers(<= goal - 1)
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     * 
     * @param nums
     * @param goal
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        return countSubArraysWithLessThanEqualsKOddNumbers(nums, n, k) -
                countSubArraysWithLessThanEqualsKOddNumbers(nums, n, k - 1);
    }

    /**
     * Using Sliding Window (Variable Size) Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param nums
     * @param n
     * @param k
     * @return
     */
    private int countSubArraysWithLessThanEqualsKOddNumbers(int[] nums, int n, int k) {
        if (k < 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        int countOdd = 0;
        int niceSubArrayCount = 0;
        while (j < n) { // TC: O(N)
            if ((nums[j] & 1) != 0) {
                // odd number at nums[j] found
                countOdd++;
            }
            while (countOdd > k) {
                // remove computation from index 'i'
                if ((nums[i] & 1) != 0) {
                    // odd number at nums[i] found
                    countOdd--;
                }
                i++;
            }
            niceSubArrayCount += (j - i + 1);
            j++;
        }
        return niceSubArrayCount;
    }
}
