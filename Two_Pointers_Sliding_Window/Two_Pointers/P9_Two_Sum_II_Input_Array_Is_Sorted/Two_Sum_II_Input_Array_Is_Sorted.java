package Two_Pointers_Sliding_Window.Two_Pointers.P9_Two_Sum_II_Input_Array_Is_Sorted;

import java.util.Arrays;

public class Two_Sum_II_Input_Array_Is_Sorted {
    public static void main(String[] args) {
        Two_Sum_II_Input_Array_Is_Sorted solution = new Two_Sum_II_Input_Array_Is_Sorted();

        int[] numbers = { 2, 7, 11, 15 };
        int target = 9;

        int[] result = solution.twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int p = 0;
        int q = n - 1;
        while (p < q) { // TC: O(N)
            int sum = numbers[p] + numbers[q];
            if (sum == target) {
                return new int[] { p + 1, q + 1 };
            } else if (sum < target) {
                p++;
            } else {
                q--;
            }
        }
        return new int[] { -1, -1 };
    }
}
