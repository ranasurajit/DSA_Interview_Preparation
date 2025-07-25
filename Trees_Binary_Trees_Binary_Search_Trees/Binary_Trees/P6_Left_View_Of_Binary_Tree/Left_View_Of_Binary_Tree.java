package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P6_Left_View_Of_Binary_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Left_View_Of_Binary_Tree {
    public static void main(String[] args) {
        Left_View_Of_Binary_Tree solution = new Left_View_Of_Binary_Tree();
        // Input array
        Integer[] input = { 1, 2, 3, 4, null, null, null, 5 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        List<Integer> view = solution.leftView(root);

        // Print result
        System.out.println("Result: " + view);
    }

    /**
     * Approach : Using BFS Traversal Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    ArrayList<Integer> leftView(TreeNode root) {
        ArrayList<Integer> view = new ArrayList<Integer>();
        if (root == null) {
            return view;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); // SC: O(N)
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) { // TC: O(N)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (view.size() == level) {
                    view.add(current.val);
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            level++;
        }
        return view;
    }
}
