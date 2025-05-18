package Binary_Search.One_Dimensional.P4_Implement_Upper_Bound;

public class Implement_Upper_Bound {
    public static void main(String[] args) {
        Implement_Upper_Bound solution = new Implement_Upper_Bound();

        int[] arr = { 2, 3, 7, 10, 11, 11, 25 };
        int target = 11;

        int lBound = solution.upperBound(arr, target);
        System.out.println(lBound);
    }

    /**
     * Approach : Using Binary Search Approach (Find arr[i] > target)
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    int upperBound(int[] arr, int target) {
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
}
