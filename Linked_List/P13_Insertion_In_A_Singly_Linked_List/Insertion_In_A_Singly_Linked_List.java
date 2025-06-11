package Linked_List.P13_Insertion_In_A_Singly_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Insertion_In_A_Singly_Linked_List {
    public static void main(String[] args) {
        Insertion_In_A_Singly_Linked_List solution = new Insertion_In_A_Singly_Linked_List();

        int n1 = 5;
        int[] arr1 = { 1, 1, 2, 3, 4 };
        int val1 = 7;
        int pos1 = 1;
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);
        ListNode resultList1 = solution.insert(head1, n1, pos1, val1);
        LinkedListUtils.printSinglyLinkedList(resultList1);

        int n2 = 5;
        int[] arr2 = { 1, 1, 2, 3, 4 };
        int val2 = 7;
        int pos2 = 0;
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);
        ListNode resultList2 = solution.insert(head2, n2, pos2, val2);
        LinkedListUtils.printSinglyLinkedList(resultList2);
    }

    /**
     * Approach : Using Linked-List Traversal and Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private ListNode insert(ListNode head, int n, int pos, int val) {
        if (pos == 0) {
            ListNode newHead = new ListNode(val);
            newHead.next = head;
            return newHead;
        }
        int count = 0;
        ListNode prev = null;
        ListNode current = head;
        while (current != null) { // TC: O(N)
            prev = current;
            current = current.next;
            count++;
            if (count == pos) {
                break;
            }
        }
        prev.next = new ListNode(val);
        prev.next.next = current;
        return head;
    }
}
