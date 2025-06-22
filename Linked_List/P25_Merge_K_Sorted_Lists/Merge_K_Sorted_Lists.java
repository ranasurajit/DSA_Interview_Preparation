package Linked_List.P25_Merge_K_Sorted_Lists;

import java.util.PriorityQueue;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Merge_K_Sorted_Lists {
    public static void main(String[] args) {
        Merge_K_Sorted_Lists solution = new Merge_K_Sorted_Lists();

        int[] arr1 = { 1, 4, 5 };
        int[] arr2 = { 1, 3, 4 };
        int[] arr3 = { 2, 6 };

        ListNode[] lists1 = new ListNode[] {
                LinkedListUtils.createSinglyLinkedList(arr1),
                LinkedListUtils.createSinglyLinkedList(arr2),
                LinkedListUtils.createSinglyLinkedList(arr3)
        };

        ListNode mergedKListsInc = solution.mergeKLists(lists1);
        LinkedListUtils.printSinglyLinkedList(mergedKListsInc);

        int[] arr4 = { 1, 4, 5 };
        int[] arr5 = { 1, 3, 4 };
        int[] arr6 = { 2, 6 };

        ListNode[] lists2 = new ListNode[] {
                LinkedListUtils.createSinglyLinkedList(arr4),
                LinkedListUtils.createSinglyLinkedList(arr5),
                LinkedListUtils.createSinglyLinkedList(arr6)
        };
        ListNode mergedKListsOpt = solution.mergeKLists(lists2);
        LinkedListUtils.printSinglyLinkedList(mergedKListsOpt);
    }

    /**
     * Approach : Using Min-Heap Approach
     *
     * TC: O(K + K x L), where L = average length of K sorted lists
     * SC: O(K)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        if (k == 0) {
            return null;
        }
        // we will be adding the head of 'k' sorted lists to Min-Heap (PriorityQueue)
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((p, q) -> p.val - q.val); // SC: O(K)
        for (int i = 0; i < k; i++) { // TC: O(K)
            if (lists[i] != null) {
                pq.offer(lists[i]);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (!pq.isEmpty()) { // TC: O(K x L)
            ListNode temp = pq.poll();
            current.next = temp;
            if (temp.next != null) {
                pq.offer(temp.next);
            }
            current = current.next;
        }
        return dummy.next;
    }
}
