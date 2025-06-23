package Trees_Binary_Trees_Binary_Search_Trees.Binary_Search_Trees.P12_Predecessor_And_Successor;

import java.util.ArrayList;
import java.util.Arrays;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Predecessor_And_Successor {
    public static void main(String[] args) {
        Predecessor_And_Successor solution = new Predecessor_And_Successor();

        // Input array (LeetCode-style)
        Integer[] input = { 8, 1, 9, null, 4, null, 10, 3, null, null, null };
        int key = 8;
        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);
        // Run your solution function
        ArrayList<TreeNode> list = solution.findPreSuc(root, key);
        // Print result
        int[] result = { list.get(0).val, list.get(1).val };
        System.out.println("Result : " + Arrays.toString(result));
    }

    /**
     * Approach : Using Property of BST Approach
     * 
     * TC: O(2 x H) ~ O(H)
     * SC: O(2 x H) ~ O(H)
     */
    public ArrayList<TreeNode> findPreSuc(TreeNode root, int key) {
        ArrayList<TreeNode> result = new ArrayList<TreeNode>();
        TreeNode predecessor = findPredecessor(root, key); // TC: O(H), SC: O(H)
        TreeNode successor = findSuccessor(root, key); // TC: O(H), SC: O(H)
        if (predecessor == null || predecessor.val >= key) {
            result.add(new TreeNode(-1));
        } else {
            result.add(predecessor);
        }
        if (successor == null || successor.val <= key) {
            result.add(new TreeNode(-1));
        } else {
            result.add(successor);
        }
        return result;
    }

    /**
     * Using Property of BST Approach
     * 
     * TC: O(H)
     * SC: O(H)
     */
    private TreeNode findPredecessor(TreeNode root, int key) {
        TreeNode predecessor = null;
        while (root != null) {
            if (key <= root.val) {
                root = root.left;
            } else {
                predecessor = root;
                root = root.right;
            }
        }
        return predecessor;
    }

    /**
     * Using Property of BST Approach
     * 
     * TC: O(H)
     * SC: O(H)
     */
    private TreeNode findSuccessor(TreeNode root, int key) {
        TreeNode successor = root;
        while (root != null) {
            if (key < root.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return successor;
    }
}
