package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P25_Count_Complete_Tree_Nodes;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Count_Complete_Tree_Nodes {
    public static void main(String[] args) {
        Count_Complete_Tree_Nodes solution = new Count_Complete_Tree_Nodes();

        // Input array (LeetCode-style)
        Integer[] input = { 1, 2, 3, 4, 5, 6 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        int totalNodes = solution.countNodes(root);

        // Print result
        System.out.println("Result : " + totalNodes);
    }

    /**
     * Approach : Using Recursion + DFS Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int countNodes(TreeNode root) {
        return solveRecursion(root);
    }

    /**
     * Using Recursion + DFS Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int solveRecursion(TreeNode root) {
        // Base Case
        if (root == null) {
            return 0;
        }
        // Hypothesis
        int leftCount = solveRecursion(root.left);
        int rightCount = solveRecursion(root.right);
        // Induction
        return 1 + leftCount + rightCount;
    }
}
