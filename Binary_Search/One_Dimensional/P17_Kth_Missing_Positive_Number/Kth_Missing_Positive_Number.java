package Binary_Search.One_Dimensional.P17_Kth_Missing_Positive_Number;

public class Kth_Missing_Positive_Number {
    public static void main(String[] args) {
        Kth_Missing_Positive_Number solution = new Kth_Missing_Positive_Number();

        int[] arr = { 2, 3, 4, 7, 11 };
        int k = 5;

        int kthMissingPositive1 = solution.findKthPositiveShiftingApproach(arr, k);
        System.out.println(kthMissingPositive1);

        int kthMissingPositive2 = solution.findKthPositiveOptimal(arr, k);
        System.out.println(kthMissingPositive2);
    }

    /**
     * Approach II : Using Binary Search Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public int findKthPositiveOptimal(int[] arr, int k) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - (mid + 1); // (mid + 1) is the number expected in the mid index
            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low + k;
    }

    /**
     * Approach I : Using Shifting Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int findKthPositiveShiftingApproach(int[] arr, int k) {
        int n = arr.length;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] <= k) {
                k++;
            } else {
                break;
            }
        }
        return k;
    }
}
