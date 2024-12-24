package Sliding_Window.Constant_Size.P4_Sliding_Window_Maximum;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Sliding_Window_Maximum {
    public static void main(String[] args) {
        int arr[] = { 45, 8, 12, 7 };
        int k = 3;
        ArrayList<Integer> maxSubArray = maxOfSubarrays(arr, k);
        System.out.println(maxSubArray);
    }

    // Function to find maximum of each subarray of size k.
    /**
     * Took two pointers i and j = 0 and increment j till window size of 'k' is
     * reached
     * 
     * add index j to a Deque (as we can add and remove from both ends of it)
     * remove all elements from beginning of a deque < arr[j]
     * 
     * Note: we will be storing indices in Deque
     * 
     * Window size: (j - i + 1)
     * 
     * when window size < k, then keep increasing j
     * when window size = k,
     * 1. add 1st element from beginning of deque to the resultant array/list
     * 2. remove 1st element from beginning of deque if it = i
     * 
     * maintain the sliding window size of k, by incrementing both i an j
     * 
     * TC: O(N)
     * SC: O(K)
     * 
     * @param arr
     * @param k
     * @return
     */
    public static ArrayList<Integer> maxOfSubarrays(int arr[], int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int n = arr.length;
        int i = 0; // pointer for start of sliding window
        int j = 0; // pointer for end of sliding window
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>(); // O(K)
        while (j < n) { // TC: O(N)
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[j]) {
                deque.pollLast();
            }
            deque.addLast(j);
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                result.add(arr[deque.peekFirst()]);
                if (i == deque.peekFirst()) {
                    deque.pollFirst();
                }
                // slide the window
                i++;
                j++;
            }
        }
        return result;
    }
}
