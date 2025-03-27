package Binary_Search.One_Dimentional_Arrays.P3_Upper_Bound;

public class Implement_Upper_Bound {
    public static void main(String[] args) {
        int[] arr = { 2, 4, 6, 7 };
        int x = 5;

        int uBoundBruteForce = upperBoundBruteForce(arr, x);
        System.out.println(uBoundBruteForce);

        int uBoundOptimal = upperBoundOptimal(arr, x);
        System.out.println(uBoundOptimal);
    }

    /**
     * Using Optimal Approach (Binary Search)
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private static int upperBoundOptimal(int[] arr, int target) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Using Brute-Force Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private static int upperBoundBruteForce(int[] arr, int target) {
        int n = arr.length;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] > target) {
                return i;
            }
        }
        return n;
    }
}
