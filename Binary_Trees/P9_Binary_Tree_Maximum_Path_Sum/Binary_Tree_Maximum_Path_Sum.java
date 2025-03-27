package Binary_Trees.P9_Binary_Tree_Maximum_Path_Sum;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Binary_Tree_Maximum_Path_Sum {
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { -10, 9, 20, null, null, 15, 7 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        Binary_Tree_Maximum_Path_Sum solution = new Binary_Tree_Maximum_Path_Sum();
        int maxSum = solution.maxPathSum(root);

        // Print result
        System.out.println(maxSum);
    }

    /**
     * Using DFS approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int maxPathSum(TreeNode root) {
        int[] maxSum = { Integer.MIN_VALUE };
        solve(root, maxSum);
        return maxSum[0];
    }

    /**
     * TC: O(N), as all nodes are visited only once
     * SC: O(N)
     * 
     * @param root
     * @param maxSum
     * @return
     */
    private int solve(TreeNode root, int[] maxSum) {
        if (root == null) {
            return 0;
        }
        int leftMaxSum = solve(root.left, maxSum);
        int rightMaxSum = solve(root.right, maxSum);
        /**
         * we may get a convergent path that turns up and down so we
         * won't be sending it back to recursion
         * dono left and right acche
         */
        int option1 = root.val + leftMaxSum + rightMaxSum;
        /**
         * we may get either leftSum or rightSum to be better
         * so we may take the max(leftSum, rightSum) and return it
         * to the recursion
         * koi ek accha
         */
        int option2 = root.val + Math.max(leftMaxSum, rightMaxSum);
        /**
         * we may get only rootSum better
         * so we may take it and return it
         * to the recursion
         * sirf root accha
         */
        int option3 = root.val;
        // set the maximum possible option to maxSum
        maxSum[0] = Math.max(maxSum[0],
                Math.max(option1, Math.max(option2, option3)));
        return Math.max(option2, option3);
    }
}
