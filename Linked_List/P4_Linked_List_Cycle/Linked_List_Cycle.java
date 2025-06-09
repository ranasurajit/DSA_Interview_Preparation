package Linked_List.P4_Linked_List_Cycle;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Linked_List_Cycle {
    public static void main(String[] args) {
        Linked_List_Cycle solution = new Linked_List_Cycle();

        int[] arr1 = { 3, 2, 0, -4 };
        int pos1 = 1;
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);

        LinkedListUtils.createCycle(head1, pos1);

        boolean containsCycle1 = solution.hasCycle(head1);
        System.out.println(containsCycle1);

        int[] arr2 = { 1, 2 };
        int pos2 = -1;
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);

        LinkedListUtils.createCycle(head2, pos2);

        boolean containsCycle2 = solution.hasCycle(head2);
        System.out.println(containsCycle2);
    }

    /**
     * Approach : Using Two Pointers (Fast and Slow Pointers) Approach
     *
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { // TC: O(N / 2)
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                // if at any time fast and slow pointers meet, then a cycle is present
                return true;
            }
        }
        return false;
    }
}
