package Two_Pointers_Sliding_Window.Sliding_Window.Fixed_Window_Size.P5_Sliding_Window_Maximum;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Sliding_Window_Maximum {
    public static void main(String[] args) {
        Sliding_Window_Maximum solution = new Sliding_Window_Maximum();

        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;

        int[] maximums = solution.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(maximums));
    }

    /**
     * Approach : Using Sliding Window (Fixed Length) Approach
     *
     * TC: O(N)
     * SC: O(K)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        int[] result = new int[n - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>(); // SC: O(K)
        int index = 0;
        while (j < n) { // TC: O(N)
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.pollLast();
            }
            deque.addLast(nums[j]);
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                result[index++] = deque.peekFirst();
                // remove calculation from index 'i'
                if (!deque.isEmpty() && deque.peekFirst() == nums[i]) {
                    deque.pollFirst();
                }
                // slide to next window
                i++;
                j++;
            }
        }
        return result;
    }
}
