package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P16_Same_Tree;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Same_Tree {
    public static void main(String[] args) {
        Same_Tree solution = new Same_Tree();

        // Input array
        Integer[] input1 = { 1, 2, 3 };
        Integer[] input2 = { 1, 2, 3 };
        // Convert array to tree
        TreeNode p = TreeUtils.arrayToTree(input1);
        TreeNode q = TreeUtils.arrayToTree(input2);
        // Run your solution function
        boolean isSame = solution.isSameTree(p, q);
        // Print result
        System.out.println("Result: " + isSame);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base Case
        if (p == null || q == null) {
            return p == q;
        }
        // Recursion Calls
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
