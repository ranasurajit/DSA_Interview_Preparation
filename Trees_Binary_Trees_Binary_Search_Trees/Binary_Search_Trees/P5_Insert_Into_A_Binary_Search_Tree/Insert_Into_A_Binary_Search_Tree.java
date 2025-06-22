package Trees_Binary_Trees_Binary_Search_Trees.Binary_Search_Trees.P5_Insert_Into_A_Binary_Search_Tree;

import java.util.Arrays;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Insert_Into_A_Binary_Search_Tree {
    public static void main(String[] args) {
        Insert_Into_A_Binary_Search_Tree solution = new Insert_Into_A_Binary_Search_Tree();

        // Input array (LeetCode-style)
        Integer[] input = { 40, 20, 60, 10, 30, 50, 70 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);
        int val = 25;

        // Run your solution function
        TreeNode modifiedRoot = solution.insertIntoBST(root, val);

        // Print result
        System.out.println("Result : " + Arrays.toString(TreeUtils.treeToArray(modifiedRoot)));
    }

    /**
     * Approach : Using Iteration + BST Property Approach
     *
     * TC: O(H)
     * SC: O(1)
     *
     * Where H = height of BST
     * H = log(N) for balanced BST
     * H = N for skewed BST
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        TreeNode current = root;
        while (true) {
            if (val < current.val) {
                // we need to lookup in the left of current node
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new TreeNode(val);
                    break;
                }
            } else if (val > current.val) {
                // we need to lookup in the right of current node
                if (current.right != null) {
                    current = current.right;
                } else {
                    current.right = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}
