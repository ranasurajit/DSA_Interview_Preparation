package Linked_List.P1_Delete_Node_In_A_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Delete_Node_In_A_Linked_List {
    public static void main(String[] args) {
        Delete_Node_In_A_Linked_List solution = new Delete_Node_In_A_Linked_List();

        int[] arr = { 4, 5, 1, 9 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        int target = 5;
        ListNode node = null;
        ListNode current = head;
        while (current != null) {
            if (current.val == target) {
                node = current;
            }
            current = current.next;
        }
        solution.deleteNode(node);
        LinkedListUtils.printSinglyLinkedList(head);
    }

    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        ListNode prev = null;
        ListNode current = node;
        while (current != null && current.next != null) { // TC: O(N)
            current.val = current.next.val;
            prev = current;
            current = current.next;
        }
        prev.next = null;
    }
}
