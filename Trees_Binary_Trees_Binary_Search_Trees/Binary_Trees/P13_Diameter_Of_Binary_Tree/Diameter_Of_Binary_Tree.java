package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P13_Diameter_Of_Binary_Tree;

import java.util.HashMap;
import java.util.Map;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Diameter_Of_Binary_Tree {
    public static void main(String[] args) {
        Diameter_Of_Binary_Tree solution = new Diameter_Of_Binary_Tree();

        // Input array
        Integer[] input1 = { 1, 2, 3, 4, 5, 6, 7, null, null, 8, 9, null, null, null, null };
        // Convert array to tree
        TreeNode root1 = TreeUtils.arrayToTree(input1);
        // Run your solution function
        int diameter1 = solution.diameterOfBinaryTreeRecursion(root1);
        // Print result
        System.out.println("Result: " + diameter1);

        // Input array
        Integer[] input2 = { 1, 2, 3, 4, 5 };
        // Convert array to tree
        TreeNode root2 = TreeUtils.arrayToTree(input2);
        // Run your solution function
        int diameter2 = solution.diameterOfBinaryTreeMemoization(root2);
        // Print result
        System.out.println("Result: " + diameter2);
    }

    /**
     * Approach II : Using Recursion + Memoization Approach
     *
     * (DP Not needed here as each node is visited once)
     * 
     * DP helps only if:
     * You're recomputing results for overlapping subproblems.
     * You're solving a top-down problem with heavy subtree reuse.
     *
     * TC: O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public int diameterOfBinaryTreeMemoization(TreeNode root) {
        int[] diameter = { 0 }; // need to pass it as pass by reference
        Map<TreeNode, Integer> memo = new HashMap<TreeNode, Integer>(); // SC: O(N)
        solveMemoization(root, diameter, memo); // TC: O(N), SC: O(N)
        return diameter[0];
    }

    /**
     * Using Recursion + Memoization Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int solveMemoization(TreeNode node, int[] diameter, Map<TreeNode, Integer> memo) {
        // Base Case
        if (node == null) {
            return 0;
        }
        // Memoization Check
        if (memo.containsKey(node)) {
            return memo.get(node);
        }
        // Hypothesis - Recursion Calls will do the magic
        int leftHeight = solveMemoization(node.left, diameter, memo);
        int rightHeight = solveMemoization(node.right, diameter, memo);
        // Induction
        // if diameter passes through this node 'node'
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);
        // if diameter does not pass through this node 'node' then return the best
        // answer to its parent
        int nodeHeight = 1 + Math.max(leftHeight, rightHeight);
        memo.put(node, nodeHeight);
        return nodeHeight;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int diameterOfBinaryTreeRecursion(TreeNode root) {
        int[] diameter = { 0 }; // need to pass it as pass by reference
        solveRecursion(root, diameter);
        return diameter[0];
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int solveRecursion(TreeNode node, int[] diameter) {
        // Base Case
        if (node == null) {
            return 0;
        }
        // Hypothesis - Recursion Calls will do the magic
        int leftHeight = solveRecursion(node.left, diameter);
        int rightHeight = solveRecursion(node.right, diameter);
        // Induction
        // if diameter passes through this node 'node'
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);
        // if diameter does not pass through this node 'node' then return the best
        // answer to its parent
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
