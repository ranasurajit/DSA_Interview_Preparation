package Linked_List_Backup.P12_Maximum_Twin_Sum_Of_A_Linked_List;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Maximum_Twin_Sum_Of_A_Linked_List {
    public static void main(String[] args) {
        Maximum_Twin_Sum_Of_A_Linked_List solution = new Maximum_Twin_Sum_Of_A_Linked_List();

        int[] arr = { 4, 2, 2, 3 };

        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        int pairSumMax = solution.pairSum(head);
        System.out.println(pairSumMax);
    }

    /**
     * Using Slow and Fast Pointers and Reversing Linked-List Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     * 
     * @param head
     * @return
     */
    private int pairSum(ListNode head) {
        if (head == null) {
            return 0;
        }
        ListNode mid = middle(head); // TC: O(N / 2)
        ListNode rev = reverse(mid); // TC: O(N)
        int currentSum = 0;
        int maxSum = 0;
        ListNode headCurrent = head;
        ListNode revCurrent = rev;
        while (revCurrent != null) { // TC: O(N / 2)
            currentSum = headCurrent.val + revCurrent.val;
            maxSum = Math.max(maxSum, currentSum);
            headCurrent = headCurrent.next;
            revCurrent = revCurrent.next;
        }
        return maxSum;
    }

    /**
     * Using Iterative Approach
     *
     * TC: O(N)
     * SC: O(1)
     * 
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
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
     * Using Slow and Fast Pointers Approach
     *
     * TC: O(N / 2)
     * SC: O(1)
     * 
     * @param head
     * @return
     */
    private ListNode middle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
