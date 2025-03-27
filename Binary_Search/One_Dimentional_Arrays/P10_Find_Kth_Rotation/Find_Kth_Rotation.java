package Binary_Search.One_Dimentional_Arrays.P10_Find_Kth_Rotation;

import java.util.Arrays;
import java.util.List;

public class Find_Kth_Rotation {
    public static void main(String[] args) {
        Find_Kth_Rotation solution = new Find_Kth_Rotation();

        List<Integer> arr = Arrays.asList(6, 9, 2, 4);
        int rotationTimes = solution.findKRotation(arr);
        System.out.println(rotationTimes);
    }

    /**
     * Using Binary Search Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param arr
     * @return
     */
    public int findKRotation(List<Integer> arr) {
        int n = arr.size();
        int low = 0;
        int high = n - 1;
        while (low < high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (arr.get(mid) > arr.get(high)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        // index of the minimum element is the number of times the array is rotated
        return low;
    }
}
