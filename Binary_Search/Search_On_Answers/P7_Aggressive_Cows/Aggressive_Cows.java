package Binary_Search.Search_On_Answers.P7_Aggressive_Cows;

import java.util.Arrays;

public class Aggressive_Cows {
    public static void main(String[] args) {
        Aggressive_Cows solution = new Aggressive_Cows();

        int[] stalls = { 10, 1, 2, 7, 5 };
        int k = 3;

        int distance = solution.aggressiveCows(stalls, k);
        System.out.println(distance);
    }

    /**
     * Approach : Using Binary Search on Answers Approach
     * 
     * TC: O(N x log(N) + N x log(K))
     * SC: O(1)
     * 
     * where K = Max(stalls) - Min(stalls)
     * 
     * @param stalls
     * @param k
     * @return
     */
    public int aggressiveCows(int[] stalls, int k) {
        int n = stalls.length;
        /**
         * to find the exact position and distances between
         * them to allocate we need to sort the 'stalls' array
         */
        Arrays.sort(stalls); // TC: O(N x log(N))
        long low = 1L;
        long high = (long) stalls[n - 1] - (long) stalls[0];
        // Applying Binary Search Approach
        long maxDist = 1;
        while (low <= high) { // TC: O(log(K))
            long mid = low + (high - low) / 2;
            long placedCows = countOfCowsPlaced(stalls, n, mid); // TC: O(N)
            if (placedCows < k) {
                high = mid - 1;
            } else {
                // maximizing mid will give the max distance when we can place >= k cows
                maxDist = Math.max(maxDist, mid);
                low = mid + 1;
            }
        }
        return (int) maxDist;
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param stalls
     * @param n
     * @param mid
     * @return
     */
    private long countOfCowsPlaced(int[] stalls, int n, long mid) {
        long cows = 1L; // 1st cow is placed at index 0
        int lastPos = 0;
        for (int i = 1; i < n; i++) { // TC: O(N)
            while (i < n && stalls[i] - stalls[lastPos] < mid) {
                i++;
            }
            if (i >= n) {
                break;
            }
            // at this position we can place the cow
            cows++;
            lastPos = i;
        }
        return cows;
    }
}
