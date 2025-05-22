package Binary_Search.Search_On_Answers.P4_Minimum_Number_Of_Days_To_Make_M_Bouquets;

public class Minimum_Number_Of_Days_To_Make_M_Bouquets {
    public static void main(String[] args) {
        Minimum_Number_Of_Days_To_Make_M_Bouquets solution = new Minimum_Number_Of_Days_To_Make_M_Bouquets();

        int[] bloomDay1 = { 1, 10, 3, 10, 2 };
        int m1 = 3;
        int k1 = 1;

        int minimumDays1 = solution.minDays(bloomDay1, m1, k1);
        System.out.println(minimumDays1);

        int[] bloomDay2 = { 7, 7, 7, 7, 12, 7, 7 };
        int m2 = 2;
        int k2 = 3;

        int minimumDays2 = solution.minDays(bloomDay2, m2, k2);
        System.out.println(minimumDays2);
    }

    /**
     * Approach : Using Binary Search on Answers Approach
     * 
     * TC: O(N x log(Max(bloomDay) - Min(bloomDay)))
     * SC: O(1)
     * 
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int minDays(int[] bloomDay, int m, int k) {
        long total = (long) m * (long) k;
        if (bloomDay.length < total) {
            // cannot make 'm' bouquets
            return -1;
        }
        long low = Integer.MAX_VALUE;
        long high = Integer.MIN_VALUE;

        for (int day : bloomDay) { // TC: O(N)
            low = Math.min(low, (long) day);
            high = Math.max(high, (long) day);
        }

        // Applying Binary Search
        long minimumDays = high;
        while (low <= high) { // TC: O(log(Max(bloomDay) - Min(bloomDay)))
            long mid = low + (high - low) / 2;
            if (countBouquetsPossible(bloomDay, k, mid) >= m) { // TC: O(N)
                minimumDays = Math.min(minimumDays, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int) minimumDays;
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param bloomDay
     * @param k
     * @param mid
     * @return
     */
    private long countBouquetsPossible(int[] bloomDay, int k, long mid) {
        long count = 0;
        long bouquets = 0;
        for (int day : bloomDay) { // TC: O(N)
            if (day <= mid) {
                count++;
            } else {
                bouquets += (count / k);
                count = 0;
            }
        }
        bouquets += (count / k);
        return bouquets;
    }
}
