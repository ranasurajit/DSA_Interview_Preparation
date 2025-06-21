package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P24_Children_Sum_In_A_Binary_Tree;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Children_Sum_In_A_Binary_Tree {
    public static void main(String[] args) {
        Children_Sum_In_A_Binary_Tree solution = new Children_Sum_In_A_Binary_Tree();

        // Input array (LeetCode-style)
        Integer[] input = { 1, 3, 2, 5, null, null, 9, 6, null, 7 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        int hasChildrenSumProperty = solution.isSumProperty(root);

        // Print result
        System.out.println("Result : " + hasChildrenSumProperty);
    }

    /**
     * Approach : Using Recursion Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    public int isSumProperty(TreeNode root) {
        return hasChildrenSum(root) ? 1 : 0;
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private boolean hasChildrenSum(TreeNode root) {
        // Base Case
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        int leftVal = root.left == null ? 0 : root.left.val;
        int rightVal = root.right == null ? 0 : root.right.val;
        return leftVal + rightVal == root.val &&
                hasChildrenSum(root.left) && hasChildrenSum(root.right);
    }
}
