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

    /**
     * This method takes two linked lists and makes them intersect at the first node
     * in listA with the given value.
     */
    public static void makeIntersectionAtValue(ListNode headA, ListNode headB, int valToIntersectAt) {
        if (headA == null || headB == null)
            return;

        // Step 1: Find the node in listA with the value `valToIntersectAt`
        ListNode target = headA;
        while (target != null && target.val != valToIntersectAt) {
            target = target.next;
        }

        if (target == null) {
            System.out.println("Value " + valToIntersectAt + " not found in List A.");
            return;
        }

        // Step 2: Find the last node in listB before intersection should happen
        ListNode curr = headB;
        while (curr != null && curr.next != null) {
            curr = curr.next;
        }

        // Step 3: Point the last node of B (currently with a different 7) to target
        // But to ensure intersection starts from 5, we want to truncate B at 8 and
        // point it to A's 5
        ListNode prev = null;
        curr = headB;
        while (curr != null && curr.val != valToIntersectAt) {
            prev = curr;
            curr = curr.next;
        }

        if (prev != null) {
            prev.next = target; // Connect B's last unique node (8) to A's 5
        }
    }
}
