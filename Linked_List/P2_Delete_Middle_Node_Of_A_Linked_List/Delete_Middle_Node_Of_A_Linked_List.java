package Linked_List.P2_Delete_Middle_Node_Of_A_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Delete_Middle_Node_Of_A_Linked_List {
    public static void main(String[] args) {
        Delete_Middle_Node_Of_A_Linked_List solution = new Delete_Middle_Node_Of_A_Linked_List();

        int[] arr = { 1, 3, 4, 7, 1, 2, 6 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode modifiedListNode = solution.deleteMiddle(head);
        LinkedListUtils.printSinglyLinkedList(modifiedListNode);
    }

    /**
     * Using Two Pointers (Slow and Fast pointers)
     * 
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     * 
     * @param head
     * @return
     */
    public ListNode deleteMiddle(ListNode head) {
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
        /**
         * here slow will be the mid of Linked List and prev node's
         * next will be the middle node so move it
         */
        prev.next = prev.next.next;
        return head;
    }
}
