package Binary_Trees.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtils {
    // Definition for a binary tree node.
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Utility: Converts an array into a binary tree (LeetCode-style).
     */
    public static TreeNode arrayToTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();

            // Set left child
            if (i < arr.length && arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                queue.add(current.left);
            }
            i++;

            // Set right child
            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    /**
     * Utility: Converts a binary tree back to an array (for debugging).
     */
    public static Integer[] treeToArray(TreeNode root) {
        if (root == null) {
            return new Integer[] {};
        }

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null) {
                result.add(current.val);
                queue.add(current.left);
                queue.add(current.right);
            } else {
                result.add(null);
            }
        }

        // Remove trailing nulls
        while (result.size() > 0 && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }

        return result.toArray(new Integer[0]);
    }

    public static TreeNode getTreeNode() {
        return new TreeNode();
    }

    public static TreeNode getTreeNode(int val) {
        return new TreeNode(val);
    }

    public static TreeNode getTreeNode(int val, TreeNode left, TreeNode right) {
        return new TreeNode(val, left, right);
    }
}
