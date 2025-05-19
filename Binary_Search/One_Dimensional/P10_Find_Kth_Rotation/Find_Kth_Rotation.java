package Binary_Search.One_Dimensional.P10_Find_Kth_Rotation;

import java.util.Arrays;
import java.util.List;

public class Find_Kth_Rotation {
    public static void main(String[] args) {
        Find_Kth_Rotation solution = new Find_Kth_Rotation();

        List<Integer> arr = Arrays.asList(5, 1, 2, 3, 4);
        int rotationTimes = solution.findKRotation(arr);
        System.out.println(rotationTimes);
    }

    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    public int findKRotation(List<Integer> arr) {
        int n = arr.size();
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            if (arr.get(low) <= arr.get(high)) {
                // array is sorted and 0 times rotated
                return low;
            }
            int mid = low + (high - low) / 2;
            int prev = (mid - 1 + n) % n;
            int next = (mid + 1) % n;
            if (arr.get(mid) <= arr.get(prev) && arr.get(mid) <= arr.get(next)) {
                // minimum value found
                return mid;
            } else if (arr.get(mid) >= arr.get(low)) {
                // left part is sorted so, minimum value lies in right portion
                low = mid + 1;
            } else if (arr.get(mid) <= arr.get(high)) {
                // right part is sorted so, minimum value lies in left portion
                high = mid - 1;
            }
        }
        return 0;
    }
}
