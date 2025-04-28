package Linked_List.P25_Sort_Linked_List_Of_0_1s_2s;

import java.util.PriorityQueue;

import Linked_List.Utils.LinkedListUtils;
import Linked_List.Utils.LinkedListUtils.ListNode;

public class Sort_Linked_List_Of_0_1s_2s {
    public static void main(String[] args) {
        Sort_Linked_List_Of_0_1s_2s solution = new Sort_Linked_List_Of_0_1s_2s();

        int[] arrI = { 1, 2, 2, 1, 2, 0, 2, 2 };
        ListNode headI = LinkedListUtils.createSinglyLinkedList(arrI);
        LinkedListUtils.printSinglyLinkedList(headI);

        ListNode sortedHeadI = solution.segregateApproachI(headI);
        LinkedListUtils.printSinglyLinkedList(sortedHeadI);

        int[] arrII = { 1, 2, 2, 1, 2, 0, 2, 2 };
        ListNode headII = LinkedListUtils.createSinglyLinkedList(arrII);
        LinkedListUtils.printSinglyLinkedList(headII);

        ListNode sortedHeadII = solution.segregateApproachII(headII);
        LinkedListUtils.printSinglyLinkedList(sortedHeadII);
    }

    // Function to sort a linked list of 0s, 1s and 2s.
    /**
     * Approach II : Using Dummy Nodes and Traversal Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private ListNode segregateApproachII(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode zeroDummy = new ListNode(-1);
        ListNode oneDummy = new ListNode(-1);
        ListNode twoDummy = new ListNode(-1);
        ListNode zeroTemp = zeroDummy;
        ListNode oneTemp = oneDummy;
        ListNode twoTemp = twoDummy;

        ListNode current = head;
        while (current != null) { // TC: O(N)
            if (current.val == 0) {
                zeroTemp.next = current;
                zeroTemp = zeroTemp.next;
            } else if (current.val == 1) {
                oneTemp.next = current;
                oneTemp = oneTemp.next;
            } else {
                twoTemp.next = current;
                twoTemp = twoTemp.next;
            }
            current = current.next;
        }
        zeroTemp.next = oneTemp.val == -1 ? twoDummy.next : oneDummy.next;
        oneTemp.next = twoTemp.val == -1 ? null : twoDummy.next;
        twoTemp.next = null;
        return zeroDummy.next;
    }

    /**
     * Approach I : Using a Min-Heap and Traversal Approach
     *
     * TC: O(2 x N x log(N)) ~ O(N x log(N))
     * SC: O(N)
     */
    private ListNode segregateApproachI(ListNode head) {
        if (head == null) {
            return null;
        }
        // Creating Min-Heap to store Pair (node, index)
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((Pair p, Pair q) -> {
            if (p.node.val == q.node.val) {
                return p.index - q.index;
            }
            return p.node.val - q.node.val;
        }); // SC: O(N)
        ListNode current = head;
        int index = 0;
        while (current != null) { // TC: O(N)
            pq.offer(new Pair(current, index++)); // TC: O(log(N))
            current = current.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while (!pq.isEmpty()) { // TC: O(N)
            temp.next = pq.poll().node; // TC: O(log(N))
            temp = temp.next;
        }
        return dummy.next;
    }

    static class Pair {
        ListNode node;
        int index;

        public Pair(ListNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

}
