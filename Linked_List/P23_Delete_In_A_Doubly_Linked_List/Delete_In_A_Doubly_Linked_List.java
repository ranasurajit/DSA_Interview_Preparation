package Linked_List.P23_Delete_In_A_Doubly_Linked_List;

import Linked_List.Utils.DoublyNode;
import Linked_List.Utils.LinkedListUtils;

public class Delete_In_A_Doubly_Linked_List {
    public static void main(String[] args) {
        Delete_In_A_Doubly_Linked_List solution = new Delete_In_A_Doubly_Linked_List();

        int[] arr = { 1, 5, 2, 9 };
        int x = 1;

        DoublyNode head = LinkedListUtils.createDoublyLinkedList(arr);
        LinkedListUtils.printListForward(head);

        DoublyNode newList = solution.deleteNode(head, x);
        LinkedListUtils.printListForward(newList);
    }

    /**
     * Approach : Using Linked-List Traversal Approach
     * 
     * TC: O(Min(N, X))
     * SC: O(1)
     */
    public DoublyNode deleteNode(DoublyNode head, int x) {
        if (head == null) {
            return null;
        }
        if (x == 1) {
            DoublyNode newHead = head.next;
            if (newHead != null) {
                newHead.prev = null;
            }
            return newHead;
        }
        int count = 1;
        DoublyNode prev = null;
        DoublyNode current = head;
        while (current != null && count < x) { // TC: O(Min(N, X))
            prev = current;
            current = current.next;
            count++;
        }
        prev.next = current != null ? current.next : null;
        if (current != null && current.next != null) {
            current.next.prev = prev;
        }
        return head;
    }
}
