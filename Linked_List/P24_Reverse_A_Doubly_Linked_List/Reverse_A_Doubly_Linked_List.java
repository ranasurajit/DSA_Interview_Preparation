package Linked_List.P24_Reverse_A_Doubly_Linked_List;

import Linked_List.Utils.DoublyNode;
import Linked_List.Utils.LinkedListUtils;

public class Reverse_A_Doubly_Linked_List {
    public static void main(String[] args) {
        Reverse_A_Doubly_Linked_List solution = new Reverse_A_Doubly_Linked_List();

        int[] arr = { 1, 5, 2, 9 };

        DoublyNode head = LinkedListUtils.createDoublyLinkedList(arr);
        LinkedListUtils.printListForward(head);

        DoublyNode revHead = solution.reverseDLL(head);
        LinkedListUtils.printListForward(revHead);
    }

    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public DoublyNode reverseDLL(DoublyNode head) {
        DoublyNode prev = null;
        DoublyNode current = head;
        while (current != null) { // TC: O(N)
            DoublyNode temp = current.next;
            current.next = prev;
            prev = current;
            prev.prev = current;
            current = temp;
        }
        return prev;
    }
}
