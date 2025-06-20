package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P22_Construct_Binary_Tree_From_Preorder_And_Inorder_Traversal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Construct_Binary_Tree_From_Preorder_And_Inorder_Traversal {
    public static void main(String[] args) {
        Construct_Binary_Tree_From_Preorder_And_Inorder_Traversal solution = new Construct_Binary_Tree_From_Preorder_And_Inorder_Traversal();

        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };

        TreeNode root = solution.buildTree(preorder, inorder);
        System.out.println(Arrays.toString(TreeUtils.treeToArray(root)));
    }

    /**
     * Approach : Using Recursion Approach
     *
     * Intuition: pre-order guarantees that root node is at the beginning
     * in-order guarantees the left and right sub-tree positions
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        // we will be storing inorder traversal array in HashMap to figure out index in
        // O(1) complexity
        Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            inorderMap.put(inorder[i], i);
        }
        int[] index = { 0 };
        return solveRecursion(preorder, index, 0, n - 1, inorderMap); // TC: O(N), SC: O(N)
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private TreeNode solveRecursion(int[] preorder, int[] index, int start, int end,
            Map<Integer, Integer> inorderMap) {
        // Base Case
        if (index[0] == preorder.length) {
            return null;
        }
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[index[0]]);
        int idx = inorderMap.get(preorder[index[0]]);
        index[0]++;
        root.left = solveRecursion(preorder, index, start, idx - 1, inorderMap);
        root.right = solveRecursion(preorder, index, idx + 1, end, inorderMap);
        return root;
    }
}
