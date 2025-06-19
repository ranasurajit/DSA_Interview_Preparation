package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P14_Binary_Tree_Maximum_Path_Sum;

import java.util.HashMap;
import java.util.Map;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Binary_Tree_Maximum_Path_Sum {
    public static void main(String[] args) {
        Binary_Tree_Maximum_Path_Sum solution = new Binary_Tree_Maximum_Path_Sum();

        // Input array
        Integer[] input1 = { -10, 9, 20, null, null, 15, 7 };
        // Convert array to tree
        TreeNode root1 = TreeUtils.arrayToTree(input1);
        // Run your solution function
        int maxSum1 = solution.maxPathSumRecursion(root1);
        // Print result
        System.out.println("Result: " + maxSum1);

        // Input array
        Integer[] input2 = { 1, 2, 3 };
        // Convert array to tree
        TreeNode root2 = TreeUtils.arrayToTree(input2);
        // Run your solution function
        int maxSum2 = solution.maxPathSumMemoization(root2);
        // Print result
        System.out.println("Result: " + maxSum2);
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
    public int maxPathSumMemoization(TreeNode root) {
        int[] maxSum = { Integer.MIN_VALUE };
        Map<TreeNode, Integer> memo = new HashMap<TreeNode, Integer>();
        solveMemoization(root, maxSum, memo);
        return maxSum[0];
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private int solveMemoization(TreeNode node, int[] maxSum, Map<TreeNode, Integer> memo) {
        // Base Case
        if (node == null) {
            return 0;
        }
        // Memoization Calls
        if (memo.containsKey(node)) {
            return memo.get(node);
        }
        // Hypothesis - Recursion Leap of Faith
        int leftSum = solveMemoization(node.left, maxSum, memo);
        int rightSum = solveMemoization(node.right, maxSum, memo);
        // Induction
        // compute maxSum assuming if it passed through node else pass the best value to
        // its parent
        // check if leftSum or rightSum < 0 then do not contribute
        if (leftSum < 0) {
            leftSum = 0;
        }
        if (rightSum < 0) {
            rightSum = 0;
        }
        maxSum[0] = Math.max(maxSum[0], leftSum + rightSum + node.val);
        // return the best value to its parent
        int currentSum = node.val + Math.max(0, Math.max(leftSum, rightSum));
        memo.put(node, currentSum);
        return currentSum;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int maxPathSumRecursion(TreeNode root) {
        int[] maxSum = { Integer.MIN_VALUE };
        solveRecursion(root, maxSum);
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
        // Hypothesis - Recursion Leap of Faith
        int leftSum = solveRecursion(node.left, maxSum);
        int rightSum = solveRecursion(node.right, maxSum);
        // Induction
        // compute maxSum assuming if it passed through node else pass the best value to
        // its parent
        // check if leftSum or rightSum < 0 then do not contribute
        if (leftSum < 0) {
            leftSum = 0;
        }
        if (rightSum < 0) {
            rightSum = 0;
        }
        maxSum[0] = Math.max(maxSum[0], leftSum + rightSum + node.val);
        // return the best value to its parent
        return node.val + Math.max(0, Math.max(leftSum, rightSum));
    }
}
