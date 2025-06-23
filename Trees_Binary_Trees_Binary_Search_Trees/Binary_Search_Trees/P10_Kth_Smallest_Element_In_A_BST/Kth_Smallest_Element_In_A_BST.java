package Trees_Binary_Trees_Binary_Search_Trees.Binary_Search_Trees.P10_Kth_Smallest_Element_In_A_BST;

import java.util.ArrayList;
import java.util.List;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Kth_Smallest_Element_In_A_BST {
    public static void main(String[] args) {
        Kth_Smallest_Element_In_A_BST solution = new Kth_Smallest_Element_In_A_BST();

        // Input array (LeetCode-style)
        Integer[] input1 = { 3, 1, 4, null, 2 };
        int k1 = 1;
        // Convert array to tree
        TreeNode root1 = TreeUtils.arrayToTree(input1);
        // Run your solution function
        int kthSmallest1 = solution.kthSmallestUsingArrayList(root1, k1);
        // Print result
        System.out.println("Result : " + kthSmallest1);

        // Input array (LeetCode-style)
        Integer[] input2 = { 5, 3, 6, 2, 4, null, null, 1 };
        int k2 = 3;
        // Convert array to tree
        TreeNode root2 = TreeUtils.arrayToTree(input2);
        // Run your solution function
        int kthSmallest2 = solution.kthSmallestOptimal(root2, k2);
        // Print result
        System.out.println("Result : " + kthSmallest2);
    }

    /**
     * Approach II : Using DFS Inorder Traversal K depth Approach (Without Space)
     *
     * TC: O(K)
     * SC: O(K)
     */
    public int kthSmallestOptimal(TreeNode root, int k) {
        int[] count = { 0 };
        int[] result = { -1 };
        dfsInorderBSTOptimal(root, k, count, result); // TC: O(K), SC: O(K)
        return result[0];
    }

    /**
     * Using DFS Inorder Traversal Based on K depth Approach
     *
     * TC: O(K)
     * SC: O(K)
     */
    private void dfsInorderBSTOptimal(TreeNode root, int k, int[] count, int[] result) {
        // Base Case
        if (root == null) {
            return;
        }
        dfsInorderBSTOptimal(root.left, k, count, result);
        count[0]++;
        if (count[0] == k) {
            result[0] = root.val;
            return;
        }
        dfsInorderBSTOptimal(root.right, k, count, result);
    }

    /**
     * Approach I : Using DFS Inorder Traversal Based on K depth Approach (With
     * Space)
     *
     * TC: O(K)
     * SC: O(2 x K) ~ O(K)
     */
    public int kthSmallestUsingArrayList(TreeNode root, int k) {
        List<Integer> result = new ArrayList<Integer>(); // SC: O(K)
        dfsInorderBST(root, k, result);
        return result.get(k - 1);
    }

    /**
     * Using DFS Inorder Traversal Based on K depth Approach
     *
     * TC: O(K)
     * SC: O(K)
     */
    private void dfsInorderBST(TreeNode root, int k, List<Integer> result) {
        // Base Case
        if (root == null) {
            return;
        }
        if (result.size() == k) {
            return;
        }
        dfsInorderBST(root.left, k, result);
        result.add(root.val);
        dfsInorderBST(root.right, k, result);
    }
}
