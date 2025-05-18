package Binary_Search_Backup.Binary_Search_On_Answers.P8_Aggressive_Cows;

import java.util.Arrays;

public class Aggressive_Cows {

    public static void main(String[] args) {
        int k = 4;
        int[] stalls = { 0, 3, 4, 7, 10, 9 };

        int maxDistBruteForce = aggressiveCowsBruteForce(stalls, k);
        System.out.println(maxDistBruteForce);

        int maxDistOptimal = aggressiveCowsBruteForce(stalls, k);
        System.out.println(maxDistOptimal);
    }

    /**
     * Optimal Approach (Using Binary Search)
     *
     * TC: O(N x log(N) + N x log(K)), where K = Max(stalls) - Min(stalls)
     * SC: O(1)
     */
    public static int aggressiveCowsOptimal(int[] stalls, int k) {
        int n = stalls.length;
        Arrays.sort(stalls); // TC: O(N x log(N))
        int low = 1;
        int high = stalls[n - 1] - stalls[0]; // high can be max distance between extremes
        int maxDist = 0;
        while (low <= high) { // TC: O(log(K)), where K = Max(stalls) - Min(stalls)
            int mid = low + (high - low) / 2;
            if (canPlaceAllCows(mid, stalls, k, n)) { // TC: O(N)
                maxDist = Math.max(maxDist, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return maxDist;
    }

    /**
     * Brute-Force Approach (Using Linear Search)
     *
     * TC: O(N x log(N) + K x N), where K = Max(stalls) - Min(stalls)
     * SC: O(1)
     */
    public static int aggressiveCowsBruteForce(int[] stalls, int k) {
        int n = stalls.length;
        Arrays.sort(stalls); // TC: O(N x log(N))
        int low = 1;
        int high = stalls[n - 1] - stalls[0]; // high can be max distance between extremes
        int maxDist = 0;
        for (int i = low; i <= high; i++) { // TC: O(K), where K = Max(stalls) - Min(stalls)
            if (canPlaceAllCows(i, stalls, k, n)) { // TC: O(N)
                maxDist = Math.max(maxDist, i);
            }
        }
        return maxDist;
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    private static boolean canPlaceAllCows(int dist, int[] stalls, int k, int n) {
        int cowsPlaced = 1;
        int lastPlaced = 0;
        for (int i = 1; i < n; i++) {
            if (stalls[i] - stalls[lastPlaced] >= dist) {
                lastPlaced = i;
                cowsPlaced++;
            }
        }
        return cowsPlaced >= k;
    }
}
