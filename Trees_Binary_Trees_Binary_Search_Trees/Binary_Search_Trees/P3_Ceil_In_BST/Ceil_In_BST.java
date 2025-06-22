package Trees_Binary_Trees_Binary_Search_Trees.Binary_Search_Trees.P3_Ceil_In_BST;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Ceil_In_BST {
    public static void main(String[] args) {
        Ceil_In_BST solution = new Ceil_In_BST();

        // Input array (LeetCode-style)
        Integer[] input = { 10, 5, 11, 4, 7, null, null, null, null, null, 8 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);
        int x = 6;

        // Run your solution function
        int ceilingValue = solution.findCeil(root, x);

        // Print result
        System.out.println("Result : " + ceilingValue);
    }

    /**
     * Approach : Using Recursion + BST Property Approach
     * 
     * TC: O(H)
     * SC: O(H)
     * 
     * where H = height of BST
     * H = log(N) for balanced BST
     * H = N for skewed Tree
     */
    int findCeil(TreeNode root, int key) {
        if (root == null) {
            return -1;
        }
        int[] ceilVal = { Integer.MAX_VALUE };
        solveRecursion(root, key, ceilVal); // TC: O(H), SC: O(H)
        return ceilVal[0] == Integer.MAX_VALUE ? -1 : ceilVal[0];
    }

    /**
     * Using Recursion + BST Property Approach
     * 
     * TC: O(H)
     * SC: O(H)
     */
    private void solveRecursion(TreeNode root, int key, int[] ceilVal) {
        // Base Case
        if (root == null) {
            return;
        }
        // Recursion Calls
        if (key <= root.val) {
            ceilVal[0] = Math.min(ceilVal[0], root.val);
            solveRecursion(root.left, key, ceilVal);
        } else {
            solveRecursion(root.right, key, ceilVal);
        }
    }
}
