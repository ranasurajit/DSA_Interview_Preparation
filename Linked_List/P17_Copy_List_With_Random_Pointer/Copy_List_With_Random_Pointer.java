package Linked_List.P17_Copy_List_With_Random_Pointer;

import java.util.HashMap;
import java.util.Map;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.Node;

public class Copy_List_With_Random_Pointer {
    public static void main(String[] args) {
        Copy_List_With_Random_Pointer solution = new Copy_List_With_Random_Pointer();

        int[][] data1 = {
                { 7, -1 },
                { 13, 0 },
                { 11, 4 },
                { 10, 2 },
                { 1, 0 }
        };
        Node head1 = LinkedListUtils.buildLinkedList(data1);
        LinkedListUtils.printListWithRandomPointers(head1);

        Node copiedHead1 = solution.copyRandomListUsingHashing(head1);
        LinkedListUtils.printListWithRandomPointers(copiedHead1);

        int[][] data2 = {
                { 3, -1 },
                { 3, 0 },
                { 3, -1 }
        };
        Node head2 = LinkedListUtils.buildLinkedList(data2);
        LinkedListUtils.printListWithRandomPointers(head2);

        Node copiedHead2 = solution.copyRandomListUsingPointersOptimal(head2);
        LinkedListUtils.printListWithRandomPointers(copiedHead2);
    }

    /**
     * Approach II : Using Pointer Linking Approach (Without Extra Space)
     *
     * TC: O(3 x N) ~ O(N)
     * SC: O(1)
     */
    public Node copyRandomListUsingPointersOptimal(Node head) {
        if (head == null) {
            return head;
        }
        // creating criss-cross linking with duplicate nodes to ease copying random
        // pointers
        Node current = head;
        while (current != null) { // TC: O(N)
            Node temp = current.next;
            current.next = new Node(current.val);
            current.next.next = temp;
            current = current.next.next;
        }
        // copying random pointers
        current = head;
        while (current != null && current.next != null) { // TC: O(N)
            current.next.random = current.random != null ? current.random.next : null;
            current = current.next.next;
        }
        // retaining the original and copied lists and removing criss-cross
        // linkages/adding next pointers
        Node original = head;
        Node copied = head.next;
        Node copiedHead = copied;
        while (original != null && copied != null) { // TC: O(N)
            original.next = original.next != null ? original.next.next : null;
            copied.next = copied.next != null ? copied.next.next : null;
            original = original.next;
            copied = copied.next;
        }
        return copiedHead;
    }

    /**
     * Approach I : Using Hashing Approach (Uses Extra Space)
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public Node copyRandomListUsingHashing(Node head) {
        if (head == null) {
            return head;
        }
        Map<Node, Node> map = new HashMap<Node, Node>(); // SC: O(N)
        Node current = head;
        // creating a HashMap to create related copies of actual nodes mapped to it
        while (current != null) { // TC: O(N)
            map.put(current, new Node(current.val));
            current = current.next;
        }
        // copying next and random pointers in the below loop
        current = head;
        while (current != null) { // TC: O(N)
            Node clonedNode = map.get(current);
            // copy the next nodes
            clonedNode.next = map.get(current.next);
            // copy the random pointers
            clonedNode.random = map.get(current.random);
            current = current.next;
        }
        return map.get(head);
    }
}
