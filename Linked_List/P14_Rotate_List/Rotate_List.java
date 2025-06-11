package Linked_List.P14_Rotate_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Rotate_List {
    public static void main(String[] args) {
        Rotate_List solution = new Rotate_List();

        int[] arr = { 1, 2, 3, 4, 5 };
        int k = 2;

        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode rotatedList = solution.rotateRight(head, k);
        LinkedListUtils.printSinglyLinkedList(rotatedList);
    }

    /**
     * Approach : Using Traversal and Reversal of Linked-List Approach
     * 
     * TC: O(3 x N) ~ O(N)
     * SC: O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode revList = reverseLL(head); // TC: O(N)
        int count = 0;
        ListNode prev = null;
        ListNode current = revList;
        int length = 0;
        while (current != null) { // TC: O(N)
            current = current.next;
            length++;
        }
        k = k % length;
        current = revList;
        while (current != null) { // TC: O(N)
            prev = current;
            current = current.next;
            count++;
            if (count == k) {
                break;
            }
        }
        ListNode sectionList1 = revList;
        ListNode sectionList2 = prev.next;
        prev.next = null;
        ListNode revSecList1 = reverseLL(sectionList1); // TC: O(K)
        ListNode revSecList2 = reverseLL(sectionList2); // TC: O(N - K)
        sectionList1.next = revSecList2;
        return revSecList1;
    }

    /**
     * Using Iterative Approach to Reverse the Linked-List
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private ListNode reverseLL(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        while (current != null) { // TC: O(N)
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }
}
