package Greedy_Algorithms.P16_Candy;

public class Candy {
    public static void main(String[] args) {
        Candy solution = new Candy();

        int[] ratings1 = { 1, 0, 2 };
        int totalCandies1 = solution.candyBruteForceApproach(ratings1);
        System.out.println(totalCandies1);

        int[] ratings2 = { 1, 2, 2 };
        int totalCandies2 = solution.candyBetterApproach(ratings2);
        System.out.println(totalCandies2);

        int[] ratings3 = { 8, 2, 2, 1, 5, 9, 1 };
        int totalCandies3 = solution.candySlopeApproach(ratings3);
        System.out.println(totalCandies3);
    }

    /**
     * Approach III : Using Slope Approach (Optimal Approach)
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int candySlopeApproach(int[] ratings) {
        int n = ratings.length;
        int i = 1;
        int totalCandies = 1;
        while (i < n) { // TC: O(N)
            // processing the valleys
            while (i < n && ratings[i] == ratings[i - 1]) {
                totalCandies += 1;
                i++;
                continue;
            }
            // processing the rise of peaks
            int peaks = 1;
            while (i < n && ratings[i] > ratings[i - 1]) {
                peaks++;
                totalCandies += peaks;
                i++;
            }
            // processing the decline of peaks
            int valleys = 1;
            while (i < n && ratings[i] < ratings[i - 1]) {
                totalCandies += valleys;
                valleys++;
                i++;
            }
            if (valleys > peaks) {
                // we need to adjust the totalCadies with difference of peaks and valleys
                totalCandies += (valleys - peaks);
            }
        }
        return totalCandies;
    }

    /**
     * Approach II : Using Greedy + Array Pre-processing (Better Approach / Less
     * Memory) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int candyBetterApproach(int[] ratings) {
        int n = ratings.length;
        // pre-processing candy allocation from left to right
        int[] leftAlloc = new int[n]; // SC: O(N)
        leftAlloc[0] = 1;
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (ratings[i] > ratings[i - 1]) {
                leftAlloc[i] = 1 + leftAlloc[i - 1];
            } else {
                leftAlloc[i] = 1;
            }
        }
        // we will be computing sum and right allocation on the fly
        int current = 1;
        int right = 1;
        int totalCandies = Math.max(1, leftAlloc[n - 1]);
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            if (ratings[i] > ratings[i + 1]) {
                current = right + 1;
            } else {
                current = 1;
            }
            totalCandies += Math.max(current, leftAlloc[i]);
            right = current;
        }
        return totalCandies;
    }

    /**
     * Approach I : Using Greedy + Array Pre-processing Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int candyBruteForceApproach(int[] ratings) {
        int n = ratings.length;
        // pre-processing candy allocation from left to right
        int[] leftAlloc = new int[n]; // SC: O(N)
        leftAlloc[0] = 1;
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (ratings[i] > ratings[i - 1]) {
                leftAlloc[i] = 1 + leftAlloc[i - 1];
            } else {
                leftAlloc[i] = 1;
            }
        }
        // pre-processing candy allocation from right to left
        int[] rightAlloc = new int[n]; // SC: O(N)
        rightAlloc[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            if (ratings[i] > ratings[i + 1]) {
                rightAlloc[i] = 1 + rightAlloc[i + 1];
            } else {
                rightAlloc[i] = 1;
            }
        }
        int totalCandies = 0;
        // summing up all candies
        for (int i = 0; i < n; i++) { // TC: O(N)
            totalCandies += Math.max(leftAlloc[i], rightAlloc[i]);
        }
        return totalCandies;
    }
}
