package Linked_List.P22_Doubly_Linked_List_Insertion_At_Given_Position;

import Linked_List.Utils.DoublyNode;
import Linked_List.Utils.LinkedListUtils;

public class Doubly_Linked_List_Insertion_At_Given_Position {
    public static void main(String[] args) {
        Doubly_Linked_List_Insertion_At_Given_Position solution = new Doubly_Linked_List_Insertion_At_Given_Position();

        int[] arr = { 1, 2, 3, 4 };
        int p = 0;
        int x = 44;

        DoublyNode head = LinkedListUtils.createDoublyLinkedList(arr);
        LinkedListUtils.printListForward(head);

        DoublyNode newList = solution.addNode(head, p, x);
        LinkedListUtils.printListForward(newList);
    }

    /**
     * Approach : Using Linked-List Traversal Approach
     * 
     * TC: O(Min(N, P))
     * SC: O(1)
     */
    DoublyNode addNode(DoublyNode head, int p, int x) {
        DoublyNode current = head;
        int count = 0;
        while (current != null && count < p) { // TC: O(Min(N, P))
            current = current.next;
            count++;
        }
        // creating a new node
        DoublyNode newNode = new DoublyNode(x);
        // attach next pointer
        newNode.next = current.next;
        // attach previous pointer
        newNode.prev = current;
        current.next = newNode;
        return head;
    }
}
