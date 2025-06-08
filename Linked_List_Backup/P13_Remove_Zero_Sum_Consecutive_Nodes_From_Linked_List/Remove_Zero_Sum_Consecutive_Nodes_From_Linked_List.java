package Linked_List_Backup.P13_Remove_Zero_Sum_Consecutive_Nodes_From_Linked_List;

import java.util.HashMap;
import java.util.Map;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Remove_Zero_Sum_Consecutive_Nodes_From_Linked_List {
    public static void main(String[] args) {
        Remove_Zero_Sum_Consecutive_Nodes_From_Linked_List solution = new Remove_Zero_Sum_Consecutive_Nodes_From_Linked_List();
        int[] arr = { 1, 2, 3, -3, -2 };

        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        ListNode resultList = solution.removeZeroSumSublists(head);
        LinkedListUtils.printSinglyLinkedList(resultList);
    }

    /**
     * Using Hashing on PrefixSum on ListNodes
     * 
     * TC: O(N ^ 2)
     * SC: O(N)
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) {
            return head;
        }
        /**
         * we need to store prefixSum so we need to start with
         * prefixSum 0, so we would need a dummy node for the
         * same
         */
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int prefixSum = 0;
        /**
         * We would need to store the ListNodes with prefixSum and
         * so we need a HashMap<Integer, ListNode>
         */
        Map<Integer, ListNode> map = new HashMap<Integer, ListNode>(); // SC: O(N)
        map.put(prefixSum, dummy);
        // traverse the nodes
        ListNode current = head;
        while (current != null) { // TC: O(N)
            prefixSum += current.val;
            if (map.containsKey(prefixSum)) {
                // we need to remove all the nodes that cancels/nullifies nodes
                ListNode start = map.get(prefixSum);
                ListNode temp = start.next;
                int pSum = prefixSum;
                while (temp != current) { // TC: O(N)
                    pSum += temp.val;
                    if (temp != current) {
                        map.remove(pSum);
                    }
                    temp = temp.next;
                }
                start.next = current.next;
            } else {
                map.put(prefixSum, current);
            }
            current = current.next;
        }
        return dummy.next;
    }
}
