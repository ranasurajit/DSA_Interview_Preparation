package Trees_Binary_Trees_Binary_Search_Trees.Binary_Trees.P10_Top_View_Of_Binary_Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeNode;
import Trees_Binary_Trees_Binary_Search_Trees.Utils.TreeUtils;

public class Top_View_Of_Binary_Tree {
    public static void main(String[] args) {
        Top_View_Of_Binary_Tree solution = new Top_View_Of_Binary_Tree();
        // Input array
        Integer[] input1 = { 10, 20, 30, 40, 60, 90, 100 };

        // Convert array to tree
        TreeNode root1 = TreeUtils.arrayToTree(input1);

        // Run your solution function
        ArrayList<Integer> topView1 = solution.topViewBFSSorting(root1);

        // Print result
        System.out.println("Result: " + topView1);

        // Input array
        Integer[] input2 = { 1, 2, 3, null, 4, null, null, null, 5, null, 6 };

        // Convert array to tree
        TreeNode root2 = TreeUtils.arrayToTree(input2);

        // Run your solution function
        ArrayList<Integer> topView2 = solution.topViewBFSOptimal(root2);

        // Print result
        System.out.println("Result: " + topView2);
    }

    /**
     * Approach II : Using BFS + Hashing Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     */
    private ArrayList<Integer> topViewBFSOptimal(TreeNode root) {
        ArrayList<Integer> path = new ArrayList<Integer>();
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
                if (!nodeMap.containsKey(col)) {
                    nodeMap.put(col, node.val);
                }
                if (node.left != null) {
                    queue.offer(new Pair(node.left, col - 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, col + 1));
                }
            }
        }
        int n = nodeMap.size();
        int key = minCol;
        int count = 0;
        while (count < n) { // TC: O(N)
            path.add(nodeMap.get(key));
            key++;
            count++;
        }
        return path;
    }

    /**
     * Approach I : Using BFS + Sorting Approach
     * 
     * TC: O(N x log(N) + 2 x N) ~ O(N x log(N))
     * SC: O(2 x N) ~ O(N)
     */
    private ArrayList<Integer> topViewBFSSorting(TreeNode root) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        List<int[]> nodePair = new ArrayList<int[]>(); // SC: O(N)
        Queue<Pair> queue = new LinkedList<Pair>(); // SC: O(N)
        queue.offer(new Pair(root, 0));
        while (!queue.isEmpty()) { // TC: O(N)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair current = queue.poll();
                TreeNode node = current.node;
                int col = current.col;
                nodePair.add(new int[] { node.val, col });
                if (node.left != null) {
                    queue.offer(new Pair(node.left, col - 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, col + 1));
                }
            }
        }
        nodePair.sort((int[] a, int[] b) -> a[1] - b[1]); // TC: O(N x log(N))
        int prevCol = Integer.MIN_VALUE;
        for (int[] node : nodePair) { // TC: O(N)
            int col = node[1];
            if (col != prevCol) {
                path.add(node[0]);
            }
            prevCol = col;
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
