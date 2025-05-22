package Binary_Search.Search_On_Answers.P8_Allocate_Minimum_Pages;

public class Allocate_Minimum_Pages {
    public static void main(String[] args) {
        Allocate_Minimum_Pages solution = new Allocate_Minimum_Pages();

        int[] arr = { 12, 34, 67, 90 };
        int k = 2;
        int minPages = solution.findPages(arr, k);
        System.out.println(minPages);
    }

    /**
     * Approach : Using Binary Search on Answers Approach
     * 
     * TC: O(N + N x log(K)) ~ O(N x log(K))
     * SC: O(1)
     * 
     * where K = Sum(arr) - Max(arr)
     * 
     * @param arr
     * @param k
     * @return
     */
    public int findPages(int[] arr, int k) {
        int n = arr.length;
        if (n < k) {
            // minimum of 1 book allocation to 1 student is not possible
            return -1;
        }
        long low = 0L;
        long high = 0L;
        for (int page : arr) { // TC: O(N)
            low = Math.max(low, (long) page);
            high += (long) page;
        }
        // Applying Binary Search Approach
        long pages = high;
        while (low <= high) { // TC: O(log(K))
            long mid = low + (high - low) / 2;
            long countStudents = countStudentsAllocated(arr, n, mid); // TC: O(N)
            if (countStudents > k) {
                // pages needs to be increased
                low = mid + 1;
            } else {
                // pages needs to be decreased
                pages = Math.min(pages, mid);
                high = mid - 1;
            }
        }
        return (int) pages;
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param arr
     * @param n
     * @param mid
     * @return
     */
    private long countStudentsAllocated(int[] arr, int n, long mid) {
        long count = 1L;
        long sum = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (sum + (long) arr[i] <= mid) {
                sum += (long) arr[i];
            } else {
                count++;
                sum = (long) arr[i];
            }
        }
        return count;
    }
}
