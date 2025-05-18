package Binary_Search_Backup.Binary_Search_On_Answers.P6_Capacity_To_Ship_Packages_Within_D_Days;

public class Capacity_To_Ship_Packages_Within_D_Days {
    public static void main(String[] args) {
        int[] weights = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int days = 5;

        int minCapacityBruteForce = shipWithinDaysBruteForce(weights, days);
        System.out.println(minCapacityBruteForce);

        int minCapacityOptimal = shipWithinDaysOptimal(weights, days);
        System.out.println(minCapacityOptimal);
    }

    /**
     * Using Optimal Approach (Binary Search)
     *
     * TC: O(N + N x log(K)) ~ O(N x log(K))
     * SC: O(1)
     */
    public static int shipWithinDaysOptimal(int[] weights, int days) {
        int n = weights.length;
        int low = Integer.MIN_VALUE;
        int high = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            low = Math.max(low, weights[i]);
            high += weights[i];
        }
        while (low <= high) { // TC: O(log(K)), where K = (high - low)
            int mid = low + (high - low) / 2;
            if (daysNeededForCapacity(weights, n, mid) > days) { // TC: O(N)
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * Using Brute-Force Approach
     *
     * TC: O(N + K x N)
     * SC: O(1)
     */
    public static int shipWithinDaysBruteForce(int[] weights, int days) {
        int n = weights.length;
        int low = Integer.MIN_VALUE;
        int high = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            low = Math.max(low, weights[i]);
            high += weights[i];
        }
        for (int i = low; i <= high; i++) { // TC: O(K), where K = (high - low)
            if (daysNeededForCapacity(weights, n, i) <= days) { // TC: O(N)
                return i;
            }
        }
        return -1;
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    private static int daysNeededForCapacity(int[] weights, int n, int capacity) {
        int sum = 0;
        int days = 1;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (sum + weights[i] > capacity) {
                days++;
                sum = weights[i];
            } else {
                sum += weights[i];
            }
        }
        return days;
    }
}
