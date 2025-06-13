package Linked_List.P20_Reverse_Nodes_In_K_Group;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Reverse_Nodes_In_K_Group {
    public static void main(String[] args) {
        Reverse_Nodes_In_K_Group solution = new Reverse_Nodes_In_K_Group();

        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);
        int k = 3;

        ListNode kReversedList = solution.reverseKGroup(head, k);
        LinkedListUtils.printSinglyLinkedList(kReversedList);
    }

    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(K x (N / K))
     * SC: O(N / K)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev = null;
        ListNode current = head;
        int count = 0;
        while (count < k && current != null) { // TC: O(K)
            prev = current;
            current = current.next;
            count++;
        }
        // prev pointer has the end of 1st segment and current pointer is the start of
        // next segment
        // remove next pointer of prev to separate the list segment
        if (prev == null) {
            return head;
        }
        prev.next = null;
        // reverse the list (if count == k)
        ListNode reversedList = count == k ? reverseLL(head) : head; // TC: O(K)
        ListNode nextSegmentListNode = reverseKGroup(current, k); // TC: O(K)
        ListNode temp = reversedList;
        while (temp != null && temp.next != null) {
            temp = temp.next;
        }
        // at this point we have the tail of reversed Linked-List segment
        temp.next = nextSegmentListNode;
        return reversedList;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(K)
     * SC: O(1)
     */
    private ListNode reverseLL(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }
}
