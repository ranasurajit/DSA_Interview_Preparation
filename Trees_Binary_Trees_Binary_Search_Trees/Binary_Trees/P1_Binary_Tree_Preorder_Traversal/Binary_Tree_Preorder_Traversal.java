package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P1_Binary_Tree_Preorder_Traversal;

import java.util.ArrayList;
import java.util.List;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Binary_Tree_Preorder_Traversal {
    public static void main(String[] args) {
        Binary_Tree_Preorder_Traversal solution = new Binary_Tree_Preorder_Traversal();
        // Input array
        Integer[] input = { 1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        List<Integer> result = solution.preorderTraversal(root);

        // Print result
        System.out.println("Result: " + result);
    }

    /**
     * Approach : Using Pre-order traversal (Node, Left, Right) Approach
     *
     * TC: O(N)
     * SC: O(H) where H = height of Binay Tree (H = log(N), but in worst case skewed
     * tree H ~ N)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> path = new ArrayList<Integer>();
        if (root == null) {
            return path;
        }
        dfsTree(root, path);
        return path;
    }

    /**
     * Using Pre-order traversal (Node, Left, Right) Approach
     *
     * TC: O(N)
     * SC: O(H) where H = height of Binay Tree (H = log(N), but in worst case skewed
     * tree H ~ N)
     */
    private void dfsTree(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        dfsTree(root.left, path);
        dfsTree(root.right, path);
    }
}
