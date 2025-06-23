package Trees_Binary_Trees_Binary_Search_Trees.Binary_Search_Trees.P7_Lowest_Common_Ancestor_Of_A_Binary_Search_Tree;

import java.util.Arrays;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Lowest_Common_Ancestor_Of_A_Binary_Search_Tree {
    public static void main(String[] args) {
        Lowest_Common_Ancestor_Of_A_Binary_Search_Tree solution = new Lowest_Common_Ancestor_Of_A_Binary_Search_Tree();

        // Input array (LeetCode-style)
        Integer[] input = { 6, 2, 8, 0, 4, 7, 9, null, null, 3, 5 };
        int p = 2;
        int q = 8;

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        TreeNode lcaBT = solution.lowestCommonAncestorRecursion(root, p, q);
        // Print result
        System.out.println("Result : " + Arrays.toString(TreeUtils.treeToArray(lcaBT)));

        // Run your solution function
        TreeNode lcaBST = solution.lowestCommonAncestorRecursionBST(root, p, q);
        // Print result
        System.out.println("Result : " + Arrays.toString(TreeUtils.treeToArray(lcaBST)));

        // Run your solution function
        TreeNode lcaOptimal = solution.lowestCommonAncestorOptimal(root, p, q);
        // Print result
        System.out.println("Result : " + Arrays.toString(TreeUtils.treeToArray(lcaOptimal)));
    }

    /**
     * Approach III : Using BST Property Approach
     * 
     * TC: O(H)
     * SC: O(1)
     * 
     * where H = height of BST
     * H = log(N) for balanced BST
     * H = N for skewed Tree
     */
    public TreeNode lowestCommonAncestorOptimal(TreeNode root, int p, int q) {
        // Base Case
        if (root == null) {
            return null;
        }
        TreeNode current = root;
        while (current != null) {
            if (current.val < p && current.val < q) {
                // both nodes 'p' and 'q' lie on the right side of 'current' node
                current = current.right;
            } else if (current.val > p && current.val > q) {
                // both nodes 'p' and 'q' lie on the left side of 'current' node
                current = current.left;
            } else {
                return current;
            }
        }
        return null;
    }

    /**
     * Approach II : Using Recursion + BST Property Approach
     * 
     * TC: O(H)
     * SC: O(H)
     * 
     * where H = height of BST
     * H = log(N) for balanced BST
     * H = N for skewed Tree
     */
    public TreeNode lowestCommonAncestorRecursionBST(TreeNode root, int p, int q) {
        // Base Case
        if (root == null) {
            return null;
        }
        TreeNode current = root;
        if (current.val < p && current.val < q) {
            // both nodes 'p' and 'q' lie on the right side of 'current' node
            return lowestCommonAncestorRecursionBST(root.right, p, q);
        } else if (current.val > p && current.val > q) {
            // both nodes 'p' and 'q' lie on the left side of 'current' node
            return lowestCommonAncestorRecursionBST(root.left, p, q);
        }
        return root;
    }

    /**
     * Approach I : Using Recursion Approach
     * 
     * TC: O(N)
     * SC: O(H)
     * 
     * where H = height of BST
     * H = log(N) for balanced BST
     * H = N for skewed Tree
     */
    public TreeNode lowestCommonAncestorRecursion(TreeNode root, int p, int q) {
        // Base Case
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        // Recursion Calls
        // Hypothesis
        TreeNode left = lowestCommonAncestorRecursion(root.left, p, q);
        TreeNode right = lowestCommonAncestorRecursion(root.right, p, q);
        // Induction
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
