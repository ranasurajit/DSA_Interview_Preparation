package Linked_List.P15_Odd_Even_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Odd_Even_Linked_List {
    public static void main(String[] args) {
        Odd_Even_Linked_List solution = new Odd_Even_Linked_List();

        int[] arr1 = { 1, 2, 3, 4, 5 };
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);

        ListNode resultList1 = solution.oddEvenListUsingExtraSpace(head1);
        LinkedListUtils.printSinglyLinkedList(resultList1);

        int[] arr2 = { 2, 1, 3, 5, 6, 4, 7 };
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);

        ListNode resultList2 = solution.oddEvenListOptimal(head2);
        LinkedListUtils.printSinglyLinkedList(resultList2);
    }

    /**
     * Approach II : Using Traversal of Linked-List Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public ListNode oddEvenListOptimal(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddHead = null;
        ListNode oddTail = null;
        ListNode evenHead = null;
        ListNode evenTail = null;
        ListNode current = head;
        int count = 1;
        while (current != null) {
            if ((count & 1) != 0) {
                if (oddHead == null) {
                    oddHead = current;
                    oddTail = current;
                } else {
                    oddTail.next = current;
                    oddTail = oddTail.next;
                }
            } else {
                if (evenHead == null) {
                    evenHead = current;
                    evenTail = current;
                } else {
                    evenTail.next = current;
                    evenTail = evenTail.next;
                }
            }
            current = current.next;
            count++;
        }
        if (evenHead == null) {
            return oddHead;
        }
        if (oddHead == null) {
            return evenHead;
        }
        evenTail.next = null;
        oddTail.next = evenHead;
        return oddHead;
    }

    /**
     * Approach I : Using Traversal of Linked-List (Extra Space) Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public ListNode oddEvenListUsingExtraSpace(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddHead = new ListNode(-1);
        ListNode oddTail = oddHead;
        ListNode evenHead = new ListNode(-1);
        ListNode evenTail = evenHead;
        ListNode current = head;
        int count = 1;
        while (current != null) { // TC: O(N)
            if ((count & 1) == 1) {
                // odd count
                oddTail.next = new ListNode(current.val);
                oddTail = oddTail.next;
            } else {
                // even count
                evenTail.next = new ListNode(current.val);
                evenTail = evenTail.next;
            }
            current = current.next;
            count++;
        }
        oddTail.next = evenHead.next;
        return oddHead.next;
    }
}
