package Binary_Trees.P3_Height_Of_Binary_Tree;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Height_Of_Binary_Tree {
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 3, -1, 1, 2, -1, -1, -1 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        Height_Of_Binary_Tree solution = new Height_Of_Binary_Tree();
        int heightBT = solution.heightOfBinaryTree(root);

        // Print result
        System.out.println("Height: " + heightBT);
    }

    /**
     * Using DFS Approach
     *
     * TC: O(N)
     * SC: O(N)
     * 
     * @param root
     * @return
     */
    public int heightOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // compute height from left sub-tree
        int lh = heightOfBinaryTree(root.left);
        // compute height from right sub-tree
        int rh = heightOfBinaryTree(root.right);
        // height = max of left or right height of sub-tree + root node
        return 1 + Math.max(lh, rh);
    }
}
