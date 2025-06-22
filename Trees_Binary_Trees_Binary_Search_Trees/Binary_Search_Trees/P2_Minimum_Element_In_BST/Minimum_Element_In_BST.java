package Trees_Binary_Trees_Binary_Search_Trees.Binary_Search_Trees.P2_Minimum_Element_In_BST;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Minimum_Element_In_BST {
    public static void main(String[] args) {
        Minimum_Element_In_BST solution = new Minimum_Element_In_BST();

        // Input array (LeetCode-style)
        Integer[] input = { 5, 4, 6, 3, null, null, 7, 1 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        int minimumValue = solution.minValue(root);

        // Print result
        System.out.println("Result : " + minimumValue);
    }

    /**
     * Approach : Using Property of Binary Search Tree
     * 
     * TC: O(H)
     * SC: O(1)
     * 
     * where H = height of BST
     * H = log(N) for balanced BST
     * H = N for skewed Tree
     */
    int minValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.val;
    }
}
