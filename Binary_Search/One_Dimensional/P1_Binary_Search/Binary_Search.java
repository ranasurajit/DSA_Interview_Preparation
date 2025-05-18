package Binary_Search.One_Dimensional.P1_Binary_Search;

public class Binary_Search {
    public static void main(String[] args) {
        Binary_Search solution = new Binary_Search();

        int[] arr1 = { 1, 2, 3, 4, 5 };
        int k1 = 4;

        int index1 = solution.binarysearch(arr1, k1);
        System.out.println(index1);

        int[] arr2 = { 1, 1, 1, 1, 2 };
        int k2 = 1;
        int index2 = solution.binarysearch(arr2, k2);
        System.out.println(index2);
    }

    /**
     * Approach : Using Binary Search
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public int binarysearch(int[] arr, int k) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        int index = -1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (arr[mid] == k) {
                index = mid;
                high = mid - 1;
                // shrink the search space to left in case there are multiple values = k
            } else if (arr[mid] < k) {
                // search space is after mid index
                low = mid + 1;
            } else {
                // search space is before mid index
                high = mid - 1;
            }
        }
        return index;
    }
}
