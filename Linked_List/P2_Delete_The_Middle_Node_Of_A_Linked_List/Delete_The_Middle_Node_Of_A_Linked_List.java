package Linked_List.P2_Delete_The_Middle_Node_Of_A_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Delete_The_Middle_Node_Of_A_Linked_List {
    public static void main(String[] args) {
        Delete_The_Middle_Node_Of_A_Linked_List solution = new Delete_The_Middle_Node_Of_A_Linked_List();

        int[] arr1 = { 1, 3, 4, 7, 1, 2, 6 };
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);

        ListNode resultHead1 = solution.deleteMiddleTwoPointers(head1);
        LinkedListUtils.printSinglyLinkedList(resultHead1);

        int[] arr2 = { 1, 2, 3, 4 };
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);

        ListNode resultHead2 = solution.deleteMiddleTwoPointersOptimal(head2);
        LinkedListUtils.printSinglyLinkedList(resultHead2);
    }

    /**
     * Approach II : Using Two Pointers (Fast and Slow Pointers Cleaner Approach)
     * Approach
     *
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     */
    public ListNode deleteMiddleTwoPointersOptimal(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { // TC: O(N / 2)
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;
        return head;
    }

    /**
     * Approach I : Using Two Pointers (Fast and Slow Pointers) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public ListNode deleteMiddleTwoPointers(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { // TC: O(N / 2)
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow pointer has the middle node
        ListNode prev = null;
        ListNode current = head;
        while (current != slow) { // TC: O(N / 2)
            prev = current;
            current = current.next;
        }
        prev.next = current.next;
        return head;
    }
}
