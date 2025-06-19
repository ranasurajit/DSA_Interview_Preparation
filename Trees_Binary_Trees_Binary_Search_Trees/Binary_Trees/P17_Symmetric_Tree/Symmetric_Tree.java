package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P17_Symmetric_Tree;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Symmetric_Tree {
    public static void main(String[] args) {
        Symmetric_Tree solution = new Symmetric_Tree();

        // Input array
        Integer[] input = { 1, 2, 2, 3, 4, 4, 3 };
        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);
        // Run your solution function
        boolean isMirror = solution.isSymmetric(root);
        // Print result
        System.out.println("Result: " + isMirror);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirrorTree(root.left, root.right);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private boolean isMirrorTree(TreeNode leftTree, TreeNode rightTree) {
        // Base Case
        if (leftTree == null || rightTree == null) {
            return leftTree == rightTree;
        }
        // Recursion Calls
        return leftTree.val == rightTree.val &&
                isMirrorTree(leftTree.left, rightTree.right) &&
                isMirrorTree(leftTree.right, rightTree.left);
    }
}
