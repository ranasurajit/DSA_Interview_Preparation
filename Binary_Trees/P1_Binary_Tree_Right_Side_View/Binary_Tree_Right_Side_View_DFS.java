package Binary_Trees.P1_Binary_Tree_Right_Side_View;

import java.util.ArrayList;
import java.util.List;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Binary_Tree_Right_Side_View_DFS {
    /**
     * Driver code: Example to test your solution locally.
     */
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 1, 2, 3, null, 5, null, 4 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        Binary_Tree_Right_Side_View_DFS solution = new Binary_Tree_Right_Side_View_DFS();
        List<Integer> result = solution.rightSideView(root); // Replace `yourFunction` with your actual function name

        // Print result
        System.out.println("Result: " + result);

        // Optional: Convert tree back to array for debugging
        // Integer[] output = treeToArray(root);
        // System.out.println("Tree as Array: " + Arrays.toString(output));
    }

    /**
     * Using BFS algorithm
     *
     * TC: O(H) ~ O(log(N), in case of skewed Tree, O(N)
     * SC: O(H) ~ O(log(N), in case of skewed Tree, O(N)
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> view = new ArrayList<Integer>();
        if (root == null) {
            return view;
        }
        dfsTree(root, 0, view);
        return view;
    }

    private void dfsTree(TreeNode node, int level, List<Integer> view) {
        if (node == null) {
            return;
        }
        if (view.size() == level) {
            view.add(node.val);
        }
        dfsTree(node.right, level + 1, view);
        dfsTree(node.left, level + 1, view);
    }
}
