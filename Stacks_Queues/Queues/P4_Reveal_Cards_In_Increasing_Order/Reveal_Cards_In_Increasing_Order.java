package Stacks_Queues.Queues.P4_Reveal_Cards_In_Increasing_Order;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Reveal_Cards_In_Increasing_Order {
    public static void main(String[] args) {
        Reveal_Cards_In_Increasing_Order solution = new Reveal_Cards_In_Increasing_Order();

        int[] deck1 = { 17, 13, 11, 2, 3, 5, 7 };
        int[] result1 = solution.deckRevealedIncreasingTwoPointers(deck1);
        System.out.println(Arrays.toString(result1));

        int[] deck2 = { 1, 1000 };
        int[] result2 = solution.deckRevealedIncreasingQueues(deck2);
        System.out.println(Arrays.toString(result2));
    }

    /**
     * Approach II : Using Two Pointers, Queues and Sorting Approach
     *
     * TC: O(N x log(N) + 2 x N) ~ O(N x log(N))
     * SC: O(N)
     */
    public int[] deckRevealedIncreasingQueues(int[] deck) {
        int n = deck.length;
        int[] result = new int[n];
        Arrays.sort(deck); // TC: O(N x log(N))
        // we will be storing indices in the Queue
        Queue<Integer> queue = new LinkedList<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            queue.offer(i);
        }
        int i = 0; // pointer at array 'deck'
        while (i < n) { // TC: O(N)
            int idx = queue.poll();
            result[idx] = deck[i];
            if (!queue.isEmpty()) {
                queue.offer(queue.poll());
            }
            i++;
        }
        return result;
    }

    /**
     * Approach I : Using Two Pointers and Sorting Approach
     *
     * TC: O(2 x N x log(N)) ~ O(N x log(N))
     * SC: O(1)
     */
    public int[] deckRevealedIncreasingTwoPointers(int[] deck) {
        int n = deck.length;
        int[] result = new int[n];
        Arrays.sort(deck); // TC: O(N x log(N))
        int i = 0; // pointer at array 'deck'
        int j = 0; // pointer at array 'result'
        boolean skip = false;
        while (i < n) { // TC: O(N x log(N))
            if (result[j] == 0) {
                // we can insert into result[index] provided skip = false
                if (!skip) {
                    result[j] = deck[i];
                    i++;
                }
                skip = !skip;
            }
            j = (j + 1) % n;
        }
        return result;
    }
}
