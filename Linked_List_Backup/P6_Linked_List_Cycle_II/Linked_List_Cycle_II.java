package Linked_List_Backup.P6_Linked_List_Cycle_II;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Linked_List_Cycle_II {
    public static void main(String[] args) {
        Linked_List_Cycle_II solution = new Linked_List_Cycle_II();

        int[] arr = { 3, 2, 0, -4 };
        int pos = 1;
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        solution.createCycle(head, pos);

        ListNode cycleNode = solution.detectCycle(head);
        System.out.println(cycleNode.val);
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
     * Using Slow and Fast pointers approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // move the slow pointer by 1 step and fast pointer by 2 steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // here if fast and slow pointer meet then there is a cycle
            if (slow == fast) {
                break;
            }
        }
        if (slow != fast) {
            // there is no cycle
            return null;
        }
        // assign fast to head and move both pointers by 1 step each
        fast = head;
        while (slow != null && fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
