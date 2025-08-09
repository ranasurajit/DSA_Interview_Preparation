package BitManipulation.Medium.P8_Sort_Integers_By_The_Number_Of_1_Bits;

import java.util.Arrays;

public class Sort_Integers_By_The_Number_Of_1_Bits {
    public static void main(String[] args) {
        Sort_Integers_By_The_Number_Of_1_Bits solution = new Sort_Integers_By_The_Number_Of_1_Bits();

        int[] arr1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] sortedBitArr1 = solution.sortByBits(arr1);
        System.out.println(Arrays.toString(sortedBitArr1));

        int[] arr2 = { 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 };
        int[] sortedBitArr2 = solution.sortByBits(arr2);
        System.out.println(Arrays.toString(sortedBitArr2));
    }

    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC: O(N) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] boxedArr = new Integer[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            boxedArr[i] = Integer.valueOf(arr[i]);
        }
        Arrays.sort(boxedArr, (a, b) -> {
            int countABits = count1Bits(a); // TC: O(1)
            int countBBits = count1Bits(b); // TC: O(1)
            if (countABits == countBBits) {
                return a - b;
            }
            return countABits - countBBits;
        }); // TC: O(N x log(N))
        for (int i = 0; i < n; i++) { // TC: O(N)
            arr[i] = boxedArr[i];
        }
        return arr;
    }

    /**
     * Using Bit-Manipulation Approach
     * 
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    private int count1Bits(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n = n >> 1;
        }
        return count;
    }
}
