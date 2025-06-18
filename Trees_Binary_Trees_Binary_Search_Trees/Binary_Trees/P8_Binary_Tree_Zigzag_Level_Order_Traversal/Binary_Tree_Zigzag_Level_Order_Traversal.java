package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P8_Binary_Tree_Zigzag_Level_Order_Traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Binary_Tree_Zigzag_Level_Order_Traversal {
    public static void main(String[] args) {
        Binary_Tree_Zigzag_Level_Order_Traversal solution = new Binary_Tree_Zigzag_Level_Order_Traversal();
        // Input array
        Integer[] input = { 3, 9, 20, null, null, 15, 7 };

        // Convert array to tree
        TreeNode root1 = TreeUtils.arrayToTree(input);

        // Run your solution function
        List<List<Integer>> path1 = solution.zigzagLevelOrderBFSArrayList(root1);

        // Print result
        System.out.println("Result: " + path1);

        // Convert array to tree
        TreeNode root2 = TreeUtils.arrayToTree(input);

        // Run your solution function
        List<List<Integer>> path2 = solution.zigzagLevelOrderOptimal(root2);

        // Print result
        System.out.println("Result: " + path2);
    }

    /**
     * Approach : Using BFS Traversal Approach (Optimal Approach)
     *
     * TC: O(N)
     * SC: O(N)
     */
    public List<List<Integer>> zigzagLevelOrderOptimal(TreeNode root) {
        List<List<Integer>> path = new ArrayList<List<Integer>>();
        if (root == null) {
            return path;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); // SC: O(N)
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) { // TC: O(N)
            int size = queue.size();
            Deque<Integer> temp = new ArrayDeque<Integer>();
            for (int i = 0; i < size; i++) { // TC: O(K)
                TreeNode current = queue.poll();
                if ((level & 1) == 0) {
                    // even levels
                    temp.addLast(current.val);
                } else {
                    // odd levels
                    temp.addFirst(current.val); // TC: O(K), where K = size
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            path.add(new ArrayList<Integer>(temp));
            level++;
        }
        return path;
    }

    /**
     * Approach : Using BFS Traversal Approach
     *
     * TC: O(N + K ^ 2)
     * SC: O(N)
     */
    public List<List<Integer>> zigzagLevelOrderBFSArrayList(TreeNode root) {
        List<List<Integer>> path = new ArrayList<List<Integer>>();
        if (root == null) {
            return path;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); // SC: O(N)
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) { // TC: O(N)
            int size = queue.size();
            List<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) { // TC: O(K)
                TreeNode current = queue.poll();
                if ((level & 1) == 0) {
                    // even levels
                    temp.add(current.val);
                } else {
                    // odd levels
                    temp.add(0, current.val); // TC: O(K), where K = size
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            path.add(temp);
            level++;
        }
        return path;
    }
}
