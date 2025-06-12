package Linked_List.Utils;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * Creates a linked list from an array of [val, random_index] pairs.
     * 
     * @param data 2D int array where each entry is [val, random_index] or
     *             random_index == -1 if null
     * @return Head of the constructed linked list
     */
    public static Node buildLinkedList(int[][] data) {
        if (data == null || data.length == 0)
            return null;

        // Step 1: Create all nodes
        Node[] nodes = new Node[data.length];
        for (int i = 0; i < data.length; i++) {
            nodes[i] = new Node(data[i][0]);
        }

        // Step 2: Set next pointers
        for (int i = 0; i < data.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        // Step 3: Set random pointers
        for (int i = 0; i < data.length; i++) {
            int randIndex = data[i][1];
            nodes[i].random = (randIndex == -1) ? null : nodes[randIndex];
        }

        // Return the head node
        return nodes[0];
    }

    /**
     * Utility to print the list for debugging purposes.
     */
    public static void printListWithRandomPointers(Node head) {
        Node curr = head;
        int index = 0;
        Map<Node, Integer> nodeToIndex = new HashMap<>();
        int i = 0;
        for (Node temp = head; temp != null; temp = temp.next) {
            nodeToIndex.put(temp, i++);
        }

        while (curr != null) {
            String randomVal = (curr.random == null) ? "null" : String.valueOf(nodeToIndex.get(curr.random));
            System.out.println("Node[" + index + "]: val=" + curr.val + ", randomIndex=" + randomVal);
            curr = curr.next;
            index++;
        }
        System.out.println();
    }
}
