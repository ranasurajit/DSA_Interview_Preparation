package Binary_Search_Backup.One_Dimentional_Arrays.P2_Lower_Bound;

public class Implement_Lower_Bound {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 3, 3, 5 };
        int x = 2;

        int lBoundBruteForce = lowerBoundBruteForce(arr, x);
        System.out.println(lBoundBruteForce);

        int lBoundOptimal = lowerBoundOptimal(arr, x);
        System.out.println(lBoundOptimal);
    }

    /**
     * Using Optimal Approach (Binary Search)
     * We need to find index where arr[i] >= x
     * 
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param arr
     * @param target
     * @return
     */
    private static int lowerBoundOptimal(int[] arr, int target) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
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
    private static int lowerBoundBruteForce(int[] arr, int target) {
        int n = arr.length;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] >= target) {
                return i;
            }
        }
        return n;
    }
}
