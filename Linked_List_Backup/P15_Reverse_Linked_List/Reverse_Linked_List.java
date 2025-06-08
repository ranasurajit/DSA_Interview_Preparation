package Linked_List_Backup.P15_Reverse_Linked_List;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Reverse_Linked_List {
    public static void main(String[] args) {
        Reverse_Linked_List solution = new Reverse_Linked_List();

        int[] arr1 = { 1, 2, 3, 4, 5 };
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);

        ListNode reverseList1 = solution.reverseListRecursive(head1);
        LinkedListUtils.printSinglyLinkedList(reverseList1);

        int[] arr2 = { 1, 2, 3, 4, 5 };
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);

        ListNode reverseList2 = solution.reverseListIterative(head2);
        LinkedListUtils.printSinglyLinkedList(reverseList2);
    }

    /**
     * Using Recursion
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param head
     * @return
     */
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextNode = head.next;
        ListNode newHead = reverseListRecursive(nextNode);
        head.next = null;
        nextNode.next = head;
        return newHead;
    }

    /**
     * Using Iterative Approach
     * 
     * TC: O(N)
     * Sc: O(1)
     * 
     * @param head
     * @return
     */
    public ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }
}
