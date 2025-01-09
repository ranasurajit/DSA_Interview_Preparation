package Linked_List.P4_Odd_Even_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Odd_Even_Linked_List {
    public static void main(String[] args) {
        Odd_Even_Linked_List solution = new Odd_Even_Linked_List();

        int[] arr = { 17, 15, 8, 9, 2, 4, 6 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode oddEvenList = solution.oddEvenList(head);
        LinkedListUtils.printSinglyLinkedList(oddEvenList);
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode evenHead = head.next; // storing even node's head
        // node pointers to traverse
        ListNode oddNode = head;
        ListNode evenNode = evenHead;
        while (evenNode != null && evenNode.next != null) {
            oddNode.next = evenNode.next;
            evenNode.next = evenNode.next.next;
            // move both odd and even node pointers
            oddNode = oddNode.next;
            evenNode = evenNode.next;
        }
        // appending even node head to odd node's end
        oddNode.next = evenHead;
        return head;
    }
}
