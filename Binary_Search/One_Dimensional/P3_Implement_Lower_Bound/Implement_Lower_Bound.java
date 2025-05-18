package Binary_Search.One_Dimensional.P3_Implement_Lower_Bound;

public class Implement_Lower_Bound {
    public static void main(String[] args) {
        Implement_Lower_Bound solution = new Implement_Lower_Bound();

        int[] arr = { 2, 3, 7, 10, 11, 11, 25 };
        int target = 11;

        int lBound = solution.lowerBound(arr, target);
        System.out.println(lBound);
    }

    /**
     * Approach : Using Binary Search Approach (Find arr[i] >= target)
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    int lowerBound(int[] arr, int target) {
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
}
