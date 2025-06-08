package Linked_List_Backup.P24_Remove_Loop_In_Linked_List;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Remove_Loop_In_Linked_List {
    public static void main(String[] args) {
        Remove_Loop_In_Linked_List solution = new Remove_Loop_In_Linked_List();

        int[] arr = { 16, 16, 10, 12, 9, 9, 14, 17, 12, 7, 18, 8, 16, 8, 9, 8, 11, 8, 2, 18, 2, 18 };
        int pos = 6;
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        solution.createCycle(head, pos);

        solution.removeLoop(head);
        LinkedListUtils.printSinglyLinkedList(head);
    }

    private void createCycle(ListNode head, int index) {
        ListNode tail = null;
        ListNode current = head;
        ListNode cycleNode = null;
        int count = 0;
        while (current != null) {
            if (count == index) {
                cycleNode = current;
            }
            tail = current;
            current = current.next;
            count++;
        }
        tail.next = cycleNode;
    }

    /**
     * Using Two (Fast and Slow) Pointers Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     * 
     * @param head
     */
    // Function to remove a loop in the linked list.
    public void removeLoop(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        // Move slow pointer by 1 step and fast pointer by 2 steps
        while (fast != null && fast.next != null) { // TC: O(N)
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            // no loop present
            return;
        }
        // if head is the start node of the loop
        if (slow == fast && fast == head) {
            prev.next = null;
            return;
        }
        // if node other than head node is the start node of the loop
        // set fast = head;
        // Move both slow and fast pointers by 1 step
        fast = head;
        while (fast != null) { // TC: O(N)
            prev = slow;
            slow = slow.next;
            fast = fast.next;
            if (slow == fast) {
                break;
            }
        }
        prev.next = null;
    }
}
