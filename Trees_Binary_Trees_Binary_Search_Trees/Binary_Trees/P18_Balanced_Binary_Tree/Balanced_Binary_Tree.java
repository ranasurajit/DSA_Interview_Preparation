package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P18_Balanced_Binary_Tree;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Balanced_Binary_Tree {
    public static void main(String[] args) {
        Balanced_Binary_Tree solution = new Balanced_Binary_Tree();

        // Input array
        Integer[] input1 = { 3, 9, 20, null, null, 15, 7 };
        // Convert array to tree
        TreeNode root1 = TreeUtils.arrayToTree(input1);
        // Run your solution function
        boolean isBalancedTree1 = solution.isBalanced(root1);
        // Print result
        System.out.println("Result: " + isBalancedTree1);

        // Input array
        Integer[] input2 = { 1, 2, 2, 3, 3, null, null, 4, 4 };
        // Convert array to tree
        TreeNode root2 = TreeUtils.arrayToTree(input2);
        // Run your solution function
        boolean isBalancedTree2 = solution.isBalanced(root2);
        // Print result
        System.out.println("Result: " + isBalancedTree2);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public boolean isBalanced(TreeNode root) {
        if (dfsTreeRecursion(root) == -1) {
            return false;
        }
        return true;
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int dfsTreeRecursion(TreeNode node) {
        // Base Case
        if (node == null) {
            return 0;
        }
        // Hypothesis - Recursion Leap of Faith
        int leftHeight = dfsTreeRecursion(node.left);
        int rightHeight = dfsTreeRecursion(node.right);
        // Induction
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        // passing the best result to its parent
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
