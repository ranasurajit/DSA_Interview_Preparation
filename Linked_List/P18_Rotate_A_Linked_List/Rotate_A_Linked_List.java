package Linked_List.P18_Rotate_A_Linked_List;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Rotate_A_Linked_List {
    public static void main(String[] args) {
        Rotate_A_Linked_List solution = new Rotate_A_Linked_List();

        int[] arr = { 10, 20, 30, 40, 50 };
        int k = 4;

        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode rotatedList = solution.rotate(head, k);
        LinkedListUtils.printSinglyLinkedList(rotatedList);
    }

    /**
     * TC: O(N + K)
     * SC: O(1)
     * 
     * @param head
     * @param k
     * @return
     */
    public ListNode rotate(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int length = 0;
        ListNode current = head;
        ListNode tail = head;
        while (current != null) { // TC: O(N)
            tail = current;
            current = current.next;
            length++;
        }
        k = k % length; // this is done to normalize rotation if k > length
        if (k == 0) {
            return head;
        }
        ListNode prev = null;
        current = head;
        while (current != null && k > 0) { // TC: O(K)
            prev = current;
            current = current.next;
            k--;
        }
        prev.next = null;
        tail.next = head;
        return current;
    }
}
