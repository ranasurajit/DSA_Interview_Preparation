package Linked_List_Backup.P21_Add_Two_Numbers_III;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Add_Two_Numbers_III {
    public static void main(String[] args) {
        Add_Two_Numbers_III solution = new Add_Two_Numbers_III();

        int[] arr1 = { 0, 0, 6, 3 };
        int[] arr2 = { 0, 7 };
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);

        ListNode addedList = solution.addTwoLists(head1, head2);
        LinkedListUtils.printSinglyLinkedList(addedList);
    }

    /**
     * Using Two Pointers Approach
     * 
     * TC: O(3 x (M + N)) ~ O(M + N)
     * SC: O(1)
     * 
     * @param num1
     * @param num2
     * @return
     */
    private ListNode addTwoLists(ListNode num1, ListNode num2) {
        if (num1 == null) {
            return num2;
        }
        if (num2 == null) {
            return num1;
        }
        // reverse both the linked-lists
        num1 = reverseLL(num1); // TC: O(M)
        num2 = reverseLL(num2); // TC: O(N)
        // proceed with add operation
        int carry = 0;
        ListNode curr1 = num1;
        ListNode curr2 = num2;
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while (curr1 != null || curr2 != null) { // TC: O(M + N)
            int sum = carry;
            if (curr1 != null) {
                sum += curr1.val;
                curr1 = curr1.next;
            }
            if (curr2 != null) {
                sum += curr2.val;
                curr2 = curr2.next;
            }
            if (sum > 9) {
                carry = sum / 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            temp.next = new ListNode(sum);
            temp = temp.next;
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        // remove leading zeros after reversing the linked-list
        return removeLeadingZeros(reverseLL(dummy.next)); // TC: O(2 x (M + N)) ~ O(M + N)
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    private ListNode reverseLL(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    private ListNode removeLeadingZeros(ListNode head) {
        while (head.val == 0) {
            head = head.next;
        }
        return head;
    }
}
