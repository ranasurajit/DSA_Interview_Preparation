package Binary_Trees.P12_Sum_Root_To_Leaf_Numbers;

import java.util.ArrayList;
import java.util.List;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Sum_Root_To_Leaf_Numbers {
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 4, 9, 0, 5, 1 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        Sum_Root_To_Leaf_Numbers solution = new Sum_Root_To_Leaf_Numbers();
        int pathSum = solution.sumNumbers(root);

        // Print result
        System.out.println(pathSum);
    }

    /**
     * Using DFS Approach
     *
     * TC: O(N + K)
     * SC: O(N + K)
     * where K = number of possible paths
     * 
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>(); // SC: O(K)
        solve(root, 0, list);
        int sumTree = 0;
        for (Integer it : list) { // TC: O(K)
            sumTree += it;
        }
        return sumTree;
    }

    /**
     * TC: O(N)
     * SC: O(N)
     * 
     * @param root
     * @param sum
     * @param list
     */
    private void solve(TreeNode root, int sum, List<Integer> list) {
        if (root == null) {
            return;
        }
        sum = sum * 10 + root.val;
        // if node is a leaft node
        if (root.left == null && root.right == null) {
            list.add(sum);
        }
        // call recursion for left sub-tree
        solve(root.left, sum, list);
        // call recursion for right sub-tree
        solve(root.right, sum, list);
        // backtrack
        sum -= root.val;
    }
}
