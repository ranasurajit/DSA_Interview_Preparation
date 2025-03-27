package Linked_List.P3_Middle_Of_The_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Middle_Of_The_Linked_List {
    public static void main(String[] args) {
        Middle_Of_The_Linked_List solution = new Middle_Of_The_Linked_List();

        int[] arr = { 1, 2, 3, 4, 5, 6 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode middleListNode = solution.middleNode(head);
        LinkedListUtils.printSinglyLinkedList(middleListNode);
    }

    /**
     * Using Two Pointers (Slow and Fast pointers)
     * 
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     * 
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { // TC: O(N / 2)
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow pointer will point to mid node of Linked List
        return slow;
    }
}
