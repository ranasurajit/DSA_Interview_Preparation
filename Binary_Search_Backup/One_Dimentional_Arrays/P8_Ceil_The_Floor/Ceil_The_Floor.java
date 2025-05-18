package Binary_Search_Backup.One_Dimentional_Arrays.P8_Ceil_The_Floor;

import java.util.Arrays;

public class Ceil_The_Floor {
    public static void main(String[] args) {
        int n = 6;
        int x = 5;
        int[] a = { 3, 4, 7, 8, 8, 10 };

        int[] floorCeilValues = getFloorAndCeil(a, n, x);
        System.out.println(Arrays.toString(floorCeilValues));
    }

    /**
     * Using Binary Search Approach
     * 
     * TC: O(2 x log(N)) ~ O(log(N))
     * SC: O(1)
     * 
     * @param a
     * @param n
     * @param x
     * @return
     */
    private static int[] getFloorAndCeil(int[] a, int n, int x) {
        int floor = getFloor(a, n, x);
        int ceiling = getCeiling(a, n, x);
        return new int[] { floor, ceiling };
    }

    /**
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param a
     * @param n
     * @param x
     * @return
     */
    private static int getFloor(int[] a, int n, int x) {
        int low = 0;
        int high = n - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] <= x) {
                result = a[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    /**
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param a
     * @param n
     * @param x
     * @return
     */
    private static int getCeiling(int[] a, int n, int x) {
        int low = 0;
        int high = n - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] >= x) {
                result = a[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}
