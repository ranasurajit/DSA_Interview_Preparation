package Binary_Trees.P8_Boundary_Traversal_Of_Binary_Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Boundary_Traversal_Of_Binary_Tree {
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 1, 2, 3, 4, 5, 6, 7, null, null, 8, 9, null, null, null, null };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        Boundary_Traversal_Of_Binary_Tree solution = new Boundary_Traversal_Of_Binary_Tree();
        List<Integer> path = solution.traverseBoundary(root);

        // Print result
        System.out.println(path);
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(N + 2 x log(N)) ~ O(N + log(N))
     * SC: O(N + 3 x log(N)) ~ O(N + log(N))
     * 
     * @param root
     * @return
     */
    public List<Integer> traverseBoundary(TreeNode root) {
        List<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        if (!isLeafNode(root)) {
            // get left boundary
            leftBoundary(root, path); // TC: O(log(N)), SC: O(log(N))
            // get leaf nodes boundary
            leafBoundary(root, path); // TC: O(N), SC: O(N)
            // get right boundary
            rightBoundary(root, path); // TC: O(log(N)), SC: O(2 x log(N))
        }
        return path;
    }

    /**
     * TC: O(log(N)), as only left side nodes are traversed
     * SC: O(log(N))
     * 
     * @param root
     * @param path
     */
    private void leftBoundary(TreeNode root, List<Integer> path) {
        TreeNode leftNode = root.left;
        while (leftNode != null) {
            if (isLeafNode(leftNode)) {
                break;
            }
            path.add(leftNode.val);
            if (leftNode.left != null) {
                leftNode = leftNode.left;
            } else {
                leftNode = leftNode.right;
            }
        }
    }

    /**
     * TC: O(log(N)), as only right side nodes are traversed
     * SC: O(2 x log(N))
     * 
     * @param root
     * @param path
     */
    private void rightBoundary(TreeNode root, List<Integer> path) {
        TreeNode rightNode = root.right;
        // needed as we need to print in reverse order
        Stack<Integer> st = new Stack<Integer>(); // SC: O(log(N))
        while (rightNode != null) {
            if (isLeafNode(rightNode)) {
                break;
            }
            st.push(rightNode.val);
            if (rightNode.right != null) {
                rightNode = rightNode.right;
            } else {
                rightNode = rightNode.left;
            }
        }
        while (!st.isEmpty()) {
            path.add(st.pop());
        }
    }

    /**
     * TC: O(N), need to traverse all nodes
     * SC: O(N)
     * 
     * @param root
     * @param path
     */
    private void leafBoundary(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }
        if (isLeafNode(root)) {
            path.add(root.val);
        }
        leafBoundary(root.left, path);
        leafBoundary(root.right, path);
    }

    /**
     * TC: O(1)
     * SC: O(1)
     * 
     * @param root
     * @return
     */
    private boolean isLeafNode(TreeNode root) {
        return root != null && root.left == null && root.right == null;
    }
}
