package Linked_List.P10_Merge_K_Sorted_Lists;

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

        ListNode mergedKListsInc = solution.mergeKListsIncremental(lists1);
        LinkedListUtils.printSinglyLinkedList(mergedKListsInc);

        int[] arr4 = { 1, 4, 5 };
        int[] arr5 = { 1, 3, 4 };
        int[] arr6 = { 2, 6 };

        ListNode[] lists2 = new ListNode[] {
                LinkedListUtils.createSinglyLinkedList(arr4),
                LinkedListUtils.createSinglyLinkedList(arr5),
                LinkedListUtils.createSinglyLinkedList(arr6)
        };
        ListNode mergedKListsOpt = solution.mergeKListsOptimal(lists2);
        LinkedListUtils.printSinglyLinkedList(mergedKListsOpt);
    }

    /**
     * Optimal Approach (Using Prority Queue (Min-Heap))
     * 
     * TC: O(K + K x L) ~ O(K x L), where L is the average length of Linked-Lists
     * SC: O(K)
     * 
     * @param lists
     * @return
     */
    public ListNode mergeKListsOptimal(ListNode[] lists) {
        int k = lists.length;
        if (k == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((p, q) -> {
            return p.val - q.val;
        }); // SC: O(K)
        for (int i = 0; i < k; i++) { // TC: O(K)
            if (lists[i] != null) {
                pq.offer(lists[i]);
            }
        }
        while (!pq.isEmpty()) {
            current.next = pq.poll();
            current = current.next;
            if (current.next != null) {
                pq.offer(current.next);
            }
        }
        return dummy.next;
    }

    /**
     * Using Incremental Approach (Merge Two LinkedList Approach)
     * 
     * TC: O(K x (M + N))
     * SC: O(M + N)
     *
     * @param lists
     * @return
     */
    private ListNode mergeKListsIncremental(ListNode[] lists) {
        int k = lists.length;
        if (k == 0) {
            return null;
        }
        for (int i = 1; i < k; i++) { // TC: O(K)
            lists[i] = mergeTwoLists(lists[i - 1], lists[i]); // TC: O(M + N)
        }
        return lists[k - 1];
    }

    /**
     * TC: O(M + N)
     * SC: O(M + N)
     * 
     * @param list1
     * @param list2
     * @return
     */
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
