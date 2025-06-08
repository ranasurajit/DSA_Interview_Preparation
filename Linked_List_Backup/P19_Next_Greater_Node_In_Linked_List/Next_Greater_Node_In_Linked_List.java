package Linked_List_Backup.P19_Next_Greater_Node_In_Linked_List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import Linked_List_Backup.Utils.LinkedListUtils;
import Linked_List_Backup.Utils.LinkedListUtils.ListNode;

public class Next_Greater_Node_In_Linked_List {
    public static void main(String[] args) {
        Next_Greater_Node_In_Linked_List solution = new Next_Greater_Node_In_Linked_List();

        int[] arr = { 2, 7, 4, 3, 5 };

        ListNode head = LinkedListUtils.createSinglyLinkedList(arr);
        LinkedListUtils.printSinglyLinkedList(head);

        int[] result = solution.nextLargerNodes(head);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new int[] { 0 };
        }
        List<Integer> nums = new ArrayList<Integer>(); // SC: O(N)
        ListNode current = head;
        while (current != null) { // TC: O(N)
            nums.add(current.val);
            current = current.next;
        }
        return nextGreaterElement(nums); // TC: O(N), SC: O(N)
    }

    /**
     * Using Monotonic Stack Approach
     *
     * TC: O(N)
     * SC: O(2 x N) ~ O(N)
     */
    private int[] nextGreaterElement(List<Integer> nums) {
        int n = nums.size();
        int[] nge = new int[n]; // SC: O(N)
        Stack<Integer> st = new Stack<Integer>(); // SC: O(N)
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            while (!st.isEmpty() && st.peek() <= nums.get(i)) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? 0 : st.peek();
            st.add(nums.get(i));
        }
        return nge;
    }
}
