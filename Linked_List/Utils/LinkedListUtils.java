package Linked_List.Utils;

public class LinkedListUtils {
    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode random;

        public ListNode(int x) {
            val = x;
            next = null;
            random = null;
        }
    }

    // Utility function to create a linked list from an array
    public static ListNode createSinglyLinkedList(int[] arr) {
        ListNode head = null;
        ListNode tail = null;
        for (int val : arr) {
            ListNode newNode = new ListNode(val);
            if (head == null) {
                head = newNode;
                tail = head;
            } else {
                tail.next = newNode;
                tail = tail.next;
            }
        }
        return head;
    }

    // Utility function to print the linked list
    public static void printSinglyLinkedList(ListNode head) {
        ListNode temp = head;
        System.out.println();
        while (temp != null) {
            System.out.print(temp.val + " --> ");
            temp = temp.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

    public static void createCycle(ListNode head, int index) {
        if (index < 0) {
            return;
        }
        ListNode tail = null;
        ListNode current = head;
        ListNode cycleNode = null;
        int count = 0;
        while (current != null) {
            if (count == index) {
                cycleNode = current;
            }
            tail = current;
            current = current.next;
            count++;
        }
        tail.next = cycleNode;
    }
}
