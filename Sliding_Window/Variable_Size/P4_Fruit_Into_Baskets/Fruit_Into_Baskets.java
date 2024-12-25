package Sliding_Window.Variable_Size.P4_Fruit_Into_Baskets;

import java.util.HashMap;
import java.util.Map;

public class Fruit_Into_Baskets {
    public static void main(String[] args) {
        int[] fruits = { 1, 2, 3, 2, 2 };
        int total = totalFruit(fruits);
        System.out.println(total);
    }

    /**
     * Took two pointers i and j = 0
     * 
     * Window size: (j - i + 1)
     * 
     * when HashMap size < k i.e. 2, j++
     * when HashMap size <= k i.e. 2,
     * compare and store the maximum window size (result), j++
     * till HashMap size > k i.e. 2, then start removing elements from index 'i'
     * and update its count/remove key from HashMap, j++
     * 
     * TC: O(N)
     * SC: O(1)
     *
     * @param fruits
     * @return
     */
    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        int i = 0; // pointer for start index of sliding window
        int j = 0; // pointer for end index of sliding window
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>(); // O(2) ~ O(1)
        int k = 2;
        int total = Integer.MIN_VALUE;
        while (j < n) { // O(N)
            hm.put(fruits[j], hm.getOrDefault(fruits[j], 0) + 1);
            while (hm.size() > k) {
                // remove the index ith element from HashMap
                hm.put(fruits[i], hm.getOrDefault(fruits[i], 0) - 1);
                if (hm.get(fruits[i]) == 0) {
                    hm.remove(fruits[i]);
                }
                i++;
            }
            if (hm.size() <= k) {
                total = Math.max(total, j - i + 1);
            }
            j++;
        }
        return total == Integer.MIN_VALUE ? 1 : total;
    }
}
