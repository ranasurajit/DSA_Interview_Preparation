package Greedy_Algorithms.P11_Minimum_Platforms;

import java.util.Arrays;
import java.util.TreeMap;

public class Minimum_Platforms {
    public static void main(String[] args) {
        Minimum_Platforms solution = new Minimum_Platforms();

        int[] arr1 = { 900, 940, 950, 1100, 1500, 1800 };
        int[] dep1 = { 910, 1200, 1120, 1130, 1900, 2000 };

        int minPlatforms1 = solution.findPlatformSweepLineAlgorithm(arr1, dep1);
        System.out.println(minPlatforms1);

        int[] arr2 = { 900, 1235, 1100 };
        int[] dep2 = { 1000, 1240, 1200 };

        int minPlatforms2 = solution.findPlatformSortingTwoPointers(arr2, dep2);
        System.out.println(minPlatforms2);
    }

    // Function to find the minimum number of platforms required at the
    // railway station such that no train waits.
    /**
     * Approach II : Using Sorting + Two Pointers Algorithm Approach
     * 
     * TC: O(M x log(M) + N x log(N)) + O(Max(M, N)) ~ O(M x log(M) + N x log(N))
     * SC: O(1)
     */
    private int findPlatformSortingTwoPointers(int arr[], int dep[]) {
        int m = arr.length;
        int n = dep.length;
        Arrays.sort(arr); // TC: O(M x log(M))
        Arrays.sort(dep); // TC: O(N x log(N))
        int overlaps = 0;
        int maxOverlap = 0;
        int p = 1; // pointer at index 1 of array 'arr'
        int q = 0; // pointer at index 0 of array 'arr'
        // Using Two Pointers Approach
        while (p < m && q < n) { // TC: O(Max(M, N))
            if (arr[p] <= dep[q]) {
                overlaps++;
                p++;
            } else if (arr[p] > dep[q]) {
                overlaps--;
                q++;
            }
            maxOverlap = Math.max(maxOverlap, overlaps);
        }
        // 1 is added as we need atleast 1 minimum platform and we started with p = 1
        return maxOverlap + 1;
    }

    /**
     * Approach I : Using Sweep Line Algorithm
     * 
     * TC: O(M x log(M) + N x log(N)) + O(M + N) ~ O(M x log(M) + N x log(N))
     * SC: O(M + N)
     */
    private int findPlatformSweepLineAlgorithm(int arr[], int dep[]) {
        int m = arr.length;
        int n = dep.length;
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(); // SC: O(M + N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1); // TC: O(log(M))
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            map.put(dep[i] + 1, map.getOrDefault(dep[i] + 1, 0) - 1); // TC: O(log(N))
        }
        int maxOverlap = 0;
        int cumSum = 0;
        for (Integer key : map.keySet()) { // TC: O(M + N)
            cumSum += map.get(key);
            maxOverlap = Math.max(maxOverlap, cumSum);
        }
        return maxOverlap;
    }
}
