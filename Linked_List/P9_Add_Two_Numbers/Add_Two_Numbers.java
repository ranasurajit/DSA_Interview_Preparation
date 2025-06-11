package Linked_List.P9_Add_Two_Numbers;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Add_Two_Numbers {
    public static void main(String[] args) {
        Add_Two_Numbers solution = new Add_Two_Numbers();

        int[] arr1 = { 9, 9, 9, 9, 9, 9, 9 };
        ListNode l1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(l1);

        int[] arr2 = { 9, 9, 9, 9 };
        ListNode l2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(l2);

        ListNode sumListNode = solution.addTwoNumbers(l1, l2);
        System.out.println();
        System.out.println("Sum of above two Linked-Lists is : ");
        LinkedListUtils.printSinglyLinkedList(sumListNode);
    }

    /**
     * Approach : Using Dummy Node and Simulation Approach
     *
     * TC: O(M + N)
     * SC: O(1)
     *
     * where M and N are the lengths of l1 and l2 respectively
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode current1 = l1;
        ListNode current2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        int carry = 0;
        int sum = 0;
        while (current1 != null || current2 != null) {
            sum = carry;
            if (current1 != null) {
                sum += current1.val;
                current1 = current1.next;
            }
            if (current2 != null) {
                sum += current2.val;
                current2 = current2.next;
            }
            int rem = sum % 10;
            carry = sum / 10;
            current.next = new ListNode(rem);
            current = current.next;
        }
        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            current.next = newNode;
        }
        return dummy.next;
    }
}
