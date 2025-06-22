package Trees_Binary_Trees_Binary_Search_Trees.Binary_Search_Trees.P4_Floor_In_BST;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Floor_In_BST {
    public static void main(String[] args) {
        Floor_In_BST solution = new Floor_In_BST();

        // Input array (LeetCode-style)
        Integer[] input = { 10, 5, 11, 4, 7, null, null, null, null, null, 8 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);
        int x = 6;

        // Run your solution function
        int floorValue = solution.floor(root, x);

        // Print result
        System.out.println("Result : " + floorValue);
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
    public int floor(TreeNode root, int x) {
        if (root == null) {
            return -1;
        }
        int[] floorVal = { Integer.MIN_VALUE };
        solveRecursion(root, x, floorVal);
        return floorVal[0] == Integer.MIN_VALUE ? -1 : floorVal[0];
    }

    /**
     * Using Recursion + BST Property Approach
     * 
     * TC: O(H)
     * SC: O(H)
     */
    private void solveRecursion(TreeNode root, int x, int[] floorVal) {
        // Base Case
        if (root == null) {
            return;
        }
        // Recursion Calls
        if (x < root.val) {
            // we need to lookup to the left of root node
            solveRecursion(root.left, x, floorVal);
        } else {
            // we need to lookup to the right of root node
            floorVal[0] = Math.max(floorVal[0], root.val);
            solveRecursion(root.right, x, floorVal);
        }
    }
}
