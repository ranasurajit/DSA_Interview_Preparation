package Binary_Trees.P2_Binary_Tree_Level_Order_Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Binary_Tree_Level_Order_Traversal {
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 3, 9, 20, null, null, 15, 7 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        Binary_Tree_Level_Order_Traversal solution = new Binary_Tree_Level_Order_Traversal();
        List<List<Integer>> traversalList = solution.levelOrder(root); // Replace `yourFunction` with your actual
                                                                       // function name

        // Print result
        System.out.println("Traversal: " + traversalList);
    }

    /**
     * Using BFS Approach
     * 
     * TC: O(N) - as all nodes will be visited once
     * SC: O(N) - using a Queue to add all the nodes
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<List<Integer>>();
        if (root == null) {
            return traversal;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> branch = new ArrayList<Integer>();
            while (n-- > 0) {
                TreeNode current = queue.poll();
                branch.add(current.val);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            traversal.add(branch);
        }
        return traversal;
    }
}
