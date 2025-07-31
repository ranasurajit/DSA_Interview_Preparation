package Line_Sweep.P8_Powerful_Integer;

public class Powerful_Integer {
    public static void main(String[] args) {
        Powerful_Integer solution = new Powerful_Integer();

        int[][] intervals1 = { { 1, 3 }, { 4, 6 }, { 3, 4 } };
        int k1 = 2;

        int powerfulInteger1 = solution.powerfulInteger(intervals1, k1);
        System.out.println(powerfulInteger1);

        int[][] intervals2 = { { 16, 21 }, { 5, 8 }, { 12, 17 }, { 17, 29 }, { 9, 24 } };
        int k2 = 3;

        int powerfulInteger2 = solution.powerfulInteger(intervals2, k2);
        System.out.println(powerfulInteger2);
    }

    /**
     * Approach : Using Difference Arrays / SweepLine Algorithm Approach
     * 
     * TC: O(N) + O(N) + O(T)
     * SC: O(T)
     * 
     * where T = Max(intervals)
     */
    public int powerfulInteger(int[][] intervals, int k) {
        int maxValue = 0;
        for (int[] interval : intervals) { // TC: O(N)
            maxValue = Math.max(maxValue, interval[1]);
        }
        // Using Difference Array Technique
        int[] diff = new int[maxValue + 2]; // SC: O(T) - added 2 so that no index spill happens
        for (int[] interval : intervals) { // TC: O(N)
            diff[interval[0]] += 1;
            diff[interval[1] + 1] -= 1;
        }
        // now we need to loop over array 'diff' to find cumulative sum
        int count = 0;
        int maxPow = -1;
        for (int i = 1; i < diff.length; i++) { // TC: O(T)
            count += diff[i];
            if (count >= k) {
                maxPow = i;
            }
        }
        return maxPow;
    }
}
