package Binary_Trees.P4_Diameter_Of_Binary_Tree;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Diameter_Of_Binary_Tree {
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 1, 2, 3, 4, 5 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        Diameter_Of_Binary_Tree solution = new Diameter_Of_Binary_Tree();
        int diameterBT = solution.diameterOfBinaryTree(root);

        // Print result
        System.out.println("Diameter: " + diameterBT);
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
    public int diameterOfBinaryTree(TreeNode root) {
        TreePair diameterBT = getDiameter(root);
        return diameterBT.diameter;
    }

    /**
     * TC: O(N)
     * SC: O(N)
     * 
     * @param root
     * @return
     */
    private TreePair getDiameter(TreeNode root) {
        if (root == null) {
            return new TreePair(0, 0);
        }
        // compute TreePair from left sub-tree
        TreePair leftTreePair = getDiameter(root.left);
        // compute TreePair from right sub-tree
        TreePair rightTreePair = getDiameter(root.right);

        int diameterLeft = leftTreePair.diameter;
        int diameterRight = rightTreePair.diameter;
        // another diameter can be found as sum of heights from left and right sub-trees
        int diameterComputed = leftTreePair.height + rightTreePair.height;

        // calculate max-diameter
        int maxDiameter = Math.max(diameterComputed, Math.max(diameterLeft, diameterRight));
        // calculate max-height
        int maxHeight = 1 + Math.max(leftTreePair.height, rightTreePair.height);
        return new TreePair(maxDiameter, maxHeight);
    }

    static class TreePair {
        int diameter;
        int height;

        public TreePair(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }
}
