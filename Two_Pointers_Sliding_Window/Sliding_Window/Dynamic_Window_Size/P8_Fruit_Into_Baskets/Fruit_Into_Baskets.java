package Two_Pointers_Sliding_Window.Sliding_Window.Dynamic_Window_Size.P8_Fruit_Into_Baskets;

import java.util.HashMap;
import java.util.Map;

public class Fruit_Into_Baskets {
    public static void main(String[] args) {
        Fruit_Into_Baskets solution = new Fruit_Into_Baskets();

        int[] fruits = { 1, 2, 3, 2, 2 };
        int numberOfFruits = solution.totalFruit(fruits);
        System.out.println(numberOfFruits);
    }

    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     * 
     * Here we need to ensure Map's size = 2 and the maximum length (number of trees
     * covered)
     *
     * TC: O(N)
     * SC: O(1)
     * 
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int i = 0;
        int j = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int maxLength = 0;
        while (j < n) { // TC: O(N)
            map.put(fruits[j], map.getOrDefault(fruits[j], 0) + 1);
            while (map.size() > 2) {
                // remove computation from index 'i'
                int freq = map.get(fruits[i]);
                if (freq == 1) {
                    map.remove(fruits[i]);
                } else {
                    map.put(fruits[i], freq - 1);
                }
                // shrink the window
                i++;
            }
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
        }
        return maxLength;
    }
}
