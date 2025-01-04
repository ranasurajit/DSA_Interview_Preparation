package Binary_Trees.P1_Binary_Tree_Right_Side_View;

import java.util.*;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Binary_Tree_Right_Side_View_BFS {
    /**
     * Driver code: Example to test your solution locally.
     */
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 1, 2, 3, null, 5, null, 4 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        Binary_Tree_Right_Side_View_BFS solution = new Binary_Tree_Right_Side_View_BFS();
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
     * TC: O(N)
     * SC: O(N)
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> view = new ArrayList<Integer>();
        if (root == null) {
            return view;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode node = null;
            while (size > 0) {
                node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            view.add(node.val);
        }
        return view;
    }
}
