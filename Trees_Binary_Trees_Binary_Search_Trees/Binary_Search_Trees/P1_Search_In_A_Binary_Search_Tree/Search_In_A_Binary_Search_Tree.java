package Trees_Binary_Trees_Binary_Search_Trees.Binary_Search_Trees.P1_Search_In_A_Binary_Search_Tree;

import java.util.Arrays;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Search_In_A_Binary_Search_Tree {
    public static void main(String[] args) {
        Search_In_A_Binary_Search_Tree solution = new Search_In_A_Binary_Search_Tree();

        // Input array (LeetCode-style)
        Integer[] input = { 4, 2, 7, 1, 3 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);
        int val = 2;

        // Run your solution function
        TreeNode searchedNode = solution.searchBST(root, val);
        // Print result
        System.out.println("Result : " + Arrays.toString(TreeUtils.treeToArray(searchedNode)));
    }

    /**
     * Approach : Using property of Binary Search Tree
     *
     * TC: O(log(N))
     * SC: O(log(N))
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }
}
