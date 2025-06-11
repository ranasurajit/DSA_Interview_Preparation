package Linked_List.P11_Find_Length_Of_Loop;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Find_Length_Of_Loop {
    public static void main(String[] args) {
        Find_Length_Of_Loop solution = new Find_Length_Of_Loop();

        int[] arr = { 1, 2, 3, 4, 5 };
        int c = 1;
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        LinkedListUtils.createCycle(head, c);

        int loopLength = solution.countNodesinLoop(head);
        System.out.println(loopLength);
    }

    /**
     * Approach : Using Two Pointers (Fast and Slow Pointers) Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     */
    public int countNodesinLoop(ListNode head) {
        if (head == null || head.next == null) {
            return 0;
        }
        ListNode slow = head;
        ListNode fast = head;
        /**
         * Move slow and fast pointers by 1 and 2 steps respectively until
         * they meet
         */
        boolean hasLoop = false;
        while (fast != null && fast.next != null) { // TC: O(N)
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }
        if (!hasLoop) {
            return 0;
        }
        int count = 0;
        /**
         * Keep the fast pointer as is and move the slow pointer by 1 step
         * until they meet and keep incrementing count which will give the
         * length of the loop
         */
        while (slow != null) { // TC: O(N)
            slow = slow.next;
            count++;
            if (slow == fast) {
                // count has the length of the loop
                break;
            }
        }
        return count;
    }
}
