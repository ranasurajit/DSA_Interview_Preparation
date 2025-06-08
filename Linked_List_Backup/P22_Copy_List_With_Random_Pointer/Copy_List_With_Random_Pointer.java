package Linked_List_Backup.P22_Copy_List_With_Random_Pointer;

import java.util.HashMap;
import java.util.Map;

import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Copy_List_With_Random_Pointer {
    public static void main(String[] args) {

    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public ListNode copyRandomList(ListNode head) {
        if (head == null) {
            return null;
        }
        // store the node references of head and newHead
        Map<ListNode, ListNode> map = new HashMap<ListNode, ListNode>(); // SC: O(N)
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        ListNode current = head;
        // traversing to copy only next nodes
        while (current != null) { // TC: O(N)
            temp.next = new ListNode(current.val);
            map.put(current, temp.next);
            // moving both the pointers
            temp = temp.next;
            current = current.next;
        }
        // setting random pointers
        current = head;
        temp = dummy.next;
        while (current != null) { // TC: O(N)
            // setting the random pointers from references from HashMap
            ListNode random = current.random;
            temp.random = map.get(random);
            temp = temp.next;
            current = current.next;
        }
        return dummy.next;
    }
}
