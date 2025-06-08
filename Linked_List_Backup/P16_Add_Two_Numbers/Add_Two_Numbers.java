package Linked_List_Backup.P16_Add_Two_Numbers;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Add_Two_Numbers {
    public static void main(String[] args) {
        Add_Two_Numbers solution = new Add_Two_Numbers();
        int[] arr1 = { 9, 9, 9, 9, 9, 9, 9 };
        int[] arr2 = { 9, 9, 9, 9 };
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);

        ListNode addedList = solution.addTwoNumbers(head1, head2);
        LinkedListUtils.printSinglyLinkedList(addedList);
    }

    /**
     * TC: O(M + N)
     * SC: O(1)
     * 
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = carry + l1.val + l2.val;
            if (sum > 9) {
                carry = sum / 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            temp.next = new ListNode(sum);
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = carry + l1.val;
            if (sum > 9) {
                carry = sum / 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            temp.next = new ListNode(sum);
            temp = temp.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = carry + l2.val;
            if (sum > 9) {
                carry = sum / 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            temp.next = new ListNode(sum);
            temp = temp.next;
            l2 = l2.next;
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
