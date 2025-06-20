package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P20_Lowest_Common_Ancestor_Of_A_Binary_Tree;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Lowest_Common_Ancestor_Of_A_Binary_Tree {
    public static void main(String[] args) {
        Lowest_Common_Ancestor_Of_A_Binary_Tree solution = new Lowest_Common_Ancestor_Of_A_Binary_Tree();

        // Input array (LeetCode-style)
        Integer[] input = { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        int p = 5;
        int q = 4;

        // Run your solution function
        TreeNode lcaNode = solution.lowestCommonAncestor(root, p, q);

        // Print result
        System.out.println("Lower Common Ancestor: " + lcaNode.val);
    }

    /**
     * Approach : Using DFS Traversal Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        // Base Case
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        // Hypothesis
        TreeNode leftTree = lowestCommonAncestor(root.left, p, q);
        TreeNode rightTree = lowestCommonAncestor(root.right, p, q);
        // Induction
        if (leftTree == null) {
            // not found in left sub-tree so possibly both are in right sub-tree
            return rightTree;
        }
        if (rightTree == null) {
            // not found in right sub-tree so possibly both are in left sub-tree
            return leftTree;
        }
        return root;
    }
}
