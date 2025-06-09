package Linked_List.P3_Middle_Of_The_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Middle_Of_The_Linked_List {
    public static void main(String[] args) {
        Middle_Of_The_Linked_List solution = new Middle_Of_The_Linked_List();

        int[] arr1 = { 1, 2, 3, 4, 5 };
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);

        ListNode middleNode1 = solution.middleNode(head1);
        LinkedListUtils.printSinglyLinkedList(middleNode1);

        int[] arr2 = { 1, 2, 3, 4, 5, 6 };
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);

        ListNode middleNode2 = solution.middleNode(head2);
        LinkedListUtils.printSinglyLinkedList(middleNode2);
    }

    /**
     * Approach : Using Two Pointers (Fast and Slow Pointers) Approach
     *
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { // TC: O(N / 2)
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
