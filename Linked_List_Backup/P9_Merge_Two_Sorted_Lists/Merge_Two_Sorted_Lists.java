package Linked_List_Backup.P9_Merge_Two_Sorted_Lists;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Merge_Two_Sorted_Lists {
    public static void main(String[] args) {
        Merge_Two_Sorted_Lists solution = new Merge_Two_Sorted_Lists();

        int[] arr1 = { 1, 2, 4 };
        int[] arr2 = { 1, 3, 4 };

        ListNode list1 = LinkedListUtils.createSinglyLinkedList(arr1);
        ListNode list2 = LinkedListUtils.createSinglyLinkedList(arr2);

        LinkedListUtils.printSinglyLinkedList(list1);
        LinkedListUtils.printSinglyLinkedList(list2);

        ListNode mergedListIterative = solution.mergeTwoListsIterative(list1, list2);
        LinkedListUtils.printSinglyLinkedList(mergedListIterative);

        int[] arr3 = { 1, 2, 4 };
        int[] arr4 = { 1, 3, 4 };

        ListNode list3 = LinkedListUtils.createSinglyLinkedList(arr3);
        ListNode list4 = LinkedListUtils.createSinglyLinkedList(arr4);

        ListNode mergedListRecursive = solution.mergeTwoListsRecursive(list3, list4);
        LinkedListUtils.printSinglyLinkedList(mergedListRecursive);
    }

    /**
     * Iterative Approach
     * 
     * TC: O(M + N)
     * SC: O(1)
     * 
     * where M and N are lengths of LinkedList list1 and list2 respectively
     * 
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoListsIterative(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode p = list1;
        ListNode q = list2;

        // Create a node reference to return
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy; // pointer to traverse
        while (p != null && q != null) {
            if (p.val < q.val) {
                temp.next = p;
                p = p.next;
            } else {
                temp.next = q;
                q = q.next;
            }
            temp = temp.next;
        }
        // check if any of the pointer is not yet null
        if (p != null) {
            temp.next = p;
        }
        if (q != null) {
            temp.next = q;
        }
        return dummy.next;
    }

    /**
     * Recursive Approach
     * 
     * TC: O(M + N)
     * SC: O(M + N)
     * 
     * where M and N are lengths of LinkedList list1 and list2 respectively
     * 
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursive(list1, list2.next);
            return list2;
        }
    }
}
