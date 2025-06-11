package Linked_List.P10_Add_1_To_A_Linked_List_Number;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Add_1_To_A_Linked_List_Number {
    public static void main(String[] args) {
        Add_1_To_A_Linked_List_Number solution = new Add_1_To_A_Linked_List_Number();

        int[] arr = { 9, 9, 9, 9, 9, 9, 9, 9 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode resultList = solution.addOne(head);
        LinkedListUtils.printSinglyLinkedList(resultList);
    }

    /**
     * Approach : Using Linked-List Traversal and Simulation Approach
     *
     * TC: O(3 x N) ~ O(N)
     * SC: O(1)
     */
    public ListNode addOne(ListNode head) {
        ListNode revList = reverseLL(head); // TC: O(N)
        ListNode current = revList;
        int carry = 1;
        int sum = 0;
        while (current != null) { // TC: O(N)
            sum = carry + current.val;
            int rem = sum % 10;
            carry = sum / 10;
            current.val = rem;
            current = current.next;
        }
        head = reverseLL(revList); // TC: O(N)
        if (carry > 0) {
            ListNode newHead = new ListNode(carry);
            newHead.next = head;
            return newHead;
        }
        return head;
    }

    /**
     * Using Iterative Approach to Reverse the Linked-List
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private ListNode reverseLL(Linked_List.Utils.LinkedListUtils.ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        while (current != null) { // TC: O(N)
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }
}
