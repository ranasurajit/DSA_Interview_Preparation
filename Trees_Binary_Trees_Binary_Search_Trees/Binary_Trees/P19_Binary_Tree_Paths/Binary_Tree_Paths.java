package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P19_Binary_Tree_Paths;

import java.util.ArrayList;
import java.util.List;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Binary_Tree_Paths {
    public static void main(String[] args) {
        Binary_Tree_Paths solution = new Binary_Tree_Paths();

        // Input array
        Integer[] input = { 1, 2, 3, null, 5 };
        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);
        // Run your solution function
        List<String> paths = solution.binaryTreePaths(root);
        // Print result
        System.out.println("Result: " + paths);
    }

    /**
     * Approach : Using DFS Traversal + Recursion Approach
     * 
     * TC: O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        dfsTree(root, sb, paths);
        return paths;
    }

    /**
     * Using DFS Traversal + Recursion Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private void dfsTree(TreeNode node, StringBuilder sb, List<String> paths) {
        // Base Case
        if (node == null) {
            return;
        }
        // Recursion Calls
        int size = sb.length();
        sb.append(node.val).append("->"); // modify
        if (node.left == null && node.right == null) {
            // leaf node
            // Induction
            sb.setLength(sb.length() - 2); // removing extra '->'
            paths.add(sb.toString());
        } else {
            // Hypothesis
            dfsTree(node.left, sb, paths); // explore
            dfsTree(node.right, sb, paths); // explore
        }
        sb.setLength(size); // backtrack
    }
}
