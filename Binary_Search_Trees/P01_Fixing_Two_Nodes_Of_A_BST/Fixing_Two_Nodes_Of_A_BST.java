package Binary_Search_Trees.P01_Fixing_Two_Nodes_Of_A_BST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Fixing_Two_Nodes_Of_A_BST {

    private TreeNode prev;
    private TreeNode first;
    private TreeNode second;
    private TreeNode temp;

    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input1 = { 10, 5, 8, 2, 20 };

        // Convert array to tree
        TreeNode root1 = TreeUtils.arrayToTree(input1);

        // Run your solution function
        Fixing_Two_Nodes_Of_A_BST solution = new Fixing_Two_Nodes_Of_A_BST();
        solution.correctBSTBruteForce(root1); // Replace `yourFunction` with your actual function name

        // Print result
        System.out.println("Result: " + Arrays.toString(TreeUtils.treeToArray(root1)));

        // Input array (LeetCode-style)
        Integer[] input2 = { 10, 5, 8, 2, 20 };

        // Convert array to tree
        TreeNode root2 = TreeUtils.arrayToTree(input2);

        solution.correctBSTOptimal(root2); // Replace `yourFunction` with your actual function name

        // Print result
        System.out.println("Result: " + Arrays.toString(TreeUtils.treeToArray(root2)));
    }

    /**
     * Optimal Approach: Using In-order Traversal
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param root
     */
    private void correctBSTOptimal(TreeNode root) {
        inorderDFS(root); // TC: O(N), SC: O(N)
        if (second == null) {
            // case 1 where two adjacent nodes are incorrect
            swapNodeValues(first, temp);
        } else {
            // case 2 where two nodes other than adjacent are incorrect
            swapNodeValues(first, second);
        }
    }

    /**
     * TC: O(1)
     * SC: O(1)
     * 
     * @param node1
     * @param node2
     */
    private void swapNodeValues(TreeNode node1, TreeNode node2) {
        int tempNodeValue = node2.val;
        node2.val = node1.val;
        node1.val = tempNodeValue;
    }

    /**
     * TC: O(N)
     * SC: O(N)
     * 
     * @param root
     */
    private void inorderDFS(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderDFS(root.left);
        // check if there is a problem with ordering
        if (prev != null && root.val < prev.val) {
            if (first == null) {
                // capture the 1st node and adjacent node which is incorrect
                first = prev;
                temp = root;
            } else {
                // capture the 2nd node which is incorrect
                second = root;
            }
        }
        prev = root;
        inorderDFS(root.right);
    }

    /**
     * Brute-Force Approach: Using In-order Traversal
     * 
     * TC: O(2 x N + N x log(N)) ~ O(N x log(N))
     * SC: O(4 x N) ~ O(N)
     * 
     * @param root
     */
    private void correctBSTBruteForce(TreeNode root) {
        // current path (should have been sorted for BST)
        List<Integer> path = new ArrayList<Integer>(); // SC: O(N)
        inorder(root, path); // TC: O(N), SC: O(N)
        List<Integer> sorted = new ArrayList<Integer>(path); // SC: O(N)
        // expected sorted array for BST
        Collections.sort(sorted); // TC: O(N x log(N))
        int[] index = { 0 };
        inorderCorrection(root, sorted, index); // TC: O(N), SC: O(N)
    }

    /**
     * Inorder Traversal
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param root
     * @param path
     */
    private void inorder(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }
        inorder(root.left, path);
        path.add(root.val);
        inorder(root.right, path);
    }

    /**
     * Inorder Traversal
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param root
     * @param sorted
     * @param index
     */
    private void inorderCorrection(TreeNode root,
            List<Integer> sorted, int[] index) {
        if (root == null) {
            return;
        }
        inorderCorrection(root.left, sorted, index);
        root.val = sorted.get(index[0]);
        index[0]++;
        inorderCorrection(root.right, sorted, index);
    }
}
