package Linked_List.P5_Intersection_Of_Two_Linked_Lists;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Intersection_Of_Two_Linked_Lists {
    public static void main(String[] args) {
        Intersection_Of_Two_Linked_Lists solution = new Intersection_Of_Two_Linked_Lists();

        int[] arrA = { 1, 2, 5, 6, 7 };
        ListNode headA = LinkedListUtils.createSinglyLinkedList(arrA);

        int[] arrB = { 9, 8, 3, 5, 6, 7 };
        ListNode headB = LinkedListUtils.createSinglyLinkedList(arrB);

        int targetNode = 5;

        LinkedListUtils.makeIntersectionAtValue(headA, headB, targetNode);

        LinkedListUtils.printSinglyLinkedList(headA);
        LinkedListUtils.printSinglyLinkedList(headB);
        System.out.println();

        ListNode intersectionNode = solution.getIntersectionNode(headA, headB);
        System.out.println(intersectionNode.val);
    }

    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // If both heads are same
        if (headA == headB) {
            return headA;
        }
        // Setting up two pointers at the start of each head ListNode
        ListNode currentA = headA;
        ListNode currentB = headB;
        while (currentA != currentB) { // TC: O(2 x N)
            currentA = currentA.next;
            currentB = currentB.next;
            if (currentA == currentB) {
                break;
            }
            if (currentA == null) {
                currentA = headB;
            }
            if (currentB == null) {
                currentB = headA;
            }
        }
        return currentA;
    }
}
