package Linked_List_Backup.P8_Convert_Sorted_List_To_Binary_Search_Tree;

import java.util.Arrays;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;
import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Convert_Sorted_List_To_Binary_Search_Tree {
    public static void main(String[] args) {
        Convert_Sorted_List_To_Binary_Search_Tree solution = new Convert_Sorted_List_To_Binary_Search_Tree();
        solution.sortedListToBST(null);

        int[] arr = { -10, -3, 0, 5, 9 };
        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        TreeNode bst = solution.sortedListToBST(head);
        Integer[] bstArray = TreeUtils.treeToArray(bst);
        System.out.println(Arrays.toString(bstArray));
    }

    /**
     * TC: O(N x log(N))
     * SC: O(log(N))
     * 
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return TreeUtils.getTreeNode(head.val);
        }
        ListNode[] middle = findMiddleNode(head); // TC: O(N)
        ListNode mid = middle[1];
        TreeNode root = TreeUtils.getTreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }

    /**
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     * 
     * @param head
     * @return
     */
    private ListNode[] findMiddleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // removing next reference of 'prev' ListNode
        if (prev != null) {
            prev.next = null;
        }
        return new ListNode[] { prev, slow };
    }
}
