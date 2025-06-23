package Trees_Binary_Trees_Binary_Search_Trees.Binary_Search_Trees.P9_Two_Sum_IV_Input_Is_A_BST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Two_Sum_IV_Input_Is_A_BST {
    public static void main(String[] args) {
        Two_Sum_IV_Input_Is_A_BST solution = new Two_Sum_IV_Input_Is_A_BST();

        // Input array (LeetCode-style)
        Integer[] input1 = { 5, 3, 6, 2, 4, null, 7 };
        int k1 = 9;
        // Convert array to tree
        TreeNode root1 = TreeUtils.arrayToTree(input1);
        // Run your solution function
        boolean isSumPresent1 = solution.findTargetBruteForce(root1, k1);
        // Print result
        System.out.println("Result : " + isSumPresent1);

        // Input array (LeetCode-style)
        Integer[] input2 = { 5, 3, 6, 2, 4, null, 7 };
        int k2 = 28;
        // Convert array to tree
        TreeNode root2 = TreeUtils.arrayToTree(input2);
        // Run your solution function
        boolean isSumPresent2 = solution.findTargetOptimal(root2, k2);
        // Print result
        System.out.println("Result : " + isSumPresent2);
    }

    /**
     * Approach : Using DFS Traversal + Hashing + Recursion Approach
     *
     * TC: O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public boolean findTargetOptimal(TreeNode root, int k) {
        Set<Integer> set = new HashSet<Integer>(); // SC: O(N)
        return solveRecursion(root, k, set);
    }

    /**
     * Using DFS Traversal + Hashing + Recursion Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private boolean solveRecursion(TreeNode root, int k, Set<Integer> set) {
        // Base Case
        if (root == null) {
            return false;
        }
        // Induction
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        // Hypothesis
        return solveRecursion(root.left, k, set) || solveRecursion(root.right, k, set);
    }

    /**
     * Approach I : Using Inorder Traversal (Left Root Right) + Two Pointers
     * Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public boolean findTargetBruteForce(TreeNode root, int k) {
        List<Integer> inorder = new ArrayList<Integer>(); // SC: O(N)
        dfsBSTTree(root, inorder); // TC: O(N), SC: O(N)
        // By property of BST, inorder of BST is always sorted, so applying Two Pointers
        // Approach
        int p = 0;
        int q = inorder.size() - 1;
        while (p < q) { // TC: O(N)
            int sum = inorder.get(p) + inorder.get(q);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                p++;
            } else {
                q--;
            }
        }
        return false;
    }

    /**
     * Using Inorder Traversal (Left Root Right) Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private void dfsBSTTree(TreeNode root, List<Integer> inorder) {
        // Base Case
        if (root == null) {
            return;
        }
        // Recursion Calls
        dfsBSTTree(root.left, inorder);
        inorder.add(root.val);
        dfsBSTTree(root.right, inorder);
    }
}
