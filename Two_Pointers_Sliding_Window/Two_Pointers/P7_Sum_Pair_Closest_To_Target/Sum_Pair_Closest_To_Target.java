package Two_Pointers_Sliding_Window.Two_Pointers.P7_Sum_Pair_Closest_To_Target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum_Pair_Closest_To_Target {
    public static void main(String[] args) {
        Sum_Pair_Closest_To_Target solution = new Sum_Pair_Closest_To_Target();

        int[] arr = { 5, 2, 7, 1, 4 };
        int target = 10;

        List<Integer> closestPair = solution.sumClosest(arr, target);
        System.out.println(closestPair);
    }

    /**
     * Approach : Using Sorting and Two Pointers Approach
     *
     * TC: O(N x log(N) + N) ~ O(N x log(N))
     * SC: O(1)
     */
    public List<Integer> sumClosest(int[] arr, int target) {
        int n = arr.length;
        List<Integer> result = new ArrayList<Integer>();
        if (n < 2) {
            // no pairs possible
            return result;
        }
        // since order does not matter here so sorting the array
        Arrays.sort(arr); // TC: O(N x log(N))
        int p = 0;
        int q = n - 1;
        int dist = Integer.MAX_VALUE;
        int first = 0;
        int second = 0;
        while (p < q) { // TC: O(N)
            int currentDist = 0;
            int sum = arr[p] + arr[q];
            if (sum == target) {
                first = arr[p];
                second = arr[q];
                break;
            } else if (sum < target) {
                currentDist = Math.abs(target - sum);
                if (currentDist < dist) {
                    dist = currentDist;
                    first = arr[p];
                    second = arr[q];
                }
                p++;
            } else {
                currentDist = Math.abs(target - sum);
                if (currentDist < dist) {
                    dist = currentDist;
                    first = arr[p];
                    second = arr[q];
                }
                q--;
            }
        }
        result.add(first);
        result.add(second);
        return result;
    }
}
