package Linked_List.P20_Linked_List_Group_Reverse;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Linked_List_Group_Reverse {
    public static void main(String[] args) {
        Linked_List_Group_Reverse solution = new Linked_List_Group_Reverse();

        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int k = 4;

        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode reversedKList = solution.reverseKGroup(head, k);
        LinkedListUtils.printSinglyLinkedList(reversedKList);
    }

    /**
     * TC: O(N)
     * SC: O(N)
     * 
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int count = 0;
        ListNode prev = null;
        ListNode current = head;
        while (current != null) { // TC: O(K)
            count++;
            prev = current;
            current = current.next;
            if (count == k) {
                break;
            }
        }
        prev.next = null;
        ListNode newHead = reverseLL(head); // TC: O(K), SC: O(K)
        head.next = reverseKGroup(current, k); // TC: O(N - K), SC: O(N - K)
        return newHead;
    }

    /**
     * TC: O(N)
     * SC: O(N)
     * 
     * @param head
     * @return
     */
    private ListNode reverseLL(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextNode = head.next;
        ListNode newHead = reverseLL(nextNode);
        head.next = null;
        nextNode.next = head;
        return newHead;
    }
}
