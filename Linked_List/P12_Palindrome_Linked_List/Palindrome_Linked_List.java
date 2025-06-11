package Linked_List.P12_Palindrome_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Palindrome_Linked_List {
    public static void main(String[] args) {
        Palindrome_Linked_List solution = new Palindrome_Linked_List();

        int[] arr1 = { 1, 2, 2, 1 };
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);
        boolean isPalindromeList1 = solution.isPalindrome(head1);
        System.out.println(isPalindromeList1);

        int[] arr2 = { 1, 2, 4, 2, 1 };
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);
        boolean isPalindromeList2 = solution.isPalindrome(head2);
        System.out.println(isPalindromeList2);

        int[] arr3 = { 1, 2, 3, 3, 1 };
        ListNode head3 = LinkedListUtils.createSinglyLinkedList(arr3);
        LinkedListUtils.printSinglyLinkedList(head3);
        boolean isPalindromeList3 = solution.isPalindrome(head3);
        System.out.println(isPalindromeList3);
    }

    /**
     * Approach : Using Linked-List Traversal and Two Pointers Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode midNode = middleLL(head); // TC: O(N / 2)
        midNode = midNode.next;
        ListNode revList = reverseLL(midNode);
        ListNode current1 = head;
        ListNode current2 = revList;
        while (current1 != null && current2 != null) { // TC: O(N / 2)
            if (current1.val != current2.val) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }
        return true;
    }

    /**
     * Using Iterative Approach to Reverse the Linked-List
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private ListNode reverseLL(ListNode head) {
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

    /**
     * Using Two Pointers (Fast and Slow Pointers) Approach
     * 
     * TC: O(N / 2)
     * SC: O(1)
     */
    private ListNode middleLL(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) { // TC: O(N)
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
