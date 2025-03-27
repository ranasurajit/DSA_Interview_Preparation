package Binary_Search.One_Dimentional_Arrays.P4_Find_Peak_Element;

public class Find_Peak_Element {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 15, 2, 23, 90, 80 };

        int peakIndexBruteForce = peakElementBruteForce(arr);
        System.out.println(peakIndexBruteForce);

        int peakIndexBruteForceBetter = peakElementBruteForceBetter(arr);
        System.out.println(peakIndexBruteForceBetter);

        int peakIndexBruteOptimal = peakElementOptimal(arr);
        System.out.println(peakIndexBruteOptimal);
    }

    /**
     * Optimal Approach (Using Binary Search)
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    public static int peakElementOptimal(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return 0;
        }
        if (arr[0] > arr[1]) {
            return 0;
        }
        if (arr[n - 1] > arr[n - 2]) {
            return n - 1;
        }
        int low = 1;
        int high = n - 2;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                // peak is at mid index
                return mid;
            } else if (arr[mid] < arr[mid + 1]) {
                // mid index is at increasing curve and peak is at right
                low = mid + 1;
            } else if (arr[mid] < arr[mid - 1]) {
                // mid index is at decreasing curve and peak is at left
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Brute-Force Better Approach (Using Linear Search)
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public static int peakElementBruteForceBetter(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if ((i == 0 || arr[i - 1] < arr[i]) && (i == n - 1) || arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Brute-Force Approach (Using Linear Search)
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public static int peakElementBruteForce(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return 0;
        }
        if (arr[0] > arr[1]) {
            return 0;
        }
        if (arr[n - 1] > arr[n - 2]) {
            return n - 1;
        }
        for (int i = 1; i < n - 1; i++) { // TC: O(N)
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}
