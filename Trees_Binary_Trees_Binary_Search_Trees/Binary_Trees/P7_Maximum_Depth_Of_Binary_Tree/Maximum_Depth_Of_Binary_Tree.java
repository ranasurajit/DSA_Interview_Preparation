package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P7_Maximum_Depth_Of_Binary_Tree;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Maximum_Depth_Of_Binary_Tree {
    public static void main(String[] args) {
        Maximum_Depth_Of_Binary_Tree solution = new Maximum_Depth_Of_Binary_Tree();
        // Input array
        Integer[] input = { 1, 2, 3, 4, null, null, null, 5 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        int depth = solution.maxDepth(root);

        // Print result
        System.out.println("Result: " + depth);
    }

    /**
     * Approach : Using DFS Traversal Approach
     *
     * TC: O(N)
     * SC: O(H) where H = height of Binay Tree (H = log(N), but in worst case skewed
     * tree H ~ N)
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
