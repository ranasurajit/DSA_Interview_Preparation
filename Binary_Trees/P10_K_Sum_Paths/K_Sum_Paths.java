package Binary_Trees.P10_K_Sum_Paths;

import java.util.HashMap;
import java.util.Map;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class K_Sum_Paths {
    public static void main(String[] args) {
        // Input array (LeetCode-style)
        Integer[] input = { 8, 4, 5, 3, 2, null, 2, 3, -2, null, 1, null, null, null, null };
        int k = 7;

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        K_Sum_Paths solution = new K_Sum_Paths();
        int kSum = solution.sumK(root, k);

        // Print result
        System.out.println(kSum);
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(N), as all nodes are visited exactly once
     * SC: O(N)
     * 
     * @param root
     * @param k
     * @return
     */
    public int sumK(TreeNode root, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1); // needed to get offset sum = k
        return solve(root, k, 0, map);
    }

    /**
     * TC: O(N)
     * SC: O(N)
     * 
     * @param root
     * @param k
     * @param sum
     * @param map
     * @return
     */
    private int solve(TreeNode root, int k, int sum, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        sum += root.val;
        int count = 0;
        if (map.containsKey(sum - k)) {
            count += map.get(sum - k);
        }
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        count += solve(root.left, k, sum, map);
        count += solve(root.right, k, sum, map);
        int freq = map.get(sum);
        if (freq == 1) {
            map.remove(sum);
        } else {
            map.put(sum, freq - 1);
        }
        return count;
    }
}
