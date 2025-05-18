package Binary_Search_Backup.Binary_Search_On_Answers.P7_Kth_Missing_Positive_Number;

import java.util.HashSet;

public class Kth_Missing_Positive_Number {
    public static void main(String[] args) {
        int[] arr = { 2, 3, 4, 7, 11 };
        int k = 5;

        int missingKthHashSet = findKthPositiveUsingHashSet(arr, k);
        System.out.println(missingKthHashSet);

        int missingKthBruteForce = findKthPositiveUsingShifting(arr, k);
        System.out.println(missingKthBruteForce);

        int missingKthOptimal = findKthPositiveOptimal(arr, k);
        System.out.println(missingKthOptimal);
    }

    /**
     * Optimal Approach - Using Binary Search
     *
     * TC: TC: O(log(N))
     * SC: O(1)
     */
    public static int findKthPositiveOptimal(int[] arr, int k) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - (mid + 1);
            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low + k;
    }

    /**
     * Brute-Force Approach - Using Shifting
     *
     * TC: O(N)
     * SC: O(1)
     */
    public static int findKthPositiveUsingShifting(int[] arr, int k) {
        int n = arr.length;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (arr[i] <= k) {
                k++;
            } else {
                break;
            }
        }
        return k;
    }

    /**
     * Using HashSet
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public static int findKthPositiveUsingHashSet(int[] arr, int k) {
        int n = arr.length;
        int low = 1;
        int high = arr[n - 1];
        HashSet<Integer> hs = new HashSet<Integer>(); // SC: O(X) - (X + Y) = N
        HashSet<Integer> missing = new HashSet<Integer>(); // SC: O(Y) - (X + Y) = N
        for (int i = 0; i < n; i++) { // TC: O(N)
            hs.add(arr[i]);
        }
        int missed = -1;
        int count = 0;
        for (int i = low; i <= high; i++) { // TC: O(N)
            count = countOfMissing(hs, missing, i);
            if (count == k) {
                missed = i;
                break;
            }
        }
        if (count < k) {
            return arr[n - 1] + (k - count);
        }
        return missed;
    }

    private static int countOfMissing(HashSet<Integer> hs,
            HashSet<Integer> missing, int current) {
        if (!hs.contains(current)) {
            missing.add(current);
        }
        return missing.size();
    }
}
