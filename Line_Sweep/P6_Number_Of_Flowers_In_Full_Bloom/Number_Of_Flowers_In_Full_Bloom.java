package Line_Sweep.P6_Number_Of_Flowers_In_Full_Bloom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Number_Of_Flowers_In_Full_Bloom {
    public static void main(String[] args) {
        Number_Of_Flowers_In_Full_Bloom solution = new Number_Of_Flowers_In_Full_Bloom();

        int[][] flowers1 = { { 1, 6 }, { 3, 7 }, { 9, 12 }, { 4, 13 } };
        int[] people1 = { 2, 3, 7, 11 };

        int[] answers1 = solution.fullBloomFlowersLineSweepOnly(flowers1, people1);
        System.out.println(Arrays.toString(answers1));

        int[][] flowers2 = { { 19, 37 }, { 19, 38 }, { 19, 35 } };
        int[] people2 = { 6, 7, 21, 1, 13, 37, 5, 37, 46, 43 };

        int[] answers2 = solution.fullBloomFlowersLineSweepBinarySearch(flowers2, people2);
        System.out.println(Arrays.toString(answers2));
    }

    /**
     * Approach II : Using Line Sweep + Binary Search Approach
     *
     * TC: O(3 x M x log(M) + N x log(M)) ~ O((M + N )x log(M))
     * SC: O(2 x M) ~ O(M)
     *
     * Accepted (53 / 53 testcases passed)
     */
    public int[] fullBloomFlowersLineSweepBinarySearch(int[][] flowers, int[] people) {
        // we need a sorted map to store the time intervals when flowers would bloom
        Map<Integer, Integer> flowerMap = new TreeMap<Integer, Integer>(); // SC: O(2 x M)
        for (int[] time : flowers) { // TC: O(M)
            int start = time[0];
            int end = time[1];
            flowerMap.put(start, flowerMap.getOrDefault(start, 0) + 1); // TC: O(log(M))
            /**
             * as end is included as [start, end]
             * is full-open interval i.e. start <= x <= end
             */
            flowerMap.put(end + 1, flowerMap.getOrDefault(end + 1, 0) - 1); // TC: O(log(M))
        }
        // creating the pre-processed map
        int sum = 0;
        for (Integer key : flowerMap.keySet()) { // TC: O(M)
            sum += flowerMap.get(key); // TC: O(log(M))
            flowerMap.put(key, sum);
        }
        // iterate over the people array to see how many flowers would bloom when they
        // arrive
        int[] answers = new int[people.length];
        int index = 0;
        List<Integer> keysList = new ArrayList<>(flowerMap.keySet());
        for (int person : people) { // TC: O(N)
            // get bloomed flower index from lower bound (binary search)
            answers[index++] = getNumberOfBloomedFlowersBinarySearch(flowerMap, keysList, person); // TC: O(log(M))
        }
        return answers;
    }

    /**
     * Find the lowerBound of person value in keysList so find keysList[i] >= person
     *
     * TC: O(log(2 x M)) ~ O(log(M))
     * SC: O(1)
     */
    private int getNumberOfBloomedFlowersBinarySearch(Map<Integer, Integer> flowerMap,
            List<Integer> keysList, int person) {
        int low = 0;
        int high = keysList.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (keysList.get(mid) >= person) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low >= keysList.size()) {
            return 0;
        }
        int lb = keysList.get(low);
        if (lb != person) {
            if (low - 1 < 0) {
                return 0;
            }
            lb = keysList.get(low - 1);
        }
        return flowerMap.get(lb);
    }

    /**
     * Approach I : Using Line Sweep Approach
     *
     * TC: O(3 x M x log(M) + M x N) ~ O(M x N)
     * SC: O(2 x M) ~ O(M)
     *
     * Time Limit Exceeded (46 / 53 testcases passed)
     */
    public int[] fullBloomFlowersLineSweepOnly(int[][] flowers, int[] people) {
        // we need a sorted map to store the time intervals when flowers would bloom
        Map<Integer, Integer> flowerMap = new TreeMap<Integer, Integer>(); // SC: O(2 x M)
        for (int[] time : flowers) { // TC: O(M)
            int start = time[0];
            int end = time[1];
            flowerMap.put(start, flowerMap.getOrDefault(start, 0) + 1); // TC: O(log(M))
            /**
             * as end is included as [start, end]
             * is full-open interval i.e. start <= x <= end
             */
            flowerMap.put(end + 1, flowerMap.getOrDefault(end + 1, 0) - 1); // TC: O(log(M))
        }
        // creating the pre-processed map
        int sum = 0;
        for (Integer key : flowerMap.keySet()) { // TC: O(M)
            sum += flowerMap.get(key); // TC: O(log(M))
            flowerMap.put(key, sum);
        }
        // iterate over the people array to see how many flowers would bloom when they
        // arrive
        int[] answers = new int[people.length];
        int index = 0;
        for (int person : people) { // TC: O(N)
            answers[index++] = getNumberOfBloomedFlowers(flowerMap, person); // TC: O(M)
        }
        return answers;
    }

    /**
     * Iterating over the Sorted Map to get the maximum key present such that key <=
     * person
     *
     * TC: O(2 x M) ~ O(M)
     * SC: O(1)
     */
    private int getNumberOfBloomedFlowers(Map<Integer, Integer> flowerMap, int person) {
        int maxKeyPresent = -1;
        for (Integer key : flowerMap.keySet()) { // TC: O(2 x M)
            if (key <= person) {
                maxKeyPresent = key;
            }
        }
        return flowerMap.getOrDefault(maxKeyPresent, 0);
    }
}
