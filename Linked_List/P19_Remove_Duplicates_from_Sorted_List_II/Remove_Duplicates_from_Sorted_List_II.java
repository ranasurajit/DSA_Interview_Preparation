package Linked_List.P19_Remove_Duplicates_from_Sorted_List_II;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Remove_Duplicates_from_Sorted_List_II {
    public static void main(String[] args) {
        Remove_Duplicates_from_Sorted_List_II solution = new Remove_Duplicates_from_Sorted_List_II();

        int[] arr1 = { 1, 2, 3, 3, 4, 4, 5 };
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);
        ListNode uniqueList1 = solution.deleteDuplicates(head1);
        LinkedListUtils.printSinglyLinkedList(uniqueList1);

        int[] arr2 = { 1, 1, 1, 2, 3 };
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);
        ListNode uniqueList2 = solution.deleteDuplicates(head2);
        LinkedListUtils.printSinglyLinkedList(uniqueList2);
    }

    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) { // TC: O(N)
            if (current.next != null && current.next.val == current.val) {
                int value = current.val;
                while (current != null && current.val == value) {
                    current = current.next;
                }
                if (prev == null) {
                    head = current;
                } else {
                    prev.next = current;
                }
            } else {
                prev = current;
                current = current.next;
            }
        }
        return head;
    }
}
