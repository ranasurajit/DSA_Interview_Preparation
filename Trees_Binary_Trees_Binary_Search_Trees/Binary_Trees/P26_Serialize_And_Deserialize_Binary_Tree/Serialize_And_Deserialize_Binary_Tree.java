package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P26_Serialize_And_Deserialize_Binary_Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Serialize_And_Deserialize_Binary_Tree {
    public static void main(String[] args) {
        Serialize_And_Deserialize_Binary_Tree solution = new Serialize_And_Deserialize_Binary_Tree();

        // Input array (LeetCode-style)
        Integer[] input = { 1, 2, 3, null, null, 4, 5 };

        // Convert array to tree
        TreeNode root = TreeUtils.arrayToTree(input);

        // Run your solution function
        String serializedTree = solution.serialize(root);
        TreeNode deSerializedTree = solution.deserialize(serializedTree);
        // Print result
        System.out.println("Serialized Tree : " + serializedTree);
        System.out.println("De-Serialized Tree : " + Arrays.toString(TreeUtils.treeToArray(deSerializedTree)));
    }

    // Encodes a tree to a single string.
    /**
     * Approach : Using Recursion + DFS (Pre-order Traversal) Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfsPreOrderTree(root, sb);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Using Recursion + DFS (Pre-order Traversal) Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private void dfsPreOrderTree(TreeNode root, StringBuilder sb) {
        // Base Case
        if (root == null) {
            sb.append("null,");
            return;
        }
        // Induction
        sb.append(root.val + ",");
        // Hypothesis
        dfsPreOrderTree(root.left, sb);
        dfsPreOrderTree(root.right, sb);
    }

    // Decodes your encoded data to tree.
    /**
     * Approach : Using Recursion + DFS (Pre-order Traversal) Approach
     *
     * TC: O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public TreeNode deserialize(String data) {
        String[] nodeData = data.split(",");
        List<String> nodeList = new LinkedList<String>(Arrays.asList(nodeData)); // SC: O(N)
        return dfsTreeDeserialize(nodeList);
    }

    /**
     * Using Recursion + DFS (Pre-order Traversal) Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    private TreeNode dfsTreeDeserialize(List<String> nodeList) {
        // Base Case
        if (nodeList.get(0).equals("null")) {
            return null;
        }
        // Using Pre-order Traversal - Node Left Right
        TreeNode root = new TreeNode(Integer.valueOf(nodeList.get(0)));
        nodeList.remove(0);
        root.left = dfsTreeDeserialize(nodeList);
        nodeList.remove(0);
        root.right = dfsTreeDeserialize(nodeList);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
