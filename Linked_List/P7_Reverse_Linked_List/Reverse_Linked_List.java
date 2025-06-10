package Linked_List.P7_Reverse_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Reverse_Linked_List {
    public static void main(String[] args) {
        Reverse_Linked_List solution = new Reverse_Linked_List();

        int[] arr1 = { 1, 2, 5, 6, 7 };
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);
        ListNode reversedList1 = solution.reverseListUsingRecursion(head1);
        LinkedListUtils.printSinglyLinkedList(reversedList1);

        int[] arr2 = { 1, 2, 3, 4, 5 };
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);
        ListNode reversedList2 = solution.reverseListUsingIteration(head2);
        LinkedListUtils.printSinglyLinkedList(reversedList2);
    }

    /**
     * Approach II : Using Iterative Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public ListNode reverseListUsingIteration(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        while (current != null) { // TC: O(N)
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public ListNode reverseListUsingRecursion(ListNode head) {
        return solveRecursion(head);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private ListNode solveRecursion(ListNode head) {
        // Base Case
        if (head == null || head.next == null) {
            return head;
        }
        // Recursion Calls
        // we will assume that Recursion will return a new head with reversed list of
        // size (n - 1)
        ListNode headNext = head.next;
        head.next = null;
        ListNode revHead = solveRecursion(headNext);
        headNext.next = head;
        return revHead;
    }
}
