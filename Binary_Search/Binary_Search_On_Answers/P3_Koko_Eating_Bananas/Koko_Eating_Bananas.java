package Binary_Search.Binary_Search_On_Answers.P3_Koko_Eating_Bananas;

public class Koko_Eating_Bananas {
    public static void main(String[] args) {
        int[] piles = { 3, 6, 7, 11 };
        int h = 8;

        int minSpeedBruteForce = minEatingSpeedBruteForce(piles, h);
        System.out.println(minSpeedBruteForce);

        int minSpeedOptimal = minEatingSpeedOptimal(piles, h);
        System.out.println(minSpeedOptimal);
    }

    /**
     * Optimal Approach
     * 
     * TC: O(N x log(K)), where K = Maximum value in piles array
     * SC: O(1)
     * 
     * @param piles
     * @param h
     * @return
     */
    public static int minEatingSpeedOptimal(int[] piles, int h) {
        int low = 1;
        int high = getMax(piles);
        int minSpeed = Integer.MAX_VALUE;
        while (low <= high) { // TC: O(log(K))
            int mid = low + (high - low) / 2;
            if (calculateTotalHours(piles, mid) <= h) { // TC: O(N)
                minSpeed = Math.min(minSpeed, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return minSpeed;
    }

    /**
     * Brute-Force Approach
     * 
     * TC: O(K x N), where K = Maximum value in piles array
     * SC: O(1)
     * 
     * @param piles
     * @param h
     * @return
     */
    public static int minEatingSpeedBruteForce(int[] piles, int h) {
        int low = 1;
        int high = getMax(piles);
        int minSpeed = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) { // TC: O(K)
            if ((int) calculateTotalHours(piles, i) <= h) { // TC: O(N)
                minSpeed = Math.min(minSpeed, i);
            }
        }
        return minSpeed;
    }

    /**
     * TC: O(N)
     * SC: O(1)
     * 
     * @param piles
     * @param current
     * @return
     */
    private static long calculateTotalHours(int[] piles, int current) {
        long sum = 0L;
        for (int pile : piles) {
            sum += pile % current == 0 ? (pile / current) : (pile / current) + 1;
        }
        return sum;
    }

    /**
     * TC: O(N)
     * SC: O(1)
     * 
     * @param piles
     * @return
     */
    private static int getMax(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        return max;
    }
}
