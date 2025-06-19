package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P15_Max_Path_Sum_Two_Special_Nodes;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Max_Path_Sum_Two_Special_Nodes {
    public static void main(String[] args) {
        Max_Path_Sum_Two_Special_Nodes solution = new Max_Path_Sum_Two_Special_Nodes();

        // Input array
        Integer[] input = { 3, 4, 1, -10, 4 };
        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);
        // Run your solution function
        int maxSum = solution.maxPathSum(root);
        // Print result
        System.out.println("Result: " + maxSum);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    int maxPathSum(TreeNode root) {
        int[] maxSum = { Integer.MIN_VALUE };
        int value = solveRecursion(root, maxSum);
        if (root.left == null || root.right == null) {
            maxSum[0] = Math.max(maxSum[0], value);
        }
        return maxSum[0];
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int solveRecursion(TreeNode node, int[] maxSum) {
        // Base Case
        if (node == null) {
            return 0;
        }
        // Hypothesis - Recursion Calls
        int leftSum = solveRecursion(node.left, maxSum);
        int rightSum = solveRecursion(node.right, maxSum);
        // Induction
        // if we consider the current node which is non-leaf node
        if (node.left != null && node.right != null) {
            // node is not a leaf node
            maxSum[0] = Math.max(maxSum[0], node.val + leftSum + rightSum);
            // return the maximum / best result to it's parent node
            return node.val + Math.max(leftSum, rightSum);
        }
        // if the node has only one child
        return node.val + (node.left == null ? rightSum : leftSum);
    }
}
