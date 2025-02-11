package Binary_Trees.P11_Root_To_Leaf_Paths;

import java.util.ArrayList;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Root_To_Leaf_Paths {
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 10, 20, 30, 40, 60 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        Root_To_Leaf_Paths solution = new Root_To_Leaf_Paths();
        ArrayList<ArrayList<Integer>> allPaths = solution.paths(root);

        // Print result
        System.out.println(allPaths);
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(N) - visits each node exactly once
     * SC: O(N)
     * 
     * @param root
     * @return
     */
    private ArrayList<ArrayList<Integer>> paths(TreeNode root) {
        ArrayList<ArrayList<Integer>> allPaths = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> currentPath = new ArrayList<Integer>();
        solve(root, currentPath, allPaths);
        return allPaths;
    }

    /**
     * DFS Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param root
     * @param currentPath
     * @param allPaths
     */
    private void solve(TreeNode root, ArrayList<Integer> currentPath,
            ArrayList<ArrayList<Integer>> allPaths) {
        if (root == null) {
            return;
        }
        // if node is not leaf then only add the root value to currentPath
        currentPath.add(root.val);
        // if node is leaf node, then add the currentPath to allPaths
        if (root.left == null && root.right == null) {
            allPaths.add(new ArrayList<Integer>(currentPath));
        }
        solve(root.left, currentPath, allPaths);
        solve(root.right, currentPath, allPaths);
        // backtrack to explore other paths
        currentPath.remove(currentPath.size() - 1);
    }
}
