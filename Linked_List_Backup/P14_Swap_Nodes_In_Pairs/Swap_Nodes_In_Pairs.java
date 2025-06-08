package Linked_List_Backup.P14_Swap_Nodes_In_Pairs;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Swap_Nodes_In_Pairs {
    public static void main(String[] args) {
        Swap_Nodes_In_Pairs solution = new Swap_Nodes_In_Pairs();
        int[] arr = { 1, 2, 3, 4 };

        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode resultList = solution.swapPairs(head);
        LinkedListUtils.printSinglyLinkedList(resultList);
    }

    /**
     * Using Recursion
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextNode = head.next;
        ListNode futureNode = nextNode.next;
        head.next = swapPairs(futureNode);
        nextNode.next = head;
        return nextNode;
    }
}
