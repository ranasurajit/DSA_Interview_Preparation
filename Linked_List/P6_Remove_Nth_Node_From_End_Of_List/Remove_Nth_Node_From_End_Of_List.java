package Linked_List.P6_Remove_Nth_Node_From_End_Of_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Remove_Nth_Node_From_End_Of_List {
    public static void main(String[] args) {
        Remove_Nth_Node_From_End_Of_List solution = new Remove_Nth_Node_From_End_Of_List();

        int[] arr = { 1, 2, 5, 6, 7 };
        int n = 2;
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode removedNthNodeList = solution.removeNthFromEnd(head, n);
        LinkedListUtils.printSinglyLinkedList(removedNthNodeList);
    }

    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode slow = dummy;
        ListNode fast = dummy;
        dummy.next = head;
        int count = 0;
        while (fast != null && count < n) {
            fast = fast.next;
            count++;
        }
        ListNode prev = null;
        while (slow != null && fast != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = slow.next;
        return dummy.next;
    }
}
