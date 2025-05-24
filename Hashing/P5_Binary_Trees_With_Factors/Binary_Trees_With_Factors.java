package Hashing.P5_Binary_Trees_With_Factors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Binary_Trees_With_Factors {
    private static final int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        Binary_Trees_With_Factors solution = new Binary_Trees_With_Factors();

        int[] arr = { 2, 4, 5, 10 };
        int countBinaryTrees = solution.numFactoredBinaryTrees(arr);
        System.out.println(countBinaryTrees);
    }

    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N x log(N) + N + N ^ 2) ~ O(N ^ 2)
     * SC: O(N)
     */
    public int numFactoredBinaryTrees(int[] arr) {
        int n = arr.length;
        /**
         * For non-leaf nodes, the value should be greater than its children
         * so that nodeVal = leftVal x rightValue, so we should sort the array 'arr'
         */
        Arrays.sort(arr); // TC: O(N x log(N))
        Map<Integer, Long> btFreqMap = new HashMap<Integer, Long>(); // SC: O(N)
        btFreqMap.put(arr[0], 1L);
        for (int i = 1; i < n; i++) { // parent node - TC: O(N)
            long ways = 1L;
            for (int j = 0; j < i; j++) { // child node (should be less than parent) - TC: O(N)
                // arr[j] should divide arr[i] and also arr[i]/arr[j] should be present in
                // HashMap
                if (arr[i] % arr[j] == 0 && btFreqMap.containsKey(arr[i] / arr[j])) {
                    long leftChildWays = btFreqMap.get(arr[j]) % MOD;
                    long rightChildWays = btFreqMap.get(arr[i] / arr[j]) % MOD;
                    ways += (leftChildWays * rightChildWays) % MOD;
                }
            }
            btFreqMap.put(arr[i], ways);
        }
        long count = 0L;
        for (Integer key : btFreqMap.keySet()) { // TC: O(N)
            count = (count + btFreqMap.get(key)) % MOD;
        }
        return (int) count;
    }
}
