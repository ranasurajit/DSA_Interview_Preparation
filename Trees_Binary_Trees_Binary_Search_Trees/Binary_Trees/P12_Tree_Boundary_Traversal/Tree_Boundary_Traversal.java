package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P12_Tree_Boundary_Traversal;

import java.util.ArrayList;
import java.util.Stack;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Tree_Boundary_Traversal {
    public static void main(String[] args) {
        Tree_Boundary_Traversal solution = new Tree_Boundary_Traversal();
        // Input array
        Integer[] input = { 1, 2, 3, 4, 5, 6, 7, null, null, 8, 9, null, null, null, null };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        ArrayList<Integer> boundaryPath = solution.boundaryTraversal(root);

        // Print result
        System.out.println("Result: " + boundaryPath);
    }

    /**
     * Approach : Using DFS, Pre-order Traversal and Stacks Approach
     * 
     * TC: O(N + 2 x log(N)) ~ O(N)
     * SC: O(3 x log(N)) ~ O(log(N))
     */
    ArrayList<Integer> boundaryTraversal(TreeNode node) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        if (node == null) {
            return path;
        }
        path.add(node.val);
        if (isLeafNode(node)) {
            return path;
        }
        printLeftBoundary(node, path); // TC: O(log(N)), SC: O(log(N))
        printLeafNodes(node, path); // TC: O(N), SC: O(log(N))
        printRightBoundary(node, path); // TC: O(log(N)), SC: O(log(N))
        return path;
    }

    /**
     * As we are travelling through height nodes
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private void printLeftBoundary(TreeNode node, ArrayList<Integer> path) {
        TreeNode current = node.left;
        while (current != null) {
            if (isLeafNode(current)) {
                break;
            }
            path.add(current.val);
            if (current.left != null) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }

    /**
     * Using Pre-order Traversal
     * 
     * As we are travelling all nodes
     * 
     * TC: O(N)
     * SC: O(log(N))
     */
    private void printLeafNodes(TreeNode node, ArrayList<Integer> path) {
        if (node == null) {
            return;
        }
        if (isLeafNode(node)) {
            path.add(node.val);
        }
        printLeafNodes(node.left, path);
        printLeafNodes(node.right, path);
    }

    /**
     * As we are travelling through height nodes
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private void printRightBoundary(TreeNode node, ArrayList<Integer> path) {
        Stack<Integer> st = new Stack<Integer>();
        TreeNode current = node.right;
        while (current != null) {
            if (isLeafNode(current)) {
                break;
            }
            st.push(current.val);
            if (current.right != null) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        while (!st.isEmpty()) {
            path.add(st.pop());
        }
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isLeafNode(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}
