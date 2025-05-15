package Two_Pointers_Sliding_Window.Sliding_Window.Fixed_Window_Size.P6_Count_Distinct_Elements_In_Every_Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Count_Distinct_Elements_In_Every_Window {
    public static void main(String[] args) {
        Count_Distinct_Elements_In_Every_Window solution = new Count_Distinct_Elements_In_Every_Window();

        int[] arr = { 1, 2, 1, 3, 4, 2, 3 };
        int k = 4;

        ArrayList<Integer> distincts = solution.countDistinct(arr, k);
        System.out.println(distincts);
    }

    /**
     * Approach : Using Sliding Window (Fixed Size) Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    ArrayList<Integer> countDistinct(int arr[], int k) {
        int n = arr.length;
        ArrayList<Integer> distincts = new ArrayList<Integer>();
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // SC: O(N)
        while (j < n) { // TC: O(N)
            map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                distincts.add(map.size());
                // remove computation of index 'i'
                int freq = map.get(arr[i]);
                if (freq == 1) {
                    map.remove(arr[i]);
                } else {
                    map.put(arr[i], map.getOrDefault(arr[i], 0) - 1);
                }
                // move to next window of size k
                i++;
                j++;
            }
        }
        return distincts;
    }
}
