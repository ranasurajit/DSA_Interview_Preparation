package Linked_List_Backup.P5_Segregate_Even_And_Odd_Nodes_In_A_Linked_List;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Segregate_Even_And_Odd_Nodes_In_A_Linked_List {
    public static void main(String[] args) {
        Segregate_Even_And_Odd_Nodes_In_A_Linked_List solution = new Segregate_Even_And_Odd_Nodes_In_A_Linked_List();

        int[] arr = { 17, 15, 8, 9, 2, 4, 6 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode segregatedListBF = solution.divideBruteForce(head);
        LinkedListUtils.printSinglyLinkedList(segregatedListBF);

        ListNode segregatedListOptimal = solution.divide(head);
        LinkedListUtils.printSinglyLinkedList(segregatedListOptimal);
    }

    /**
     * Optimal Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    ListNode divide(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode evenHead = null;
        ListNode oddHead = null;
        ListNode evenTail = null;
        ListNode oddTail = null;
        ListNode current = head;
        while (current != null) {
            if (current.val % 2 == 0) {
                // even value nodes
                if (evenHead == null) {
                    evenHead = evenTail = current;
                } else {
                    evenTail.next = current;
                    evenTail = evenTail.next;
                }
            } else {
                // odd value nodes
                if (oddHead == null) {
                    oddHead = oddTail = current;
                } else {
                    oddTail.next = current;
                    oddTail = oddTail.next;
                }
            }
            current = current.next;
        }
        if (evenHead == null) {
            return oddHead;
        }
        if (oddHead == null) {
            return evenHead;
        }
        // removing unwanted values from end of odd node
        oddTail.next = null;
        evenTail.next = oddHead;
        return evenHead;
    }

    /**
     * Brute-Force Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    ListNode divideBruteForce(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode evenHead = new ListNode(-1);
        ListNode oddHead = new ListNode(-1);
        ListNode evenNode = evenHead;
        ListNode oddNode = oddHead;
        ListNode current = head;
        while (current != null) {
            if (current.val % 2 == 0) {
                evenNode.next = new ListNode(current.val);
            } else {
                oddNode.next = new ListNode(current.val);
            }
            if (evenNode.next != null) {
                evenNode = evenNode.next;
            }
            if (oddNode.next != null) {
                oddNode = oddNode.next;
            }
            current = current.next;
        }
        evenNode.next = oddHead.next;
        return evenHead.next;
    }
}
