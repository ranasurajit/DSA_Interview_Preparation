package Binary_Trees.P5_Lowest_Common_Ancestor_Of_A_Binary_Tree;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Lowest_Common_Ancestor_Of_A_Binary_Tree {
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        int p = 5;
        int q = 4;

        // Run your solution function
        Lowest_Common_Ancestor_Of_A_Binary_Tree solution = new Lowest_Common_Ancestor_Of_A_Binary_Tree();
        TreeNode lcaNode = solution.lowestCommonAncestor(root, p, q);

        // Print result
        System.out.println("Lower Common Ancestor: " + lcaNode.val);
    }

    /**
     * Using DFS Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        // Base case
        if (root == null) {
            return null;
        }
        if (root.val == p || root.val == q) {
            return root;
        }
        // compute LCA node from left sub-tree
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        // compute LCA node from left sub-tree
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
        if (leftNode != null && rightNode != null) {
            return root;
        }
        return leftNode == null ? rightNode : leftNode;
    }
}
