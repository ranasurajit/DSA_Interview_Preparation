package Sliding_Window.Constant_Size.P6_Count_Distinct_Elements_In_Every_Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Count_Distinct_Elements_In_Every_Window {
    public static void main(String[] args) {
        Count_Distinct_Elements_In_Every_Window solution = new Count_Distinct_Elements_In_Every_Window();

        int[] arr = { 1, 2, 1, 3, 4, 2, 3 };
        int k = 4;

        ArrayList<Integer> distinctList = solution.countDistinct(arr, k);
        System.out.println(distinctList);
    }

    /**
     * Using Sliding Window Technique (Constant Size)
     * 
     * TC: O(N)
     * SC: O(N)
     */
    ArrayList<Integer> countDistinct(int arr[], int k) {
        int n = arr.length;
        ArrayList<Integer> distinctList = new ArrayList<Integer>();
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        while (j < n) { // TC: O(N)
            map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                // our sliding window size is met here so now store the unique elements
                distinctList.add(map.size());
                // now slide the window after removing the result
                int count = map.get(arr[i]);
                if (count > 1) {
                    map.put(arr[i], map.get(arr[i]) - 1);
                } else {
                    map.remove(arr[i]);
                }
                // slide the window to maintain the window size
                i++;
                j++;
            }
        }
        return distinctList;
    }
}
