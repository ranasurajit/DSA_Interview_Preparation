package Trees_Binary_Trees_Binary_Search_Trees.Binary_Search_Trees.P6_Validate_Binary_Search_Tree;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Validate_Binary_Search_Tree {
    public static void main(String[] args) {
        Validate_Binary_Search_Tree solution = new Validate_Binary_Search_Tree();

        // Input array (LeetCode-style)
        Integer[] input = { 5, 4, 6, null, null, 3, 7 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        boolean isValid = solution.isValidBST(root);

        // Print result
        System.out.println("Result : " + isValid);
    }

    /**
     * Approach : Using Recursion + BST Property Approach
     * 
     * TC: O(N)
     * SC: O(H)
     * 
     * where H = height of BST
     * H = log(N) for balanced BST
     * H = N for skewed Tree
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Using Recursion + BST Property Approach
     * 
     * TC: O(N)
     * SC: O(H)
     */
    private boolean isBST(TreeNode root, long start, long end) {
        // Base Case
        if (root == null) {
            return true;
        }
        // Range Check for BST Property
        if (root.val <= start || root.val >= end) {
            return false;
        }
        // Recursion
        return isBST(root.left, start, root.val) && isBST(root.right, root.val, end);
    }
}
