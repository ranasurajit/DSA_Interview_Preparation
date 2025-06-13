package Linked_List.P21_Sort_A_Linked_List_Of_0s_1s_And_2s;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Sort_A_Linked_List_Of_0s_1s_And_2s {
    public static void main(String[] args) {
        Sort_A_Linked_List_Of_0s_1s_And_2s solution = new Sort_A_Linked_List_Of_0s_1s_And_2s();

        int[] arr = { 1, 2, 2, 1, 2, 0, 2, 2, 0 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode sortedList = solution.segregate(head);
        LinkedListUtils.printSinglyLinkedList(sortedList);
    }

    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private ListNode segregate(ListNode head) {
        ListNode zeroHead = new ListNode(-1);
        ListNode oneHead = new ListNode(-1);
        ListNode twoHead = new ListNode(-1);
        ListNode curr0 = zeroHead;
        ListNode curr1 = oneHead;
        ListNode curr2 = twoHead;
        ListNode current = head;
        while (current != null) { // TC: O(N)
            if (current.val == 0) {
                curr0.next = current;
                curr0 = curr0.next;
            } else if (current.val == 1) {
                curr1.next = current;
                curr1 = curr1.next;
            } else {
                curr2.next = current;
                curr2 = curr2.next;
            }
            current = current.next;
        }
        // Terminate the last node to avoid cycle or incorrect linking
        curr2.next = null;
        // creating all links
        curr1.next = twoHead.next;
        curr0.next = oneHead.next;
        return zeroHead.next;
    }
}
