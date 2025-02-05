package Binary_Trees.P6_Invert_Binary_Tree;

import java.util.Arrays;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Invert_Binary_Tree {
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 4, 2, 7, 1, 3, 6, 9 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        Invert_Binary_Tree solution = new Invert_Binary_Tree();
        TreeNode invertedTree = solution.invertTree(root);

        // Print result
        System.out.println("Inverted Tree: " + Arrays.toString(TreeUtils.treeToArray(invertedTree)));
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // swap left and right children nodes for current node
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        // perform DFS operation on left and right sub-trees
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
