package Linked_List_Backup.P23_First_Node_Of_The_Loop_In_A_Linked_List;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class First_Node_Of_The_Loop_In_A_Linked_List {
    public static void main(String[] args) {
        First_Node_Of_The_Loop_In_A_Linked_List solution = new First_Node_Of_The_Loop_In_A_Linked_List();

        int[] arr = { 1, 2, 3, 4 };
        int pos1 = 1;
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head1);

        solution.createCycle(head1, pos1);

        ListNode cycleNode1 = solution.findFirstNode(head1);
        System.out.println(cycleNode1.val);

        int pos2 = 0;
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head2);

        solution.createCycle(head2, pos2);

        ListNode cycleNode2 = solution.findFirstNode(head2);
        System.out.println(cycleNode2.val);
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
     * SC: O(N)
     */
    public ListNode findFirstNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // move the slow pointer by 1 step and fast pointer by 2 steps
        while (fast != null && fast.next != null) { // TC: O(N)
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            // there is no loop
            return null;
        }
        // the linked-list has a loop
        // if slow and fast meets at head
        if (slow == head && fast == head) {
            return head;
        }
        // now set the fast pointer to head and move both slow and fast pointers by 1
        // step
        fast = head;
        while (fast != null) { // TC: O(N)
            slow = slow.next;
            fast = fast.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }
}
