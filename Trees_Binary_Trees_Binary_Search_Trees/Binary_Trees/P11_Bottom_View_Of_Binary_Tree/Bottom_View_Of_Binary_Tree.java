package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P11_Bottom_View_Of_Binary_Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Bottom_View_Of_Binary_Tree {
    public static void main(String[] args) {
        Bottom_View_Of_Binary_Tree solution = new Bottom_View_Of_Binary_Tree();
        // Input array
        Integer[] input1 = { 10, 20, 30, 40, 60 };

        // Convert array to tree
        TreeNode root1 = TreeUtils.arrayToTree(input1);

        // Run your solution function
        ArrayList<Integer> bottomView1 = solution.bottomView(root1);

        // Print result
        System.out.println("Result: " + bottomView1);

        // Input array
        Integer[] input2 = { 1, 3, 2 };

        // Convert array to tree
        TreeNode root2 = TreeUtils.arrayToTree(input2);

        // Run your solution function
        ArrayList<Integer> bottomView2 = solution.bottomView(root2);

        // Print result
        System.out.println("Result: " + bottomView2);
    }

    /**
     * Approach : Using BFS + Hashing Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public ArrayList<Integer> bottomView(TreeNode root) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        if (root == null) {
            return path;
        }
        HashMap<Integer, Integer> nodeMap = new HashMap<Integer, Integer>(); // SC: O(N)
        Queue<Pair> queue = new LinkedList<Pair>(); // SC: O(N)
        queue.offer(new Pair(root, 0));
        int minCol = Integer.MAX_VALUE;
        while (!queue.isEmpty()) { // TC: O(N)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair current = queue.poll();
                TreeNode node = current.node;
                int col = current.col;
                minCol = Math.min(minCol, col);
                nodeMap.put(col, node.val);
                if (node.left != null) {
                    queue.offer(new Pair(node.left, col - 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, col + 1));
                }
            }
        }
        int n = nodeMap.size();
        int count = 0;
        int start = minCol;
        while (count < n) { // TC: O(N)
            path.add(nodeMap.get(start));
            start++;
            count++;
        }
        return path;
    }

    static class Pair {
        TreeNode node;
        int col;

        public Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }
}
