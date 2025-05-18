package Binary_Search_Backup.Binary_Search_On_Answers.P9_Allocate_Minimum_Pages;

public class Allocate_Minimum_Pages {
    public static void main(String[] args) {
        int[] arr = { 15, 10, 19, 10, 5, 18, 7 };
        int k = 5;

        int pagesBruteForce = findPagesBruteForce(arr, k);
        System.out.println(pagesBruteForce);

        int pagesOptimal = findPagesOptimal(arr, k);
        System.out.println(pagesOptimal);
    }

    /**
     * Optimal Approach (Using Binary Search)
     * 
     * TC: O(N x log(K)), where K = Sum(arr) - Max(arr)
     * SC: O(1)
     */
    public static int findPagesOptimal(int[] arr, int k) {
        int n = arr.length;
        if (n < k) {
            /*
             * if number of books < number of students then it voilates
             * the rule - Each student receives atleast one book.
             */
            return -1;
        }
        int low = 0;
        int high = 0;
        for (int item : arr) {
            low = Math.max(low, item);
            high += item;
        }
        // Using Binary search
        int result = 0;
        while (low <= high) { // TC: O(log(K))
            int mid = low + (high - low) / 2;
            if (wouldStudentsNotExceed(mid, arr, k, n)) { // TC: O(N)
                // decrease pages from student to adjust
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    /**
     * Brute-Force Approach (Using Linear Search)
     * 
     * TC: O(K x N), where K = Sum(arr) - Max(arr)
     * SC: O(1)
     */
    public static int findPagesBruteForce(int[] arr, int k) {
        int n = arr.length;
        if (n < k) {
            /*
             * if number of books < number of students then it voilates
             * the rule - Each student receives atleast one book.
             */
            return -1;
        }
        int low = 0;
        int high = 0;
        for (int item : arr) {
            low = Math.max(low, item);
            high += item;
        }
        // Using Linear search
        for (int i = low; i <= high; i++) { // TC: O(K), where K = Sum(arr) - Max(arr)
            if (wouldStudentsNotExceed(i, arr, k, n)) { // TC: O(N)
                return i;
            }
        }
        return -1;
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    private static boolean wouldStudentsNotExceed(int maxPages, int[] arr, int k, int n) {
        int numStudents = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum > maxPages) {
                numStudents++;
                sum = arr[i];
            }
        }
        return numStudents <= k;
    }
}
