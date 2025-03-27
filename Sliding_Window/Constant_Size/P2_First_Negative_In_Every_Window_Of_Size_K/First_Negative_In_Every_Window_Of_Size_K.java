package Sliding_Window.Constant_Size.P2_First_Negative_In_Every_Window_Of_Size_K;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class First_Negative_In_Every_Window_Of_Size_K {
    public static void main(String[] args) {
        int arr[] = { 12, -1, -7, 8, -15, 30, 16, 28 };
        int k = 3;
        List<Integer> firstNegativeList = firstNegativeInteger(arr, k);
        System.out.println(firstNegativeList);
    }

    // Function to find the first negative integer in every window of size k
    /**
     * Took two pointers i and j = 0 and increment j till window size of 'k' is
     * reached
     * 
     * add arr[j] < 0 to a Deque as we can add and remove from both ends of it
     * 
     * Window size: (j - i + 1)
     * 
     * when window size < k, then keep increasing j
     * when window size = k, 
     *  1. if deque is empty add 0 to result
     *  2. if not, add the 1st element of deque to result, check if arr[i] == deque's 
     *     1st element then remove from deque to move the sliding window
     * 
     * maintain this, by incrementing both i an j
     * 
     * TC: O(N)
     * SC: O(N)
     * @param arr
     * @param k
     * @return
     */
    private static List<Integer> firstNegativeInteger(int arr[], int k) {
        int n = arr.length;
        int i = 0; // pointer for start of sliding window
        int j = 0; // pointer for end of sliding window
        List<Integer> result = new ArrayList<Integer>(); // SC: O(N - K + 1) - ignored
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>(); // SC: O(N)
        while (j < n) { // TC: O(N)
            if (arr[j] < 0) {
                deque.addLast(arr[j]);
            }
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                // window size is reached
                if (deque.isEmpty()) {
                    result.add(0);
                } else {
                    // deque has atleast 1 negative number present
                    result.add(deque.peekFirst());
                    if (deque.peekFirst() == arr[i]) {
                        deque.pollFirst();
                    }
                }
                // maintain the sliding window and move it further by 1 element
                i++;
                j++;
            }
        }
        return result;
    }
}
