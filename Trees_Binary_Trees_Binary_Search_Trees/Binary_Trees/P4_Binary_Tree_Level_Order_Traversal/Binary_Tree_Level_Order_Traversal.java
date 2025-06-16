package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P4_Binary_Tree_Level_Order_Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Binary_Tree_Level_Order_Traversal {
    public static void main(String[] args) {
        Binary_Tree_Level_Order_Traversal solution = new Binary_Tree_Level_Order_Traversal();
        // Input array
        Integer[] input = { 3, 9, 20, null, null, 15, 7 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        List<List<Integer>> result = solution.levelOrder(root);

        // Print result
        System.out.println("Result: " + result);
    }

    /**
     * Approach : Using BFS Traversal Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> path = new ArrayList<List<Integer>>();
        if (root == null) {
            return path;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); // SC: O(N)
        queue.offer(root);
        while (!queue.isEmpty()) { // TC: O(N)
            int size = queue.size();
            List<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                level.add(current.val);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            path.add(level);
        }
        return path;
    }
}
