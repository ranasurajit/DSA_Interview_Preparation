package Recursion.P12_Reverse_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Reverse_Linked_List {
    public static void main(String[] args) {
        Reverse_Linked_List solution = new Reverse_Linked_List();

        int[] arr = { 1, 2, 3, 4, 5 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode reverseHead = solution.reverseList(head);
        LinkedListUtils.printSinglyLinkedList(reverseHead);
    }

    /**
     * Approach : Using Recursion
     *
     * TC: O(N)
     * SC: O(N)
     */
    public ListNode reverseList(ListNode head) {
        return solveRecursion(head);
    }

    /**
     * TC: O(N)
     * SC: O(N)
     */
    private ListNode solveRecursion(ListNode head) {
        // Base Case
        if (head == null || head.next == null) {
            return head;
        }
        // Hypothesis
        /**
         * we assume that recursion will return me reversed
         * LinkedList from (head.next till last and return
         * a new head)
         */
        ListNode tail = head.next; // stored reference of next node
        head.next = null;
        ListNode newHead = solveRecursion(tail);
        // Induction
        tail.next = head;
        return newHead;
    }
}
