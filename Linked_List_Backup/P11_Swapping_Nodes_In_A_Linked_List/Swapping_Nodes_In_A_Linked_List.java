package Linked_List_Backup.P11_Swapping_Nodes_In_A_Linked_List;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Swapping_Nodes_In_A_Linked_List {
    public static void main(String[] args) {
        Swapping_Nodes_In_A_Linked_List solution = new Swapping_Nodes_In_A_Linked_List();

        int[] arr = { 7, 9, 6, 6, 7, 8, 3, 0, 9, 5 };
        int k = 3;

        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode resultList = solution.swapNodes(head, k);
        LinkedListUtils.printSinglyLinkedList(resultList);
    }

    /**
     * Using Slow and Fast Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     * 
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int count = 1;
        ListNode slow = head;
        ListNode fast = head;
        ListNode first = head;
        ListNode second = head;

        while (count < k) { // TC: O(K)
            fast = fast.next;
            count++;
        }
        first = fast;
        while (fast != null && fast.next != null) { // TC: O(N - K)
            slow = slow.next;
            fast = fast.next;
        }
        second = slow;
        // swap values of first and second ListNodes
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
        return head;
    }
}
