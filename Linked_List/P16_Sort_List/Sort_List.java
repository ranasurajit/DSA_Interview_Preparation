package Linked_List.P16_Sort_List;

import java.util.PriorityQueue;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Sort_List {
    public static void main(String[] args) {
        Sort_List solution = new Sort_List();

        int[] arr1 = { 4, 2, 1, 3 };
        ListNode head1 = LinkedListUtils.createSinglyLinkedList(arr1);
        LinkedListUtils.printSinglyLinkedList(head1);

        ListNode sortedList1 = solution.sortListUsingHeaps(head1);
        LinkedListUtils.printSinglyLinkedList(sortedList1);

        int[] arr2 = { 2, 1, 3, 5, -1, 6, 4, 7, 3 };
        ListNode head2 = LinkedListUtils.createSinglyLinkedList(arr2);
        LinkedListUtils.printSinglyLinkedList(head2);

        ListNode sortedList2 = solution.sortListOptimal(head2);
        LinkedListUtils.printSinglyLinkedList(sortedList2);
    }

    /**
     * Approach II : Using Merge Sort Approach
     *
     * TC: O(N x log(N) + N) ~ O(N x log(N))
     * SC: O(log(N))
     */
    public ListNode sortListOptimal(ListNode head) {
        if (head == null || head.next == null) {
            // list is already sorted
            return head;
        }
        ListNode midNode = middleLL(head); // TC: O(N / 2), SC: O(1)
        // splitting Linked-List into two halves
        ListNode leftHead = head;
        ListNode rightHead = midNode.next;
        midNode.next = null;
        // sorting both halves by Recursion
        leftHead = sortListOptimal(leftHead); // TC: log(N), each time the size is reduced by 1/2
        rightHead = sortListOptimal(rightHead); // TC: log(N), each time the size is reduced by 1/2
        return mergeSortedLists(leftHead, rightHead); // TC: O(N), SC: O(N)
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private ListNode mergeSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode mergedList = new ListNode(-1);
        ListNode p = l1; // pointer at the start of list l1
        ListNode q = l2; // pointer at the start of list l2
        ListNode r = mergedList; // pointer at the start of resultant list mergedList
        while (p != null && q != null) { // TC: O(N)
            if (p.val < q.val) {
                r.next = p;
                p = p.next;
            } else {
                r.next = q;
                q = q.next;
            }
            r = r.next;
        }
        if (p != null) {
            r.next = p;
        } else if (q != null) {
            r.next = q;
        }
        return mergedList.next;
    }

    /**
     * Using Fast and Slow Pointers Approach
     *
     * TC: O(N / 2)
     * SC: O(1)
     */
    private ListNode middleLL(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // mobve slow pointer by 1 step and fast pointer by 2 steps
        while (fast != null && fast.next != null && fast.next.next != null) { // TC: O(N / 2)
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * Approach I : Using Min-Heap (PriorityQueue) Approach
     *
     * TC: O(N x log(N) + N) ~ O(N x log(N))
     * SC: O(N)
     */
    public ListNode sortListUsingHeaps(ListNode head) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // SC: O(N)
        ListNode current = head;
        while (current != null) { // TC: O(N)
            pq.offer(current.val); // TC: O(log(N))
            current = current.next;
        }
        ListNode dummy = new ListNode(-1);
        current = dummy;
        while (!pq.isEmpty()) { // TC: O(N)
            current.next = new ListNode(pq.poll());
            current = current.next;
        }
        return dummy.next;
    }
}
