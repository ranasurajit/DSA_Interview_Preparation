package Linked_List.P1_Delete_Node_In_A_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Delete_Node_In_A_Linked_List {
    public static void main(String[] args) {
        Delete_Node_In_A_Linked_List solution = new Delete_Node_In_A_Linked_List();
        int[] arr = { 4, 5, 1, 9 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        int target = 1;
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
     * TC: O(N)
     * SC: O(1)
     * 
     * @param node
     */
    public void deleteNode(ListNode node) {
        ListNode prev = null;
        while (node != null && node.next != null) { // TC: O(N)
            // setting the node value to node's next value so that target node value is
            // deleted
            node.val = node.next.val;
            prev = node;
            node = node.next;
        }
        prev.next = null;
    }
}
