package Binary_Trees.P7_Construct_Tree_From_Inorder_And_Preorder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Binary_Trees.Utils.TreeUtils;
import Binary_Trees.Utils.TreeUtils.TreeNode;

public class Construct_Tree_From_Inorder_And_Preorder {
    public static void main(String[] args) {
        int[] inorder = { 3, 1, 4, 0, 2, 5 };
        int[] preorder = { 0, 1, 3, 4, 2, 5 };

        TreeNode root = buildTree(inorder, preorder);
        Integer[] result = TreeUtils.treeToArray(root);

        System.out.println(Arrays.toString(result));
    }

    /**
     * TC: O(N)
     * SC: O(N)
     * 
     * @param inorder
     * @param preorder
     * @return
     */
    public static TreeNode buildTree(int inorder[], int preorder[]) {
        // pre-order means - Root Left Right, so root of our Tree is 0th index of
        // pre-order
        // in-order means - Left Root Right so we need to find root value in this and
        // split
        int n = inorder.length;
        Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            inorderMap.put(inorder[i], i);
        }
        int[] index = { 0 };
        return solve(inorder, preorder, index, 0, n - 1, inorderMap);
    }

    /**
     * TC: O(N)
     * SC: O(N)
     * 
     * @param inorder
     * @param preorder
     * @param index
     * @param start
     * @param end
     * @param inorderMap
     * @return
     */
    private static TreeNode solve(int[] inorder, int[] preorder, int[] index,
            int start, int end, Map<Integer, Integer> inorderMap) {
        if (start > end) {
            return null;
        }
        int rootValue = preorder[index[0]];
        index[0]++;
        TreeNode root = new TreeNode(rootValue);
        int p = inorderMap.get(rootValue);
        root.left = solve(inorder, preorder, index, start, p - 1, inorderMap);
        root.right = solve(inorder, preorder, index, p + 1, end, inorderMap);
        return root;
    }
}
